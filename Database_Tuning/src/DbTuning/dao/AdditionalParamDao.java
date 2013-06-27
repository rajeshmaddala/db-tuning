package DbTuning.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBHelper.ConnectionDtls;
import util.DBHelper.DBConnection;

import DbTuning.Bean.AdditionalParameters.WaitTimesBean;



public class AdditionalParamDao {
	
	static ConnectionDtls connDtls = null; // single connection established in one class
	static Connection conn = null;

	
	public AdditionalParamDao(){
		connDtls = DBConnection.getConnection();
		conn = connDtls.getConn();
		if (connDtls.getMessage() != null) {
			System.out.println(connDtls.getMessage());
		}
	}

	public ResultSet getParam(String param){
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String query="";
			if(param.contains("table_scan"))
				query = AdditionalParamSQL.GET_TABLE_SCAN;
			else if (param.contains("chained_fetch_ratio"))
				query = AdditionalParamSQL.GET_CHAINED_FETCH_RATIO;
			else if (param.contains("free_list_contention"))
				query = AdditionalParamSQL.GET_FREE_LIST_CONTENTION;
			else if (param.contains("CPU_Parse_Overlolad"))
				query = AdditionalParamSQL.GET_CPU_PARSE_OVERLOAD;
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
					
			System.out.println("in additional_param_dao:");
		} catch (SQLException sqle) {

			sqle.printStackTrace();
		}catch(NullPointerException e)
		{

			e.printStackTrace();
		}catch(ArrayIndexOutOfBoundsException e)
		{

			e.printStackTrace();
		}
		catch(Exception e)
		{

			e.printStackTrace();
		} 
		
		finally {
			//DBConnection.close(rs, pstmt, conn);
		}
		
		return rs;
	}
	
	public float getTableScan() {
		
		try{
		
		ResultSet rs=getParam("table_scan");
		while (rs.next()) {
		System.out.println("table scan ratio is : "+ rs.getFloat("SHORT/FULL SCAN RATIO"));
		return rs.getFloat("SHORT/FULL SCAN RATIO");
		}
		}
		catch(Exception e)
		{

			e.printStackTrace();
		} 
		
		finally {
			//DBConnection.close(rs, pstmt, conn);
		}
		
		return 0;
	}

	public float getChainedFetchRatio() {
		
		try{
			
			ResultSet rs=getParam("chained_fetch_ratio");
			while (rs.next()) {
			System.out.println("chained_fetch_ratio is : "+ rs.getFloat("CHAINED_FETCH_RATIO"));
			return rs.getFloat("CHAINED_FETCH_RATIO");
			}}
			catch(Exception e)
			{

				e.printStackTrace();
			} 
			
			finally {
				//DBConnection.close(rs, pstmt, conn);
			}
			
			return 0;
	}

	public float getFreeListContention() {
			try{
			
			ResultSet rs=getParam("free_list_contention");
			while (rs.next()) {
			System.out.println("free_list_contention is : "+ rs.getFloat("FREE_LIST_CONTENTION"));
			return rs.getFloat("FREE_LIST_CONTENTION");
			}}
			catch(Exception e)
			{

				e.printStackTrace();
			} 
			
			finally {
				//DBConnection.close(rs, pstmt, conn);
			}
			
			return 0;
	}

	public float getCpuParseOverload() {
		
	try{
			
			ResultSet rs=getParam("CPU_Parse_Overlolad");
			while (rs.next()) {
			System.out.println("CPU_Parse_Overlolad is : "+ rs.getFloat("CPU_PARSE_OVERLOAD"));
			return rs.getFloat("CPU_PARSE_OVERLOAD");
			}}
			catch(Exception e)
			{

				e.printStackTrace();
			} 
			
			finally {
				//DBConnection.close(rs, pstmt, conn);
			}
			
			return 0;
	}

	public WaitTimesBean getWaitListTimes() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WaitTimesBean wtbean=new WaitTimesBean();
		ArrayList<WaitTimesBean> wtbean_list=new ArrayList<WaitTimesBean>();
		
		try {
			String query=AdditionalParamSQL.GET_WAIT_TIMES;
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
	
			
			while (rs.next()) {
				WaitTimesBean wtbean1=new WaitTimesBean();
				System.out.println(rs.getString("event"));
				wtbean1.setEvent(rs.getString("event"));
				wtbean1.setCount(rs.getInt("COUNT"));
				wtbean_list.add(wtbean1);
			}
			
			wtbean.setWtbean(wtbean_list);	
			for(int i=0;i<wtbean.getWtbean().size();i++){
			System.out.println("in additional_param_daogggggggg: "+ +wtbean.getWtbean().size()+" "+wtbean_list.get(i).getEvent());
			}
			
		} catch (SQLException sqle) {

			sqle.printStackTrace();
		}catch(NullPointerException e)
		{

			e.printStackTrace();
		}catch(ArrayIndexOutOfBoundsException e)
		{

			e.printStackTrace();
		}
		catch(Exception e)
		{

			e.printStackTrace();
		} 
		
		finally {
			//DBConnection.close(rs, pstmt, conn);
		}
		
		return wtbean;
	}

}
