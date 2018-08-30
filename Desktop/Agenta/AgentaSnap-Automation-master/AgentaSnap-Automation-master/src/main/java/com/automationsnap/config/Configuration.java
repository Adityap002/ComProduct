package com.automationsnap.config;


public class Configuration {
	
	public static class url {
		// create inner class for url
		public static String app_url = "https://localhost:8080/";
	}

	public static class browser {
		// create browser variable
		public static String browse = "chrome";
	}

	public static class excelSetUp {
		// create browser variable
		public static String excelLocation = ".\\src\\main\\resources\\";
		public static String fileName = "ExcelData.xlsx";
	}
	
	public static class extentReportSetUp {
		// create browser variable
		public static String extentReportLocation = ".\\src\\main\\java\\com\\agentaDemo\\resource\\";
		public static String reportName = "report.html";
	}

	public static class jsonSetUp {
		// create browser variable
		public static String jsonLocation = "C:\\Users\\aditya.p\\Desktop\\Automate2\\agentaSnapDemo\\src\\main\\java\\com\\agentaDemo\\resource\\";
		public static String fileName = "Testjson.json";
	}
}
