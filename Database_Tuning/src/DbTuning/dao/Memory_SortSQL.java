package DbTuning.dao;

public class Memory_SortSQL {

	public static final String GET_WORK_AREA = "select name,value from v$sysstat where name like 'workarea executions%'";
	public static final String GET_PGA_TARGET ="select name,value from v$pgastat where name = 'aggregate PGA target parameter'";
	public static final String GET_PGA_TARGET_ADVICE ="select round(PGA_TARGET_FOR_ESTIMATE/1024/1024) as " +
			"target_size_MB,bytes_processed,ESTD_EXTRA_BYTES_RW as est_rw_extra_bytes,ESTD_PGA_CACHE_HIT_PERCENTAGE" +
			" as est_hit_pct,ESTD_OVERALLOC_COUNT as est_overalloc from v$pga_target_advice";
}

