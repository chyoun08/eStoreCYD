<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<!-- csrf의 공격을 대처하기위해 token을 넣어주는데 이를 form형태를 사용하지않을경우 아래와같이 넣어줘야한다. -->
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>

<title><tiles:insertAttribute name="title" /></title>

<!-- Bootstrap core CSS -->
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="<c:url value="/resources/css/carousel.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- AngularJS -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>

</head>

<body>
	<div>
		<tiles:insertAttribute name="menu" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
</body>
</html>