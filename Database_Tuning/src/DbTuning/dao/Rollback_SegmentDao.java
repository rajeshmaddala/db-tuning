package DbTuning.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DbTuning.Bean.Rollback_Segment.Rollback_SegmentBean;
import DbTuning.Bean.ThresholdConfig.ThresholdConfigBean;




import util.DBHelper.ConnectionDtls;
import util.DBHelper.DBConnection;




public class Rollback_SegmentDao {

		static ConnectionDtls connDtls = null; // single connection established in
		// one class
		static Connection conn = null;
		Thresholddao tcd=new Thresholddao();
		ThresholdConfigBean tcb=new ThresholdConfigBean();
		
	public Rollback_SegmentBean getRollbackBean() {
		connDtls = DBConnection.getConnection();
		conn = connDtls.getConn();
		if (connDtls.getMessage() != null) {
			System.out.println(connDtls.getMessage());
		}
		Rollback_SegmentBean bean = new Rollback_SegmentBean();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String query = (Rollback_SegmentSQL.ROLLBACK_SEGMENT_RATIO);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			populateBean(bean, rs,1);
			tcb=tcd.getThesholdConfigBean("Rollback Segment Hit Ratio");
			bean.setTcb(tcb);
			System.out.println("in rollback_segmentdao :"+bean.getRollback_hit_ratio()+" "+bean.getTcb().getMax_healthy());	
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

	public Rollback_SegmentBean getRollbackWaitStat() {
		connDtls = DBConnection.getConnection();
		conn = connDtls.getConn();
		if (connDtls.getMessage() != null) {
			System.out.println(connDtls.getMessage());
		}
		Rollback_SegmentBean bean = new Rollback_SegmentBean();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String query = (Rollback_SegmentSQL.ROLLBACK_WAIT);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			populateBean(bean, rs,2);
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
	
	
	private static void populateBean(Rollback_SegmentBean bean, ResultSet rs,int action) { 
		
		
		ArrayList<Rollback_SegmentBean> rollback_list = new ArrayList<Rollback_SegmentBean>(); 
		try {
			if(action==1){
			while (rs.next()) {
				System.out.println(rs.getString("HIT RATIO"));
				bean.setRollback_hit_ratio(rs.getFloat("HIT RATIO"));
			}
			System.out.println("in rollback_segmentdao: in populate bean :"+bean.getRollback_hit_ratio());
			}
			else if(action==2){
				while (rs.next()) {
					Rollback_SegmentBean bean1=new Rollback_SegmentBean();
					bean1.setRollback_class(rs.getString("class"));
					bean1.setCount(rs.getInt("count"));
					rollback_list.add(bean1);
				}
				bean.setRollback_hit_rate_List(rollback_list);
				System.out.println("in rollback_segmentdao: in populate bean :"+bean.getRollback_hit_rate_List().size());
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
