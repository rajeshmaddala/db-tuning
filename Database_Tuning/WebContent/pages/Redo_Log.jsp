<%@page import="DbTuning.Bean.RedoLog.RedoLogSpaceWaitBean"%>
<%@page import="DbTuning.Bean.RedoLog.RedoLogSwitchBean"%>
<%@page import="DbTuning.Bean.ThresholdConfig.ThresholdConfigBean"%>
<%@page import="DbTuning.Bean.RedoLog.RedoLogBufferEntryBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="DbTuning.Bean.SharedPool.SharedPoolBean" %>
<%@page import="java.util.ArrayList" %>  
<style type="text/css">    
            .pg-normal {
                color: black;
                font-weight: normal;
                text-decoration: none;    
                cursor: pointer;    
            }
            .pg-selected {
                color: black;
                font-weight: bold;        
                text-decoration: underline;
                cursor: pointer;
            }
        </style>
<%				
				String msg = (String)request.getAttribute("msg");
				RedoLogBufferEntryBean rlbe=null;
				RedoLogSwitchBean rlsb=null;
				RedoLogSpaceWaitBean rlswb=null;
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
					if(msg.contains("Buffer")){
							rlbe=(RedoLogBufferEntryBean)request.getAttribute("RedoLogBufferEntryRatio");
							value=rlbe.getRedo_buffer_entries_ratio();
							param="Redo Log Buffer Entry Ratio";
							tcb=rlbe.getTcb();
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
					else if(msg.contains("Switch")){
						rlsb=(RedoLogSwitchBean)request.getAttribute("RedoLogSwitch");
						//value=shared_pool.getReload_ratio();
						param="Redo Log Switch";
					}
					else if(msg.contains("Space")){
						rlswb=(RedoLogSpaceWaitBean)request.getAttribute("RedoSpaceWaitRatio");
						value=rlswb.getWait();
						param="Redo Log Space Wait Ratio";
						tcb=rlswb.getTcb();
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
<!-- <script type='text/javascript' src='https://www.google.com/jsapi'></script> -->
   <script src="<%=request.getContextPath()%>/js/paging.js"></script>
    <script type='text/javascript'>
      //google.load('visualization', '1', {packages:['gauge']});
      //google.setOnLoadCallback(drawChart);
      //function drawChart() {
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

        //var chart = new google.visualization.Gauge(document.getElementById('chart_div'));
       // chart.draw(data, options);
     // }
    </script>


<%if(param.contains("Space") || param.contains("Buffer")) {%>
      <table>
        <tr>
        	<td>
       		<p> </p>
        	</td>
        </tr>
      </table>
       <% }%>
 <%if(param.contains("Switch")){ %>
 <div align='center'>
 <table class="CSSTableGenerator" id ='results' border="1" align="center">
			<tr align="center">
			<th>Day</th>
			<th>Hour</th>
			<th>No.of Switches</th>
			</tr>
<%ArrayList<RedoLogSwitchBean> redoList = rlsb.getRedo_log_switch_list();
					int size = redoList.size();
					int srno=0;
					if(size > 0){
						for (int i = 0; i < size; i++) {
							RedoLogSwitchBean bean = redoList.get(i);%>
						
			<tr align="center">	
			<td><%=redoList.get(i).getDay()%></td>
			<td><%=redoList.get(i).getHour()%></td>	
			<td><%=redoList.get(i).getTotal()%></td>							
			</tr>
		
							
							<%} }%>
</table>
<br></br>
   <div id="pageNavPosition"> </div>
			
				</div>  
      <%} %>
      
 <script type="text/javascript"><!--
    var pager = new Pager('results', 10); 
    pager.init(); 
    pager.showPageNav('pager', 'pageNavPosition'); 
    pager.showPage(1);
//--></script>      