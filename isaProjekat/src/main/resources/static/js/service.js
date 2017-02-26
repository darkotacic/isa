var app = angular.module('webApp');

app.factory('SessionService', function sessionService($http) {
	
	sessionService.register = function(user){
		return $http({
			method : 'POST',
			url: '../guests/register',
			data: {
				"userName": user.userName,
				"password": user.password,
				"surname": user.surname,
				"email": user.email,
				"userRole": 'GUEST',
				"status": 'NOT_ACTIVE'
			}
		});
	}
	
	
	
	sessionService.getLoggedUser = function(){
		return $http({
			method : 'GET',
			url: '../users/loggedUser'

		});
	}
	
	sessionService.logOut = function(){
		return $http({
			method : 'GET',
			url: '../users/logout'

		});
	}
	
	sessionService.login = function(user){
		return $http({
			method : 'POST',
			url: '../users/login',
			data: {
				"email": user.email,
				"password": user.password,
			}
		});
	}
	
	sessionService.update = function(user){
		return $http({
			method : 'PUT',
			url: '../'+ user.userRole.toLowerCase()+'s/update',
			data: {
				"id": user.id,
				"userName": user.userName,
				"password": user.password,
				"surname": user.surname,
				"userRole": user.userRole,
				"email": user.email,
				"dateOfBirth": user.dateOfBirth,
				"shirtSize": user.shirtSize,
				"shoeNumber": user.shoeNumber
			}
		});
	}
	
	
	return sessionService;
	
});

app.factory('GuestService', function guestService($http) {
	
	guestService.getFriends = function(id){
		return $http({
			method : 'GET',
			url: '../guests/friends/'+id
		});
	}
	
	guestService.getRequests = function(id){
		return $http({
			method : 'GET',
			url: '../guests/recievedRequests/'+id
		});
	}
	
	guestService.acceptRequest = function(user_id,sender_id){
		return $http({
			method : 'POST',
			url: '../guests/acceptRequest?user_id='+user_id+'&sender_id='+sender_id
		});
	}
	
	guestService.declineRequest = function(user_id,sender_id){
		return $http({
			method : 'POST',
			url: '../guests/declineRequest?user_id='+user_id+'&sender_id='+sender_id
		});
	}
	
	guestService.removeFriend = function(user_id,friend_id){
		return $http({
			method : 'POST',
			url: '../guests/removeFriend?user_id='+user_id+'&friend_id='+friend_id
		});
	}

	return guestService;
});