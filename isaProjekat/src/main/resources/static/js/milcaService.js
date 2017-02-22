var app = angular.module('webApp');

app.factory('SystemManagerService', function systemManagerService($http) {

	systemManagerService.registerRestaurant = function(restaurant) {
		return $http({
			method : 'POST',
			url : '../systemManager/registerRestaurant',
			data : {
				"restaurantName" : restaurant.restaurantName,
				"description" : restaurant.description
			}
		});
	}

	systemManagerService.editRestaurant = function(restaurant) {
		return $http({
			method : 'PUT',
			url : '../systemManager/updateRestaurant',
			data : {
				"id" : restaurant.id,
				"restaurantName" : restaurant.restaurantName,
				"description" : restaurant.description
			}
		});
	}

	systemManagerService.registerRestaurantManager = function(restaurant_id,
			restaurantManager) {
		return $http({
			method : 'POST',
			url : '../systemManager/registerRestaurantManager?id='
					+ restaurant_id,
			data : {
				"userName" : restaurantManager.userName,
				"surname" : restaurantManager.surname,
				"email" : restaurantManager.email,
				"password" : restaurantManager.password,
				"dateOfBirth" : restaurantManager.dateOfBirth,
				"userRole" : "RESTAURANT_MANAGER"
			}
		});
	}

	systemManagerService.registerSystemManager = function(systemManager) {
		return $http({
			method : 'POST',
			url : '../systemManager/registerSystem',
			data : {
				"userName" : systemManager.userName,
				"surname" : systemManager.surname,
				"email" : systemManager.email,
				"password" : systemManager.password,
				"dateOfBirth" : systemManager.dateOfBirth,
				"userRole" : "SYSTEM_MANAGER"
			}
		});
	}

	systemManagerService.deleteRestaurant = function(restaurant_id) {
		return $http({
			method : 'DELETE',
			url : '../systemManager/deleteRestaurant?id=' + restaurant_id
		});
	}

	systemManagerService.deleteRestaurantManager = function(
			restaurant_manager_id) {
		return $http({
			method : 'DELETE',
			url : '../systemManager/deleteRestaurantManager?id='
					+ restaurant_manager_id
		});
	}

	systemManagerService.deleteSystemManager = function(system_manager_id) {
		return $http({
			method : 'DELETE',
			url : '../systemManager/deleteSystemManager?id='
					+ system_manager_id
		});
	}

	systemManagerService.getRestaurantManagers = function(id) {
		return $http({
			method : 'GET',
			url : '../systemManager/getRestaurantManagersForRestaurant?id='
					+ id
		});
	}

	systemManagerService.getRestaurants = function() {
		return $http({
			method : 'GET',
			url : '../systemManager/getAllRestaurants'
		});
	}

	systemManagerService.getSystemManagers = function() {
		return $http({
			method : 'GET',
			url : '../systemManager/getAllSystemManager'
		});
	}
	systemManagerService.editSystemManager = function(manager) {
		return $http({
			method : 'PUT',
			url : '../systemManager/updateSystem',
			data : {
				"id" : manager.id,
				"userName" : manager.userName,
				"surname" : manager.surname,
				"email" : manager.email,
				"password" : manager.password,
				"dateOfBirth" : manager.dateOfBirth
			}
		});
	}

	return systemManagerService;

});

