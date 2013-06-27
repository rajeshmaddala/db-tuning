package DbTuning.Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DbTuning.Bean.BufferHitRatio.BufferHitRatioBean;
import DbTuning.dao.Buffer_Hitdao;


public class Buffer_Hit {

Buffer_Hitdao bufferDao = new Buffer_Hitdao(); // used by all methods to call DAO class

	
	public void displayBufferHitRatio(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		BufferHitRatioBean buffer_bean = bufferDao.getBufferHitRatioBean();
		System.out.println(buffer_bean.getBuffer_hit_ratio());
		request.setAttribute("buffer_hit_ratio", buffer_bean);
		request.setAttribute("msg", "buffer_hit_ratio");
		displayResult(request, response);
	}

	private void displayResult(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String r = request.getAttribute("msg").toString();
		System.out.println(r);
		if ("Record added successfully".equals(r)) {
			forwardToLocation("/jsp/College/successfulCreation.jsp", request,
					response);
		} else if ("buffer_hit_ratio".equals(r)) {
			forwardToLocation("pages/Buffer_Cache_hit.jsp", request,response);
		}
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
		;
	}
	
}
