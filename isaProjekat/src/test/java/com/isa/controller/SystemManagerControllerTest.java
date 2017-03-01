package com.isa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemManagerControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	

	@Test
	public void testGetRestaurantManagersForRestaurant() throws Exception {

		this.mvc.perform(get("/systemmanagers/getRestaurantManagersForRestaurant?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteRestaurant() throws Exception {

		this.mvc.perform(delete("/systemmanagers/deleteRestaurant?id=1")).andExpect(status().isOk());
	}
}
