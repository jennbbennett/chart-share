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
        $scope.changeTheme = function (setTheme) {

          $('<link>')
            .appendTo('head')
            .attr({type: 'text/css', rel: 'stylesheet'})
            .attr('href', 'styles/app-' + setTheme + '.css?v=' + window.app_version);

          // $.get('/api/change-theme?setTheme='+setTheme);

        }
        $scope.rightToLeft = function () {
          // alert('message');
          $('body').toggleClass('rtl');

          // var t = $('body').hasClass('rtl');
          // console.log(t);

          if ($('body').hasClass('rtl')) {
            $('.stat').removeClass('hvr-wobble-horizontal');
          }
        }
        $scope.logOutUser = function () {
          console.log("clicked logout");
          authService.logout();
        }

      }
    }
  }]);
