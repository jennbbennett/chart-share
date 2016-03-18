'use strict';
/**
 * Created by jenn on 3/13/16.
 */

angular.module('chart-share').factory('authService', ['$rootScope', '$http', '$location', function ($rootScope, $http, $location) {
  var authServiceInstance = {};

  authServiceInstance.isAuthenticated = function () {
    var self = {};

    if (self.authenticated != true) {
      $http.get("/user")
        .then(function success(data) {


          if (data.name !== null) {
            self.authenticated = true;
            self.user = data.name;
            self.source = data.source;
            $rootScope.user = data.name;
            $rootScope.source = data.source;
            console.log(data.name);
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
          }

    }, function error(err){
      console.log('request for authentication failed');
    })
} else {
  console.log("user", $rootScope.user, $rootScope.authenticated);
    self.user = $rootScope.user;
    self.authenticated = true;
    self.source = $rootScope.source;
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
