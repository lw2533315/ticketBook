<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
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
<!-- //Custom Theme files -->
<link href="css/bootstrap.css" type="text/css" rel="stylesheet"
	media="all">
<link href="css/style.css" type="text/css" rel="stylesheet" media="all">
<link rel="stylesheet" href="css/flexslider.css" type="text/css"
	media="screen" />
<link type="text/css" rel="stylesheet" href="css/JFFormStyle-1.css" />
<!-- jquery -->
<script src="js/jquery.min.js"></script>
<!-- jquery validator -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>
<!-- jquery ui -->
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>

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
						<a href="index.html"><span>My</span>ticket</a>
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
						<li class="active"><a href="home">Home</a></li>
						<%
							if (session.getAttribute("name") == null) {
						%>
						<li><a href="findsignup">SignUp</a></li>
						<%
							} else {
						%>

						<li><a href="account">Hi ${sessionScope.name}</a></li>
						<li><a href="signOut">LogOut</a></li>

						<%
							}
						%>
						<!--	<li><a href="holidays.html">Holidays</a></li>
						<li><a href="flights-hotels.html">Flight+Hotel</a></li> -->

					</ul>

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
				<%
					if (session.getAttribute("name") == null) {
				%>
				<div class="dropdown-grids">



					<div id="loginContainer">
						<a href="#" id="loginButton"><span>Login</span></a>
						<div id="loginBox">
						<!-- do not stop the form function ,the validate will work and ajax validate email and 
						password match or not  -->
						
							<form id="loginForm" class="formValidate" 
								method="get">
								<div class="login-grids">
									<div class="login-grid-left">
										<fieldset id="body">
											<fieldset>
												<label for="email">Email Address</label> <input type="email"
													name="email" id="email">
											</fieldset>
											<fieldset>
												<label for="password">Password</label> <input
													type="password" name="password" id="password"
													class="passwordValidate">
											</fieldset>
											<span class="error" id = "matchCheck"></span>
											<input type="submit" id="login" value="Sign in">

											<!-- <label for="checkbox"><input type="checkbox" id="checkbox"> <i>Remember me</i></label> -->
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


			</div>
			<%
				}
			%>
		</div>
	</div>
	<!--//header-->
	<!-- banner -->
	<div class="banner">
		<!-- container -->
		<div class="container">
			<div class="col-md-4 banner-left">
				<section class="slider2">
					<div class="flexslider">
						<ul class="slides">
							<li>
								<div class="slider-info">
									<img src="images/1.jpg" alt="">
								</div>
							</li>
							<li>
								<div class="slider-info">
									<img src="images/2.jpg" alt="">
								</div>
							</li>
							<li>
								<div class="slider-info">
									<img src="images/3.jpg" alt="">
								</div>
							</li>
							<li>
								<div class="slider-info">
									<img src="images/4.jpg" alt="">
								</div>
							</li>
							<li>
								<div class="slider-info">
									<img src="images/2.jpg" alt="">
								</div>
							</li>
						</ul>
					</div>
				</section>
				<!--FlexSlider-->
			</div>
			<div class="col-md-8 banner-right">
				<div class="sap_tabs">
					<div class="booking-info">
						<h2>Book Domestic & International Flight Tickets</h2>
					</div>
					<div id="horizontalTab"
						style="display: block; width: 100%; margin: 0px;">
						<ul class="resp-tabs-list">
							<li class="resp-tab-item" aria-controls="tab_item-0" role="tab"><span
								id="roundtrip">Round</span></li>
							<li class="resp-tab-item" aria-controls="tab_item-1" role="tab"><span
								id="onetrip">One way</span></li>
						</ul>
						<!---->
						<div class="resp-tabs-container">
							<div class="tab-1" aria-labelledby="tab_item-0">
								<div class="facts">
									<div class="booking-form">
										<form action="findbook" method="POST">

											<div class="online_reservation">
												<div class="b_room">
													<div class="booking_room">
														<div class="reservation">
															<ul>
																<li class="span1_of_1 desti">
																	<h5>Flying from</h5>

																	<div class="book_date">

																		<span class="glyphicon glyphicon-map-marker"
																			aria-hidden="true"></span> <input type="text"
																			id="flightFrom" name="flightFrom"
																			placeholder="Type Departure City"
																			class="typeahead1 input-md form-control tt-input"
																			required="">

																	</div>
																</li>
																<li class="span1_of_1 left desti">
																	<h5>Flying to</h5>
																	<div class="book_date">

																		<span class="glyphicon glyphicon-map-marker"
																			aria-hidden="true"></span> <input type="text"
																			id="flightTo" name="flightTo"
																			placeholder="Type Destination City"
																			class="typeahead1 input-md form-control tt-input"
																			required=""
																			>

																	</div>
																</li>
															</ul>
														</div>
														<div class="reservation">
															<ul>
																<li class="span1_of_1">
																	<h5>Departure</h5>
																	<div class="book_date">

																		<span class="glyphicon glyphicon-calendar"
																			aria-hidden="true"></span> <input type="date"
																			value = "" name="dptTime" id="dptTime" required=""  >

																	</div>
																</li>

																<li class="span1_of_1 left ">
																	<div class=" resp-tab-content">
																		<h5>Return</h5>
																		<div class="book_date">

																			<span class="glyphicon glyphicon-calendar"
																				aria-hidden="true"></span> <input type="date" id="rtnTime"
																				 value = "" name="rtnTime" >

																		</div>
																	</div>
																</li>
															</ul>

														</div>
														<div class="reservation">
															<ul>
																<li class="span1_of_3">
																	<div class="date_btn">

																		<input style="height: 33px; width: 76px" type="submit" class="formButton" value="Search" />

																	</div>
																</li>
															</ul>
														</div>
													</div>

												</div>
											</div>
											<!---->
									</div>
									</form>
								</div>
							</div>
							<!--  <div class="tab-2 resp-tab-content" aria-labelledby="tab_item-1">
								<div class="facts"></div>-->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- //container -->
	
	<!-- //banner -->






	<script defer src="js/jquery.flexslider.js"></script>
	<!-- <script src="js/easyResponsiveTabs.js" type="text/javascript"></script> -->
	<script src="js/jquery-ui.js"></script>
	<!-- <script type="text/javascript" src="js/script.js"></script> -->
	<script type="text/javascript" src="js/index.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
	<script type="text/javascript">
		$(window).load(function() {
			$('.flexslider').flexslider({
				animation : "slide",
				start : function(slider) {
					$('body').removeClass('loading');
				}
			});
		});
	</script>
</body>
</html>