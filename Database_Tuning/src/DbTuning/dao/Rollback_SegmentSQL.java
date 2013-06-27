package DbTuning.dao;

public class Rollback_SegmentSQL {


	public static final String ROLLBACK_SEGMENT_RATIO = "select round(sum(waits)/sum(gets)*100,2) \"HIT RATIO\" " +
			"from v$rollstat";
	
	public static final String ROLLBACK_WAIT= "select class, count from v$waitstat where class in" +
			" ('free list','system undo header','system undo block','undo header','undo block') group by class,count";
	
	
}

