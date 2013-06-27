package DbTuning.SharedPoolBean;

import java.util.ArrayList;

public class SharedPoolBean {
	
	private int gets;
	private int getsmisses;
	private int shared_pool_hit_rate;
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
	public int getShared_pool_hit_rate() {
		return shared_pool_hit_rate;
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
