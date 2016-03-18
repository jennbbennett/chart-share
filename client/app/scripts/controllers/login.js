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
      authService.isAuthenticated(function(authenticated){
        if(authenticated){
          console.log("you don't need to login");
          $location.path('/dashboard/home');
          $state.transitionTo('home');
        } else {
          $state.transitionTo("login");
          //event.preventDefault();
        }
      })
    }
    $scope.submit = function () {
      console.log('Performing Login');
      authService.isAuthenticated(function(authenticated){
          if(authenticated){
            $location.path('/dashboard/home');
            $state.transitionTo('home');
          } else {
            $state.transitionTo("login");
            //event.preventDefault();
          }
        })
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
