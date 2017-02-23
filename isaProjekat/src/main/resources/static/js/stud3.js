var app=angular.module('webApp')

app.factory('WaiterService', function waiterService($http) {
	
	waiterService.getWorkSchedules = function(){
		return $http({
			method : 'GET',
			url: '../waiters/getWorkSchedules'
		});
	}
	
	waiterService.createOrder = function(tableNum){
		return $http({
			method : 'POST',
			url: 'waiters/createOrder/'+tableNum
		});
	}
	
	waiterService.updateOrder = function(order,tableNum){
		return $http({
			method : 'PUT',
			url: '../waiters/updateOrder/'+tableNum,
			data: {
				"id": order.id,
				"date": order.date,
				"time": order.time
			},
			headers: {
				   'Content-Type': 'application/json',
				   'Accept': 'application/json'
			}
		});
	}
	
	waiterService.getOrders = function(){
		return $http({
			method : 'GET',
			url: '../waiters/getOrders'
		});
	}
	
	waiterService.deleteOrder = function(order){
		return $http({
			method : 'DELETE',
			url: '../waiters/deleteOrder',
			data: {
				"id": order.id,
				"date": order.date,
				"time": order.time
			},
			headers: {
				   'Content-Type': 'application/json',
				   'Accept': 'application/json'
			}
		});
	}
	
	waiterService.addOrderItem = function(orderId,productId,orderItem){
		return $http({
			method : 'POST',
			url: '../waiters/addOrderItem/'+orderId+'/'+productId,
			data: {
				"quantity": orderItem.quantity
			},
			headers: {
				   'Content-Type': 'application/json',
				   'Accept': 'application/json'
			}
		});
	}
	
	waiterService.updateOrderItem = function(orderItem){
		return $http({
			method : 'PUT',
			url: '../waiters/updateOrderItem',
			data: {
				"quantity": orderItem.quantity
			},
			headers: {
				   'Content-Type': 'application/json',
				   'Accept': 'application/json'
			}
		});
	}
	
	waiterService.deleteOrderItem = function(order){
		return $http({
			method : 'DELETE',
			url: '../waiters/deleteOrder',
			data: {
				"id": order.id
			},
			headers: {
				   'Content-Type': 'application/json',
				   'Accept': 'application/json'
			}
		});
	}
	
	waiterService.makeCheck = function(order){
		return $http({
			method : 'POST',
			url: '../waiters/makeCheck',
			data: {
				"id": order.id
			},
			headers: {
				   'Content-Type': 'application/json',
				   'Accept': 'application/json'
			}
		});
	}
	
	waiterService.updateInformation = function(waiter){
		return $http({
			method : 'PUT',
			url: '../waiters/updateInformation',
			data: {
				"id": waiter.id,
				"email": waiter.email,
				"name": waiter.name,
				"surname": waiter.surname,
				"password": waiter.password,
				"dateOfBirth": waiter.dateOfBirth,
				"shoeNumber": waiter.shoeNumber,
				"shirtSize": waiter.shirtSize
			},
			headers: {
				   'Content-Type': 'application/json',
				   'Accept': 'application/json'
			}
		});
	}
	
	waiterService.getSegments = function(){
		return $http({
			method : 'GET',
			url: '../waiters/getSegments'
		});
	}
	
	waiterService.getTablesForSegment = function(segmentId){
		return $http({
			method : 'GET',
			url: '../waiters/getTablesForSegment/'+segmentId
		});
	}
	
	waiterService.getWorkSchedule = function(){
		return $http({
			method : 'GET',
			url: '../waiters/getWorkSchedule/'
		});
	}
	
	return waiterService;
	
});

app.factory('WorkerService', function workerService($http) {
	
	workerService.getTablesForSegment = function(segmentId){
		return $http({
			method : 'GET',
			url: '../waiters/getTablesForSegment/'+segmentId
		});
	}
	
	return workerService;
	
});

app.controller('waiterController',['$rootScope','$scope','$location','WaiterService',function($rootScope,$scope,$location,waiterService){
	
	$scope.selected= "";
	$scope.allSegments=true;
	
	$scope.setSelected = function(ord){
		if($scope.selected == ord){
			$scope.selected= "";
			$scope.edit=false;
			return;
		}
		$scope.selected = ord;
	}
	
	waiterService.getWorkSchedules().then(function(response){
		$scope.schedules=response.data;
	})
	
	waiterService.getSegments().then(function(response){
		$scope.segments=response.data;
	})
	
	waiterService.getWorkSchedule().then(function(response){
		$scope.workSchedule=response.data;
	});
	
	$scope.getTablesForSegment=function(id,index){
		waiterService.getTablesForSegment(id).then(function(response){
			$scope.segments[index].tables=response.data;
		});
	}
	
	$scope.checkSegment=function(seg){
		if($scope.allSegments || $scope.workSchedule.segment.id==seg.id)
			return true;
		return false;
	}
	
	waiterService.getOrders().then(function(response){
		$scope.orders=response.data;
	});
	
	$scope.showEditOrder=function(){
		$scope.edit=true;
	}
	
	$scope.editOrder=function(){
		waiterService.updateOrder($scope.selected,$scope.selected.table.id).then(function(response){
		});
		$scope.edit=false;
	}
	
}]);