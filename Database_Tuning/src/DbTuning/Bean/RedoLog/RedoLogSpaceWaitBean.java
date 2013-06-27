package DbTuning.Bean.RedoLog;

import DbTuning.Bean.ThresholdConfig.ThresholdConfigBean;



public class RedoLogSpaceWaitBean {
	
	private float wait;
	private ThresholdConfigBean tcb;

	public ThresholdConfigBean getTcb() {
		return tcb;
	}

	public void setTcb(ThresholdConfigBean tcb) {
		this.tcb = tcb;
	}

	public float getWait() {
		return wait;
	}

	public void setWait(float wait) {
		this.wait = wait;
	}
	
}
