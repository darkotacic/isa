var app = angular.module('webApp');

app.factory('SystemManagerService', function systemManagerService($http) {

	systemManagerService.getRestaurantManagers = function(id) {
		return $http({
			method : 'GET',
			url : '../systemmanagers/getRestaurantManagersForRestaurant?id='
					+ id
		});
	}

	systemManagerService.getRestaurants = function() {
		return $http({
			method : 'GET',
			url : '../systemmanagers/getAllRestaurants'
		});
	}

	systemManagerService.getSystemManagers = function() {
		return $http({
			method : 'GET',
			url : '../systemmanagers/getAllSystemManager'
		});
	}
	
	systemManagerService.registerRestaurant = function(restaurant) {
		return $http({
			method : 'POST',
			url : '../systemmanagers/registerRestaurant',
			data : {
				"restaurantName" : restaurant.restaurantName,
				"description" : restaurant.description
			}
		});
	}

	systemManagerService.editRestaurant = function(restaurant) {
		return $http({
			method : 'PUT',
			url : '../systemmanagers/updateRestaurant',
			data : {
				"id" : restaurant.id,
				"restaurantName" : restaurant.restaurantName,
				"description" : restaurant.description
			}
		});
	}
	systemManagerService.deleteRestaurant = function(restaurant_id) {
		return $http({
			method : 'DELETE',
			url : '../systemmanagers/deleteRestaurant?id=' + restaurant_id
		});
	}

	systemManagerService.registerRestaurantManager = function(restaurant_id,
			restaurantManager) {
		return $http({
			method : 'POST',
			url : '../systemmanagers/registerRestaurantManager?id='
					+ restaurant_id,
			data : {
				"userName" : restaurantManager.userName,
				"surname" : restaurantManager.surname,
				"email" : restaurantManager.email,
				"password" : restaurantManager.password,
				"dateOfBirth" : restaurantManager.dateOfBirth,
				"userRole" : "RESTAURANTMANAGER"
			}
		});
	}
	
	systemManagerService.deleteRestaurantManager = function(
			restaurant_manager_id) {
		return $http({
			method : 'DELETE',
			url : '../systemmanagers/deleteRestaurantManager?id='
					+ restaurant_manager_id
		});
	}


	systemManagerService.registerSystemManager = function(systemManager) {
		return $http({
			method : 'POST',
			url : '../systemmanagers/registerSystem',
			data : {
				"userName" : systemManager.userName,
				"surname" : systemManager.surname,
				"email" : systemManager.email,
				"password" : systemManager.password,
				"dateOfBirth" : systemManager.dateOfBirth,
				"userRole" : "SYSTEMMANAGER"
			}
		});
	}

	systemManagerService.deleteSystemManager = function(system_manager_id) {
		return $http({
			method : 'DELETE',
			url : '../systemmanagers/deleteSystemManager?id='
					+ system_manager_id
		});
	}

	return systemManagerService;

});

