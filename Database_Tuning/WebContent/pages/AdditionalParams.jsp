
<%@page import="org.richfaces.component.TabEncoder"%>
<%@page import="DbTuning.Bean.AdditionalParameters.WaitTimesBean"%>
<%@page import="DbTuning.Bean.AdditionalParameters.AdditionalParamBean"%>
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
				AdditionalParamBean adbean=null;
				WaitTimesBean wtbean=null;
				float cpu_parse=0,chained_fetch=0,free_list=0,table_scan=0;
				if( null !=msg ){
					//out.println("<center><font color='red'>" + msg + "</font></center>");
					if(msg.contains("Additional Parameter List")){
							adbean=(AdditionalParamBean)request.getAttribute("additional_param");
							table_scan=adbean.getSf_scan_ratio();
							chained_fetch=adbean.getChained_fetch_ratio();
							free_list=adbean.getFree_list_contention();
							cpu_parse=adbean.getCpu_parse_overload();
							wtbean=adbean.getWtbean();
							//out.println("<center><font color='red'>"+table_scan+" "+chained_fetch+" "+free_list+" "+cpu_parse+" "+"</font></center>");
					}
				}else{	
					out.println("<center><font color='red'>Cannot retrieve Additional Parameters </font></center>");
				}
				
%>
<script type='text/javascript' src='https://www.google.com/jsapi'></script>
   <script src="<%=request.getContextPath()%>/js/paging.js"></script>
   <script type='text/javascript'>
      google.load('visualization', '1', {packages:['gauge']});
     // google.setOnLoadCallback(drawChart);
     // function drawChart() {
    	var shared_hit_value ="<%=table_scan%>";
		var data1 =parseInt(shared_hit_value);
        var data = google.visualization.arrayToDataTable([
          ['Label', 'Value'],
          ["Table Scan",<%=table_scan%>]
        ]);
        var options = {
          width: 600, height: 220,
          redFrom: 0, redTo: 60,
          yellowFrom:60, yellowTo: 90,
          greenFrom: 90,
		   greenTo: 100,
		   greenColor: "#00ff00",
          minorTicks: 10,
          majorTicks:["25","50","75","100"]
        };

        var chart = new google.visualization.Gauge(document.getElementById('table_scan'));
       chart.draw(data, options);
       
       //draw for chained fetch ratio
       data.setValue(0, 0,"Chained Fetch Ratio");
       data.setValue(0,1,<%=chained_fetch%>);
       options.redFrom=1;
       options.redTo=2;
       options.greenFrom=0;
       options.greenTo=0.5;
       options.yellowFrom=0.5;
       options.yellowTo=1;
       options.minorTicks=5;
       options.min=0;
		options.max=2;
		options.majorTicks[0]="0";
		options.majorTicks[1]="0.5";
		options.majorTicks[2]="1";
		options.majorTicks[3]="1.5";
		options.majorTicks[4]="2";
       chart=new google.visualization.Gauge(document.getElementById('chained_fetch'));
       chart.draw(data, options);
       
       //draw for free list contention
       data.setValue(0, 0,"Free List Contention");
       data.setValue(0,1,<%=free_list%>);
       options.redFrom=1;
       options.redTo=2;
       options.greenFrom=0;
       options.greenTo=0.5;
       options.yellowFrom=0.5;
       options.yellowTo=1;
       options.minorTicks=5;
       options.min=0;
		options.max=2;
		options.majorTicks[0]="0";
		options.majorTicks[1]="0.5";
		options.majorTicks[2]="1";
		options.majorTicks[3]="1.5";
		options.majorTicks[4]="2";
       chart=new google.visualization.Gauge(document.getElementById('free_list'));
       chart.draw(data, options);
       
       //draw for CPU Parse load
       data.setValue(0, 0,"CPU Parse Overload");
       data.setValue(0,1,<%=cpu_parse%>);
       options.redFrom=8;
       options.redTo=10;
       options.greenFrom=0;
       options.greenTo=2;
       options.yellowFrom=2;
       options.yellowTo=8;
       options.minorTicks=5;
       options.min=0;
		options.max=10;
		options.majorTicks[0]="2";
		options.majorTicks[1]="4";
		options.majorTicks[2]="6";
		options.majorTicks[3]="8";
		options.majorTicks[4]="10";
       chart=new google.visualization.Gauge(document.getElementById('cpu_parse'));
       chart.draw(data, options);
    // }
    </script>
<div align='center'>
    <table>
    <tr><td><div id='table_scan'></div></td>
   <td><div id='chained_fetch'></div></td>
   <td><div id='free_list'></div></td>
   <td><div id='cpu_parse'></div></td>
   </tr>
   </table>
<h2>Session Wait Times</h2>
 <table class="CSSTableGenerator" id ='results' border="1" align="center">
			<tr align="center">
			<th>Event</th>
			<th>Count</th>
			</tr>
<%ArrayList<WaitTimesBean> wtbean_List = wtbean.getWtbean();
					int size = wtbean_List.size();
					int srno=0;
					if(size > 0){
						for (int i = 0; i < size; i++) {
							%>
						
			<tr align="center">	
			<td><%=wtbean_List.get(i).getEvent()%></td>
			<td><%=wtbean_List.get(i).getCount()%></td>								
			</tr>
		
							
							<%} }%>
</table>
<br></br>
   <div id="pageNavPosition"> </div>
			
				</div>  
      
 <script type="text/javascript"><!--
    var pager = new Pager('results', 10); 
    pager.init(); 
    pager.showPageNav('pager', 'pageNavPosition'); 
    pager.showPage(1);
//--></script>      