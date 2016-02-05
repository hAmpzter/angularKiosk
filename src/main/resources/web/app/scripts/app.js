'use strict';

/**
 * @ngdoc overview
 * @name webApp
 * @description
 * # webApp
 *
 * Main module of the application.
 */
angular
  .module('webApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'angular-jwt',
    'angular-storage'
  ]).config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      }).when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl',
        controllerAs: 'login'
      }).otherwise({
        redirectTo: '/'
      });
  }).run( function($rootScope, $location) {

      // register listener to watch route changes
      $rootScope.$on( '$routeChangeStart', function(event, next) {
        if ( localStorage.getItem('jwt') === null ) {
          // no logged user, we should be going to #login
          if ( next.templateUrl === 'login.html' ) {
            // already going to #login, no redirect needed
          } else {
            // not going to #login, we should redirect now
            $location.path( '/login' );
          }
        }
      });
      });
