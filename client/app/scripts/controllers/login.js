'use strict';

/**
 * @ngdoc function
 * @name AniTheme.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of AniTheme
 */
angular.module('chart-share')
  .controller('LoginCtrl', ['$scope', '$location','$timeout', '$q', '$state', 'authService', function($scope, $location, $timeout, $q, $state, authService) {
    $scope.checkOnLoad = function(){
      //debugger
      console.log("Checking OnLoad");
      if(authService.isAuthenticated()){
          $location.path('/signup');
        $state.transitionTo('signup');
        }
    }
    $scope.submit = function() {
      if(authService.isAuthenticated()) {
        $location.path('/signup');
      }
      	//return false;

    }

    $scope.authenticate = function() {

    	var defer = $q.defer();

    	$timeout(function(){

    		defer.resolve();

    		$timeout(function(){
          //debugger;
    		   	$location.path('/dashboard/home');
    		}, 600);

    	}, 1100);

    	return defer.promise;

    }
    $scope.checkOnLoad();
  }]);
