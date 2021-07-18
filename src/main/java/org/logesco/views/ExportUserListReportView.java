/**
 * 
 */
package org.logesco.views;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @author cedrickiadjeu
 *
 */
public class ExportUserListReportView extends AbstractPdfView {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.document.AbstractPdfView
	 * #buildPdfDocument(java.util.Map, com.lowagie.text.Document, com.lowagie.text.pdf.PdfWriter,
	 *  javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	
	/*******************
	 * Dans l'objet datasource se trouve les données qu'on désire affiche dans le pdf ou le fichier excel
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, 
			HttpServletRequest request,  HttpServletResponse response) throws Exception {
		
		response.setHeader("content-Disposition", "attachement; filename=\"listee.pdf\"");
		
		Table table = new Table(4);
		
		Map<String, Object> datasource=(Map<String, Object>) model.get("datasource");
		
		table.addCell((String)datasource.get("col1"));
		table.addCell((String)datasource.get("col2"));
		table.addCell((String)datasource.get("col3"));
		table.addCell("LastName");

		document.add(table);
		
	}

}
