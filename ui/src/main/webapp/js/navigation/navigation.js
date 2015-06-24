angular
    .module('myApp')
    .controller('navigation', function($scope, $route, $http, auth) {

        $scope.isAuthenticated = function() {
            return auth.authenticated;
        };

        $scope.user = '';

        $scope.logout = auth.clear;

        $scope.login = function() {
            		$http({
            			method: 'POST',
            			url: '/login',
            			data: $.param($scope.form),
            			headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            		}).then(function() {
            			window.location.href = '/';
            		}, function(err) {
            			console.log(err);
            		});
            	};
    });