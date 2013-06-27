function navigationScript(){
	$('.submenu').hide();
	
	$('#TuneParameters').bind('click',function(){	
			boldMe('TuneParameters');
			$('#subparameters').empty();
			$('#sub2parameters').empty();
			emptyMe('content_div');
			emptyMe('content');
			$('.submenu').show();
			$('#heading').text("Database Tuning Parameters");
			addSpace('content_div');
			$('#subparameters').append('<li id=\'buffercache\'>Buffer Cache</li>'+
										'<li id=\'sharedPool\'>Shared Pool</li>'+
		  								'<li id=\'memSortArea\'>Memory Sort Area</li>'+
		  								'<li id=\'redoLog\'>Redo Log Activity</li>'+
		  								'<li id=\'rollbackSeg\'>Rollback Segment</li>');	
			
			
			$('#buffercache').bind('click',function(){
				boldMe('buffercache');
	  			$('#content_div').empty();
	  			$('#content').empty();
	  			$('#heading').text("Buffer Cache Parameters");
	  			$('#sub2parameters').empty();
	  			$('#sub2parameters').append(	 
	  			 '<li id=\'BufferCacheHitRatio\'><a>Buffer Cache Hit Ratio</a></li>'
	  			);
	  			$('#content_div').append("<br></br>Whenever a user queries some data, Oracle first checks for that data in buffer cache. If the data is stored in the cache it is returned to the user instantly. When the data is buffered in the cache, it leads to reading the data from physical disk into an available buffer in the cache.");
	  			
	  			$('#BufferCacheHitRatio').bind('click',function(){
					emptyMe('content_div');
					emptyMe('content');
					$('#heading').text("Buffer Cache Hit Ratio");
					getBufferCacheHitRatio();
					
				});
			});
	  			
			
			
			
			/*************************SharedPool Parameters Start************************************/
			$('#sharedPool').bind('click',function(){
				boldMe('sharedPool');
	  			$('#content_div').empty();
	  			$('#content').empty();
	  			$('#heading').text("Shared Pool Parameters");
	  			$('#sub2parameters').empty();
	  			$('#sub2parameters').append(	 
	  			 '<li id=\'DicCacheHitRatio\'><a>Dictionary Cache Hit Ratio</a></li>'+
	  			 '<li id=\'SharedPoolReload\'><a>Shared Pool Reload Ratio</a></li>'+
	  			 '<li id=\'SharedPoolFree\'><a>Shared Pool Free Ratio</a></li>'+
	  			 '<li id=\'LibraryCacheHitRate\'><a>Library Cache Hit Rate</a></li>'+
	  			 '<li id=\'LibraryCacheGetHitRate\'><a>Library Cache Get Hit Rate</a></li>'+
	  			 '<li id=\'LibraryCachePinHitRate\'><a>Library Cache Pin Hit Rate</a></li>'+
	  			 '<li id=\'AggregatedDictionaryHitRate\'><a>Aggregated Dictionary Cache Hit Ratio</a></li>'
	  			); 
	  			$('#content_div').append("<br></br>When an Oracle SQL statement is fired, it is first compiles in two phases. The first phase parses the SQL statement and the second phase executes it. Oracle searches the Library cache while parsing the SQL statement. If it does not find any record for the current SQL statement, it will allocate an area in the shared pool RAM within the library cache and then parse the SQL statement. Thus, when executing this statement, Oracle checks whether this SQL statement already exists in the library cache. If not, then it will reparse the statement as mentioned above and execute it.");
	  			
	  			$('#DicCacheHitRatio').bind('click',function(){
					emptyMe('content_div');
					emptyMe('content');
					$('#heading').text("Dictionary Cache Hit Ratio");
					getDicCacheHitRatio();
					
				});
	  			$('#SharedPoolReload').bind('click',function(){
					emptyMe('content_div');
					emptyMe('content');
					$('#heading').text("Shared Pool Reload Ratio");
					getSharedPoolReload();
					
				});
		  		$('#SharedPoolFree').bind('click',function(){
		  			emptyMe('content_div');
		  			emptyMe('content');
		  			$('#heading').text("Shared Pool Free Ratio");
		  			getSharedPoolFree();
		  		});	
		  		$('#LibraryCacheHitRate').bind('click',function(){
		  			emptyMe('content_div');
		  			emptyMe('content');
		  			$('#heading').text("Library Cache Hit Rate");
		  			getLibraryCacheHitRate();
		  		});	
		  		$('#LibraryCacheGetHitRate').bind('click',function(){
		  			emptyMe('content_div');
		  			emptyMe('content');
		  			$('#heading').text("Library Cache Get Hit Rate");
		  			getLibraryCacheGetHitRate();
		  		});	
		  		$('#LibraryCachePinHitRate').bind('click',function(){
		  			emptyMe('content_div');
		  			emptyMe('content');
		  			$('#heading').text("Library Cache Pin Hit Rate");
		  			getLibraryCachePinHitRate();
		  		});	
		  		$('#AggregatedDictionaryHitRate').bind('click',function(){
		  			emptyMe('content_div');
		  			emptyMe('content');
		  			$('#heading').text("Aggregated Dictioanary Cache Hit Rate");
		  			getAggregatedDictionaryHitRate();
		  		});	
	  			//drawSPChart();	
	  		});
			
			/*************************MemorySort Area Start************************************/
			$('#memSortArea').bind('click',function(){
				boldMe('memSortArea');
	  			$('#content_div').empty();
	  			$('#content').empty();
	  			$('#heading').text("Memory Sort Area");
	  			$('#sub2parameters').empty();
	  			getMemorySortArea();
			});
			
			
			/*************************Redo Log Activity Start************************************/
			$('#redoLog').bind('click',function(){
				boldMe('redoLog');
				$('#content_div').empty();
				emptyMe('content');
				$('#heading').text("Redo Log Activity");
	  			$('#sub2parameters').empty();
	  			$('#sub2parameters').append('<li id=\'RedoSpaceWaitRatio\'><a>Redo Space Wait Ratio</a></li>'
	  									+'<li id=\'RedoLogBufferEntryRatio\'><a>Redo Log Buffer Entry Ratio</a></li>'
	  									+'<li id=\'RedoLogSwitch\'><a>Redo Log Switches</a></li>');
	  			$('#content_div').append("<br></br>Redo Log Buffer is a part of System Global Area (SGA) wherein changes made to a database instance are persisted. This enables the Oracle database to recover quickly in case of any media failures. Usually there are two or more pre-allocated files that store all updates made to the database, and each database instance is associated with one of these files. The database initialization parameter log_buffer defines the default size of the redo log buffer within Oracle 11g. Proper sizing of redo logs can greatly enhance performance.");
	  			$('#RedoSpaceWaitRatio').bind('click',function(){
					emptyMe('content_div');
					emptyMe('content');
					$('#heading').text("Redo Space Wait Ratio");
					getRedoSpaceWaitRatio();
					
				});
	  			$('#RedoLogBufferEntryRatio').bind('click',function(){
					emptyMe('content_div');
					emptyMe('content');
					$('#heading').text("Redo Log Buffer Entry Ratio");
					getRedoLogBufferEntryRatio();
					
				});
		  		$('#RedoLogSwitch').bind('click',function(){
		  			emptyMe('content_div');
		  			emptyMe('content');
		  			$('#heading').text("Redo Log Switches");
		  			getRedoLogSwitch();
		  		});	
	  			
			});
			
			/*************************Rollback Segment Start************************************/
			$('#rollbackSeg').bind('click',function(){
				boldMe('rollbackSeg');
	  			$('#content_div').empty();
	  			$('#content').empty();
	  			addSpace("content");
	  			$('#heading').text("Rollback Segment");
	  			$('#sub2parameters').empty();
	  			$('#sub2parameters').append('<li id=\'RollbackSegmentHitRatio\'><a>Rollback Segment Hit Ratio</a></li>'
	  					 					+'<li id=\'RollbackSegmentWaitStatics\'><a>Rollback Segment Wait Statistics</a></li>'); 
	  			$('#content_div').append("<br></br>In order to provide read consistency, rollback segments store pre-image of a data. When concurrent transactions are updating the database, the changes made to the data by one of the transactions should not be visible to others unless they are committed. This is especially important in case of long running queries.");
	  			$('#RollbackSegmentHitRatio').bind('click',function(){
					emptyMe('content_div');
					emptyMe('content');
					$('#heading').text("Rollback Segment Hit Ratio");
					getRollbackSegmentHitRatio();
				});
	  			$('#RollbackSegmentWaitStatics').bind('click',function(){
					emptyMe('content_div');
					emptyMe('content');
					$('#heading').text("Rollback Segment Wait Statistics");
					getRollbackSegmentWaitStatics();
				});
			});
	});
	
	$('#AddParams').bind('click',function(){
		boldMe('AddParams');
		$('.submenu').hide();
		$('#subparameters').empty();
		$('#sub2parameters').empty();
		emptyMe('content_div');
		emptyMe('content');
		$('#heading').text("Additional Parameters");
		$('#subparameters').append('');
		getAddParams();
		 		
	});
	$('#SQLDebug').bind('click',function(){
		boldMe('SQLDebug');
		$('.submenu').hide();
		$('#subparameters').empty();
		$('#sub2parameters').empty();
		emptyMe('content_div');
		emptyMe('content');
		$('#heading').text("SQL Debugger");
		$('#subparameters').append('');	
		getSQLDebug();
		 		
	});
	$('#ThresConfig').bind('click',function(){
		boldMe('ThresConfig');
		$('.submenu').hide();
		$('#subparameters').empty();
		$('#sub2parameters').empty();
		emptyMe('content_div');
		emptyMe('content');
		$('#heading').text("Threshold Configurator");
		getThresholdConfig(); 		
	});
	$('#AWR').bind('click',function(){
		boldMe('AWR');
		$('.submenu').hide();
		$('#subparameters').empty();
		$('#sub2parameters').empty();
		emptyMe('content_div');
		emptyMe('content');
		$('#heading').text("On Demand Reports");
		getAWR();		 		
	});
	
}

