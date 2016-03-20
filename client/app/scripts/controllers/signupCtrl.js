'use strict';

/**
 * @ngdoc function
 * @name chart-share.controller:signupCtrl
 * @description
 * # signupCtrl
 * Controller of Chart-Share
 */
angular.module('chart-share')
  .controller('signupCtrl', ['$scope', '$location', '$timeout', '$state', '$rootScope', '$http', function ($scope, $location, $timeout, $state, $rootScope, $http) {
   console.log('I am in the signup controller');
    $scope.person = {};
    $scope.person.firstName = $rootScope.principal.userAuthentication.details.first_name;
    $scope.person.lastName = $rootScope.principal.userAuthentication.details.last_name;
    $scope.addNewPerson = function (person) {
      console.log("in addNewPerson");
      $http.post('/service/user/'+ $rootScope.principalSource +'/' + $rootScope.principalName, {
        firstName: person.firstName,
        lastName: person.lastName
      }).then(function(resp){
        console.log("result from creating user", resp.data);
        $rootScope.person = resp.data.person;
        $rootScope.user = resp.data.user;
        $state.go('home');
      })
      console.log($scope.person.firstName);
    };

  }]);
