package util.DBHelper;

import javax.servlet.http.HttpServletRequest;
public class DateUtil {
	
	public String changeDate(String date)
	{
		int pos=date.indexOf("-");
		int lastpos=date.lastIndexOf("-");
		String mon=(date.substring(pos+1, lastpos));
		System.out.println(mon);
		if(mon.equals("1"))
		{
			mon = "JAN";
		}
		
		else if(mon.equals("2"))
		{
			mon = "FEB";
		}
	
		else if(mon.equals("3"))
		{
			mon= "MAR";

		}
		else if(mon.equals("4"))
		{
			mon= "APR";
		}
		else if(mon.equals("5"))
		{
			mon= "MAY";
		}
		else if(mon.equals("6"))
		{
			mon= "JUN";
		}
		else if(mon.equals("7"))
		{
			mon= "JUL";
		}
		else if(mon.equals("8"))
		{
			mon= "AUG";
		}
		else if(mon.equals("9"))
		{
			mon=  "SEP";
		}
		else if(mon.equals("10"))
		{
			mon= "OCT";
		}
		else if(mon.equals("11"))
		{
			mon= "NOV";
		}
		else if(mon.equals("12"))
		{
			mon= "DEC";
		}
		
		date=date.substring(0,pos+1)+mon+date.substring(lastpos);
		
		pos=date.indexOf(":");
		lastpos=date.indexOf(" ");
		String hour=date.substring(lastpos+1,pos);
		
		System.out.println("hour is :"+ hour);
		
		int hr=Integer.parseInt(hour);
		int flag=0;
		System.out.println("hour is :"+ hr);
		
		if(hr>12){
			hr=hr-12;
			flag=1;
		}
		if(hr==0){
			hr=12;
		}
		
		date=date.substring(0,lastpos+1)+hr+date.substring(pos);
		if(flag==0){
		date=date+" AM";
		}
		else{
			date=date+" PM";	
		}
		return date;
	}
	
	/* -----------------to remove the time from timestamp format and display just date----------------------
	 * @ param request , bean
	 * @return String 
	 * */
	
	public String tsToDate(String date)
	{
		int pos=date.indexOf(" ");
		date=date.substring(0,pos)+"_"+date.substring(pos+1);
		date=date.replaceAll(":", ".");
		System.out.println(date);
		return date;
	}
}