function getSQLDebug(){
	$.post('DebugInterface.jsp',function(response){
		$('#content').append(response);
	});
}
function getAddParams(){
	$.post('../Controller?action=Additional_Paramaters',function(response){
		//alert(response);
		$('#content').append(response);
	});
}
/*********Utility Functions **********/
function boldMe(tag){
	//alert("Bold");
	$('li').removeClass("bold");
	$('#'+tag).addClass("bold");
}
function emptyMe(tag){
	$('#'+tag).empty();
}
function addSpace(tag){
	$('#'+tag).append("<br><br><br><br><br><br><br><br><br><br>");
}

function getBufferCacheHitRatio(){
	google.load('visualization', '1', {packages:['gauge']});
	 $.post('../Controller?action=Buffer_Hit_Ratio',function(response){
		// alert("Output ::::"+response);
		 $('head').append(response);
		 var chart = new google.visualization.Gauge(document.getElementById('content_div'));
	     chart.draw(data, options);
	     $('#content').append("Buffer Cache Hit Rate indicates the overall percentage of data requests that are catered directly from the Buffer Cache. This can be calculated using the following formula:Buffer Cache Hit Rate indicates the overall percentage of data requests that are catered directly from the Buffer Cache. This can be calculated using the following formula:");
	     $('#content').append("<br></br><font color='blue'><i>Cache Hit Ratio = 100 * (1 - physical reads/logical reads)</i></font>");
	     $('#content').append("<br></br>If the buffer cache is very small then, the cache hit rate will be very small indicating that more physical disk I/O would be done. If the buffer cache is too big, then proportions of buffer cache will be under-utilized and memory resources are wasted.");
	     $('#content').append("<br></br>Paramater to be changed in init.ora: <font color='red'><i>DB_BLOCK_BUFFERS</i></font>");
	     $('#content').append("<br></br>Optimum Value: <font color='green'><i>High(>90)</i></font>");
	 });
}


