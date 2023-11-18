package com.neusoft.elmboot.controller.controller;

import com.neusoft.elmboot.controller.BusinessController;
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
public class BusinessControllerTest {
    protected Logger logger = LoggerFactory.getLogger(BusinessControllerTest.class);
    @Resource
    BusinessController doTestController;

    private MockMvc mockMvc;

    @Resource
    private ControllerTest controllerTest;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(doTestController).build();
    }

    @Test
    public void listBusinessByOrderTypeId() throws Exception {
        ResultActions ractions = mockMvc.perform(
                MockMvcRequestBuilders.get("/business/order-type-id")
                        .param("orderTypeId", "1")
        );
        MvcResult mvcResult = controllerTest.dotest(ractions);
        logger.info("调用返回的结果：{}", mvcResult.getResponse().getContentAsString());
    }


    @Test
    public void listBusinessByName() throws Exception {
        ResultActions ractions = mockMvc.perform(
                MockMvcRequestBuilders.get("/business/name")
                        .param("businessName", "饭")
        );
        MvcResult mvcResult = controllerTest.dotest(ractions);
        logger.info("调用返回的结果：{}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void listBusinessByAddress() throws Exception {
        ResultActions ractions = mockMvc.perform(
                MockMvcRequestBuilders.get("/business/address")
                        .param("businessAddress", "沈阳")
        );
        MvcResult mvcResult = controllerTest.dotest(ractions);
        logger.info("调用返回的结果：{}", mvcResult.getResponse().getContentAsString());
    }
}