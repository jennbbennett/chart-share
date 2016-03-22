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

  $scope.person = {};


  $http.get('/service/person/' + $scope.params.personId).then(function (response){
    console.log(response.data);
    $scope.person = response.data.person;
    $scope.people = response.data.people;
    console.log('person scope', $scope.person);

  })



  $scope.deletePerson = function(person){
    console.log('I am deleting a person', person.id);
    $http.delete('/service/person/' + person.id).then(function(response){
      console.log('response from person delete', response);
      $state.go('dashboard');
    })

  }


}]);
