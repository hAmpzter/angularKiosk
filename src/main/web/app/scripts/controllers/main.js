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
    console.log('called with: '+item);
      $scope.cart.push(item);
    };
<<<<<<< HEAD

    $scope.purchase = function() {
     $http.post('http://localhost:8280/purchase', {items: $scope.cart}).then(
         function successCallback(response) {
             $scope.cart = [];
             console.log(response);
           },
           function errorCallback(response) {
             console.log('error');
             console.log(response);
           });
    };
 $scope.removeFromPurchase = function(purchaseId, itemId) {
     $http.delete('http://localhost:8280/purchase/'+purchaseId+'/'+itemId).then(
         function successCallback(response) {
             $scope.cart = [];
             console.log(response);
           },
           function errorCallback(response) {
             console.log('error');
             console.log(response);
           });
    };

    $scope.removePurchase = function(purchaseId) {
     $http.delete('http://localhost:8280/purchase/'+purchaseId).then(
         function successCallback(response) {
             $scope.cart = [];
             console.log(response);
           },
           function errorCallback(response) {
             console.log('error');
             console.log(response);
           });
    };

   $http.get('http://localhost:8280/stock/categories').then(
         function successCallback(response) {
           $scope.categories=response.data;
         },
         function errorCallback(response) {
           $scope.categories=[];
           console.log('error');
           console.log(response);
         });

   $http.get('http://localhost:8280/purchase/history').then(
         function successCallback(response) {
           $scope.history=response.data;
         },
         function errorCallback(response) {
           $scope.history=[];
           console.log('error');
           console.log(response);
         });
=======
>>>>>>> parent of 61ce471... added more functionality
  }]);
