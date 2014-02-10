'use strict';
var app = angular.module('websocket-callback-demo', ['angular.websocket.callback']);

var DemoController = function ($scope, $window, WebSocketService) {
    $scope.firstName = "";
    $scope.response = "";
    $scope.isLoading = false;

    WebSocketService.connect(getWSUrl());
    $scope.send = function() {
        $scope.isLoading = true;
        WebSocketService.post("/helloworld", $scope.firstName).then(function(response) {
            $scope.response = response;
            $scope.isLoading = false;
        }, function(error) {
            console.log("ERROR: " + error);
            $scope.isLoading = false;
        })
    };

    function getWSUrl() {
        var appPath = $window.location.pathname.split('/')[1];
        var host = $window.location.hostname;
        var protocol = "ws";
        var port = '8080';
        return protocol + '://'+ host + ':'+ port + '/' + appPath + '/websocket/';
    }
};

app.controller("DemoController", DemoController);