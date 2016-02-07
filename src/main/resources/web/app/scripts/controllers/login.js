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
    var item = localStorage.getItem('jwt');
    if(item!==null) {
      return item.replace(/"/g, '');
    }
    return null;
  };

  $httpProvider.interceptors.push('jwtInterceptor');
}).controller('LoginCtrl', ['$scope','store', '$http', '$location', '$timeout', function($scope,store, $http, $location, $timeout) {
    $scope.login = [];
    $scope.loginAlertMessage = '';
    $scope.doLogin = function() {
        $http.post('/login', {username: $scope.username, password: $scope.password}).then(
                 function successCallback(response) {
                     store.set('jwt', response.data.token);
                     $location.path('/');
                   },
                   function errorCallback(response) {
                     $scope.loginAlertMessage = 'invalid username or password';
                     $timeout(function () { $scope.loginAlertMessage = ''; }, 5000);
                     console.log(response);
                   });
         };
    }
  ]);
