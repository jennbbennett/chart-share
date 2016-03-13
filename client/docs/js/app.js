'use strict';

var app = angular.module('docs',[]);
app.controller('todoCtrl', function ($scope) {
 	$scope.todos = [
 	{text:'Meeting with Xian Lu.', done:false, id: 'option1'}, 
 	{text:'Exercise at 6:pm.', done:false, id: 'option3'},         
 	{text:'Avengers Age of Ultron.', done:false, id: 'option4'},         
 	{text: 'Sarah birthday', done:false, id: 'option5'}
 	];
  	function makeid()
 	{
 		var text = "";
 		var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

 		for( var i=0; i < 5; i++ )
 			text += possible.charAt(Math.floor(Math.random() * possible.length));

 		return text;
 	}
 	$scope.addTodo = function () {
 		$scope.todos.push({text:$scope.formTodoText, done:false, id:makeid()});
 		$scope.formTodoText = '';
 	};

 });
app.directive('todolist',function(){
		return {
	    templateUrl:'js/directive/to-do.html',
	    restrict: 'E',
	    replace: true,
    	controller: function($scope){

			setTimeout(function(){
    			$('.todo-list-wrap').perfectScrollbar();
			}, 100);

        }
	}
});