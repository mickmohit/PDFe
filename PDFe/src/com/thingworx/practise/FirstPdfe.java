package com.thingworx.practise;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.thingworx.logging.LogUtilities;
import com.thingworx.metadata.FieldDefinition;
import com.thingworx.metadata.annotations.ThingworxPropertyDefinition;
import com.thingworx.metadata.annotations.ThingworxPropertyDefinitions;
import com.thingworx.metadata.annotations.ThingworxServiceDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceResult;
import com.thingworx.relationships.RelationshipTypes;
import com.thingworx.things.Thing;
import com.thingworx.types.BaseTypes;
import com.thingworx.types.InfoTable;
import com.thingworx.types.collections.ValueCollection;
import com.thingworx.types.primitives.BooleanPrimitive;
import com.thingworx.types.primitives.NumberPrimitive;
import com.thingworx.types.primitives.StringPrimitive;

@ThingworxPropertyDefinitions(
		properties=
		{
	@ThingworxPropertyDefinition(name="PDFrate" , description="PDF rate property", category="", baseType="STRING", aspects ={"isReadOnly:false", "defaultValue:wakow"}),	
	@ThingworxPropertyDefinition(name="PDFN" , description="PDF Num", category="", baseType="NUMBER", aspects ={"isReadOnly:false", "defaultValue:70.0"}),	
				
		}
		)
public class FirstPdfe extends Thing {
	
	private static Logger _logger = LogUtilities.getInstance().getApplicationLogger(FirstPdfe.class);
	private static String FILE = "C:/Inservice_cases/FirstPdf3.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    String s1;
    Number n;
    
    
	/*@ThingworxServiceDefinition(name = "pdfcrea", description = "", category = "", isAllowOverride = false, aspects = {
			"isAsync:false" })
	@ThingworxServiceResult(name = "Result", description = "", baseType = "INFOTABLE", aspects = {
			"isEntityDataShape:true" })
	public InfoTable pdfcrea() throws Exception, DocumentException {
		_logger.trace("Entering Service: pdfcrea");
		_logger.trace("Exiting Service: pdfcrea");
		
		
		InfoTable it= new InfoTable();
		it.getDataShape().addFieldDefinition(new FieldDefinition("SN", BaseTypes.NUMBER));
		it.getDataShape().addFieldDefinition(new FieldDefinition("Value", BaseTypes.STRING));
		ValueCollection vc= new ValueCollection();
		ValueCollection vc1= new ValueCollection();
		
		
		InfoTable s=this.getClient().readProperty(RelationshipTypes.ThingworxEntityTypes.Things, this.getName(), "lastConnection", Integer.valueOf(10000));
		s.getDataShape().addFieldDefinition(new FieldDefinition("SN", BaseTypes.BOOLEAN));
		//it.getDataShape().addFieldDefinition(new FieldDefinition("Value", BaseTypes.STRING));
		
		ValueCollection vc= new ValueCollection();
		vc.put("SN", new BooleanPrimitive(this.getClient().isConnected()));
		s.addRow(vc);
		
		s1=getPDFrate() ;
		n=getPDFN();
		
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+s1+ "-----"+n);
		
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(FILE));
        document.open();
        addMetaData(document);
        addTitlePage(document);
        addContent(document);
        document.close();
		return null;
	}*/
	
	@ThingworxServiceDefinition(name = "pdfcr", description = "", category = "", isAllowOverride = false, aspects = {
	"isAsync:false" })
@ThingworxServiceResult(name = "Result", description = "", baseType = "NOTHING", aspects = {})
public void pdfcr() throws Exception {
_logger.trace("Entering Service: pdfcr");
_logger.trace("Exiting Service: pdfcr");

Document document = new Document();
PdfWriter.getInstance(document, new FileOutputStream(FILE));
document.open();
addMetaData(document);
addTitlePage(document);
addContent(document);
document.close();
	
	}
	
	
	public double getPDFN() throws Exception {
		return   (Double) getProperty("PDFN").getValue().getValue();
	}
	
	public String getPDFrate() throws Exception {
		return   (String) getProperty("PDFrate").getValue().getValue();
	}
	public void setPDFN() {
		try
		{
			setPropertyValue("PDFN", new NumberPrimitive(12.0));	
			//super.updateSubscribedProperties(5000);
		}
		catch(Exception e)
		{
			_logger																									.error("Failed to set default value", e);
		}
	}
	public void setPDFrate() {
		try
		{
			setPropertyValue("PDFrate", new StringPrimitive("Mohittttttt"));	
			//super.updateSubscribedProperties(5000);
		}
		catch(Exception e)
		{
			_logger.error("Failed to set default value", e);
		}
	}
	
