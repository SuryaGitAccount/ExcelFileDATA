package com.example.service;
import com.example.model.Transaction;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ExcelDownloadService {

    // Method to generate 100,000 sample records
    public List<Transaction> generateSampleTransactions() {
        return IntStream.rangeClosed(1, 100000)
                .mapToObj(i -> new Transaction((long) i, "Name" + i, "email" + i + "@example.com", i * 10.0))
                .collect(Collectors.toList());
    }

    public ByteArrayInputStream generateExcelFile() {
        // Generate 100,000 sample transactions
        List<Transaction> transactions = generateSampleTransactions();

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Transactions");

            // Header
            Row headerRow = sheet.createRow(0);
            String[] columns = {"ID", "Name", "Email", "Amount"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Data rows
            int rowIdx = 1;
            for (Transaction transaction : transactions) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(transaction.getId());
                row.createCell(1).setCellValue(transaction.getName());
                row.createCell(2).setCellValue(transaction.getEmail());
                row.createCell(3).setCellValue(transaction.getAmount());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
