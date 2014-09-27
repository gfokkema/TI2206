package nl.tudelft.ti2206.bubbleshooter.util;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Inet4Address;
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
		String Local = Inet4Address.getLocalHost().getHostAddress();

		assertEquals(Local, getIP.getLocalIP());
	}

	/**
	 * We zijn uitgegaan van de conventionele localhost, en houden dus geen
	 * rekening met zelf gewijzigde systemen.
	 * 
	 * @throws Exception
	 */
	@Test
	public void getLoopbackIPTest() throws Exception {
		String loopback = "localhost/127.0.0.1";
		assertEquals(loopback, getIP.getLoopbackIP());
	}

	// TEST linux, blijkbaar returned ie op windows ipv 4, maar op linux wel
	// ipv6 als je kan testen en
	// anders deleten
	// @Test
	// public void getLocalIPnot6Test() throws Exception{
	// String Local = Inet6Address.getLocalHost().getHostAddress();
	// System.out.println(Local);
	// assertEquals(Local, getIP.getLocalIP());
	// }

}
