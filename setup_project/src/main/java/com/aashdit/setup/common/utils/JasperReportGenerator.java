package com.aashdit.setup.common.utils;
//package com.aashdit.prod.heads.common.utils;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletResponse;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import com.aashdit.prod.heads.framework.core.ServiceOutcome;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Component
//public class JasperReportGenerator {
//
//	
//	@Value("${UPLOAD.JASPER.FILE.PATH}")
//	private String uploadJasperFilePath;
//	
//	@Autowired	
//	private ServletContext servletContext;
//	
//	@Autowired
//	private DataSource dataSource;
//
//	public ServiceOutcome<List<String>> listToExcelInJasperUsingBatchProcess(List<?> list, String reportFilePath, String reportName, int batchSize) throws IOException, JRException {
//	    ServiceOutcome<List<String>> outcome = new ServiceOutcome<>();
//	    List<String> fileNames = new ArrayList<>();
//	    Boolean flag = true;
//
//	    try {
//	        String filePath = servletContext.getRealPath("/WEB-INF/jasperReports/" + reportFilePath);
//	        File file = new File(filePath);
//	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//
//	        // Calculate the number of batches
//	        int totalRecords = list.size();
//	        int totalBatches = (int) Math.ceil((double) totalRecords / batchSize);
//
//	        File checkFolderPath = new File(uploadJasperFilePath);
//	        if (!checkFolderPath.exists()) {
//	            checkFolderPath.mkdirs();
//	        }
//
//	        for (int batchNumber = 0; batchNumber < totalBatches; batchNumber++) {
//	            int startIndex = batchNumber * batchSize;
//	            int endIndex = Math.min((batchNumber + 1) * batchSize, totalRecords);
//
//	            List<?> batchList = list.subList(startIndex, endIndex);
//	            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(batchList);
//
//	            Map<String, Object> parameters = new HashMap<>();
//	            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
//
//	            String fileName = CommonUploadFile.addCurrenDateTimeToDocAndRenameItModified(uploadJasperFilePath + File.separator, reportName + "_Batch" + (batchNumber + 1) + ".xlsx");
//
//	            try (FileOutputStream fos = new FileOutputStream(fileName)) {
//	                JRXlsxExporter exporter = new JRXlsxExporter();
//	                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//	                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(fos));
//	                SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
//	                configuration.setOnePagePerSheet(false);
//	                configuration.setDetectCellType(true);
//	                configuration.setCollapseRowSpan(false);
//	                exporter.setConfiguration(configuration);
//	                exporter.exportReport();
//	                System.out.println("Batch " + (batchNumber + 1) + " exported to Excel (XLSX) successfully.");
//	                fileNames.add(fileName);
//	            }
//	        }
//	    } catch (FileNotFoundException e) {
//	        e.printStackTrace();
//	        flag = false;
//	    } catch (JRException e) {
//	        e.printStackTrace();
//	        flag = false;
//	    }
//
//	    outcome.setData(fileNames);
//	    outcome.setOutcome(flag);
//	    return outcome;
//	}
//
//	
//	public ServiceOutcome<String> listToExcelInJasper(List<?> list, String reportFilePath, String reportName) throws IOException, JRException {
//	    ServiceOutcome<String> outcome = new ServiceOutcome<>();
//	    Boolean flag = true;
//	    String fileName = "";
//
//	    try {
//	        String filePath = servletContext.getRealPath("/WEB-INF/jasperReports/" + reportFilePath);
//	        File file = new File(filePath);
//	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//	        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
//	        Map<String, Object> parameters = new HashMap<>();
//	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
//
//	        File checkFolderPath = new File(uploadJasperFilePath);
//	        if (!checkFolderPath.exists()) {
//	            checkFolderPath.mkdirs();
//	        }
//
//	        fileName = CommonUploadFile.addCurrenDateTimeToDocAndRenameItModified(uploadJasperFilePath + File.separator, reportName + ".xlsx");
//
//	        try (FileOutputStream fos = new FileOutputStream(fileName)) {
//	            JRXlsxExporter exporter = new JRXlsxExporter();
//	            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//	            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(fos));
//	            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
//	            configuration.setOnePagePerSheet(false); // If you want multiple sheets
//	            configuration.setDetectCellType(true); // Detect cell data types
//	            configuration.setCollapseRowSpan(false);
//	            configuration.setIgnorePageMargins(false);
//	            exporter.setConfiguration(configuration);
//	            exporter.exportReport();
//	            System.out.println("Report exported to Excel (XLSX) successfully.");
//	        }
//
//	    } catch (FileNotFoundException e) {
//	        e.printStackTrace();
//	        flag = false;
//	    } catch (JRException e) {
//	        e.printStackTrace();
//	        flag = false;
//	    }
//
//	    outcome.setData(fileName);
//	    outcome.setOutcome(flag);
//	    return outcome;
//	}
//
////    public ServiceOutcome<String> listToExcelInJasper(List<?> list,String reportFilePath,String reportName) throws IOException, JRException {
////    	ServiceOutcome<String> outcome = new ServiceOutcome<>();
////    	Boolean flag = true;
////    	String fileName = "";
////    	try {
////			String filePath = servletContext.getRealPath("/WEB-INF/jasperReports/"+reportFilePath);
////			File file = new File(filePath);
////			JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
////			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);
////			Map<String, Object> parameters = new HashMap();
////		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
////			File checkFolderPath = new File(rb.getString("UPLOAD.JASPER.FILE.PATH"));
////			if (!checkFolderPath.exists()) {
////				checkFolderPath.mkdirs();
////			}
////			fileName=CommonUploadFile.addCurrenDateTimeToDocAndRenameItModified(rb.getString("UPLOAD.JASPER.FILE.PATH")+ File.separator,reportName+".xlsx");
////			JRXlsxExporter exporter = new JRXlsxExporter();
////			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
////			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new FileOutputStream(fileName)));
////			SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
////			configuration.setOnePagePerSheet(false); // If you want multiple sheets
////			configuration.setDetectCellType(true); // Detect cell data types
////			configuration.setCollapseRowSpan(false);
////			exporter.setConfiguration(configuration);
////			exporter.exportReport();
////			System.out.println("Report exported to Excel (XLSX) successfully.");
////		} catch (FileNotFoundException e) {
////			e.printStackTrace();
////		} catch (JRException e) {
////			e.printStackTrace();
////		}
////    	outcome.setData(fileName);
////		outcome.setOutcome(flag);
////		return outcome;
////    }
//
//    public ServiceOutcome<String> reportForJasper(String reportFormat ,String reportFilePath,String reportName,Map map,HttpServletResponse response){
//    	ServiceOutcome<String> outcome = new ServiceOutcome<>();
//    	Boolean flag = true;
//    	String fileName = "";
//    	Connection connection = null;
//		try {
//			String filePath = servletContext.getRealPath("/WEB-INF/jasperReports/"+reportFilePath);
//			File file = new File(filePath);
//			JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
//			connection = dataSource.getConnection();
////			if(connection == null) {
////				connection = getDbaseConnection();
////			}
//			JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,map,connection);
//			File checkFolderPath = new File(uploadJasperFilePath);
//			if (!checkFolderPath.exists()) {
//				checkFolderPath.mkdirs();
//			}
//			if(reportFormat.equals("PDF")) {
//				fileName = CommonUploadFile.addCurrenDateTimeToDocAndRenameItModified_OLD(checkFolderPath.getAbsolutePath() + File.separator +reportName, "pdf");
//				JasperExportManager.exportReportToPdfFile(jasperPrint,fileName);
//				fileName = new File(fileName).getName();
//				closeDataBaseConnection(connection);
//			}else if(reportFormat.equals("EXCEL")) {
//				JRXlsxExporter exporter = new JRXlsxExporter();
//				exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//				fileName=CommonUploadFile.addCurrenDateTimeToDocAndRenameItModified_OLD(uploadJasperFilePath + File.separator+reportName,"xlsx");
//				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new FileOutputStream(fileName)));
//				SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
//				configuration.setOnePagePerSheet(false); // 
//				configuration.setDetectCellType(true); // 
//				exporter.setConfiguration(configuration);
//				exporter.exportReport();
//				fileName = new File(fileName).getName();
//				closeDataBaseConnection(connection);
//		    }else {
//		    	HtmlExporter htmlExporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
//		    	htmlExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//		    	htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
//		    	htmlExporter.exportReport();
//		    	
//		    }
//		} catch (Exception e) {
//			flag = false;
//			log.error("Exception Occured in JasperReportGenerator at reportForJasper() ==>" + e);
//		} 
//		outcome.setData(fileName);
//		outcome.setOutcome(flag);
//		return outcome;
//    }
//	
////	private  Connection getDbaseConnection()
////	{
////		Connection connection = null;
////		try {
////			connection = DriverManager.getConnection(rb.getString("db.url"),rb.getString("db.username"),rb.getString("db.password"));
////			if(Optional.ofNullable(connection).isPresent()) {
////				log.info("Database Connection Open For Jasper Report.");
////			}
////		} catch (SQLException e) {
////			log.error("Exception Occured in JasperReportGenerator at getDbaseConnection() ==>"+ e);
////		}
////		return connection;
////	}
//	
//	private void closeDataBaseConnection(Connection connection)
//	{
//		try {
//			if(Optional.ofNullable(connection).isPresent()) {
//				connection.close();
//			}
//		}catch(Exception e) {
//			log.error("Exception Occured in JasperReportGenerator at closeDataBaseConnection() ==>"+ e);
//		}
//	}
//}
