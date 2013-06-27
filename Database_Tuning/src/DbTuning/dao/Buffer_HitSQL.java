package DbTuning.dao;

public class Buffer_HitSQL {


	public static final String BUFFER_SQL="select a.value + b.value \"logical_reads\",c.value \"phys_reads\","
			+"round(100 * ((a.value+b.value)-c.value) /(a.value+b.value)) \"BUFFER HIT RATIO\" from v$sysstat a, v$sysstat b,"
			+"v$sysstat c WHERE a.name = \'db block gets\' AND b.name = \'consistent gets\' AND c.name = \'physical reads\'";
	
	public static final String BUFFER_CACHE_SIZE="select name,size_for_estimate,size_factor,estd_physical_read_factor from v$db_cache_advice";


	/*added by Shailee on 11/04/2013*/
	public static final String SELECT_RECORD_Q_VALUE= "select * from buffer_quarterly where recorded_time >=? and recorded_time <=?";
	
	public static final String SELECT_RECORD_H_VALUE= "select * from buffer_hourly where recorded_time >=? and recorded_time <=?";
	/*added by Shailee on 11/04/2013*/

	public static final String GET_FULL_SCAN = "SELECT   A.file_name, B.phyrds, B.phyblkrd" +
			" FROM     SYS.dba_data_files A, v$filestat B " +
			"WHERE    B.file# = A.file_id ORDER BY A.file_id";

	public static final String GET_DB_CACHE_ADVICE = "SELECT name,block_size,size_for_estimate,size_factor,estd_physical_reads " +
			"FROM v$db_cache_advice";

}
