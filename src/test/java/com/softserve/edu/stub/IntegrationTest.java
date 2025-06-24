package com.softserve.edu.stub;

import com.softserve.edu.dao.ProductDao;
import com.softserve.edu.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegrationTest {

	@Test
	public void checkLastDigits() {
		ProductDao productDao = new ProductDao();
		ProductService productService = new ProductService(productDao);
		String actual;
		String expected;
		//
		expected = "181";
		actual = productService.getLastDigits();
		//
		Assertions.assertEquals(expected, actual, "LastDigits ERROR");
	}
	
}