	 private static void addMetaData(Document document) {
	        document.addTitle("My first PDF");
	        document.addSubject("Using iText");
	        document.addKeywords("Java, PDF, iText");
	        document.addAuthor("Mohit D.");
	        document.addCreator("Mohit D.");
	    }

	 private static void addTitlePage(Document document)
	            throws DocumentException {
	        Paragraph preface = new Paragraph();
	        // We add one empty line
	        addEmptyLine(preface, 1);
	        // Lets write a big header
	        preface.add(new Paragraph("Title of the document--Dummy Test", catFont));

	        addEmptyLine(preface, 1);
	        // Will create: Report generated by: _name, _date
	        
	        
	        
	        /*preface.add(new Paragraph(
	                "Report generated by: " + System.getProperty("user.name") + ", "+ "s1----"  + "~~~~~~~" +s.getRow(0).getPrimitive("lastConnection").getValue() + "::" + s.getRow(1).getPrimitive("SN").getValue()+ "-------" + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	                smallBold));
	        */
	        preface.add(new Paragraph(
	                "Report generated by: " + System.getProperty("user.name") + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	                smallBold));
	        
	        addEmptyLine(preface, 3);
	        preface.add(new Paragraph(
	                "This document describes something which is very important ",
	                smallBold));

	        addEmptyLine(preface, 8);

	        preface.add(new Paragraph(
	                "This document is a preliminary version and not subject to your license agreement or any other agreement.",
	                redFont));

	        document.add(preface);
	        // Start a new page
	        document.newPage();
	    }
	 
	 private static void addContent(Document document) throws DocumentException {
	        Anchor anchor = new Anchor("First Chapter1111", catFont);
	        anchor.setName("First Chapter22");

	        // Second parameter is the number of the chapter
	        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

	        Paragraph subPara = new Paragraph("Subcategory 1", subFont);
	        Section subCatPart = catPart.addSection(subPara);
	        subCatPart.add(new Paragraph("Hello"));

	        subPara = new Paragraph("Subcategory 2", subFont);
	        subCatPart = catPart.addSection(subPara);
	        subCatPart.add(new Paragraph("Paragraph 1"));
	        subCatPart.add(new Paragraph("Paragraph 2"));
	        subCatPart.add(new Paragraph("Paragraph 3"));

	        // add a list
	        createList(subCatPart);
	        Paragraph paragraph = new Paragraph();
	        addEmptyLine(paragraph, 5);
	        subCatPart.add(paragraph);

	        // add a table
	        createTable(subCatPart);

	        // now add all this to the document
	        document.add(catPart);

	        // Next section
	        anchor = new Anchor("Second Chapter22", catFont);
	        anchor.setName("Second Chapter333");

	        // Second parameter is the number of the chapter
	        catPart = new Chapter(new Paragraph(anchor), 1);

	        subPara = new Paragraph("Subcategory", subFont);
	        subCatPart = catPart.addSection(subPara);
	        subCatPart.add(new Paragraph("This is a very important message"));

	        // now add all this to the document
	        document.add(catPart);

	    }
	
	 private static void createTable(Section subCatPart)
	            throws BadElementException {
	        PdfPTable table = new PdfPTable(3);

	        // t.setBorderColor(BaseColor.GRAY);
	        // t.setPadding(4);
	        // t.setSpacing(4);
	        // t.setBorderWidth(1);

	        PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);

	        c1 = new PdfPCell(new Phrase("Table Header 2"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);

	        c1 = new PdfPCell(new Phrase("Table Header 3"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);
	        table.setHeaderRows(1);

	        table.addCell("1.0");
	        table.addCell("1.1");
	        table.addCell("1.2");
	        table.addCell("2.1");
	        table.addCell("2.2");
	        table.addCell("2.3");

	        subCatPart.add(table);

	    }
	 

	    private static void createList(Section subCatPart) {
	        List list = new List(true, false, 10);
	        list.add(new ListItem("First point"));
	        list.add(new ListItem("Second point"));
	        list.add(new ListItem("Third point"));
	        subCatPart.add(list);
	    }
	    

	    private static void addEmptyLine(Paragraph paragraph, int number) {
	        for (int i = 0; i < number; i++) {
	            paragraph.add(new Paragraph(" "));
	        }
	    }

		


}


