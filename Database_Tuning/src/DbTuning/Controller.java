package DbTuning;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DbTuning.Action.Additional_Parameters;
import DbTuning.Action.Buffer_Hit;
import DbTuning.Action.Generate_Report;
import DbTuning.Action.MemorySort;
import DbTuning.Action.Redo_log;
import DbTuning.Action.Rollback_Segment;
import DbTuning.Action.Shared_Pool;
import DbTuning.Action.ThesholdConfig;
import DbTuning.Action.Debug_Interface;


import java.net.URLDecoder;  

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
     Buffer_Hit buffer = new Buffer_Hit(); 
     Shared_Pool sp=new Shared_Pool();
     Rollback_Segment rs=new Rollback_Segment();
     Redo_log rl=new Redo_log();
     ThesholdConfig th=new ThesholdConfig();
     Generate_Report gr=new Generate_Report();
     Debug_Interface di=new Debug_Interface();
     Additional_Parameters ad=new Additional_Parameters();
     MemorySort ms=new MemorySort();
    /**
     * 
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();   
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("Action is : " + action);
		if (action.contains("Buffer_Hit_Ratio"))
		{
			buffer.displayBufferHitRatio(request, response);
		}
		else if (action.contains("Dictionary_Cache_Hit_Rate"))
		{
			sp.displaySharedHitRate(request,response);
		}
		else if (action.contains("Shared_Pool_Reload"))
		{
			sp.displaySharedPoolRatio(request,response,0);
		}
		else if (action.contains("Shared_Pool_Free"))
		{
			sp.displaySharedPoolRatio(request,response,1);
		}
		else if (action.contains("Library_Cache_Hit_Rate"))
		{
			sp.displayLibraryCacheHitRate(request,response,0);
		}
		else if (action.contains("Library_Cache_Get_Hit_Rate"))
		{
			sp.displayLibraryCacheHitRate(request,response,1);
		}
		else if (action.contains("Library_Cache_Pin_Hit_Rate"))
		{
			sp.displayLibraryCacheHitRate(request,response,2);
		}
		else if (action.contains("Redo_Space_Wait_Ratio"))
		{
			rl.displayRedoSpaceWaitRatio(request, response);
		}
		else if (action.contains("Redo_Log_Buffer_Entry_Ratio"))
		{
			rl.displayRedoLogBufferEntryRatio(request, response);
		}
		else if (action.contains("Redo_Log_Latches"))
		{
			rl.displayRedoLogLatches(request, response);
		}
		else if (action.contains("Redo_Log_Switch"))
		{
			rl.displayRedoLogSwitch(request, response);
		}
		else if (action.contains("Rollback_SegmentHitRatio"))
		{
			rs.displayRollback_SegmentHitRatio(request, response);
		}
		else if (action.contains("Rollback_SegmentWaitStatics"))
		{
		rs.displayRollback_SegmentWaitStats(request, response);
		}
		else if (action.contains("Threshold_Config"))
		{
			th.displayThresholdConfig(request,response);
		}
		else if (action.contains("Aggregated_Dictionary_Hit_Rate"))
		{
			sp.getRecordedTime(request,response);
		}
		else if (action.contains("Generate_Reports"))
		{
			String report_param=request.getParameter("report_param");
			gr.getRecordedTime(request, response,report_param);
			
		}
		else if (action.contains("Debug_Interface"))
		{
			String query=URLDecoder.decode(request.getParameter("query"), "UTF-8");
			query = query.replaceAll("<percentage>", "%");
			if (query.endsWith(";")) {
		        query = query.substring(0, query.length() - 1);
		    }
			
			System.out.println("Debugg Interface :"+ query);
			di.displayQueryResult(request, response, query);
			
			
		}
		else if (action.contains("Additional_Paramaters"))
		{
			ad.displayAdditionalParameters(request, response);
		}
		else if (action.contains("Memory_Sort"))
		{
			ms.displayMemorySortParam(request, response);
		}
		else if (action.contains("Update_Config"))
		{
			System.out.println(request.getParameter("checkBox"));
			String thresParam = request.getParameter("checkBox");
			String[] splits = thresParam.split(",");
			ArrayList<Float> minvalue=new ArrayList<Float>();
			ArrayList<Float> maxvalue=new ArrayList<Float>();
			ArrayList<String> param=new ArrayList<String>();
			
			System.out.println("splits.size: " + splits.length);
			
			for(String asset: splits){
				String[] splitsPart = asset.split(":");
				
				System.out.println(splitsPart[1]);
				param.add(splitsPart[1]);
				
				System.out.println(splitsPart[0]+" "+request.getParameter("min"+splitsPart[0]));
				minvalue.add(Float.parseFloat(request.getParameter("min"+splitsPart[0])));
				
				System.out.println(splitsPart[0]+" "+request.getParameter("max"+splitsPart[0]));
				maxvalue.add(Float.parseFloat(request.getParameter("max"+splitsPart[0])));
			}

			th.updateThresholdConfig(param,minvalue,maxvalue,request,response);
			
		}
	}

}


