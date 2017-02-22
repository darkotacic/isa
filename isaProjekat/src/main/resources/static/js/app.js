var app = angular.module('webApp', [ 'ngRoute' ]);

// routeProvider
app.config(function($routeProvider, $locationProvider) {

	$routeProvider.when('/login', {
		templateUrl : 'login.html',
		controller : 'loginController'
	}).when('/registerRestaurant', {
		templateUrl : 'registerRestaurant.html',
		controller : 'systemManagerController'
	}).when('/showRestaurants', {
		templateUrl : 'restaurants.html',
		controller : 'systemManagerController'
	}).when('/showSystemManagers', {
		templateUrl : 'systemManagers.html',
		controller : 'systemManagerController'
	}).when('/showSystemManagerProfile', {
		templateUrl : 'editProfileSystemManager.html',
		controller : 'systemManagerController'
	}).when('/showActiveRequestOffers', {
		templateUrl : 'activeRequestOffers.html',
		controller : 'bidderController'
	}).when('/showBidderOffersForBidder', {
		templateUrl : 'bidderOffersForBidder.html',
		controller : 'bidderController'
	}).when('/showBidderProfile', {
		templateUrl : 'editProfileBidder.html',
		controller : 'bidderController'
	}).when('/showRestaurantForManager', {
		templateUrl : 'restaurantForManager.html',
		controller : 'restaurantManagerController'
	}).when('/showRestaurantWorkers', {
		templateUrl : 'workersOfRestaurant.html',
		controller : 'restaurantManagerController'
	}).when('/showRestaurantSegments', {
		templateUrl : 'segmentsOfRestaurant.html',
		controller : 'restaurantManagerController'
	})
	.when('/showRestaurantWorkSchedules', {
		templateUrl : 'restaurantShifts.html',
		controller : 'restaurantManagerController'
	})
	.when('/showManagerOffers', {
		templateUrl : 'managersRequestOffers.html',
		controller : 'restaurantManagerController'
	})
});
