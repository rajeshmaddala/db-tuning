package DbTuning.Action;


import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import DbTuning.dao.Debug_Dao;



public class Debug_Interface {

	

	Debug_Dao dDao=new Debug_Dao();

	public void displayQueryResult(HttpServletRequest request,
			HttpServletResponse response, String query) throws ServletException, IOException {
		System.out.println("In Debug_Inrfc.java");
		String result = dDao.getQueryResult(query);
		System.out.println(result);
				
		
		PrintWriter out = response.getWriter();
		out.println(result);
		
	}
	

	
}
