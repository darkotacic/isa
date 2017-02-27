var app = angular.module('webApp');


var compareTo = function() {
    return {
        require: "ngModel",
        scope: {
            otherModelValue: "=compareTo"
        },
        link: function(scope, element, attributes, ngModel) {
             
            ngModel.$validators.compareTo = function(modelValue) {
                return modelValue == scope.otherModelValue;
            };
 
            scope.$watch("otherModelValue", function() {
                ngModel.$validate();
            });
        }
    };
};

app.directive("compareTo", compareTo);


app.controller('appController',['$rootScope','$scope','$location','SessionService',function($rootScope,$scope,$location,sessionService){
	
	
	sessionService.getLoggedUser().then(function(response){
		$rootScope.loggedUser = response.data;
		if (!$rootScope.loggedUser) {
			$location.path('/login');
		} else {
			if($rootScope.loggedUser.userRole == 'BIDDER' && $rootScope.loggedUser.firstLogIn)
				$location.path('/changePassword');
			else
				$location.path('/home');
		}
	});
	
	
	$scope.logout = function(){
		$rootScope.loggedUser = '';
		
		
		sessionService.logOut().then(function(response){
		});
		
	}
}]);

app.controller('loginController',['$rootScope','$scope','$location','SessionService',function($rootScope,$scope,$location,sessionService){
		
	
	if (!$rootScope.loggedUser) {
		$location.path('/login');
	} else {
		if($rootScope.loggedUser.userRole == 'BIDDER' && $rootScope.loggedUser.firstLogIn)
			$location.path('/changePassword');
		else
			$location.path('/home');
	}
	
	
	$scope.login = function(){
		var user = $scope.user;
	    sessionService.login(user)
	    .then(function(response){
    		$rootScope.logged = true;
    		$rootScope.loggedUser = response.data;
    		swal({
    			  title: "Success!",
    			  text: "Welcome " + response.data.userName,
    			  type: "success",
    			  timer: 2000
 
    			});
    		if($rootScope.loggedUser.userRole == 'BIDDER' && $rootScope.loggedUser.firstLogIn)
				$location.path('/changePassword');
			else
				$location.path('/home');
	    })
	    .catch(function(response) {
	    	swal("ERROR", "BAD CREDENTIALS", "error");
	    });
	
	}
	
    $scope.register = function(){
    	$location.path('/register')
    }
}]);

app.controller('registerController',['$rootScope','$scope','$location','$http','SessionService',function($rootScope,$scope,$location,$http,sessionService) {
	
	
	if (!$rootScope.loggedUser) {
		$location.path('/register');
	} else {
		$location.path('/shoppingCart');
	}
	
	$scope.error = false;
	
    $scope.register = function(){
    	var user = $scope.user;
    	sessionService.register(user).then(function(response){
    		if(response.data){
    			$scope.error = false;
    			$location.path('/login')
    		} else {
    			$scope.error = true;
    		}
    	});
	}
}]);

app.controller('homeController',['$rootScope','$scope','$location','$http', 'RestaurantManagerService',function($rootScope,$scope,$location,$http, restaurantManagerService) {
	
	if (!$rootScope.loggedUser) {
		$location.path('/login');
	}  else if($rootScope.loggedUser.userRole == 'BIDDER' && $rootScope.loggedUser.firstLogIn)
		$location.path('/changePassword');
	
	restaurantManagerService
	.checkIfRequestOfferExpired();
	
	restaurantManagerService
	.checkIfWorkScheduleIsDone();
	
	$scope.minDate = moment(new Date()).format('YYYY-MM-DD')

	
	switch($rootScope.loggedUser.userRole) {
	    case 'GUEST':
	    	$scope.show = 1;
	        break;
	    case 'WAITER':
	    	$scope.show = 6;
	        break;
	    case 'COOK':
	    	$scope.show = 17;
	        break;
	    case 'BARTENDER':
	    	$scope.show = 18;
	        break;
	}
	
	$scope.showView = function(number){
		$scope.show = number;
	}
	
}]);