/*************************SharedPool Parameters Functions************************************/
function getDicCacheHitRatio(){
	google.load('visualization', '1', {packages:['gauge']});
	 $.post('../Controller?action=Dictionary_Cache_Hit_Rate',function(response){
		// alert("Output ::::"+response);
		 $('head').append(response);
		 var chart = new google.visualization.Gauge(document.getElementById('content_div'));
	     chart.draw(data, options);
	     $('#content').append("Data Dictionary cache also known as row cache is the area where all the referential integrity, indexing information, table definitions and other metadata about Oracle’s internal system tables. It stores all the data dictionary related information in shared pool RAM for easy and quick access");
	     $('#content').append("<br></br>Paramater to be changed in init.ora: <font color='red'><i>SHARED_POOL_SIZE</i></font>");
	     $('#content').append("<br></br>Optimum Value: <font color='green'><i>High(>95)</i></font>");
	 });
}
function getSharedPoolReload(){
	google.load('visualization', '1', {packages:['gauge']});
	$.post('../Controller?action=Shared_Pool_Reload',function(response){
		// alert("Output ::::"+response);
				 $('head').append(response);
				 var chart = new google.visualization.Gauge(document.getElementById('content_div'));
				 chart.draw(data, options);
				 $('#content').append("Shared Pool Reload indicates the number of times Oracle fails to search the library cache for the current SQL/PLSQL statement and reparse the statement in to the library cache instead of being reused.");
				 $('#content').append("<br></br>Paramater to be changed in init.ora: <font color='red'><i>SHARED_POOL_SIZE and OPEN_CURSORS</i></font><br></br>Optimum Value: <font color='green'><i>Low (<1)</i></font>");
	});
}  
function getSharedPoolFree(){
	google.load('visualization', '1', {packages:['gauge']});
	$.post('../Controller?action=Shared_Pool_Free',function(response){
		// alert("Output ::::"+response);
				 $('head').append(response);
				 var chart = new google.visualization.Gauge(document.getElementById('content_div'));
				 chart.draw(data, options);
				 $('#content').append("Shared Pool Free Ratio indicates the ratio of the shared pool RAM area which is not currently in use. If a large portion of the shared pool are is always free then a suggestion is to reduce the size of the shared pool. A low shared pool free ratio is acceptable unless other above mentioned factors like Library Cache Hit Rate or Shared Pool Reload Ratio are not affected.");
				 $('#content').append("<br></br>Paramater to be changed in init.ora: <font color='red'><i>SHARED_POOL_SIZE</i></font><br></br>Optimum Value: <font color='green'><i>Low (<1)</i></font>");
	});
}  
function getLibraryCacheHitRate(){
	google.load('visualization', '1', {packages:['gauge']});
	$.post('../Controller?action=Library_Cache_Hit_Rate',function(response){
		// alert("Output ::::"+response);
				 $('head').append(response);
				 var chart = new google.visualization.Gauge(document.getElementById('content_div'));
				 chart.draw(data, options);
				 $('#content').append("The library cache hit ratio indicates how many times Oracle hits the library cache to parse a particular SQL/PLSQL statement without a cache miss occurring.");
				 $('#content').append("<br></br>Paramater to be changed in init.ora: <font color='red'><i>SHARED_POOL_SIZE and OPEN_CURSORS</i></font><br></br>Optimum Value: <font color='green'><i>High (>95)</i></font>");
	});
}  
function getLibraryCacheGetHitRate(){
	google.load('visualization', '1', {packages:['gauge']});
	$.post('../Controller?action=Library_Cache_Get_Hit_Rate',function(response){
		// alert("Output ::::"+response);
				 $('head').append(response);
				 var chart = new google.visualization.Gauge(document.getElementById('content_div'));
				 chart.draw(data, options);
				 $('#content').append("<br></br>Paramater to be changed in init.ora: <font color='red'><i>SHARED_POOL_SIZE and OPEN_CURSORS</i></font><br></br>Optimum Value: <font color='green'><i>High (>95)</i></font>");

	});
}  
function getLibraryCachePinHitRate(){
	google.load('visualization', '1', {packages:['gauge']});
	$.post('../Controller?action=Library_Cache_Pin_Hit_Rate',function(response){
		// alert("Output ::::"+response);
				 $('head').append(response);
				 var chart = new google.visualization.Gauge(document.getElementById('content_div'));
				 chart.draw(data, options);
				 $('#content').append("<br></br>Paramater to be changed in init.ora: <font color='red'><i>SHARED_POOL_SIZE and OPEN_CURSORS</i></font><br></br>Optimum Value: <font color='green'><i>High (>95)</i></font>");

	});
}  
function getAggregatedDictionaryHitRate(){
	google.load('visualization', '1', {packages:['gauge']});
	$.post('../Controller?action=Aggregated_Dictionary_Hit_Rate',function(response){
		//alert("Output ::::"+response);
				 $('#content').append(response);
				 var chart = new google.visualization.Gauge(document.getElementById('content_div'));
				 chart.draw(data, options);
	});
} 

