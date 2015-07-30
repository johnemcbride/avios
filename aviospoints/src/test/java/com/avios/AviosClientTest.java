package com.avios;

import static org.junit.Assert.*;


import java.math.BigDecimal;
import java.util.logging.Level;

import org.junit.Before;
import org.junit.Test;

import com.avios.screenscraping.client.AviosClient;

public class AviosClientTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAviosClient() {

		//pushing a change
		//again
		String username = System.getenv("aviosUser");
		String password = System.getenv("aviosPass");
		BigDecimal balance = new AviosClient(username,password).fetchBalance();
		System.out.println("Avios = " + balance);
	
	}
	
	@Test
	public void testDummy() {

		//pushing a change
		//again
		assert(true);
	
	}

}
