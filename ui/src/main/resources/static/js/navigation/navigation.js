angular
    .module('navigation', ['ngRoute', 'auth'])
    .controller('navigation', function($scope, $route, auth) {

        $scope.credentials = {};

        $scope.tab = function(route) {
            return $route.current && route === $route.current.controller
        };

        $scope.authenticated = function() {
            return auth.authenticated;
        };

        $scope.logout = auth.clear;
    });