var app = angular.module('webApp');

app.run([
         'ngNotify',
         function(ngNotify) {

             ngNotify.config({
            	    position: 'bottom',
            	    duration: 1000,
					theme : 'pitchy',
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
								if(tab == 4)
									$scope.editRestaurant = $scope.selected;
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
												ngNotify.set('Successfuly registrated restaurant' , {
													type : 'success'
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

							$scope.editRestaurantData = function() {
								$scope.restaurant = null;
								systemManagerService
										.editRestaurantData($scope.editRestaurant)
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
								systemManagerService
										.deleteRestaurant($scope.selected.id)
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
														$scope.selected = null;
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
												ngNotify.set('Successfuly registrated restaurant manager' , {
													type : 'success'
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
															type : 'success'
														});
														$scope.selectedRestaurantManager = null;
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
												ngNotify.set('Successfuly registrated system manager' , {
													type : 'success'
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
														$selectedSystemManager = null;
													} 
												}).catch(function(response) {
													ngNotify.set('Delete error' , {
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
								if(tab == 1)
									$scope.selected = null;
								
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
								bidderService.getRequestOffer(
										$scope.selectedRequestOffer.id).then(
										function(response) {
											if (response.data) {
												if($scope.selectedRequestOffer.status != response.data.status) {
													var index = $scope.requestOffers
													.indexOf($scope.selectedRequestOffer);
													$scope.requestOffers[index] = response.data;
													$scope.show = null;
												}
												else {
													bidderService
													.getBidderOfferForBidderAndRequest(
															$scope.selectedRequestOffer.id,
															$rootScope.loggedUser.id)
													.then(
															function(response) {
																if (response.data) {
																	$scope.show = 3;
																	$scope.editBidderOffer = response.data;
																} else 
																	{
																	$scope.show = 1;
																	}
															});
												}							
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
								bidderService.getBidderOffer(
										$scope.selectedBidderOffer.id).then(
										function(response) {
											if (response.data) {
												if($scope.selectedBidderOffer.offerStatus != response.data.offerStatus) {
													var index = $scope.bidderOffers
													.indexOf($scope.selectedBidderOffer);
													$scope.bidderOffers[index] = response.data;
													$scope.show = null;
												}
												else 		
													$scope.show = 3;
											}
										});
								$scope.editBidderOffer = $scope.selectedBidderOffer;
							}

							$scope.editBidderOfferData = function() {
								bidderService.editBidderOfferData(
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
															type : 'success'
														});
														$scope.show = null;
														$scope.selectedBidderOffer = null;
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
						'$location','ngNotify',
						'RestaurantManagerService',
						function($rootScope, $scope, $location,ngNotify,
								restaurantManagerService) {
							$scope.currentMonth=0;
							$scope.display = function(tab) {
								$scope.show = tab;
								$scope.product = null;
								$scope.selectedProduct = null;
								$scope.selectedRestaurantProduct = null;
								if(tab == 2)
									$scope.editRestaurant = $scope.restaurant;
							}
							$scope.displayWorker = function(tab) {
								if(tab == 1 || tab == 2 || tab == 3)
									$scope.selectedWorker = null;
								$scope.showW = tab;
							}
							$scope.displaySegment = function(tab) {
								if(tab == 1)
									$scope.selectedSegment = null;
								if(tab == 2)
									$scope.editSegment = $scope.selectedSegment;
								$scope.showS = tab;
								
							}
							$scope.displayShift = function(tab) {
								if(tab == 1)
									$scope.selectedShift = null;
								$scope.showQ = tab;
								if(tab == 2) {
									$scope.editWorkSchedule = $scope.selectedShift;
									restaurantManagerService.getWorkSchedule(
											$scope.selectedShift.id).then(
											function(response) {
												if (response.data) {
													if($scope.selectedShift.done != response.data.done){
														var index = $scope.restaurantShifts
														.indexOf($scope.selectedShift);
														$scope.restaurantShifts[index] = response.data;
														$scope.showQ = null;
														$scope.selectedShift = response.data;
													}
													else 		
														$scope.showQ = 2;
												}
											});
								}
								
								
							}
							$scope.displayRequest = function(tab) {
								$scope.showR = tab;
								if(tab == 1)
									$scope.selectedRequestOffer = null; 
								if(tab == 2) {
									$scope.editRequestOffer = $scope.selectedRequestOffer;
									restaurantManagerService.getRequestOffer(
											$scope.selectedRequestOffer.id).then(
											function(response) {
												if (response.data) {
													if($scope.selectedRequestOffer.status != response.data.status){
														var index = $scope.managerOffers
														.indexOf($scope.selectedRequestOffer);
														$scope.managerOffers[index] = response.data;
														$scope.showR = null;
														$scope.selectedShift = response.data;
													}
													else 		
														$scope.showR = 2;
												}
											});
									}
								$scope.selectedBid = null;
								$scope.selectedRestaurantProduct = null;
								$scope.selectedRequestOfferProduct = null;
								
							}

							$scope.setSelectedWorker = function(selected) {
								$scope.selectedWorker = selected;
								$scope.showW = null;
								$scope.waiterGrade = null;
								$scope.waiterEarnings = null;
								$scope.bartender = null;
								$scope.waiter = null;
								$scope.cook = null;
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
							$scope.setSelectedWaiter = function(selected) {
								$scope.selectedWaiter = selected;
							}
							$scope.setSelectedNameProduct = function(selected) {
								$scope.selectedNameProduct = selected;
							}
							$scope.canEditSegment = false;
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
								$scope.selectedSegment = selected;
								$scope.selectedTable = null;
								$scope.showS = null;
							}
							$scope.setSelectedTable = function(selected) {
								$scope.selectedTable = selected;
								$scope.editTable = selected;
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
																$scope.restaurantShifts = response.data;
																$scope.showQ = null;
															} 
														});
											});
							
							$scope.editRestaurantData = function() {
								restaurantManagerService
										.editRestaurantData($scope.editRestaurant)
										.then(
												function(response) {
													if (response.data) {
														$scope.show = null;
														$scope.restaurant =  response.data;
													}
												}).catch(function(response) {
													ngNotify.set('This will never happen' , {
														type : 'error',
													    sticky: true
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							restaurantManagerService
							.getRequestOffers($rootScope.loggedUser.id)
							.then(
									function(response) {
										if (response.data) {
											$scope.managerOffers = response.data;
										} 
									});
							$scope.getProductsForRestaurant = function() {
								$scope.product = null;
								$scope.selectedProduct = null;
								restaurantManagerService
										.getProductsForRestaurant($scope.restaurant.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.restaurantProducts = response.data;
														$scope.selectedBid = null;
														$scope.selectedRestaurantProduct = null;
														$scope.selectedRequestOfferProduct = null;
														$scope.show = 3;
														$scope.showR = 5;
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
														$scope.allProducts = response.data;
														$scope.show = 4;
													}
												});
							}
							
							$scope.registerProduct = function() {
								restaurantManagerService
										.registerProduct($scope.product,
												$scope.restaurant.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.show = null;
														ngNotify.set('Successfuly added product' , {
															type : 'success',
														});
													} 
												}).catch(function(response) {
													ngNotify.set('This will never happen' , {
														type : 'error',
														sticky : true
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
							$scope.deleteProduct = function() {
								restaurantManagerService
										.deleteProduct($scope.restaurant.id,$scope.selectedRestaurantProduct.id)
										.then(
												function(response) {
													if (response.status == 200) {
														ngNotify.set('Successfuly removed product' , {
															type : 'success',
														});
														$scope.selectedRestaurantProduct = null;
														$scope.show = null;
													}
												}).catch(function(response) {
													ngNotify.set('Error delete' , {
														type : 'error',
														sticky : true
													});
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
														ngNotify.set('Successfuly added product' , {
															type : 'success'
														});
														$scope.show = null;
													}
												}).catch(function(response) {
													ngNotify.set('This will never happen' , {
														type : 'error',
														sticky : true
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.registerCook = function() {
								restaurantManagerService
										.registerCook($scope.cook,
												$scope.restaurant.id)
										.then(
												function(response) {
													if (response.data) {
														response.data.dateOfBirth =  moment(response.data.dateOfBirth).format('YYYY-MM-DD');
														$scope.restaurantWorkers
																.push(response.data);
														ngNotify.set('Successfuly registrated cook' , {
															type : 'success'
														});
														$scope.show = null;
													}
												}).catch(function(response) {
													ngNotify.set('Email you have entered is already used' , {
														type : 'error',
														sticky : true
													});
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
														response.data.dateOfBirth =  moment(response.data.dateOfBirth).format('YYYY-MM-DD');
														$scope.restaurantWorkers
																.push(response.data);
														ngNotify.set('Successfuly registrated bartender' , {
															type : 'success'
														});
														$scope.bartender = null;
														$scope.show = null;
													} 
												}).catch(function(response) {
													ngNotify.set('Email you have entered is already used' , {
														type : 'error',
														sticky : true
													});
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
														response.data.dateOfBirth =  moment(response.data.dateOfBirth).format('YYYY-MM-DD');
														$scope.restaurantWorkers
																.push(response.data);
														ngNotify.set('Successfuly registrated waiter' , {
															type : 'success'
														});
														$scope.waiter = null;
														$scope.show = null;
													} 
												}).catch(function(response) {
													ngNotify.set('Email you have entered is already used' , {
														type : 'error',
														sticky : true
													});
													   console.error('Gists error', response.status, response.data)
												  });;
							}
							$scope.registerBidder = function() {
								restaurantManagerService
										.registerBidder($scope.newBidder)
										.then(
												function(response) {
													if (response.data) {
														$scope.showR = null;
														$scope.newBidder = null;
														$scope.show = null;
														ngNotify.set('Successfuly registrated bidder' , {
															type : 'success'
														});
													} else {
														$scope.error = true;
													}
												}).catch(function(response) {
													ngNotify.set('Email you have entered is already used' , {
														type : 'error',
														sticky : true
													});
													   console.error('Gists error', response.status, response.data)
												  });;
							}
							$scope.deleteWorker = function() {
								restaurantManagerService
										.deleteWorker($scope.selectedWorker.id)
										.then(
												function(response) {
													if (response.status == 200) {
														ngNotify.set('Successfuly deleted worker' , {
															type : 'success'
														});
														var index = $scope.restaurantWorkers
																.indexOf($scope.selectedWorker);
														$scope.restaurantWorkers
																.splice(index,
																		1);
														$scope.show = null;
														$scope.selectedWorker = null;
													}
												}).catch(function(response) {
													ngNotify.set('Error delete' , {
														type : 'error',
														sticky : true
													});
													   console.error('Gists error', response.status, response.data)
												  });;
							}
							
							
							$scope.showSegmentTables = function() {
								$scope.replaceSegment = $scope.selectedSegment;
								restaurantManagerService
										.getTables($scope.selectedSegment.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.segmentTables = response.data;
														$scope.showS = 3;
													}
												});
							}
							
							$scope.showSegmentViewTables = function(id, index) {
								restaurantManagerService
										.getTables(id)
										.then(
												function(response) {
													if (response.data) {
														$scope.restaurantSegments[index].tables = response.data;
														$scope.showS = 3;
													}
												});
							}
							

							$scope.changeReplaceSegment = function(segment) {
								$scope.replaceSegment = segment;
							}
							
							$scope.registerTable = function() {
								restaurantManagerService
										.registerTable($scope.table,
												$scope.selectedSegment.id)
										.then(
												function(response) {
													if (response.data) {
														ngNotify.set('Successfuly registrated table' , {
															type : 'success'
														});
														$scope.showS = null;
													} 
												}).catch(function(response) {
													ngNotify.set('Unexpected error' , {
														type : 'error',
														sticky : 'true'
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}

							$scope.editTableData = function() {
								$scope.checkTableStatus();
								restaurantManagerService
										.editTableData($scope.editTable, $scope.editTable.segment.id)
										.then(
												function(response) {
													if (response.data) {
														var index = $scope.segmentTables
														.indexOf($scope.selectedTable);
														if($scope.editTable.segment.id == $scope.selectedSegment.id)
															$scope.segmentTables[index] = response.data;
														else 
															$scope.segmentTables.splice(index, 1);
														$scope.selectedTable = null;
													} 
												}).catch(function(response) {
													ngNotify.set('Position in segment taken' , {
														type : 'error',
														sticky : 'true'
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.checkTableStatus = function() {
								restaurantManagerService.getRestaurantTable(
										$scope.selectedTable.id).then(
										function(response) {
											if(response.data) {
												if($scope.selectedTable.free != response.data.free){
													var index = $scope.segmentTables
													.indexOf($scope.selectedTable);
													$scope.segmentTables[index] = response.data;
													$scope.selectedTable = null;
												}
											}
										});
							}
							
							$scope.deleteTable = function() {
								$scope.checkTableStatus();
								restaurantManagerService
										.deleteTable($scope.selectedTable.id)
										.then(
												function(response) {
													if (response.status == 200) {
														var index = $scope.segmentTables
														.indexOf($scope.selectedTable);
												$scope.segmentTables
														.splice(index,
																1);
												ngNotify.set('Successfuly delete table' , {
													type : 'success'
												});
													}
													$scope.selectedTable = null;
												}).catch(function(response) {
													ngNotify.set('Unexpected error' , {
														type : 'error',
														sticky : 'true'
													});
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
														$scope.restaurantSegments.push(response.data);
														$scope.showS = null;
														ngNotify.set('Successfuly registrated segment to this restaurant' , {
															type : 'success'
														});
													} 
												}).catch(function(response) {
													ngNotify.set('Unexpected error' , {
														type : 'error',
														sticky : 'true'
													});s
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.editSegmentData = function() {
								restaurantManagerService
										.editSegmentData($scope.editSegment)
										.then(
												function(response) {
													if (response.data) {
														var index = $scope.restaurantSegments
														.indexOf($scope.selectedSegment);
												$scope.restaurantSegments[index] = response.data;
														$scope.showS = null;
													} 
												}).catch(function(response) {
													ngNotify.set('Unexpected error' , {
														type : 'error',
														sticky : 'true'
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.deleteSegment = function() {
								restaurantManagerService
										.deleteSegment($scope.selectedSegment.id)
										.then(
												function(response) {
													if (response.status == 200) {
														var index = $scope.restaurantSegments
														.indexOf($scope.selectedSegment);
												$scope.restaurantSegments
														.splice(index,
																1);
														$scope.showS = null;
														$scope.selectedSegment = null;
														ngNotify.set('Successfuly deleted segment' , {
															type : 'success'
														});
													} 
												}).catch(function(response) {
													ngNotify.set('Unexpected error' , {
														type : 'error',
														sticky : 'true'
													});
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
							$scope.showWorkerShifts = function() {
								restaurantManagerService
										.showWorkerShifts($scope.selectedWorker.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.workerShifts = response.data;
														$scope.showW = 4;
													}
												});
							}
							
							$scope.registerWorkSchedule = function() {
								var id = 0;
									if($scope.workSchedule.segment == null && $scope.workerIsWaiter) {
										ngNotify.set('If worker is waiter, then you must choose segment' , {
											type : 'info',
											sticky : 'true'
										});		
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
																$scope.workSchedule = response.data;
																restaurantManagerService
																.getReplacments(response.data.id).then(
																		function(response) {
																			if (response.data != 0) {
																				$scope.possibleReplacments = response.data;
																				$scope.enableReplacments = true;
																				$scope.enableSubmit = false;
																			} else {
																				$scope.showQ = null;
																				$scope.enableReplacments = false;
																			}
															}).catch(function(response) {
																ngNotify.set('Probably never happen' , {
																	type : 'error',
																	sticky : 'true'
																});
																   console.error('Gists error', response.status, response.data)
															  });
															} else {
																$scope.showQ = null;
															}
															ngNotify.set('Successfuly registrated work schedule' , {
																type : 'success'
															});
															response.data.date =  moment(response.data.date).format('YYYY-MM-DD');
															response.data.secondDate =  moment(response.data.secondDate).format('YYYY-MM-DD');
															$scope.restaurantShifts.push(response.data);
														} 
													}).catch(function(response) {
														ngNotify.set('Date must be today, or future, also if not twoDaysShift, than end time must be after start time' , {
															type : 'error',
															sticky : 'true'
														});
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
														response.data.date =  moment(response.data.date).format('YYYY-MM-DD');
														response.data.secondDate =  moment(response.data.secondDate).format('YYYY-MM-DD');
														$scope.workSchedule.replacement = response.data;
														var index = $scope.restaurantShifts
														.indexOf($scope.workSchedule);
												$scope.restaurantShifts[index] = response.data;
														$scope.showQ = null;
													}
												}).catch(function(response) {
													ngNotify.set('Never happen' , {
														type : 'error',
														sticky : 'true'
													});		
													console.error('Gists error', response.status, response.data)
												  });
							}
							$scope.deleteShift  = function() {
								restaurantManagerService
										.deleteShift($scope.selectedShift.id)
										.then(
												function(response) {
													if (response.status == 200) {
														var index = $scope.restaurantShifts
														.indexOf($scope.selectedShift);
														$scope.restaurantShifts.splice(index,1);
														$scope.showQ = null;
													ngNotify.set('Successfuly deleted workSchedule' , {
														type : 'success'
													});		
													$scope.selectedShift = null;
													} 
												}).catch(function(response) {
													ngNotify.set('Error delete' , {
														type : 'error',
														sticky : 'true'
													});	
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.editWorkScheduleInfo  = function() {
								restaurantManagerService
										.editWorkScheduleInfo($scope.editWorkSchedule)
										.then(
												function(response) {
													if (response.data) {
														response.data.date =  moment(response.data.date).format('YYYY-MM-DD');
														response.data.secondDate =  moment(response.data.secondDate).format('YYYY-MM-DD');
														var index = $scope.restaurantShifts
														.indexOf($scope.selectedShift);
													$scope.restaurantShifts[index] = response.data;
													$scope.showQ = null;
													$scope.selectedShift = null;
													}
												}).catch(function(response) {
													ngNotify.set('Date must be today, or future, also if not twoDaysShift, than end time must be after start time' , {
														type : 'error',
														sticky : 'true'
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.registerRequestOffer  = function() {
								restaurantManagerService
										.registerRequestOffer($scope.requestOffer, $rootScope.loggedUser.id)
										.then(
												function(response) {
													if (response.data) {
														response.data.startDate =  moment(response.data.startDate).format('YYYY-MM-DD');
														response.data.expirationDate =  moment(response.data.expirationDate).format('YYYY-MM-DD');
														$scope.managerOffers.push(response.data);
														$scope.showR = null;
														ngNotify.set('Successfuly registrated request offer' , {
															type : 'success'
														});
													} 
												}).catch(function(response) {
													ngNotify.set('Date must be today, or future, expiration date must be after start date' , {
														type : 'error',
														sticky : 'true'
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.editRequestOfferData  = function() {
								restaurantManagerService
										.editRequestOfferData($scope.editRequestOffer)
										.then(
												function(response) {
													if (response.data) {
														response.data.startDate =  moment(response.data.startDate).format('YYYY-MM-DD');
														response.data.expirationDate =  moment(response.data.expirationDate).format('YYYY-MM-DD');
														var index = $scope.managerOffers
														.indexOf($scope.selectedRequestOffer);
												$scope.managerOffers[index] = response.data;
														$scope.showR = null;

													} 
												}).catch(function(response) {
													ngNotify.set('Date must be today, or future, expiration date must be after start date' , {
														type : 'error',
														sticky : 'true'
													});
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
														ngNotify.set('Successfuly deleted request offer' , {
															type : 'success'
														});
													}
												}).catch(function(response) {
													ngNotify.set('Error delete' , {
														type : 'error',
														sticky : 'true'
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
							$scope.addProdustToRequestOffer  = function() {
								restaurantManagerService
										.addProductToRequestOffer($scope.selectedRequestOffer.id, $scope.selectedRestaurantProduct.id)
										.then(
												function(response) {
													if (response.data) {
														ngNotify.set('Successfuly added product to request offer' , {
															type : 'success'
														});
														$scope.showR = null;

													}
												}).catch(function(response) {
													ngNotify.set('Error adding product' , {
														type : 'error',
														sticky: true
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
							$scope.removeProdustFromRequestOffer  = function() {
								restaurantManagerService
										.removeProdustFromRequestOffer($scope.selectedRequestOffer.id, $scope.selectedRequestProduct.id)
										.then(
												function(response) {
													if (response.status == 200) {
														var index = $scope.requestOfferProducts
														.indexOf($scope.selectedRequestProduct);
												$scope.requestOfferProducts.splice(index, 1);
														$scope.showR = null;
														ngNotify.set('Successfuly deleted product from request offer' , {
															type : 'success'
														});

													}
												}).catch(function(response) {
													ngNotify.set('Error delete' , {
														type : 'error',
														sticky: true
													});
													   console.error('Gists error', response.status, response.data)
												  });
							}
							
							$scope.getBidderOffersForRequest = function() {
								restaurantManagerService
										.getBidderOffersForRequest($scope.selectedRequestOffer.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.biddings = response.data;
														$scope.showR = 3;
														$scope.selectedBid = null;
														$scope.selectedRestaurantProduct = null;
														$scope.selectedRequestOfferProduct = null;
													} 
												});
							}
							
							$scope.showProductsForRequestOffer = function() {
								restaurantManagerService
										.getProducts($scope.selectedRequestOffer.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.requestOfferProducts = response.data;
														$scope.showR = 4;
														$scope.selectedBid = null;
														$scope.selectedRestaurantProduct = null;
														$scope.selectedRequestOfferProduct = null;
													}
												});
							}
							
							$scope.acceptBid = function() {
								restaurantManagerService
										.acceptBid($scope.selectedRequestOffer.id, $scope.selectedBid.id)
										.then(
												function(response) {
													if (response.data) {
														$scope.selectedBid = null;
														$scope.selectedRestaurantProduct = null;
														$scope.selectedRequestOfferProduct = null;
														$scope.selectedRequestOffer.status = false;
														$scope.showR = null;
														ngNotify.set('Successfuly accepted bidder offer' , {
															type : 'success'
														});
													}
												});
							}
							$scope.getGradeOfWaiter = function() {
							restaurantManagerService
							.getGradeOfWaiter($scope.selectedWaiter.id)
							.then(
									function(response) {
										if(response.data) { 
											if(response.data != -1)  
												$scope.waiterGrade = response.data;
											else 
												$scope.waiterGrade = 'No grades yet'
												
													
										}});
							}
							
							$scope.getEarningForWaiter = function() {
								restaurantManagerService
								.getEarningForWaiter($scope.selectedWorker.id)
								.then(
										function(response) {
											if(response.data) {
												if(response.data != -1)
													$scope.waiterEarnings = response.data;
												else 
													$scope.waiterEarnings = 'No earnings yet'
											}
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
												if(response.data != -1)
													$scope.restaurantEarnings = response.data;
												else 
													$scope.restaurantEarnings = 'No earnings yet'
											}
									
										});
								}
							
							$scope.getProductGrade = function() {
								restaurantManagerService
								.getProductGrade($scope.restaurant.id, $scope.selectedNamedProduct.id)
								.then(
										function(response) {
											if(response.data) {
												if(response.data != -1)
													$scope.gradeProduct = response.data;
												else 
													$scope.gradeProduct = 'No grades yet'
											}
										});
								}
							$scope.onlyWithThatName = true;
							$scope.getAllWaitersByNameAndRestaurant = function() {
								restaurantManagerService
								.getAllWaitersByNameAndRestaurant($scope.restaurant.id, $scope.waiterName)
								.then(
										function(response) {
											if(response.data) {
												if(response.data.length > 1) {
													$scope.namedWaiters = response.data;
													$scope.onlyWithThatName = false;
												}else if(response.data.length == 0){
													$scope.waiterGrade = 'No waiter with that name'
												} else{
													$scope.onlyWithThatName = true;
													$scope.selectedWaiter = response.data[0];
													$scope.getGradeOfWaiter();
													
												}
											}
										});
								}
							$scope.showProducts = false;
							$scope.getAllProductsByNameAndRestaurant = function() {
								restaurantManagerService
								.getAllProductsByNameAndRestaurant($scope.restaurant.id, $scope.productName)
								.then(
										function(response) {
											if(response.data) {
												if(response.data.length > 1) {
													$scope.namedProducts = response.data;
													$scope.showProducts = true;
												}else if(response.data.length == 0){
													$scope.gradeProduct = 'No product with that name'
												} else{
													$scope.showProducts = false;
													$scope.selectedNamedProduct = response.data[0];
													$scope.getProductGrade();
													
												}
											}
										});
								}
							
							$scope.getReservations = function() {
								var start =  moment($scope.dailyChart.date).format('MM-DD-YYYY');
								if($scope.dailyChart.base){
									restaurantManagerService
									.getReservationsForWeek($scope.restaurant.id, start)
									.then(
											function(response) {
												if(response.data) {
													if(response.data.length >= 1) {
														$scope.reservations = response.data;
														$scope.createChart();
														$scope.showChart = true;
													}else {
														$scope.showChart = false;
														$scope.noResevations = 'No visits for selected week'
													}
												}
											});
									} else {
										restaurantManagerService
										.getReservationsForDay($scope.restaurant.id, start)
										.then(
												function(response) {
													if(response.data) {
														if(response.data.length >= 1) {
															$scope.reservations = response.data;
															$scope.createChart();
															$scope.showChart = true;
														}else {
															$scope.showChart = false;
															$scope.noResevations = 'No visits for selected day'
														}
													}
												});
									}
								}
							$scope.createChart = function() {
								var datesName = [];
								var startTimes = [];
								var days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
								for(var i = 0; i < $scope.reservations.length; i++) {
									var temp = $scope.reservations[i];
									var date = new Date(temp.date);
									var dayName = days[date.getDay()];
									startTimes[i] = temp.reservation.startTime;
									datesName[i] = dayName;			
								}
								var counts = {};

								datesName.forEach(function(element) {
								  counts[element] = (counts[element] || 0) + 1;
								});
								var countDay = [];
								var n = 0;
								for (var element in counts) {
								 countDay[n] = counts[element];
								 n++;
								} 
								$scope.labels =  ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
								  $scope.series = ['Series A', 'Series B'];
								  $scope.data = [
								    [countDay[0], countDay[1], countDay[2], countDay[3], countDay[4], countDay[5], countDay[6]]
								  ];
								  $scope.datasetOverride = [{ yAxisID: 'y-axis-1' }];
								  $scope.options = {
								    scales: {
								      yAxes: [
								        { 
								        	ticks: {
								            min: 0,
								            beginAtZero:true,
								            max: 10,
								            stepSize: 1
								        },
								          id: 'y-axis-1',
								          type: 'linear',
								          display: true,
								          position: 'left'
								        }
								      ]
								    }
								  };
							}
							

							$scope.range = function(min, max, step) {
							    step = step || 1;
							    var input = [];
							    for (var i = min; i < max; i += step) {
							        input.push(i);
							    }
							    return input;
							};
							
							$scope.showCalendar = function() {
								restaurantManagerService
								.getAllWorkSchedulesCalendar($scope.restaurant.id, $scope.currentMonth)
								.then(
										function(response) {
											if(response.data) {
													$scope.groups = response.data;
													$scope.showQ = 3;
											}
										});
							}
							
							$scope.nextMonth=function(){
								$scope.currentMonth+=1;
								$scope.showCalendar();
							}
							
							$scope.previousMonth=function(){
								$scope.currentMonth-=1;
								$scope.showCalendar();
							}

						} ]);

