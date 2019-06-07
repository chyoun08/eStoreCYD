<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-wrapper">
	<div class="container">
		<h2>All Products</h2>
		<p>Product market</p>
		<table class="table table-striped">
			<thead>
				<tr class="bg-success">
					<th>Photo Thumb</th>
					<th>Name</th>
					<th>Category</th>
					<th>Price</th>
					<th>Manufacturer</th>
					<th>UnitInStock</th>
					<th>Description</th>
					<th> </th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${products}">
					<tr>
						<td> <img src="<c:url value="/resources/image/${product.imageFileName}"/>" alt="image" style="width:80%" /> </td>
						<td>${product.name}</td>
						<td>${product.category}</td>
						<td>${product.price}</td>
						<td>${product.manufacturer}</td>
						<td>${product.unitInStock}</td>
						<td>${product.description}</td>
						<td>
							<a  href="<c:url value="/viewProduct/${product.id}" />"> 
							<i class="fa fa-info-circle" style="color:#0000ff;"></i></a>
						</td>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>