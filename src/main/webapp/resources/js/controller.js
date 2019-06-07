//angular 모델 정의
	var cartApp = angular.module('cartApp', []);

//변수를 받아 사용	scope와 http란 서비스객체가 주입됨
	cartApp.controller("cartCtrl",function($scope, $http) {
		
//		다음과같은 initCartId란 함수는 아래와같은일들을한다.가 모든넘들에관한 내용이다.
		$scope.initCartId = function(cartId) {
			$scope.cartId = cartId;
			$scope.refreshCart();
		};
		
//		내가만든 CartRestController의 @RequsetMapping이 ("/api/Cart/{cartId}")된부분의
//		그중 GET으로 되있는부분이 호출됨
		$scope.refreshCart = function() {
			$http.get('/eStore/api/cart/' + $scope.cartId).then(
					function successCallback(response){
						$scope.cart = response.data;
					});
		};
		
//		method가 DELETE일
		$scope.clearCart = function() {
			
			$scope.setCsrfToken();
			
			$http({
				method : 'DELETE',
				url : '/eStore/api/cart/' + $scope.cartId
			}).then(function successCallback(){
				$scope.refreshCart();
			}, function errorCallback(response){
				console.log(response.data);
			});
		};
		
		$scope.addToCart = function(productId) {
			
			$scope.setCsrfToken();
			
			$http.put('/eStore/api/cart/add/' + productId).then(
					function successCallback(){
						alert("Product successfully add to the cart!");
					}, function errorCallback(){
						alert("Adding to the cart failed!!")
					});
		};
		
		$scope.addToCartFromUser = function(productId) {
			
			$scope.setCsrfToken();
			
			$http.put('/eStore/api/cart/add/' + productId).then(
					function successCallback(){
						alert("Product successfully add to the cart!");
						$scope.refreshCart();
					}, function errorCallback(){
						alert("Adding to the cart failed!!")
					});
		};
		
		$scope.removeFromCart = function(productId){

			$scope.setCsrfToken();
			
			$http({
				method : 'DELETE',
				url : '/eStore/api/cart/cartItem/' + productId
			}).then(function successCallback(){
				$scope.refreshCart();
			},function errorCallback(response){
				alert("Deleting to the cart failed!!")
				console.log(response.data);
			});
		};
		
		$scope.removeFromCartMinus = function(productId){
			
			$scope.setCsrfToken();
			
			$http({
				method : 'PUT',
				url : '/eStore/api/cart/revise/cartItem/' + productId
			}).then(function successCallback(){
				alert("Product successfully minus to the cart!");
				$scope.refreshCart();
			},function errorCallback(response){
				alert("Deleting to th cart as one faild!!")
				console.log(response.data);
			});
		};
		
//		각상품의 총 가격계산
		$scope.calGrandTotal = function() {
			var grandTotal = 0;
			
			for(var i=0; i<$scope.cart.cartItems.length; i++){
				grandTotal += $scope.cart.cartItems[i].totalPrice;
			}
			return grandTotal;
		};
		
//		setCsrfToken이란 method를 정의하여 header에서 token으로 정의된값을 가져와서 사용할수있도록 한다.
//		이렇게되면 http의 header정보에 실질적 token값이 담겨서 넘어가며, form형태로 정보를 전달하지않아도된다.
		$scope.setCsrfToken = function(){
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfHeader = $("meta[name='_csrf_header']").attr("content");
			
			$http.defaults.headers.common[csrfHeader] = csrfToken;
		};
		
	});