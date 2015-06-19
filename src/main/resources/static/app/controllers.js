(function(angular) {
  var AppController = function($scope, Item) {
    Item.query(function(response) {
      $scope.candidates = response ? response : [];
    });

    $scope.addItem = function(description) {
      new Item({
        description: description,
        checked: false
      }).$save(function(item) {
        $scope.candidates.push(item);
      });
      $scope.newItem = "";
    };

    $scope.updateItem = function(item) {
      item.$update();
    };

    $scope.deleteItem = function(item) {
      item.$remove(function() {
        $scope.candidates.splice($scope.candidates.indexOf(item), 1);
      });
    };
  };

  AppController.$inject = ['$scope', 'Item'];
  angular.module("myApp.controllers")
    .controller("AppController", AppController)
    .controller('navigation', function () {});
}(angular));