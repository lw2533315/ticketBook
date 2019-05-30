/**
 * 
 */
$(function(){
	
	//update email
	$(".formValidate").on('submit', function(){
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
		console.log(phone);
       	let id = $("#id").text();
       	console.log("work")
       	if(/^[0-9,\+-]+$/.test(phone)){   //preentDefault need to check the value is match or not
       	$.get("updatePhone?phone="+phone+"&id="+id, function(){
       		console.log("insert value")
       		console.log(phone)
       		$("#changedPhone").text(phone);
       		return false;
       	})}
	})
	
	//update password
	$(".formValidate3").on('submit', function(){
		event.preventDefault();
		let firstTime = $("#firstTime").val();
		let secondTime = $("#secondTime").val();
		console.log(firstTime);
       	let id = $("#id").text();
       	console.log(secondTime)
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
	
	
	let cardNumber = $("#passCardNumber").val().substring(12,16);
	let cardNewNumber = "************" + cardNumber;
				console.log("card number" + cardNewNumber);
	$(".ticketcancel").change(function(){
		let id = $(this).val();
		console.log(id + "!!!!!!!");
		if(confirm("you could get refund of 90% of ticket price, continue ?")){
			$.get("returnTicket?id="+id, function(back){
				console.log("account js test")
				$("#"+id).empty();
				let price = parseInt(back);
				
				let cardNumber = $("#passCardNumber").val().substring(12,16);
				
				let row = "<h5 class='error'>$" +parseInt(price * .9)+ " will refund to you card (ending with" + cardNewNumber+") in 5 business days</h5>"
				$("#"+id).append(row);
			})
		}else{
			$(this).attr("checked", false);
		}
	})
	
	
	$("#updateEmail, #updatePhone, #updatePassword").mouseenter(function(){
		$(this).css("background", "#6fd508")
		$(this).css("color" ,"white")
		
	})
	
	$("#updateEmail, #updatePhone, #updatePassword").mouseleave(function(){
		$(this).css("background", "white")
		$(this).css("color","black")
	})


})

