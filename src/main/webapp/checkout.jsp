<%@page import="com.sun.org.apache.xml.internal.serialize.LineSeparator"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@page import="java.util.*, com.bean.Flight"%>
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

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>

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
    <%!List<Flight> line = new ArrayList<Flight>(); %>
    
   <!-- <input type = "hidden" value='${sessionScope.name}' id="passFirstName"/>
    <input type = "hidden" value='${sessionScope.lastName}' id="passLastName"/> --> 
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
						<li><a href="index.jsp">Home</a></li>


						<li><a href="account">Hi ${sessionScope.name}</a></li>
						<li><a href="signOut">LogOut</a></li>

					</ul>

				</div>

			</div>
		</div>

		<!--//header-->
		<!-- banner-bottom -->
		<div class="banner">
			<!-- container -->
			<div class="container">

				<div class="faqs-top-grids">

					<div class="contact-grids1 checkoutcard">
						<div class="col-md-7 contact-para">

							<img src="images/card.jpg" alt="no picture">
							<br>
							 <label
								for="checkbox"><input type="checkbox" id="defaultCheckbox">
								<i>User Default card:</i> <span class="error" id="defaultCard"></span></label>
							<form  class= "formValidate" action="confirm" method="post">
								<p>Card Number</p>
								<input size="25" type="text" id="cardNumber" name="cardNumber">
								<p>Name on the Card</p>
								<input size="25" type="text" id="cardName" name="cardName">
								<p>Expiration Day</p>
								<input type="number" min="1" max="12"  id="month" name="month"> 
								<input type="number" min="2019" max="2049" id="year" name="year">
								<p>CVV</p>
								<input type="text" size="10" id="cvv" name = "cvv"><br> <br> 
								<label for="checkbox"><input type="checkbox" id="saveDefault" name = "saveDefault">
									<i>Save as Default card:</i></label> <input type="submit"
									value="check out">
							</form>
						</div>



						<!-- <h2 class="top">Flight Info</h2> -->
						<div class="div_pad_3">

							<table class="tickettable1_1_1">
							<%line = (List<Flight>)session.getAttribute("line");
							  int size = line.size();
							  for(int i = 0; i < size; i++){
								  Flight flight = line.get(i);
								  String airAndId = flight.getFlightCompany() + "&" + flight.getFlightId();
								  String dptTime = flight.getDepartureTime();
								  String arvTime = flight.getArriveTime();
								  String dptBrief = flight.getDepartureCityBrief();
								  String arvBrief = flight.getArriveCityBrief();
								  String dptCity = flight.getDepartureCity();
								  String arvCity = flight.getArriveCity();
								  
							%>
								<tr>
									<td class="tableleft1_1"><b><%=airAndId %></b></td>

									

								</tr>
								<tr>
									<td><table class="innertable ticketinfo">
											<tr>
												<td class="dpttime"><%=dptTime%></td>
												<td></td>
												<td class="arvtime"><%=arvTime%></td>
											</tr>
											<tr>
												<td class="dptport"><%=dptBrief%></td>
												<td style="padding-left: 50px">To</td>
												<td class="arvport"><%=arvBrief%></td>

											</tr>
											<tr>
												<td class="dptcity"><%=dptCity%></td>
												<td></td>
												<td class="arvcity"><%=arvCity%></td>
											</tr>
										</table></td>
									
								</tr>

                                <%} %>
							</table>
							<HR style="border: 1 dashed #8a5bc0" width="85%" color=#987cb9
                                SIZE=1>
							<br>
							<br>
							<table   style="margin-left:60px; " >
							<tr>
							<td><span>${sessionScope.carbinLevel}</span></td>
                             <td></td>
                            </tr>
							<tr >
							 <td style="width:80%"><span>Adult X ${adultSize}</span></td>
							 <td><span><strong>Price: </strong>$${adultSize * adultPrice}</span></td>
							 
							
							</tr>
							<tr>
							 <td><span>Child X ${childSize}</span></td>
                             <td><span><strong>Price: </strong>$${childSize * childPrice}</span></td>
							</tr>
							
							
							
							<tr>
							<td><b >Total:</b></td>
							<td><b>$${adultSize * adultPrice + childSize*childPrice }</b>
							</tr>
							
							
							
							</table>
							
							

                            
						</div>


					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- //container -->

	<script defer src="js/jquery.flexslider.js"></script>

	<script src="js/jquery-ui.js"></script>
	<script src="js/checkout.js"></script>
	<script src="js/login.js"></script>

</body>
</html>