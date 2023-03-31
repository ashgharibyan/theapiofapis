<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" type="text/css" href="/css/header.css" />
<link rel="stylesheet" type="text/css" href="/css/footer.css" />
<script type="text/javascript" src="/js/headerScript.js"></script>

</head>
<script>
        function copyText() {
      
            /* Copy text into clipboard */
            navigator.clipboard.writeText
                ("localhost:8080/api/all");
        }
    </script>
<body>


	<div class="header">

		<!--Content before waves-->
		<div class="inner-header flex">
			
			<div>
				<h1 class="display-1">Welcome ${user.userName}!</h1>
				<input class="input-group text-center h2 text-dark" type="text" value="localhost:8080/api/all" disabled />
<a onclick="copyText()"
					class="mt-3 btn btn-outline-light btn-lg display-3 ">COPY</a>					
					<a href="/create/API"
					class="mt-3 btn btn-outline-light btn-lg display-3 ">CREATE AN API</a>					
				
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
	<!--Header ends-->

	<!-- Main Content -->
	<div class="container mt-5 mb-5">

		<div class="d-flex justify-content-center align-items-center my-5">
		
		<p class="display-4" style="color:#2776BC">List of all the APIs created by  </p>
		<p class="display-1" style="color:#503FB7"> YOU</p>
		</div>
		<table class="table table-hover text-center">
			<thead></thead>
			<tr>
				<th class="h2" style="color:#503FB7">API Name</th>
				<th class="h2" style="color:#503FB7">API Link</th>
				<th class="h2" style="color:#503FB7">Add Element</th>
			</tr>
			<tbody>
				<c:forEach var="apiName" items="${allApis }">
					<tr>
						<td ><a class="h4" style="color:#2776BC" href="/${user.userName }/${apiName}s">${apiName.toUpperCase()}</a></td>
						<td><input class="input-group " type="text"
							value="localhost:8080/api/${user.userName }/${apiName}s" disabled /></td>
						<td><a class="btn btn-outline-primary" href="/${user.userName }/${apiName}/create">Add a ${apiName}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div class="mt-5 d-flex justify-content-center">
			<a href="/logout" class="btn btn-outline-primary">LOG
				OUT</a>
		</div>

	</div>

<div style="height:450px;width:auto;"></div>

	<footer>
		<svg viewBox="0 0 120 28">
 <defs> 
   <mask id="xxx">
     <circle cx="7" cy="12" r="40" fill="#fff" />
   </mask>
   
   <filter id="goo">
      <feGaussianBlur in="SourceGraphic" stdDeviation="2" result="blur" />
      <feColorMatrix in="blur" mode="matrix"
				values="
           1 0 0 0 0  
           0 1 0 0 0  
           0 0 1 0 0  
           0 0 0 13 -9"
				result="goo" />
      <feBlend in="SourceGraphic" in2="goo" />
  	</filter>
     <path id="wave"
				d="M 0,10 C 30,10 30,15 60,15 90,15 90,10 120,10 150,10 150,15 180,15 210,15 210,10 240,10 v 28 h -240 z" />
  </defs> 

   <use id="wave3" class="wave" xlink:href="#wave" x="0" y="-2"></use> 
   <use id="wave2" class="wave" xlink:href="#wave" x="0" y="0"></use>
 
  
  <g class="gooeff">
  <circle class="drop drop1" cx="20" cy="2" r="1.8" />
  <circle class="drop drop2" cx="25" cy="2.5" r="1.5" />
  <circle class="drop drop3" cx="16" cy="2.8" r="1.2" />
    <use id="wave1" class="wave" xlink:href="#wave" x="0" y="1" />
  

</svg>

		<div>
			2023 Developed by Ashot Gharibyan | <a style="color: white"
				href="https://www.linkedin.com/in/ashgharibyan/">Connect with
				the Developer on Linkedin</a>
		</div>
	</footer>



<!-- 

	<div class=" mt-5">

		<footer class="bg-light text-center">
		

			Copyright
			<div class="text-center p-3"
				style="background-color: rgba(0, 0, 0, 0.2);">
				© Developed by Ashot Gharibyan: <a class="text-primary"
					href="https://www.linkedin.com/in/ashgharibyan/">Connect with the Developer</a>
			</div>
			Copyright
		</footer>

	</div> -->
</body>
</html>