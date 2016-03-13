'use strict';

/**
 * @ngdoc function
 * @name AniTheme.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of AniTheme
 */
 angular.module('chart-share').controller('todoCtrl', function ($scope) {
 	$scope.todos = [
 	{text:'Meeting with Xian Lu.', done:false, id: 'option1'},
 	{text:'Exercise at 6:pm.', done:false, id: 'option3'},
 	{text:'Avengers Age of Ultron.', done:false, id: 'option4'},
 	{text: 'Sarah birthday', done:false, id: 'option5'}
 	];
 	$scope.getTotalTodos = function () {
 		return $scope.todos.length;
 	};
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

 	$scope.clearCompleted = function () {
 		$scope.todos = _.filter($scope.todos, function(todo){
 			return !todo.done;
 		});
 	};
 });
