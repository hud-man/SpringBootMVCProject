package com.mashibin.spring;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootMvcProjectApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void MD5Encryption() throws NoSuchAlgorithmException
	{
		String password="ugpass";
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] md5Arr = md5.digest(password.getBytes());
		
		System.out.println(Base64.getEncoder().encodeToString(md5Arr));
	}
	
}
