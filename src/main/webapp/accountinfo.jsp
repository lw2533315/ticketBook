<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" isErrorPage="false"%>
<%@ page language="java" import="com.bean.*, java.text.SimpleDateFormat,  java.util.*"%>
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
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style2.css" type="text/css" media="all">
<link rel="stylesheet" href="css/main.css" type="text/css" media="all">
<link rel="stylesheet" href="css/jquery-ui.css" />
<!-- js -->

<script src="js/jquery.min.js"></script>

<script
    src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>

  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
  

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
<script src="js/filter.js"></script>
<!--//pop-up-->
</head>
<body>
<%!

Map<FlightLine, List<Ticket>> info = new LinkedHashMap<FlightLine, List<Ticket>>();
//calculate teh diff of two time
boolean  compareTime(String s) {
    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date feature = null;
    try {
        feature =simpleFormat.parse(s);
    } catch (Exception e) {
        e.printStackTrace();
    } 
     Date date = new Date();
    long diff = feature.getTime() - date.getTime();
     return (diff/(1000 * 60 * 60.0)>2)? true: false;
} %>
<%info = (LinkedHashMap<FlightLine, List<Ticket>>)request.getAttribute("lineInfo");
  if(info == null || request.getAttribute("member") == null){
%>
    <a href="home"></a>
    <%} %>
    <input type="hidden"   value='${member.card.cardNumber }' id="passCardNumber" />
	<!--header-->
	<div class="header1">
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
                        <li ><a href="home">Home</a></li>
                        <%
                            if (session.getAttribute("name") == null) {
                        %>
                        <li><a href="findsignup">SignUp</a></li>
                        
                        <%
                            } else {
                        %>
                        
                        <li  class="active"><a href="account">Hi ${sessionScope.name}</a></li>
                        <li><a href="signOut">LogOut</a></li>

                        <%
                            }
                        %>

                    </ul>
                    <div class="clearfix"></div>
                    <!-- script-for-menu -->

                    <!-- /script-for-menu -->
                </div>
         
        </div>
    </div>
    </div>
	<!--//header-->
	<!-- banner-bottom -->
	<div class="banner1">
	<div id="light" class="white_content">
	   <img src="images/card.jpg" alt="no picture">
	   <form  class= "formValidate" action="index.jsp" method="get">
                                <input type="hidden"  value="${member.memberId}" name="memberId" id="memberId"/>
                                <label>Card Number</label><br>
                                <input size="25" type="text" id="cardNumber" name="cardNumber"><br>
                                <label>Name on the Card</label><br>
                                <input size="25" type="text" id="cardName" name="cardName"><br>
                                <label>Expiration Day</label><br>
                                <input type="number" min="1" max="12"  id="month" name="month"> 
                                <input type="number" min="2019" max="2049" id="year" name="year"><br>
                                <label>CVV</label><br>
                                <input type="text" size="10" id="cvv" name = "cvv"><br> <br> 
                                 <input type="submit"  style="height: 33px; width: 76px" 
                                    value="update" id="updateCard">
                                 <input type="submit"  style="height: 33px; width: 76px" 
                                    value="Cancel" id="cancelCard"> <span id="emptyInput" class="error"></span>
                            </form>
	
	</div>
		<!-- container -->
		<div class="container">
			<div class="faqs-top-grids">
				<div class="book-grids">
					<div class="col-md-6-account book-left">
						
						<div class="book-left-form memberColor">
							
								<h3><b>Member Id: </b></h3>
								<span  id ="id"><strong><i>${member.memberId}</i></strong></span><br>
								<h3><b>Name</b></h3>
								<span><strong><i>${member.firstName} ${member.lastName}</i></strong></span><br>
								<h3><b>Phone Number</b></h3>
								<span id="changedPhone"><strong><i>${member.phone}</i></strong></span><br>
								<h3><b>Email Address</b></h3>
								<span id="changedEmail"><strong><i>${member.email}</i></strong></span><br>
								<br>
								
								<span><strong><i id="newCard">Card Number: *${cardNumber }</i></strong></span><br>
								<strong><i><u><a  id="cardChange" style="font-size: 16px; color:blue ;" href=''>Update Bank Card</a></u></i></strong>
								
						
						</div>
					</div>
					<div class="col-md-6-account">
						<div class="book-left-info">
							<h4><strong>Update Email</strong></h4>
							<form  class="formValidate1" 
                                method="get">
								<input type="email" placeholder="email" name="email" id="email">
                                <br><input type="submit" value="update"  id="updateEmail" >
								
							
							</form>
							<br>
						</div>
						<div class="book-left-info">
							<h4><strong>Update Phone</strong></h4>
							<form action=""    method="get" class="formValidate2" >
								<input type="text" placeholder="phone number" name="phone" id="phone">
								<br><input type="submit" value="update"  id="updatePhone" >
							</form><br>
						</div>
						<div class="book-left-info">
							<h4><strong>Update Password</strong></h4>
							<form action=""    method="get" class="formValidate3">
								<input type="password" placeholder="password" name="password"  id="firstTime"><br>
								<input type="password" placeholder="confirm password" name="password" id="secondTime">
								<span id="passwordError" class = "error"></span>
								<br><input type="submit" value="Update" id="updatePassword">
							</form><br>
						</div>
						
					</div>
				
				</div>
			</div>
		</div>
		<!-- //container -->
	</div>
	<!-- //banner-bottom -->
	<!-- footer -->
	<div class="footer">
		<!-- container -->
		<div class="container">
			<div class="footer-top-grids">
				
					<!--  current ticket-->
					<div class="div_pad_1_account"> 
                      <%
                       
                       for(Map.Entry<FlightLine, List<Ticket>> row: info.entrySet()){
                           Ticket firstTicketOfLine = row.getValue().get(0);
                           String carbin = firstTicketOfLine.getCarbinLevel();
                           String dptDay = firstTicketOfLine.getFlight().getDepartureTime().substring(0,10);
                           
                           
                           String dptWholeTime = firstTicketOfLine.getFlight().getDepartureTime();
                           
                           int price = row.getKey().getPrice(); 
                           long lineId = row.getKey().getLineId();
                           %>
                        <div  id="<%=lineId %>" value="<%=price %>" >                      
						<table class="tickettable1_account">
						      <% 
							   
							   for(Ticket ticket: row.getValue()){
								   String airAndNo = ticket.getFlight().getFlightCompany() + "&" + ticket.getFlight().getFlightId();
								   String dptTime = ticket.getFlight().getDepartureTime().substring(11,16);
	                               String arvTime = ticket.getFlight().getArriveTime().substring(11,16);
	                               String dptBrief = ticket.getFlight().getDepartureCityBrief();
	                               String dptCity = ticket.getFlight().getDepartureCity();
	                               String arvBrif = ticket.getFlight().getArriveCityBrief();
	                               String arvCity = ticket.getFlight().getArriveCity();
	                               String name = ticket.getPassenger().getFirstName() + " " + firstTicketOfLine.getPassenger().getLastName();
	                               int age = ticket.getPassenger().getAge();
							   %>
								<tr>
									<th style = "width: 40%" class="tableflightinfo"  style="float: left"><b><%=airAndNo %></b></th>
									<th  style = "width: 20%" class="tabledate"><b>Day</b></th>
									<th  style = "width: 20%" class="tableName"><b>Name</b></th>
									<th style = "width: 10%"  class="tableLevel"><b>Cabin</b></th>
									<th style = "width: 10%" class="tableprice"><b>Category</b></th>
									
								</tr>
								<tr>
									<td><table style="text-align:left" class="innertable ticketinfo">
										<tr>
												<td style="width: 30%" class="dpttime1"><%=dptTime %></td>
												<td style="width: 30%" class="to1"></td>
												<td style="width: 40%" class="arvtime1"><%=arvTime %></td>
										</tr>
										<tr>
												<td class="dptport"><%=dptBrief %></td>
												<td>TO</td>
												<td class="arvport"><%=arvBrif %></td>
	
										</tr>
										<tr>
											 <td class="dptcity"><%=dptCity %></td>
											 <td></td>
											 <td class="arvcity"><%=arvCity %></td>
										</tr>
										</table>
									</td>
									
									<td style="text-align: left"><%=dptDay %></td>
									<td style="text-align: left"><%=name %></td>
									<td style="text-align: left"><%=carbin %></td>
									<%if (age >= 16) {%>
									<td style="text-align: left">Adult</td>
									<%}else{%>
									<td style="text-align: left">Child</td>
									<%} %>
									</tr>
							<%} %>
									
									
								
							</table>
							<ul style="float: right; margin-right: 50px">
							<li><h4><label><b>Total Price: </b>$<%=price %></label></h4></li>
							<%if(compareTime(dptWholeTime)){ %>
							      <li><h4><label><b title="you could get refund of 90% of ticket price, continue ?">Refund: </b><input type="checkbox" title="you could get refund of 90% of ticket price, continue ?" value = "<%=lineId %>" class="ticketcancel"></label></h4></li>
							   
							<%}else{ %>
							     <li><h4><label><b title="cannot refund ">Refund: </b><input type="checkbox"  title="cannot refund " disabled= "disabled" ></label></h4></li>
							
							<%} %>
                            </ul>
                            </div>
                            <HR style="border: 1 dashed #8a5bc0" width="85%" color=#987cb9
                                SIZE=1>
                              <%} %>
				   
					
				</div>
				
				<!-- news-letter -->
				
				<!-- //news-letter -->
			
		</div>
		<!-- //container -->
	</div>
	</div>
	<script defer src="js/jquery.flexslider.js"></script>
	
	<script src="js/jquery-ui.js"></script>
	<script src="js/login.js"></script>
	<script src="js/account.js"></script>
	
	
</body>
</html>