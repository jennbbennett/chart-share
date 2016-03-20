'use strict';

angular.module('chart-share')
  .directive('topnav', ['$rootScope', 'authService', function ($rootScope, authService) {
    return {
      templateUrl: 'scripts/directives/topnav/topnav.html?v=' + window.app_version,
      restrict: 'E',
      replace: true,
      controller: function ($scope) {
        $scope.user = $rootScope.user;
        if ($rootScope.authenticated != true) {
          console.log("Hey, you are not authenticated!");
        }
        $scope.showMenu = function () {

          $('.dashboard-page').toggleClass('push-right');

        }

        $scope.logOutUser = function () {
          console.log("clicked logout");
          authService.logout();
        }
        //$scope.view.searchKeyword = '';
      }
    }
  }]);
