package DbTuning.Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DbTuning.Bean.Rollback_Segment.Rollback_SegmentBean;
import DbTuning.dao.Rollback_SegmentDao;



public class Rollback_Segment {

Rollback_SegmentDao rsDao=new Rollback_SegmentDao();
	
	public void displayRollback_SegmentHitRatio(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Rollback_SegmentBean bean = rsDao.getRollbackBean();
		System.out.println(bean.getRollback_hit_ratio());
		request.setAttribute("rollback_segment1", bean);
		request.setAttribute("msg", "Rollback_SegmentHitRatio");
		displayResult(request, response);
	}
	
	public void displayRollback_SegmentWaitStats(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Rollback_SegmentBean bean = rsDao.getRollbackWaitStat();
		System.out.println(bean.getRollback_hit_rate_List().size());
		request.setAttribute("rollback_segment2", bean);
		request.setAttribute("msg", "Rollback_SegmentWaitStats");
		displayResult(request, response);
	}

	private void displayResult(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String r = request.getAttribute("msg").toString();
		System.out.println(r);
			forwardToLocation("pages/Rollback_Segment.jsp", request,response);
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
