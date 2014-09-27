package nl.tudelft.ti2206.bubbleshooter.util;


 		 
import static org.junit.Assert.assertEquals;

import org.mockito.*;		

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
	 public void getExternIPTest() throws Exception {
	 		URL externIP = new URL("http://icanhazip.com");
	 		BufferedReader read = new BufferedReader(new InputStreamReader(
	 				externIP.openStream()));
	 }
	 
	 @Test
	 public void getLocalIPTest() throws Exception {
	 		String Local = Inet4Address.getLocalHost().getHostAddress().toString();
	 		
	 		assertEquals(Local, getIP.getLocalIP());
	 	}
	 
	 /** 
	  * We zijn uitgegaan van de conventionele localhost, 
	  * en houden dus geen rekening met zelf gewijzigde systemen.
	  * @throws Exception
	  */
	 @Test
	 public void getLoopbackIPTest() throws Exception {
	 		String loopback = "localhost/127.0.0.1";
	 		assertEquals(loopback,getIP.getLoopbackIP());
	 	} 
	 
	 
	 
	//TEST linux, blijkbaar returned ie op windows ipv 4, maar op linux wel ipv6 als je kan testen en
	//	anders deleten
	//@Test
	//	public void getLocalIPnot6Test() throws Exception{
	//		String Local = Inet6Address.getLocalHost().
	//		System.out.println(Local);
	//		assertEquals(Local, getIP.getLocalIP());
	//	}
	 
 }