/**
 * Created by jenn on 3/20/16.
 */
'use strict';

/**
 * @ngdoc function
 * @name chart-share.controller:AddPhysNoteCtrl
 * @description
 * # AddPhysNoteCtrl
 * Controller of Chart-Share
 */
angular.module('chart-share').controller('AddPhysNoteCtrl', ['$scope', '$http', '$timeout','authService', "$rootScope", '$state', '$stateParams', function ($scope, $http, $timeout, authService, $rootScope, $state, $stateParams) {
  console.log("I'm in the add a phys note controller");

  $stateParams;
  console.log($stateParams);
  $scope.state = $state.current;
  $scope.params = $stateParams;
  console.log("Params",$scope.params.physicianId);
  $scope.view = {};

  $http.get('/service/physician/' + $scope.params.physicianId).then(function (response){
    console.log(response.data);
    $scope.physician = response.data;
  })

  $scope.note = {};
  $scope.createPhysicianNote = function(note){
    console.log("I will create a note with this information", note);
    $http.post('/service/note', {
      title: note.title,
      text: note.text,
      targetType: "PHYSICIAN",
      targetId: $scope.params.physicianId
    }).then(function (resp){
      console.log("response from note post", resp.data);
    })

  }





}]);
