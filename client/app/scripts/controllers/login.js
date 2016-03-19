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

    //$scope.checkOnLoad = function () {
    //  console.log("Calling isAuthenticated from login checkOnLoad");
    //  authService.isAuthenticated(function(authenticated){
    //    if(authenticated){
    //      console.log("you don't need to login");
    //      $location.path('/dashboard/home');
    //      $state.transitionTo('home');
    //    } else {
    //      $state.transitionTo("login");
    //      //event.preventDefault();
    //    }
    //  })
    //}
    $scope.submit = function () {
      console.log(' Login  onSubmit - calling isAuthenticated');
      authService.isAuthenticated(function(authenticated){
          if(authenticated){
            if($rootScope.person !== undefined){
              $location.path('/dashboard/home');
              $state.transitionTo('home');
            } else{
              $location.path('/signup');
              $state.transitionTo('signup');
            }
          } else {
            $state.transitionTo("login");
            //event.preventDefault();
          }
        })
      }



    //$scope.checkOnLoad();
  }]);
