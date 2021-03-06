<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" isErrorPage="false"%>
<%@ page language="java" import="com.bean.Flight, java.util.*"%>
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
    <%!List<Map.Entry<List<Flight>, Integer>> lines = new ArrayList();
    %>
    <%
        lines = (List<Map.Entry<List<Flight>, Integer>>) request.getAttribute("lines");
    
       if(session.getAttribute("name") == null){   //set flag for jump button if null means not login now
    %>
    <input type="hidden" id="signinFlag" value="yes">
    <%}else {%>
    <input type="hidden" id="signinFlag" value="no">
    <%} %>
    <input type="hidden" id="passValue" value="${rtnTime}" />
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

                    </ul>
                    <div class="clearfix"></div>
                    <!-- script-for-menu -->

                    <!-- /script-for-menu -->
                </div>
              <%
                    if (session.getAttribute("name") == null) {
                %>
                <div class="dropdown-grids">



                    <div id="loginContainer">
                        <a href="#" id="loginButton"><span>Login</span></a>
                        <div id="loginBox">
                            <form id="loginForm" class="formValidate" action="login"
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
    <div class="bannerticket">
        <!-- container -->
        <div class="container">
            <div class="wrapper pad1">
              <article class="col2book">
                    <div class="banner-right">
                        <div class="sap_tabs">

                            <div id="horizontalTab"
                                style="display: block; width: 100%; margin: 0px;">
                                <span id="tripType" style = "font-size: 20px; font-weight:800; color: white">one Trip</span>
                         
                               <table>
                                                                <tr style="font-weight:bold; color: white">
                                                                <td style="width:17%">Flying from</td>
                                                                <td style="width:17%">Flying to</td> 
                                                                <td  style="width:11%">Departure</td> 
                                                                <td style="width:11%">Return</td> 
                                                                <td style="width:18%">AirLine</td> 
                                                                <td style="width:10%">Stops</td> 
                                                                <td style="width:16%">Cabin</td>
                                                                </tr>
                                                                
                                                                <tr>
                                                                    <td style="width:17%"><input
                                                                                value="${flightFrom }"
                                                                                type="text" placeholder="Type Departure City"
                                                                                class="typeahead1 input-md form-control tt-input inputbook"
                                                                                id="flightFrom1"
                                                                                required=""></td>
                                                                    <td style="width:17%"><input
                                                                                value="${flightTo }" type="text"
                                                                                placeholder="Type Destination City"
                                                                                class="typeahead1 input-md form-control tt-input inputbook"
                                                                                id="flightTo1"
                                                                                required="">
                                                                    </td>            
                                                                                
                                                                    <td style="width:11%"> <input id="dptTime1" required="" type="date"  class="inputbook"
                                                                                value="<%=request.getAttribute("dptTime")%>"
                                                                                >
                                                                    </td>   
                                                                    <td style="width:11%"><input type="date" id="rtnTime1" style="width:100%"  class="inputbook"
                                                                                    value="${rtnTime}" 
                                                                                    >
                                                                    </td>  
                                                                                    
                                                                     <td style="width:22%"> <select id="airline1" style="width:100%"
                                                                                    
                                                                                    class="frm-field required inputbook">
                                                                                    <option value="Any">Any</option>
                                                                                    <option value="Alaska Airline">Alaska Airline</option>
                                                                                    <option value="American Airline">American Airline</option>
                                                                                    <option value="United Airline">United Airline</option>
                                                                                    <option value="Delta Airline">Delta Airline</option>
                                                                                </select></td>
                                                                     
                                                                     <td style="width:6%" ><select id="stops1" style="width:100%"
                                                                                    
                                                                                    class="frm-field required inputbook" >
                                                                                    <option value="Any">Any</option>
                                                                                    <option value="0">0</option>
                                                                                    <option value="1">1</option>
                                                                                    <option value="2">2</option>

                                                                                </select></td>
                                                                     <td style="width:16%"><select id="carbinLevel1"  style="width:100%"
                                                                                
                                                                                    class="frm-field required inputbook">
                                                                                    <option value="Economy">Economy</option>
                                                                                    <option value="Business">Business</option>
                                                                                </select></td>       
                                                                                    
                                                                </tr>
                                                            </table>    
                                    
                                
                            </div>
                        </div>
                    </div>

                </article>
                <article class="col1book">
                    <div class="box1">
                        <h2 class="top">Flight Info</h2>
                        
                        <span><b  id="diplayDay">${dptTime}</b></span>  <span style = "float:right"><b id="diplayBackDay">${rtnTime }</b></span>
                        <div class="div_pad_book" id="tableParent">
                            <%
                                for (int i = 0; i < lines.size(); i++) {
                                    int availableSeats = 1000;
                                    int totalPrice = lines.get(i).getValue();
                                    
                                    %>
                                    <table class="tickettablebook">
                                    <%
                                    for (int j = 0; j < lines.get(i).getKey().size(); j++) {
                                        Flight flight = lines.get(i).getKey().get(j);
                                        int price = (int) (flight.getEcoPrice() * flight.getEcoDiscount());

                                        String companyAndId = flight.getFlightCompany() + "&" + flight.getFlightId();
                                        String dptTime = flight.getDepartureTime().substring(11, 16);

                                        String arvTime = flight.getArriveTime().substring(11, 16);
                                        if (Integer.parseInt(arvTime.substring(0, 2)) < Integer.parseInt(dptTime.substring(0, 2))) {
                                            arvTime += "  + 1 day";
                                        }
                                        String dptBrief = flight.getDepartureCityBrief();
                                        String arvBrief = flight.getArriveCityBrief();
                                        String dptCity = flight.getDepartureCity();
                                        String arvCity = flight.getArriveCity();
                                        availableSeats = Math.min(availableSeats, flight.getEcoSeats() - flight.getReservedEcoSeats());
                                        long serialId = flight.getSerialId();
                                        String idClass = "id"+i;
                                        
                                        
                            %>
                                     <input type='hidden'  value='<%=serialId%>'  class='<%=idClass%>' />
                                <tr>
                                    
                                    <td style="text-align: left" class="tableleft"><b><%=companyAndId%></b></td>
                                    
                                    <td class="tableright"><b>Price</b></td>
                                </tr>
                                <tr>
                                    <td><table style="text-align: left"
                                            class="innertable ticketinfo">
                                            <tr>
                                                <td class="dpttime"><%=dptTime%></td>
                                                <td style="text-align: left"></td>
                                                <td class="arvtime"><%=arvTime%></td>
                                            </tr>
                                            <tr>
                                                <td class="dptport"><%=dptBrief%></td>
                                                <td>To</td>
                                                <td class="arvport"><%=arvBrief%></td>

                                            </tr>
                                            <tr>
                                                <td class="dptcity"><%=dptCity%></td>
                                                <td></td>
                                                <td class="arvcity"><%=arvCity%></td>
                                            </tr>
                                        </table></td>
                                    <td>$<%=price%></td>
                                </tr>

                            <%
                                }
                            %>
                            </table>
                            <span><b>Total Price: $<%=totalPrice%></b></span>
                            <%if(availableSeats < 5){ 
                            String value = "<=  " + availableSeats;%>
                            <button style="float: right; margin-left: 20px; width: 80px"  id='id<%=i%>'  class="button1"><%=value%></button>
                            <%}else if(availableSeats < 15) {%>
                            <button style="float: right; margin-left: 20px ;width: 80px" id='id<%=i%>' class="button1">Tight</button>
                            <%}else{ %>
                            <button style="float: right; margin-left: 20px; width: 80px" id='id<%=i%>'  class="button1">Lots</button>
                            <%} %>
                            <HR style="border: 1 dashed #8a5bc0" width="85%" color=#987cb9
                                SIZE=1>

                            <%
                                }
                            %>


                        </div>

                    </div>
                </article>
              
            </div>
        </div>
        <!-- //container -->
    </div>
    <!-- //banner -->

    <script defer src="js/jquery.flexslider.js"></script>
    <!-- <script src="js/easyResponsiveTabs.js" type="text/javascript"></script> -->
    <script src="js/jquery-ui.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
    <script type="text/javascript" src="js/filter.js"></script>
    <script type="text/javascript" src="js/booking.js"></script>

</body>
</html>