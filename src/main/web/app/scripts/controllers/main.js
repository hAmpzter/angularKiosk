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
    $scope.cart = {};
    $scope.sum = function() {
      var sum =0;
      for (var index in $scope.cart) {
          var item = $scope.cart[index];
          console.log(item);
          sum+=item.count*item.item.price;
      }
      return sum;
    };

    $http.get('http://localhost:8280/stock').then(
      function successCallback(response) {
        $scope.items=response.data;
      },
      function errorCallback(response) {
        console.log('error');
        console.log(response);
      });

    $scope.addToCart = function(item) {
      console.log('called with: '+item);
        var ean = ''+item.ean;
        if($scope.cart[ean]) {
          $scope.cart[ean].count++;
        } else {
          $scope.cart[ean]= {item: item, count: 1};
        }
    };

    $scope.increaseItemCount = function(ean) {
      $scope.cart[ean].count++;
    }
        $scope.decreaseItemCount = function(ean) {
      $scope.cart[ean].count--;
    }

    $scope.stockForCategory = function(category) {
      $http.get('http://localhost:8280/stock/categories/'+category+'/stock').then(
          function successCallback(response) {
            $scope.items=response.data;
          },
          function errorCallback(response) {
            console.log('error');
            console.log(response);
          });
    };

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
  }]);
