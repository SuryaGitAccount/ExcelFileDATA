package com.example.controller;

import com.example.service.ExcelDownloadService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
public class ExcelDownloadController {

    @Autowired
    private ExcelDownloadService excelDownloadService;

    @GetMapping("/downloadExcel")
    public ResponseEntity<byte[]> downloadExcel() throws IOException {
        // Generate the Excel file
        ByteArrayInputStream in = excelDownloadService.generateExcelFile();
        byte[] bytes = in.readAllBytes();

        // Set headers for the file download
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=transaction_data.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(bytes);
    }
}
