package org.mones.chartjsgenj;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mones.chartjsgenj.Colour;
import org.mones.chartjsgenj.JSON;
import org.mones.chartjsgenj.OptionsLine;

public class LineOptionsTest {

	@Test
	public void testAddJSON() {
		OptionsLine o = new OptionsLine();
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
		StringBuilder sb3 = new StringBuilder();
		o.bezierCurveTension = 0.1f;
		o.addJSON(sb3);
		assertEquals(JSON.OBJ_BEGIN.length() + "scaleFontColor".length()
				+ JSON.FV_SEP.length() + "\"rgba(0, 0, 0, 1.0)\"".length()
				+ JSON.SEP.length() + "bezierCurveTension".length()
				+ JSON.FV_SEP.length() + "0.1".length()
				+ JSON.OBJ_END.length(), sb3.length());
//		System.out.println(sb3.toString());
	}

}
