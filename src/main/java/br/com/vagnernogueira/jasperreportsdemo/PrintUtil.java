package br.com.vagnernogueira.jasperreportsdemo;

import java.util.List;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import com.google.common.collect.Lists;

public class PrintUtil
{
	public static List<String> getPrintsName() {
		List<String> result = Lists.newArrayList();
		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

        for (PrintService printer : printServices) {
        	result.add(printer.getName());
        }
        return result;
	}
	
	public static String getDefault() {
		PrintService service = PrintServiceLookup.lookupDefaultPrintService();
		if (service != null) {
			return service.getName();
		}
		return null;
	}
}