/*************************Redo Log Parameters Functions************************************/
function getRedoSpaceWaitRatio(){
	google.load('visualization', '1', {packages:['gauge']});
	 $.post('../Controller?action=Redo_Space_Wait_Ratio',function(response){
		//alert("Output ::::"+response);
		 $('#content').append(response);
		 var chart = new google.visualization.Gauge(document.getElementById('content_div'));
	     chart.draw(data, options);
	     $('#content').append("When the redo buffers are small, transactions need to wait till space becomes available and they are recorded into the redo logs. The time for which the oracle log writer waits is reflected to the user, and is indicative of the fact that redo log buffer size is too small compared to the rate of transactions. Hence it is more prudent to increase the buffer size in such circumstances.");
	     $('#content').append("<br></br>Action to be taken : <font color='red'><i>Increase the redolog buffer size</i></font>");
	 });
}
function getRedoLogBufferEntryRatio(){
	google.load('visualization', '1', {packages:['gauge']});
	 $.post('../Controller?action=Redo_Log_Buffer_Entry_Ratio',function(response){
		//alert("Output ::::"+response);
		 $('#content').append(response);
		 var chart = new google.visualization.Gauge(document.getElementById('content_div'));
	     chart.draw(data, options);
	     $('#content').append("It is essential to monitor the Redo Log Buffer entries, for a positive value indicates that the buffer is full and the log writer is waiting to write the transactions. As these entries are crucial for failure recovery, hence they must be maintained in redo logs and the wait time should be negligible as it impacts performance.");

	});
}
function getRedoLogSwitch(){
	google.load('visualization', '1', {packages:['gauge']});
	 $.post('../Controller?action=Redo_Log_Switch',function(response){
		//alert("Output ::::"+response);
		 $('#content').append("<br></br>"+response);
		 var chart = new google.visualization.Gauge(document.getElementById('content_div'));
	     //chart.draw(data, options);
		 $('#content').append("A log switch occurs when the log writer stops writing to one redo log group and switches to another because the former ran out of space. By default log switch occurs after every 15 to 30 minutes. DBA monitor the number of log switches per hour, and if this number is too high, then the size of log files can be increased in order to bring it down to the recommended settings.");
		 $('#content').append("<br></br>Action to be taken : <font color='red'><i>Increase the redolog file size</i></font>");
		 $('#content').append("<br></br>Optimum Value: <font color='green'><i>One switch per 15 to 30 minutes</i></font>");
	 });
}

