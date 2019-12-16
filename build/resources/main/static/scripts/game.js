let data;
let player;
let opponent;
let params = new URLSearchParams(location.search);
let gamePlayer = params.get('gp');

getGameData(gamePlayer);

function getGameData(gpId) {

    document.getElementById("dock").innerHTML = '<div id="display"><p>Welcome...</p></div><div id="board"></div>';
    document.getElementById("grid-ships").innerHTML = "";
    document.getElementById("grid-salvoes").innerHTML = "";

    createGrid(11, document.getElementById('grid-ships'), 'ships');


    fetch('/api/game_view/' + gpId)
        .then(res => {
            if (res.ok) {
                return res.json();

            } else {
                throw new Error(res.statusText)
            }
        })
        .then(json => {
            data = json;

            if (data.ships.length > 0) {
                getShips(data.ships);
                createGrid(11, document.getElementById('grid-salvoes'), 'salvoes');
                document.getElementById("board").innerHTML += '<div class="hide" id="fire"><button class="btn" onclick="">Fire!</button></div>';
                document.getElementById("board").innerHTML += '<div><button class="btn" onclick="gridView(event)">View Salvoes</button></div>';
            } else {
                document.getElementById("board").innerHTML += '<div><button class="btn" onclick="addShips()">Add Ships</button></div>';
                createShips('carrier', 5, 'horizontal', document.getElementById('dock'), false);
                createShips('battleship', 4, 'horizontal', document.getElementById('dock'), false);
                createShips('submarine', 3, 'horizontal', document.getElementById('dock'), false);
                createShips('destroyer', 3, 'horizontal', document.getElementById('dock'), false);
                createShips('patrol_boat', 2, 'horizontal', document.getElementById('dock'), false);
            }

            data.gamePlayers.forEach(e => {
                if (e.id == gamePlayer) {
                    player = e.player;
                } else {
                    opponent = e.player;
                }
            })
            if (data.salvoes.length > 0) {
                getSalvoes(data.salvoes, player.id)
            }

        })
        .catch(error => console.log(error))

}

function getShips(ships) {

    ships.forEach(ship => {

        createShips(ship.type,
            ship.locations.length,
            ship.locations[0][0] == ship.locations[1][0] ? "horizontal" : "vertical",
            document.getElementById(`ships${ship.locations[0]}`),
            true
        )

    })
}

function getSalvoes(salvoes, playerId) {
    salvoes.forEach(salvo => {
        salvo.locations.forEach(loc => {
            if (salvo.player == playerId) {
                let cell = document.getElementById("salvoes" + loc);
                cell.style.background = "red";
                cell.innerText = salvo.turn;
            } else {
                let cell = document.getElementById("ships" + loc);
                if (cell.classList.contains('busy-cell')) {
                    cell.style.background = "red";
                }

            }
        })

    })
}

function addShips() {
    let ships = [];

    document.querySelectorAll(".grid-item").forEach(item => {
        let ship = {};

        ship.type = item.id;
        ship.locations = [];

        if (item.dataset.orientation == "horizontal") {
            for (let i = 0; i < item.dataset.length; i++) {
                ship.locations.push(item.dataset.y + (parseInt(item.dataset.x) + i));
            }
        } else {
            for (let i = 0; i < item.dataset.length; i++) {
                ship.locations.push(String.fromCharCode(item.dataset.y.charCodeAt() + i) + item.dataset.x);
            }
        }

        ships.push(ship);
    });

    sendShips(ships, gamePlayer)
}

function sendShips(ships, gamePlayerId) {
    console.log(JSON.stringify(ships));
    let url = '/api/games/players/' + gamePlayerId + '/ships';
    let init = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(ships)
    };
    fetch(url, init)
        .then(res => {
            if (res.ok) {
                return res.json();
            } else {
                return Promise.reject(res.json());
            }
        })
        .then(json => {

            getGameData(gamePlayer);
        })
        .catch(error => error)
        .then(error => console.log(error))

}


function gridView(ev) {

	ev.target.innerText = ev.target.innerText == "View Salvoes" ? "View Ships" : "View Salvoes";

    document.querySelectorAll(".grid").forEach(grid => grid.classList.toggle("active"));
    document.getElementById("fire").classList.toggle("hide");
}
