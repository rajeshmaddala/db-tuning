<%@page import="DbTuning.Bean.Rollback_Segment.Rollback_SegmentBean"%>
<%@page import="DbTuning.Bean.RedoLog.RedoLogSpaceWaitBean"%>
<%@page import="DbTuning.Bean.RedoLog.RedoLogSwitchBean"%>
<%@page import="DbTuning.Bean.RedoLog.RedoLogBufferEntryBean"%>
<%@page import="DbTuning.Bean.BufferHitRatio.BufferHitRatioBean"%>
<%@page import="DbTuning.Bean.ThresholdConfig.ThresholdConfigBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="DbTuning.Bean.SharedPool.SharedPoolBean" %>
<%@page import="java.util.ArrayList" %>  

<%				
				String msg = (String)request.getAttribute("msg");
				BufferHitRatioBean rsb=null;
				float value=0;
				String param=null;
				ThresholdConfigBean tcb=null; 
				float min=0,max=0;
				float redTo=0,redFrom=0,greenTo=0,greenFrom=0,yellowTo=0,yellowFrom=0;
				float minorTicks=0;
				String[] majorTicks = new String[5];
				int minv=0,maxv=0;
				if( null !=msg ){
					//out.println("<center><font color='red'>" + msg + "</font></center>");
					
			 if(msg.contains("buffer_hit_ratio")){
						rsb=(BufferHitRatioBean)request.getAttribute("buffer_hit_ratio");
						value=rsb.getBuffer_hit_ratio();
						param="Buffer Cache Hit Ratio";
						tcb=rsb.getTcb();
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
					
				}else{	
					out.println("<center><font color='red'>Shared_pool is null </font></center>");
				}
				
%>
<script type='text/javascript' src='https://www.google.com/jsapi'></script>
    <script type='text/javascript'>
      google.load('visualization', '1', {packages:['gauge']});
    // google.setOnLoadCallback(drawChart);
     // function drawChart() {
		var shared_hit_value ="<%=rsb.getBuffer_hit_ratio()%>";
		var data1 =parseFloat(shared_hit_value);
		var parameter="<%=param%>";
		var rdTo=parseFloat("<%=redTo%>");
		var rdFrom=parseFloat("<%=redFrom%>");
		var yTo=parseFloat("<%=yellowTo%>");
		var yFrom=parseFloat("<%=yellowFrom%>");
		var gTo=parseFloat("<%=greenTo%>");
		var gFrom=parseFloat("<%=greenFrom%>");
		var data1 =parseFloat(shared_hit_value);
		var parameter="<%=param%>";
		var mint=parseFloat("<%=minorTicks%>");
		var minv=parseFloat("<%=minv%>");
		var maxv=parseFloat("<%=maxv%>");
        var data = google.visualization.arrayToDataTable([
          ['Label', 'Value'],
          [parameter,data1]
        ]);
        var options = {
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

       // var chart = new google.visualization.Gauge(document.getElementById('chart_div'));
      //  chart.draw(data, options);
    //  }
    </script>


<%if(param.contains("Buffer Cache Hit Ratio")) {%>
      <table>
        <tr>
        <td>
       <p> </p>
<td><div id="chart_div"></div>
    </td>    </tr>
      </table>
       <% }%>