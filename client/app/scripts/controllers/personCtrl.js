/**
 * Created by jenn on 3/22/16.
 */
'use strict';

/**
 * @ngdoc function
 * @name chart-share.controller:PersonCtrl
 * @description
 * # PersonCtrl
 * Controller of Chart-Share
 */
angular.module('chart-share').controller('PersonCtrl', ['$scope', '$http', '$timeout','authService', "$rootScope", '$state', '$stateParams', function ($scope, $http, $timeout, authService, $rootScope, $state, $stateParams) {
  console.log("I'm in the person controller");

  $stateParams;
  console.log($stateParams);
  $scope.state = $state.current;
  $scope.params = $stateParams;
  console.log("Params",$scope.params.personId);
  $scope.view = {};

  $scope.currentPerson = {};


  $http.get('/service/person/' + $scope.params.personId).then(function (response){
    console.log("person data",response);
    $scope.currentPerson = response.data;
    $scope.people = response.data.people;
    console.log('person scope', $scope.currentPerson.name);

  })



  $scope.deletePerson = function(currentPerson){
    console.log('I am deleting a person', currentPerson.id);
    $http.delete('/service/person/' + currentPerson.id).then(function(response){
      console.log('response from person delete', response);
      $state.go('dashboard');
    })

  }


}]);
