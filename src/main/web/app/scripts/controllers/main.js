'use strict';

/**
 * @ngdoc function
 * @name webApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the webApp
 */
angular.module('webApp')
  .controller('MainCtrl', ['$scope', '$http', function($scope, $http) {
    $scope.cart = [];
    $http.get('http://localhost:8280/stock').then(
      function successCallback(response) {
        $scope.items=response.data
      },
      function errorCallback(response) {
        console.log('error');
        console.log(response);
      });

    $scope.addToCart = function(item) {
    console.log("called with: "+item);
      $scope.cart.push(item);
    };
  }]);
