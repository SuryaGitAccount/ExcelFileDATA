package com.testing;

import com.testing.repository.CategoryRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExcelDownloadApplicationTests {


	@Autowired
	private CategoryRepo categoryRepo;


	@Test
	void contextLoads() {
	}


	@Test
	public void testrepo(){
		System.out.println("test passed");
		categoryRepo.findAll().forEach(ca->System.out.println(ca.getTitle()));
	}

}
