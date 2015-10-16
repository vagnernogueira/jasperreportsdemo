package br.com.vagnernogueira.jasperreportsdemo;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;

import org.junit.Test;

import com.google.common.collect.Lists;

public class MainTest
{
	private static String source = "G:\\workspaces\\jbdevstudio8\\jasperreportsdemo\\src\\test\\resources\\relatorio-teste.jrxml";

	private static List<TesteDTO> beanCollection = Lists.newArrayList(new TesteDTO("João"), new TesteDTO("Xico"), new TesteDTO("Maria"), new TesteDTO("José"));

	private static Map<String, Object> parameters = new HashMap<String, Object>();
	static
	{
		parameters.put("DATE", new Date());
	}

	@Test
	public void exportPdf()
	{
		String pathPdf = System.getProperty("user.home") + "\\Downloads\\maxima-orcamento.pdf";
		try
		{
			JasperPrint jp = JasperreportsUtil.createReport(source, parameters, beanCollection);
			JasperreportsUtil.exportPdf(jp, pathPdf);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		assertTrue(true);
	}

	// @Test
	public void printDefault()
	{
		try
		{
			JasperPrint jp = JasperreportsUtil.createReport(source, parameters, beanCollection);
			JasperreportsUtil.sendToPrint(jp, false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		assertTrue(true);
	}

	// @Test
	public void print()
	{
		String printerName = "Microsoft XPS Document Writer";
		try
		{
			JasperPrint jp = JasperreportsUtil.createReport(source, parameters, beanCollection);
			JasperreportsUtil.sendToPrint(jp, printerName, false);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		assertTrue(true);
	}
}
