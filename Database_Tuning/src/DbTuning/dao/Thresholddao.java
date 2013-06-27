package DbTuning.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DbTuning.Bean.ThresholdConfig.ThresholdConfigBean;




import util.DBHelper.ConnectionDtls;
import util.DBHelper.DBConnection;




public class Thresholddao {

		static ConnectionDtls connDtls = null; // single connection established in
		// one class
		static Connection conn = null;
		
	public ThresholdConfigBean getThesholdConfig() {
		connDtls = DBConnection.getConnection();
		conn = connDtls.getConn();
		if (connDtls.getMessage() != null) {
			System.out.println(connDtls.getMessage());
		}
		ThresholdConfigBean bean = new ThresholdConfigBean();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String query = (Threshold_ConfigSQL.THRESHOLD_CONFIG);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			populateBean(bean, rs,0);
		} catch (SQLException sqle) {

			sqle.printStackTrace();
		}catch(NullPointerException e)
		{

			e.printStackTrace();
		}catch(Exception e)
		{

			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pstmt, conn);
		}

		return bean;
	}
	
	public ThresholdConfigBean getThesholdConfigBean(String param) {
		connDtls = DBConnection.getConnection();
		conn = connDtls.getConn();
		if (connDtls.getMessage() != null) {
			System.out.println(connDtls.getMessage());
		}
		ThresholdConfigBean bean = new ThresholdConfigBean();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String query = (Threshold_ConfigSQL.THRESHOLD_CONFIG_GET);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,param);
			rs = pstmt.executeQuery();
			populateBean(bean, rs,1);
		} catch (SQLException sqle) {

			sqle.printStackTrace();
		}catch(NullPointerException e)
		{

			e.printStackTrace();
		}catch(Exception e)
		{

			e.printStackTrace();
		} finally {
			DBConnection.close(rs, pstmt, conn);
		}

		return bean;
	}
	
	public int updateThresholdConfig(ArrayList<String> param,ArrayList<Float> minvalue, ArrayList<Float> maxvalue) {
		connDtls = DBConnection.getConnection();
		conn = connDtls.getConn();
		if (connDtls.getMessage() != null) {
			System.out.println(connDtls.getMessage());
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int rows =0;
		try {
			String query = (Threshold_ConfigSQL.UPDATE_THRESHOLD_CONFIG);
			int i=0;
			pstmt = conn.prepareStatement(query);
			System.out.println(query);
			conn.setAutoCommit(false);
			for (String suit : param){
				System.out.println(minvalue.get(i));	
			//pstmt.setFloat(1, minvalue.get(i));
			System.out.println( maxvalue.get(i));
			//pstmt.setFloat(2, maxvalue.get(i));
			System.out.println(suit);
			//pstmt.setString(3,suit);
			System.out.println(query);
			 pstmt.executeUpdate();
			 //conn.commit();
			 System.out.println(rows);
			i++;
			}
		
		} catch (SQLException sqle) {
			
			sqle.printStackTrace();
			 return 0;
		}catch(NullPointerException e)
		{

			e.printStackTrace();
			 return 0;
		}catch(Exception e)
		{

			e.printStackTrace();
			 return 0;
		} finally {
			DBConnection.close(rs, pstmt, conn);
		}
		return rows;
	}
	
	private static void populateBean(ThresholdConfigBean bean, ResultSet rs,int i) { 
	
		
		try {
			if(i==0){
				ArrayList<ThresholdConfigBean> threshold_List = new ArrayList<ThresholdConfigBean>(); 
				while (rs.next()) {
					ThresholdConfigBean bean1=new ThresholdConfigBean();
					bean1.setParamter_name(rs.getString("param_name"));
					bean1.setMin_healthy(rs.getFloat("min_healthy"));
					bean1.setMax_healthy(rs.getFloat("max_healthy"));
					threshold_List.add(bean1);
				}
				bean.setThreshold_List(threshold_List);
			}else if(i==1){
				while (rs.next()) {
					bean.setParamter_name(rs.getString("param_name"));
					bean.setMin_healthy(rs.getFloat("min_healthy"));
					bean.setMax_healthy(rs.getFloat("max_healthy"));
				}
			}
				

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}catch(NullPointerException e)
		{

			e.printStackTrace();
		}catch(ArrayIndexOutOfBoundsException e)
		{

			e.printStackTrace();
		}catch(Exception e)
		{

			e.printStackTrace();
		}
	}

	

}
