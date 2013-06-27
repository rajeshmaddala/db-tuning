package DbTuning.dao;

public class Shared_PoolSQL {


	public static final String SHARED_POOL_SQL = "select sum(gets),sum(getmisses)," +
			"(1-(sum(getmisses)/(sum(gets) + sum(getmisses))))*100 as \"HIT RATE\" from v$rowcache";
	
	public static final String SHARED_POOL_FREE="select round((sum(decode(name,'free memory',bytes,0))/sum(bytes))*100,2) \"FREE\" " +
			"from v$sgastat";
	
	public static final String SHARED_POOL_RELOAD="select round(sum(reloads)/sum(pins)*100,2) \"RATIO\" from v$librarycache " +
			"where namespace in ('SQL AREA','TABLE/PROCEDURE','BODY','TRIGGER')";
	
	public static final String LIB_CACHE_GET_HIT="select round(sum(gethits)/sum(gets)*100,2) \"RATIO\" " +
			"from v$librarycache";
	
	public static final String LIB_CACHE_PIN_HIT="select round(sum(pinhits)/sum(pins)*100,2) \"RATIO\" " +
			"from v$librarycache";

	/*changed by Shailee on 10/04/2013*/
	public static final String LIB_CACHE_HIT="SELECT round((1-((sum(RELOADS)/sum(PINS))))*100,2) \"Hit Ratio\" FROM V$LIBRARYCACHE";
	/*changed by Shailee on 10/04/2013*/

	public static final String RECORD_Q_VALUE = "select * from quarterly";

	public static final String RECORD_H_VALUE = "select * from hourly";

	/*added by Shailee on 11/04/2013*/
	public static final String SELECT_RECORD_Q_VALUE= "select * from quarterly where recorded_time >=? and recorded_time <=?";
	
	public static final String SELECT_RECORD_H_VALUE= "select * from hourly where recorded_time >=? and recorded_time <=?";
	/*added by Shailee on 11/04/2013*/
}

