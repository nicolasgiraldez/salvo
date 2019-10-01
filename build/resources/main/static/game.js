$(function() {

    const urlParams = new URLSearchParams(window.location.search);

    let numbers = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"];
    let letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];
    let locations = [];

    function getHeadersHtml(headers) {
        return "<tr><th></th>" + headers.map(function(header) {
            return "<th>" + header + "</th>";
        }).join("") + "</tr>";
    }

    function renderHeaders() {
        var html = getHeadersHtml(numbers);
        document.getElementById("grid-numbers").innerHTML = html;
    }

    function getColumnsHtml(i) {
        let html = "";
        for (let j = 0; j < numbers.length; j++) {
            //let cellContent = "";
            let cellColor = "darkblue";
            for (let k = 0; k < locations.length; k++) {
                if (locations[k] == letters[i] + numbers[j]) {
                    //cellContent = "si";
                    cellColor = "gray";
                }
            }
            html = html + "<td style='background-color: " + cellColor + "'></td>";
        }
        return html;
    }

    function getRowsHtml() {
        let html = "";
        for (let i = 0; i < letters.length; i ++) {
            html = html + "<tr><th>" + letters[i] + "</th>" + getColumnsHtml(i) + "</tr>";
        }
        return html;
    }

    function renderRows() {
        var html = getRowsHtml();
        document.getElementById("grid-rows").innerHTML = html;
    }

    function renderTable() {
        renderHeaders();
        renderRows();
    }

    function setLocations(data) {
        mappedLocations = data.ships.map(function(ship) { return ship.locations });
        locations = [].concat.apply([], mappedLocations);
    }

    function loadData() {
        $.get("/api/game_view/"+urlParams.get('gp'))
            .done(function(data) {
                //console.log(data);
                setLocations(data);
                renderTable();
            })
            .fail(function( jqXHR, textStatus ) {
                alert( "Failed: " + textStatus );
            });
    }

    loadData();

});