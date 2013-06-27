package DbTuning.Action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DbTuning.Bean.ThresholdConfig.ThresholdConfigBean;
import DbTuning.dao.Thresholddao;


public class ThesholdConfig {

Thresholddao tcDao = new Thresholddao(); // used by all methods to call DAO class

	public void displayThresholdConfig(HttpServletRequest request,
			HttpServletResponse response)  throws ServletException, IOException {
		ThresholdConfigBean threshold_bean = tcDao.getThesholdConfig();
		request.setAttribute("threshold_bean", threshold_bean);
		request.setAttribute("msg", "displayThresholdConfig");
		displayResult(request, response);
	}
	
public void updateThresholdConfig(ArrayList<String> param, ArrayList<Float> minvalue, ArrayList<Float> maxvalue,HttpServletRequest request,
		HttpServletResponse response)  throws ServletException, IOException {
	
		int success=tcDao.updateThresholdConfig(param,minvalue,maxvalue);
		System.out.println("status is : "+ success);
		request.setAttribute("updation", success);
		request.setAttribute("msg", "updation");
		displayResult(request, response);
		
	}
	
	private void displayResult(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String r = request.getAttribute("msg").toString();
		System.out.println(r);	
		forwardToLocation("pages/Threshold.jsp", request,response);
		
		}


	/*
	 * This method is called by displayresult() method to forward the response
	 * to various pages.
	 * 
	 * @param url , request ,response
	 * 
	 * @return void
	 * 
	 * exception ServletException,IOException for failed or interrupted I/O
	 * operations
	 */
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
