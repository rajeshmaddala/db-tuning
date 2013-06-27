package DbTuning.Action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CreateReport;
import util.DBHelper.DateUtil;

import DbTuning.Bean.SharedPool.RecordBean;
import DbTuning.dao.Buffer_Hitdao;
import DbTuning.dao.Shared_Pooldao;


public class Generate_Report {

	DateUtil db=new DateUtil();
	Shared_Pooldao spDao=new Shared_Pooldao();
	CreateReport cr=new util.CreateReport();
	Buffer_Hitdao bhdao=new Buffer_Hitdao();
	
	public void getRecordedTime(HttpServletRequest request,
			HttpServletResponse response, String report_param) throws ServletException, IOException  {
		System.out.println(request.getParameter("stDate")+" "+request.getParameter("endDate"));
		String startDate=db.changeDate(request.getParameter("stDate"));
		String endDate=db.changeDate(request.getParameter("endDate"));
		System.out.println(startDate+" "+endDate);
		RecordBean bean=null;
		if(report_param.equalsIgnoreCase("Dictionary_Cache_Reports")){
		 bean =spDao.getDictionaryRecordBean(startDate,endDate);
		}
		else if(report_param.equalsIgnoreCase("Buffer_Cache_Reports")){
			bean=bhdao.getBufferHitRateRecordBean(startDate,endDate);
		}
		if(cr.generateReport(request,response,bean)){
		request.setAttribute("msg", "successfully_generated");
		}
		displayResult(request, response);
		
	}
	
	private void displayResult(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String r = request.getAttribute("msg").toString();
		System.out.println(r);
		PrintWriter out = response.getWriter();
		out.println(r);
		
		//forwardToLocation("pages/AWR.jsp", request,response);
	}
	
	private void forwardToLocation(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(url);

		if (null != rd) {
			System.out.println("in forwarding");
			rd.forward(request, response);
		} else {

			response.sendError(403, "No page found matching the URL:" + url);
		}
		
	}
}
