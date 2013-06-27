package DbTuning.Bean.AdditionalParameters;

import java.util.ArrayList;

import DbTuning.Bean.ThresholdConfig.ThresholdConfigBean;

public class AdditionalParamBean {
	
	float sf_scan_ratio;
	float chained_fetch_ratio;
	float free_list_contention;
	float cpu_parse_overload;
	WaitTimesBean wtbean;
	ArrayList<AdditionalParamBean> additionalParamList;

	public float getSf_scan_ratio() {
		return sf_scan_ratio;
	}
	public void setSf_scan_ratio(float sf_scan_ratio) {
		this.sf_scan_ratio = sf_scan_ratio;
	}
	public float getChained_fetch_ratio() {
		return chained_fetch_ratio;
	}
	public void setChained_fetch_ratio(float chained_fetch_ratio) {
		this.chained_fetch_ratio = chained_fetch_ratio;
	}
	public float getFree_list_contention() {
		return free_list_contention;
	}
	public void setFree_list_contention(float free_list_contention) {
		this.free_list_contention = free_list_contention;
	}
	public float getCpu_parse_overload() {
		return cpu_parse_overload;
	}
	public void setCpu_parse_overload(float cpu_parse_overload) {
		this.cpu_parse_overload = cpu_parse_overload;
	}
	public WaitTimesBean getWtbean() {
		return wtbean;
	}
	public void setWtbean(WaitTimesBean wtbean) {
		this.wtbean = wtbean;
	}
	public ArrayList<AdditionalParamBean> getAdditionalParamList() {
		return additionalParamList;
	}
	public void setAdditionalParamList(
			ArrayList<AdditionalParamBean> additionalParamList) {
		this.additionalParamList = additionalParamList;
	}

	
	
}
