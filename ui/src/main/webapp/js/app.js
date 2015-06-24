var myApp = angular
    .module("myApp", [
        "ngResource",
        "ngRoute",
        "auth",
        "home",
        "candidates",
        "navigation",
        'spring-security-csrf-token-interceptor'
    ]);
myApp.config(function($routeProvider, $httpProvider, $locationProvider) {

        $locationProvider.html5Mode(true);

        $routeProvider.when('/', {
            templateUrl : 'js/home/home.html',
            controller : 'home'
        }).when('/candidates', {
            templateUrl : 'partials/candidates.html',
            controller : 'candidates'
        }).when('/login', {
            templateUrl : 'js/navigation/login.html',
            controller : 'navigation'
        }).when('/register', {
            templateUrl: 'js/register/register.html',
            controller: 'register'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    });
myApp.run(function(auth) {
    auth.init("/", "/login", "/logout");
});