package com.neusoft.elmboot.controller.controller;

import com.neusoft.elmboot.controller.VirtualWalletController;
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
class VirtualWalletControllerTest {
    protected Logger logger = LoggerFactory.getLogger(VirtualWalletControllerTest.class);
    @Resource
    VirtualWalletController doTestController;
    private MockMvc mockMvc;
    @Resource
    private ControllerTest controllerTest;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(doTestController).build();
    }

    @Test
    public void transferMoney() throws Exception {
        ResultActions ractions = mockMvc.perform(
                MockMvcRequestBuilders.post("/virtual-wallet/transfer")
                        .param("targetUsername", "111")
                        .param("targetWalletId", "14")
                        .param("money", "100.0")
        );
        MvcResult mvcResult = controllerTest.dotest(ractions);
        logger.info("调用返回的结果：{}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void recharge() throws Exception {
        ResultActions ractions = mockMvc.perform(
                MockMvcRequestBuilders.post("/virtual-wallet")
                        .param("money", "100.0")
        );
        MvcResult mvcResult = controllerTest.dotest(ractions);
        logger.info("调用返回的结果：{}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void userCreateVirtualWallet() throws Exception {
        ResultActions ractions = mockMvc.perform(
                MockMvcRequestBuilders.post("/virtual-wallet/create")
        );
        MvcResult mvcResult = controllerTest.dotest(ractions);
        logger.info("调用返回的结果：{}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void queryBalance() throws Exception {
        ResultActions ractions = mockMvc.perform(
                MockMvcRequestBuilders.get("/virtual-wallet")
        );
        MvcResult mvcResult = controllerTest.dotest(ractions);
        logger.info("调用返回的结果：{}", mvcResult.getResponse().getContentAsString());
    }
}