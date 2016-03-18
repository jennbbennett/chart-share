'use strict';

/**
 * @ngdoc function
 * @name chart-share.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of Chart-Share
 */
angular.module('chart-share')
  .controller('LoginCtrl', ['$scope', '$location', '$timeout', '$q', '$state', 'authService','$rootScope', '$http', function ($scope, $location, $timeout, $q, $state, authService, $rootScope, $http) {

    $scope.checkOnLoad = function () {
      console.log("Checking OnLoad");
      //var authenticated = "";
      //var profile =
      if (authService.isAuthenticated()) {
        if ($rootScope.person === undefined) {
          console.log("Person is not known!!!");
          $location.path('/signup');
          $state.transitionTo('signup');
        }
        else {
          console.log("Person is known");
          $location.path('/dashboard/home');
          $state.transitionTo('home');
        }
      }
    }
    $scope.submit = function () {
      if (authService.isAuthenticated()) {
        $location.path('/signup');
        $state.transitionTo('signup');
      }

    }
    //$scope.authenticate = function () {
    //
    //  var defer = $q.defer();
    //
    //  $timeout(function () {
    //
    //    defer.resolve();
    //
    //    $timeout(function () {
    //      //debugger;
    //      $location.path('/dashboard/home');
    //    }, 600);
    //
    //  }, 1100);
    //
    //  return defer.promise;
    //
    //}
    $scope.checkOnLoad();
  }]);
