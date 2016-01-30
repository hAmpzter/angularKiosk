'use strict';

/**
 * @ngdoc function
 * @name webApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the webApp
 */
angular.module('webApp')
.config(function Config($httpProvider, jwtInterceptorProvider) {
  jwtInterceptorProvider.tokenGetter = function() {
    var item = localStorage.getItem('jwt')
    if(item!=null) {
      return item.replace(/"/g, "");
    }
    return null;
  };

  $httpProvider.interceptors.push('jwtInterceptor');
}).controller('LoginCtrl', ['$scope','store', '$http', function($scope,store, $http) {
    $scope.login = [];
    $scope.doLogin = function() {
        $http.post('http://localhost:8280/login', {username: $scope.username, password: $scope.password}).then(
                 function successCallback(response) {
                     store.set('jwt', response.data.token);
                       console.log(response);
                   },
                   function errorCallback(response) {
                     console.log(response);
                   });
         };

    $scope.isLoggedin = function() {
      $http.get('http://localhost:8280/login/with-permit');
    };
    $scope.noLogin = function() {
      $http.get('http://localhost:8280/login/no-permit');
    };
    }
  ]);
