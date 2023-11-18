package com.neusoft.elmboot.controller.controller;

import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Component
public class ControllerTest {
    public MvcResult dotest(ResultActions result) throws Exception {
        MvcResult mvcResult = result.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        return mvcResult;
    }
}
