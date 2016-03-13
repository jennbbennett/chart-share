/**
 * Created by jenn on 3/13/16.
 */

angular.module('chart-share').factory('authService', ['$rootScope', function ($rootScope) {
  var authServiceInstance;

  authServiceInstance.isAuthenticated() = function() {
    if ($rootScope.authenticated != true) {
      $http.get("/user").success(function (data) {
        if (data.name) {
          self.user = data.name;
          console.log(data.name);
          self.authenticated = true;
        } else {
          self.user = "N/A";
          self.authenticated = false;
        }
        console.log("user", $rootScope.user);
      }).error(function () {
        self.user = "N/A";
        self.authenticated = false;
      });
      $rootScope.user = self;
      $rootScope.authenticated = self.authenticated;
    }
    return $rootScope.authenticated;
  };
  return authServiceInstance;

}]);
