package util;


import java.awt.font.TextAttribute;
import java.io.File;
import java.io.FileOutputStream;
import java.text.AttributedString;

import DbTuning.Bean.SharedPool.RecordBean;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBHelper.DateUtil;


public class CreateReport {


	private static final Font redFont24 =  new Font(Font.FontFamily.TIMES_ROMAN, 24,
			Font.NORMAL, BaseColor.RED);
	private String startDate=null;
	private String endDate=null;
	private String REPORT_PATH = "E:/";
	private String REPORT_NAME = "Report_";
	DateUtil db=new DateUtil();
	private static Font smallNormal8 = new Font(Font.FontFamily.TIMES_ROMAN, 8,
			Font.NORMAL);
	Font smallNormalRed8= new Font(Font.FontFamily.TIMES_ROMAN, 8,
			Font.NORMAL,BaseColor.RED);
	private static Font small8 = new Font(Font.FontFamily.TIMES_ROMAN, 8,
			Font.NORMAL);
	private static Font mediumFontBold8 = new Font(Font.FontFamily.TIMES_ROMAN,
			8, Font.BOLD);
	private static Font titleFont12 = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.BOLD);
	private static Font titleFont10=new Font(Font.FontFamily.TIMES_ROMAN, 10,
			Font.BOLD,BaseColor.BLUE);
	private static Font redFont14 = new Font(Font.FontFamily.TIMES_ROMAN, 14,
			Font.NORMAL, BaseColor.RED);
	private static Font bigFont14 = new Font(Font.FontFamily.TIMES_ROMAN, 14,
			Font.BOLD);


	public boolean generateReport(HttpServletRequest request, HttpServletResponse response, RecordBean bean) {
		System.out.println("Executing generateReport()");
		System.out.println(request.getParameter("stDate"));
		startDate=request.getParameter("stDate");
		endDate=request.getParameter("endDate");
		startDate=db.changeDate(startDate);
		endDate=db.changeDate(endDate);
		REPORT_NAME=REPORT_NAME+db.tsToDate(startDate)+"_to_"+db.tsToDate(endDate)+".pdf";
		new File(REPORT_PATH, REPORT_NAME);

		String report_param=request.getParameter("report_param");
		String status=createPDF(bean,report_param);
		REPORT_NAME="Report_";
		if(status.equalsIgnoreCase("created")){
			return true;
		}
		return false;

	}

	public String createPDF(RecordBean bean, String report_param) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(REPORT_PATH + "/" + REPORT_NAME));
			document.open();

			addMetaData(document);
			addFirstPage(document,bean);
			addReportPage(document,bean,report_param);

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "created";
	}

	private void addReportPage(Document document, RecordBean bean,
			String report_param)throws DocumentException {
		Paragraph preface = new Paragraph();
		// We add one empty line
		addEmptyLine(preface, 1);

		// Lets write a big header
		if(report_param.contains("Dictionary")){
			preface.add(new Paragraph("Dictionary Cache Hit Ratio", titleFont10));
			addEmptyLine(preface, 1);
			createPerformanceParametersTable(preface,bean);
		}
		else if(report_param.contains("Buffer")){
			preface.add(new Paragraph("Buffer Cache Hit Ratio", titleFont12));
			addEmptyLine(preface, 1);

			/**********Introduction*********/
			preface.add(new Paragraph("Introduction",titleFont10));
			String text="Oracle maintains its own buffer cache inside the system global area (SGA) for each instance." +
					"A properly sized buffer cache can usually yield a cache hit ratio over 90%, meaning that nine requests out of" +
					" ten are satisfied without going to disk. If a buffer cache is too small," +
					" the cache hit ratio will be small and more physical disk I/O will result. " +
					"If a buffer cache is too big, then parts of the buffer cache will be under-utilized and memory resources " +
					"will be wasted. Following table shows the quarterly and hourly values for Buffer Cache Hit Rate between "
					+ startDate+" and "+endDate;
			preface.add(new Paragraph(text,smallNormal8));
			addEmptyLine(preface, 1);
			/**********Introduction*********/

			/**********Tables*********/
			createDetailsTable(preface);
			addEmptyLine(preface, 1);
			createPerformanceParametersTable(preface,bean);
			addEmptyLine(preface, 1);
			/**********Tables*********/
			
			/************FUll Table Scan************/
			preface.add(new Paragraph("Full Table Scans",titleFont10));
			
			text="When Oracle performs a full table scan of a large table," +
					" the blocks are read into the buffer cache but placed at the least recently used end of the LRU list." +
					" This causes the blocks to be aged out quickly, and prevents one large full" +
					" table scan from wiping out the entire buffer cache. ";
			//preface.add(new Paragraph(text,smallNormal8));
			
			text="Full table scans of large tables usually result in physical disk" +
					" reads and a lower buffer cache hit ratio. You can get an idea of full " +
					"table scan activity at the data file level by querying v$filestat and joining " +
					"to SYS.dba_data_files. Following is a query you can use and sample results: ";
			preface.add(new Paragraph(text,smallNormal8));
			addEmptyLine(preface, 1);
			/************FUll Table Scan************/
			
			/**********Table*********/
			createFullScanTable(preface,bean);
			addEmptyLine(preface, 1);
			text="Physical Reads = the number of reads from the data file since the instance was started.\n" +
				" Physical Block Reads = the actual number of data blocks read.\n" +
				" Usually blocks are requested one at a time." +
				" However, Oracle requests blocks in batches when performing full table scans." ;
			preface.add(new Paragraph(text,smallNormal8));
			text="(The db_file_multiblock_read_count parameter controls this batch size.)";
			preface.add(new Paragraph(text,smallNormalRed8));
			addEmptyLine(preface, 1);
			/**********Table*********/
			
			/*******Adjusting size of Buffer Cache****/
			preface.add(new Paragraph("Adjusting The Size Of The Buffer Cache",titleFont10));
	
			text="Db_block_buffers parameter in the parameter file determines the" +
					" size of the buffer cache for the instance.";
			preface.add(new Paragraph(text,smallNormalRed8));
			
			text="The size of the buffer cache" +
					"(in bytes) is equal to the value of the db_block_buffers parameter multiplied by the data block size.";
			preface.add(new Paragraph(text,smallNormal8));
			
			text="You can change the size of the buffer cache by editing the db_block_buffers parameter in the parameter" +
					" file and restarting the instance.";
			preface.add(new Paragraph(text,smallNormalRed8));
			addEmptyLine(preface, 1);
			/*******Adjusting size of Buffer Cache****/
			
			/******DB Cache Advice************/
			
			/******DB Cache Advice************/
			preface.add(new Paragraph("Using DB Cache Advice",titleFont10));
			text="Oracle 10g includes the Automatic Shared Memory Management (ASMM)" +
					" to automatically configure the shared memory areas, including the buffer cache." +
					" Oracle 10g also has some Memory Advisors, including the Buffer Cache Advisor." +
					" This advisor is basically the buffer cache advice available in Oracle 9i," +
					" but with recommendations as to what you should do. ";
			preface.add(new Paragraph(text,smallNormal8));
			addEmptyLine(preface, 1);
			text="To get an idea of sizing of the buffer cache, the V$DB_CACHE_ADVICE dynamic performance view is as given below: ";
			preface.add(new Paragraph(text,smallNormal8));
			createDBAdviceTable(preface,bean);
			addEmptyLine(preface, 1);
			text="Once you have collected the information, you will want to review it and determine the appropriate size for your database buffer cache.";
			preface.add(new Paragraph(text,smallNormal8));
			addEmptyLine(preface, 1);
			/**********Conclusion*********/
			preface.add(new Paragraph("Conclusion",titleFont10));
			text="This brief report gives you the basic information you need in order to optimize" +
					" the buffer cache size for your Oracle database. Also, you can zero" +
					" in on SQL statements that cause a lot of I/O, and data files that experience a lot of full table scans. ";
		preface.add(new Paragraph(text,smallNormal8));
			addEmptyLine(preface, 1);
			/**********Conclusion*********/

			addEmptyLine(preface, 1);
		}

		document.add(preface);


	}

	private void createDBAdviceTable(Paragraph subCatPart, RecordBean bean) {
	
		int columnCount = 0;
		columnCount=bean.getDcb().getDbcache_advice_List().size();
		
		System.out.println("Size of DCB:"+ columnCount);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		int i=0;
			

		if(columnCount!=0){
					table.addCell(new PdfPCell(new Phrase("Name  " , mediumFontBold8)));
					PdfPCell cell = new PdfPCell(new Phrase( "Block Size ", mediumFontBold8));
					table.addCell(cell);
					table.addCell(new PdfPCell(new Phrase("Size for Estimate " , mediumFontBold8)));
					table.addCell(new PdfPCell(new Phrase("Size factor " , mediumFontBold8)));
					table.addCell(new PdfPCell(new Phrase("Estimated Physical Reads " , mediumFontBold8)));
					table.completeRow();
					for(i=0;i<columnCount;i++){
						table.addCell(new Phrase(bean.getDcb().getDbcache_advice_List().get(i).getName() + "",smallNormal8));
						table.addCell(new Phrase(bean.getDcb().getDbcache_advice_List().get(i).getBlock_size() + "",smallNormal8));
						table.addCell(new Phrase(bean.getDcb().getDbcache_advice_List().get(i).getSize_for_estimate() + "",smallNormal8));
						table.addCell(new Phrase(bean.getDcb().getDbcache_advice_List().get(i).getSize_factor() + "",smallNormal8));
						table.addCell(new Phrase(bean.getDcb().getDbcache_advice_List().get(i).getEstd_physical_reads() + "",smallNormal8));
						table.completeRow();
					
				}
		
				}
		
			subCatPart.add(table);
	}

	private void createFullScanTable(Paragraph subCatPart, RecordBean bean) {
		int columnCount = 0;
		columnCount=bean.getFsb().getFull_scan_ratio_List().size();
		
		System.out.println("Size of FSB:"+ columnCount);

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100f);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		int i=0;
			

		if(columnCount!=0){
					table.addCell(new PdfPCell(new Phrase("File Name  " , mediumFontBold8)));
					PdfPCell cell = new PdfPCell(new Phrase( "Physical Reads ", mediumFontBold8));
					table.addCell(cell);
					table.addCell(new PdfPCell(new Phrase("Physical Block Reads " , mediumFontBold8)));
					table.completeRow();
					for(i=0;i<columnCount;i++){
						table.addCell(new Phrase(bean.getFsb().getFull_scan_ratio_List().get(i).getFile_name() + "",smallNormal8));
						table.addCell(new Phrase(bean.getFsb().getFull_scan_ratio_List().get(i).getPhyrds() + "",smallNormal8));
						table.addCell(new Phrase(bean.getFsb().getFull_scan_ratio_List().get(i).getPhyblkrd() + "",smallNormal8));
						table.completeRow();
					
				}
		
				}
		
			subCatPart.add(table);
		}
		

	private void addMetaData(Document document) {
		document.addTitle("DB Performance Parameters");
		document.addSubject("DB Performance Parameters");
		document.addAuthor("Rajesh");
		document.addCreator("Database Tuning Project");
	}

	private void addFirstPage(Document document,RecordBean bean) throws DocumentException {
		Paragraph preface = new Paragraph();
		// We add one empty line
		addEmptyLine(preface, 1);
		// Lets write a big header
		preface.add(new Paragraph("DB Paramters Report", redFont24));
		preface.setAlignment(Element.ALIGN_CENTER);
		addEmptyLine(preface, 1);
		document.add(preface);
		
		drawLogo(document);
		
		preface.clear();
		preface.add(new Paragraph("NUS- School of Computing",redFont14));
		preface.setAlignment(Element.ALIGN_CENTER);
		
		createDetailsTable(preface);
		addEmptyLine(preface, 1);
		document.add(preface);
		
		// Start a new page
		document.newPage();
	}

	private void drawLogo(Document document) {
		try {
	    

	       Image image = Image.getInstance("F:"+File.separator+"NUS Mcomp"+File.separator+"Database Tuning"+File.separator+"Project" +
	       		""+File.separator+"Database_Tuning"+File.separator+"" +
	       		"Database_Tuning"+File.separator+"WebContent"+File.separator+"pages"+File.separator+"images"+File.separator+""+File.separator+"log-management.jpg");
	      //Image image = Image.getInstance("/Database_Tuning/WebContent/pages/images/log-management.jpg");  
	      document.add(image);
	      image.scaleAbsolute(150f, 150f);
	    } catch(Exception e){
	      e.printStackTrace();
	    }
		
	}

	private void createPerformanceParametersTable(Paragraph subCatPart,RecordBean bean)
			throws BadElementException {
		int columnCount1 = 0,columnCount2 = 0;
		if(bean.getRbean_list().get(0).getRbean_list() !=null){
			columnCount1=bean.getRbean_list().get(0).getRbean_list().size();
			System.out.println("in generate report: createPerformanceParameterTable"+columnCount1);
		}
		if(bean.getRbean_list().get(1).getRbean_list() !=null)
		{
			columnCount2=bean.getRbean_list().get(1).getRbean_list().size();
			System.out.println("in generate report: createPerformanceParameterTable"+columnCount2);
		}

		
		for(int k=0;k<2;k++){	

			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(100f);
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			int i=0;
			if(k==0){

				if(columnCount1!=0){
					subCatPart.add(new Paragraph("Quarterly", titleFont10));
					table.addCell(new PdfPCell(new Phrase("Record Time  " , mediumFontBold8)));
					PdfPCell cell = new PdfPCell(new Phrase( "Dictonary Cache Hit Rate ", mediumFontBold8));
					table.addCell(cell);
					table.completeRow();
					for(i=0;i<columnCount1;i++){
						table.addCell(new Phrase(bean.getRbean_list().get(k).getRbean_list().get(i).getRecorded_time() + "",smallNormal8));
						table.addCell(new Phrase(bean.getRbean_list().get(k).getRbean_list().get(i).getRate() + "",smallNormal8));
						table.completeRow();
					}
				}
			}else if(k==1){

				if(columnCount2!=0){
					subCatPart.add(new Paragraph("Hourly", titleFont10));	
					table.addCell(new PdfPCell(new Phrase("Record Time  " , mediumFontBold8)));
					PdfPCell cell = new PdfPCell(new Phrase( "Dictonary Cache Hit Rate ", mediumFontBold8));
					table.addCell(cell);
					table.completeRow();
					for(i=0;i<columnCount2;i++){
						table.addCell(new Phrase(bean.getRbean_list().get(k).getRbean_list().get(i).getRecorded_time() + "",smallNormal8));
						table.addCell(new Phrase(bean.getRbean_list().get(k).getRbean_list().get(i).getRate() + "",smallNormal8));
						table.completeRow();
					}
				}
			}
			subCatPart.add(table);}
	}

	private void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private void createDetailsTable(Paragraph subCatPart)
			throws BadElementException {
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100f);

		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		PdfPCell cell = new PdfPCell(new Phrase("Details", mediumFontBold8));

		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(2);
		table.addCell(cell);

		table.addCell(new PdfPCell(new Phrase("Start Timestamp : " + startDate,
				smallNormal8)));
		table.addCell(new PdfPCell(new Phrase("End Timestamp : " + endDate,
				smallNormal8)));
		table.completeRow();
		subCatPart.add(table);
	}


}
