package DbTuning.Bean.BufferHitRatio;

import java.util.ArrayList;

public class FullScanBean {
	
	private String file_name;
	private int phyrds;
	private int phyblkrd;
	private ArrayList<FullScanBean> full_scan_ratio_List;
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public int getPhyrds() {
		return phyrds;
	}
	public void setPhyrds(int phyrds) {
		this.phyrds = phyrds;
	}
	public int getPhyblkrd() {
		return phyblkrd;
	}
	public void setPhyblkrd(int phyblkrd) {
		this.phyblkrd = phyblkrd;
	}
	public ArrayList<FullScanBean> getFull_scan_ratio_List() {
		return full_scan_ratio_List;
	}
	public void setFull_scan_ratio_List(ArrayList<FullScanBean> full_scan_ratio_List) {
		this.full_scan_ratio_List = full_scan_ratio_List;
	}
	
	
}
