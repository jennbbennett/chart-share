'use strict';
/**
 * Created by jenn on 3/13/16.
 */

angular.module('chart-share').factory('authService', ['$rootScope', '$http', '$location', function ($rootScope, $http, $location) {
  var authServiceInstance = {};

  authServiceInstance.isAuthenticated = function (procAuth) {
    var self = {};
    console.log("In isAuthenticated - rootscope authenticated", $rootScope.authenticated);
    if ($rootScope.authenticated != true) {
      console.log("Checking with server");
      $http.get("/user")
        .then(function success(resp) {
            console.log("received response from server", resp.data.name);

          if (resp.data.name != undefined) {
            self.authenticated = true;
            self.user = resp.data.name;
            self.source = resp.data.source;
            $rootScope.user = resp.data.name;
            $rootScope.source = resp.data.source;
            console.log("return from call to /user",resp.data.name);
            $rootScope.authenticated = true;

            //going to get person data
            $http.get('/service/user/' + $rootScope.source + "/" + $rootScope.user)
              .then(function success(response) {
                self.person = response.data;
                $rootScope.person = response.data;
                console.log('you are a person');
              }, function error(err) {
                console.log('request for person failed');
              });
          } else {
            self.authenticated = false;
          }
          procAuth(self.authenticated);
    }, function error(err){
      console.log('request for authentication failed');
    })
} else {
  console.log("user", $rootScope.user, $rootScope.authenticated);
    self.user = $rootScope.user;
    self.authenticated = true;
    self.source = $rootScope.source;
      procAuth(self.authenticated);
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
