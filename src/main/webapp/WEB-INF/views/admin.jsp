<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container-wrapper">
	<div class="container">
		<h2>Administrator_Page</h2>
		<p class="lead">Administorator of Products</p>
	</div>
	<div class="container">
		<h2>
			<a href="<c:url value="/admin/productInventory"/>">Product_Inventory</a>
		</h2>
		<p class="lead">It's a page of Product Inventory for view,check,update.</p>
	</div>
</div>