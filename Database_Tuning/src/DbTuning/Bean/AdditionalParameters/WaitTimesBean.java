package DbTuning.Bean.AdditionalParameters;

import java.util.ArrayList;

public class WaitTimesBean {
	
	String event;
	int count;
	ArrayList<WaitTimesBean> wtbeanlist;
	
	public ArrayList<WaitTimesBean> getWtbean() {
		return wtbeanlist;
	}
	public void setWtbean(ArrayList<WaitTimesBean> wtbeanlist) {
		this.wtbeanlist = wtbeanlist;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
