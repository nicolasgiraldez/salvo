$(function () {

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

    loadData();
});