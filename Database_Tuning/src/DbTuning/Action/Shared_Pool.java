package DbTuning.Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DbTuning.Bean.SharedPool.RecordBean;
import DbTuning.Bean.SharedPool.SharedPoolBean;
import DbTuning.dao.Shared_Pooldao;


public class Shared_Pool {

Shared_Pooldao spDao=new Shared_Pooldao();
String sharedPool=null;
String lib_hit=null;

	public void displaySharedHitRate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		SharedPoolBean bean = spDao.getSharedPoolBean();
		System.out.println(bean.getShared_pool_hit_rate());
		request.setAttribute("shared_pool", "Dictionary");
		request.setAttribute("dictionary", bean);
		request.setAttribute("msg", "Display List");
		displayResult(request, response);
	}

	public void displayLibraryCacheHitRate(HttpServletRequest request,
			HttpServletResponse response, int i) throws ServletException, IOException {
		SharedPoolBean bean = spDao.getLibHitRatios(i);
		if(i==0){
			lib_hit="Library_Cache_Hit_Rate";
			System.out.println(bean.getLib_hit_rate());
		}
		else if(i==1){
			lib_hit="Library_Cache_Get_Hit_Rate";
			System.out.println(bean.getLib_get_hit_rate());
		}
		else if(i==2){
			lib_hit="Library_Cache_Pin_Hit_Rate";
			System.out.println(bean.getLib_pin_hit_rate());
		}
		System.out.println(lib_hit);
		request.setAttribute("shared_pool", lib_hit);
		request.setAttribute("lib_hit", bean);
		request.setAttribute("msg", lib_hit);
		displayResult(request, response);
		
	}

	public void displaySharedPoolRatio(HttpServletRequest request,
			HttpServletResponse response, int i) throws ServletException, IOException {
		SharedPoolBean bean =spDao.getSharedPoolRatios(i);
		if(i==0){
			sharedPool="Shared_Pool_Reload";
			System.out.println(bean.getReload_ratio());
		}
		else if(i==1){
			sharedPool="Shared_Pool_Free";
			System.out.println(bean.getFree());
		}
		System.out.println(sharedPool);
		request.setAttribute("shared_pool", sharedPool);
		request.setAttribute("sharedPool", bean);
		request.setAttribute("msg", sharedPool);
		displayResult(request, response);
	}

	public void getRecordedTime(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException  {
		RecordBean bean =spDao.getRecordBean();
		request.setAttribute("recordBean", bean);
		request.setAttribute("shared_pool", "dropdown");
		request.setAttribute("msg", "dropdown");
		displayResult(request, response);
		
	}

	
	
	private void displayResult(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String r = request.getAttribute("msg").toString();
		System.out.println(r);
		if("dropdown".contains(r))
			forwardToLocation("pages/AggregatedRate.jsp", request,response);
		else
			forwardToLocation("pages/Shared_Pool.jsp", request,response);
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
