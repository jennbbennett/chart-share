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



  if ($rootScope.person == undefined) {
    $state.go('signup');
  } else {
    $http.get('/service/findgroup?personId=' + $rootScope.person.id).then(function (response) {
      console.log("group response", response.data);
      $scope.groupData = response.data;
    })


    $http.get('/service/physician?personId=' + $rootScope.person.id).then(function (response) {
      console.log("physician response", response.data);
      $scope.physicians = response.data;
    })

    $http.get('/service/medication?personId=' + $rootScope.person.id).then(function (response){
      console.log("medication response", response.data);
      $scope.medications = response.data;
    })

  }
  $scope.view.addPersonShow = false;

  $scope.toggleAddPersonShow = function () {
    $scope.view.addPersonShow = !$scope.view.addPersonShow;
  };


  $scope.view.addPhysicianShow = false;
  $scope.toggleAddPhysicianShow = function () {
    $scope.view.addPhysicianShow = !$scope.view.addPhysicianShow;
  };

  $scope.view.addMedicationShow = false;
  $scope.toggleAddMedicationShow = function () {
    $scope.view.addMedicationShow = !$scope.view.addMedicationShow;
  };

  $scope.newMember = {};
  $scope.createPerson = function (member) {
    $http.post('service/person?groupId=' + $scope.groupData.group.id, member.person).then(function (resp) {
      $http.get('/service/findgroup?personId=' + $rootScope.person.id).then(function (response) {
        console.log(response);
        $scope.groupData = response.data;
      });
      $scope.newMember = {};
      $scope.view.addPersonShow = false;
    })
  }

  $scope.physician = {};
  $scope.createPhysician = function (physician) {
    console.log("I will create a physician with this information", physician);
    $http.post('/service/physician', {
      firstName: physician.firstName,
      lastName: physician.lastName,
      practice: physician.practice,
      groupId: $scope.groupData.group.id
    }).then(function (resp) {
      console.log("response from post", resp.data);
      $http.get('/service/physician?personId=' + $rootScope.person.id).then(function (response) {
        console.log(response);
        $scope.physicians = response.data;
      });
      $scope.physician = {};
      $scope.view.addPhysicianShow = false;
    });
  }

  $scope.medication = {};
  $scope.createMedication = function (medication){
    console.log("I will create a medication with this information", medication);
    $http.post('/service/medication', {
      rxName: medication.rxName,
      refills: medication.refills,
      groupId: $scope.groupData.group.id,
      physicianId: medication.physicianId
    }).then(function (resp) {
      console.log("response from post", resp.data);
      $http.get('/service/medication?personId=' + $rootScope.person.id).then(function (response){
        console.log("medication response", response.data);
        $scope.medications = response.data;
      })
    });
    $scope.medication = {};
    $scope.view.addMedicationShow = false;
  };

}]);
