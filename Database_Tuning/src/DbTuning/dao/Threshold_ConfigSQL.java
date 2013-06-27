package DbTuning.dao;

public class Threshold_ConfigSQL {


	public static final String THRESHOLD_CONFIG = "select * from threshold_config";	
	public static final String THRESHOLD_CONFIG_GET = "select * from threshold_config where param_name = ?";
	//public static final String UPDATE_THRESHOLD_CONFIG = "update threshold_config set MIN_HEALTHY=?, MAX_HEALTHY=? where PARAM_NAME=?";	
	public static final String UPDATE_THRESHOLD_CONFIG ="update threshold_config set MIN_HEALTHY=60.0, MAX_HEALTHY=90.0 where PARAM_NAME='Dictionary Hit Rate'";
	
}

