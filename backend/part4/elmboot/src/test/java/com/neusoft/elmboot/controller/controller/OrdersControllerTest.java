package com.neusoft.elmboot.controller.controller;

import com.neusoft.elmboot.controller.OrdersController;
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
class OrdersControllerTest {
    protected Logger logger = LoggerFactory.getLogger(OrdersControllerTest.class);
    @Resource
    OrdersController doTestController;
    private MockMvc mockMvc;
    @Resource
    private ControllerTest controllerTest;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(doTestController).build();
    }

    @Test
    public void createOrders() throws Exception {
        ResultActions ractions = mockMvc.perform(
                MockMvcRequestBuilders.post("/orders")
                        .param("businessId", "10001")
                        .param("daId", "5")
                        .param("orderTotal", "100.0")
        );
        MvcResult mvcResult = controllerTest.dotest(ractions);
        logger.info("调用返回的结果：{}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void listOrdersByUserId() throws Exception {
        ResultActions ractions = mockMvc.perform(
                MockMvcRequestBuilders.get("/orders/user-id")
                        .param("userId", "2e5533c7-7b39-4442-a419-0336d383e20a")
        );
        MvcResult mvcResult = controllerTest.dotest(ractions);
        logger.info("调用返回的结果：{}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void payOrders() throws Exception {
        ResultActions ractions = mockMvc.perform(
                MockMvcRequestBuilders.post("/orders/pay")
                        .param("orderId", "1")
        );
        MvcResult mvcResult = controllerTest.dotest(ractions);
        logger.info("调用返回的结果：{}", mvcResult.getResponse().getContentAsString());
    }
}