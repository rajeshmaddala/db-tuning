<%@page import="DbTuning.Bean.MemorySort.PGATargetBean"%>
<%@page import="DbTuning.Bean.MemorySort.SysPgaStatBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="org.richfaces.component.TabEncoder"%>
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
				SysPgaStatBean bean=null,bean2=null,bean1=null;
				PGATargetBean bean3=null;
				if( null !=msg ){
					//out.println("<center><font color='red'>" + msg + "</font></center>");
					if(msg.contains("Memory_sort_bean")){
							bean=(SysPgaStatBean)request.getAttribute("Memory_sort_bean");
							bean1=bean.getList().get(0);
							bean2=bean.getList().get(1);
							bean3=(PGATargetBean)request.getAttribute("Memory_sort_bean_pga");
							//out.println("<center><font color='red'>"+table_scan+" "+chained_fetch+" "+free_list+" "+cpu_parse+" "+"</font></center>");
					}
				}else{	
					out.println("<center><font color='red'>Cannot retrieve Additional Parameters </font></center>");
				}
				
%>

<script>
$(function() { 
	
	$('#mainTable').hide();
	$('#sub2parameters').empty();
		$('#sub2parameters').append('<li id=\'statWA\'><a>System Statistics for Work Area Execution</a></li>'
				 					+'<li id=\'pgaTP\'><a>PGA Target Parameter</a></li>'
				 					+'<li id=\'pgaTA\'><a>PGA Target Advice</a></li>'); 


	$('#statWA').bind('click',function(){
			$('#heading').text("System Statistics for Work Area Execution");
			$('#mainTable').show();
			$('#statTable').show();
			$('#tpTable').hide();
			$('#taTable').hide();
	});
	$('#pgaTP').bind('click',function(){
			$('#heading').text("PGA Target Parameter");
			$('#mainTable').show();
			$('#statTable').hide();
			$('#tpTable').show();
			$('#taTable').hide();
	
	});
	$('#pgaTA').bind('click',function(){
			$('#heading').text("PGA Target Advice");
			$('#mainTable').show();
			$('#statTable').hide();
			$('#tpTable').hide();
			$('#taTable').show();
	});

});
	
</script>
<div align='center'>
	<div id="heading"><br>
    <div id="mainTable">
    	
		    	
		 		<table class="CSSTableGenerator" id ='statTable' border="1" align="center">
					<tr align="center">
						<th>Name</th>
						<th>Value</th>
					</tr>
					<%ArrayList<SysPgaStatBean> list = bean1.getList();
							int size = list.size();
							int srno=0;
							if(size > 0){
								for (int i = 0; i < size; i++) {
									%>
								
					<tr align="center">	
						<td><%=list.get(i).getName()%></td>
						<td><%=list.get(i).getValue()%></td>								
					</tr>
				
									
									<%} }%>
				</table>
				
		  		 <div id="pageNavPosition"> </div>
			
 		
 			<table class="CSSTableGenerator" id ='tpTable' border="1" align="center">
				<tr align="center">
					<th>Event</th>
					<th>Count</th>
				</tr>					
				<tr align="center">	
					<td><%=bean2.getName()%></td>
					<td><%=bean2.getValue()%></td>								
				</tr>
			</table>
			

			
		
			
 			<table class="CSSTableGenerator" id ='taTable' border="1" align="center">
				<tr align="center">
					<th>TARGET_SIZE_MB</th>
					<th>BYTES_PROCESSED</th>
					<th>EST_RW_EXTRA_BYTES</th>
					<th>EST_HIT_PCT</th>
					<th>EST_OVERALLOC</th>
				</tr>
						
		<%ArrayList<PGATargetBean> pgalist = bean3.getList();
					size = pgalist.size();
					srno=0;
					if(size > 0){
						for (int i = 0; i < size; i++) {
							%>
						
				<tr align="center">	
					<td><%=pgalist.get(i).getTARGET_SIZE_MB()%></td>
					<td><%=pgalist.get(i).getBYTES_PROCESSED()%></td>								
					<td><%=pgalist.get(i).getEST_RW_EXTRA_BYTES()%></td>
					<td><%=pgalist.get(i).getEST_HIT_PCT()%></td>	
					<td><%=pgalist.get(i).getEST_OVERALLOC()%></td>
				</tr>
		
							
							<%} }%>
			</table>
			
		</div>	
	</div>		
</div>  
<br>
If the memory acquisition requires a single pass through pga_aggregate_target, the memory allocation is marked as one pass. If all memory is in use, Oracle may have to make multiple passes through pga_aggregate_target to acquire the memory. Multipass executions are an indication of memory shortage. It is important to ensure that at least 95 percent of connected tasks can acquire their memory optimally. 
Depending on the optimal values of one pass and multi pass value and cache hit percentage of PGA_TARGET, DBA changes the  <font color='red'><i>SORT_AREA_SIZE parameter </i></font>in init.ora.
<br>
<br><br>
