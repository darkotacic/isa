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

app.controller('restaurantController',['$rootScope','$scope','$location','$http','SessionService','SystemManagerService','WaiterService','GuestService',function($rootScope,$scope,$location,$http,sessionService,systemManagerService,waiterService,guestService) {
	
	if (!$rootScope.loggedUser) {
		$location.path('/login');
	} else if($rootScope.loggedUser.userRole == 'BIDDER' && $rootScope.loggedUser.firstLogIn)
		$location.path('/changePassword');
	
	
	$scope.selected = null;
	
	$scope.currentDate = new Date();
	
	$scope.selectedTables = [];
	
	$scope.selectedFriends = [];
	
	$scope.makeReservation = false;
	
	$scope.show = null;
	
	$scope.invite = function(friend){
		var friendIndex = $scope.selectedFriends.indexOf(friend.id);
		if(friendIndex == -1){
			$scope.selectedFriends.push(friend.id);
			friend.invited = true;
		} else {
			$scope.selectedFriends.splice(friendIndex,1);
			friend.invited = false;
		}
	}
	
	$scope.confirm = function(){
		guestService.createReservation($scope.reservation,$scope.selected.id).then(function(response){
			   var reservation = response.data
			   angular.forEach($scope.selectedTables, function(value, key){
				      guestService.createOrder(value,reservation.id,$scope.reservation.date).then(function(response){
				    	 $scope.lastAddedOrder = response.data; 
				      });
			   });
			   
			   angular.forEach($scope.selectedFriends, function(value, key){
				      guestService.inviteFriend(value,reservation.id).then(function(response){
				    	 $scope.lastAddedFriend = response.data; 
				      });
			   });
		})

	}
	
	
	$scope.initFriend = function(friend){
		friend.invited = false;
	}
	
	$scope.showMake = function(){
		$scope.makeReservation = true;
	}
	
	$scope.getTablesForSegment=function(id,index){
		waiterService.getTablesForSegment(id).then(function(response){
			$scope.segments[index].tables=response.data;
		});
	}
	
	
	$scope.addTable=function(table,index){
		if(table.free){
			var tableIndex = $scope.selectedTables.indexOf(table.id);
			if(tableIndex == -1){
				var id =table.segment.id+"#"+index;
				document.getElementById(id).style.backgroundColor ="blue";
				$scope.selectedTables.push(table.id);
			} else {
				var id =table.segment.id+"#"+index;
				document.getElementById(id).style.backgroundColor = null;
				$scope.selectedTables.splice(tableIndex,1);
			}
		}
	}
	
	
	$scope.showView = function(show){
		$scope.show = show;
	}
	
	$scope.clearTables=function(){
		$scope.selectedTables = [];
	}
	$scope.setSelected = function(selected){
		if($scope.selected == selected){
			$scope.selected = null;
		} else {
			$scope.selected = selected;
		}
		
		$scope.makeReservation = false;
		$scope.selctedSegment = null;
		$scope.reservation = null;
		$scope.show = null;
	}
	
	systemManagerService.getRestaurants($rootScope.loggedUser.id).then(function(response){
		$scope.restaurants = response.data;
	});
	
	guestService.getSegments().then(function(response){
		$scope.segments = response.data;
	});
	
	guestService.getFriends($rootScope.loggedUser.id).then(function(response){
		$scope.friends = response.data;
	});
	
	$scope.range = function(min, max, step) {
	    step = step || 1;
	    var input = [];
	    for (var i = min; i < max; i += step) {
	        input.push(i);
	    }
	    return input;
	};
	
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