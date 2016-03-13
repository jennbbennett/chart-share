'use strict';

angular.module('chart-share')
	.directive('menubar',function(){
		return {
        templateUrl:'scripts/directives/sidebar/menu-bar/menu-bar.html?v='+window.app_version,
        restrict: 'E',
        replace: true,
    }
});
