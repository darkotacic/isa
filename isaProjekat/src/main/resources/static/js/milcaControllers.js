var app = angular.module('webApp');

app.run([
         'ngNotify',
         function(ngNotify) {

             ngNotify.config({
            	 theme: 'pure',
            	    position: 'bottom',
            	    duration: 1000,
            	    type: 'info',
            	    sticky: false,
             });
         }
     ]);

app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}]);

app.directive('ngConfirmClick', [
        function(){
            return {
                link: function (scope, element, attr) {
                    var msg = attr.ngConfirmClick || "Are you sure?";
                    var clickAction = attr.confirmedClick;
                    element.bind('click',function (event) {
                        if ( window.confirm(msg) ) {
                            scope.$eval(clickAction)
                        }
                    });
                }
            };
    }]);
    
app
		.controller(
				'systemManagerController',
				[
						'$rootScope',
						'$scope',
						'$location','ngNotify',
						'SystemManagerService',
						function($rootScope, $scope, $location,
								ngNotify, systemManagerService) {

							$scope.display = function(tab) {
								if(tab == 1)
									$scope.selected = null;
								$scope.show = tab;
								$scope.selectedRestaurantManager = null;
								$scope.restaurant = null;
								$scope.newRestaurantManager = null;
							}
							
							$scope.setSelected = function(selected) {
								$scope.selected = selected;
								$rootScope.restaurant = $scope.selected;
								$scope.selectedRestaurantManager = null;
								$scope.show = null;
								$scope.restaurant = null;
								$scope.newRestaurantManager = null;

							}

							$scope.setSelectedRestaurantManager = function(
									selected) {
								$scope.selectedRestaurantManager = selected;
							}

							$scope.setSelectedSystemManager = function(selected) {
								$scope.show = null;
								$scope.selectedSystemManager = selected;
							}

							systemManagerService.getSystemManagers().then(
									function(response) {
										$scope.systemManagers = response.data;
									});

							systemManagerService.getRestaurants().then(
									function(response) {
										$scope.restaurants = response.data;
									});


							$scope.registerRestaurant = function() {
								var restaurant = $scope.restaurant;
								systemManagerService.registerRestaurant(
										restaurant).then(
										function(response) {
											if (response.data) {
												swal({
									    			  title: "Successful registration",
									    			  text: "Registrated " + response.data.restaurantName,
									    			  type: "success",
									    			  timer: 1000
									    			});
												$scope.restaurants
														.push(response.data);
												$scope.show = null;
												$scope.restaurant = null;
											}
										}).catch(function(response) {
											ngNotify.set('There is error in your input. Name and description must start with capital letter and must not contain special signs' , {
												type : 'error',
											    sticky: true
											});
											console.error('Gists error', response.status, response.data)
										  });
							}

							$scope.editRestaurant = function() {
								$scope.restaurant = null;
								$scope.newRestaurantManager = null;
								if ($scope.editRestaurant.description == null)
									$scope.editRestaurant.description = $scope.selected.description;
								if ($scope.editRestaurant.restaurantName == null)
									$scope.editRestaurant.restaurantName = $scope.selected.restaurantName;
								$scope.editRestaurant.id = $scope.selected.id;
								var restaurant = $scope.editRestaurant;
								systemManagerService
										.editRestaurant(restaurant)
										.then(
												function(response) {
													if (response.data) {
														var index = $scope.restaurants
																.indexOf($scope.selected);
														$scope.restaurants[index] = response.data;
														$scope.restaurant = null;
														$scope.show = null;
													} 
												}).catch(function(response) {
													ngNotify.set('There is error in your input. Name and description must start with capital letter and must not contain special signs' , {
														type : 'error',
													    sticky: true
													});
													console.error('Gists error', response.status, response.data)
												  });
							}

							$scope.deleteRestaurant = function() {
								$scope.restaurant = null;
								$scope.newRestaurantManager = null;
								var selected_id = $scope.selected.id;
								systemManagerService
										.deleteRestaurant(selected_id)
										.then(
												function(response) {
													if (response.status == 200) {
														var index = $scope.restaurants
																.indexOf($scope.selected);
														$scope.restaurants
																.splice(index,
																		1);
														$scope.selected = null;
														$scope.show = null;
														ngNotify.set('Successfuly deleted' , {
															type : 'success',
															theme : 'pitchy'
														});
													}
												}).catch(function(response) {
													ngNotify.set('Delete error' , {
														type : 'error',
													    sticky: true
													});
													console.error('Gists error', response.status, response.data)
												  });

							}

							$scope.getRestaurantManagers = function() {
								var restaurant = $scope.selected;
								systemManagerService.getRestaurantManagers(
										restaurant.id).then(function(response) {
									$scope.show = 2;
									$scope.restaurantManagers = response.data;
								});
							}

							$scope.registerRestaurantManager = function() {
								var newRestaurantManager = $scope.newRestaurantManager;
								systemManagerService.registerRestaurantManager(
										$scope.selected.id,
										newRestaurantManager).then(
										function(response) {
											if (response.data) {
												$scope.show = null;
												swal({
									    			  title: "Successful registration",
									    			  text: "Registrated " + response.data.userName,
									    			  type: "success",
									    			  timer: 1000
									    			});
											} 
										}).catch(function(response) {
											ngNotify.set('Name, Surname must start with capital letter, also must not contain special signs. If you did everything as written, your email address is not unique' , {
												type : 'error',
											    sticky: true
											});
											console.error('Gists error', response.status, response.data)
										  });
							}

							$scope.deleteRestaurantManager = function() {
								systemManagerService
										.deleteRestaurantManager(
												$scope.selectedRestaurantManager.id)
										.then(
												function(response) {
													if (response.status == 200) {
														$scope.show = null;
														ngNotify.set('Successfuly deleted' , {
															type : 'success',
															theme : 'pitchy'
														});
													} 
												}).catch(function(response) {
													ngNotify.set('Delete error' , {
														type : 'error',
													    sticky: true
													});
													console.error('Gists error', response.status, response.data)
												  });

							}

							$scope.registerSystemManager = function() {
								$scope.selectedSystemManager = null;
								systemManagerService.registerSystemManager(
										$scope.newSystemManager).then(
										function(response) {
											if (response.data) {
												swal({
									    			  title: "Successful registration",
									    			  text: "Registrated " + response.data.restaurantName,
									    			  type: "success",
									    			  timer: 1000
									    			});
												response.data.dateOfBirth =  moment(response.data.dateOfBirth).format('YYYY-MM-DD');
												$scope.systemManagers
														.push(response.data);
												$scope.show = null;
											} 
										}).catch(function(response) {
											ngNotify.set('Name, Surname must start with capital letter, also must not contain special signs. If you did everything as written, your email address is not unique' , {
												type : 'error',
											    sticky: true
											});
											console.error('Gists error', response.status, response.data)
										  });
							}

							$scope.deleteSystemManager = function() {
								systemManagerService
										.deleteSystemManager(
												$scope.selectedSystemManager.id)
										.then(
												function(response) {
													if (response.status == 200) {
														var index = $scope.systemManagers
																.indexOf($scope.selectedSystemManager);
														$scope.systemManagers
																.splice(index,
																		1);
														$scope.show = null;
														ngNotify.set('Successfuly deleted' , {
															type : 'success',
															theme : 'pitchy'
														});
													} 
												}).catch(function(response) {
													ngNotify.set('Delete error' , {
														type : 'error',
													    sticky: true
													});
													   console.error('Gists error', response.status, response.data)
												  });

							}
							$scope.editSystemManager = function() {
								if ($scope.editSystemManager.userName == null)
									$scope.editSystemManager.userName = $rootScope.loggedUser.userName;
								if ($scope.editSystemManager.surname == null)
									$scope.editSystemManager.surname = $rootScope.loggedUser.surname;
								if ($scope.editSystemManager.password == null)
									$scope.editSystemManager.password = $rootScope.loggedUser.password;
								if ($scope.editSystemManager.dateOfBirth == null)
									$scope.editSystemManager.dateOfBirth = new Date(
											$rootScope.loggedUser.dateOfBirth);
								if ($scope.editSystemManager.email == null)
									$scope.editSystemManager.email = $rootScope.loggedUser.email;
								$scope.editSystemManager.id = $rootScope.loggedUser.id;
								var manager = $scope.editSystemManager;
								systemManagerService
										.editSystemManager(manager)
										.then(
												function(response) {
													if (response.data) {
														$rootScope.loggedUser = response.data;
													} 
												}).catch(function(response) {
													ngNotify.set('Name, Surname must start with capital letter, also must not contain special signs. If you did everything as written, your email address is not unique' , {
														type : 'error',
													    sticky: true
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
						} ]);






app
		.controller(
				'bidderController',
				[
						'$rootScope',
						'$scope',
						'$location', 'ngNotify',
						'BidderService',
						function($rootScope, $scope, $location, ngNotify, bidderService) {
							
							$scope.display = function(tab) {
								$scope.show = tab;
							}
							$scope.setSelectedRequestOffer = function(selected) {
								$scope.selectedRequestOffer = selected;
								$scope.show = null;
							}
							$scope.setSelectedBidderOffer = function(selected) {
								$scope.selectedBidderOffer = selected;
								$scope.show = null;
								$scope.newBidderOffer = null;
							}

							bidderService.getActiveOffers().then(
									function(response) {
										$scope.requestOffers = response.data;
									});

							bidderService.getBidderOffersForBidder(
									$rootScope.loggedUser.id).then(
									function(response) {
										$scope.bidderOffers = response.data;
									});

							$scope.showProductsForRequestOffer = function(id) {
								bidderService
										.getProducts(id)
										.then(
												function(response) {
													if (response.data) {
														$scope.requestOfferProducts = response.data;
														$scope.show = 2;
													}
												});
							}

							$scope.getBidderOfferForBidderAndRequest = function() {
								bidderService
										.getBidderOfferForBidderAndRequest(
												$scope.selectedRequestOffer.id,
												$rootScope.loggedUser.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.show = 3;
														$scope.bidderOffer = response.data;
													} else 
														{
														$scope.show = 1;
														}
												});
							}

							$scope.getDetailsOfRequestOffer = function() {
								bidderService
										.getRequestOffer(
												$scope.selectedBidderOffer.requestOffer.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.show = 1;
														$scope.requestOffer = response.data;
													}
												});
							}

							$scope.registerBidderOffer = function() {
								bidderService.registerBidderOffer(
										$scope.newBidderOffer,
										$rootScope.loggedUser.id,
										$scope.selectedRequestOffer.id).then(
										function(response) {
											if (response.data) {
												$scope.show = null;
											}
										}).catch(function(response) {
											ngNotify.set('All text inputs must start with capital letter, date input must be after expiration date.' , {
												type : 'error',
											    sticky: true
											});
											   console.error('Gists error', response.status, response.data)
										  });
							}
							
							$scope.editSelectedBidderOffer = function(id) {
								$scope.bidderOffer = $scope.selectedBidderOffer;
								$scope.show = 3;
							}

							$scope.editBidderOffer = function() {
								if ($scope.editBidderOffer.price == null)
									$scope.editBidderOffer.price = $scope.bidderOffer.price;
								if ($scope.editBidderOffer.garanty == null)
									$scope.editBidderOffer.garanty = $scope.bidderOffer.garanty;
								if ($scope.editBidderOffer.dateOfDelivery == null)
									$scope.editBidderOffer.dateOfDelivery = $scope.bidderOffer.dateOfDelivery;
								$scope.editBidderOffer.id = $scope.bidderOffer.id;
								bidderService.editBidderOffer(
										$scope.editBidderOffer).then(
										function(response) {
											if (response.data) {
												$scope.show = null;
											}
										}).catch(function(response) {
											ngNotify.set('Name, Surname must start with capital letter, also must not contain special signs. If you did everything as written, your email address is not unique' , {
												type : 'error',
											    sticky: true
											});
											   console.error('Gists error', response.status, response.data)
										  });
							}

							$scope.deleteBidderOffer = function() {
								bidderService
										.deleteBidderOffer(
												$scope.selectedBidderOffer.id)
										.then(
												function(response) {
													if (response.status == 200) {
														var index = $scope.bidderOffers
														.indexOf($scope.selectedBidderOffer);
														$scope.bidderOffers
																.splice(index,
																		1);
														ngNotify.set('Successfuly deleted' , {
															type : 'success',
															theme : 'pitchy'
														});
														$scope.show = null;
													}
												}).catch(function(response) {
													ngNotify.set('Error delete' , {
														type : 'error',
													    sticky: true
													});
													   console.error('Gists error', response.status, response.data)
												  });

							}

							$scope.editBidder = function(firstLog) {
								if ($scope.editBidder.userName == null)
									$scope.editBidder.userName = $rootScope.loggedUser.userName;
								if ($scope.editBidder.surname == null)
									$scope.editBidder.surname = $rootScope.loggedUser.surname;
								if ($scope.editBidder.password == null)
									$scope.editBidder.password = $rootScope.loggedUser.password;
								if ($scope.editBidder.dateOfBirth == null)
									$scope.editBidder.dateOfBirth = new Date(
											$rootScope.loggedUser.dateOfBirth);
								if ($scope.editBidder.email == null)
									$scope.editBidder.email = $rootScope.loggedUser.email;
								$scope.editBidder.id = $rootScope.loggedUser.id;
								$scope.editBidder.firstLogIn = firstLog;
								var bidder = $scope.editBidder;
								bidderService
										.editBidder(bidder)
										.then(
												function(response) {
													if (response.data) {
														$rootScope.loggedUser = response.data;
														if(!firstLog)
															$location.path('/home');
													}
												}).catch(function(response) {
													ngNotify.set('Name, Surname must start with capital letter, also must not contain special signs. If you did everything as written, your email address is not unique' , {
														type : 'error',
													    sticky: true
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
						} ]);






app
		.controller(
				'restaurantManagerController',
				[
						'$rootScope',
						'$scope',
						'$location',
						'RestaurantManagerService',
						function($rootScope, $scope, $location,
								restaurantManagerService) {

							$scope.display = function(tab) {
								$scope.show = tab;
								$scope.product = null;
								$scope.selectedProduct = null;
								$scope.selectedRestaurantProduct = null;
							}
							$scope.displayWorker = function(tab) {
								$scope.showW = tab;
							}
							$scope.displaySegment = function(tab) {
								$scope.showS = tab;
								
							}
							$scope.displayShift = function(tab) {
								$scope.showQ = tab;
								
							}
							$scope.displayRequest = function(tab) {
								$scope.showR = tab;
								$scope.selectedBid = null;
								$scope.selectedRestaurantProduct = null;
								$scope.selectedRequestOfferProduct = null;
								
							}

							$scope.setSelectedWorker = function(selected) {
								$scope.selectedWorker = selected;
								$scope.showW = null;
								$scope.waiterGrade = null;
								$scope.waiterEarnings = null;
							}
							
							$scope.setSelectedRequestOffer = function(selected) {
								$scope.selectedRequestOffer = selected;
								$scope.showR = null;
							}
							
							$scope.setSelectedBid = function(selected) {
								$scope.selectedBid = selected;
							}
							
							$scope.setSelectedRestaurantProduct = function(selected) {
								$scope.selectedRestaurantProduct = selected;
							}
							$scope.setSelectedRequestOfferProduct = function(selected) {
								$scope.selectedRequestOfferProduct = selected;
							}
							$scope.setSelectedProduct = function(selected) {
								$scope.selectedProduct = selected;
							}
							$scope.setSelectedShift = function(selected) {
								$scope.selectedShift = selected;
								$scope.showQ = null;
							}
							$scope.canEditSegment = false;
							$scope.canEditTable = false;
				
							$scope.setSelectedSegment = function(selected) {
								restaurantManagerService
								.seeIfCanDeleteSegment(selected.id).then(function(response) {
									if(response.data == 0) {
										$scope.canEditSegment = true;
									}
									else{
										$scope.canEditSegment = false;
									}
								});
								if(selected.smokingAllowed)
									$scope.editSegmentSM = {
											smokingAllowed : 'true'
										}
								else 
									$scope.editSegmentSM = {
										smokingAllowed : 'false'
									}
								$scope.selectedSegment = selected;
								$scope.selectedTable = null;
								$scope.showS = null;
								$scope.selectedTable = null;
							}
							$scope.setSelectedTable = function(selected) {
								if(selected.free)
									$scope.canEditTable = true;
								else 
									$scope.canEditTable = false;
								$scope.selectedTable = selected;
							}
							
							restaurantManagerService
									.getRestaurant($rootScope.loggedUser.id)
									.then(
											function(response) {
												$scope.restaurant = response.data;
												$rootScope.restaurant = $scope.restaurant;
												restaurantManagerService
												.restaurantGrade($scope.restaurant.id)
												.then(
														function(response) {
															if(response.data) {
																$scope.error = false
																if(response.data != -1)
																	$scope.grade = response.data;
																else 
																	$scope.grade = 'No grades yet'
															}
															else
																$scope.error = true;
														});
												restaurantManagerService
														.getRestaurantWorkers(
																$scope.restaurant.id)
														.then(
																function(
																		response) {
																	$scope.restaurantWorkers = response.data;
																});
												restaurantManagerService
												.getRestaurantSegments(
														$scope.restaurant.id)
												.then(
														function(
																response) {
															$scope.restaurantSegments = response.data;
														});
												restaurantManagerService
												.getWorkSchedulesForRestaurant($scope.restaurant.id)
												.then(
														function(response) {
															if (response.data) {
																$scope.error = false;
																$scope.restaurantShifts = response.data;
																$scope.showQ = null;
															} else {
																$scope.error = true;
															}
														});
											});
							restaurantManagerService
							.getRequestOffers($rootScope.loggedUser.id)
							.then(
									function(response) {
										if (response.data) {
											$scope.error = false;
											$scope.managerOffers = response.data;
										} else {
											$scope.error = true;
										}
									});
							$scope.cook = {
								cookType : 'SALAT'
							}
							$scope.product = {
									productType : 'DRINK'
								}
							$scope.segment = {
									smokingAllowed : 'true'
								}
							
								
					
							$scope.showProducts = false;
							$scope.getProductsForRestaurant = function() {
								$scope.product = null;
								$scope.selectedProduct = null;
								restaurantManagerService
										.getProductsForRestaurant($scope.restaurant.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														$scope.showProducts = true;
														$scope.restaurantProducts = response.data;
														$scope.selectedBid = null;
														$scope.selectedRestaurantProduct = null;
														$scope.selectedRequestOfferProduct = null;
														$scope.show = 3;
														$scope.showR = 5;
													} else {
														$scope.error = true;
													}
												});
							}
							$scope.getAllProducts = function() {
								$scope.product = null;
								restaurantManagerService
										.getAllProducts()
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														$scope.allProducts = response.data;
														$scope.show = 4;
													} else {
														$scope.error = true;
													}
												});
							}
							
							$scope.registerCook = function() {
								restaurantManagerService
										.registerCook($scope.cook,
												$scope.restaurant.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														$scope.restaurantWorkers
																.push(response.data);
														$scope.show = null;
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });;
							}
							$scope.registerBartender = function() {
								restaurantManagerService
										.registerBartender($scope.bartender,
												$scope.restaurant.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														$scope.restaurantWorkers
																.push(response.data);
														$scope.show = null;
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });;
							}
							$scope.registerWaiter = function() {
								restaurantManagerService
										.registerWaiter($scope.waiter,
												$scope.restaurant.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														$scope.restaurantWorkers
																.push(response.data);
														$scope.show = null;
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });;
							}
							$scope.registerBidder = function() {
								restaurantManagerService
										.registerBidder($scope.newBidder)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														$scope.showR = null;
														$scope.newBidder = null;
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });;
							}
							$scope.deleteWorker = function() {
								restaurantManagerService
										.deleteWorker($scope.selectedWorker.id)
										.then(
												function(response) {
													if (response.status == 200) {
														$scope.error = false;
														var index = $scope.restaurantWorkers
																.indexOf($scope.selectedWorker);
														$scope.restaurantWorkers
																.splice(index,
																		1);
														$scope.show = null;
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });;
							}
							
							$scope.showWorkerShifts = function() {
								restaurantManagerService
										.showWorkerShifts($scope.selectedWorker.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														$scope.workerShifts = response.data;
														$scope.showW = 4;
													} else {
														$scope.error = true;
													}
												});
							}
							
							$scope.showSegmentTables = function() {
								/*
								 * $scope.editTable = { segment :
								 * $scope.selectedSegment }
								 */
								restaurantManagerService
										.getTables($scope.selectedSegment.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														$scope.segmentTables = response.data;
														$scope.showS = 3;
													} else {
														$scope.error = true;
													}
												});
							}
							
							$scope.registerTable = function() {
								restaurantManagerService
										.registerTable($scope.table,
												$scope.selectedSegment.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														$scope.showS = null;
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });
							}

							$scope.editTable = function() {
								$scope.editTable.id = $scope.selectedTable.id;
								if($scope.editTable.segment == null)
									$scope.editTable.segment = $scope.selectedTable.segment;
								restaurantManagerService
										.editTable($scope.editTable, $scope.editTable.segment.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														var index = $scope.segmentTables
														.indexOf($scope.selectedTable);
														if($scope.editTable.segment.id == $scope.selectedSegment.id)
															$scope.segmentTables[index] = response.data;
														else 
															$scope.segmentTables.splice(index, 1);
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.deleteTable = function() {
								restaurantManagerService
										.deleteTable($scope.selectedTable.id)
										.then(
												function(response) {
													if (response.status == 200) {
														$scope.error = false;
														var index = $scope.segmentTables
														.indexOf($scope.selectedTable);
												$scope.segmentTables
														.splice(index,
																1);
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.editRestaurant = function() {
								if ($scope.editRestaurant.description == null)
									$scope.editRestaurant.description = $scope.restaurant.description;
								if ($scope.editRestaurant.restaurantName == null)
									$scope.editRestaurant.restaurantName = $scope.restaurant.restaurantName;
								$scope.editRestaurant.id = $scope.restaurant.id;
								var restaurant = $scope.editRestaurant;
								restaurantManagerService
										.editRestaurant(restaurant)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														$scope.show = 2;
														$scope.restaurant =  response.data;
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.registerProduct = function() {
								restaurantManagerService
										.registerProduct($scope.product,
												$scope.restaurant.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														$scope.show = null;
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });
							}
							$scope.deleteProduct = function() {
								restaurantManagerService
										.deleteProduct($scope.restaurant.id,$scope.selectedRestaurantProduct.id)
										.then(
												function(response) {
													if (response.status == 200) {
														$scope.error = false;
														var index = $scope.restaurantProducts
																.indexOf($scope.selectedRestaurantProduct);
														$scope.restaurantProducts
																.splice(index,
																		1);
														$scope.show = null;
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.addProduct = function() {
								restaurantManagerService
										.addProduct($scope.selectedProduct.id,
												$scope.restaurant.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														$scope.show = null;
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.registerSegment = function() {
								restaurantManagerService
										.registerSegment($scope.segment,
												$scope.restaurant.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														$scope.restaurantSegments.push(response.data);
														$scope.showS = null;
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.editSegment = function() {
								if($scope.editSegment.position == null)
									$scope.editSegment.position = $scope.selectedSegment.position;
								$scope.editSegment.smokingAllowed = $scope.editSegmentSM.smokingAllowed;
								$scope.editSegment.id = $scope.selectedSegment.id;
								restaurantManagerService
										.editSegment($scope.editSegment)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														var index = $scope.restaurantSegments
														.indexOf($scope.selectedSegment);
												$scope.restaurantSegments[index] = response.data;
														$scope.showS = null;
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.deleteSegment = function() {
								restaurantManagerService
										.deleteSegment($scope.selectedSegment.id)
										.then(
												function(response) {
													if (response.status == 200) {
														$scope.error = false;
														var index = $scope.restaurantSegments
														.indexOf($scope.selectedSegment);
												$scope.restaurantSegments
														.splice(index,
																1);
														$scope.showS = null;
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });
							}
							$scope.workerIsWaiter = false;
							$scope.enableReplacments = false;
							$scope.enableSubmit = true;
							$scope.changeStatusOfWorkerIsWaiter = function(worker) {
								if(worker.userRole == 'WAITER') {
									$scope.workerIsWaiter = true;		
								}
								else 
									$scope.workerIsWaiter = false;
							}
							
							$scope.registerWorkSchedule = function() {
								var id = 0;
									if($scope.workSchedule.segment == null && $scope.workerIsWaiter) {
										alert('must enter segment for waiter')		
									}
								else{
									if($scope.workerIsWaiter)
										id = $scope.workSchedule.segment.id;	
									restaurantManagerService
											.registerWorkSchedule($scope.workSchedule, $scope.workSchedule.worker.id, id)
											.then(
													function(response) {
														if (response.data) {
															$scope.error = false;
															if($scope.workerIsWaiter) {
																restaurantManagerService
																.getReplacments(response.data.id).then(
																		function(response) {
																			if (response.data != 0) {
																				$scope.possibleReplacments = response.data;
																				$scope.enableReplacments = true;
																				$scope.enableSubmit = false;
																				$scope.workSchedule.replacement = response.data;
																				
																			} else {
																				$scope.enableReplacments = false;
																			}
															}).catch(function(response) {
																$scope.error = true;
																   console.error('Gists error', response.status, response.data)
															  });
															} else {
																$scope.showQ = null;
															}
															$scope.restaurantShifts.push(response.data);
															$scope.workSchedule = response.data;
														} else {
															$scope.error = true;
														}
													}).catch(function(response) {
														$scope.error = true;
														   console.error('Gists error', response.status, response.data)
													  });
								}
							}
							$scope.setReplacement  = function() {
								restaurantManagerService
										.setReplacment($scope.workSchedule.id, $scope.workSchedule.replacement.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														var index = $scope.restaurantShifts
														.indexOf($scope.selectedShift);
												$scope.restaurantShifts[index] = response.data;
														$scope.showQ = null;

													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });
							}
							$scope.deleteShift  = function() {
								restaurantManagerService
										.deleteShift($scope.selectedShift.id)
										.then(
												function(response) {
													if (response.status == 200) {
														$scope.error = false;
														var index = $scope.restaurantShifts
														.indexOf($scope.selectedShift);
												$scope.restaurantShifts.splice(index,
														1);
												$scope.showQ = null;

													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.editWorkScheduleInfo  = function() {
								if($scope.editWorkSchedule.date == null)
									$scope.editWorkSchedule.date = $scope.selectedShift.date;
								if($scope.editWorkSchedule.startTime == null)
									$scope.editWorkSchedule.startTime = $scope.selectedShift.startTime;
								if($scope.editWorkSchedule.endTime == null)
									$scope.editWorkSchedule.endTime = $scope.selectedShift.endTime;
								if($scope.editWorkSchedule.twoDays == null)
									$scope.editWorkSchedule.twoDays = $scope.selectedShift.twoDays;
								$scope.editWorkSchedule.id = $scope.selectedShift.id;
								restaurantManagerService
										.editWorkScheduleInfo($scope.editWorkSchedule)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														var index = $scope.restaurantShifts
														.indexOf($scope.selectedShift);
												$scope.restaurantShifts[index] = response.data;
												showQ = null;

													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.registerRequestOffer  = function() {
								restaurantManagerService
										.registerRequestOffer($scope.requestOffer, $rootScope.loggedUser.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														$scope.managerOffers.push(response.data);
														$scope.showR = null;

													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.editRequestOffer  = function() {
								if($scope.editRequestOffer.startDate == null)
									$scope.editRequestOffer.startDate = $scope.selectedRequestOffer.startDate;
								if($scope.editRequestOffer.expirationDate == null)
									$scope.editRequestOffer.expirationDate = $scope.selectedRequestOffer.expirationDate;
								$scope.editRequestOffer.id = $scope.selectedRequestOffer.id;
								restaurantManagerService
										.editRequestOffer($scope.editRequestOffer)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														var index = $scope.managerOffers
														.indexOf($scope.selectedRequestOffer);
												$scope.managerOffers[index] = response.data;
														$scope.showR = null;

													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.deleteRequestOffer  = function() {
								restaurantManagerService
										.deleteRequestOffer($scope.selectedRequestOffer.id)
										.then(
												function(response) {
													if (response.status == 200) {
														$scope.error = false;
														var index = $scope.managerOffers
														.indexOf($scope.selectedRequestOffer);
												$scope.managerOffers.splice(index, 1);
														$scope.showR = null;
														$scope.selectedBid = null;
														$scope.selectedRestaurantProduct = null;
														$scope.selectedRequestOfferProduct = null;

													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });
							}
							$scope.addProdustToRequestOffer  = function() {
								restaurantManagerService
										.addProductToRequestOffer($scope.selectedRequestOffer.id, $scope.selectedRestaurantProduct.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														$scope.showR = null;

													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });
							}
							$scope.removeProdustFromRequestOffer  = function() {
								restaurantManagerService
										.removeProdustFromRequestOffer($scope.selectedRequestOffer.id, $scope.selectedRequestProduct.id)
										.then(
												function(response) {
													if (response.status == 200) {
														$scope.error = false;
														var index = $scope.requestOfferProducts
														.indexOf($scope.selectedRequestProduct);
												$scope.requestOfferProducts.splice(index, 1);
														$scope.showR = null;

													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.getBidderOffersForRequest = function() {
								restaurantManagerService
										.getBidderOffersForRequest($scope.selectedRequestOffer.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														$scope.biddings = response.data;
														$scope.showR = 3;
														$scope.selectedBid = null;
														$scope.selectedRestaurantProduct = null;
														$scope.selectedRequestOfferProduct = null;
													} else {
														$scope.error = true;
													}
												});
							}
							
							$scope.showProductsForRequestOffer = function() {
								restaurantManagerService
										.getProducts($scope.selectedRequestOffer.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														$scope.requestOfferProducts = response.data;
														$scope.showR = 4;
														$scope.selectedBid = null;
														$scope.selectedRestaurantProduct = null;
														$scope.selectedRequestOfferProduct = null;
													} else {
														$scope.error = true;
													}
												});
							}
							
							$scope.acceptBid = function() {
								restaurantManagerService
										.acceptBid($scope.selectedRequestOffer.id, $scope.selectedBid.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														$scope.selectedBid = null;
														$scope.selectedRestaurantProduct = null;
														$scope.selectedRequestOfferProduct = null;
														$scope.selectedRequestOffer.status = false;
														$scope.showR = null;
													} else {
														$scope.error = true;
													}
												});
							}
							$scope.getGradeOfWaiter = function() {
							restaurantManagerService
							.getGradeOfWaiter($scope.selectedWorker.id)
							.then(
									function(response) {
										if(response.data) {
											$scope.error = false
											if(response.data != -1)
												$scope.waiterGrade = response.data;
											else 
												$scope.waiterGrade = 'No grades yet'
										}
										else
											$scope.error = true;
									});
							}
							
							$scope.getEarningForWaiter = function() {
								restaurantManagerService
								.getEarningForWaiter($scope.selectedWorker.id)
								.then(
										function(response) {
											if(response.data) {
												$scope.error = false
												if(response.data != -1)
													$scope.waiterEarnings = response.data;
												else 
													$scope.waiterEarnings = 'No earnings yet'
											}
											else
												$scope.error = true;
										});
								}
							
							$scope.getRestaurantEarnings = function() {
								var start =  moment($scope.restaurantStart).format('MM-DD-YYYY');
								var end = moment($scope.restaurantEnd).format('MM-DD-YYYY');
								restaurantManagerService
								.getRestaurantEarnings($scope.restaurant.id, start, end)
								.then(
										function(response) {
											if(response.data) {
												$scope.error = false
												if(response.data != -1)
													$scope.restaurantEarnings = response.data;
												else 
													$scope.restaurantEarnings = 'No earnings yet'
											}
											else
												$scope.error = true;
										});
								}
							
							$scope.getProductGrade = function() {
								restaurantManagerService
								.getProductGrade($scope.restaurant.id, $scope.selectedRestaurantProduct.id)
								.then(
										function(response) {
											if(response.data) {
												$scope.error = false
												if(response.data != -1)
													$scope.gradeProduct = response.data;
												else 
													$scope.gradeProduct = 'No grades yet'
											}
											else
												$scope.error = true;
										});
								}
							
						} ]);

