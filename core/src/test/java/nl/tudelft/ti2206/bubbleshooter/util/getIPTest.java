package nl.tudelft.ti2206.bubbleshooter.util;

import static org.junit.Assert.assertEquals;

import org.mockito.*;

import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

import org.junit.Test;

import nl.tudelft.ti2206.bubbleshooter.core.Bubble;

import com.badlogic.gdx.graphics.Color;

public class getIPTest {

	@Test
	public void getExternIP() throws Exception {
		URL externIP = new URL("http://icanhazip.com");
		BufferedReader read = new BufferedReader(new InputStreamReader(
				externIP.openStream()));
		String externIp = read.readLine();

		assertEquals(externIp, getIP.getExternIP());
	}
	
	@Test
	public void getLocalIP() throws Exception {
		String Local = Inet4Address.getLocalHost().getHostAddress().toString();
		
		assertEquals(Local, getIP.getLocalIP());
	}
	
}
