package DbTuning.Bean.BufferHitRatio;

import java.util.ArrayList;

import DbTuning.Bean.ThresholdConfig.ThresholdConfigBean;

public class BufferHitRatioBean {
	
	private int logical_read;
	private int physical_read;
	private int buffer_hit_ratio;
	private ArrayList<BufferHitRatioBean> buffer_hit_ratio_List;
	private ThresholdConfigBean tcb;

	public ThresholdConfigBean getTcb() {
		return tcb;
	}

	public void setTcb(ThresholdConfigBean tcb) {
		this.tcb = tcb;
	}
	public int getLogical_read() {
		return logical_read;
	}
	public void setLogical_read(int logical_read) {
		this.logical_read = logical_read;
	}
	public int getPhysical_read() {
		return physical_read;
	}
	public void setPhysical_read(int physical_read) {
		this.physical_read = physical_read;
	}
	public int getBuffer_hit_ratio() {
		return buffer_hit_ratio;
	}
	public void setBuffer_hit_ratio(int buffer_hit_ratio) {
		this.buffer_hit_ratio = buffer_hit_ratio;
	}
	public ArrayList<BufferHitRatioBean> getBuffer_hit_ratio_List() {
		return buffer_hit_ratio_List;
	}
	public void setBuffer_hit_ratio_List(
			ArrayList<BufferHitRatioBean> buffer_hit_ratio_List) {
		this.buffer_hit_ratio_List = buffer_hit_ratio_List;
	}
	

	
}
