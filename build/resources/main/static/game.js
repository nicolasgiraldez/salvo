

function paramObj(search) {
    var obj = {};
    var reg = /(?:[?&]([^?&#=]+)(?:=([^&#]*))?)(?:#.*)?/g;

    search.replace(reg, function(match, param, val) {
        obj[decodeURIComponent(param)] = val === undefined ? "" : decodeURIComponent(val);
    });

    return obj;
}


let numbers = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"];
let letters = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];
let locations = ["A1", "B4", "C2"];

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
        let cellContent = "";
        for (let k = 0; k < locations.length; k++) {
            if (locations[k] == letters[i] + numbers[j]) {
                cellContent = "si";
            }
        }
        html = html + "<td>" + cellContent + "</td>";
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

renderTable();
