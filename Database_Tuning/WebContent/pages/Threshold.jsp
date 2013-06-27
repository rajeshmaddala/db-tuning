<%@page import="DbTuning.Bean.ThresholdConfig.ThresholdConfigBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>  

<%				
				String msg = (String)request.getAttribute("msg");
				ThresholdConfigBean tcb=null;
				String param=null;
				int message=0;
				if( null !=msg ){
					out.println("<br>");
					if(msg.contains("displayThresholdConfig")){
						tcb=(ThresholdConfigBean)request.getAttribute("threshold_bean");
							param="Threshold Config";
				}else if(msg.contains("updation")){
					message=Integer.parseInt((String)request.getAttribute("updation"));
					if(message==0)
						out.println("<center><font color='red'>Threshold Config cannot be updated! </font></center>");
					else
						out.println("<center><font color='green'>"+message+" number of Threshold Config updated successfully! </font></center>");
				}
				else{	
					out.println("<center><font color='red'>Shared_pool is null </font></center>");
				}
				}
				
%>

<script language="javascript" type="text/javascript">

function setCheckBox(i){
	//alert(i);
	//alert(document.getElementById(i).checked);
	var status=document.getElementById(i).checked;
	if(status==true){
		//alert("hi");
		document.getElementById("checkBox").value=document.getElementById("checkBox").value+i+":"+document.getElementById(i).value+',';
		//alert(document.getElementById("checkBox").value);
	}
}
</script>

<%if(param.contains("Threshold Config")){ %>
 <form method="post" action="<%=request.getContextPath()%>/Controller?action=Update_Config" name="config" id="config">
 <input type="hidden" id="checkBox" name="checkBox"/>
 <table class="CSSTableGenerator" border="1" align="center">
			<tr align="center">
			<th>Parameter Name</th>
			<th>Minimum Healthy Value</th>
			<th>Maximum Healthy Value</th>
			<th>Check to Edit</th>
			</tr>
<% ArrayList<ThresholdConfigBean> thresholdList = tcb.getThreshold_List();
					int size = thresholdList.size();
					int srno=0;
					if(size > 0){
						for (int i = 0; i < size; i++) {
							ThresholdConfigBean bean = thresholdList.get(i);%>
						
			<tr align="center">	
			<td><%=thresholdList.get(i).getParamter_name()%></td>
			<td><input type="text" name="min<%=i%>" id='min<%=i%>' value="<%=thresholdList.get(i).getMin_healthy()%>" /></td>	
			<td><input type="text" name="max<%=i%>" id='max<%=i%>' value="<%=thresholdList.get(i).getMax_healthy()%>" /></td>	
			<td ><input type="checkbox" name="<%=i%>" id="<%=i%>" value="<%=thresholdList.get(i).getParamter_name()%>" onchange=setCheckBox(<%=i%>)></td>							
			</tr>				
			<%} }%>
			</table><br>
			<input type="submit" value="Submit" /><br>
     </form>
      <%} %>
