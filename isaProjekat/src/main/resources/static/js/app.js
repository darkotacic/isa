var app = angular.module('webApp',['ngRoute']);

//routeProvider
app.config(function($routeProvider , $locationProvider){

	
	$routeProvider
		.when('/login', { 
			templateUrl: 'login.html',
			controller: 'loginController'
		})
		.when('/register', {
			templateUrl: 'register.html',
			controller: 'registerController'
		})
});

