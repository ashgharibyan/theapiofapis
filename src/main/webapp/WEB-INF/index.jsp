<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/css/header.css" />
<link rel="stylesheet" type="text/css" href="/css/footer.css" />
<link rel="stylesheet" type="text/css" href="/css/longnreg.css" />
<script type="text/javascript" src="/js/headerScript.js" defer></script>
<title>The API of APIs</title>
<link rel="icon" type="image/x-icon" href="/imgs/api.png">
<script>
	function copyText() {

		/* Copy text into clipboard */
		navigator.clipboard.writeText("localhost:8080/api/all");
	}
</script>
</head>
<body>

	<div class="header">

		<!--Content before waves-->
		<div class="inner-header flex">

			<div>
				<h1 class="display-1">The API of APIs</h1>
				<input id="allLink" class="input-group text-center h2 text-dark" type="text"
					value="localhost:8080/api/all" disabled /> 
					
					<a onclick="copyText()"
					class="mt-3 btn btn-outline-light btn-lg display-3 ">COPY</a>


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

	<div class="d-flex justify-content-center align-items-center m-5">
		<div class="main">
			<input type="checkbox" id="chk" aria-hidden="true">

			<div class="signup">
				<form:form action="/register" method="post" modelAttribute="newUser">
					<label for="chk" aria-hidden="true">Sign up</label>

					<form:input class="formInp" path="userName" placeholder="User Name" />
					<div class="err fix-stroke">

						<form:errors class="err" path="userName"></form:errors>
					</div>

					<form:input class="formInp" path="email" placeholder="Email" />
					<div class="err">
						<form:errors class="err" path="email"></form:errors>
					</div>

					<form:input type="password" class="formInp" path="password"
						placeholder="Password" />
					<div class="err">
						<form:errors class="err" path="password"></form:errors>

					</div>

					<form:input type="password" class="formInp" path="confirm"
						placeholder="Confirm Password" />
					<div class="err">
						<form:errors class="text-center err" path="confirm"></form:errors>
					</div>
					<button>Sign up</button>
				</form:form>
			</div>




			<%-- <form:form action="/register" method="post" modelAttribute="newUser">
				<div class="mt-2">
					<form:label class="form-label" path="userName">User Name:</form:label>
					<form:input class="form-control" path="userName" />
					<form:errors class="text-danger" path="userName"></form:errors>
				</div>
				<div class="mt-2">
					<form:label class="form-label" path="email">Email:</form:label>
					<form:input class="form-control" path="email" />
					<form:errors class="text-danger" path="email"></form:errors>
				</div>
				<div class="mt-2">
					<form:label class="form-label" path="password">Password:</form:label>
					<form:input type="password" class="form-control" path="password" />
					<form:errors class="text-danger" path="password"></form:errors>
				</div>
				<div class="mt-2">
					<form:label class="form-label" path="confirm">Confirm Password:</form:label>
					<form:input type="password" class="form-control" path="confirm" />
					<form:errors class="text-danger" path="confirm"></form:errors>
				</div>
				<div class="mt-3">
					<input class="btn btn-primary" type="submit" value="Submit" />
				</div>
			</form:form> --%>














			<div class="login">
				<form:form action="/login" method="post" modelAttribute="newLogin">
					<label for="chk" aria-hidden="true">Login</label>
					<form:input class="formInp" path="email" placeholder="Email" />
					<div class="logErr">
						<form:errors class="text-danger" path="email"></form:errors>
					</div>
					<form:input type="password" class="formInp" path="password"
						placeholder="Password" />
					<div class="logErr">
						<form:errors class="text-danger" path="password"></form:errors>
					</div>
					<button>Login</button>
				</form:form>
			</div>
		</div>
		<div class="d-flex align-items-center">
			<div>
				<h1 style="color:#419fce; font-weight: bold;" class="display-3 nomarg">CREATE</h1>
				<h1 style="color:#4279e3" class="display-3 nomarg">YOUR</h1>
				<h1 style="color:#8d7cb1" class="display-3 nomarg">OWN</h1>
				<h1 style="color:#b0689e; font-weight: 900;" class="display-3 nomarg">API</h1>
				<h1 style="color:#e83f7c" class="display-3 nomarg">WITHIN</h1>
				<h1 style="color:#5344b9; font-weight: bold;" class="display-1 nomarg mt-1">SECONDS</h1>
			</div>

		</div>
	</div>
	<div style="height: 390px"></div>









	<%-- <form:form action="/login" method="post" modelAttribute="newLogin">
		<div class="mt-2">
			<form:label class="form-label" path="email">Email:</form:label>
			<form:input class="form-control" path="email" />
			<form:errors class="text-danger" path="email"></form:errors>
		</div>
		<div class="mt-2">
			<form:label class="form-label" path="password">Password:</form:label>
			<form:input type="password" class="form-control" path="password" />
			<form:errors class="text-danger" path="password"></form:errors>
		</div>
		<div class="mt-3">
			<input class="btn btn-primary" type="submit" value="Log In" />
		</div>
	</form:form> --%>


	<%-- 	<div class=" my-5">
		<div class="rounded mt-3 p-4"
			style="background-color: #0E1428; color: #ffffff">
			<h1>Welcome!</h1>
			<h4>Join APIofAPIs and create your own API within seconds.</h4>
		</div>

		<div class="d-flex justify-content-around align-content-center pt-4"
			style="gap: 20px">
			<div class="container p-4 rounded"
				style="background-color: #ECE9F0; color: #0E1428">
				<h2 class="mb-3 p-2 text-center rounded"
					style="background-color: #0E1428; color: #ffffff">Register</h2>
				<form:form action="/register" method="post" modelAttribute="newUser">
					<div class="mt-2">
						<form:label class="form-label" path="userName">User Name:</form:label>
						<form:input class="form-control" path="userName" />
						<form:errors class="text-danger" path="userName"></form:errors>
					</div>
					<div class="mt-2">
						<form:label class="form-label" path="email">Email:</form:label>
						<form:input class="form-control" path="email" />
						<form:errors class="text-danger" path="email"></form:errors>
					</div>
					<div class="mt-2">
						<form:label class="form-label" path="password">Password:</form:label>
						<form:input type="password" class="form-control" path="password" />
						<form:errors class="text-danger" path="password"></form:errors>
					</div>
					<div class="mt-2">
						<form:label class="form-label" path="confirm">Confirm Password:</form:label>
						<form:input type="password" class="form-control" path="confirm" />
						<form:errors class="text-danger" path="confirm"></form:errors>
					</div>
					<div class="mt-3">
						<input class="btn btn-primary" type="submit" value="Submit" />
					</div>
				</form:form>
			</div>
			<div class="container p-4 rounded"
				style="background-color: #ECE9F0; color: #0E1428">
				<h2 class="mb-3 p-2 text-center rounded"
					style="background-color: #0E1428; color: #ffffff">Log In</h2>
				<form:form action="/login" method="post" modelAttribute="newLogin">
					<div class="mt-2">
						<form:label class="form-label" path="email">Email:</form:label>
						<form:input class="form-control" path="email" />
						<form:errors class="text-danger" path="email"></form:errors>
					</div>
					<div class="mt-2">
						<form:label class="form-label" path="password">Password:</form:label>
						<form:input type="password" class="form-control" path="password" />
						<form:errors class="text-danger" path="password"></form:errors>
					</div>
					<div class="mt-3">
						<input class="btn btn-primary" type="submit" value="Log In" />
					</div>
				</form:form>
			</div>
		</div>
		<div style="height:200px"></div>
	</div> --%>


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
    <p>&copy;2023 Developed by Ashot Gharibyan | <a class="text-primary"
					href="https://www.linkedin.com/in/ashgharibyan/">Connect with the Developer </a></p>
 -->


</body>
</html>