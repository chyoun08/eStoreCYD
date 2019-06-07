<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="<c:url value="/resources/js/controller.js"/>"></script>

<div class ="container-wrapper">
	<div class="container">
		<div class="jumbotron">
			<div class="container">
				<h2>Cart!</h2>
				<p>All the selected products in your shopping cart</p>
			</div>
		</div>
	
		<section class="container" ng-app="cartApp">
			<div ng-controller="cartCtrl" ng-init="initCartId('${cartId}')">
				<div><a class="btn btn-warning pull-left" ng-click="clearCart()">
					<i class="fa fa-trash"></i> Clear Cart
				</a>
				<br><br><br><br>
				</div>
				<table class="table table-bordered">
					<tr>
						<th style="width: 15%">Product</th>
						<th style="width: 15%">Unit Price</th>
						<th style="width: 15%">Ouantity</th>
						<th style="width: 15%">Total Price</th>
						<th style="width: 40%">Action</th>
					</tr>
					
					<tr ng-repeat="item in cart.cartItems">
						<td style="width: 15%">{{item.product.name}}</td>
						<td style="width: 15%">{{item.product.price}}</td>
						<td style="width: 15%">{{item.quantity}}</td>
						<td style="width: 15%">{{item.totalPrice}}</td>
						<td style="width: 40%">
							<div class=container>
								<div class="row">
									<a class="btn btn-danger" style="width: 32%" ng-click="removeFromCart(item.product.id)">
									<i class="fa fa-times"></i>remove</a>
									<a style="width: 2%"></a>
									<a class="btn btn-warning" style="width: 32%" ng-click = "addToCartFromUser(item.product.id)">
									<i class="fa fa-plus"></i>plus</a>
									<a style="width: 2%"></a>
									<a class="btn btn-primary" style="width: 32%" ng-click="removeFromCartMinus(item.product.id)">
									<i class="fa fa-minus"></i>minus</a>
								</div>
							</div>
						</td>
					</tr>
					
					<tr>
						<td></td>
						<td></td>
						<td>Grand Total</td>
						<td>{{calGrandTotal()}}</td>
						<td></td>
				</table>
				
				<a class="btn btn-info" href="<c:url value="/products"/>" class="btn btn-default">Continue Shopping</a>
			</div>
		</section>
	</div>
</div>