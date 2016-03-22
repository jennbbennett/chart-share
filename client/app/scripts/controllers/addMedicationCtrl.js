/**
 * Created by jenn on 3/21/16.
 */
'use strict';

/**
 * @ngdoc function
 * @name chart-share.controller:AddMedicationCtrl
 * @description
 * # AddMedicationCtrl
 * Controller of Chart-Share
 */
angular.module('chart-share').controller('AddMedicationCtrl', ['$scope', '$http', '$timeout','authService', "$rootScope", '$state', '$stateParams', function ($scope, $http, $timeout, authService, $rootScope, $state, $stateParams) {
  console.log("I'm in the add medication controller");

  $stateParams;
  console.log($stateParams);
  $scope.state = $state.current;
  $scope.params = $stateParams;
  console.log("Params medicationId",$scope.params.medicationId);
  $scope.view = {};
  $scope.medication = {};
  $scope.patients = [];

  $http.get('/service/medication/' + $scope.params.medicationId).then(function (response){
    console.log("response in addmedication", response.data);
    $scope.medication = response.data.medication;
  })

  $scope.persons = {};
  $http.get('/service/findgroup?personId=' + $rootScope.person.id).then(function (response){
    console.log(response.data);
    $scope.members = [];

    response.data.members.map(function(member){
      $scope.members.push(member.person);
    });
    //$scope.members = response.data.members;
    console.log("My group is", $scope.members);

  })

  $scope.createMedication = function (medication) {
    console.log("I will update a medication with this information", medication,$scope.persons);

    $http.put('/service/medication', {
      id: $scope.params.medicationId,
      rxName: medication.rxName,
      rxNumber: medication.rxNumber,
      rxQuantity: medication.rxQuantity,
      refills: medication.refills,
      rxDate: medication.rxDate,
      groupId : $scope.medication.groupId,
      physicianId: $scope.medication.physicianId,
      personId: $scope.medication.personId,
      rxCui:  medication.rxCui,
      rxPharm: medication.rxPharm,
      patients: medication.patients
      //groupId: $scope.groupData.group.id
    }).then(function (resp) {
      console.log("response from post", resp.data);
      $state.go('medication',{'medicationId':$scope.params.medicationId});
    });
  }


}]);
