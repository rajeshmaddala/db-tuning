package DbTuning.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import util.DBHelper.ConnectionDtls;
import util.DBHelper.DBConnection;

public class Debug_Dao {
	
	static ConnectionDtls connDtls = null; // single connection established in
	// one class
	static Connection conn = null;
	
	
	public String getQueryResult(String q){
		String result="";
		System.out.println("In Debug_DAO.java url: "+q);
		connDtls = DBConnection.getConnection();
		conn = connDtls.getConn();
		if (connDtls.getMessage() != null) {
			System.out.println(connDtls.getMessage());
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String query = q;//" select * from v$sysstat where name like '%table scans%' ";
			//String query = (Buffer_HitSQL.BUFFER_SQL1);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
					
			
			int rowCount = 0;

			 result+="<P ALIGN='center'><TABLE class=\"CSSTableGenerator\" BORDER=1>";
			 ResultSetMetaData rsmd = rs.getMetaData();
			 int columnCount = rsmd.getColumnCount();
			 // table header
			 result+="<TR>";
			 for (int i = 0; i < columnCount; i++) {
				 result+="<TH>" + rsmd.getColumnLabel(i + 1) + "</TH>";
			   }
			 result+="</TR>";
			 // the data
			 while (rs.next()) {
			  rowCount++;
			  result+="<TR>";
			  for (int i = 0; i < columnCount; i++) {
				  result+="<TD>" + rs.getString(i + 1) + "</TD>";
			    }
			  result+="</TR>";
			  }
			 result+="</TABLE><br><b>Total Rows :"+rowCount+"</b></P>";
			 			
			System.out.println("in debug_dao:");
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
			DBConnection.close(rs, pstmt, conn);
		}

			
		return result;
	}
}
