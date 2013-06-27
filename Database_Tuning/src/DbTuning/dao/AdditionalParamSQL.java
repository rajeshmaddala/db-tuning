package DbTuning.dao;

public class AdditionalParamSQL {

	public static final String GET_TABLE_SCAN = "select round((shrt.value/(shrt.value+lng.value))*100,2) \"SHORT/FULL SCAN RATIO\" from " +
			"v$sysstat shrt, v$sysstat lng where shrt.name='table scans (short tables)' and lng.name='table scans (long tables)'";
	public static final String GET_CHAINED_FETCH_RATIO = "select round((cont.value/(scn.value+rid.value))*100,2) \"CHAINED_FETCH_RATIO\" " +
			"from v$sysstat cont, v$sysstat scn, v$sysstat rid " +
			"where cont.name= 'table fetch continued row' and scn.name= 'table scan rows gotten' and rid.name= 'table fetch by rowid'";
	public static final String GET_FREE_LIST_CONTENTION = "select round((sum(decode(w.class,'free list',count,0))/(sum(decode(name,'db block gets',value,0))+sum(decode(name,'consistent gets',value,0))))*100,2) \"FREE_LIST_CONTENTION\"" +
			" from v$waitstat w, v$sysstat";
	public static final String GET_CPU_PARSE_OVERLOAD = "select round((prs.value/(prs.value+exe.value))*100,2) \"CPU_PARSE_OVERLOAD\"" +
			" from v$sysstat prs, v$sysstat exe where prs.name like 'parse count (hard)'  and exe.name= 'execute count'";
	public static final String GET_WAIT_TIMES = "select event, count(*) \"COUNT\" from v$session_wait group by event";

}
