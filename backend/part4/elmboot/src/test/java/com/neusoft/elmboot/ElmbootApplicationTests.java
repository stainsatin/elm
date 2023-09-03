package com.neusoft.elmboot;

import com.neusoft.elmboot.util.CommonUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

@SpringBootTest
class ElmbootApplicationTests {


	@Test
	void contextLoads() throws NoSuchAlgorithmException {

		System.out.println(CommonUtil.encodePassword("123"));
	}

}
