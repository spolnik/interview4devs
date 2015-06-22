angular.module('candidates', [])
    .factory("Candidate", function($resource) {
        return $resource('resource/api/candidates/:id', {
            id: '@id'
        }, {
            update: {
                method: "PUT"
            },
            remove: {
                method: "DELETE"
            }
        });
    })
    .controller('candidates', function($scope, Candidate) {
        Candidate.query(function(response) {
          $scope.candidates = response ? response : [];
        });
    });