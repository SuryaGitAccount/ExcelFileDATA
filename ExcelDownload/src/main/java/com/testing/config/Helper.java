package com.testing.config;

import com.testing.model.Category;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class Helper {
//    public static String[] HEADERS={
//        "ID",
//            "TITLE",
//            "DESCRIPTION",
//            "COVER IMAGE"
//
//    };
//    public static String SHEET_NAME="category_excel_data";
//
//
//    public static ByteArrayInputStream dataToExcel(List<Category> list) throws IOException {
//        Workbook workbook = new XSSFWorkbook();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        try{
//
//            //create workbook
//
//            //create sheet
//            Sheet sheet = workbook.createSheet(SHEET_NAME);
//            //create row for headers
//            Row row = sheet.createRow(0);
//
//            for(int i=0;i < HEADERS.length;i++){
//                Cell cell = row.createCell(i);
//                cell.setCellValue(HEADERS[i]);
//            }
//            //create row for values
//            int rowIndex=1;
//            for (Category c:list){
//
//                Row dataRow = sheet.createRow(rowIndex);
//                rowIndex ++;
//                dataRow.createCell(0).setCellValue(c.getId());
//                dataRow.createCell(1).setCellValue(c.getTitle());
//                dataRow.createCell(2).setCellValue(c.getDescription());
//                dataRow.createCell(3).setCellValue(c.getCoverImage());
//            }
//            workbook.write(out);
//            return new ByteArrayInputStream(out.toByteArray());
//
//
//        }catch (IOException e){
//            e.printStackTrace();
//            System.out.println("testing");
//            return null;
//        } finally {
//            workbook.close();
//
//        }
//    }
private static final String SHEET_NAME = "Categories";
    private static final String[] HEADERS = {"ID", "Title", "Description", "CoverImage"};

    public static ByteArrayInputStream dataToExcel(List<Category> list) throws IOException {
        // Use SXSSFWorkbook to handle large datasets
        SXSSFWorkbook workbook = new SXSSFWorkbook(100); // Keep only 100 rows in memory at a time
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // Create a sheet
            Sheet sheet = workbook.createSheet(SHEET_NAME);

            // Create row for headers
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < HEADERS.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(HEADERS[i]);
            }

            // Create rows for the actual data
            int rowIndex = 1;
            for (Category c : list) {
                Row dataRow = sheet.createRow(rowIndex++);
                dataRow.createCell(0).setCellValue(c.getId());
                dataRow.createCell(1).setCellValue(c.getTitle());
                dataRow.createCell(2).setCellValue(c.getDescription());
                dataRow.createCell(3).setCellValue(c.getCoverImage());
            }

            // Write data to the output stream
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        } finally {
            workbook.dispose(); // Clean up temporary files
            out.close();        // Close the output stream
        }
    }

}
