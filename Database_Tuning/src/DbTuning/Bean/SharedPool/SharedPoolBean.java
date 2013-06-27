package DbTuning.Bean.SharedPool;

import java.util.ArrayList;

import DbTuning.Bean.ThresholdConfig.ThresholdConfigBean;

public class SharedPoolBean {
	
	private int gets;
	private int getsmisses;
	private float shared_pool_hit_rate;
	private float free;
	private float reload_ratio;
	private float lib_hit_rate;
	private float lib_pin_hit_rate;
	private float lib_get_hit_rate;
	private ThresholdConfigBean tcb=null;
	public ThresholdConfigBean getTcb() {
		return tcb;
	}
	public void setTcb(ThresholdConfigBean tcb) {
		this.tcb = tcb;
	}
	
	public void setLib_get_hit_rate(int lib_get_hit_rate) {
		this.lib_get_hit_rate = lib_get_hit_rate;
	}
	private ArrayList<SharedPoolBean> shared_pool_hit_rate_List;
	public int getGets() {
		return gets;
	}
	public void setGets(int gets) {
		this.gets = gets;
	}
	public int getGetsmisses() {
		return getsmisses;
	}
	public void setGetsmisses(int getsmisses) {
		this.getsmisses = getsmisses;
	}
	public float getShared_pool_hit_rate() {
		return shared_pool_hit_rate;
	}
	public void setShared_pool_hit_rate(float shared_pool_hit_rate) {
		this.shared_pool_hit_rate = shared_pool_hit_rate;
	}
	public float getFree() {
		return free;
	}
	public void setFree(float free) {
		this.free = free;
	}
	public float getReload_ratio() {
		return reload_ratio;
	}
	public void setReload_ratio(float reload_ratio) {
		this.reload_ratio = reload_ratio;
	}
	public float getLib_hit_rate() {
		return lib_hit_rate;
	}
	public void setLib_hit_rate(float lib_hit_rate) {
		this.lib_hit_rate = lib_hit_rate;
	}
	public float getLib_pin_hit_rate() {
		return lib_pin_hit_rate;
	}
	public void setLib_pin_hit_rate(float lib_pin_hit_rate) {
		this.lib_pin_hit_rate = lib_pin_hit_rate;
	}
	public float getLib_get_hit_rate() {
		return lib_get_hit_rate;
	}
	public void setLib_get_hit_rate(float lib_get_hit_rate) {
		this.lib_get_hit_rate = lib_get_hit_rate;
	}
	public void setShared_pool_hit_rate(int shared_pool_hit_rate) {
		this.shared_pool_hit_rate = shared_pool_hit_rate;
	}
	public ArrayList<SharedPoolBean> getShared_pool_hit_rate_List() {
		return shared_pool_hit_rate_List;
	}
	public void setShared_pool_hit_rate_List(
			ArrayList<SharedPoolBean> shared_pool_hit_rate_List) {
		this.shared_pool_hit_rate_List = shared_pool_hit_rate_List;
	}
	
	

	
}
