package DbTuning.Bean.MemorySort;

import java.util.ArrayList;

public class SysPgaStatBean {

	private String name;
	private int value;
	public ArrayList<SysPgaStatBean> getList() {
		return list;
	}
	public void setList(ArrayList<SysPgaStatBean> list) {
		this.list = list;
	}
	ArrayList<SysPgaStatBean> list;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
