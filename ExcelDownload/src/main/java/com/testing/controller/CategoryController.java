package com.testing.controller;

import com.testing.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ExcelService excelService;

    @RequestMapping("/excel")
    public ResponseEntity<Resource> download() throws IOException {

        String filename= "categories.xlsx";
        ByteArrayInputStream actualdata = excelService.getActualdata();
        InputStreamResource file= new InputStreamResource(actualdata);

        ResponseEntity<Resource> body = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
        return body;
    }

}
