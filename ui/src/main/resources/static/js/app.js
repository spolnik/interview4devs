angular
    .module("myApp", ["ngResource", "ngRoute", "auth", "home", "candidates", "navigation", 'spring-security-csrf-token-interceptor'])
    .config(function($routeProvider, $httpProvider, $locationProvider) {

        $locationProvider.html5Mode(true);

        $routeProvider.when('/', {
            templateUrl : 'js/home/home.html',
            controller : 'home'
        }).when('/candidates', {
            templateUrl : 'js/candidates/candidates.html',
            controller : 'candidates'
        }).when('/login', {
            templateUrl : 'js/navigation/login.html',
            controller : 'navigation'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    }).run(function(auth) {
        auth.init("/", "/login", "/logout");
    });