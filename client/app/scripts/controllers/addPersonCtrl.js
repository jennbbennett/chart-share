/**
 * Created by jenn on 3/22/16.
 */
'use strict';

/**
 * @ngdoc function
 * @name chart-share.controller:AddPersonCtrl
 * @description
 * # AddPersonCtrl
 * Controller of Chart-Share
 */
angular.module('chart-share').controller('AddPersonCtrl', ['$scope', '$http', '$timeout','authService', "$rootScope", '$state', '$stateParams', function ($scope, $http, $timeout, authService, $rootScope, $state, $stateParams) {
  console.log("I'm in the add person controller");

  $stateParams;
  console.log($stateParams);
  $scope.state = $state.current;
  $scope.params = $stateParams;
  console.log("Params personId",$scope.params.personId);
  $scope.view = {};
  $scope.currentPerson = {};

  $http.get('/service/person/' + $scope.params.personId).then(function (response){
    console.log("response in addperson", response.data);
    $scope.currentPerson = response.data;
  })

  $scope.createPerson = function (person) {
    console.log("I will update a person with this information", person,$scope.persons);

    $http.put('/service/person', {
      id: $scope.params.personId,
      firstName: person.firstName,
      lastName: person.lastName,
      birthDate: person.birthDate,
      insurance: person.insurance,
      insurancePolicy: person.insurancePolicy,
      allergy : person.allergy,
      condition: person.condition,
      surgery: person.surgery,
      hospital: person.hospital,
      groupId: $rootScope.group.id
    }).then(function (resp) {
      console.log("response from post", resp.data);
      $state.go('person',{'personId':resp.data.id});
    });
  }


}]);
