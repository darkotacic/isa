var app = angular.module('webApp');

app.controller('appController',['$rootScope','$scope','$location','SessionService','ShoppingCartService',function($rootScope,$scope,$location,sessionService,shoppingCartService){
	
	
	sessionService.getLoggedUser().then(function(response){
		$rootScope.loggedUser = response.data;
		if (!$rootScope.loggedUser) {
			$location.path('/login');
		} else {
			$location.path('/shoppingCart');
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
		$location.path('/shoppingCart');
	}
	
	$scope.error = false;
	
	$scope.login = function(){
		var user = $scope.user;
	    sessionService.login(user).then(function(response){
	    	if(response.data){
	    		$scope.error = false;
    			$rootScope.logged = true;
    			$rootScope.loggedUser = response.data;
	    		$location.path('/shoppingCart');
	    	} else {
	    		$scope.error = true;
	    	}
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

app.controller('shopController',['$rootScope','$scope','$location','$http','ShopService','ProductService',function($rootScope,$scope,$location,$http,shopService,productService) {
	
	if (!$rootScope.loggedUser) {
		$location.path('/login');
	} 
	
	$scope.shopProducts = [];
	
	$scope.defaultItem = {
			"name": '',
			"country": '',
			"grade": undefined,
		};
	
	$scope.searchTypes = ["Name","Country","Grade"];
	
	productService.getProducts().then(function(response){
		$scope.products = response.data;
	});
	
	shopService.getShops().then(function(response){
		$scope.shops = response.data;
	});
	
	$scope.refreshSearch = function(){
		$scope.shopSearch = angular.copy($scope.defaultItem);
	}
	
	$scope.search = function(){
		var shopSearch = angular.copy($scope.shopSearch);
		var searchParameter = angular.copy($scope.searchShop);
		var searchType = angular.copy($scope.showSearch);
		
		shopService.searchProducts(shopSearch,searchType,searchParameter).then(function(response){
			$scope.shops = response.data;
		});
	}
	
	$scope.setSearchType = function(searchType){
		if(searchType=='undo'){
			shopService.getShops().then(function(response){
				$scope.shops = response.data;
			})
		}
		$scope.shopSearch = angular.copy($scope.defaultItem);
		
		$scope.showSearch = searchType;
	}
	
	$scope.getShopProducts = function(){
		var shop = $scope.selected;
		productService.getProductsForShop(shop.code).then(function(response){
			$scope.shopProducts = response.data;
			$scope.show = 3;
		});
	}
	
	$scope.setSelected = function(selected){
		$scope.editShop = '';
		$scope.newShop = '';
		$scope.selected = selected;
		$scope.show = '';
		
	}	
	
	$scope.display = function(tab){
		$scope.editShop = '';
		$scope.show = tab;
	}
	
	$scope.addShop = function(){
		var shop = $scope.newShop;
		shopService.addShop(shop).then(function(response){
    		if(response.data){
    			$scope.error = false;
    			alert("Uspesno dodat shop: " + response.data.code);
    			$scope.shops.push(response.data);
    			$scope.show = null;
    			$scope.newShop = '';
    		} else {
    			$scope.error = true;
    		}
    	});
	}
	
	$scope.deleteShop = function(){
		var shop = $scope.selected;
		shopService.deleteShop(shop).then(function(response){
    		if(response.data){
    			alert("Uspesno obrisan shop: " + response.data.code);
    			var index = $scope.shops.indexOf($scope.selected);
    			$scope.shops.splice(index,1);
    			$scope.selected = null;
    			$scope.show = '';
    		} else {
    		}
		});	
	}
	
	
	$scope.updateShop = function(){
		$scope.editShop.code = $scope.selected.code;
		$scope.editShop.name = $scope.selected.name;
		var shop = $scope.editShop;
		shopService.updateShop(shop).then(function(response){
    		if(response.data){
    			alert("Uspesno izmenjen shop: " + response.data.code);
    			var index = $scope.shops.indexOf($scope.selected);
    			$scope.shops[index] = response.data;
    			$scope.selected = null;
    			$scope.show = '';
    			$scope.editShop = '';
    		} else {
    		}
		});	
	}
	
}]);

app.controller('productController',['$rootScope','$scope','$location','$http','SessionService','ProductService','ShoppingCartService','CategoryService','ShopService','ActionService',function($rootScope,$scope,$location,$http,sessionService,productService,shoppingCartService,categoryService,shopService,actionService) {
	
	if (!$rootScope.loggedUser) {
		$location.path('/login');
	} 
	
	$scope.defaultItem = {
			"name": '',
			"range": {
				"max": 500,
				"min": 50,
			},
			"description": '',
			"country": '',
			"category": null,
			"color": '',
			"grade": undefined,
			"reviewCount": undefined,
			"quantity": undefined
		};
	
	actionService.getActions().then(function(response){
		$scope.actions = response.data;
	});
	
	productService.getProducts().then(function(response){
		$scope.products = response.data;
	});
	
	
	shopService.getShops().then(function(response){
		$scope.shops = response.data;
	});
	
	$scope.addOrRemoveFromWishlist = function(product){
		var add = true;
		if($rootScope.loggedUser.wishlist.wishlist == undefined){
			$rootScope.loggedUser.wishlist.wishlist = [];
		} else {
			var i;
			var wishlist = $rootScope.loggedUser.wishlist.wishlist;
			for(i = 0; i < wishlist.length;i++){
				if(wishlist[i].code === product.code){
					add = false;
					break;
				}
			}
		}
		
		if(add){
			sessionService.addProductToWishlist(product).then(function(response){
				$rootScope.loggedUser.wishlist.wishlist.push($scope.selected);
				var actions = $scope.actions;
				var i;
				for(i = 0; i < actions.length;i++){
					if(product.code == actions[i].productCode){
						alert("Product " + product.code + " is on action");
					}
				}
			});
		} else {
			sessionService.deleteProductFromWishlist(product).then(function(response){
				var index = $rootScope.loggedUser.wishlist.wishlist.indexOf($scope.selected);
				$rootScope.loggedUser.wishlist.wishlist.splice(index,1);
			});
		}
	}
	
	
	$scope.isOnWishlist = function(product){
		if($rootScope.loggedUser != '' && $rootScope.loggedUser.wishlist.wishlist != undefined){
			var wishlist = $rootScope.loggedUser.wishlist.wishlist;
			var i;
			for(i = 0; i < wishlist.length;i++){
				if(wishlist[i].code === product.code){
					return true;
				}
			}
		}
		return false;
		
	}
	
	$scope.refreshSearch = function(){
		$scope.productSearch = angular.copy($scope.defaultItem);
	}
	
	
	$scope.searchTypes = ["Name","Price range","Description","Category","Country",
	                      "Color","Grade","Review count","Quantity"
	                      ];
	
	$scope.displayReview = function(showReview){
		if(showReview == 1){
			$scope.selectedReview = '';
		}
		$scope.showReview = showReview;
	}
	
	$scope.addReview = function(){
		var reviewNumber = $scope.selected.reviews.length+1;
		$scope.newReview.code = $scope.selected.code + '#' + reviewNumber;
		$scope.newReview.username = $rootScope.loggedUser.username;
		$scope.newReview.date = new Date();
		$scope.newReview.grade = 0;
		var newReview = $scope.newReview;
		productService.addReview(newReview).then(function(response){
			$scope.selected.reviews.push(response.data);
			$scope.showReview = '';
		});
	}
	
	$scope.closeReviews = function(){
		$scope.showReview = '';
		$scope.show = '';
	}
	
	$scope.updateReview = function(){
		$scope.editReview.code = $scope.selectedReview.code;
		$scope.editReview.username = $rootScope.loggedUser.username;
		$scope.editReview.date = new Date();
		$scope.editReview.grade = $scope.selectedReview.grade;
		var editReview = $scope.editReview;
		
		productService.updateReview(editReview).then(function(response){
			var index = $scope.selected.reviews.indexOf($scope.selectedReview);
			$scope.selected.reviews[index] = response.data;
			$scope.showReview = '';
		});
	}
	
	$scope.deleteReview = function(){

		var review = $scope.selectedReview;
		
		productService.deleteReview(review).then(function(response){
			var index = $scope.selected.reviews.indexOf($scope.selectedReview);
			$scope.selected.reviews.splice(index,1);
			$scope.selectedReview = '';
			$scope.showReview = '';
		});
	}
	
	
	$scope.search = function(){
		var categories = $scope.categories;
		for (var i = 0; i < categories.length; i++) { 
		    if(categories[i].name === $scope.productSearch.category){
		    	$scope.productSearch.category = categories[i];
		    	break;
		    }
		}
		var productSearch = angular.copy($scope.productSearch);
		var searchParameter = angular.copy($scope.searchProduct);
		var searchType = angular.copy($scope.showSearch);
		if(searchType=='combined'){
			$scope.ProductSearch.category = anguar.copy(productSearch.category.name);
		}
		
		productService.searchProducts(productSearch,searchType,searchParameter).then(function(response){
			$scope.products = response.data;
		});
	}
	
	
	$scope.setSearchType = function(searchType){
		if(searchType=='undo'){
			productService.getProducts().then(function(response){
				$scope.products = response.data;
			})
		}
		$scope.productSearch = angular.copy($scope.defaultItem);
		$scope.showSearch = searchType;
	}
	
	$scope.addToCart = function(){
		var product = $scope.selected;
		shoppingCartService.addItem(product).then(function(response){
			if(response.data){
				alert("Product "+ response.data.code+" prebacen u korpu");
				$rootScope.loggedUser.shoppingCart.shoppingCart.push(response.data);
			}
		});
	}
	
	categoryService.getCategories().then(function(response){
		$scope.categories = response.data;
	});
	
	
	productService.getProducts().then(function(response){
		$scope.products = response.data;
	});
	
	$scope.setSelectedReview = function(selectedReview){
		$scope.newReview = '';
		$scope.editReview = '';
		$scope.selectedReview = selectedReview;
	}
	
	$scope.setSelected = function(selected){
		$scope.editProduct = '';
		$scope.newProduct = '';
		if($scope.show==3){
			$scope.show = '';
			$scope.showReview = '';
			$scope.selectedReview = '';
		}
		$scope.selected = selected;
	}	
	
	$scope.display = function(tab){
		$scope.editProduct = '';
		$scope.show = tab;
	}
	
	$scope.addProduct = function(){
		var categories = $scope.categories;
		for (var i = 0; i < categories.length; i++) { 
		    if(categories[i].name === $scope.newProduct.category){
		    	$scope.newProduct.category = categories[i];
		    	break;
		    }
		}
		var product = $scope.newProduct;
		productService.addProduct(product).then(function(response){
    		if(response.data){
    			$scope.error = false;
    			alert("Uspesno dodat product: " + response.data.code);
    			$scope.products.push(response.data);
    			$scope.show = null;
    			$scope.newProduct = '';
    		} else {
    			$scope.error = true;
    		}
    	});
	}
	
	$scope.deleteProduct = function(){
		var product = $scope.selected;
		productService.deleteProduct(product).then(function(response){
    		if(response.data){
    			alert("Uspesno obrisan product: " + response.data.code);
    			var index = $scope.products.indexOf($scope.selected);
    			$scope.products.splice(index,1);
    			$scope.selected = null;
    			$scope.show = '';
    		} else {
    		}
		});	
	}
	
	$scope.updateProduct = function(){
		$scope.editProduct.code = $scope.selected.code;
		$scope.editProduct.shop = $scope.selected.shop;
		$scope.editProduct.reviews = $scope.selected.reviews;
		var categories = $scope.categories;
		for (var i = 0; i < categories.length; i++) { 
		    if(categories[i].name === $scope.editProduct.category){
		    	$scope.editProduct.category = categories[i];
		    	break;
		    }
		}
		var product = $scope.editProduct;
		productService.updateProduct(product).then(function(response){
    		if(response.data){
    			alert("Uspesno izmenjen product: " + response.data.code);
    			var index = $scope.products.indexOf($scope.selected);
    			$scope.products[index] = response.data;
    			$scope.selected = null;
    			$scope.show = '';
    			$scope.editProduct = '';
    		} else {
    		}
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