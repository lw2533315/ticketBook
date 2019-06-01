/**
 * 
 */

function fillCardInfo(cardNumber, name, expireMonth,expireYear, cvv ){
	
		$("#cardNumber").val(cardNumber);
		
		$("#cardName").val(name);
		$("#year").val(expireYear);
		$("#month").val(expireMonth);
		$("#cvv").val(cvv);
	
	
	
}


//fill the default card info by choose checked info 
$("#defaultCheckbox").click(function() {
	var paymentCard = [];
	
	if($(this).is(":checked")){
		$.get("bankInfo", function(back){
			paymentCard = JSON.parse(back);
			if(paymentCard != null && paymentCard["cardNumber"] != null){
				console.log(paymentCard);
				let cardNumber = paymentCard['cardNumber'];
				let expireMonth = paymentCard['expireMonth'];
				console.log(expireMonth)
				let expireYear = paymentCard['expireYear'];
				console.log(expireYear);
				let cvv = paymentCard['cvv'];
				let nameOnCard = paymentCard["nameOnTheCard"]
				
				fillCardInfo(cardNumber, nameOnCard, expireMonth, expireYear, cvv);
			}else{
				fillCardInfo("", "", "", "", "");
				$("#defaultCard").text("Not Set Default Card Yet")
			}
			
			
		})
	}else{
		console.log("not check");
		$("#defaultCard").text("")    //clear the error message
		$("#cardNumber").val("");
		$("#cardName").val("");
		$("#year").val("");
		$("#month").val("");
		$("#cvv").val("")
	}
	
})