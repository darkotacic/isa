var app = angular.module('webApp');
app
		.controller(
				'systemManagerController',
				[
						'$rootScope',
						'$scope',
						'$location',
						'SystemManagerService',
						function($rootScope, $scope, $location,
								systemManagerService) {

							$scope.display = function(tab) {
								$scope.show = tab;
							}

							systemManagerService.getSystemManagers().then(
									function(response) {
										$scope.systemManagers = response.data;
									});

							systemManagerService.getRestaurants().then(
									function(response) {
										$scope.restaurants = response.data;
									});

							$scope.setSelected = function(selected) {
								$scope.selected = selected;
								$rootScope.restaurant = $scope.selected;
								$scope.show = null;

							}

							$scope.setSelectedRestaurantManager = function(
									selected) {
								$scope.selectedRestaurantManager = selected;
							}

							$scope.setSelectedSystemManager = function(selected) {
								if (!selected.predefined)
									$scope.selectedSystemManager = selected;
							}

							$scope.registerRestaurant = function() {
								var restaurant = $scope.restaurant;
								systemManagerService.registerRestaurant(
										restaurant).then(
										function(response) {
											if (response.data) {
												$scope.error = false;
												alert("Uspesno registrovan:"
														+ response.data.id);
												$scope.restaurants
														.push(response.data);
												$scope.show = null;
												$scope.restaurant = null;
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
														$scope.error = false;
														alert("Uspesno izmjenje:"
																+ response.data.id);
														$scope.show = null;
														var index = $scope.restaurants
																.indexOf($scope.selected);
														$scope.restaurants[index] = response.data;
														$scope.restaurant = null;
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });
							}

							$scope.deleteRestaurant = function() {
								var selected_id = $scope.selected.id;
								systemManagerService
										.deleteRestaurant(selected_id)
										.then(
												function(response) {
													if (response.status == 200) {
														$scope.error = false;
														alert("Uspesno izbrisan");
														var index = $scope.restaurants
																.indexOf($scope.selected);
														$scope.restaurants
																.splice(index,
																		1);
														$scope.selected = null;
														$scope.show = null;
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
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
												$scope.error = false;
												alert("Uspesno registrovan:");
												$scope.getRestaurantManagers();
												$scope.restaurantManagers
														.push(response.data);
												$scope.show = 2;
											} else {
												$scope.error = true;
											}
										}).catch(function(response) {
											$scope.error = true;
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
														$scope.error = false;
														alert("Uspesno izbrisan");
														var index = $scope.restaurantManagers
																.indexOf($scope.selectedRestaurantManager);
														alert(" " + index);
														$scope.restaurantManagers
																.splice(index,
																		1);
														$scope
																.getRestaurantManagers();
														$scope.show = 2;
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
													   console.error('Gists error', response.status, response.data)
												  });

							}

							$scope.registerSystemManager = function() {
								var newSystemManager = $scope.newSystemManager;
								systemManagerService.registerSystemManager(
										newSystemManager).then(
										function(response) {
											if (response.data) {
												$scope.error = false;
												alert("Uspesno registrovan:");
												$scope.systemManagers
														.push(response.data);
												$scope.show = null;
											} else {
												$scope.error = true;
											}
										}).catch(function(response) {
											$scope.error = true;
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
														$scope.error = false;
														alert("Uspesno izbrisan");
														var index = $scope.systemManagers
																.indexOf($scope.selectedSystemManager);
														alert(" " + index);
														$scope.systemManagers
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
														$scope.error = false;
														alert("Uspesno izmjenje:"
																+ response.data.id);
														$rootScope.loggedUser = response.data;
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
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
						'$location',
						'BidderService',
						function($rootScope, $scope, $location, bidderService) {

							$scope.selectedRequestOffer = null;
							$scope.selectedBidderOffer = null;
							$scope.show = null;
							$scope.requestOfferProducts = null;
							$scope.bidderOffer = null;
							$scope.requestOffer = null;

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
														$scope.error = false;
														$scope.requestOfferProducts = response.data;
														$scope.show = 2;
													} else {
														$scope.error = true;
													}
												});
							}

							$scope.editSelectedBidderOffer = function(id) {
								$scope.bidderOffer = $scope.selectedBidderOffer;
								$scope.show = 3;
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
														$scope.error = false;
													} else {
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
														$scope.error = false;
													} else {
														$scope.show = 1;
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

							$scope.deleteBidderOffer = function() {
								bidderService
										.deleteBidderOffer(
												$scope.selectedBidderOffer.id)
										.then(
												function(response) {
													if (response.status == 200) {
														$scope.error = false;
														alert("Uspesno izbrisan");
														var index = $scope.bidderOffers
																.indexOf($scope.selectedBidderOffer);
														$scope.bidderOffers
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

							$scope.editBidder = function() {
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
								var bidder = $scope.editBidder;
								bidderService
										.editBidder(bidder)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														alert("Uspesno izmjenje:"
																+ response.data.id);
														$rootScope.loggedUser = response.data;
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													$scope.error = true;
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
							}
							$scope.displayWorker = function(tab) {
								$scope.showW = tab;
							}
							$scope.displaySegment = function(tab) {
								$scope.showS = tab;
							}

							$scope.setSelectedWorker = function(selected) {
								$scope.selectedWorker = selected;
								$scope.showW = null;
							}
							$scope.setSelectedRestaurantProduct = function(selected) {
								$scope.selectedRestaurantProduct = selected;
							}
							$scope.setSelectedProduct = function(selected) {
								$scope.selectedProduct = selected;
							}
							$scope.setSelectedSegment = function(selected) {
								$scope.selectedSegment = selected;
							}
							$scope.setSelectedTable = function(selected) {
								$scope.selectedTable = selected;
							}
							restaurantManagerService
									.getRestaurant($rootScope.loggedUser.id)
									.then(
											function(response) {
												$scope.restaurant = response.data;
												$rootScope.restaurant = $scope.restaurant;
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
							$scope.editSegment = {
									smokingAllowed : 'true'
								}
							
							$scope.getProductsForRestaurant = function() {
								restaurantManagerService
										.getProductsForRestaurant($scope.restaurant.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														$scope.restaurantProducts = response.data;
														$scope.show = 3;
													} else {
														$scope.error = true;
													}
												});
							}
							$scope.getAllProducts = function() {
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
								restaurantManagerService
										.editTable($scope.editTable)
										.then(
												function(response) {
													if (response.data) {
														$scope.error = false;
														var index = $scope.segmentTables
														.indexOf($scope.selectedTable);
												$scope.segmentTables[index] = response.data;
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
							
							
						} ]);

