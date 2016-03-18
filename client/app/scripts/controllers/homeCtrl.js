'use strict';

/**
 * @ngdoc function
 * @name chart-share.controller:HomeCtrl
 * @description
 * # HomeCtrl
 * Controller of AniTheme
 */
angular.module('chart-share').controller('HomeCtrl', ['$scope', '$http', '$timeout','authService', "$rootScope",function ($scope, $http, $timeout, authService, $rootScope) {
  console.log("I'm in the home controller");
//debugger;
    $http.get('/service/findgroup?personId='+$rootScope.person.personId).then(function(response){
      console.log(response);
      $scope.groupData = response.data;
    })
    //
    //$http.get('/').then(function(data){
    //  console.log(data);
    //  $scope.physicians = data;
    //})


}]);
