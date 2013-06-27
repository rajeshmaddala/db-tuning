package DbTuning.Bean.Rollback_Segment;

import java.util.ArrayList;

import DbTuning.Bean.ThresholdConfig.ThresholdConfigBean;

public class Rollback_SegmentBean {
	
	private float rollback_hit_ratio;
	private String rollback_class;
	private int count;
	private ArrayList<Rollback_SegmentBean> rollback_hit_rate_List;
	private ThresholdConfigBean tcb;

	public ThresholdConfigBean getTcb() {
		return tcb;
	}

	public void setTcb(ThresholdConfigBean tcb) {
		this.tcb = tcb;
	}
	public float getRollback_hit_ratio() {
		return rollback_hit_ratio;
	}
	public void setRollback_hit_ratio(float f) {
		this.rollback_hit_ratio = f;
	}
	public String getRollback_class() {
		return rollback_class;
	}
	
	public void setRollback_class(String rollback_class) {
		this.rollback_class = rollback_class;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ArrayList<Rollback_SegmentBean> getRollback_hit_rate_List() {
		return rollback_hit_rate_List;
	}
	public void setRollback_hit_rate_List(
			ArrayList<Rollback_SegmentBean> rollback_hit_rate_List) {
		this.rollback_hit_rate_List = rollback_hit_rate_List;
	}
	


	
}
