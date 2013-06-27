package DbTuning.Bean.RedoLog;

import java.util.ArrayList;



public class RedoLogSwitchBean {
	
	private int hour;
	private int total;
	private String day;
	private ArrayList<RedoLogSwitchBean> redo_log_switch_list;
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public ArrayList<RedoLogSwitchBean> getRedo_log_switch_list() {
		return redo_log_switch_list;
	}
	public void setRedo_log_switch_list(
			ArrayList<RedoLogSwitchBean> redo_log_switch_list) {
		this.redo_log_switch_list = redo_log_switch_list;
	}
	
	
	
}
