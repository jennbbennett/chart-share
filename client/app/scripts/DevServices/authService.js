/**
 * Created by jenn on 3/13/16.
 */

angular.module('chart-share').factory('authService', ['$rootScope', '$http', '$location', function ($rootScope, $http, $location) {
  var authServiceInstance = {};

  authServiceInstance.isAuthenticated = function () {
    //debugger;
    if ($rootScope.authenticated != true) {
      $http.get("/user").success(function (data) {
        if (data.name) {
          self.user = data.name;
          console.log(data.name);
          self.authenticated = true;
          console.log('you are authenticated');
        } else {
          self.user = "N/A";
          self.authenticated = false;
        }
      }).error(function () {
        self.user = "N/A";
        self.authenticated = false;
      });
      $rootScope.user = self.user;
      $rootScope.authenticated = self.authenticated;
      console.log("user", $rootScope.user, $rootScope.authenticated);
    }
    return $rootScope.authenticated;
  };

  authServiceInstance.logout = function () {
    //debugger;
    $http.post('/logout', {}).success(function () {
      self.authenticated = false;
      $location.path("/");
    }).error(function (data) {
      console.log("Logout failed")
      self.authenticated = false;
    });
    $rootScope.user = null;
    $rootScope.authenticated = false;
    console.log("You are logged out");
  }

  return authServiceInstance;

}]);
