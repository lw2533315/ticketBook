/**
 * 
 */
$(function(){
	
	//update email
	$(".formValidate1").on('submit', function(){
		
		event.preventDefault();
		let email = $("#email").val();
       	let id = $("#id").text();
       	
       	if(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i.test(email)){
	       	$.get("updateEmail?email="+email+"&id="+id, function(){
	       		$("#changedEmail").text(email);
	       		return false;
	       	})
       	}
	})
	
	//update phone
	$(".formValidate2").on('submit', function(){
		event.preventDefault();
		let phone = $("#phone").val();
       	let id = $("#id").text();
       	if(/^[0-9,\+-]+$/.test(phone)){   //preentDefault need to check the value is match or not
       	$.get("updatePhone?phone="+phone+"&id="+id, function(){
       		$("#changedPhone").text(phone);
       		return false;
       	})}
	})
	
	//update password
	$(".formValidate3").on('submit', function(){
		event.preventDefault();
		let firstTime = $("#firstTime").val();
		let secondTime = $("#secondTime").val();
       	let id = $("#id").text();
       	if(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,16}$/i.test(secondTime)){
	       	if(firstTime === secondTime){
	       		console.log("equals")
		       	$.get("updatePassword?password="+secondTime+"&id="+id, function(){
		       		
		       		return false;
		       	})
	       	}else{
	       		$("#passwordError").text("two time not same");
	       	}
       	}
	})
	
	
	//refund ticket and print out message
	$(".ticketcancel").change(function(){
		let id = $(this).val();
		
			$.get("returnTicket?id="+id, function(back){
				$("#"+id).empty();
				let price = parseInt(back);
				
				let cardNumber = $("#passCardNumber").val().substring(12,16);
				
				let row = "<h5 class='error'>$" +parseInt(price * .9)+ " will refund to you card (ending with" + cardNumber+") in 5 business days</h5>"
				$("#"+id).append(row);
			})
		
	})
	
	
	
	//decorate update botton
	$("#updateEmail, #updatePhone, #updatePassword , #updateCard ,#cancelCard").mouseenter(function(){
		$(this).css("background", "#6fd508")
		$(this).css("color","white")
		
	})
	
	$("#updateEmail, #updatePhone, #updatePassword, #updateCard, #cancelCard").mouseleave(function(){
		$(this).css("background", "#DDDDDD")
		$(this).css("color","black")
	})
	
	
	
	//decorate the hover
	$("#cardChange").mouseenter(function(){
		$(this).attr("style", "color:#6fd508 !important; font-size:24px" )
	})
	
	$("#cardChange").mouseleave(function(){
		$(this).attr("style", "color:black !important; font-size:16px" )
	})
	
	$("#cardChange").click(function(){
		event.preventDefault();
		$("#light").attr("style", "display: block");
	})
	
	
	//pop up a new window update bank card and back to the page
	$("#updateCard").click(function(){
		event.preventDefault();
		let cn = false;
		
		let memberId = $("#memberId").val();
		let cardNumber = $("#cardNumber").val();
		
		
		let cardName = $("#cardName").val();
		let cardMonth = $("#month").val();
		let cardYear = $("#year").val();
		let cardCvv = $("#cvv").val();
		if(memberId != "" && /^[0-9]{16}$/.test(cardNumber) && /^[a-zA-Z\s]*$/.test(cardName) && (parseInt(cardMonth) >= 1 && parseInt(cardMonth) <= 12) && (parseInt(cardYear) > 2019 && parseInt(cardYear) < 2049) && /^[0-9]{3}$/i.test(cardCvv))
			$.get("updateBankCard?memberId="+memberId+"&cardNumber="+cardNumber+"&cardName="+cardName+ "&month="+cardMonth+ "&year="+cardYear+ "&cvv=" + cardCvv, function(back){
				$("#light").attr("style", "display: none");
				console.log("fill")
				$("#newCard").text("Card Number: *" + cardNumber.substring(12,16) );
			})
		else{
			$("#emptyInput").text("InValid card info")
		}
	})
	
	//back to the origin layer
	$("#cancelCard").click(function(){
		event.preventDefault();
		$("#light").attr("style", "display: none");
	})
	


})

