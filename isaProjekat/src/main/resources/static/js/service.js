var app = angular.module('webApp');

app.factory('SessionService', function sessionService($http) {
	
	sessionService.register = function(user){
		return $http({
			method : 'POST',
			url: '../users/register',
			data: {
				"username": user.username,
				"password": user.password,
				"name": user.name,
				"lastName": user.lastName,
				"email": user.email,
				"phone": user.phone,
				"country": user.country,
				"address": user.address
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
	
	
	return sessionService;
	
});

app.factory('ShopService', function shopService($http) {
	
	shopService.getShops = function(){
		return $http({
			method : 'GET',
			url: '../webProject/webapi/shops',
		});
	}
	
	shopService.searchProducts = function(shopSearch,searchType,searchParameter){
		return $http({
			method : 'POST',
			url: '../webProject/webapi/shops/search/' + searchType + '/' + searchParameter,
			data: {
				"name": shopSearch.name,
				"country": shopSearch.country,
				"grade": shopSearch.grade,
			}
		});
	}
	
	shopService.addShop = function(shop){
		return $http({
			method : 'POST',
			url: '../webProject/webapi/shops',
			data: {
				"code": shop.code,
				"name": shop.name,
				"address": shop.address,
				"country": shop.country,
				"phone": shop.phone,
				"responsibleSeller": shop.seller,
				"email": shop.email,
				"grade": shop.grade
			}
		});
	}
	
	shopService.deleteShop = function(shop){
		return $http({
			method : 'DELETE',
			url: '../webProject/webapi/shops',
			data: {
				"code": shop.code,
				"name": shop.name,
				"address": shop.address,
				"country": shop.country,
				"phone": shop.phone,
				"responsibleSeller": shop.seller,
				"email": shop.email,
				"grade": shop.grade
			},
			headers: {
				   'Content-Type': 'application/json',
				   'Accept': 'application/json'
			}
		});
	}
	
	
	shopService.updateShop = function(shop){
		return $http({
			method : 'PUT',
			url: '../webProject/webapi/shops',
			data: {
				"code": shop.code,
				"name": shop.name,
				"address": shop.address,
				"country": shop.country,
				"phone": shop.phone,
				"responsibleSeller": shop.seller,
				"email": shop.email,
				"grade": shop.grade
			},
		});
	}
	
	return shopService;
});

app.factory('ProductService', function productService($http) {
	
	productService.getProducts = function(){
		return $http({
			method : 'GET',
			url: '../webProject/webapi/products',
		});
	}
	
	productService.addReview = function(newReview){
		return $http({
			method : 'POST',
			url: '../webProject/webapi/products/addReview',
			data: {
				"code": newReview.code,
				"username": newReview.username,
				"date": newReview.date,
				"grade": newReview.grade,
				"comment": newReview.comment,
			}
		});
	}
	
	productService.updateReview = function(editReview){
		return $http({
			method : 'POST',
			url: '../webProject/webapi/products/updateReview',
			data: {
				"code": editReview.code,
				"username": editReview.username,
				"date": editReview.date,
				"grade": editReview.grade,
				"comment": editReview.comment,
			}
		});
	}
	
	productService.deleteReview = function(review){
		return $http({
			method : 'DELETE',
			url: '../webProject/webapi/products/deleteReview',
			data: {
				"code": review.code,
				"username": review.username,
				"date": review.date,
				"grade": review.grade,
				"comment": review.comment,
			},
			headers: {
				   'Content-Type': 'application/json',
				   'Accept': 'application/json'
			}
		});
	}
	
	productService.searchProducts = function(productSearch,searchType,searchParameter){
		return $http({
			method : 'POST',
			url: '../webProject/webapi/products/search/' + searchType + '/' + searchParameter,
			data: {
				"name": productSearch.name,
				"minPrice": productSearch.range.min,
				"maxPrice": productSearch.range.max,
				"description": productSearch.description,
				"category": productSearch.category,
				"productionCountry": productSearch.country,
				"color": productSearch.color,
				"grade": productSearch.grade,
				"reviewQuantity": productSearch.reviewCount,
				"shopQuantitiy": productSearch.quantity,
			}
		});
	}
	
	productService.getProductsForShop = function(code){
		return $http({
			method : 'GET',
			url: '../webProject/webapi/products/getProductsForShop/' + code,
		});
	}
	
	productService.addProduct = function(product){
		return $http({
			method : 'POST',
			url: '../webProject/webapi/products',
			data: {
				"code": product.code,
				"name": product.name,
				"color": product.color,
				"weight": product.weight,
				"productionCountry": product.country,
				"productCategory": product.category,
				"manufacturer": product.manufacturer,
				"grade": product.grade,
				"price": product.price,
				"shop": product.shop
			}
		});
	}
	
	productService.deleteProduct = function(product){
		return $http({
			method : 'DELETE',
			url: '../webProject/webapi/products',
			data: {
				"code": product.code,
				"name": product.name,
				"color": product.color,
				"weight": product.weight,
				"productionCountry": product.country,
				"manufacturer": product.manufacturer,
				"grade": product.grade,
				"price": product.price,
				"shop": product.shop
			},
			headers: {
				   'Content-Type': 'application/json',
				   'Accept': 'application/json'
			}
		});
	}
	
	
	productService.updateProduct = function(product){
		return $http({
			method : 'PUT',
			url: '../webProject/webapi/products',
			data: {
				"code": product.code,
				"name": product.name,
				"color": product.color,
				"weight": product.weight,
				"productionCountry": product.country,
				"productCategory": product.category,
				"manufacturer": product.manufacturer,
				"grade": product.grade,
				"price": product.price,
				"shop": product.shop,
				"reviews": product.reviews
			}
		});
	}
	
	return productService;
});

app.factory('CategoryService', function categoryService($http) {
	
	categoryService.getCategories = function(){
		return $http({
			method : 'GET',
			url: '../webProject/webapi/categories',
		});
	}
	
	categoryService.addCategory = function(category){
		return $http({
			method : 'POST',
			url: '../webProject/webapi/categories',
			data: {
				"name": category.name,
				"description": category.description,
				"subcategory": category.subcategory
			}
		});
	}
	
	categoryService.deleteCategory = function(category){
		return $http({
			method : 'DELETE',
			url: '../webProject/webapi/categories',
			data: {
				"name": category.name,
				"description": category.description,
				"subcategory": category.subcategory
			},
			headers: {
				   'Content-Type': 'application/json',
				   'Accept': 'application/json'
			}
		});
	}
	
	
	categoryService.updateCategory = function(category){
		return $http({
			method : 'PUT',
			url: '../webProject/webapi/categories',
			data: {
				"name": category.name,
				"description": category.description,
				"subcategory": category.subcategory
			}
		});
	}
	
	return categoryService;
});

app.factory('DelivererService', function delivererService($http) {
	
	delivererService.getDeliverers = function(){
		return $http({
			method : 'GET',
			url: '../webProject/webapi/deliverers',
		});
	}

	
	delivererService.addDeliverer = function(deliverer){
		return $http({
			method : 'POST',
			url: '../webProject/webapi/deliverers',
			data: {
				"code": deliverer.code,
				"name": deliverer.name,
				"description": deliverer.description,
				"workCountries": deliverer.countries,
				"price": deliverer.price
			}
		});
	}
	
	delivererService.deleteDeliverer = function(deliverer){
		return $http({
			method : 'DELETE',
			url: '../webProject/webapi/deliverers',
			data: {
				"code": deliverer.code,
				"name": deliverer.name,
				"description": deliverer.description,
				"workCountries": deliverer.workCountries,
				"price": deliverer.price
			},
			headers: {
				   'Content-Type': 'application/json',
				   'Accept': 'application/json'
			}
		});
	}
	
	
	delivererService.updateDeliverer = function(deliverer){
		return $http({
			method : 'PUT',
			url: '../webProject/webapi/deliverers',
			data: {
				"code": deliverer.code,
				"name": deliverer.name,
				"description": deliverer.description,
				"workCountries": deliverer.countries,
				"price": deliverer.price
			}
		});
	}
	
	return delivererService;
});

app.factory('ShoppingCartService', function shoppingCartService($http){
	
	shoppingCartService.addItem = function(product){
		return $http({
			method: 'POST',
			url: '../webProject/webapi/shoppingCart/addItem',
			data: {
				"code": product.code,
				"name": product.name,
				"color": product.color,
				"weight": product.weight,
				"productionCountry": product.productionCountry,
				"productCategory": product.productCategory,
				"manufacturer": product.manufacturer,
				"grade": product.grade,
				"price": product.price,
				"shop": product.shop
			}
		});
	}
	
	shoppingCartService.getItems = function(){
		return $http({
			method: 'GET',
			url: '../webProject/webapi/shoppingCart/getItems',
		});
	}
	
	shoppingCartService.removeItem = function(product){
		return $http({
			method: 'DELETE',
			url: '../webProject/webapi/shoppingCart/deleteItem',
			data: {
				"code": product.code,
				"name": product.name,
				"color": product.color,
				"weight": product.weight,
				"productionCountry": product.productionCountry,
				"productCategory": product.productCategory,
				"manufacturer": product.manufacturer,
				"grade": product.grade,
				"price": product.price,
				"shop": product.shop
			},
			headers: {
				   'Content-Type': 'application/json',
				   'Accept': 'application/json'
			}
		});
	}
	
	shoppingCartService.clearShoppingCart = function(product){
		return $http({
			method: 'DELETE',
			url: '../webProject/webapi/shoppingCart/deleteItems',
		});
	}
	
	return shoppingCartService;
});

app.factory('ActionService', function actionService($http){
	
	actionService.addAction = function(action){
		return $http({
			method: 'POST',
			url: '../webProject/webapi/actions/'
				+ action.duration + '/'
				+ action.productCode 
				+ '/' + action.actionPercent,

		});
	}
	
	actionService.getActions = function(){
		return $http({
			method: 'GET',
			url: '../webProject/webapi/actions',
		});
	}
	
	actionService.getActionForProduct = function(productCode){
		return $http({
			method: 'GET',
			url: '../webProject/webapi/actions/getActionForProduct/' + productCode,
		});
	}
	
	actionService.deleteAction = function(action){
		return $http({
			method: 'DELETE',
			url: '../webProject/webapi/actions/' ,
			data: {
				"productCode": action.productCode,
				"startDate": action.startDate,
				"endDate": action.endDate,
				"actionPercent": action.actionPercent,
				"duration": action.duration,
			},
			headers: {
				   'Content-Type': 'application/json',
				   'Accept': 'application/json'
			}
		});
	}
	
	
	return actionService;
});