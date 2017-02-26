var app = angular.module('webApp', [ 'ngRoute' , 'chart.js' , 'ngNotify','ngMessages' ]);

// routeProvider
app.config(function($routeProvider, $locationProvider) {

	$routeProvider.when('/login', {
		templateUrl : 'login.html',
		controller : 'loginController'
	}).when('/home', {
		templateUrl: 'home.html',
		controller: 'homeController'
	}).when('/register', {
		templateUrl: 'register.html',
		controller: 'registerController'
	}).when('/profile', {
		templateUrl: 'profile.html',
		controller: 'profileController'
	}).when('/showSystemManagerProfile', {
		templateUrl : 'editProfileSystemManager.html',
		controller : 'systemManagerController'
	}).when('/showBidderProfile', {
		templateUrl : 'editProfileBidder.html',
		controller : 'bidderController'
	}).when('/changePassword', {
		templateUrl : 'changePassword.html',
		controller : 'bidderController'
	})
});
