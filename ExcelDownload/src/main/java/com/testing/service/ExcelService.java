package com.testing.service;

import com.testing.config.Helper;
import com.testing.model.Category;
import com.testing.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ExcelService {

    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getSampleCategories() {
        return IntStream.rangeClosed(1, 1048575)
                .mapToObj(i -> {
                    Category category = new Category();
                    category.setId(i); // Simulated ID
                    category.setTitle("Title " + i);
                    category.setDescription("Description for item " + i);
                    category.setCoverImage("Image" + i + ".jpg");
                    return category;
                })
                .collect(Collectors.toList());
    }

    public ByteArrayInputStream getActualdata() throws IOException {

        List<Category> all = getSampleCategories();
        return Helper.dataToExcel(all);
    }

}
