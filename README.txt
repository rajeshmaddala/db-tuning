README.txt 

1.	Project Deployment: The Database performance tool project is a java web application that requires a web server
	installed in the system like Apache Tomcat and a java IDE like eclipse to compile and deploy the project jar on the server.

2.	Database Configuration: The system requires a Database installed, in this case Oracle 10g/11g

	2.1	Create a system privileged user in the database.
	2.2	The required database parameters of system privilege username, password and connection string are to be updated at src/util/DBHelper/DBConnection.java
	2.3 	Execute SQL commands from setup.sql file to configure the Inventory system tables and data.sql to populate the tables.


3.	On Demand Reporting: Set the path of the local folder where you want to store the on-demand reports at CreateReport.java 
	SET variable REPORT_PATH.
