'use strict';

/**
 * @ngdoc function
 * @name AniTheme.controller:HomeCtrl
 * @description
 * # HomeCtrl
 * Controller of AniTheme
 */
angular.module('chart-share').controller('HomeCtrl', ['$scope', '$http', '$timeout','authService',function ($scope, $timeout, authService) {
  if (authService.isAuthenticated()) {
    console.log('You are logged in!');

    $http.get('/').then(function(data){
      console.log(data);
      $scope.people = data;
    })

    $http.get('/').then(function(data){
      console.log(data);
      $scope.physicians = data;
    })
  }
}]);
