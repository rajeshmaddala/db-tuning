package DbTuning.Bean.ThresholdConfig;

import java.util.ArrayList;

public class ThresholdConfigBean {
	
	private String paramter_name;
	private float min_healthy;
	private float max_healthy;
	private ArrayList<ThresholdConfigBean> threshold_List;
	public String getParamter_name() {
		return paramter_name;
	}
	public void setParamter_name(String paramter_name) {
		this.paramter_name = paramter_name;
	}
	
	public float getMin_healthy() {
		return min_healthy;
	}
	public void setMin_healthy(float min_healthy) {
		this.min_healthy = min_healthy;
	}
	public float getMax_healthy() {
		return max_healthy;
	}
	public void setMax_healthy(float max_healthy) {
		this.max_healthy = max_healthy;
	}
	public ArrayList<ThresholdConfigBean> getThreshold_List() {
		return threshold_List;
	}
	public void setThreshold_List(ArrayList<ThresholdConfigBean> threshold_List) {
		this.threshold_List = threshold_List;
	}
	
}
