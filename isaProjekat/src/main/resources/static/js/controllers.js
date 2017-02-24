var app = angular.module('webApp');

app.controller('appController',['$rootScope','$scope','$location','SessionService','ShoppingCartService',function($rootScope,$scope,$location,sessionService,shoppingCartService){
	
	
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
    			alert("Uspesno registrovan:" + response.data.username);
    			$location.path('/login')
    		} else {
    			$scope.error = true;
    		}
    	});
	}
}]);

app.controller('homeController',['$rootScope','$scope','$location','$http',function($rootScope,$scope,$location,$http) {
	
	if (!$rootScope.loggedUser) {
		$location.path('/login');
	}  else if($rootScope.loggedUser.userRole == 'BIDDER' && $rootScope.loggedUser.firstLogIn)
		$location.path('/changePassword');
	
	
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

app.controller('delivererController',['$rootScope','$scope','$location','$http','DelivererService',function($rootScope,$scope,$location,$http,delivererService) {
	
	if (!$rootScope.loggedUser) {
		$location.path('/login');
	} 
	
	delivererService.getDeliverers().then(function(response){
		$scope.deliverers = response.data;
	});
	
	$scope.setSelected = function(selected){
		$scope.editDeliverer = '';
		$scope.newDeliverer = '';
		$scope.selected = selected;
	}	
	
	$scope.display = function(tab){
		if(tab==1){
			$scope.selected = null;
		}
		$scope.editDeliverer = '';
		$scope.show = tab;
	}
	
	
	$scope.addDeliverer = function(){
		var deliverer = $scope.newDeliverer;
		delivererService.addDeliverer(deliverer).then(function(response){
    		if(response.data){
    			$scope.error = false;
    			alert("Uspesno dodat deliverer: " + response.data.code);
    			$scope.deliverers.push(response.data);
    			$scope.show = null;
    			$scope.newDeliverer = '';
    		} else {
    			$scope.error = true;
    		}
    	});
	}
	
	$scope.deleteDeliverer = function(){
		var deliverer = $scope.selected;
		delivererService.deleteDeliverer(deliverer).then(function(response){
    		if(response.data){
    			alert("Uspesno obrisan deliverer: " + response.data.code);
    			var index = $scope.deliverers.indexOf($scope.selected);
    			$scope.deliverers.splice(index,1);
    			$scope.selected = null;
    			$scope.show = '';
    		} else {
    		}
		});	
	}
	
	$scope.updateDeliverer = function(){
		$scope.editDeliverer.code = $scope.selected.code;
		var deliverer = $scope.editDeliverer;
		delivererService.updateDeliverer(deliverer).then(function(response){
    		if(response.data){
    			alert("Uspesno izmenjen deliverer: " + response.data.code);
    			var index = $scope.deliverers.indexOf($scope.selected);
    			$scope.deliverers[index] = response.data;
    			$scope.selected = null;
    			$scope.show = '';
    			$scope.editDeliverer = '';
    		} else {
    		}
		});	
	}
}]);

app.controller('categoryController',['$rootScope','$scope','$location','$http','CategoryService',function($rootScope,$scope,$location,$http,categoryService) {
	
	if (!$rootScope.loggedUser) {
		$location.path('/login');
	} 
	
	categoryService.getCategories().then(function(response){
		$scope.categories = response.data;
	});
	
	$scope.setSelected = function(selected){
		$scope.editCategory = '';
		$scope.newCategory = '';
		$scope.selected = selected;
	}	
	
	$scope.display = function(tab){
		if(tab==1){
			$scope.selected = null;
		}
		$scope.editCategory = '';
		$scope.show = tab;
	}
	
	$scope.addCategory = function(){
		var categories = $scope.categories;
		for (var i = 0; i < categories.length; i++) { 
		    if(categories[i].name === $scope.newCategory.subcategory){
		    	$scope.newCategory.subcategory = categories[i];
		    	break;
		    }
		}
		var category = $scope.newCategory;
		categoryService.addCategory(category).then(function(response){
    		if(response.data){
    			$scope.error = false;
    			alert("Uspesno dodat category: " + response.data.name);
    			$scope.categories.push(response.data);
    			$scope.show = null;
    			$scope.newCategory = '';
    		} else {
    			$scope.error = true;
    		}
    	});
	}
	
	$scope.deleteCategory = function(){
		var category = $scope.selected;
		categoryService.deleteCategory(category).then(function(response){
    		if(response.data){
    			$scope.categories = response.data
    			$scope.selected = null;
    			$scope.show = '';
    		} else {
    		}
		});	
	}
	
	$scope.updateCategory = function(){
		$scope.editCategory.name = $scope.selected.name;
		var categories = $scope.categories;
		for (var i = 0; i < categories.length; i++) { 
		    if(categories[i].name === $scope.editCategory.subcategory){
		    	$scope.editCategory.subcategory = categories[i];
		    	break;
		    }
		}
		var category = $scope.editCategory;
		categoryService.updateCategory(category).then(function(response){
    		if(response.data){
    			alert("Uspesno izmenjen category: " + response.data.name);
    			var index = $scope.categories.indexOf($scope.selected);
    			$scope.categories[index] = response.data;
    			$scope.selected = null;
    			$scope.show = '';
    			$scope.editCategory = '';
    		} else {
    		}
		});	
	}
}]);

app.controller('shoppingCartController',['$rootScope','$scope','$location','$http','ShoppingCartService','DelivererService',function($rootScope,$scope,$location,$http,shoppingCartService,delivererService) {
	
	
	shoppingCartService.getItems().then(function(response){
		$scope.products = response.data;
	});
	
	delivererService.getDeliverers().then(function(response){
		$scope.deliverers =  response.data;
	});
	
	$scope.removeItem = function(product){
		shoppingCartService.removeItem(product).then(function(response){
			var index = $scope.products.indexOf($scope.selected);
			$scope.products.splice(index,1);
			$rootScope.loggedUser.shoppingCart.shoppingCart.splice(index,1);
		});
		
	}
	
	$scope.setSelected = function(selected){
		$scope.selected = selected;
	}	
	
	
}]);

app.controller('actionController',['$rootScope','$scope','$location','$http','ActionService','ProductService',function($rootScope,$scope,$location,$http,actionService,productService) {
	
	
	actionService.getActions().then(function(response){
		$scope.actions = response.data;
	});
	
	productService.getProducts().then(function(response){
		$scope.products = response.data;
	});
	
	
	$scope.deleteAction = function(){
		var action = $scope.selected;
		actionService.deleteAction(action).then(function(response){
			var index = $scope.actions.indexOf($scope.selected);
			$scope.actions.splice(index,1);
			$scope.selected = '';
		});
		
	}
	
	$scope.addAction = function(){
		var action = $scope.newAction;
		actionService.addAction(action).then(function(response){
			$scope.actions.push(response.data);
			$scope.show = '';
			$scope.newAction = '';
		});
		
	}
	
	$scope.validProduct = function(product){
		var valid = true;
		var actions = $scope.actions;
		var i;
		for(i = 0; i < actions.length;i++){
			if(actions[i].productCode===product.code){
				valid = false;
				break;
			}
		}
		return valid;
	}
	
	$scope.display = function(tab){
		if(tab==1){
			$scope.selected = '';
		}
		$scope.show = 1;
		
	}
	
	$scope.setSelected = function(selected){
		$scope.selected = selected;
		$scope.show = '';
		$scope.newAction = '';
	}	
	
}]);