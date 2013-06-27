package DbTuning.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DbTuning.Bean.BufferHitRatio.BufferHitRatioBean;
import DbTuning.Bean.BufferHitRatio.DBCacheAdviceBean;
import DbTuning.Bean.BufferHitRatio.FullScanBean;
import DbTuning.Bean.SharedPool.RecordBean;
import DbTuning.Bean.ThresholdConfig.ThresholdConfigBean;


import util.DBHelper.ConnectionDtls;
import util.DBHelper.DBConnection;


public class Buffer_Hitdao {

		static ConnectionDtls connDtls = null; // single connection established in
		// one class
		static Connection conn = null;
		Thresholddao tcd=new Thresholddao();
		ThresholdConfigBean tcb=new ThresholdConfigBean();

		
	public BufferHitRatioBean getBufferHitRatioBean() {
		connDtls = DBConnection.getConnection();
		conn = connDtls.getConn();
		if (connDtls.getMessage() != null) {
			System.out.println(connDtls.getMessage());
		}
		BufferHitRatioBean bean = new BufferHitRatioBean();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String query = (Buffer_HitSQL.BUFFER_SQL);
			//String query = (Buffer_HitSQL.BUFFER_SQL1);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			populateBean(bean, rs);
			tcb=tcd.getThesholdConfigBean("Buffer Cache Hit Ratio");
			bean.setTcb(tcb);
			System.out.println("in buffer_hitdao:"+bean.getBuffer_hit_ratio());
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

	private static void populateBean(BufferHitRatioBean bean, ResultSet rs) { 
		try {

			while (rs.next()) {
				
				System.out.println(rs.getString("BUFFER HIT RATIO"));
				bean.setLogical_read(rs.getInt("logical_reads"));
				bean.setPhysical_read(rs.getInt("phys_reads"));
				bean.setBuffer_hit_ratio(rs.getInt("BUFFER HIT RATIO"));
			}
			System.out.println("in buffer_hitdao: in populate bean :"+bean.getBuffer_hit_ratio());	

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

	public RecordBean getBufferHitRateRecordBean(String startDate,
			String endDate) {
		connDtls = DBConnection.getConnection();
		conn = connDtls.getConn();
		if (connDtls.getMessage() != null) {
			System.out.println(connDtls.getMessage());
		}
		ArrayList<RecordBean> rbean_list=new ArrayList<RecordBean>();
		RecordBean bean = new RecordBean();
		RecordBean bean1 = new RecordBean();
		RecordBean bean2 = new RecordBean();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query=null;
		try {
			query=(Buffer_HitSQL.SELECT_RECORD_Q_VALUE);
			pstmt = conn.prepareStatement(query);
			System.out.println("ffffffin in buffer_hitdao: in populate bean :"+startDate+" "+endDate);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();
			populateRecordBean(bean1, rs);
			rbean_list.add(bean1);
			
			query=(Buffer_HitSQL.SELECT_RECORD_H_VALUE);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();
			populateRecordBean(bean2, rs);
			rbean_list.add(bean2);
		
			bean.setRbean_list(rbean_list);
			
			FullScanBean fsb=new FullScanBean();
			query=(Buffer_HitSQL.GET_FULL_SCAN);
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			populateFullScanRecordBean(fsb, rs);
			
			bean.setFsb(fsb);
			
			DBCacheAdviceBean dcb=new DBCacheAdviceBean();
			query=(Buffer_HitSQL.GET_DB_CACHE_ADVICE);
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			populateDBAdviceBean(dcb, rs);
			
			bean.setDcb(dcb);
			
			System.out.println("ffffffin in buffer_hitdao: in populate bean :"+bean.getDcb().getDbcache_advice_List().get(0).getEstd_physical_reads());
			System.out.println("ffffffin in buffer_hitdao: in populate bean :"+bean.getFsb().getFull_scan_ratio_List().get(0).getFile_name());
			System.out.println("ffffffin in buffer_hitdao: in populate bean :"+bean.getRbean_list().get(0).getRbean_list().size());
			//System.out.println("bbbbbbin in buffer_hitdao: in populate bean :"+bean.getRbean_list().get(0).getRbean_list().get(0).getRate());
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

	private void populateDBAdviceBean(DBCacheAdviceBean dcb, ResultSet rs) {
		ArrayList<DBCacheAdviceBean> dcb_list=new ArrayList<DBCacheAdviceBean>();
		try {
			
			while (rs.next()) {
				DBCacheAdviceBean bean1=new DBCacheAdviceBean();
					System.out.println(rs.getString("name"));
					bean1.setName(rs.getString("name"));
					bean1.setBlock_size(rs.getInt("block_size"));
					bean1.setSize_for_estimate(rs.getInt("size_for_estimate"));
					bean1.setSize_factor(rs.getInt("size_factor"));
					bean1.setEstd_physical_reads(rs.getInt("estd_physical_reads"));
					dcb_list.add(bean1);
			}
			dcb.setDbcache_advice_List(dcb_list);
	
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

	private void populateFullScanRecordBean(FullScanBean fsb, ResultSet rs) {
		ArrayList<FullScanBean> fsb_list=new ArrayList<FullScanBean>();
		try {
			
			while (rs.next()) {
				FullScanBean bean1=new FullScanBean();
					System.out.println(rs.getString("file_name"));
					bean1.setFile_name(rs.getString("file_name"));
					bean1.setPhyrds(rs.getInt("phyrds"));
					bean1.setPhyblkrd(rs.getInt("phyblkrd"));
					fsb_list.add(bean1);
			}
			fsb.setFull_scan_ratio_List(fsb_list);
	
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


	private void populateRecordBean(RecordBean bean, ResultSet rs) {
		ArrayList<RecordBean> rbean_list=new ArrayList<RecordBean>();
		try {
			
			while (rs.next()) {
					RecordBean bean1=new RecordBean();
					System.out.println(rs.getTimestamp("RECORDED_TIME"));
					bean1.setRate(rs.getFloat("RATE"));
					bean1.setRecorded_time(rs.getTimestamp("RECORDED_TIME"));
					System.out.println("in shared_pooldao: in populate bean :"+bean1.getRate());	
					rbean_list.add(bean1);
			}
			bean.setRbean_list(rbean_list);
	
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
