'use strict';

/**
 * @ngdoc function
 * @name AniTheme.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of AniTheme
 */
angular.module('chart-share')
  .controller('DashboardCtrl', function($scope, $state, $translate, $http, $rootScope) {



    $scope.$state = $state;

    $scope.date = new Date();
    $scope.layoutToggler = function(y){

		if(y==$scope.multiCollapseVar)
			$scope.multiCollapseVar = 0;
		else
			$scope.multiCollapseVar = y;
	};

	$scope.load = (function(){
		$('#world-map').vectorMap({
			backgroundColor: '#FFFFFF',
			zoomOnScroll: false,
			regionStyle: {
				initial: {
					fill: '#CCC'
				},
				hover: {
					fill: "#3CA2E0"
				}
			}
		});
	});
	$scope.changeLanguage = (function (l) {

		$translate.use(l);

	});

});
