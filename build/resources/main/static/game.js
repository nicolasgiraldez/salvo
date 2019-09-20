
let headers = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10"];

function getHeadersHtml(headers) {
    return "<tr><th></th>" + headers.map(function(number) {
        return "<th>" + number + "</th>";
    }).join("") + "</tr>";
}

function renderHeaders(headers) {
    var html = getHeadersHtml(headers);
    document.getElementById("grid-numbers").innerHTML = html;
}

renderHeaders();