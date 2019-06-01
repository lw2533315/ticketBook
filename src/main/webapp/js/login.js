/**
 * 
 */



//if ($.validator) {
//    console.log("sdfdfsdfd");
//}


//check 16 bits credit card
$.validator.addMethod("bankCard", function(value, element) {
 return this.optional(element) || /^[0-9]{16}$/.test(value);
}, "16 bits digits");


//check email
$.validator.addMethod( "email", function( value, element ) {
	return this.optional( element ) || /^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i.test( value );
}, "email invalid" );


//check password
$.validator.addMethod("PASSWORD", function (value, element) {
    return this.optional(element) || /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,16}$/i.test(value);
}, "6-16 uppercase letters, lowercase letters and at least one number."); 

//check confirmpassword
$.validator.addMethod("CONFIRMPASSWORD", function (value, element, param) {
    return this.optional(element) || value == $(param).val()
},"two times passwords dose not match ");

//check letters
$.validator.addMethod("lettersonly", function(value, element) {
	  return this.optional(element) || /^[a-zA-Z]+$/i.test(value);
	}, "Letters only please"); 

//check letters and space
$.validator.addMethod( "alphaAndspace", function( value, element ) {
	return this.optional( element ) || /^[a-zA-Z\s]*$/.test( value );
}, "character and whitespace only" );

//check phonenumber
$.validator.addMethod("phonenumber", function(value, element) {
    return this.optional(element) || value.match(/^[0-9,\+-]+$/);
}, "Please enter a valid number");

//compare specific string
$.validator.addMethod("gender", function(value, element) {
    return this.optional(element) || value.match("female") || value.match("male");
}, "Please male/female");

//check number between [a, b]
$.validator.addMethod("agecheck", function(value, element) {
    return this.optional(element) || parseInt(value) > 0 && parseInt(value) < 100;
}, "Please 1 - 99");


//check month
$.validator.addMethod("monthCheck", function(value, element) {
    return this.optional(element) || parseInt(value) >= 1 && parseInt(value) <= 12;
}, "Please 1-12");



//check year
$.validator.addMethod("yearCheck", function(value, element) {
    return this.optional(element) || parseInt(value) > 2019 && parseInt(value) < 2049;
}, "Please 2019-2049");


$.validator.addMethod("cvvCheck", function(value, element) {
	 return this.optional(element) || /^[0-9]{3}$/i.test(value);
	}, "3 bits digits");


$(function(){
	console.log("test");
	$(".formValidate").validate({

		rules:{
			
			email:{
				required:true,
				email: true
			},
			
			password:{
				required: true,
				PASSWORD: true
			},
			
			password:{
				required: true,
				PASSWORD: true
			},
			
			confirmpassword:{
				PASSWORD: true,
				CONFIRMPASSWORD: '#password1',
			},
			
			firstName:{
				required:true,
				lettersonly:true,
			},
			
			lastName:{
				required:true,
				lettersonly:true,
			},
			
			phone:{
				required:true,
				phonenumber:true,
			},
			
			age:{
				required:true,
				phonenumber:true,
				agecheck: true,
			},
			
			cardNumber:{
				required:true,
				bankCard: true,
			},
			
			cardName:{
				required:true,
				alphaAndspace: true,
			},
			
			month:{
				required:true,
				monthCheck: true,
				
			},
			
			year:{
				required:true,
				yearCheck:true,
			},
			
			cvv:{
				required:true,
				cvvCheck:true,
			}
		}
	
	
	})
	
	
	$(".formValidate1").validate({

		rules:{
			
			
			
			
			password:{
				required: true,
				
				PASSWORD: true
			}
		},
//		errorElement : 'div',
//	    errorLabelContainer: '.errorTxt'
	})
	
	//one page has more then one form table
	$(".formValidate2").validate({

		rules:{
			
			phone:{
				required:true,
				phonenumber:true,
				
			},
			
			
			password:{
				required: true,
				
				PASSWORD: true
			}
		},
	})
	
	$(".formValidate3").validate({

		rules:{
			
			
			password:{
				required: true,
				
				PASSWORD: true
			}
		},
	})
	
	$("#register").click(function(){
		console.log($("#password1").val())
		
		
	})
	
	
	
	var cities = [];
//	set flight from and to auto complete
	$("#flightFrom").bind('input oninput',  function(){
		
		$.get("autoAddress", function(back){
			cities = JSON.parse(back);
			
			$("#flightFrom").autocomplete({
				source:cities
				
			});
		});
	});
//	
	$("#flightTo").bind('input oninput',  function(){
		
		
			$("#flightTo").autocomplete({
				source:cities
				
		
		});
	});
	
	
	//prevent submit event and test email and password match or not ,if match jump
	$("#login").click(function(){
		event.preventDefault();    //prevent submit event
		let email = $("#email").val();
		let password = $("#password").val();
		$.get("loginmatch?email="+email+"&password="+password, function(back){
			
			if(back === "yes"){
				window.location.href="login?url=index";
			}else{
				$("#matchCheck").text("email or password not match");
			}
		})
		
	});
	
	
	
	
	//set color login.jsp search button
	$(".formButton").mouseenter(function(){
		$(this).css("background", "#6fd508");
		$(this).css("color", "white");
	})
	$(".formButton").mouseleave(function(){
		$(this).css("background", "#DDDDDD");
		$(this).css("color", "black");
	})
	
	
	//set booking.jsp book line button
	$(document).on("mouseenter", ".button1", function(){
		$(this).css("background", "#00CC00");
	})
	$(document).on("mouseleave", ".button1", function(){
		$(this).css("background", "#3399CC");
	})
	
	
	
})



