package DbTuning.Bean.RedoLog;

import DbTuning.Bean.ThresholdConfig.ThresholdConfigBean;



public class RedoLogBufferEntryBean {
	
	private int redo_buffer_entries_ratio;
	private ThresholdConfigBean tcb;

	public ThresholdConfigBean getTcb() {
		return tcb;
	}

	public void setTcb(ThresholdConfigBean tcb) {
		this.tcb = tcb;
	}

	public int getRedo_buffer_entries_ratio() {
		return redo_buffer_entries_ratio;
	}

	public void setRedo_buffer_entries_ratio(int redo_buffer_entries_ratio) {
		this.redo_buffer_entries_ratio = redo_buffer_entries_ratio;
	}
	
}
