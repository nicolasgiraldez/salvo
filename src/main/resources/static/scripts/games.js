$(document).ready(function () {

    let currentUser = "Guest";
    let currentUserGamePlayerId;

    function createLeaderBoardTable(leaderboard) {

        // Encabezados de las columnas
        let columns = ["Name", "Total", "Won", "Lost", "Tied"];

        // Creamos la tabla
        let table = document.createElement("table");

        // Creamos el encabezado

        let tr = table.insertRow(-1);

        for (var i = 0; i < columns.length; i++) {
            var th = document.createElement("th");
            th.innerHTML = columns[i];
            tr.appendChild(th);
        }

        // Agregamos los datos en las filas
        for (var i = 0; i < leaderboard.length; i++) {

            let nameCell, totalCell, wonCell, lostCell, tiedCell;

            tr = table.insertRow(-1);

            nameCell = tr.insertCell(-1);
            nameCell.innerHTML = leaderboard[i].name;
            totalCell = tr.insertCell(-1);
            totalCell.innerHTML = leaderboard[i].score.total;
            wonCell = tr.insertCell(-1);
            wonCell.innerHTML = leaderboard[i].score.won;
            lostCell = tr.insertCell(-1);
            lostCell.innerHTML = leaderboard[i].score.lost;
            tiedCell = tr.insertCell(-1);
            tiedCell.innerHTML = leaderboard[i].score.tied;

        }

        // Agregamos la nueva tabla al contenedor
        var divContainer = document.getElementById("leaderboard");
        divContainer.innerHTML = "";
        divContainer.appendChild(table);
    }

    // display list
    function updateList(data) {
        let htmlList = data.games.map(function (game) {

            let joinButtonHtml = "";
            if (game.gamePlayers.length < 2) {
                joinButtonHtml = '<button class="join-game-button" data-gameid="' + game.id + '">Join</button>';
            }

            // si el jugador actual es uno de los gameplayers de un juego, muestra el link al juego en la lista
            if (game.gamePlayers.map(function (gamePlayer) {
                if (gamePlayer.player.email == currentUser) {
                    currentUserGamePlayerId = gamePlayer.id;
                }
                return gamePlayer.player.email
            }).includes(currentUser)) {
                return '<a href="game.html?gp=' + currentUserGamePlayerId + '"><li>' +
                    new Date(game.created).toLocaleString() + ' ' + game.gamePlayers.map(function (gamePlayer) {
                        return gamePlayer.player.email
                    }).join(', ') + '</li></a>';
            }

            // sino, muestra los datos del juego sin el link

            return '<li>' + new Date(game.created).toLocaleString() + ' ' + game.gamePlayers.map(function (gamePlayer) {
                return gamePlayer.player.email
            }).join(', ') + joinButtonHtml + '</li>';
        }).join('');
        document.getElementById("game-list").innerHTML = htmlList;

        $('.join-game-button').on('click', function (event) {
            event.preventDefault();
            $.post("/api/game/" + $(this).data('gameid') + "/players")
                .done(function (data) {
                    console.log(data);
                    console.log("joined game");
                    loadData();
                })
                .fail(function (data) {
                    console.log("game join failed");
                });
        });
    }

    // load and display JSON sent by server for /api/games
    function loadData() {
        $.get("/api/games")
            .done(function (data) {
                updateList(data);
            })
            .fail(function (jqXHR, textStatus) {
                alert("Failed: " + textStatus);
            });
    }

    // cargamos y mostramos la tabla de puntajes
    function loadLeaderBoardData() {
        $.get("/api/leaderboard")
            .done(function (leaderboard) {
                createLeaderBoardTable(leaderboard);
            })
            .fail(function (jqXHR, textStatus) {
                alert("Failed: " + textStatus);
            });
    }

    $("#login-form").on("submit", function (event) {

        event.preventDefault()

        $.post("/api/login",
            {
                email: $("#login-email").val(),
                password: $("#login-password").val()
            })
            .done(function () {
                console.log("login ok");
                updateCurrentUser();
            })
            .fail(function () {
                console.log("login failed");
            });

    });

    $("#logout-button").on("click", function (event) {

        event.preventDefault()

        $.post("/api/logout")
            .done(function () {
                console.log("logout ok");
                updateCurrentUser();
            })
            .fail(function () {
                console.log("logout failed");
            });

    });

    $("#signup-form").on("submit", function (event) {

        event.preventDefault()

        $.post("/api/players",
            {
                name: $("#signup-name").val(),
                email: $("#signup-email").val(),
                password: $("#signup-password").val()
            })
            .done(function () {
                console.log("signup ok");
                $.post("/api/login",
                    {
                        email: $("#signup-email").val(),
                        password: $("#signup-password").val()
                    })
                    .done(function () {
                        console.log("login ok");
                        updateCurrentUser();
                    })
                    .fail(function () {
                        console.log("login failed");
                    });
            })
            .fail(function () {
                console.log("signup failed");
            });

    });

    $('#new-game-button').on('click', function (event) {
        event.preventDefault();
        $.post("/api/games")
            .done(function (data) {
                console.log(data);
                console.log("game created");
                loadData();
            })
            .fail(function (data) {
                console.log("game creation failed");
            });
    });

    // actualiza el usuario actual y según si está logueado o no, muestra los formularios de login/logout/signup
    function updateCurrentUser() {
        $.get("/api/myusername")
            .done(function (username) {
                $("#current-user").text("Current user: " + username);
                currentUser = username;
                if (username != "Guest") {
                    $("#login-form").hide();
                    $("#logout-button").show();
                    $("#signup-form").hide();
                } else {
                    $("#login-form").show();
                    $("#logout-button").hide();
                    $("#signup-form").show();
                }
                loadData();
            })
            .fail(function (jqXHR, textStatus) {
                alert("Failed: " + textStatus);
            });
    }

    loadData();
    loadLeaderBoardData();
    updateCurrentUser();
});