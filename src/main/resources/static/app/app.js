(function(angular) {
  angular.module("myApp.controllers", []);
  angular.module("myApp.services", []);
  angular.module("myApp", ["ngResource", "ngRoute", "myApp.controllers", "myApp.services"])
    .config(function($routeProvider, $httpProvider) {
            $routeProvider.when('/', {
                templateUrl : 'partials/home.html'
            }).when('/candidates', {
                templateUrl : 'partials/candidates.html',
                controller : 'AppController'
            }).when('/login', {
                templateUrl : 'partials/login.html',
                controller : 'navigation'
            }).otherwise('/');

            $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
        });
}(angular));