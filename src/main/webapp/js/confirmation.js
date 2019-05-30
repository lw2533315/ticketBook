/**
 * 
 */


//if it is round trip modified the display in jsp
$(function(){
	let price  = $("#passPrice").val();
	console.log(price);
	
	//need check undefined ,since the jsp do not sent any value the 
	//js still works
	if(typeof(price) != 'undefined'){
		let dptTime = $("#passDptDay").val();
		
		let dptCity = $("#passDptCity").val();
		let arvCity = $("#passArvCity").val();
		let carbin = $("#passCarbin").val();
		console.log(carbin);
		
		$("#backCarbin").text(carbin);
		$("#backTrip").text("Round Trip");
		$("#backCityFrom").text(dptCity);
		$("#backCityTo").text(arvCity);
		$("#backTime").text(dptTime);
		$("#oneTripPrice").text("");
		$("#roundTripPrice").text("$" + price);
		
		}

})
