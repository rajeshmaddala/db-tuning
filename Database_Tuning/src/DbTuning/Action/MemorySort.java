package DbTuning.Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DbTuning.Bean.MemorySort.PGATargetBean;
import DbTuning.Bean.MemorySort.SysPgaStatBean;
import DbTuning.Bean.SharedPool.SharedPoolBean;
import DbTuning.dao.MemorySortDao;

public class MemorySort {

	MemorySortDao ms=new MemorySortDao();
	public void displayMemorySortParam(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		SysPgaStatBean bean = ms.getSysPgaStatBean();
		System.out.println(bean.getList().get(1).getName()+" "+bean.getList().get(1).getValue());
		request.setAttribute("Memory_sort_bean", bean);
		PGATargetBean pgabean=ms.getPGATargetAdviceBean();
		request.setAttribute("Memory_sort_bean_pga", pgabean);
		request.setAttribute("msg", "Memory_sort_bean");
		displayResult(request, response);
	}
	
	private void displayResult(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String r = request.getAttribute("msg").toString();
		System.out.println(r);
			forwardToLocation("pages/Memory_Sort_Area.jsp", request,response);
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
