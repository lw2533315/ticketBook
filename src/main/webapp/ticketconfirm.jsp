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
<%! 
List<Flight> line = new ArrayList<Flight>();
int totalPrice = 0;
String carbinLevel = "";

int seats = 0;
int adultPrice = 0;
int childPrice = 0;
%>
<%
line = (List<Flight>)request.getAttribute("line"); 
carbinLevel = (String)request.getAttribute("carbinLevel");
childPrice = (Integer)request.getAttribute("childPrice");
adultPrice = (Integer)request.getAttribute("adultPrice");
seats = (Integer)request.getAttribute("seat");

%>
    <!-- pass the seat number to js -->
    <input type="hidden" value='<%=seats%>' id= "passValue" />
    <input type="hidden" value=<%=childPrice %> id="child" />
    <input type="hidden" value=<%=adultPrice%> id="adult" /> 
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

	<div class="bannerticket">
		<!-- container -->
		<div class="container">
			
			<div class="faqs-top-grids">
				
					<div class="contact-grids">
					   <div class="col-md-12 contact-map-ticket">
                            <h3><b>Flight Information</b></h3>
                            <div class="map">
                                
                                  
                                            
                                            <!-- <h2 class="top">Flight Info</h2> -->
                                            <div class="div_pad_1_1"> 
                                                <div class="reservation newbackground">
                                                
                                                    <ul class="reservation" >
                                                                                                 
                                                            <li class="span1_of_1  adult">
                                                                  <span id="seatNoice" class="error"></span>
                                                                 <h6 style="width: 70px"><b>Adult</b></h6>
                                                                 
                            
                                                                 <div class="section_room">
                                                                        <select id="adultNo"  class="frm-field required">
                                                                            
                                                                            <option value="1">1</option>    
                                                                            <option value="2">2</option>
                                                                            <option value="3">3</option>         
                                                                            <option value="4">4</option>
                                                                            <option value="5">5</option>   
                                                                                      
                                                                            
                                                                        </select>
                                                                 </div> 
                                                            </li>
                                                            <li class="span1_of_1  children">
                                                                 <h6 style="color:black; width: 70px"><b>Child</b></h6>
                                                                
                                                                 <div class="section_room">
                                                                        <select id="childNo"  class="frm-field required">
                                                                                
                                                                            <option value="0">0</option>
                                                                            <option value="1">1</option>         
                                                                            <option value="2">2</option>
                                                                            <option value="3">3</option>         
                                                                            <option value="4">4</option>
                                                                            <option value="5">5</option>         
                                                                            
                                                                            
                                                                        </select>
                                                                 </div> 
                                                            </li> 
                                                            <li>
                                                                <h6 style="margin-left: 20px; " ><b>Carbin</b></h6> 
                                                                <span style = "margin-left: 20px ;width: 100px; font-size: 16px; color:white" id="displayCarbin"><%=carbinLevel %></span>
                                                                <!-- <input type="text" style="height: 37px; color:black; width: 100px" value ="<%=carbinLevel %>"  readonly="readonly" id="displayCarbin"> -->
                                                            </li>
                                                            <li>
                                                                <h6 style="margin-left: 50px"><b>Price</b></h6>
                                                                <span style = "margin-left: 50px; font-size: 16px; color:white" id="displayPrice">$<%=adultPrice %></span>
                                                                <!--  <input type="text" style="height: 37px; color:black; width: 100px" value ="$<%=adultPrice %>"  readonly="readonly" id="displayPrice">-->
                                                            </li>
                                                        </ul>
                                                    </div>
                                                    
                                                    <table class="tickettable1_1_tiket">
                                                    <%for(int i = 0; i < line.size(); i++){ 
                                                           String company = line.get(i).getFlightCompany();
                                                           String flightId = line.get(i).getFlightId();
                                                           String dptBrief = line.get(i).getDepartureCityBrief();
                                                           String arvBrief = line.get(i).getArriveCityBrief();
                                                           String dptCity = line.get(i).getDepartureCity();
                                                           String arvCity = line.get(i).getArriveCity();
                                                           String dptTime = line.get(i).getDepartureTime();
                                                           String arvTime = line.get(i).getArriveTime();
                                                           String comAndId = company+" & "+ flightId;
                                                    %>
                                                            <tr>
                                                                <td style="margin-bottom: 10px;"><b><%=comAndId%></b></td>
                                                                
                                                              <!--   <td class="tableright"><b>price</b></td> -->
                                                                
                                                            </tr>
                                                            <tr>
                                                                <td><table class="innertable ticketinfo">
                                                                    <tr>
                                                                        <td style="margin-bottom: 10px;">Departure information</td>
                                                                        <td ></td>
                                                                        <td style="margin-bottom: 10px;">Arrival information</td>
                                                                        
                                                                    </tr>
                                                                    <tr>
                                                                            <td class="dpttime"><%=dptTime %></td>
                                                                            <td></td>
                                                                            <td class="arvtime"><%=arvTime %></td>
                                                                    </tr>
                                                                    <tr>
                                                                            <td class="dptport"><%=dptBrief %></td>
                                                                            <td></td>
                                                                            <td class="arvport"><%=arvBrief %></td>
                                
                                                                    </tr>
                                                                    <tr>
                                                                         <td class="dptcity"><%=dptCity %></td>
                                                                         <td></td>
                                                                         <td class="arvcity"><%=arvCity %></td>
                                                                    </tr>
                                                                    </table>
                                                                </td>
                                                               <!--  <td>$249</td> -->
                                                            </tr>
                                
                                                           <%} %>
                                                        </table>
                                                        
                                               
                                                
                                            </div>
                                            
                                       
                            </div>
                        </div>
                    </div>
						<div class="col-md-12 contact-para ">
							 
							<h2 class="personal "><b>Personal Information</b> </h2>
							<br>
				 	 	
							<form action="payment"  method="post" class="formValidate" >
							<!-- <div class= "overflowDiv"  > -->
							 <div class="passengerinfo">        
							         <h5 style="color: #CD853F;font-size:22px">the 1st passenger</h5>
							         
								
										<p class="cofirminfo">First Name</p>		
										<input class="mywidth"  type="text" name ='firstName' value="" />						
									
									
										<p class="cofirminfo">Last Name</p>		
										<input class="mywidth" type="text" name ='lastName' value="" />						
								
							
									
									<p class="cofirminfo">Email</p>						
										<input class="mywidth" type="text" name ='email' value="" />							
								
												
									<p class="cofirminfo">Phone</p>						
										<input class="mywidth" type="text" name ='phone' value="" />							
									
									
									
                                    <p class="cofirminfo ">Gender</p>   
                                    <select class="mywidth" name = "gender" style="color:black; font-size: 15px ">
                                        <option style= "color:black; font-size: 15px" value ="female">Female</option>
                                        <option style= "color:black; font-size: 15px" value="male">Male</option>
                                    </select>                  
                                       <!--   <input type="text" name ='gender' value="" />   -->                       
                                               
                                    <p class="cofirminfo">Age</p>                     
                                        <input class="mywidth" type="text" name ='age' value="" />                          
                                 
								
									<div id="removePoint">
									
										<div class="send">
										    <input type = "hidden" value = "1" name="getAdultSize" id = "psgAdultNumbers"/>
										    <input type = "hidden" value = "0" name="getChildSize" id = "psgChildtNumbers"/>
										    
										    <input type = "hidden" value = "$<%=adultPrice%>"  name="adultPrice" id = "passPrice"/>
										    <input type = "hidden" value = "$<%=childPrice%>"  name="childPrice" id = "passPrice"/>
											<input type="submit" value="Confirm"  id="submitPassagers" >
										</div>
									
									</div>
									
							 </div>
							</form>
							
						</div>
						
			</div>
		</div>
		<!-- //container -->
	</div>
	<!-- //banner-bottom -->
	<!-- footer -->

	<!-- //footer -->
	
	<script defer src="js/jquery.flexslider.js"></script>
	<script defer src="js/ticketconfirm.js"></script>
	<script defer src="js/login.js"></script>
	
	
</body>
</html>