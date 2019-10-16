$(function () {

    // parametros de URL
    const urlParams = new URLSearchParams(window.location.search);

    // encabezados de la grilla
    let numbers = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"];
    let letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];

    // posiciones de todos los barcos del jugador
    let shipLocations = [];

    // posiciones de todos los salvos lanzados
    let salvoLocations = [];

    // genera el HTML de los encabezados de la grilla
    function getHeadersHtml(headers) {
        return "<tr><th></th>" + headers.map(function (header) {
            return "<th>" + header + "</th>";
        }).join("") + "</tr>";
    }

    // dibuja los encabezados
    function renderHeaders(numbersId) {
        var html = getHeadersHtml(numbers);
        document.getElementById(numbersId).innerHTML = html;
    }

    // genera el HTML de las columnas
    function getColumnsHtml(i, locations, color) {
        let html = "";
        for (let j = 0; j < numbers.length; j++) {
            let cellColor = "darkblue";
            for (let k = 0; k < locations.length; k++) {
                if (locations[k] == letters[i] + numbers[j]) {
                    cellColor = color;
                }
            }
            html = html + "<td style='background-color: " + cellColor + "'></td>";
        }
        return html;
    }

    // genera el HTML de las filas (depende de getColumnsHtml)
    function getRowsHtml(locations, color) {
        let html = "";
        for (let i = 0; i < letters.length; i++) {
            html = html + "<tr><th>" + letters[i] + "</th>" + getColumnsHtml(i, locations, color) + "</tr>";
        }
        return html;
    }

    // dibuja las filas de la grilla
    function renderRows(locations, rowsId, color) {
        var html = getRowsHtml(locations, color);
        document.getElementById(rowsId).innerHTML = html;
    }

    // dibuja las filas de la grilla
    function renderRows(locations, rowsId, color) {
        var html = getRowsHtml(locations, color);
        document.getElementById(rowsId).innerHTML = html;
    }

    // dibuja la grilla de barcos
    function renderShipTable(shipLocations) {
        renderHeaders("ship-grid-numbers");
        renderRows(shipLocations, "ship-grid-rows", "gray");
    }

    // dibuja la grilla de salvos
    function renderSalvoTable(salvoLocations) {
        renderHeaders("salvo-grid-numbers");
        renderRows(salvoLocations, "salvo-grid-rows", "orange");
    }

    // muestra los datos de los jugadores de la partida
    function showPlayersData(data) {
        let thisPlayer;
        let otherPlayer;
        let gamePlayer1 = data.gamePlayers[0];
        let gamePlayer2 = data.gamePlayers[1];
        // según el ID del gameplayer actual asigna thisPlayer al jugador correspondiente, otherPlayer al contrincante
        if (gamePlayer1.id == urlParams.get('gp')) {
            thisPlayer = gamePlayer1.player.email;
            otherPlayer = gamePlayer2.player.email;
        } else {
            thisPlayer = gamePlayer2.player.email;
            otherPlayer = gamePlayer1.player.email;
        }
        document.getElementById("players-data").innerHTML = thisPlayer + " (you) vs " + otherPlayer;
    }

    // recibe los datos del gameplayer y setea en el array locations las posiciones de todos los barcos del jugador
    function setShipLocations(data) {
        mappedLocations = data.ships.map(function (ship) {
            return ship.locations
        });
        shipLocations = [].concat.apply([], mappedLocations);
    }

    // recibe los datos del gameplayer y setea en el array locations las posiciones de todos los salvos lanzados
    function setSalvoLocations(data) {
        mappedLocations = data.salvoes.map(function (salvo) {
            return salvo.locations
        });
        salvoLocations = [].concat.apply([], mappedLocations);
    }

    // carga los datos del gameplayer según el parámetro 'gp' en la URL y llama a los métodos que dibujan la grilla
    function loadData() {
        $.get("/api/game_view/" + urlParams.get('gp'))
            .done(function (data) {
                setShipLocations(data);
                setSalvoLocations(data);
                showPlayersData(data);
                renderShipTable(shipLocations);
                renderSalvoTable(salvoLocations);
            })
            .fail(function (jqXHR, textStatus) {
                alert("Failed: " + textStatus);
            });
    }

    loadData();

});