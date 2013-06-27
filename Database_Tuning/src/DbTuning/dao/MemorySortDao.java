package DbTuning.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DbTuning.Bean.MemorySort.PGATargetBean;
import DbTuning.Bean.MemorySort.SysPgaStatBean;
import util.DBHelper.ConnectionDtls;
import util.DBHelper.DBConnection;

public class MemorySortDao {

		static ConnectionDtls connDtls = null; 
		static Connection conn = null;
		
	public SysPgaStatBean getSysPgaStatBean() {
		connDtls = DBConnection.getConnection();
		conn = connDtls.getConn();
		if (connDtls.getMessage() != null) {
			System.out.println(connDtls.getMessage());
		}
		SysPgaStatBean bean = new SysPgaStatBean();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<SysPgaStatBean> list=new ArrayList<SysPgaStatBean>();
		try {
			SysPgaStatBean bean1 = new SysPgaStatBean();
			String query = (Memory_SortSQL.GET_WORK_AREA);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			populateBean(bean1, rs,1);
			list.add(bean1);
			
			SysPgaStatBean bean2 = new SysPgaStatBean();
			query = (Memory_SortSQL.GET_PGA_TARGET);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			populateBean(bean2, rs,2);
			list.add(bean2);
			
			bean.setList(list);
			System.out.println(bean.getList().get(1).getName()+" "+bean.getList().get(1).getValue());
			
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

	public PGATargetBean getPGATargetAdviceBean() {
		connDtls = DBConnection.getConnection();
		conn = connDtls.getConn();
		if (connDtls.getMessage() != null) {
			System.out.println(connDtls.getMessage());
		}
		PGATargetBean bean = new PGATargetBean();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			String query = (Memory_SortSQL.GET_PGA_TARGET_ADVICE);
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			populateAdviceBean(bean, rs);
			
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
	
	private void populateAdviceBean(PGATargetBean bean, ResultSet rs) {
		try {
			ArrayList<PGATargetBean> list=new ArrayList<PGATargetBean>();
			while (rs.next()) {
				PGATargetBean bean1=new PGATargetBean();
				System.out.println(rs.getInt("TARGET_SIZE_MB"));
				bean1.setTARGET_SIZE_MB(rs.getInt("TARGET_SIZE_MB"));
				bean1.setBYTES_PROCESSED(rs.getInt("BYTES_PROCESSED"));
				bean1.setEST_RW_EXTRA_BYTES(rs.getInt("EST_RW_EXTRA_BYTES"));
				bean1.setEST_HIT_PCT(rs.getInt("EST_HIT_PCT"));
				bean1.setEST_OVERALLOC(rs.getInt("EST_OVERALLOC"));
				list.add(bean1);
				}
			bean.setList(list);

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

	private void populateBean(SysPgaStatBean bean, ResultSet rs, int i) {
		try {
			if(i==1){

			ArrayList<SysPgaStatBean> list=new ArrayList<SysPgaStatBean>();
			while (rs.next()) {
				SysPgaStatBean bean1=new SysPgaStatBean();
				System.out.println(rs.getString("name"));
				bean1.setName(rs.getString("name"));
				bean1.setValue(rs.getInt("value"));
				list.add(bean1);
				}
			bean.setList(list);
			System.out.println(bean.getList().size()+" "+bean.getList().get(0).getName());
			
		}
			else if(i==2){
				while (rs.next()) {
					System.out.println(rs.getString("name"));
					bean.setName(rs.getString("name"));
					bean.setValue(rs.getInt("value"));
				}
				System.out.println(bean.getName()+" "+bean.getValue());
				
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
