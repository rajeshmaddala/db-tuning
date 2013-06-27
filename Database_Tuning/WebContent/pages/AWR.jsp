<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>On Demand Reports</title>
<script language="JavaScript" src="<%=request.getContextPath()%>/js/ts_picker.js"></script>
<script language="JavaScript" type="text/javascript">
function setAction(value)
{
	alert(value+" is selected");
}

	$(function() {  
	 
	  $(".button").click(function() { 
		  
			var dataString = "report_param="+$('#report_param').val()+"&stDate="+$('#stDate').val()+"&endDate="+$('#endDate').val();
			//alert (dataString);return false;  
			$.ajax({  
			  type: "POST",
			  url: "../Controller?action=Generate_Reports",  
			  data: dataString,  
			  success: function(data) {  	
				 // alert(data);
			    if (data.indexOf("successfully_generated") >= 0){
			    	 $('#Alert').html("<h3><font color='green'>Report generated successfully!</font></h3>");
			    }
			    else{
			    	$('#Alert').html("<h3><font color='red'>Report could not be generated successfully!</font></h3>");
			    }
			    	 
			    
			  },
			error: function(){
				$('#Alert').text("Unable to send request to server");
			}
			});  
			return false; 
	  });  
	}); 



</script>
</head>
<body>

<%String msg = " ";
//if(request.getAttribute("msg") ==null)
//{
%> 
<br><br><br>

<form name="AWR" id="AWR" action="">
	<input type="radio" name="report_param" id="report_param" value="Dictionary_Cache_Reports" />Dictionary Cache Hit Rate
 	&nbsp;&nbsp;&nbsp;
	<input type="radio" name="report_param" id="report_param" value="Buffer_Cache_Reports"/>Buffer Cache Hit Rate
	<br></br>
	<label>Enter start Date:</label><input type="Text" name="stDate" id=stDate value=""/>
	<a href="javascript:show_calendar('document.AWR.stDate', document.AWR.stDate.value);">
	<img src="<%=request.getContextPath()%>/images/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a><br><br>
	<label>Enter end Date:</label><input  type="Text" name="endDate" id=endDate value=""/>
	<a href="javascript:show_calendar('document.AWR.endDate', document.AWR.endDate.value);"><img src="<%=request.getContextPath()%>/images/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the timestamp"></a>
<br><br><br><input type="submit" value="Submit" class="button"/><br><br><br><br>
</form>
<div id="Alert"></div><br><br>



<%//}
//else{
	//msg = (String)request.getAttribute("msg");
	//if(msg.equalsIgnoreCase("successfully_generated")){
		//out.println("<p><font color='blue'>Report Generated Successfully</font></p>");
		//out.println("<a href='/index.jsp'>Goto Homepage</a>");
	//}
//}
%>


</body>
</html>