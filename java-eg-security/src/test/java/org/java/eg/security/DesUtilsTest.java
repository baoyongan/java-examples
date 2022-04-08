package org.java.eg.security;

import org.java.eg.security.des.DesUtils;
import org.junit.Test;

public class DesUtilsTest{

	@Test
	public void encrypt() {
		// 加密
		System.out.println(DesUtils.encrypt("18795858635"));
	}

	@Test
	public void decrypt() {
		System.out.println(DesUtils.decrypt("cetTbY8PLTs="));
	}
}
