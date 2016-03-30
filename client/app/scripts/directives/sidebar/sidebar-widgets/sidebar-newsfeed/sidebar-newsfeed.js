'use strict';

angular.module('chart-share')
	.directive('sidebarNewsfeed',function(){
		return {
      templateUrl: 'scripts/directives/sidebar/sidebar-widgets/sidebar-newsfeed/sidebar-newsfeed.html?v=' + window.app_version,
      restrict: 'E',
      replace: true,
      controller: ['$scope', '$http', '$rootScope', function ($scope, $http, $rootScope){
        console.log("In sidebar controller");
        $scope.view = {};

        $http.get('/service/findgroup?personId=' + $rootScope.person.id).then(function (response) {
          console.log("group response", response.data);
          $scope.group = response.data.group;
          console.log("group", $scope.group.id);

          $http.get('/service/activity/group/' + $scope.group.id).then(function (response) {
            console.log("Activity data", response.data);
            $scope.activities = response.data;
          })
        })
      }]

    }
	});
