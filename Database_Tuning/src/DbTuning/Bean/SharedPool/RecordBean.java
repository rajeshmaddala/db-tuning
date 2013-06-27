package DbTuning.Bean.SharedPool;

import java.sql.Timestamp;
import java.util.ArrayList;

import DbTuning.Bean.BufferHitRatio.DBCacheAdviceBean;
import DbTuning.Bean.BufferHitRatio.FullScanBean;
import DbTuning.Bean.ThresholdConfig.ThresholdConfigBean;


public class RecordBean {
	
	private float rate;
	private Timestamp recorded_time;
	private FullScanBean fsb;
	private DBCacheAdviceBean dcb;
	public DBCacheAdviceBean getDcb() {
		return dcb;
	}
	public void setDcb(DBCacheAdviceBean dcb) {
		this.dcb = dcb;
	}
	public FullScanBean getFsb() {
		return fsb;
	}
	public void setFsb(FullScanBean fsb) {
		this.fsb = fsb;
	}
	public ArrayList<RecordBean> getRbean_list() {
		return rbean_list;
	}
	public void setRbean_list(ArrayList<RecordBean> rbean_list) {
		this.rbean_list = rbean_list;
	}
	private ArrayList <RecordBean> rbean_list;
	
	private ThresholdConfigBean tcb=null;
	public ThresholdConfigBean getTcb() {
		return tcb;
	}
	public void setTcb(ThresholdConfigBean tcb) {
		this.tcb = tcb;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public Timestamp getRecorded_time() {
		return recorded_time;
	}
	public void setRecorded_time(Timestamp recorded_time) {
		this.recorded_time = recorded_time;
	}
	
}
