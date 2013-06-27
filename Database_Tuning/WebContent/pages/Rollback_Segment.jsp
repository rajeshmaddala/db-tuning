<%@page import="DbTuning.Bean.Rollback_Segment.Rollback_SegmentBean"%>
<%@page import="DbTuning.Bean.RedoLog.RedoLogSpaceWaitBean"%>
<%@page import="DbTuning.Bean.RedoLog.RedoLogSwitchBean"%>
<%@page import="DbTuning.Bean.RedoLog.RedoLogBufferEntryBean"%>
<%@page import="DbTuning.Bean.ThresholdConfig.ThresholdConfigBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="DbTuning.Bean.SharedPool.SharedPoolBean" %>
<%@page import="java.util.ArrayList" %>  

<%				
				String msg = (String)request.getAttribute("msg");
				Rollback_SegmentBean rsb=null;
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
					
					if(msg.contains("Wait")){
						rsb=(Rollback_SegmentBean)request.getAttribute("rollback_segment2");
						//value=shared_pool.getReload_ratio();
						param="Rollback Segment Wait Statistics";
							
					}
					else if(msg.contains("Hit")){
						rsb=(Rollback_SegmentBean)request.getAttribute("rollback_segment1");
						value=rsb.getRollback_hit_ratio();
						param="Rollback Segment Hit Ratio";
						tcb=rsb.getTcb();
						min=tcb.getMin_healthy();
						max=tcb.getMax_healthy();
						redFrom=min;
						redTo=2;
						yellowFrom=max;
						yellowTo=min;
						greenFrom=0;
						greenTo=max;
						minv=0;
						maxv=2;
						minorTicks=5;
						majorTicks[0]="0";
						majorTicks[1]="0.5";
						majorTicks[2]="1";
						majorTicks[3]="1.5";
						majorTicks[4]="2";
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
		var shared_hit_value ="<%=rsb.getRollback_hit_ratio()%>";
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


<%if(param.contains("Hit")) {%>
      <table>
        <tr>
        <td>
       <p> </p>
<td><div id="chart_div"></div>
    </td>    </tr>
      </table>
       <% }%>
 <%if(param.contains("Wait")){ %>
 <br><br>
 <table class='CSSTableGenerator' border="1" align="center">
			<tr align="center">
			<th>Class</th>
			<th>Count</th>
			</tr>
				<%ArrayList<Rollback_SegmentBean> rollbackList = rsb.getRollback_hit_rate_List();
					int size = rollbackList.size();
					int srno=0;
					if(size > 0){
						for (int i = 0; i < size; i++) {
							Rollback_SegmentBean bean = rollbackList.get(i);%>
						
			<tr align="center">	
			<td><%=rollbackList.get(i).getRollback_class()%></td>
			<td><%=rollbackList.get(i).getCount()%></td>								
			</tr>
		
							
							<%} }%>
								</table><br><br>
     
      <%} %>