app.factory('BidderService', function bidderService($http) {

	bidderService.registerBidderOffer = function(bidderOffer, bidder_id,
			request_offer_id) {
		return $http({
			method : 'POST',
			url : '../bidders/registerBid?request_offer_id=' + request_offer_id
					+ '&bidder_id=' + bidder_id,
			data : {
				"price" : bidderOffer.price,
				"garanty" : bidderOffer.garanty,
				"dateOfDelivery" : bidderOffer.dateOfDelivery
			}
		});
	}

	bidderService.editBidderOffer = function(bidderOffer) {
		return $http({
			method : 'PUT',
			url : '../bidders/updateBid',
			data : {
				"id" : bidderOffer.id,
				"price" : bidderOffer.price,
				"garanty" : bidderOffer.garanty,
				"dateOfDelivery" : bidderOffer.dateOfDelivery
			}
		});
	}

	bidderService.getActiveOffers = function() {
		return $http({
			method : 'GET',
			url : '../bidders/getActiveRequestOffers'
		});
	}

	bidderService.getProducts = function(request_id) {
		return $http({
			method : 'GET',
			url : '../restaurantManagers/getAllProductsForRequestOffer?id='
					+ request_id
		});
	}

	bidderService.getBidderOffersForBidder = function(bidder_id) {
		return $http({
			method : 'GET',
			url : '../bidders/getBiddingsForBidder?id=' + bidder_id
		});
	}

	bidderService.getRequestOffer = function(id) {
		return $http({
			method : 'GET',
			url : '../bidders/getRequestOffer?id=' + id
		});
	}

	bidderService.getBidderOfferForBidderAndRequest = function(request_id,
			bidder_id) {
		return $http({
			method : 'GET',
			url : '../bidders/getBidderOfferForBidderAndRequestOffer?b_id='
					+ bidder_id + '&ro_id=' + request_id
		});
	}

	bidderService.deleteBidderOffer = function(id) {
		return $http({
			method : 'DELETE',
			url : '../bidders/deleteBid?id=' + id
		});
	}

	bidderService.editBidder = function(bidder) {
		return $http({
			method : 'PUT',
			url : '../bidders/updateBidder',
			data : {
				"id" : bidder.id,
				"userName" : bidder.userName,
				"surname" : bidder.surname,
				"email" : bidder.email,
				"password" : bidder.password,
				"dateOfBirth" : bidder.dateOfBirth
			}
		});
	}

	return bidderService;
});

