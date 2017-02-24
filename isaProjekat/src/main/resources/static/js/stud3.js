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
	
	waiterService.addOrderItem = function(orderId,productId,quantity){
		return $http({
			method : 'POST',
			url: '../waiters/addOrderItem/'+orderId+'/'+productId,
			data: {
				"quantity": quantity
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
				"id": orderItem.id,
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
			url: '../waiters/getWorkSchedule'
		});
	}
	
	waiterService.getWorkSchedulesBetween = function(startDate,endDate){
		return $http({
			method : 'GET',
			url: '../waiters/getWorkSchedules/'+startDate+'/'+endDate
		});
	}
	
	waiterService.getProducts = function(){
		return $http({
			method : 'GET',
			url: '../waiters/getProducts'
		});
	}
	
	waiterService.getOrderItemsForOrder = function(orderId){
		return $http({
			method : 'GET',
			url: '../waiters/getOrderItems/'+orderId
		});
	}
	
	return waiterService;
	
});

app.controller('waiterController',['$rootScope','$scope','$location','WaiterService',function($rootScope,$scope,$location,waiterService){
	
	$scope.selected= "";
	$scope.allSegments=true;
	$scope.lastAddedOrder=null;
	$scope.workSchedule=null;
	$scope.orders=null;
	$scope.products=null;
	$scope.editOrdersItems=null;
	$scope.orderItems=null;
	
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
	
	$scope.initDate=function(){
		var now=new Date();
		$scope.startDate=now;
		$scope.endDate=now;
	}
	
	$scope.searchSchedules=function(){
		waiterService.getWorkSchedulesBetween($scope.startDate,$scope.endDate).then(function(response){
			$scope.schedules=response.data;
		});
	}
	
	$scope.getTodayTables=function(){
		if($scope.workSchedule==undefined){
			alert("There is no work schedule for today.");
			return;
		}
		waiterService.getTablesForSegment($scope.workSchedule.segment.id).then(function(response){
			$scope.resTables=response.data;
		});
	}
	
	$scope.createOrder=function(tableNum){
		$scope.editOrdersItems=null;
		waiterService.createOrder(tableNum).then(function(response){
			$scope.lastAddedOrder=response.data;
			$scope.selected=null;
			$scope.orders.push(response.data);
		});
	}
	
	$scope.deleteOrder=function(){
		waiterService.deleteOrder($scope.selected).then(function(response){
		});
	}
	
	waiterService.getProducts().then(function(response){
		$scope.products=response.data;
	});
	
	$scope.addOrderItems=function(){
		for(var i=0;i<$scope.products.length;i++){
			var productId=$scope.products[i].id;
			var quantity=document.getElementById('product'+productId).value;
			waiterService.addOrderItem($scope.lastAddedOrder.id,productId,quantity).then(function(response){
			});
		}
		$scope.lastAddedOrder=null;
	}
	
	$scope.addItemsTo=function(){
		$scope.lastAddedOrder=$scope.selected;
	}
	
	$scope.editOrderItems=function(){
		$scope.editOrdersItems=$scope.selected;
		$scope.lastAddedOrder=null;
		waiterService.getOrderItemsForOrder($scope.editOrdersItems.id).then(function(response){
			$scope.orderItems=response.data;
		});
	}
	
	$scope.editItems=function(){
		var orderItem=new Object();
		for(var i=0;i<$scope.orderItems.length;i++){
			orderItem.id=$scope.orderItems[i].id;
			orderItem.quantity=document.getElementById('item'+orderItem.id).value;
			waiterService.updateOrderItem(orderItem).then(function(response){
			});
		}
		$scope.editOrdersItems=null;
	}
	
	$scope.makeCheck=function(){
		waiterService.makeCheck($scope.selected).then(function(response){
			var check=response.data;
			alert('Waiter: '+check.waiter.userName+', price: '+check.price);
		});
	}
	
}]);