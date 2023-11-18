package com.neusoft.elmboot.controller.controller;

import com.neusoft.elmboot.controller.CartController;
import jakarta.annotation.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CartControllerTest {
    protected Logger logger = LoggerFactory.getLogger(CartControllerTest.class);
    @Resource
    CartController doTestController;

    private MockMvc mockMvc;

    @Resource
    private ControllerTest controllerTest;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(doTestController).build();
    }

    @Test
    public void getCartByUserId() throws Exception {
        ResultActions ractions = mockMvc.perform(
                MockMvcRequestBuilders.get("/cart")
                        .param("userId", "05fcdde2-45b8-4efe-a8e6-c09f60ac92cc")
        );
        MvcResult mvcResult = controllerTest.dotest(ractions);
        logger.info("调用返回的结果：{}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void saveCart() throws Exception {
        ResultActions ractions = mockMvc.perform(
                MockMvcRequestBuilders.post("/cart")
                        .param("businessId", "2e5533c7-7b39-4442-a419-0336d383e20a")
                        .param("foodId", "4")
        );
        MvcResult mvcResult = controllerTest.dotest(ractions);
        logger.info("调用返回的结果：{}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void removeCart() throws Exception {
        ResultActions ractions = mockMvc.perform(
                MockMvcRequestBuilders.post("/cart")
                        .param("businessId", "2e5533c7-7b39-4442-a419-0336d383e20a")
                        .param("foodId", "4")
        );
        MvcResult mvcResult = controllerTest.dotest(ractions);
        logger.info("调用返回的结果：{}", mvcResult.getResponse().getContentAsString());
    }
}