app.factory('BidderService', function bidderService($http) {

	bidderService.getActiveOffers = function() {
		return $http({
			method : 'GET',
			url : '../bidders/getActiveRequestOffers'
		});
	}

	bidderService.getProducts = function(request_id) {
		return $http({
			method : 'GET',
			url : '../restaurantmanagers/getAllProductsForRequestOffer?id='
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

	bidderService.deleteBidderOffer = function(id) {
		return $http({
			method : 'DELETE',
			url : '../bidders/deleteBid?id=' + id
		});
	}

	bidderService.editBidder = function(bidder) {
		return $http({
			method : 'PUT',
			url : '../bidders/update',
			data : {
				"id" : bidder.id,
				"userName" : bidder.userName,
				"surname" : bidder.surname,
				"email" : bidder.email,
				"password" : bidder.password,
				"dateOfBirth" : bidder.dateOfBirth,
				"firstLogIn" : bidder.firstLogIn
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
							url : '../restaurantmanagers/checkIfRequestOfferExpired'
						});
					}
					
					restaurantManagerService.checkIfWorkScheduleIsDone = function() {
						return $http({
							method : 'PUT',
							url : '../restaurantmanagers/checkIfWorkScheduleIsDone'
						});
					}


					restaurantManagerService.seeIfCanDeleteSegment = function(
							id) {
						return $http({
							method : 'GET',
							url : '../restaurantmanagers/checkIfSegmentCanBeDeleted?id='
									+ id
						});
					}
					restaurantManagerService.restaurantGrade = function(
							id) {
						return $http({
							method : 'GET',
							url : '../restaurantmanagers/getGradeForRestaurant?id='
									+ id
						});
					}
					
					restaurantManagerService.getGradeOfWaiter = function(
							id) {
						return $http({
							method : 'GET',
							url : '../restaurantmanagers/getGradeForWaiter?id='
									+ id
						});
					}
					
					restaurantManagerService.getProductGrade = function(
							id, pro_id) {
						return $http({
							method : 'GET',
							url : '../restaurantmanagers/getGradeForOrder?id='
									+ pro_id + '&res_id=' + id
						});
					}
					
					restaurantManagerService.getEarningForWaiter = function(
							id) {
						return $http({
							method : 'GET',
							url : '../restaurantmanagers/getWaiterEarnings?id='
									+ id
						});
					}
					
					restaurantManagerService.getRestaurantEarnings = function(
							id, start, end) {
						return $http({
							method : 'GET',
							url : '../restaurantmanagers/getRestaurantEarnings?id='
									+ id + '&start=' + start + '&end=' + end
						});
					}

					restaurantManagerService.getRestaurant = function(id) {
						return $http({
							method : 'GET',
							url : '../restaurantmanagers/getRestaurantForManager?id='
									+ id
						});
					}

					restaurantManagerService.getRestaurantWorkers = function(id) {
						return $http({
							method : 'GET',
							url : '../restaurantmanagers/getAllWorkersForRestaurant?id='
									+ id
						});
					}

					restaurantManagerService.getRestaurantSegments = function(
							id) {
						return $http({
							method : 'GET',
							url : '../restaurantmanagers/getAllSegmentsForRestaurant?id='
									+ id
						});
					}

					restaurantManagerService.getTables = function(id) {
						return $http({
							method : 'GET',
							url : '../restaurantmanagers/getAllTablesForSegment?id='
									+ id
						});
					}

					restaurantManagerService.getProductsForRestaurant = function(
							id) {
						return $http({
							method : 'GET',
							url : '../restaurantmanagers/getAllProductsForRestaurant?id='
									+ id
						});
					}

					restaurantManagerService.getAllProducts = function() {
						return $http({
							method : 'GET',
							url : '../restaurantmanagers/getAllProducts'
						});
					}

					restaurantManagerService.registerCook = function(cook, id) {
						return $http({
							method : 'POST',
							url : '../restaurantmanagers/registerCook?id=' + id,
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
							url : '../restaurantmanagers/registerBartender?id='
									+ id,
							data : {
								"userName" : bartender.userName,
								"surname" : bartender.surname,
								"email" : bartender.email,
								"password" : bartender.password,
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
							url : '../restaurantmanagers/registerWaiter?id='
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
					restaurantManagerService.registerBidder = function(b) {
						return $http({
							method : 'POST',
							url : '../restaurantmanagers/registerBidder',
							data : {
								"userName" : b.userName,
								"surname" : b.surname,
								"email" : b.email,
								"password" : b.password,
								"dateOfBirth" : b.dateOfBirth,
								"userRole" : "BIDDER"
							}
						});
					}
					restaurantManagerService.deleteWorker = function(id) {
						return $http({
							method : 'DELETE',
							url : '../restaurantmanagers/removeWorker?id=' + id
						});
					}

					restaurantManagerService.showWorkerShifts = function(id) {
						return $http({
							method : 'GET',
							url : '../restaurantmanagers/getAllWorkSchedulesForWorker?id='
									+ id
						});
					}
					restaurantManagerService.editRestaurant = function(
							restaurant) {
						return $http({
							method : 'PUT',
							url : '../restaurantmanagers/updateRestaurant',
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
							url : '../restaurantmanagers/addProduct?rest_id=' + id,
							data : {
								"productName" : product.productName,
								"description" : product.description,
								"price" : product.price, 
								"productType" : product.productType
							}
						});
					}

					restaurantManagerService.addProduct = function(product_id,
							id) {
						return $http({
							method : 'PUT',
							url : '../restaurantmanagers/addExistingProduct?product_id='
									+ product_id + '&rest_id=' + id
						});
					}

					restaurantManagerService.registerTable = function(table, id) {
						return $http({
							method : 'POST',
							url : '../restaurantmanagers/addRestaurantTable?segment='
									+ id,
							data : {
								"numberOfChairs" : table.numberOfChairs
							}
						});
					}
					restaurantManagerService.editTable = function(table, id) {
						return $http({
							method : 'PUT',
							url : '../restaurantmanagers/updateRestaurantTable?id='
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
							url : '../restaurantmanagers/removeProduct?product_id='
									+ pro_id + '&rest_id=' + id
						});
					}

					restaurantManagerService.deleteSegment = function(id) {
						return $http({
							method : 'DELETE',
							url : '../restaurantmanagers/removeSegment?id='
									+ id
						});
					}

					restaurantManagerService.deleteTable = function(id) {
						return $http({
							method : 'DELETE',
							url : '../restaurantmanagers/removeRestaurantTable?id='
									+ id
						});
					}

					restaurantManagerService.registerSegment = function(
							segment, id) {
						return $http({
							method : 'POST',
							url : '../restaurantmanagers/addSegment?rest=' + id,
							data : {
								"position" : segment.position,
								"smokingAllowed" : segment.smokingAllowed
							}
						});
					}
					restaurantManagerService.editSegment = function(segment) {
						return $http({
							method : 'PUT',
							url : '../restaurantmanagers/updateSegment',
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
							url : '../restaurantmanagers/registerWorkSchedule?segment_id='
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
							url : '../restaurantmanagers/removeWorkSchedule?id='
									+ id
						});
					}
					restaurantManagerService.editWorkScheduleInfo = function(
							workSchedule) {
						return $http({
							method : 'PUT',
							url : '../restaurantmanagers/updateWorkSchedule',
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
							url : '../restaurantmanagers/getPossableReplacements?id='
									+ id
						});
					}
					restaurantManagerService.getRequestOffers = function(id) {
						return $http({
							method : 'GET',
							url : '../restaurantmanagers//getAllRequestOffersForManager?id='
									+ id
						});
					}
					restaurantManagerService.setReplacment = function(id, r_id) {
						return $http({
							method : 'POST',
							url : '../restaurantmanagers/gupdateWorkScheduleSetReplacement?repl_id='
									+ r_id + '&ws_id=' + id
						});
					}
					
					restaurantManagerService.getWorkSchedulesForRestaurant = function(id) {
						return $http({
							method : 'GET',
							url : '../restaurantmanagers/getAllWorkSchedulesForRestaurant?id='
									+ id
						});
					}
					
					restaurantManagerService.registerRequestOffer = function(
							rq, id) {
						return $http({
							method : 'POST',
							url : '../restaurantmanagers/registerRequestOffer?rm_id=' + id,
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
							url : '../restaurantmanagers/updateRequestOffer',
							data : {
								"id" : rq.id,
								"startDate" : rq.startDate,
								"expirationDate" : rq.expirationDate
							}
						});
					}
					restaurantManagerService.addProductToRequestOffer = function(
							ro_id, p_id) {
						return $http({
							method : 'PUT',
							url : '../restaurantmanagers/addProductToRequestOffer?product_id=' + p_id + '&rest_id=' + ro_id
						});
					}
					
					restaurantManagerService.acceptBid = function(
							ro_id, bid_id) {
						return $http({
							method : 'PUT',
							url : '../restaurantmanagers/acceptBidderOffer?bid_id=' + bid_id + '&req_id=' + ro_id
						});
					}
					restaurantManagerService.removeProdustFromRequestOffer = function(
							ro_id, p_id) {
						return $http({
							method : 'POST',
							url : '../restaurantmanagers/removeProductFromRequestOffer?product_id=' + p_id + '&ro_id=' + ro_id
						});
					}
					restaurantManagerService.deleteRequestOffer = function(id) {
						return $http({
							method : 'DELETE',
							url : '../restaurantmanagers/removeRequestOffer?id='
									+ id
						});
					}
					
					restaurantManagerService.getProducts = function(request_id) {
						return $http({
							method : 'GET',
							url : '../restaurantmanagers/getAllProductsForRequestOffer?id='
									+ request_id
						});
					}
					
					restaurantManagerService.getBidderOffersForRequest = function(request_id) {
						return $http({
							method : 'GET',
							url : '../restaurantmanagers/getAllBidderOffersForRequestOffer?id='
									+ request_id
						});
					}

					restaurantManagerService.getAllWaitersByNameAndRestaurant = function(id, name) {
						return $http({
							method : 'GET',
							url : '../restaurantmanagers/getAllWaitersByNameAndRestaurant?id='
									+ id + '&name=' + name
						});
					}
					
					restaurantManagerService.getAllProductsByNameAndRestaurant = function(id, name) {
						return $http({
							method : 'GET',
							url : '../restaurantmanagers/getAllProductsByNameAndRestaurant?id='
									+ id + '&name=' + name
						});
					}
					
					return restaurantManagerService;

				});