/*************************Rollback Segment Functions************************************/
function getRollbackSegmentHitRatio(){
	google.load('visualization', '1', {packages:['gauge']});
	 $.post('../Controller?action=Rollback_SegmentHitRatio',function(response){
		//alert("Output ::::"+response);
		 $('#content').append(response);
		 var chart = new google.visualization.Gauge(document.getElementById('content_div'));
	     chart.draw(data, options);
	     $('#content').append("The number and size of rollback segments is an important performance parameter. We examine the GETS and WAITS columns of the dynamic views v$rollstat and v$rollname in order to determine the rollback segment usage estimate its optimum size for our applications. GETS parameter gives the number of rollback segment header requests, whereas the WAITS tell us the number of header requests that resulted in waits.<br></br><font color='red'><i>If the waits to gets ratio is greater than 0.01, then either new rollback segments should be created or the size of existing segments should be increased.</i></font>");
	     $('#content').append("<br></br>Optimum Value: <font color='green'><i>Low(<0.01)</i></font>");
	});
}
function getRollbackSegmentWaitStatics(){
	google.load('visualization', '1', {packages:['gauge']});
	 $.post('../Controller?action=Rollback_SegmentWaitStatics',function(response){
		//alert("Output ::::"+response);
		 $('#content').append(response);
		 var chart = new google.visualization.Gauge(document.getElementById('content_div'));
	     //chart.draw(data, options);
		 $('#content').append("Rollback contention occurs when too many transactions try to access the same rollback segment concurrently. Consequently some of the transactions end up waiting. In order to avoid rollback contention, on an average one rollback segment should be created for every 4 concurrent transactions.");
	});
}
/************************Threshold Config Function*****************/
function getThresholdConfig(){
	google.load('visualization', '1', {packages:['gauge']});
	 $.post('../Controller?action=Threshold_Config',function(response){
		//alert("Output ::::"+response);
		 $('#content').append(response);
		
		 //var chart = new google.visualization.Gauge(document.getElementById('content_div'));
	     //chart.draw(data, options);
	});
}

/************************AWR Function*****************/
function getAWR(){
	
	 $.post('AWR.jsp',function(response){
		//alert("Output ::::"+response);
		 $('#content').append(response);
		 
	});	 
}
/****************Get Memory Sort Area******************/
function getMemorySortArea(){
	google.load('visualization', '1', {packages:['gauge']});
	 $.post('../Controller?action=Memory_Sort',function(response){
		 //alert("Output ::::"+response);
		 $('#content').append(response);
		 
	});
	
}