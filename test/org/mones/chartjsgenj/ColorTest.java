package org.mones.chartjsgenj;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ColorTest {

	@Test
	public void testColor() {
		Colour c = new Colour();
		Assert.assertNotNull(c);
		Assert.assertEquals(0, c.getR());
		Assert.assertEquals(0, c.getG());
		Assert.assertEquals(0, c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
	}

	@Test
	public void testColorString() {
		Colour c = new Colour(null);
		Assert.assertNotNull(c);
		Assert.assertEquals(0, c.getR());
		Assert.assertEquals(0, c.getG());
		Assert.assertEquals(0, c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
		c = new Colour("");
		Assert.assertNotNull(c);
		Assert.assertEquals(0, c.getR());
		Assert.assertEquals(0, c.getG());
		Assert.assertEquals(0, c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
		c = new Colour("#abc");
		Assert.assertNotNull(c);
		Assert.assertEquals(Integer.parseInt("aa", 16), c.getR());
		Assert.assertEquals(Integer.parseInt("bb", 16), c.getG());
		Assert.assertEquals(Integer.parseInt("cc", 16), c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
		c = new Colour("#1a2b3c");
		Assert.assertNotNull(c);
		Assert.assertEquals(Integer.parseInt("1a", 16), c.getR());
		Assert.assertEquals(Integer.parseInt("2b", 16), c.getG());
		Assert.assertEquals(Integer.parseInt("3c", 16), c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
		c = new Colour("orange");
		Assert.assertNotNull(c);
		Assert.assertEquals(255, c.getR());
		Assert.assertEquals(128, c.getG());
		Assert.assertEquals(0, c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
	}

	@Test
	public void testEqualsObject() {
		Colour c1 = new Colour(32, 64, 128, 0.5f);
		Assert.assertNotNull(c1);
		Colour c2 = new Colour(32, 64, 128, 0.5f);
		Assert.assertNotNull(c2);
		Assert.assertNotSame(c1, c2);
		Assert.assertTrue(c1.equals(c2));
		Assert.assertTrue(c2.equals(c1));
	}

	@Test
	public void testColorFloat() {
		Colour c = new Colour(1.0f);
		Assert.assertNotNull(c);
		Assert.assertEquals(0, c.getR());
		Assert.assertEquals(0, c.getG());
		Assert.assertEquals(0, c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
		c = new Colour(-0.1f);
		Assert.assertNotNull(c);
		Assert.assertEquals(0, c.getR());
		Assert.assertEquals(0, c.getG());
		Assert.assertEquals(0, c.getB());
		Assert.assertEquals(0.0f, c.getA(), 0f);
		c = new Colour(1.1f);
		Assert.assertNotNull(c);
		Assert.assertEquals(0, c.getR());
		Assert.assertEquals(0, c.getG());
		Assert.assertEquals(0, c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
		c = new Colour(0.5f);
		Assert.assertNotNull(c);
		Assert.assertEquals(0, c.getR());
		Assert.assertEquals(0, c.getG());
		Assert.assertEquals(0, c.getB());
		Assert.assertEquals(0.5f, c.getA(), 0f);
	}

	@Test
	public void testColorIntIntInt() {
		Colour c = new Colour(1,2,3);
		Assert.assertNotNull(c);
		Assert.assertEquals(1, c.getR());
		Assert.assertEquals(2, c.getG());
		Assert.assertEquals(3, c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
		c = new Colour(-1,-2,-3);
		Assert.assertNotNull(c);
		Assert.assertEquals(0, c.getR());
		Assert.assertEquals(0, c.getG());
		Assert.assertEquals(0, c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
		c = new Colour(301,302,303);
		Assert.assertNotNull(c);
		Assert.assertEquals(255, c.getR());
		Assert.assertEquals(255, c.getG());
		Assert.assertEquals(255, c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
	}

	@Test
	public void testColorInt() {
		Colour c = new Colour(11);
		Assert.assertNotNull(c);
		Assert.assertEquals(11, c.getR());
		Assert.assertEquals(11, c.getG());
		Assert.assertEquals(11, c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
		c = new Colour(-1);
		Assert.assertNotNull(c);
		Assert.assertEquals(0, c.getR());
		Assert.assertEquals(0, c.getG());
		Assert.assertEquals(0, c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
		c = new Colour(301);
		Assert.assertNotNull(c);
		Assert.assertEquals(255, c.getR());
		Assert.assertEquals(255, c.getG());
		Assert.assertEquals(255, c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
	}

	@Test
	public void testColorIntFloat() {
		Colour c = new Colour(11, 0.5f);
		Assert.assertNotNull(c);
		Assert.assertEquals(11, c.getR());
		Assert.assertEquals(11, c.getG());
		Assert.assertEquals(11, c.getB());
		Assert.assertEquals(0.5f, c.getA(), 0f);
		c = new Colour(11, -0.5f);
		Assert.assertNotNull(c);
		Assert.assertEquals(11, c.getR());
		Assert.assertEquals(11, c.getG());
		Assert.assertEquals(11, c.getB());
		Assert.assertEquals(0f, c.getA(), 0f);
		c = new Colour(11, 1.5f);
		Assert.assertNotNull(c);
		Assert.assertEquals(11, c.getR());
		Assert.assertEquals(11, c.getG());
		Assert.assertEquals(11, c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
		c = new Colour(-1, 0.5f);
		Assert.assertNotNull(c);
		Assert.assertEquals(0, c.getR());
		Assert.assertEquals(0, c.getG());
		Assert.assertEquals(0, c.getB());
		Assert.assertEquals(0.5f, c.getA(), 0f);
		c = new Colour(-1, -0.5f);
		Assert.assertNotNull(c);
		Assert.assertEquals(0, c.getR());
		Assert.assertEquals(0, c.getG());
		Assert.assertEquals(0, c.getB());
		Assert.assertEquals(0.0f, c.getA(), 0f);
		c = new Colour(-1, 1.5f);
		Assert.assertNotNull(c);
		Assert.assertEquals(0, c.getR());
		Assert.assertEquals(0, c.getG());
		Assert.assertEquals(0, c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
		c = new Colour(301, 0.5f);
		Assert.assertNotNull(c);
		Assert.assertEquals(255, c.getR());
		Assert.assertEquals(255, c.getG());
		Assert.assertEquals(255, c.getB());
		Assert.assertEquals(0.5f, c.getA(), 0f);
		c = new Colour(301, -0.5f);
		Assert.assertNotNull(c);
		Assert.assertEquals(255, c.getR());
		Assert.assertEquals(255, c.getG());
		Assert.assertEquals(255, c.getB());
		Assert.assertEquals(0.0f, c.getA(), 0f);
		c = new Colour(301, 1.5f);
		Assert.assertNotNull(c);
		Assert.assertEquals(255, c.getR());
		Assert.assertEquals(255, c.getG());
		Assert.assertEquals(255, c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
	}

	@Test
	public void testColorIntIntIntFloat() {
		Colour c = new Colour(11, 22, 33, 0.5f);
		Assert.assertNotNull(c);
		Assert.assertEquals(11, c.getR());
		Assert.assertEquals(22, c.getG());
		Assert.assertEquals(33, c.getB());
		Assert.assertEquals(0.5f, c.getA(), 0f);
		c = new Colour(11, 22, 33, -0.5f);
		Assert.assertNotNull(c);
		Assert.assertEquals(11, c.getR());
		Assert.assertEquals(22, c.getG());
		Assert.assertEquals(33, c.getB());
		Assert.assertEquals(0f, c.getA(), 0f);
		c = new Colour(11, 22, 33, 1.5f);
		Assert.assertNotNull(c);
		Assert.assertEquals(11, c.getR());
		Assert.assertEquals(22, c.getG());
		Assert.assertEquals(33, c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
		c = new Colour(-1, -2, -3, 0.5f);
		Assert.assertNotNull(c);
		Assert.assertEquals(0, c.getR());
		Assert.assertEquals(0, c.getG());
		Assert.assertEquals(0, c.getB());
		Assert.assertEquals(0.5f, c.getA(), 0f);
		c = new Colour(-1, -2, -3, -0.5f);
		Assert.assertNotNull(c);
		Assert.assertEquals(0, c.getR());
		Assert.assertEquals(0, c.getG());
		Assert.assertEquals(0, c.getB());
		Assert.assertEquals(0.0f, c.getA(), 0f);
		c = new Colour(-1, -2, -3, 1.5f);
		Assert.assertNotNull(c);
		Assert.assertEquals(0, c.getR());
		Assert.assertEquals(0, c.getG());
		Assert.assertEquals(0, c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
		c = new Colour(301, 302, 303, 0.5f);
		Assert.assertNotNull(c);
		Assert.assertEquals(255, c.getR());
		Assert.assertEquals(255, c.getG());
		Assert.assertEquals(255, c.getB());
		Assert.assertEquals(0.5f, c.getA(), 0f);
		c = new Colour(301, 302, 303, -0.5f);
		Assert.assertNotNull(c);
		Assert.assertEquals(255, c.getR());
		Assert.assertEquals(255, c.getG());
		Assert.assertEquals(255, c.getB());
		Assert.assertEquals(0.0f, c.getA(), 0f);
		c = new Colour(301, 302, 303, 1.5f);
		Assert.assertNotNull(c);
		Assert.assertEquals(255, c.getR());
		Assert.assertEquals(255, c.getG());
		Assert.assertEquals(255, c.getB());
		Assert.assertEquals(1.0f, c.getA(), 0f);
	}

	@Test
	public void testToHTML() {
		Colour c = new Colour();
		Assert.assertNotNull(c);
		Assert.assertEquals("#000000", c.toHTML());
		c = new Colour(32);
		Assert.assertNotNull(c);
		Assert.assertEquals("#202020", c.toHTML());
		c = new Colour(32,64,128);
		Assert.assertNotNull(c);
		Assert.assertEquals("#204080", c.toHTML());
		c = new Colour(32, 1.0f);
		Assert.assertNotNull(c);
		Assert.assertEquals("#202020", c.toHTML());
		c = new Colour("#123456");
		Assert.assertEquals("#123456", c.toHTML());		
		try {
			c = new Colour(32, 0.5f);
			Assert.assertNotNull(c);
			Assert.assertEquals("#202020", c.toHTML());
		} catch (IllegalStateException e) {
			// ok!
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}		
	}

	@Test
	public void testAddJSON() {
		Colour c = new Colour();
		Assert.assertNotNull(c);
		StringBuilder sb = new StringBuilder();
		Assert.assertNotNull(sb);
		c.addJSON(sb);
		Assert.assertEquals("\"rgba(0, 0, 0, 1.0)\"", sb.toString());
		c = new Colour("#204080");
		Assert.assertNotNull(c);
		sb = new StringBuilder();
		Assert.assertNotNull(sb);
		c.addJSON(sb);
		Assert.assertEquals("\"rgba(32, 64, 128, 1.0)\"", sb.toString());
		c = new Colour("#804020");
		Assert.assertNotNull(c);
		sb = new StringBuilder();
		Assert.assertNotNull(sb);
		c.addJSON(sb);
		Assert.assertEquals("\"rgba(128, 64, 32, 1.0)\"", sb.toString());
		c = new Colour(32, 64, 128, 0.5f);
		Assert.assertNotNull(c);
		sb = new StringBuilder();
		Assert.assertNotNull(sb);
		c.addJSON(sb);
		Assert.assertEquals("\"rgba(32, 64, 128, 0.5)\"", sb.toString());
	}

	@Test
	public void testClone() {
		Colour c1 = new Colour(32, 64, 128, 0.5f);
		Assert.assertNotNull(c1);
		Colour c2 = c1.clone();
		Assert.assertNotNull(c2);
		Assert.assertSame(c1, c1);
		Assert.assertSame(c2, c2);
		Assert.assertNotSame(c1, c2);
		Assert.assertEquals(c1.getR(), c2.getR());
		Assert.assertEquals(c1.getG(), c2.getG());
		Assert.assertEquals(c1.getB(), c2.getB());
		Assert.assertEquals(c1.getA(), c2.getA(), 0.0f);
	}
}
