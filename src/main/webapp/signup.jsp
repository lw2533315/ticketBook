<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Govihar a Travel Agency Category Flat bootstrap
	Responsive website Template | Home :: w3layouts</title>
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Govihar Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
			Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<!-- <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script> -->
<!-- //Custom Theme files -->
<link href="css/bootstrap.css" type="text/css" rel="stylesheet"
	media="all">
<link href="css/style.css" type="text/css" rel="stylesheet" media="all">
<link rel="stylesheet" href="css/flexslider.css" type="text/css"
	media="screen" />
<link type="text/css" rel="stylesheet" href="css/JFFormStyle-1.css" />
<link type="text/css" rel="stylesheet" href="css/main.css" />
<!-- js -->
<script src="js/jquery.min.js"></script>
<!-- <script src="js/modernizr.custom.js"></script> -->
<!-- //js -->
<!-- fonts -->
<link
	href='//fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,700,500italic,700italic,900,900italic'
	rel='stylesheet' type='text/css'>
<link
	href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css'>
<!-- //fonts -->

<!--pop-up-->
<script src="js/menu_jquery.js"></script>
<!--//pop-up-->
</head>
<body>
	<!--header-->
	<div class="header">
		<div class="container">
			<div class="header-grids">
				<div class="logo">
					<h1>
						<a href="index.html"><span>Go</span>vihar</a>
					</h1>
				</div>
				<!--navbar-header-->
				<div class="header-dropdown">
					<div class="emergency-grid">
						<ul>
							<li>Toll Free :</li>
							<li class="call">+1 234 567 8901</li>
						</ul>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="nav-top">
				<div class="top-nav">
					<span class="menu"><img src="images/menu.png" alt="" /></span>
					<ul class="nav1">
						<li><a href="index.html">Home</a></li>

					</ul>
					<div class="clearfix"></div>
					<!-- script-for-menu -->
					<script>
						$("span.menu").click(function() {
							$("ul.nav1").slideToggle(300, function() {
								// Animation complete.
							});
						});
					</script>
					<!-- /script-for-menu -->
				</div>
				<div class="dropdown-grids">
					<div id="loginContainer">
						<a href="#" id="loginButton"><span>Login</span></a>
						<div id="loginBox">
							<form id="loginForm">
								<div class="login-grids">
									<div class="login-grid-left">
										<fieldset id="body">
											<fieldset>
												<label for="email">Email Address</label> <input type="text"
													name="email" id="email">
											</fieldset>
											<fieldset>
												<label for="password">Password</label> <input
													type="password" name="password" id="password"> <input
													type="hidden" name="curpage" value="home">
											</fieldset>
											<input type="submit" id="login" value="Sign in">

										</fieldset>


										<div class="social-sits">


											<div class="button-bottom">
												<p>
													New account? <a href="findsignup">Signup</a>
												</p>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--//header-->
	<!-- banner-bottom -->
	<div class="banner-bottom">
		<!-- container -->
		<div class="container">
			<div class="faqs-top-grids">
				<div class="book-grids">
					<div class="col-md-6 book-left">
					    <c:if test="${signup == false}" >
					    <span class = "error"><strong>This person Info Exists</strong></span>
					    </c:if>
						<div class="book-left-info">
							<h3>Create Your MyTicket Account</h3>
						</div>
						<div class="book-left-form">
						 
							<form:form action="savesignup" modelAttribute="member"
								method="post">
								<p>First Name</p>
								 <form:input type="text" path="firstName" placeholder="First name *" />
								<p>Last Name</p>
								<form:input type="text" value=""  path="lastName" />
								<p>Phone Number</p>
								<form:input type="text" value=""  path="phone" />
								<p>Email Address</p>
								<form:input type="email" value="" path="email" />
								
								
								<br>
								<p>Password</p>
								<input type="password" name="pwd" />
								<p>Confirm Password</p>
								<form:input type="password" name="password" id="password"
									path="password" />

								<input type="submit" id="register" value="Register"/>
							</form:form>
						</div>
					</div>
					<div class="col-md-6 book-left book-right">
						<img src="images/bgpart.png" alt="No Images" />
					</div>

				</div>
			</div>
		</div>

	</div>


	<script defer src="js/jquery.flexslider.js"></script>
	<!-- <script src="js/easyResponsiveTabs.js" type="text/javascript"></script> -->
	<script src="js/jquery-ui.js"></script>
	 <script type="text/javascript" src="js/login.js"></script>
</body>
</html>