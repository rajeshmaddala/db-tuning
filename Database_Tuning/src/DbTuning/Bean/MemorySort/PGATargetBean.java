package DbTuning.Bean.MemorySort;

import java.util.ArrayList;

public class PGATargetBean {
private int TARGET_SIZE_MB;
private int BYTES_PROCESSED;
private int EST_RW_EXTRA_BYTES;
private int EST_HIT_PCT;
private int EST_OVERALLOC;
ArrayList<PGATargetBean> list;
public int getTARGET_SIZE_MB() {
	return TARGET_SIZE_MB;
}
public void setTARGET_SIZE_MB(int tARGET_SIZE_MB) {
	TARGET_SIZE_MB = tARGET_SIZE_MB;
}
public int getBYTES_PROCESSED() {
	return BYTES_PROCESSED;
}
public void setBYTES_PROCESSED(int bYTES_PROCESSED) {
	BYTES_PROCESSED = bYTES_PROCESSED;
}
public int getEST_RW_EXTRA_BYTES() {
	return EST_RW_EXTRA_BYTES;
}
public void setEST_RW_EXTRA_BYTES(int eST_RW_EXTRA_BYTES) {
	EST_RW_EXTRA_BYTES = eST_RW_EXTRA_BYTES;
}
public int getEST_HIT_PCT() {
	return EST_HIT_PCT;
}
public void setEST_HIT_PCT(int eST_HIT_PCT) {
	EST_HIT_PCT = eST_HIT_PCT;
}
public int getEST_OVERALLOC() {
	return EST_OVERALLOC;
}
public void setEST_OVERALLOC(int eST_OVERALLOC) {
	EST_OVERALLOC = eST_OVERALLOC;
}
public ArrayList<PGATargetBean> getList() {
	return list;
}
public void setList(ArrayList<PGATargetBean> list) {
	this.list = list;
}
}
