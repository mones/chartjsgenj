/*
 * ChartjsGenj - Cheap an dirty Chart.js generator for Java.
 * Copyright (c) 2013-2015, Ricardo Mones <ricardo@mones.org>
 * All rights reserved.
 * Read the accompanying LICENCE file for usage details.
 */
package org.mones.chartjsgenj;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mones.chartjsgenj.Colour;
import org.mones.chartjsgenj.JSON;
import org.mones.chartjsgenj.Options;

public class OptionsTest {

	@Test
	public void testAddJSON() {
		Options o = new Options();
		assertNotNull(o);
		StringBuilder sb = new StringBuilder();
		o.addJSON(sb);
		assertEquals(JSON.OBJ_BEGIN.length() + JSON.OBJ_END.length(),
			sb.length());
		StringBuilder sb2 = new StringBuilder();
		o.scaleFontColor = new Colour();
		o.addJSON(sb2);
		assertEquals(JSON.OBJ_BEGIN.length() + "scaleFontColor".length()
			+ JSON.FV_SEP.length() + "\"rgba(0, 0, 0, 1.0)\"".length()
			+ JSON.OBJ_END.length(), sb2.length());
	}

	@Test
	public void testJsOptionsVar() {
		Options o = new Options();
		assertNotNull(o);
		String options = o.jsOptionsVar("options");
		assertEquals("var options = {  };", options);
		o.scaleFontSize = 10;
		String jsoptions = o.jsOptionsVar("options");
		assertEquals("var options = { scaleFontSize : 10 };", jsoptions);
		o.scaleFontColor = new Colour();
		String jsoptions2 = o.jsOptionsVar("options2");
		assertEquals("var options2 = { scaleFontSize : 10,"
				+ " scaleFontColor : \"rgba(0, 0, 0, 1.0)\" };", jsoptions2);
		o.scaleLabel =
				new JavaScript("function(i) { return \" \" + i.value; }");
		String jsoptions3 = o.jsOptionsVar("options3");
		assertEquals("var options3 = { scaleLabel : function(i) {"
				+ " return \" \" + i.value; }, scaleFontSize : 10,"
				+ " scaleFontColor : \"rgba(0, 0, 0, 1.0)\" };", jsoptions3);
	}

}
