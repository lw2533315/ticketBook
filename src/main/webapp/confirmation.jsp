<%@page import="com.sun.org.apache.xml.internal.serialize.LineSeparator"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@page import="java.util.*, com.bean.Flight"%>
<!DOCTYPE html>
<html>
<head>
<title>Govihar a Travel Agency Category Flat bootstrap Responsive website Template | Home :: w3layouts</title>
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Govihar Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
    Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<!-- <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script> -->
<!-- //Custom Theme files -->
<link href="css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
<link href="css/style.css" type="text/css" rel="stylesheet" media="all">
<link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
<link type="text/css" rel="stylesheet" href="css/JFFormStyle-1.css" />
<link type="text/css" rel="stylesheet" href="css/main.css" />
<!-- js -->
<script src="js/jquery.min.js"></script>

<script
    src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>

  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<!-- <script src="js/modernizr.custom.js"></script> -->
<!-- //js -->
<!-- fonts -->
<link href='//fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,700,500italic,700italic,900,900italic' rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<!-- //fonts -->    

<!--pop-up-->
<script src="js/menu_jquery.js"></script>
<!--//pop-up--> 
</head>
<body>
<%if(!session.getAttribute("rtnDay").equals("")){
	
%>
    <input type = "hidden" id="passPrice"  value = '${sessionScope.totalPrice}'/>
    <input type = "hidden" id="passDptCity" value = '${sessionScope.rtnCity}'/>
    <input type = "hidden" id="passArvCity" value = '${sessionScope.dptCity}'/>
     <input type = "hidden" id="passDptDay" value = '${sessionScope.rtnDay}'/>
      <input type = "hidden" id="passCarbin" value = '${sessionScope.carbinLevel}'/>
<%} %> 
    <!--header-->
    <div class="header">
        <div class="container">
            <div class="header-grids">
                <div class="logo">
                    <h1><a  href="index.html"><span>My</span>ticket</a></h1>
                </div>
                <!--navbar-header-->
                <div class="header-dropdown">
                    <div class="emergency-grid">
                        <ul>
                            <li>Toll Free : </li>
                            <li class="call">+1 234 567 8901</li>
                        </ul>
                    </div>
                    <div class="clearfix"> </div>
                </div>
                <div class="clearfix"> </div>
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
	<div class="banner-bottom">
		<!-- container -->
		<div class="container">
			<div class="about-info">
				<h2>Dear ${sessionScope.name} </h2>
			</div>
			<div class="about-grids">
				<div style = "color: black"class="col-md-8 about-left">
					<h3>Ticket book successful</h3>
					<table style ="width:80%; text-align: center">
					<tr style=" text-align: center">
					   <th style="width:33%"></th>
					   <th style="width: 33%;">One trip</th>
					   <th id="backTrip"><th>
					</tr>
					<tr>
					   <th>From</th>
					   <th>${sessionScope.dptCity}</th>
					   <th id="backCityFrom"></th>
					</tr>
					<tr>
					   <th>To</th>
					   <th>${sessionScope.rtnCity}</th>
					   <th id="backCityTo"></th>
					</tr>
					   
					<tr>
					<th>DptTime</th>
                       <th>${sessionScope.dptDay}</th>
                       <th id="backTime"></th>
					</tr>
					<tr>
					<th>Carbin</th>
                       <th>${sessionScope.carbinLevel}</th>
                       <th id="backCarbin"></th>
					</tr>
					<tr>
					<th>Price:</th>
					<th id="oneTripPrice">$${sessionScope.totalPrice}</th>
					<th id="roundTripPrice"></th>
					</tr>
				
					
					
					
					</table>
					
					
					<br><br>
					<div class="div_pad_1_confirmation"> 
                                                
							<table class="tickettable1_confirmation">
							     <%List<Flight> line = (List<Flight>)session.getAttribute("line");
							       int size = line.size();
							       for(int i = 0; i < size; i++){
							         Flight flight = line.get(i);
							         String airAndcompany = flight.getFlightCompany() + "&" + flight.getFlightId();
							         String dptTime = flight.getDepartureTime();
							         String arvTime = flight.getArriveTime();
							         String dptBrief = flight.getDepartureCityBrief();
							         String arvBrief = flight.getArriveCityBrief();
							         String dptCity = flight.getDepartureCity();
							         String arvCity = flight.getArriveCity();
							         
							         
							         %>
									<tr>
										<td class="tableflightinfo"  style="float: left"><b><%=airAndcompany %></b></td>
										
										
										
									</tr>
									<tr>
										<td><table style="text-align:left" class="innertable ticketinfo">
											<tr>
													<td class="dpttime1"><%=dptTime %></td>
													<td class="to1"></td>
													<td class="arvtime1"><%=arvTime %></td>
											</tr>
											<tr>
													<td class="dptport"><%=dptBrief %></td>
													<td>TO</td>
													<td class="arvport"><%=arvBrief %></td>
		
											</tr>
											<tr>
												 <td class="dptcity"><%=dptCity %></td>
												 <td></td>
												 <td class="arvcity"><%=arvCity %></td>
											</tr>
											</table>
										</td>
									
										
									</tr>
		<%} %>
		
								</table>
					   
						
					</div>
				</div>
				<div class="col-md-4 about-right">
					<img src="images/w1.jpg" alt="" />
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
		<!-- //container -->
	</div>
	<!-- //banner-bottom -->
	<!-- footer -->
	
	<!-- //footer -->
	
	<script defer src="js/jquery.flexslider.js"></script>
	
	<script src="js/jquery-ui.js"></script>
	<script src="js/confirmation.js"></script>
	
</body>
</html>