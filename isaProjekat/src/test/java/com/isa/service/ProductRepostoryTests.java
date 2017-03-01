package com.isa.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.isa.entity.Product;
import com.isa.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepostoryTests {

	@Autowired
	private ProductRepository pr;

	@Test
	public void getProductsForRestaurant() {
		List<Product> products = pr.getProductsForRestaurant((long) 1);
		assertEquals(products.size(), 3);
	}

	@Test
	public void seeIfBelongsToRestaurant() {
		Product product = pr.seeIfBelongsToRestaurant((long) 1, (long) 1);
		assertEquals(product.getId(), (long) 1);
	}

	@Test
	public void getProductsForRequestOffer() {
		List<Product> products = pr.getProductsForRequestOffer((long) 1);
		assertEquals(products.size(), 1);
	}
	
	@Test
	public void findProductByRestaurantAndName() {
		List<Product> products = pr.findProductByRestaurantAndName("Coca cola",(long) 1);
		assertEquals(products.size(), 1);
	}
}
