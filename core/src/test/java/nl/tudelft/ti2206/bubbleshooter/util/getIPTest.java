package nl.tudelft.ti2206.bubbleshooter.util;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.URL;

import org.junit.Test;

public class getIPTest {

	@Test
	public void getExternIPTest() throws Exception {
		URL externIP = new URL("http://icanhazip.com");
		BufferedReader read = new BufferedReader(new InputStreamReader(
				externIP.openStream()));
		String externIp = read.readLine();

		assertEquals(externIp, getIP.getExternIP());
	}
	
	@Test
	public void getLocalIPTest() throws Exception {
		String Local = Inet4Address.getLocalHost().getHostAddress().toString();
		
		assertEquals(Local, getIP.getLocalIP());
	}
	
// TEST linux, blijkbaar returned ie op windows ipv 4, maar op linux wel ipv6 als je kan testen en
//	anders deleten
//	@Test
//	public void getLocalIPnot6Test() throws Exception{
//		String Local = Inet6Address.getLocalHost().
//		System.out.println(Local);
//		assertEquals(Local, getIP.getLocalIP());
//	}
	
}
