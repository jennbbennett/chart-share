'use strict';


angular.module('chart-share')
	.directive('sidebarNewsfeed',function(){
		return {
        templateUrl:'scripts/directives/sidebar/sidebar-widgets/sidebar-newsfeed/sidebar-newsfeed.html?v='+window.app_version,
        restrict: 'E',
        replace: true,
    	}
	});
