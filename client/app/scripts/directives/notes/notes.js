angular.module('chart-share')
  .directive('itemNotes', ['$rootScope', '$http', function ($rootScope, $http) {
    return {
      templateUrl: 'scripts/directives/notes/notes.html?v=' + window.app_version,
      restrict: 'E',
      replace: true,
      scope: {
        target: '=targetId'
      },link: function (scope, element, attrs) {
        if (!attrs.targetId) {
          scope.targetId = 0;
        } else {
          scope.targetId = attrs.targetId;
        }
        console.log("Link id " +  attrs.targetId);
      },
      controller:['$scope','$attrs', function ($scope,$attrs) {
        console.log("In notes directive controller",$scope.target);
        $http.get('/service/note/' + $attrs.targetType+'/' + $attrs.targetId).then(function (response){
          console.log("notes response",response.data);
          $scope.notes = response.data;
        })

        $scope.deleteNote = function(note){
          console.log('I am deleting a note', note.id);

          $http.delete('/service/note/' + note.id).then(function(response) {
            console.log('response from delete', response);
            var newNotes = [];
            $scope.notes.map(function(oldnote){
              if(oldnote.id != note.id){
                newNotes.push(oldnote);
              }
            });
            $scope.notes = newNotes;
          })

        }

        $scope.createNote = function(note){
          console.log("I will create a note with this information", note);
          $http.post('/service/note', {
            title: note.title,
            text: note.text,
            targetType: $attrs.targetType,
            targetId: $scope.params.physicianId
          }).then(function (resp){
            console.log("response from note post", resp.data);
          })

        }
        //$scope.view.searchKeyword = '';
      }]
    }
  }]);
