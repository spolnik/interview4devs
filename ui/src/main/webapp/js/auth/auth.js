angular.module('myApp').factory(
    'auth',
    function($rootScope, $http, $location, Register) {

        enter = function() {
            if ($location.path() != auth.loginPath &&
                $location.path() != auth.homePath &&
                $location.path() != '/register') {
                auth.path = $location.path();
                if (!auth.authenticated) {
                    $location.path(auth.loginPath);
                }
            }
        };

        var auth = {
            authenticated : false,
            loginPath : '/login',
            logoutPath : '/logout',
            homePath : '/',
            path : $location.path(),

            authenticate : function(credentials, callback) {

                $http.get('user').success(function(data) {
                    if (data.name) {
                        auth.authenticated = true;
                        $rootScope.user = data.name
                    } else {
                        auth.authenticated = false;
                    }
                    callback && callback(auth.authenticated);
                    $location.path(auth.path==auth.loginPath ? auth.homePath : auth.path);
                }).error(function() {
                    auth.authenticated = false;
                    callback && callback(false);
                });

            },

            clear : function() {
                $location.path(auth.loginPath);
                auth.authenticated = false;
                $rootScope.user = ''
                $http.post(auth.logoutPath, {}).success(function() {
                    console.log("Logout succeeded");
                }).error(function(data) {
                    console.log("Logout failed");
                });
            },

            init : function(homePath, loginPath, logoutPath) {

                auth.homePath = homePath;
                auth.loginPath = loginPath;
                auth.logoutPath = logoutPath;

                auth.authenticate({}, function(authenticated) {
                    if (authenticated) {
                        $location.path(auth.path);
                    }
                })

                // Guard route changes and switch to login page if unauthenticated
                $rootScope.$on('$routeChangeStart', function() {
                    enter();
                });
            },

            createAccount : function (account, callback) {
                var cb = callback || angular.noop;

                return Register.save(account,
                    function () {
                        return cb(account);
                    },
                    function (err) {
                        this.logout();
                        return cb(err);
                    }.bind(this)).$promise;
            }
        };

        return auth;
    }
);