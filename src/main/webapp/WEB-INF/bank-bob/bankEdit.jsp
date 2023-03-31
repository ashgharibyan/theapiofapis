<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/css/header.css" />
<meta charset="UTF-8">
<title>Edit Bank</title>
</head>
<body>

	<div class="header">

		<!--Content before waves-->
		<div class="inner-header flex">

			<path fill="#FFFFFF" stroke="#000000" stroke-width="10"
				stroke-miterlimit="10" d="M57,283" />
			<g> <path fill="#fff"
				d="M250.4,0.8C112.7,0.8,1,112.4,1,250.2c0,137.7,111.7,249.4,249.4,249.4c137.7,0,249.4-111.7,249.4-249.4
C499.8,112.4,388.1,0.8,250.4,0.8z M383.8,326.3c-62,0-101.4-14.1-117.6-46.3c-17.1-34.1-2.3-75.4,13.2-104.1
c-22.4,3-38.4,9.2-47.8,18.3c-11.2,10.9-13.6,26.7-16.3,45c-3.1,20.8-6.6,44.4-25.3,62.4c-19.8,19.1-51.6,26.9-100.2,24.6l1.8-39.7		c35.9,1.6,59.7-2.9,70.8-13.6c8.9-8.6,11.1-22.9,13.5-39.6c6.3-42,14.8-99.4,141.4-99.4h41L333,166c-12.6,16-45.4,68.2-31.2,96.2	c9.2,18.3,41.5,25.6,91.2,24.2l1.1,39.8C390.5,326.2,387.1,326.3,383.8,326.3z" />
			</g>
			</svg>
			<div>
				<h1 class="display-3 mb-3">Edit Bank</h1>
				<form:form action="/bob/bank/edit/${bank.id}/process" method="post"
					modelAttribute="bank">
					<input type="hidden" name="_method" value="put" />
					<div class="form-group h3">
						<label>Name</label>
						<form:input path="name" class="form-control" />
						<form:errors path="name" class="text-danger" />
					</div>
					<div class="form-group h3">
						<label>Address</label>
						<form:input path="address" class="form-control" />
						<form:errors path="address" class="text-danger" />
					</div>
					<input type="submit" value="Submit" class="btn btn-outline-light btn-lg mt-3"  />
				</form:form>
				<a class="btn btn-outline-light mt-5" href="/dashboard">BACK
					TO THE DASHBOARD</a>
			</div>

		</div>

		<!--Waves Container-->
		<div>
			<svg class="waves" xmlns="http://www.w3.org/2000/svg"
				xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 24 150 28"
				preserveAspectRatio="none" shape-rendering="auto">
<defs>
<path id="gentle-wave"
					d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z" />
</defs>
<g class="parallax">
<use xlink:href="#gentle-wave" x="48" y="0" fill="rgba(255,255,255,0.7" />
<use xlink:href="#gentle-wave" x="48" y="3" fill="rgba(255,255,255,0.5)" />
<use xlink:href="#gentle-wave" x="48" y="5" fill="rgba(255,255,255,0.3)" />
<use xlink:href="#gentle-wave" x="48" y="7" fill="#fff" />
</g>
</svg>
		</div>
		<!--Waves end-->

	</div>



	<!-- Footer content -->
	<div class=" mt-5">

		<footer class=" text-center">
			<!-- Grid container -->
			<div class="container p-4 pb-0">
				<!-- Section: Form -->
				<section class="">
					<form action="">
						<!--Grid row-->
						<div class="row d-flex justify-content-center"></div>
						<!--Grid row-->
					</form>
				</section>
				<!-- Section: Form -->
			</div>
			<!-- Grid container -->
			<div class="text-center">
				<p>*Current version only takes in 2 String attributes</p>
			</div>
			<!-- Copyright -->
			<div class="text-center p-3 text-white"
				style="background-color: #ADD8E6;">
				© Developed by Ashot Gharibyan: <a class="text-dark"
					href="https://www.linkedin.com/in/ashgharibyan/">Connect with
					the Developer</a>
			</div>
			<!-- Copyright -->
		</footer>

	</div>
	<!-- End of .container -->

</body>
</html>