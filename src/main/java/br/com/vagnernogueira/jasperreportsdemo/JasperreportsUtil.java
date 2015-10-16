package br.com.vagnernogueira.jasperreportsdemo;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterName;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimplePrintServiceExporterConfiguration;

public class JasperreportsUtil
{
	public static JasperPrint createReport(String pathJrxml, Map<String, Object> parameters, Collection<?> beanCollection) throws Exception
	{
		try
		{
			File reportFile = new File(pathJrxml);
			JasperDesign jasperDesign = JRXmlLoader.load(reportFile);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(beanCollection);
			return JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
		}
		catch (JRException e)
		{
			throw new Exception(e);
		}
	}

	public static void exportPdf(JasperPrint jasperPrint, String pathPdf) throws Exception
	{
		try
		{
			JasperExportManager.exportReportToPdfFile(jasperPrint, pathPdf);
		}
		catch (JRException e)
		{
			throw new Exception(e);
		}
	}

	public static void sendToPrint(JasperPrint jasperPrint, boolean withPrintDialog) throws Exception
	{
		try
		{
			JasperPrintManager.printReport(jasperPrint, withPrintDialog);
		}
		catch (JRException e)
		{
			throw new Exception(e);
		}
	}

	public static void sendToPrint(JasperPrint jasperPrint, String printerName, boolean withPrintDialog) throws Exception
	{
		try
		{
			PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
			printRequestAttributeSet.add(MediaSizeName.ISO_A4);
			PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
			printServiceAttributeSet.add(new PrinterName(printerName, null));
			JRPrintServiceExporter exporter = new JRPrintServiceExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			SimplePrintServiceExporterConfiguration configuration = new SimplePrintServiceExporterConfiguration();
			configuration.setPrintRequestAttributeSet(printRequestAttributeSet);
			configuration.setPrintServiceAttributeSet(printServiceAttributeSet);
			configuration.setDisplayPageDialog(false);
			configuration.setDisplayPrintDialog(withPrintDialog);
			exporter.setConfiguration(configuration);
			exporter.exportReport();
		}
		catch (JRException e)
		{
			throw new Exception(e);
		}
	}
}
