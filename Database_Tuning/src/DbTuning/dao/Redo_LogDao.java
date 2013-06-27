package DbTuning.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DbTuning.Bean.RedoLog.RedoLogBufferEntryBean;
import DbTuning.Bean.RedoLog.RedoLogSpaceWaitBean;
import DbTuning.Bean.RedoLog.RedoLogSwitchBean;
import DbTuning.Bean.ThresholdConfig.ThresholdConfigBean;





import util.DBHelper.ConnectionDtls;
import util.DBHelper.DBConnection;




public class Redo_LogDao {

		static ConnectionDtls connDtls = null; 
		static Connection conn = null;
		Thresholddao tcd=new Thresholddao();
		ThresholdConfigBean tcb=new ThresholdConfigBean();
		
	public RedoLogSpaceWaitBean getRedoSpaceWaitRatio() {
		connDtls = DBConnection.getConnection();
		conn = connDtls.getConn();
		if (connDtls.getMessage() != null) {
			System.out.println(connDtls.getMessage());
		}
		RedoLogSpaceWaitBean bean = new RedoLogSpaceWaitBean();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String query = (Redo_LogSQL.REDO_SPACE_WAIT);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			populateRedoSpaceWaitBean(bean, rs);
			tcb=tcd.getThesholdConfigBean("Redo Space Wait Ratio");
			bean.setTcb(tcb);
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


	public RedoLogBufferEntryBean getRedoLogBufferEntryRatio() {
		connDtls = DBConnection.getConnection();
		conn = connDtls.getConn();
		if (connDtls.getMessage() != null) {
			System.out.println(connDtls.getMessage());
		}
		RedoLogBufferEntryBean bean = new RedoLogBufferEntryBean();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String query = (Redo_LogSQL.REDO_LOG_BUF_ENTRY);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			populateRedoLogBufferEntryBean(bean, rs);
			tcb=tcd.getThesholdConfigBean("Redo Log Buffer Entry Ratio");
			bean.setTcb(tcb);
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


	
	
	public RedoLogSwitchBean getRedoLogSwitch() {
		
		connDtls = DBConnection.getConnection();
		conn = connDtls.getConn();
		if (connDtls.getMessage() != null) {
			System.out.println(connDtls.getMessage());
		}
		RedoLogSwitchBean bean = new RedoLogSwitchBean();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String query = (Redo_LogSQL.REDO_LOG_SWITCH);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			populateRedoLogSwitchBean(bean, rs);
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

	public RedoLogSpaceWaitBean getRedoLogLatches() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private static void populateRedoSpaceWaitBean(RedoLogSpaceWaitBean bean, ResultSet rs) { 
		try {
			while (rs.next()) {
				System.out.println(rs.getString("WAIT"));
				bean.setWait(rs.getFloat("WAIT"));
			}
			System.out.println("in redolog_dao: in populateRedoSpaceWaitBean :"+bean.getWait());
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


	private void populateRedoLogBufferEntryBean(RedoLogBufferEntryBean bean,
			ResultSet rs) {
		try {
			while (rs.next()) {
				System.out.println(rs.getString("redo buffer entries ratio"));
				bean.setRedo_buffer_entries_ratio(rs.getInt("redo buffer entries ratio"));
			}
			System.out.println("in redolog_dao: in populateRedoLogBufferEntryBean :"+bean.getRedo_buffer_entries_ratio());
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

	private void populateRedoLogSwitchBean(RedoLogSwitchBean bean, ResultSet rs) {
		try {
			ArrayList<RedoLogSwitchBean> redo_log_switch_list=new ArrayList<RedoLogSwitchBean>();
			while (rs.next()) {
				RedoLogSwitchBean bean1 =new RedoLogSwitchBean();
				System.out.println(rs.getString("day"));
				bean1.setDay(rs.getString("day"));
				bean1.setHour(rs.getInt("hour"));
				bean1.setTotal(rs.getInt("total"));
				redo_log_switch_list.add(bean1);
			}
			bean.setRedo_log_switch_list(redo_log_switch_list);
			System.out.println("in redolog_dao: in populateRedoLogSwitchBean :"+bean.getRedo_log_switch_list().size());
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
