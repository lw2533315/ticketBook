/**
 * 
 */

$(function(){
	
	
	var availableSeats = $("#passValue").val();
	var childPrice = $("#child").val();
	var adultPrice = $("#adult").val();
	var orgSeats = 1;
	var totalSeats = 1;
	
	// watch on the adult + child number to compare available seats
	// if not valid print out notice and ban the jump of form
	// dynamic insert passenger form by the new numbers of passengers
	$("#adultNo, #childNo").change(function(){
		let adultNo = $("#adultNo").val();
		let childNo = $("#childNo").val();
		
		orgSeats = totalSeats;
		totalSeats = Number(adultNo) + Number(childNo);
		if(totalSeats > availableSeats){
			$("#seatNotice").text("no enough seats") // notice
			$("#submitPassagers").click(function(){
				return false;    // stop jump
			})
		}
		
		// update total price by the passenger change
		let price = Number(adultNo) * Number(adultPrice) + Number(childNo)*Number(childPrice);
		console.log("update price");
		$("#displayPrice").text("$" + price);
// $("#passPrice").val(price);
		
		
		
		// dynamic insert a form table to jsp, it will changed by the passenger
		// number
		var header = $("#removePoint");
		if(totalSeats > orgSeats){
		for(let i = orgSeats; i < totalSeats; i++){
			let newRow = "<div style='height: 30px'></div>";
			header.before(newRow);
			let row1 = "<h5 style='color: #CD853F; font-size:22px' class='block"+(i+1)+"' >the "+ (i+1)+"st passenger</5>"
			header.before(row1);
			
			let row2 = "<div class='block"+(i+1)+"'><p class='cofirminfo'>First Name</p><input class='mywidth' type='text' name ='firstName' value=''/></div>"
			let row3 = "<div class='block"+(i+1)+"'><p class='cofirminfo'>Last Name</p><input class='mywidth' type='text' name ='lastName' value=''/></div>"
			header.before(row2);
			header.before(row3);
			
			let row4 = "<div class='block"+(i+1)+"'><p class='cofirminfo'>Email</p><input class='mywidth' type='text' name ='email' value=''/></div>"
			let row5 = "<div class='block"+(i+1)+"'><p class='cofirminfo'>Phone</p><input class='mywidth' type='text' name ='phone' value=''/></div>"	
			header.before(row4);
			header.before(row5);
			
			let row6 = "<div class='block"+(i+1)+"'><p class='cofirminfo'>Gender</p> <select class='mywidth' name = 'gender' style='color:black; font-size: 15px' >"
                                        +"<option style= 'color:black; font-size: 15px' value ='female'>Female</option>" +
                                        "<option style= 'color:black; font-size: 15px' value='male'>Male</option>"+
                                    +"</select> </div>"
			let row7 = "<div class='block"+(i+1)+"'><p class='cofirminfo'>Age</p><input class='mywidth' type='text' name ='age' value=''/></div>"
			header.before(row6);
			header.before(row7);
		}
		
		}
		else if(totalSeats < orgSeats){
			for(let i = orgSeats; i > totalSeats; i--){
				console.log("work")
				$(".block"+i).remove();
			}
			
		}
		
		// update the child and adult passenger number
		$("#psgAdultNumbers").val(adultNo);  // set the hidden input value
												// let Controller know how many
												// passenger was passted
		$("#psgChildtNumbers").val(childNo);
			
	})
	
	
	
	
})