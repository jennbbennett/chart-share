'use strict';

angular.module('chart-share')
	.directive('sidebarwidgets',function(){
		return {
        templateUrl:'scripts/directives/sidebar/sidebar-widgets/sidebar-widgets.html?v='+window.app_version,
        restrict: 'E',
        replace: true,
	}
});
