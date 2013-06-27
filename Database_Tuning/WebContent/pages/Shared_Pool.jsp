<%@page import="DbTuning.Bean.ThresholdConfig.ThresholdConfigBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="DbTuning.Bean.SharedPool.SharedPoolBean" %>
<%@page import="java.util.ArrayList" %>  

<%				
				String msg = (String)request.getAttribute("shared_pool");
				SharedPoolBean shared_pool=null;
				float value=0;
				String param=null;
				ThresholdConfigBean tcb=null; 
				float min=0,max=0;
				float redTo=0,redFrom=0,greenTo=0,greenFrom=0,yellowTo=0,yellowFrom=0;
				float minorTicks=0;
				String[] majorTicks = new String[5];
				int minv=0,maxv=0;
				if( null !=msg ){
					if(msg.contains("Dictionary")){
							shared_pool=(SharedPoolBean)request.getAttribute("dictionary");
							value=shared_pool.getShared_pool_hit_rate();
							param="Dictonary Hit Rate";
							tcb=shared_pool.getTcb();
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
					else if(msg.contains("Shared")){
						shared_pool=(SharedPoolBean)request.getAttribute("sharedPool");
						if(msg.contains("Reload")){
						value=shared_pool.getReload_ratio();
						param="Reload Ratio";
						tcb=shared_pool.getTcb();
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
						else if(msg.contains("Free")){
							value=shared_pool.getFree();
							param="Shared Pool Free";
							tcb=shared_pool.getTcb();
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
					}
					else if(msg.contains("Library")){
						shared_pool=(SharedPoolBean)request.getAttribute("lib_hit");
						if(msg.contains("Pin")){
						value=shared_pool.getLib_pin_hit_rate();
						param="Library Pin Hit Rate";
						tcb=shared_pool.getTcb();
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
					else if(msg.contains("Get")){
							value=shared_pool.getLib_get_hit_rate();
							param="Library Get Hit Rate";
							tcb=shared_pool.getTcb();
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
					else {
							value=shared_pool.getLib_hit_rate();
							param="Library Hit Rate";	
							tcb=shared_pool.getTcb();
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
				}else{	
					out.println("<center><font color='red'>Shared_pool is null </font></center>");
				}
				
%>
<script type='text/javascript' src='https://www.google.com/jsapi'></script>
  <script type='text/javascript'>
    	var shared_hit_value ="<%=value%>";
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

</script>
