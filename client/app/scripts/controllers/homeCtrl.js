'use strict';

/**
 * @ngdoc function
 * @name chart-share.controller:HomeCtrl
 * @description
 * # HomeCtrl
 * Controller of AniTheme
 */
angular.module('chart-share').controller('HomeCtrl', ['$scope', '$http', '$timeout','authService', "$rootScope", '$state', function ($scope, $http, $timeout, authService, $rootScope, $state) {
    console.log("I'm in the home controller");

  $scope.view = {};


    if($rootScope.person == undefined){
      $state.go('signup');
    } else {
      $http.get('/service/findgroup?personId=' + $rootScope.person.id).then(function (response) {
        console.log(response);
        $scope.groupData = response.data;
      })
      //
      //$http.get('/').then(function(data){
      //  console.log(data);
      //  $scope.physicians = data;
      //})
    }

  $scope.view.addPersonShow = false;

  $scope.toggleAddPersonShow = function(){
    $scope.view.addPersonShow = !$scope.view.addPersonShow;
  };




  $scope.view.addPhysicianShow = false;
  $scope.toggleAddPhysicianShow = function(){
    $scope.view.addPhysicianShow = !$scope.view.addPhysicianShow;
  };
    $scope.newMember = {};
    $scope.createPerson = function(member){
      console.log("I will create a person with this information",member);
      console.log($scope.groupData);
      $http.post('service/person?groupId=' + $scope.groupData.group.id, member.person).then(function(resp){
        $http.get('/service/findgroup?personId=' + $rootScope.person.id).then(function (response) {
          console.log(response);
          $scope.groupData = response.data;


        });
        $scope.newMember = {};
        $scope.view.addPersonShow = false;
      })
    }

}]);
