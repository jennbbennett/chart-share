/**
 * Created by jenn on 3/20/16.
 */
'use strict';

/**
 * @ngdoc function
 * @name chart-share.controller:PhysicianCtrl
 * @description
 * # PhysicianCtrl
 * Controller of Chart-Share
 */
angular.module('chart-share').controller('PhysicianCtrl', ['$scope', '$http', '$timeout','authService', "$rootScope", '$state', '$stateParams', function ($scope, $http, $timeout, authService, $rootScope, $state, $stateParams) {
  console.log("I'm in the physician controller");

  $stateParams;
  console.log($stateParams);
  $scope.state = $state.current;
  $scope.params = $stateParams;
  console.log("Params",$scope.params.physicianId);
  $scope.view = {};

  $scope.physician = {};

  $http.get('/service/physician/' + $scope.params.physicianId).then(function (response){
    console.log(response.data);
    $scope.physician = response.data;
    console.log('physician scope', $scope.physician);

  })

  $scope.physician.patients = {};

  $http.get('/service/physician/patients/' + $scope.params.physicianId).then(function (response){
    $scope.physician.patients = response.data;
  })


  $scope.deletePhysician = function(physician){
    console.log('I am deleting a physician', physician.id);
    $http.delete('/service/physician/' + physician.id).then(function(response){
      console.log('response from physician delete', response);
      $state.go('dashboard');
    })

  }


}]);
