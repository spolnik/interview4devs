angular
    .module("myApp", ["ngResource", "ngRoute", "auth", "home", "candidates", "navigation"])
    .config(function($routeProvider, $httpProvider, $locationProvider) {

        $locationProvider.html5Mode(true);

        $routeProvider.when('/', {
            templateUrl : 'app/home/home.html',
            controller : 'home'
        }).when('/candidates', {
            templateUrl : 'app/candidates/candidates.html',
            controller : 'candidates'
        }).when('/login', {
            templateUrl : 'app/navigation/login.html',
            controller : 'navigation'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    }).run(function(auth) {
        auth.init("/", "/login", "/logout");
    });