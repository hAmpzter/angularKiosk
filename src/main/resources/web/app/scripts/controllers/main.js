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
    var host = '/'
    $scope.sum = function() {
      var sum =0;
      for (var index in $scope.cart) {
          var item = $scope.cart[index];
          console.log(item);
          sum+=item.count*item.item.price;
      }
      return sum;
    };
    $scope.allStock = function() {

    $http.get(host+'stock').then(
      function successCallback(response) {
        $scope.items=response.data;
      },
      function errorCallback(response) {
        console.log('error');
        console.log(response);
      });
    }
    $scope.topStock = function() {

    $http.get(host+'stock/top').then(
      function successCallback(response) {
        $scope.items=response.data;
      },
      function errorCallback(response) {
        console.log('error');
        console.log(response);
      });
    }
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
    };
        $scope.decreaseItemCount = function(ean) {
      $scope.cart[ean].count--;
    };

    $scope.stockForCategory = function(category) {
      $http.get(host+'stock/categories/'+category+'/stock').then(
          function successCallback(response) {
            $scope.items=response.data;
          },
          function errorCallback(response) {
            console.log('error');
            console.log(response);
          });
    };

    $scope.purchase = function() {
        var items = [];
        for (var index in $scope.cart) {
            var item = $scope.cart[index];
            for (var i = 0; i < item.count; i++) {
            console.log('adding item: '+item.item.name+' count: '+item.count);
            items.push(item.item);
            }
        }
       $http.post(host+'purchase', {items: items}).then(
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
       $http.delete(host+'purchase/'+purchaseId+'/'+itemId).then(
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
     $http.delete(host+'purchase/'+purchaseId).then(
         function successCallback(response) {
             $scope.cart = [];
             console.log(response);
           },
           function errorCallback(response) {
             console.log('error');
             console.log(response);
           });
    };


   $http.get(host+'stock/categories').then(
         function successCallback(response) {
           $scope.categories=response.data;
         },
         function errorCallback(response) {
           $scope.categories=[];
           console.log('error');
           console.log(response);
         });

   $http.get(host+'purchase/history').then(
         function successCallback(response) {
           $scope.history=response.data;
         },
         function errorCallback(response) {
           $scope.history=[];
           console.log('error');
           console.log(response);
         });
   $scope.topStock()
  }]);
