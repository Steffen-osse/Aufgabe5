package de.hfu;
import org.junit.Test;
import static org.junit.Assert.*;

public class UtilTest{	
	@Test
	public void testIstErstesHalbesJahr0(){
		try {
		Util.istErstesHalbjahr(0);
		fail("Exeption wurde nicht geworfen!");
		}
		catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testIstErstesHalbesJahr13(){
		try {
		Util.istErstesHalbjahr(13);
		fail("Exeption wurde nicht geworfen!");
		}
		catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testIstErstesHalbesJahr3() {
		boolean b = Util.istErstesHalbjahr(6);
		assertTrue(b);
	}
}