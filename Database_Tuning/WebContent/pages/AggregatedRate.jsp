<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="DbTuning.Bean.SharedPool.RecordBean" %>
    <%@page import="DbTuning.Bean.ThresholdConfig.ThresholdConfigBean"%>
    <%@page import="java.util.ArrayList" %>

<%				
				String msg = (String)request.getAttribute("shared_pool");
				RecordBean recordbean=null;
				float value=0;
				String param=null;
				ThresholdConfigBean tcb=null; 
				float min=0,max=0;
				float redTo=0,redFrom=0,greenTo=0,greenFrom=0,yellowTo=0,yellowFrom=0;
				float minorTicks=0;
				String[] majorTicks = new String[5];
				int minv=0,maxv=0;
				if( null !=msg ){	
					if(msg.contains("dropdown")){
						recordbean=(RecordBean)request.getAttribute("recordBean");
						param="Dictonary Hit Rate";
						tcb=recordbean.getTcb();
						min=tcb.getMin_healthy();
						max=tcb.getMax_healthy();
						redTo=min;
						redFrom=0;
						yellowFrom=min;
						yellowTo=max;
						greenFrom=max;
						greenTo=100;
						minv=0;
						maxv=100;
						minorTicks=10;
						majorTicks[0]="0";
						majorTicks[1]="25";
						majorTicks[2]="50";
						majorTicks[3]="75";
						majorTicks[4]="100";
					}
					
				}
				
%>
 <script type='text/javascript' src='https://www.google.com/jsapi'></script>
  <script type="text/javascript">
    google.load('visualization', '1', {packages: ['gauge']});
    </script>
    <script type="text/javascript">
	var chart;
    var data;
    var options;
    //  function drawChart() {
		var rdTo=parseFloat("<%=redTo%>");
		var rdFrom=parseFloat("<%=redFrom%>");
		var yTo=parseFloat("<%=yellowTo%>");
		var yFrom=parseFloat("<%=yellowFrom%>");
		var gTo=parseFloat("<%=greenTo%>");
		var gFrom=parseFloat("<%=greenFrom%>");
		var parameter="<%=param%>";
		var mint=parseFloat("<%=minorTicks%>");
		var minv=parseFloat("<%=minv%>");
		var maxv=parseFloat("<%=maxv%>");
        data = google.visualization.arrayToDataTable([
          ['Label', 'Value'],
          [parameter,0]
        ]);
         chart = new google.visualization.Gauge(document.getElementById('content_div'));
		options = {
          width: 600, height: 220,
          redFrom: rdFrom, redTo: rdTo,
          yellowFrom: yFrom, yellowTo: yTo,
          greenFrom: gFrom,greenTo: gTo,
		  greenColor: "#00ff00",
          minorTicks:mint,
          min:minv,
          max:maxv,
          majorTicks:["<%=majorTicks[0]%>","<%=majorTicks[1]%>","<%=majorTicks[2]%>","<%=majorTicks[3]%>","<%=majorTicks[4]%>"]
        };
       //chart.draw(data, options);
    //  }
	  
	   function plot(choice) {
		   if(choice=="Select"){
			   data.setValue(0, 1,0);}
		   else{
			   data.setValue(0, 1,parseFloat(choice));
		   }
      	   chart.draw(data, options);
    	}
	   function getNextDropDown(choice) {
		   var qelement = document.getElementById("Quarterly");
		   var helement= document.getElementById("Hourly");
		  	if(choice=="quarterly")
		  	{
		  		qelement.style.visibility = "visible";
		  		helement.style.visibility = "hidden";
		  		
		  	}
		  	if(choice=="hourly")
		  	{
			     helement.style.visibility = "visible";
			     qelement.style.visibility = "hidden";
		  	}
		  	if(choice=="Select")
		  		{
		  	   helement.style.visibility = "hidden";
			     qelement.style.visibility = "hidden";
		  		}
		  	chart.draw(data, options);
		  }
	   
	   $('#QuarterlyDiv').hide();
	   $('#HourlyDiv').hide();
	   
	   $('#dropdown').change(function()
	   {
			if($(this).attr('value')=="quarterly"){
				emptyMe("options");
				$('#options').append($('#QuarterlyDiv').html());
			}
			else if($(this).attr('value')=="hourly"){
				emptyMe("options");
				$('#options').append($('#HourlyDiv').html());
			}
			else{emptyMe("options");
			}			
	   });
	   
    </script>

<table>
	<tr>
		<td>
			<div align="center">
				<select id ="dropdown" name="mydropdown">
					<option value="Select">--Select--</option>
					<option value="quarterly">Quarterly</option>
					<option value="hourly">Hourly</option>
				</select>
			</div>
        </td> 		<td><div id="options"></div>
			<div id='QuarterlyDiv'><select name="Quarterly" id="Quarterly" onchange="plot(this.value)">
			<option value="Select">--Select--</option>
			<%if(recordbean!=null){ 
				int size=recordbean.getRbean_list().get(0).getRbean_list().size();
				ArrayList<RecordBean> recList = recordbean.getRbean_list().get(0).getRbean_list();
				for(int i=0;i<size;i++){
			%>
			<option value="<%=recList.get(i).getRate()%>"><%=recList.get(i).getRecorded_time().toLocaleString()%></option>
			<%}}%>
			</select></div>
			<div id='HourlyDiv'><select name="Hourly" id="Hourly" onchange="plot(this.value)">
			<%if(recordbean!=null){ %>
			<%int size=recordbean.getRbean_list().get(1).getRbean_list().size();
				ArrayList<RecordBean> recList = recordbean.getRbean_list().get(1).getRbean_list();%>
					<option value="Select">--Select--</option>
					<%for(int i=0;i<size;i++){ %>
					<option value="<%=recList.get(i).getRate()%>"><%=recList.get(i).getRecorded_time().toLocaleString()%></option> <%} }%>
					</select>
			</div>	
		</td>
 		<td align="center"> 
			<br><br>
 		</td>
	</tr>
</table>