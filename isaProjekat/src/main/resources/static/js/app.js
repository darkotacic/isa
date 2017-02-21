var app = angular.module('webApp',['ngRoute']);

app.config(function($routeProvider , $locationProvider){

	
	$routeProvider
		.when('/login', { 
			templateUrl: 'login.html',
			controller: 'loginController'
		})
		.when('/home', {
			templateUrl: 'home.html',
			controller: 'homeController'
		})
		.when('/register', {
			templateUrl: 'register.html',
			controller: 'registerController'
		})
		.when('/profile', {
			templateUrl: 'profile.html',
			controller: 'profileController'
		})
		.when('/registerRestaurant', {
			templateUrl: 'registerRestaurant.html',
			controller: 'systemManagerController'
		})
		.when('/showRestaurants', {
			templateUrl: 'restaurants.html',
			controller: 'systemManagerController'
		})
		.when('/showSystemManagers', {
			templateUrl: 'systemManagers.html',
			controller: 'systemManagerController'
		})
		.when('/showSystemManagerProfile', {
			templateUrl: 'editProfileSystemManager.html',
			controller: 'systemManagerController'
		})
		.when('/showActiveRequestOffers', {
			templateUrl: 'activeRequestOffers.html',
			controller: 'bidderController'
		})
		.when('/showBidderOffersForBidder', {
			templateUrl: 'bidderOffersForBidder.html',
			controller: 'bidderController'
		})
		.when('/showBidderProfile', {
			templateUrl: 'editProfileBidder.html',
			controller: 'bidderController'
		})
		.when('/showRestaurantForManager', {
			templateUrl: 'restaurantForManager.html',
			controller: 'restaurantManagerController'
		})
		.when('/showRestaurantWorkers', {
			templateUrl: 'workersOfRestaurant.html',
			controller: 'restaurantManagerController'
		})
});

