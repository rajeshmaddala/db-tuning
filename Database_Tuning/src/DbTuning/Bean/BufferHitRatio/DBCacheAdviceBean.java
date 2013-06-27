package DbTuning.Bean.BufferHitRatio;

import java.util.ArrayList;

public class DBCacheAdviceBean {
	
	private String name;
	private int block_size;
	private int size_for_estimate;
	private int size_factor;
	private int estd_physical_reads;
	
	private ArrayList<DBCacheAdviceBean> dbcache_advice_List;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBlock_size() {
		return block_size;
	}

	public void setBlock_size(int block_size) {
		this.block_size = block_size;
	}

	public int getSize_for_estimate() {
		return size_for_estimate;
	}

	public void setSize_for_estimate(int size_for_estimate) {
		this.size_for_estimate = size_for_estimate;
	}

	public int getSize_factor() {
		return size_factor;
	}

	public void setSize_factor(int size_factor) {
		this.size_factor = size_factor;
	}

	public int getEstd_physical_reads() {
		return estd_physical_reads;
	}

	public void setEstd_physical_reads(int estd_physica_reads) {
		this.estd_physical_reads = estd_physica_reads;
	}

	public ArrayList<DBCacheAdviceBean> getDbcache_advice_List() {
		return dbcache_advice_List;
	}

	public void setDbcache_advice_List(
			ArrayList<DBCacheAdviceBean> dbcache_advice_List) {
		this.dbcache_advice_List = dbcache_advice_List;
	}
	
	
}
