package DbTuning.Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DbTuning.Bean.RedoLog.RedoLogBufferEntryBean;
import DbTuning.Bean.RedoLog.RedoLogSpaceWaitBean;
import DbTuning.Bean.RedoLog.RedoLogSwitchBean;
import DbTuning.dao.Redo_LogDao;




public class Redo_log {

Redo_LogDao rlDao=new Redo_LogDao();
	

	public void displayRedoSpaceWaitRatio(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		RedoLogSpaceWaitBean bean = rlDao.getRedoSpaceWaitRatio();
		System.out.println(bean.getWait());
		request.setAttribute("RedoSpaceWaitRatio", bean);
		request.setAttribute("msg", "RedoSpaceWaitRatio");
		displayResult(request, response);
		
	}
	
	public void displayRedoLogBufferEntryRatio(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		RedoLogBufferEntryBean bean = rlDao.getRedoLogBufferEntryRatio();
		System.out.println(bean.getRedo_buffer_entries_ratio());
		request.setAttribute("RedoLogBufferEntryRatio", bean);
		request.setAttribute("msg", "RedoLogBufferEntryRatio");
		displayResult(request, response);
		
	}

	public void displayRedoLogLatches(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RedoLogSpaceWaitBean bean = rlDao.getRedoLogLatches();
		System.out.println(bean.getWait());
		request.setAttribute("RedoLogLatches", bean);
		request.setAttribute("msg", "RedoLogLatches");
		displayResult(request, response);
		
	}

	public void displayRedoLogSwitch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RedoLogSwitchBean bean = rlDao.getRedoLogSwitch();
		System.out.println(bean.getRedo_log_switch_list().size());
		request.setAttribute("RedoLogSwitch", bean);
		request.setAttribute("msg", "RedoLogSwitch");
		displayResult(request, response);
		
	}

	
	private void displayResult(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String r = request.getAttribute("msg").toString();
		System.out.println(r);
		forwardToLocation("pages/Redo_Log.jsp", request,response);
		
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
