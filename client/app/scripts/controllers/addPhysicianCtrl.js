/**
 * Created by jenn on 3/20/16.
 */
'use strict';

/**
 * @ngdoc function
 * @name chart-share.controller:AddPhysicianCtrl
 * @description
 * # AddPhysicianCtrl
 * Controller of Chart-Share
 */
angular.module('chart-share').controller('AddPhysicianCtrl', ['$scope', '$http', '$timeout','authService', "$rootScope", '$state', '$stateParams', function ($scope, $http, $timeout, authService, $rootScope, $state, $stateParams) {
  console.log("I'm in the add physician controller");

  $stateParams;
  console.log($stateParams);
  $scope.state = $state.current;
  $scope.params = $stateParams;
  console.log("Params",$scope.params.physicianId);
  $scope.view = {};
  $scope.physician = {};
  $scope.patients = [];
  $scope.physician.patients = [];

  $http.get('/service/physician/' + $scope.params.physicianId).then(function (response){
    console.log(response.data);
    $scope.physician = response.data;
    $scope.physician.patients = [];
  })

  $scope.physician.patients = {};
  $http.get('/service/physician/patients/' + $scope.params.physicianId).then(function (response){
    $scope.physician.patients = response.data;
  })

  $scope.persons ={};
  $http.get('/service/findgroup?personId=' + $rootScope.person.id).then(function (response){
    console.log(response.data);
    $scope.members = response.data.members;
    console.log("My group is", $scope.members);

  })

  $scope.createPhysician = function (physician) {
    console.log("I will update a physician with this information", physician);

    var patients = [];
    if((physician.patients !== undefined) && (Array.isArray(physician.patients))) {
      physician.patients.map(function (patient) {
        console.log("Adding person to patient list", patient.person);
        patients.push(patient.person.id);
      })
    }

    $http.put('/service/physician', {
      id: $scope.params.physicianId,
      firstName: physician.firstName,
      lastName: physician.lastName,
      practice: physician.practice,
      email: physician.email,
      phoneNumber: physician.phoneNumber,
      officeAddress: physician.officeAddress,
      patients: patients,
      groupId: $rootScope.group.id
    }).then(function (resp) {
      console.log("response from post", resp.data);
      $state.go('physician', {physicianId: resp.data.id});
    });
  }



}]);
