'use strict';
var app = angular.module('websocket-callback-demo', ['angular.websocket.callback', 'highcharts-ng']);

var PerformanceController = function ($scope, $window, $http, WebSocketService) {
    $scope.nrOfRequests = 1000;
    $scope.websocketTotals = [];
    $scope.servletTotals = [];
    $scope.isLoading = false;
    $scope.status = [];
    var websocketTotal = 0;
    var servletTotal = 0;

    WebSocketService.connect(getWSUrl());
    $scope.sendRequests = function()  {
        $scope.isLoading = true;
        websocketTotal = 0;
        servletTotal = 0;
        $scope.status = [];
        sendViaCallbackWebSocket();
    };

    function sendViaCallbackWebSocket() {
        var websocketResponses = $scope.nrOfRequests;
        var start = new Date().getTime();
        for(var i = 0; i < $scope.nrOfRequests; i++) {
            WebSocketService.post("/helloViaWebSocket", i).then(function() {
                websocketResponses--;
                if (websocketResponses === 0) {
                    websocketTotal = new Date().getTime() - start;
                    $scope.websocketTotals.push(websocketTotal);
                    callbackDone();
                }
            }, function(error) {
                alert(error);
            })
        }
    }

    function callbackDone() {
        $scope.status.push("Callback WebSocket done in " + websocketTotal + "ms");
        sendViaServlet();
    }

    function sendViaServlet() {
        var servletResponses = $scope.nrOfRequests;
        var start = new Date().getTime();
        for(var i = 0; i < $scope.nrOfRequests; i++) {
            $http.post('/websocket/helloViaServlet', i).then(function() {
                servletResponses--;
                if (servletResponses === 0) {
                    servletTotal = new Date().getTime() - start;
                    $scope.servletTotals.push(servletTotal);
                    servletDone();
                }
            }, function(error) {
                alert(error);
            })
        }
    }

    function servletDone(){
        $scope.status.push("Standard ajax done in " + servletTotal+"ms");
        $scope.isLoading = false;
        $scope.status.push("Callback WebSocket is " + (100 - ((websocketTotal * 100) / servletTotal)).toFixed(2) + "% faster");
    }

    function getWSUrl() {
        var appPath = $window.location.pathname.split('/')[1];
        var host = $window.location.hostname;
        var protocol = "ws";
        var port = '8080';
        return protocol + '://'+ host + ':'+ port + '/' + appPath + '/websocket/';
    }

    $scope.chartConfig = {
        options: {
            chart: {
                type: 'line',
                zoomType: 'x'
            }
        },
        series: [{
            data: $scope.websocketTotals,
            name: "callback-websocket"
        },{
            data: $scope.servletTotals,
            name: "standard ajax"
        }],
        title: {
            text: 'Performance comparison chart'
        },
        xAxis: {currentMin: 0, minRange: 1},
        yAxis: {title:{text:"ms"}},
        loading: false
    }
};

app.controller("PerformanceController", PerformanceController);