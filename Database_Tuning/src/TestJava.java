
public class TestJava {
	public static void main (String args[])
	{
	 String BUFFER_SQL = "select a.value + b.value \"logical_reads\",c.value \"phys_reads\",round(100 * ((a.value+b.value)-c.value) /(a.value+b.value)) \"BUFFER HIT RATIO\" from v$sysstat a, v$sysstat b, v$sysstat c where a.statistic# = 38 and b.statistic# = 39 and c.statistic# = 40;";
	        System.out.println ("If you need to 'quote' in Java");
	        System.out.println ("you can use single \' or double \" quote");
	        System.out.println(BUFFER_SQL);
	}

}
