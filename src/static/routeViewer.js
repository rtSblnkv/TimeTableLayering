var map = L.map('map').setView([-37.7738026,144.9836466], 13);

var mapMarkers = [];
var mapPolylines = [];

L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token={accessToken}', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox/streets-v11',
    tileSize: 512,
    zoomOffset: -1,
    accessToken: 'pk.eyJ1IjoicnQxNDg4IiwiYSI6ImNrd21oMXh5dzEzMzQydnBkMmczYnFkbm8ifQ.yo6Bx5cgo5vJp7KRD-Ii6Q'
}).addTo(map);


var markerNS = L.marker([-37.7738026,144.9836466]).addTo(map);
var markerTP = L.marker([-37.8618349,144.905716]).addTo(map);
var markerBK = L.marker([-37.8158343,145.04645]).addTo(map);

function loadJSON(fileName,callback){
    var xobj = new XMLHttpRequest();
    xobj.overrideMimeType("application/json");
    xobj.open('GET', fileName, true);
    xobj.onreadystatechange = function () {
        if (xobj.readyState == 4 && xobj.status == "200") {
            callback(xobj.responseText);
        }
    };
    xobj.send(null);
}

function displayNode(node,icon){
    var marker = L.marker([node["latitude"],node["longtitude"]],{icon:icon});
    marker.addTo(map);
    this.mapMarkers.push(marker);
    return false;
}

function displayRoute(route,color,icon){
    console.log(route);
    displayNode(route[route.length - 1],icon);
    var polylinePoints = [];
    route.forEach(function(item){
        polylinePoints.push([item["latitude"],item["longtitude"]]);
    });
    var polyline = L.polyline(polylinePoints,{color:color}).addTo(map);
    this.mapPolylines.push(polyline);
    map.fitBounds(polyline.getBounds());
    return false;
}

function displayRoutes(response,color,icon){
    for(var key in response){
        displayRoute(response[key]["route"],color,icon);
    }
    return false;
}

function display(fileName,color,icon) {
    loadJSON(fileName,function(response) {
        var actual_JSON = JSON.parse(response);
        console.log(actual_JSON);
        displayRoutes(actual_JSON,color,icon);
        return false;
    });
}
function clearMap(){
    if(mapMarkers.length != 0){
        console.log(mapMarkers);
        for(var marker in this.mapMarkers){
            this.map.removeLayer(marker);
        }
    }
    if(mapPolylines.length != 0) {
        console.log(mapPolylines);
        for (var polyline in this.mapPolylines) {
            this.map.removeLayer(polyline);
        }
    }
}


function routing(){
    //clearMap();
    var branch = $("#filter_branch option:selected").text();
    var type =  $("#filter_type option:selected").text();
    var color = $("#filter_color option:selected").text();
    var alg = $("#filter_alg option:selected").text();

    var fileName = "./json/" + branch + "_BranchCode" + type + alg +".json";

    var branchIcon = L.icon({
        iconUrl: "./images/BranchIcon" + color +".png",
        iconSize:     [25, 45], // size of the icon
    });

    display(fileName,color,branchIcon);
    return false;
}

$(document).ready(function(){
    $(".filter_button").on("click",routing);
    $(".clear_button").on("click",clearMap);
});




