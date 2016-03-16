'use strict';

/**
 * @ngdoc function
 * @name AniTheme.controller:HomeCtrl
 * @description
 * # HomeCtrl
 * Controller of AniTheme
 */
angular.module('chart-share').controller('HomeCtrl', ['$scope', '$timeout','authService',function ($scope, $timeout, authService) {
  if (authService.isAuthenticated()) {
    console.log('You are logged in!');
  }
}]);
