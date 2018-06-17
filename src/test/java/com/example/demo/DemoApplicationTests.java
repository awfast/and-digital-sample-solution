package com.example.demo;

import com.example.demo.dto.ChangePhoneStatusDTO;
import com.example.demo.entity.Customer;
import com.example.demo.service.DemoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ DemoApplication.class })
public class DemoApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	DemoService demoService;

	@Before
	public void setUp() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getAllPhoneNumbers_Expected_8() throws Exception {
		demoService.setDummyData();

		MvcResult mvcResult = this.mockMvc.perform(get("/api/all-phone-numbers"))
				.andExpect(status().isOk())
				.andReturn();

		int size = mvcResult.getResponse().getContentAsString().split(",").length;
		System.out.println(mvcResult.getResponse().getContentAsString());

		Assert.assertEquals(8, size);
	}

	@Test
	public void getAllCustomerPhoneNumbers_Expected_2() throws Exception {
		demoService.setDummyData();

		MvcResult mvcResult = this.mockMvc.perform(get("/api/1234/all-phone-numbers"))
				.andExpect(status().isOk())
				.andReturn();

		System.out.println(mvcResult.getResponse().getContentAsString());
		int size = mvcResult.getResponse().getContentAsString().split(",").length;

		Assert.assertEquals(2, size);
	}

	@Test
	public void changeStatus_Expected_200_And_Active() throws Exception {
		demoService.setDummyData();
		Set<Customer> allCustomers = demoService.getAllDummyCustomerData();

		ChangePhoneStatusDTO dto = new ChangePhoneStatusDTO()
				.setCustomerId("123")
				.setName("Alice")
				.setPhoneNumber("0078384932")
				.setStatus(true);

		Optional<Customer> c = demoService.getCustomer(dto.getCustomerId());
		boolean statusBeforeRequest = c.get().getPhoneDetails().get(0).isActive();

		sendPostRequest(dto, status().isOk());

		boolean statusAfterRequest = c.get().getPhoneDetails().get(0).isActive();

		for(Customer customer: allCustomers) {
			if(customer.getId().equals("123")) {
				Assert.assertEquals("Customer " + customer.getName() + " phone number should be inactive: ", false, statusBeforeRequest);
				Assert.assertEquals("Customer " + customer.getName() + " phone number should now be active: ", true, statusAfterRequest);
			}
		}
	}

	public void sendPostRequest(ChangePhoneStatusDTO dto, ResultMatcher expectedStatus) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson= null;

		requestJson = ow.writeValueAsString(dto);

		this.mockMvc.perform(post("/api/update-phone-number-status")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJson))
				.andExpect(expectedStatus);
	}
}
