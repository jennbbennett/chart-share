/**
 * Created by jenn on 3/21/16.
 */
'use strict';

/**
 * @ngdoc function
 * @name chart-share.controller:MedicationCtrl
 * @description
 * # MedicationCtrl
 * Controller of Chart-Share
 */
angular.module('chart-share').controller('MedicationCtrl', ['$scope', '$http', '$timeout','authService', "$rootScope", '$state', '$stateParams', function ($scope, $http, $timeout, authService, $rootScope, $state, $stateParams) {
  console.log("I'm in the medication controller");

  $stateParams;
  console.log($stateParams);
  $scope.state = $state.current;
  $scope.params = $stateParams;
  console.log("Params",$scope.params.medicationId);
  $scope.view = {};

  $scope.medication = {};


  $http.get('/service/medication/' + $scope.params.medicationId).then(function (response){
    console.log(response.data);
    $scope.medication = response.data;
    console.log('medication scope', $scope.medication);

  })

  $scope.medication.notes = {};


  $http.get('/service/note/MEDICATION/' + $scope.params.medicationId).then(function (response){
    $scope.medication.notes = response.data;
  })



}]);
