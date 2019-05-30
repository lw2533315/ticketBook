/**
 * 
 */

function fillCardInfo(cardNumber, firstName, lastName, expireYear, expireMonth, cvv ){
	if(cardNumber === ''){
		$("#cardNumber").val("");
		$("#cardName").val("");
		$("#expireYear").val("")
		$("#expireMonth").val("")
		$("#cvv").val("");
	
	}else{
		$("#cardNumber").val(cardNumber);
		let cardName = firstName + " " + lastName;
		$("#cardName").val(cardName);
		$("#year").val(expireYear);
		$("#month").val(expireMonth);
		$("#cvv").val(cvv);
	}
	
	
}


//fill the default card info by choose checked info 
$("#defaultCheckbox").click(function() {
	console.log("listener check box !!!");
	var paymentCard = [];
	var firstName = $("#passFirstName").val();
	var lastName =$("#passLastName").val();
	console.log(firstName);
	console.log(lastName);
	if($(this).is(":checked")){
		$.get("bankInfo", function(back){
			paymentCard = JSON.parse(back);
			if(paymentCard != null){
				console.log(paymentCard);
				let cardNumber = paymentCard['cardNumber'];
				let expireMonth = paymentCard['expireMonth'];
				console.log(expireMonth)
				let expireYear = paymentCard['expireYear'];
				console.log(expireYear);
				let cvv = paymentCard['cvv'];
				
				fillCardInfo(cardNumber, firstName, lastName, expireYear, expireMonth, cvv)
			}else{
				fillCardInfo("", "", "", "", "", "");
			}
			
			
		})
	}else{
		console.log("not check");
		$("#cardNumber").val("");
		$("#cardName").val("");
		$("#year").val("");
		$("#month").val("");
		$("#cvv").val("")
	}
	
})