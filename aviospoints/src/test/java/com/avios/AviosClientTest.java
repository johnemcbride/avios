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
		
		BigDecimal balance = new AviosClient("test","test").fetchBalance();
		System.out.println("Avios = " + balance);
	
	}

}