app
		.factory(
				'RestaurantManagerService',
				function restaurantManagerService($http) {

					restaurantManagerService.checkIfRequestOfferExpired = function() {
						return $http({
							method : 'PUT',
							url : '../restaurantManagers/checkIfRequestOfferExpired'
						});
					}

					restaurantManagerService.seeIfCanDeleteSegment = function(
							id) {
						return $http({
							method : 'GET',
							url : '../restaurantManagers/checkIfSegmentCanBeDeleted?id='
									+ id
						});
					}

					restaurantManagerService.checkIfWorkScheduleIsDone = function() {
						return $http({
							method : 'PUT',
							url : '../restaurantManagers/checkIfWorkScheduleIsDone'
						});
					}

					restaurantManagerService.getRestaurant = function(id) {
						return $http({
							method : 'GET',
							url : '../restaurantManagers/getRestaurantForManager?id='
									+ id
						});
					}

					restaurantManagerService.getRestaurantWorkers = function(id) {
						return $http({
							method : 'GET',
							url : '../restaurantManagers/getAllWorkersForRestaurant?id='
									+ id
						});
					}

					restaurantManagerService.getRestaurantSegments = function(
							id) {
						return $http({
							method : 'GET',
							url : '../restaurantManagers/getAllSegmentsForRestaurant?id='
									+ id
						});
					}

					restaurantManagerService.getTables = function(id) {
						return $http({
							method : 'GET',
							url : '../restaurantManagers/getAllTablesForSegment?id='
									+ id
						});
					}

					restaurantManagerService.getProductsForRestaurant = function(
							id) {
						return $http({
							method : 'GET',
							url : '../restaurantManagers/getAllProductsForRestaurant?id='
									+ id
						});
					}

					restaurantManagerService.getAllProducts = function() {
						return $http({
							method : 'GET',
							url : '../restaurantManagers/getAllProducts'
						});
					}

					restaurantManagerService.registerCook = function(cook, id) {
						return $http({
							method : 'POST',
							url : '../restaurantManagers/registerCook?id=' + id,
							data : {
								"userName" : cook.userName,
								"surname" : cook.surname,
								"email" : cook.email,
								"password" : cook.password,
								"dateOfBirth" : cook.dateOfBirth,
								"shirtSize" : cook.shirtSize,
								"shoeNumber" : cook.shoeNumber,
								"cookType" : cook.cookType,
								"userRole" : "COOK"
							}
						});
					}

					restaurantManagerService.registerBartender = function(
							bartender, id) {
						return $http({
							method : 'POST',
							url : '../restaurantManagers/registerBartender?id='
									+ id,
							data : {
								"userName" : bartender.userName,
								"surname" : bartender.surname,
								"email" : bartender.email,
								"password" : cook.password,
								"dateOfBirth" : bartender.dateOfBirth,
								"shirtSize" : bartender.shirtSize,
								"shoeNumber" : bartender.shoeNumber,
								"userRole" : "BARTENDER"
							}
						});
					}

					restaurantManagerService.registerWaiter = function(waiter,
							id) {
						return $http({
							method : 'POST',
							url : '../restaurantManagers/registerWaiter?id='
									+ id,
							data : {
								"userName" : waiter.userName,
								"surname" : waiter.surname,
								"email" : waiter.email,
								"password" : waiter.password,
								"dateOfBirth" : waiter.dateOfBirth,
								"shirtSize" : waiter.shirtSize,
								"shoeNumber" : waiter.shoeNumber,
								"cookType" : waiter.cookType,
								"userRole" : "WAITER"
							}
						});
					}
					restaurantManagerService.deleteWorker = function(id) {
						return $http({
							method : 'DELETE',
							url : '../restaurantManagers/removeWorker?id=' + id
						});
					}

					restaurantManagerService.showWorkerShifts = function(id) {
						return $http({
							method : 'GET',
							url : '../restaurantManagers/getAllWorkSchedulesForWorker?id='
									+ id
						});
					}
					restaurantManagerService.editRestaurant = function(
							restaurant) {
						return $http({
							method : 'PUT',
							url : '../restaurantManagers/updateRestaurant',
							data : {
								"id" : restaurant.id,
								"restaurantName" : restaurant.restaurantName,
								"description" : restaurant.description
							}
						});
					}

					restaurantManagerService.registerProduct = function(
							product, id) {
						return $http({
							method : 'POST',
							url : '../restaurantManagers/addProduct?id=' + id,
							data : {
								"productName" : product.productName,
								"description" : product.description,
								"productType" : product.productType
							}
						});
					}

					restaurantManagerService.addProduct = function(product_id,
							id) {
						return $http({
							method : 'PUT',
							url : '../restaurantManagers/addExistingProduct?product_id='
									+ product_id + '&rest_id=' + id
						});
					}

					restaurantManagerService.registerTable = function(table, id) {
						return $http({
							method : 'POST',
							url : '../restaurantManagers/addRestaurantTable?id='
									+ id,
							data : {
								"numberOfChairs" : table.numberOfChairs
							}
						});
					}
					restaurantManagerService.editTable = function(table, id) {
						return $http({
							method : 'PUT',
							url : '../restaurantManagers/updateRestaurantTable?id='
									+ id,
							data : {
								"id" : table.id,
								"numberOfChairs" : table.numberOfChairs
							}
						});
					}

					restaurantManagerService.deleteProduct = function(id,
							pro_id) {
						return $http({
							method : 'DELETE',
							url : '../restaurantManagers/removeProduct?product_id='
									+ pro_id + '&rest_id=' + id
						});
					}

					restaurantManagerService.deleteSegment = function(id) {
						return $http({
							method : 'DELETE',
							url : '../restaurantManagers/removeSegment?id='
									+ id
						});
					}

					restaurantManagerService.deleteTable = function(id) {
						return $http({
							method : 'DELETE',
							url : '../restaurantManagers/removeRestaurantTable?id='
									+ id
						});
					}

					restaurantManagerService.registerSegment = function(
							segment, id) {
						return $http({
							method : 'POST',
							url : '../restaurantManagers/addSegment?rest=' + id,
							data : {
								"position" : segment.position,
								"smokingAllowed" : segment.smokingAllowed
							}
						});
					}
					restaurantManagerService.editSegment = function(segment) {
						return $http({
							method : 'PUT',
							url : '../restaurantManagers/updateSegment',
							data : {
								"id" : segment.id,
								"position" : segment.position,
								"smokingAllowed" : segment.smokingAllowed
							}
						});
					}

					restaurantManagerService.registerWorkSchedule = function(
							workSchedule, w_id, s_id) {
						return $http({
							method : 'POST',
							url : '../restaurantManagers/registerWorkSchedule?segment_id='
									+ s_id + '&worker_id=' + w_id,
							data : {
								"date" : workSchedule.date,
								"startTime" : workSchedule.startTime,
								"endTime" : workSchedule.endTime,
								"twoDays" : workSchedule.twoDays
							}
						});
					}
					restaurantManagerService.deleteShift = function(id) {
						return $http({
							method : 'DELETE',
							url : '../restaurantManagers/removeWorkSchedule?id='
									+ id
						});
					}
					restaurantManagerService.editWorkScheduleInfo = function(
							workSchedule) {
						return $http({
							method : 'POST',
							url : '../restaurantManagers/updateWorkSchedule',
							data : {
								"id" : workSchedule.id,
								"date" : workSchedule.date,
								"startTime" : workSchedule.startTime,
								"endTime" : workSchedule.endTime,
								"twoDays" : workSchedule.twoDays
							}
						});
					}
					restaurantManagerService.getReplacments = function(id) {
						return $http({
							method : 'GET',
							url : '../restaurantManagers/getPossableReplacements?id='
									+ id
						});
					}
					restaurantManagerService.getRequestOffers = function(id) {
						return $http({
							method : 'GET',
							url : '../restaurantManagers//getAllRequestOffersForManager?id='
									+ id
						});
					}
					restaurantManagerService.setReplacment = function(id, r_id) {
						return $http({
							method : 'POST',
							url : '../restaurantManagers/gupdateWorkScheduleSetReplacement?repl_id='
									+ r_id + '&ws_id=' + id
						});
					}
					
					restaurantManagerService.getWorkSchedulesForRestaurant = function(id) {
						return $http({
							method : 'GET',
							url : '../restaurantManagers/getAllWorkSchedulesForRestaurant?id='
									+ id
						});
					}
					
					restaurantManagerService.registerRequestOffer = function(
							rq, id) {
						return $http({
							method : 'POST',
							url : '../restaurantManagers/registerRequestOffer?rm_id=' + id,
							data : {
								"startDate" : rq.startDate,
								"expirationDate" : rq.expirationDate
							}
						});
					}
					
					restaurantManagerService.editRequestOffer = function(
							rq) {
						return $http({
							method : 'PUT',
							url : '../restaurantManagers/updateRequestOffer',
							data : {
								"id" : rq.id,
								"startDate" : rq.startDate,
								"expirationDate" : rq.expirationDate
							}
						});
					}
					restaurantManagerService.addProdusttoRequestOffer = function(
							ro_id, p_id) {
						return $http({
							method : 'POST',
							url : '../restaurantManagers//addProductToRequestOffer?product_id=' + p_id + '&rest_id=' + ro_id
						});
					}
					restaurantManagerService.removeProdustFromRequestOffer = function(
							ro_id, p_id) {
						return $http({
							method : 'POST',
							url : '../restaurantManagers/removeProductFromRequestOffer?product_id=' + p_id + '&ro_id=' + ro_id
						});
					}
					restaurantManagerService.deleteRequestOffer = function(id) {
						return $http({
							method : 'DELETE',
							url : '../restaurantManagers/removeRequestOffer?id='
									+ id
						});
					}
					
					restaurantManagerService.getProducts = function(request_id) {
						return $http({
							method : 'GET',
							url : '../restaurantManagers/getAllProductsForRequestOffer?id='
									+ request_id
						});
					}

					
					return restaurantManagerService;

				});
