'use strict';
/**
 * Created by jenn on 3/13/16.
 */

angular.module('chart-share').factory('authService', ['$rootScope', '$http', '$location', function ($rootScope, $http, $location) {
  var authServiceInstance = {};

  authServiceInstance.isAuthenticated = function (procAuth) {
    var self = {};
    //console.log("In isAuthenticated - rootscope authenticated", $rootScope.authenticated);
    if ($rootScope.authenticated != true) {
      //console.log("Checking with server");
      $http.get("/user")
        .then(function success(resp) {
            //console.log("received response from server", resp.data.name);

          if (resp.data.authenticated == true) {
            $rootScope.user = resp.data.user;
            $rootScope.principalName = resp.data.principalName;
            $rootScope.principal = resp.data.principal;
            $rootScope.principalSource  = 'facebook';
            console.log("return from call to /user",resp.data);
            $rootScope.authenticated = true;
            $rootScope.person = resp.data.person;
          } else {
            $rootScope.authenticated = false;
          }
          procAuth($rootScope.authenticated);
    }, function error(err){
      console.log('request for authentication failed');
    })
} else {
  console.log("user", $rootScope.user, $rootScope.authenticated);
      procAuth($rootScope.authenticated);
  }
};


//else
//            {
//              console.log('you are NOT authenticated');
//              $rootScope.user = "N/A";
//              $rootScope.authenticated = false;
//              deferred.resolve(self);
//            }
//
//          } else{
//        console.log("already authenticated");
//        deferred.resolve(self.authenticated);
//      }
//    }
//
//          //.error(function (data) {
//          //console.log('ERROR - you are NOT authenticated');
//          //$rootScope.user = "N/A";
//          //$rootScope.authenticated = false;
//        //})



  authServiceInstance.logout = function () {
    //debugger;
    $http.post('/logout', {})
      .then(function success() {
      self.authenticated = false;
      $location.path("/");
    }, function error(err) {
      console.log("Logout failed");
    });
    $rootScope.user = null;
    $rootScope.authenticated = false;
    console.log("You are logged out");
  }

  return authServiceInstance;

}]);
