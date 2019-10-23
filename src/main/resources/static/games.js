$(function () {

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
        let htmlList = data.map(function (games) {
            return '<li>' + new Date(games.created).toLocaleString() + ' ' + games.gamePlayers.map(function (p) {
                return p.player.email
            }).join(', ') + '</li>';
        }).join('');
        document.getElementById("game-list").innerHTML = htmlList;
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

    loadData();
    loadLeaderBoardData();
});