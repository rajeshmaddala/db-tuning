<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!-- InstanceBegin template="/Templates/main_page.dwt" codeOutsideHTMLIsLocked="false" -->


<head>
<style>
	.nopad {padding-left: 1px;
	vertical-align:super;}
	#nav {
    float: left;
    width: 280px;
    border-top: 1px solid #999;
    border-right: 1px solid #999;
    border-left: 1px solid #999;
    
}
.nav li a {
    display: block;
    padding: 10px 15px;
    background: #ccc;
    border-top: 1px solid #eee;
    border-bottom: 1px solid #999;
    text-decoration: none;
    color: #000;
}
.nav li a:hover, #nav li a.active {
    background: #999;
    color: #fff;
}
.nav li ul {
    display: none; // used to hide sub-menus
}
.nav li ul li a {
    padding: 10px 25px;
    background: #ececec;
    border-bottom: 1px dotted #ccc;
}

</style>
<link rel="stylesheet" href="demo.css?v=2">
	<!--<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<!-- InstanceBeginEditable name="doctitle" -->
		<title>DB Dashboard</title> <!-- InstanceEndEditable -->
		<link href="stylesheets/reset.css" rel="stylesheet" type="text/css" />
		<link href="stylesheets/index.css" rel="stylesheet" type="text/css" />
		<link href="stylesheets/style.css" rel="stylesheet" type="text/css" />
		<link href="stylesheets/TableCSSCode.css" rel="stylesheet" type="text/css" />
		
		<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js?ver=1.4.2"></script>
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
		<script type="text/javascript" src="scripts/browser-compatibility.js"></script>
		<script type="text/javascript" src="scripts/login.js"></script>
		<script type="text/javascript" src="scripts/index_navigation.js"></script>
		<script type="text/javascript" src="ajax.js"></script>  
		<script type="text/javascript">
		
	 		google.load("visualization", "1", { packages: ["corechart"] });
  			google.load('visualization', '1', {packages:['gauge']});
    			
  	
	  	$("document").ready(function() {
	  		
	  		 $('#nav > li > a').click(function(){
	  		    if ($(this).attr('class') != 'active'){
	  		      $('#nav li ul').slideUp();
	  		      $(this).next().slideToggle();
	  		      $('#nav li a').removeClass('active');
	  		      $(this).addClass('active');
	  		    }
	  		  });
	  		navigationScript();
	  	
	  	});
	  	
  </script> 
</head>


<body>
	<!--Login Module-->
	<div id="bar">
		<div id="container">
			<!-- Login Starts Here -->
			<div id="loginContainer">
				<a href="#" id="loginButton"><span>Login</span><em></em></a>
				<div style="clear: both"></div>
				<div id="loginBox">
					<form id="loginForm">
						<fieldset id="body">
							<fieldset>
								<label for="email">Email Address</label> <input type="text"
									name="email" id="email" />
							</fieldset>
							<fieldset>
								<label for="password">Password</label> <input type="password"
									name="password" id="password" />
							</fieldset>
							<input type="submit" id="login" value="Sign in" /> <label
								for="checkbox"><input type="checkbox" id="checkbox" />Remember
								me</label>
						</fieldset>
						<span><a href="#">Forgot your password?</a></span>
					</form>
				</div>
			</div>
			<!-- Login Ends Here -->
		</div>
	</div>
	<!--Login Module-->



	<div class="main_wrapper cf">
		<div class="header cf">
			<img src="images/logo.png" width="920" height="278" alt=""
				longdesc="images/logo.png" />
		</div>
		<div class="content cf">
			<!-- InstanceBeginEditable name="ContentRegion" -->
			<div class="hover_boxes cf">
				<div id="header-wrapper">
					<div class="wrapper">
						
						<ul class="navigation"><div id='navi'>
							<li id="TuneParameters">Tuning Parameters</li>	
							<li id="AddParams">Additional Params</li>
							<li id="SQLDebug">SQL Debugging</li>
							<li id="ThresConfig">Threshold Config</li>
							<li id="AWR">Reporting</li>
							</div>
						</ul>
					</div>
				</div>
				<ul id="subparameters" class="navigation subnavigation"></ul>
				<table width="914" height="500" border="2" style="height: 100%;">
					<tr>						
						<td class="submenu" width='230px' align='left'>
							<ul id="sub2parameters" class="nopad nav subnavigation"></ul>
							
						</td>
						<td align='center'><br>
							<h2 id='heading'>Database Performance Dashboard</h2>
							<div class='cube' id="content_div"><br><br><br><p align="left">Database Tuning is the activity of making a database application run more quickly,
							 i.e. to obtain higher throughput, albeit at the cost of lower response time for time-critical applications.
							  In this project a Database Performance Dashboard has been developed that aids the database administrators to monitor the health of the database and support performance investigations.
							   The performance dashboard provides a one-stop overview of various key performance indicators (KPIs) on database performance.
							   Users can then make use of the high-level information to further zoom in to identify the performance issues, and tune the system to improve performance.<br><br><br><br><br><br><br><br>
							  <p></div>
							<div id="content"></div>
						</td>
					</tr>
				</table>

				
			</div>
			<!-- InstanceEndEditable -->
		</div>
		<div class="footer cf">
			<p class="rights">Database Tuning Project Undertaking &#169; 2013
				All Rights Reserved</p>
		</div>
	</div>
</body>
</html>