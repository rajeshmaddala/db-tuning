<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<style>
textarea {
    width:550px;
    padding: 0;
    border: 1px solid #ccc;
    }
</style>
<script>
	/*$("form").submit(function() {
		if ($("#query").val() == "correct") {
			$("#Alert").text("Valid!").show();
			
			return true;
		}
		$("#Alert").text("Not valid!").show();
		return false;
	}); */
	
	$(function() {  
		 
		  $(".button").click(function() { 
			  $('#result').empty();
			  $('#Alert').empty();
				var query=$('#query').val().replace(new RegExp("%","g"),"<percentage>");
				//alert(query);
				if(query==""){
					$('#Alert').text("Query cannot be empty");
					return false;
				}
				
				var dataString = 'query='+ encodeURI(query);  
				//alert (dataString);return false;  
				$.ajax({  
				  type: "POST",
				  url: "../Controller?action=Debug_Interface",  
				  data: dataString,  
				  success: function(data) {  				   
				  // alert(data);
				    if (data.indexOf("TABLE") >= 0){
				    	 $('#Alert').html("<h3><font color='green'>Query Executed!</font></h3>");
				    	 $('#result').html(data);}
				    else{
				    	$('#Alert').html("<h3><font color='red'>Please check the query executed!</font></h3>");
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
<br>
<form action="">
<label>Enter your query here:</label><br>
<textarea id="query" name="query"></textarea>
<input type="Submit" value="Submit" class="button"/>
</form>

<hr>
<span id="Alert"></span>
<br>
<div id="result"></div><br><br><br><br><br><br>
