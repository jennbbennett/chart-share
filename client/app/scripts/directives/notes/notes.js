angular.module('chart-share')
  .directive('itemNotes', ['$rootScope', '$http', function ($rootScope, $http) {
    return {
      templateUrl: 'scripts/directives/notes/notes.html?v=' + window.app_version,
      scope: {
        targetId: '@'
      },
      link :function(scope, element, attributes){

        console.log("In notes directive controller",attributes.targetId);
        console.log("In notes directive scope target id ",scope.targetId);
        scope.targetType = attributes.targetType;
        attributes.$observe('targetId', function(value){
          if(value){
            scope.targetId = value;
            $http.get('/service/note/' + attributes.targetType+'/' + value).then(function (response){
              console.log("notes response",response.data);
              scope.notes = response.data;
            })
          }
        });
        scope.deleteNote = function(note){
          console.log('I am deleting a note', note.id);

          $http.delete('/service/note/' + note.id).then(function(response) {
            console.log('response from delete', response);
            var newNotes = [];
            scope.notes.map(function(oldnote){
              if(oldnote.id != note.id){
                newNotes.push(oldnote);
              }
            });
            scope.notes = newNotes;
          })

        }




        //$scope.view.searchKeyword = '';
      },
      controller: function($scope){
        $scope.add = {};
        $scope.add.show = false;

        $scope.toggleAddNote = function () {
          $scope.add.show = !$scope.add.show;
        };

        $scope.createNote = function(note){
          console.log("I will create a note with this information", note);
          $http.post('/service/note', {
            title: note.title,
            text: note.text,
            targetType: $scope.targetType,
            targetId: $scope.targetId,
            groupId: $rootScope.group.id
          }).then(function (resp){
            console.log("response from note post", resp.data);
            $scope.note = {};
            $scope.toggleAddNote();
            $scope.notes.push(resp.data);
          })

        }

      }

  };}]);
