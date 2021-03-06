



$(function(){
	//update the rtntime by the filled dptTime11
	let time = $("#dptTime1").val();
	$("#rtnTime1").attr("min", $("#dptTime1").val() );
	
	
	//listen many filter
	$(" #dptTime1, #rtnTime1, #airline1, #stops1, #carbinLevel1").off().on("change", filter)
	
	
	//listen to flight from
	$("#flightFrom1").keyup(function(event){
		  if(event.keyCode ==13){
		    filter();
		  }
	});
	
	//listen to flight from
	$("#flightTo1").keyup(function(event){
		  if(event.keyCode ==13){
		    filter();
		  }
	});
	
	
})

//jump with flights serial ids
$(document). on('click',".button1", function(){
		//not signup , jump back origin
		if($("#signinFlag").val() === "yes"){
			window.location.href="home";
		}else{
			console.log("click button");
			let ary = [];
			let className = $(this).attr("id");
			$("."+className).each(function(){
				
				ary.push($(this).val());
			})
			
			let url = "jumpToTicket?param0="+ary.length ;
			let i = 0;
			for(; i < ary.length; i++){
				url += ("&param" + (i+1) + "=" + ary[i]);
			}
			
			//add carbin level info
			url += ("&param"+(i+1) + "="+ $("#carbinLevel1").val());
			
			
			//add check one trip or round trip
			if ($("#rtnTime1").val() != ""){
				url += ("&param"+(i+2) + "=1");
			}else{
				url += ("&param"+(i+2) + "=0");
			}
			
			//add dptcity
			url += ("&param"+(i+3) + "="+ unescape($("#flightFrom1").val()));
			
			//add rtncity
			url += ("&param"+(i+4) + "="+ $("#flightTo1").val());
			
			//add dpttime
			url += ("&param"+(i+5) + "="+ $("#dptTime1").val());
			
			//add rtntime
			url += ("&param"+(i+6) + "="+ $("#rtnTime1").val());
			
			console.log(url);
			window.location.href=url;
		}
})



function filter(){
		console.log("change")
		let cityFrom = $("#flightFrom1").val();
		let cityTo = $("#flightTo1").val();
		let dptTime = $("#dptTime1").val();
		let time = $("#dptTime1").val();
		
		//update rtnTime date input by the dptTime was changed
		$("#rtnTime1").attr("min", $("#dptTime1").val() );
		
		let rtnTime = $("#rtnTime1").val();
		let airline = $("#airline1").val();
		let stops = $("#stops1").val();
		let carbinLevel = $("#carbinLevel1").val();
		$.post("filterBooking", {
			"flightFrom" : cityFrom,
			"flightTo"  : cityTo,
			"dptTime" :dptTime,
			"rtnTime" : rtnTime,
			"airline" : airline,
			"stops" : stops,
			"carbinlevel" : carbinLevel
		}, function(back){
			var lines = JSON.parse(back);
			$("#tableParent").empty();
			$("#diplayDay").text(dptTime)   //update the time of the 
			$("#displayBackDay").text(rtnTime);
			
			var div = $("#tableParent");
			$.each(lines, function(i, line) {  // index  : (flights: price)
				let availableSeats = 1000;
				
				
				let title = "<table id='flight" + i + "' class='tickettablebook' ></table>"  ;
				div.append(title);
				let totalPrice = 0;		
					$.each(line, function(j, flight){   //index: flight
						
						let price =0;
						if(carbinLevel.toLowerCase() == "economy"){
							price= flight.ecoPrice* flight.ecoDiscount;
						}else{
							price= flight.busPrice* flight.busDiscount;
						}
						price = Math.round(price)
						totalPrice += price;
						
						let companyAndId = flight.flightCompany + "&" + flight.flightId;
						
						let dptTime = flight.departureTime.substring(11,16); 
						
						
						let arvTime = flight.arriveTime.substring(11, 16)
						
						//pass midnight day + 1 
						if (Number(arvTime.substring(0, 2)) < Number(dptTime.substring(0, 2))) {
							arvTime += "  + 1 day";
						}
						let dptBrief = flight.departureCityBrief;
						let arvBrief = flight.arriveCityBrief;
						let dptCity = flight.departureCity;
						let arvCity = flight.arriveCity;
						let serialId = flight.serialId;
						
						if(carbinLevel.toLowerCase() == "economy"){
							availableSeats = Math.min(availableSeats, flight.ecoSeats -flight.reservedEcoSeats);
						}else{
							availableSeats = Math.min(availableSeats, flight.busSeats -flight.reservedBusSeats);
						}
					    // table name
						let outTable = $("#flight"+i)
						let id="<input type='hidden' class='id"+i+"' value='"+serialId+"'/>"
						outTable.append(id);
						let row1 = "<tr><td style='text-align: left' class='tableleft' ><b>" + companyAndId+"</b></td><td class='tableright'><b>price</b></td></tr>"
						outTable.append(row1);
						let row2 = "<tr><td><table style = 'text-align : left' class='innertable ticketinfo' id='inflight"+i +"" + j+ "'><tr><td class='dpttime'>" +dptTime +"</td><td styles='text-align: left'></td><td class='arvtime'>" + arvTime+ "</td></tr><tr><td class='dptport'>" +dptBrief +"</td><td>To</td><td class='arvport'>" + arvBrief+ "</td></tr><tr><td class='dptcity'>" +dptCity +"</td><td></td><td class='arvtime'>" + arvCity+ "</td></tr></table></td><td>"+price+"</td></tr>"
						outTable.append(row2);
					})
					
					
					let outRow	
					if(carbinLevel.toLowerCase() == "economy"){
						outRow= "<span><b>Total Price: "+Math.round(totalPrice * .8)+"</b></span>";
					}else{
						outRow= "<span><b>Total Price: "+Math.round(totalPrice * .9)+"</b></span>";
					}
				div.append(outRow);
				if(availableSeats < 5){
					let value = "<= " + availableSeats;
					let outRow2 = "<button style='float: right; margin-left: 20px; width: 80px' id='id"+i+"' class='button1'>" +value+"</button>";
					div.append(outRow2);
				}else if(availableSeats < 15 ){
					let outRow2 = "<button style='float: right; margin-left: 20px; width: 80px' id='id"+i+"' class='button1'>Tight</button>";
					div.append(outRow2);
				}else{
					let outRow2 = "<button style='float: right; margin-left: 20px; width: 80px' id='id"+i+"' class='button1'>Lots</button>";
					div.append(outRow2);
				}
				let outRow3 = "<HR style='border: 1 dashed #8a5bc0'' width='85%' color=#987cb9 SIZE=1>"
					div.append(outRow3);
				
			})
		
		})	
		
}





		 