package DbTuning.dao;

public class Redo_LogSQL {


	public static final String REDO_SPACE_WAIT = "select round((req.value/wrt.value)*100,2) \"WAIT\" from " +
			"v$sysstat req, v$sysstat wrt where req.name= 'redo log space requests' " +
			"and wrt.name= 'redo writes' ";
	
	public static final String REDO_LOG_BUF_ENTRY="SELECT (a.value/b.value) \"redo buffer entries ratio\" FROM v$sysstat a, v$sysstat b " +
			"WHERE a.name = 'redo buffer allocation entries' AND b.name = 'redo entries'";
	
	public static final String REDO_LOG_SWITCH ="select to_char(first_time,'yyyy-mm-dd') day,to_char(first_time,'hh24') hour,count(*) total from v$log_history "
			+"group by to_char(first_time,'yyyy-mm-dd'),to_char(first_time,'hh24') "
			+"order by to_char(first_time,'yyyy-mm-dd'),to_char(first_time,'hh24') asc";
	
}

