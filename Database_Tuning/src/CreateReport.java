import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.dbtune.project.data.entities.DashboardEntity;
import com.dbtune.project.data.exceptions.DbTuneGenericException;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;


public class CreateReport {
	
	@SuppressWarnings("deprecation")
	private Date startDate=new Date("11/04/2013");
	@SuppressWarnings("deprecation")
	private Date endDate=new Date("12/04/2013");
	private int value=10;
	private String selectedParameters="Dictionary Hit Rate";
	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
	private static String REPORT_PATH = "D:/DbTune";
	private static String REPORT_NAME = "DBPerformanceReport.pdf";

	private static Font smallNormal6 = new Font(Font.FontFamily.TIMES_ROMAN, 6,
			Font.NORMAL);
	private static Font small6 = new Font(Font.FontFamily.TIMES_ROMAN, 6,
			Font.NORMAL);
	private static Font mediumFontBold8 = new Font(Font.FontFamily.TIMES_ROMAN,
			8, Font.BOLD);
	private static Font titleFont10 = new Font(Font.FontFamily.TIMES_ROMAN, 10,
			Font.BOLD);
	private static Font redFont12 = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.NORMAL, BaseColor.RED);
	private static Font bigFont14 = new Font(Font.FontFamily.TIMES_ROMAN, 14,
			Font.BOLD);

	public String generateReport() {
		System.out.println("Executing generateReport()");
		createPDF();
		return "";

	}
	
	public String createPDF() {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(REPORT_PATH + "/" + REPORT_NAME));
			document.open();
			// drawLogo(document);
			addMetaData(document);
			addFirstPage(document);

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "created";
	}

	private void addMetaData(Document document) {
		document.addTitle("DB Performance Parameters");
		document.addSubject("DB Performance Parameters");
		document.addAuthor("Rajesh");
		document.addCreator("Database Tuning Project");
	}

	private void addFirstPage(Document document) throws DocumentException {
		Paragraph preface = new Paragraph();
		// We add one empty line
		addEmptyLine(preface, 1);
		// Lets write a big header
		preface.add(new Paragraph("DB Paramters Report", titleFont10));
		addEmptyLine(preface, 1);

		createDetailsTable(preface);
		addEmptyLine(preface, 1);

		createPerformanceParametersTable(preface);
		document.add(preface);
		// Start a new page
		// document.newPage();
	}
	
	private void createPerformanceParametersTable(Paragraph subCatPart)
			throws BadElementException {
		int columnCount = 0;
		
		/*if (selectedParameters != null) {
			columnCount = selectedParameters.size();
		}*/
		
		PdfPTable table = new PdfPTable(columnCount+1);
		table.setWidthPercentage(100f);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		table.addCell(new PdfPCell(new Phrase("Record Time : " , small6)));
		
		/*for (CustomParameter param : selectedParameters) {
			PdfPCell cell = new PdfPCell(new Phrase(param.getParameterName() + " : ", small6));
			
			table.addCell(cell);
		}*/

		GregorianCalendar calStart = new GregorianCalendar();
		calStart.setTime(startDate);
		/*calStart.set(GregorianCalendar.HOUR_OF_DAY, Integer
				.parseInt(startHours));
		calStart.set(GregorianCalendar.MINUTE, Integer.parseInt(startMinutes));
		calStart.set(GregorianCalendar.SECOND, Integer.parseInt(startSeconds));*/
		Date newStartDate = calStart.getTime();

		GregorianCalendar calEnd = new GregorianCalendar();
		calEnd.setTime(endDate);
		/*
		calEnd.set(GregorianCalendar.HOUR_OF_DAY, Integer.parseInt(endHours));
		calStart.set(GregorianCalendar.MINUTE, Integer.parseInt(endMinutes));
		calEnd.set(GregorianCalendar.SECOND, Integer.parseInt(endSeconds));*/
		Date newEndDate = calEnd.getTime();
		/*List<DashboardEntity> records = null;
		try {
			records = dashboardService.fetchInDateRange(newStartDate,
					newEndDate, Integer.parseInt(units));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DbTuneGenericException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//if (records != null) {
			//for (DashboardEntity record : records) {
				table.addCell(new Phrase("11/04/2013" + "", small6));
				/*for (CustomParameter param : selectedParameters) {
					
					if (param.getParameterName().equalsIgnoreCase(
							DbTuneWebConstants.LIBRARY_CACHE_HIT)) {
						table.addCell(new Phrase(record.getmLibraryCacheHit()+"" , small6));
					} else if (param.getParameterName().equalsIgnoreCase(
							DbTuneWebConstants.LIBRARY_CACHE_MISS)) {
						table.addCell(new Phrase(record.getmLibraryCacheMiss() + "", small6));
					} else if (param.getParameterName().equalsIgnoreCase(
							DbTuneWebConstants.DICTIONARY_CACHE_HIT)) {
						table.addCell(new Phrase(record.getmDictCacheHit() + "", small6));
					} else if (param.getParameterName().equalsIgnoreCase(
							DbTuneWebConstants.DICTIONARY_CACHE_MISS)) {
						table.addCell(new Phrase(record.getmDictCacheMiss() + "",small6));
					} else if (param.getParameterName().equalsIgnoreCase(
							DbTuneWebConstants.BUFFER_CACHE_CONSISTENT_GETS)) {
						table.addCell(new Phrase(record.getmBufCacheConsistentGets() + "",small6));
					} else if (param.getParameterName().equalsIgnoreCase(
							DbTuneWebConstants.BUFFER_CACHE_DB_BLOCK_GETS)) {
						table.addCell(new Phrase(record.getmBufCacheDBBlkGets() + "",small6));
					} else if (param.getParameterName().equalsIgnoreCase(
							DbTuneWebConstants.BUFFER_CACHE_PHYSICAL_READS)) {
						table.addCell(new Phrase(record.getmBufCachePhysicalReads() + "",small6));
					} else if (param.getParameterName().equalsIgnoreCase(
							DbTuneWebConstants.REDO_LOG_BUFFER)) {
						table.addCell(new Phrase(record.getmRedoLogBuffer() + "",small6));
					} else if (param.getParameterName().equalsIgnoreCase(
							DbTuneWebConstants.IN_MEMORY_SORT)) {
						table.addCell(new Phrase(record.getmInMemorySort() + "",small6));
					} else if (param.getParameterName().equalsIgnoreCase(
							DbTuneWebConstants.ROLL_BACK_SEGMENTS)) {
						table.addCell(new Phrase(record.getmRollBackSeg() + "",small6));
					}
				}*/
		
		subCatPart.add(table);
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
		PdfPCell cell = new PdfPCell(new Phrase("Details", small6));

		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(2);
		table.addCell(cell);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
		String fromDate = sdf.format(startDate);
		String lastDate = sdf.format(endDate);

		table.addCell(new PdfPCell(new Phrase("Start Date : " + fromDate,
				small6)));
		table.addCell(new PdfPCell(new Phrase("End Date : " + lastDate,
				small6)));
		/*table.addCell(new PdfPCell(new Phrase("Start Hours : " + startHours,
				small6)));
		table.addCell(new PdfPCell(new Phrase("End Hours : " + endHours,
				small6)));
		table.addCell(new PdfPCell(new Phrase(
				"Start Minutes : " + startMinutes, small6)));
		table.addCell(new PdfPCell(new Phrase("End Minutes : " + endMinutes,
				small6)));
		table.addCell(new PdfPCell(new Phrase(
				"Start Seconds : " + startSeconds, small6)));
		table.addCell(new PdfPCell(new Phrase("End Seconds : " + endSeconds,
				small6)));*/
		table.addCell(new PdfPCell(new Phrase("Units Averaged Over : " + value,
				small6)));
		table.addCell(new PdfPCell(new Phrase("Units Averaged Over : " + value,
				small6)));
		subCatPart.add(table);
	}

public static void main(String[] args){
	CreateReport cr=new CreateReport();
	cr.generateReport();

}
}
