

//get current day time yyy/mm/dd
function getDate(param) {
  var today = param;
  var dd = today.getDate();
  var mm = today.getMonth()+1; //January is 0!
  var yyyy = today.getFullYear();

  if(dd<10) {
      dd = '0'+dd
  } 

  if(mm<10) {
      mm = '0'+mm
  } 

  today = yyyy + '-' + mm + '-' + dd;
  return today;
};




//display or hidden return date calendar
$(function(){
	
	
	//set today as the start time for date input 
	let today = getDate(new Date());
	$("#dptTime" ).attr("min", today);
	$("#rtnTime").attr("min" ,today);
	$("#dptTime1" ).attr("min", today);
	//after choose dpt time, set rtn time >= dpt time
	$("#rtnTime" ).focus(function(){
		let newToday = $("#dptTime").val();

		$("#rtnTime").attr("min", newToday );
	})
	
	
	

	


	
	if($("#passValue").val() != "" && $("#passValue").val() != null ){
		
		$(".resp-tab-content").css("display", "block");
	}
    $("#roundtrip").click(function(){
      
        $(".resp-tab-content").css("display", "block");
        
    })

    $("#onetrip").click(function(){
     
        $(".resp-tab-content").css("display", "none");
        $("#rtnTime").val("");
    })
    
    
    
})