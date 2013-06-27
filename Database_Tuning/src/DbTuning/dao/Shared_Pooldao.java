package DbTuning.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DbTuning.Bean.SharedPool.RecordBean;
import DbTuning.Bean.SharedPool.SharedPoolBean;
import DbTuning.Bean.ThresholdConfig.ThresholdConfigBean;

import util.DBHelper.ConnectionDtls;
import util.DBHelper.DBConnection;

public class Shared_Pooldao {

		static ConnectionDtls connDtls = null; // single connection established in
		// one class
		static Connection conn = null;
		Thresholddao tcd=new Thresholddao();
		ThresholdConfigBean tcb=new ThresholdConfigBean();

		
	public SharedPoolBean getSharedPoolBean() {
		connDtls = DBConnection.getConnection();
		conn = connDtls.getConn();
		if (connDtls.getMessage() != null) {
			System.out.println(connDtls.getMessage());
		}
		SharedPoolBean bean = new SharedPoolBean();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String query = (Shared_PoolSQL.SHARED_POOL_SQL);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			populateBean(bean, rs,0);
			tcb=tcd.getThesholdConfigBean("Dictionary Hit Rate");
			bean.setTcb(tcb);
			System.out.println("in shared_pooldao :"+bean.getShared_pool_hit_rate()+" "+bean.getTcb().getParamter_name()+" "+bean.getTcb().getMin_healthy()+" "+bean.getTcb().getMax_healthy());
			
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

	public SharedPoolBean getLibHitRatios(int i) {
		connDtls = DBConnection.getConnection();
		conn = connDtls.getConn();
		if (connDtls.getMessage() != null) {
			System.out.println(connDtls.getMessage());
		}
		SharedPoolBean bean = new SharedPoolBean();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query=null;
		int k=0;
		try {
			if(i==0){
				query = (Shared_PoolSQL.LIB_CACHE_HIT);
				k=3;
				tcb=tcd.getThesholdConfigBean("Library Cache Hit Rate");	
				}
			else if(i==1){
				query = (Shared_PoolSQL.LIB_CACHE_GET_HIT);
				k=4;
				tcb=tcd.getThesholdConfigBean("Library Cache Get Hit Rate");
			}
			else if(i==2){
				query = (Shared_PoolSQL.LIB_CACHE_PIN_HIT);
				k=5;
				tcb=tcd.getThesholdConfigBean("Library Cache Pin Hit Rate");
			}
			else
				k=-1;
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			populateBean(bean, rs,k);
			bean.setTcb(tcb);
			System.out.println("in shared_pooldao :"+bean.getShared_pool_hit_rate()+" "+bean.getTcb().getParamter_name()+" "+bean.getTcb().getMin_healthy()+" "+bean.getTcb().getMax_healthy());
		
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


	public SharedPoolBean getSharedPoolRatios(int i) {
		connDtls = DBConnection.getConnection();
		conn = connDtls.getConn();
		if (connDtls.getMessage() != null) {
			System.out.println(connDtls.getMessage());
		}
		SharedPoolBean bean = new SharedPoolBean();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query=null;
		int k=0;
		try {
			if(i==0){
				query = (Shared_PoolSQL.SHARED_POOL_RELOAD);
				k=1;
				tcb=tcd.getThesholdConfigBean("Shared Pool Reload Ratio");
			}
			else if(i==1){
				query = (Shared_PoolSQL.SHARED_POOL_FREE);
				k=2;
				tcb=tcd.getThesholdConfigBean("Shared Pool Free Ratio");
			}
			else
				k=-1;
		
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			populateBean(bean, rs,k);
			bean.setTcb(tcb);
			System.out.println("in shared_pooldao :"+bean.getShared_pool_hit_rate()+" "+bean.getTcb().getParamter_name()+" "+bean.getTcb().getMin_healthy()+" "+bean.getTcb().getMax_healthy());
		
		
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
	
	public RecordBean getRecordBean() {
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
			query=(Shared_PoolSQL.RECORD_Q_VALUE);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			populateRecordBean(bean1, rs);
			rbean_list.add(bean1);
			
			query=(Shared_PoolSQL.RECORD_H_VALUE);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			populateRecordBean(bean2, rs);
			rbean_list.add(bean2);
		
			bean.setRbean_list(rbean_list);
			tcb=tcd.getThesholdConfigBean("Dictionary Hit Rate");
			bean.setTcb(tcb);
			System.out.println("kkkkkin shared_pooldao: in populate bean :"+bean.getRbean_list().get(0).getRbean_list().size());
			System.out.println("kkkkkin shared_pooldao: in populate bean :"+bean.getRbean_list().get(0).getRbean_list().get(0).getRate());
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
	
	public RecordBean getDictionaryRecordBean(String startDate, String endDate) {
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
			query=(Shared_PoolSQL.SELECT_RECORD_Q_VALUE);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();
			populateRecordBean(bean1, rs);
			rbean_list.add(bean1);
			
			query=(Shared_PoolSQL.SELECT_RECORD_H_VALUE);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			rs = pstmt.executeQuery();
			populateRecordBean(bean2, rs);
			rbean_list.add(bean2);
		
			bean.setRbean_list(rbean_list);
			tcb=tcd.getThesholdConfigBean("Dictionary Hit Rate");
			bean.setTcb(tcb);
			//System.out.println("ffffffin shared_pooldao: in populate bean :"+bean.getRbean_list().get(0).getRbean_list().size());
			//System.out.println("bbbbbbin shared_pooldao: in populate bean :"+bean.getRbean_list().get(0).getRbean_list().get(0).getRate());
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
				/*for(int i=0;i<3;i++){
				System.out.println("ssssssssss:in shared_pooldao: in populate bean :"+bean.getRbean_list().get(i).getRate());	
			
				}*/
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


	private static void populateBean(SharedPoolBean bean, ResultSet rs,int i) { 
		if(i==-1){
			return;
		}
		else{
		try {
			while (rs.next()) {
				if(i==0){
				System.out.println(rs.getFloat("HIT RATE"));
				bean.setShared_pool_hit_rate(rs.getFloat("HIT RATE"));
				System.out.println("in shared_pooldao: in populate bean :"+bean.getShared_pool_hit_rate());	
				}
				else if(i==1){
					System.out.println(rs.getFloat("RATIO"));
					bean.setReload_ratio(rs.getFloat("RATIO"));
					System.out.println("in shared_pooldao: in populate bean :"+bean.getReload_ratio());	
				}
				else if(i==2){
					System.out.println(rs.getFloat("FREE"));
					bean.setReload_ratio(rs.getFloat("FREE"));
					System.out.println("in shared_pooldao: in populate bean :"+bean.getReload_ratio());	
				}
				else if(i==3){
					System.out.println(rs.getFloat("Hit Ratio"));
					bean.setLib_hit_rate(rs.getFloat("Hit Ratio"));
					System.out.println("in shared_pooldao: in populate bean :"+bean.getLib_hit_rate());	
				}
				else if(i==4){
					System.out.println(rs.getFloat("RATIO"));
					bean.setLib_get_hit_rate(rs.getFloat("RATIO"));
					System.out.println("in shared_pooldao: in populate bean :"+bean.getLib_get_hit_rate());	
				}
				else if(i==5){
					System.out.println(rs.getFloat("RATIO"));
					bean.setLib_pin_hit_rate(rs.getFloat("RATIO"));
					System.out.println("in shared_pooldao: in populate bean :"+bean.getLib_pin_hit_rate());	
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


	
}
