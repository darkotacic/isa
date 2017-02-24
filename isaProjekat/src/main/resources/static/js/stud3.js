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
	
	$scope.getTodayTables=function(){
		if($scope.workSchedule==undefined){
			swal({
	  			  title: "Schedule",
	  			  text: "There is no work schedule for today.",
	  			  type: "success",
	  			  timer: 3000
	  		});
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
			var index=$scope.orders.indexOf($scope.selected);
			alert(index);
			$scope.orders.splice(index,1);
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
			swal("Check created!", 'Waiter: '+check.waiter.userName+', price: '+check.price, "success")
		});
	}
	
}]);

app.factory('CookService', function cookService($http) {
	
	cookService.getWorkSchedules = function(){
		return $http({
			method : 'GET',
			url: '../cooks/getWorkSchedules'
		});
	}
	
	cookService.getFoodOrderItems = function(cookType){
		return $http({
			method : 'GET',
			url: 'cooks/getFoodOrderItems/'+cookType
		});
	}
	
	cookService.startPreparing = function(orderItem){
		return $http({
			method : 'PUT',
			url: '../cooks/startPreparing',
			data: {
				"id": orderItem.id,
				"quantity": orderItem.quantity,
				"order": orderItem.order
			}
		});
	}
	
	cookService.getTasks = function(){
		return $http({
			method : 'GET',
			url: '../cooks/getTasks'
		});
	}
	
	cookService.notify = function(orderItem){
		return $http({
			method : 'PUT',
			url: '../cooks/notify',
			data: {
				"id": orderItem.id,
				"quantity": orderItem.quantity,
				"order": orderItem.order
			}
		});
	}
	
	cookService.getWorkSchedulesBetween = function(startDate,endDate){
		return $http({
			method : 'GET',
			url: '../cooks/getWorkSchedules/'+startDate+'/'+endDate
		});
	}
	
	return cookService;
	
});

app.factory('BartenderService', function bartenderService($http) {
	
	bartenderService.getWorkSchedules = function(){
		return $http({
			method : 'GET',
			url: '../bartenders/getWorkSchedules'
		});
	}
	
	bartenderService.getDrinkOrderItems = function(){
		return $http({
			method : 'GET',
			url: 'bartenders/getDrinkOrderItems'
		});
	}
	
	bartenderService.notify = function(orderItem){
		return $http({
			method : 'PUT',
			url: '../bartenders/notify',
			data: {
				"id": orderItem.id,
				"quantity": orderItem.quantity,
				"order": orderItem.order
			}
		});
	}
	
	bartenderService.getWorkSchedulesBetween = function(startDate,endDate){
		return $http({
			method : 'GET',
			url: 'bartenders/getWorkSchedules/'+startDate+'/'+endDate
		});
	}
	
	return bartenderService;
	
});

app.controller('workerController',['$rootScope','$scope','$location','WaiterService','CookService','BartenderService',function($rootScope,$scope,$location,waiterService,cookService,bartenderService){
	
	$scope.user= $rootScope.loggedUser;
	$scope.schedules=null;
	
	$scope.initDate=function(){
		var now=new Date();
		$scope.startDate=now;
		$scope.endDate=now;
	}
	
	$scope.getWorkSchedules=function(){
		if($scope.user.userRole=="WAITER"){
			waiterService.getWorkSchedules().then(function(response){
				$scope.schedules=response.data;
			})
		}else if($scope.user.userRole=="COOK"){
			cookService.getWorkSchedules().then(function(response){
				$scope.schedules=response.data;
			});
		}else if($scope.user.userRole=="BARTENDER"){
			bartenderService.getWorkSchedules().then(function(response){
				$scope.schedules=response.data;
			})
		}
	}

	$scope.searchSchedules=function(){
		if($scope.user.userRole=="WAITER"){
			waiterService.getWorkSchedulesBetween($scope.startDate,$scope.endDate).then(function(response){
				$scope.schedules=response.data;
			});
		}else if($scope.user.userRole=="COOK"){
			cookService.getWorkSchedulesBetween($scope.startDate,$scope.endDate).then(function(response){
				$scope.schedules=response.data;
			});
		}else if($scope.user.userRole=="BARTENDER"){
			bartenderService.getWorkSchedulesBetween($scope.startDate,$scope.endDate).then(function(response){
				$scope.schedules=response.data;
			});
		}
	}
	
}]);

app.controller('cookController',['$rootScope','$scope','$location','CookService',function($rootScope,$scope,$location,cookService){
	
	$scope.user= $rootScope.loggedUser;
	$scope.onHoldOrders=null;
	$scope.tasks=null;
	$scope.selectedOnHold=null;
	$scope.selectedTask=null;
	
	cookService.getFoodOrderItems($scope.user.cookType).then(function(response){
		$scope.onHoldOrders=response.data;
	});
	
	cookService.getTasks().then(function(response){
		$scope.tasks=response.data;
	});
	
	$scope.setSelectedOnHold = function(ord){
		if($scope.selectedOnHold == ord){
			$scope.selectedOnHold= null;
			return;
		}
		$scope.selectedOnHold = ord;
	}
	
	$scope.setSelectedTask = function(ord){
		if($scope.selectedTask == ord){
			$scope.selectedTask = null;
			return;
		}
		$scope.selectedTask = ord;
	}
	
	$scope.startPreparing=function(){
		cookService.startPreparing($scope.selectedOnHold).then(function(response){
			var item=response.data;
			var index=$scope.onHoldOrders.indexOf($scope.selectedOnHold);
			$scope.tasks.push(item);
			$scope.onHoldOrders.splice(index,1);
			$scope.selectedOnHold= null;
			swal({
	  			  title: "Start preparing",
	  			  text: 'You accepted to prepare '+item.quantity+' '+ item.product.productName+'\n for order '+item.order.id,
	  			  type: "success",
	  			  timer: 3000
	  		});
		});
	}
	
	$scope.notify=function(){
		cookService.notify($scope.selectedTask).then(function(response){
			var item=response.data;
			var index=$scope.tasks.indexOf($scope.selectedTask);
			$scope.tasks.splice(index,1);
			$scope.selectedTask=null;
			swal({
  			  title: "Finish!",
  			  text: 'You finished preparing '+item.quantity+' '+item.product.productName+'\n for order '+item.order.id,
  			  type: "success",
  			  timer: 3000
  			});
		});
	}
	
}]);

app.controller('bartenderController',['$rootScope','$scope','$location','BartenderService',function($rootScope,$scope,$location,bartenderService){
	
	$scope.user= $rootScope.loggedUser;
	$scope.onHoldOrders=null;
	$scope.selected=null;
	
	bartenderService.getDrinkOrderItems().then(function(response){
		$scope.onHoldOrders=response.data;
	});
	
	$scope.setSelectedOnHold = function(ord){
		if($scope.selected == ord){
			$scope.selected= null;
			return;
		}
		$scope.selected = ord;
	}
	
	$scope.notify=function(){
		bartenderService.notify($scope.selected).then(function(response){
			var item=response.data;
			var index=$scope.onHoldOrders.indexOf($scope.selected);
			$scope.onHoldOrders.splice(index,1);
			$scope.selected=null;
			swal({
  			  title: "Finish!",
  			  text: 'You finished preparing '+item.quantity+' '+item.product.productName+'\n for order '+item.order.id,
  			  type: "success",
  			  timer: 3000
  			});
		});
	}
	
}]);