app.controller('profileController',['$rootScope','$scope','$location','$http','SessionService',function($rootScope,$scope,$location,$http,sessionService) {
	
	if (!$rootScope.loggedUser) {
		$location.path('/login');
	} else if($rootScope.loggedUser.userRole == 'BIDDER' && $rootScope.loggedUser.firstLogIn)
		$location.path('/changePassword');
	
	
	sessionService.getLoggedUser().then(function(response){
		$scope.user = response.data;
		$scope.user.dateOfBirth = new Date(response.data.dateOfBirth);
	});
	
	$scope.confirmEditProfile=function(){
		sessionService.update($scope.user).then(function(response){
			$rootScope.loggedUser = response.data;
		});
	}

}]);

app.controller('restaurantController',['$rootScope','$scope','$location','$http','SessionService','SystemManagerService',function($rootScope,$scope,$location,$http,sessionService,systemManagerService) {
	
	if (!$rootScope.loggedUser) {
		$location.path('/login');
	} else if($rootScope.loggedUser.userRole == 'BIDDER' && $rootScope.loggedUser.firstLogIn)
		$location.path('/changePassword');
	
	systemManagerService.getRestaurants($rootScope.loggedUser.id).then(function(response){
		$scope.restaurants = response.data;
	});
	
}]);

app.controller('friendsController',['$rootScope','$scope','$location','$http','GuestService',function($rootScope,$scope,$location,$http,guestService) {
	
	if (!$rootScope.loggedUser) {
		$location.path('/login');
	} 
	
	$scope.forRemoval = null;
	
	$scope.showRequest = true;
	$scope.showFriend = true;
	$scope.search = true;
	$scope.showSent = true;
	
	
		
	$scope.showDialog = function(friend){
		document.getElementById('id01').style.display='block';
		$scope.display = friend;
	}
	
	$scope.showSearch = function(){
		if(!$scope.search){
			$scope.search = true;
		}else{
			$scope.search = false;
		}
	}
	
	$scope.showSents = function(){
		if(!$scope.showSent){
			$scope.showSent = true;
		}else{
			$scope.showSent = false;
		}
	}
	
	$scope.showRequests = function(){
		if(!$scope.showRequest){
			$scope.showRequest = true;
		}else{
			$scope.showRequest = false;
		}
	}
	
	$scope.showFriends = function(){
		if(!$scope.showFriend){
			$scope.showFriend = true;
		}else{
			$scope.showFriend = false;
		}
	}
	
	$scope.acceptRequest = function(request){
		$scope.forRemoval = request;
		guestService.acceptRequest($rootScope.loggedUser.id,request.id).then(function(response){
			var index = $scope.requests.indexOf($scope.forRemoval);
			$scope.requests.splice(index,1);
			$scope.friends.push(response.data);
			$scope.forRemoval = null;
		});
	}
	
	$scope.declineRequest = function(request){
		$scope.forRemoval = request;
		guestService.declineRequest($rootScope.loggedUser.id,request.id).then(function(response){
			var index = $scope.requests.indexOf($scope.forRemoval);
			$scope.requests.splice(index,1);
			$scope.forRemoval = null;
		});
	}
	
	$scope.removeFriend = function(friend){
		$scope.forRemoval = friend;
		guestService.removeFriend($rootScope.loggedUser.id,friend.id).then(function(response){
			var index = $scope.friends.indexOf($scope.forRemoval);
			$scope.friends.splice(index,1);
			$scope.nonFriends.push($scope.forRemoval)
			$scope.forRemoval = null;
		});
	}
	
	$scope.sendRequest = function(reciever){
		$scope.forRemoval = reciever
		guestService.sendRequest($rootScope.loggedUser.id,reciever.id).then(function(response){
			var index = $scope.nonFriends.indexOf($scope.forRemoval);
			$scope.nonFriends.splice(index,1);
			$scope.sentRequests.push($scope.forRemoval);
			$scope.forRemoval = null;
		});
	}
	
	guestService.getFriends($rootScope.loggedUser.id).then(function(response){
		$scope.friends = response.data;
	});
	
	guestService.getRequests($rootScope.loggedUser.id).then(function(response){
		$scope.requests = response.data;
	});
	
	guestService.getSentRequests($rootScope.loggedUser.id).then(function(response){
		$scope.sentRequests = response.data;
	});
	
	guestService.getNonFriends($rootScope.loggedUser.id).then(function(response){
		$scope.nonFriends = response.data;
	});



}]);