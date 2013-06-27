package DbTuning.Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DbTuning.Bean.AdditionalParameters.AdditionalParamBean;
import DbTuning.Bean.ThresholdConfig.ThresholdConfigBean;
import DbTuning.dao.AdditionalParamDao;
import DbTuning.dao.Thresholddao;

public class Additional_Parameters {

	AdditionalParamDao apdao=new AdditionalParamDao();
	Thresholddao tcd=new Thresholddao();
	ThresholdConfigBean tcb=new ThresholdConfigBean();
	
	public void displayAdditionalParameters(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		AdditionalParamBean addParam_bean = new AdditionalParamBean();
		addParam_bean.setSf_scan_ratio(apdao.getTableScan());
		addParam_bean.setChained_fetch_ratio(apdao.getChainedFetchRatio());
		addParam_bean.setFree_list_contention(apdao.getFreeListContention());
		addParam_bean.setCpu_parse_overload(apdao.getCpuParseOverload());
		addParam_bean.setWtbean(apdao.getWaitListTimes());

		request.setAttribute("additional_param", addParam_bean);
		request.setAttribute("msg", "Additional Parameter List");
		displayResult(request, response);
		
	}

	private void displayResult(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String r = request.getAttribute("msg").toString();
		System.out.println(r);
		forwardToLocation("pages/AdditionalParams.jsp", request,response);
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
