package org.mones.chartjsgenj;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.mones.chartjsgenj.Colour;
import org.mones.chartjsgenj.Dataset;

public class DatasetTest {

	@Test
	public void testAddJSON() {
		Dataset ds = new Dataset();
		assertNotNull(ds);

		ds.setLabel("Label");
		StringBuilder sb = new StringBuilder();
		ds.addJSON(sb);
		Assert.assertEquals("·{··label·:·\"Label\"··}·",
				sb.toString().replaceAll("\\s", "·"));
//		System.out.println(sb.toString().replaceAll("\\s", "·"));

		ds.setValue(new Integer(7));
		sb = new StringBuilder();
		ds.addJSON(sb);
		Assert.assertEquals("·{··value·:·7,·label·:·\"Label\"··}·",
				sb.toString().replaceAll("\\s", "·"));
//		System.out.println(sb.toString().replaceAll("\\s", "·"));

		ds = new Dataset();
		ds.setData(new int[] { });
		sb = new StringBuilder();
		ds.addJSON(sb);
		Assert.assertEquals("·{··data·:·[··]··}·",
				sb.toString().replaceAll("\\s", "·"));
//		System.out.println(sb.toString().replaceAll("\\s", "·"));

		ds.setData(new float[] { 1.0f });
		sb = new StringBuilder();
		ds.addJSON(sb);
		Assert.assertEquals("·{··data·:·[·1.0·]··}·",
				sb.toString().replaceAll("\\s", "·"));
//		System.out.println(sb.toString().replaceAll("\\s", "·"));

		ds.setData(new int[] { 2, 1, 3 });
		sb = new StringBuilder();
		ds.addJSON(sb);
		Assert.assertEquals("·{··data·:·[·2,·1,·3·]··}·",
				sb.toString().replaceAll("\\s", "·"));
//		System.out.println(sb.toString().replaceAll("\\s", "·"));

		ds.setFillColor(new Colour("#102030"));
		sb = new StringBuilder();
		ds.addJSON(sb);
		Assert.assertEquals("·{··fillColor·:·\"rgba(16,·32,·48,·1.0)\",·data·:·[·2,·1,·3·]··}·",
				sb.toString().replaceAll("\\s", "·"));
//		System.out.println(sb.toString().replaceAll("\\s", "·"));

		ds.setLabel("LABEL");
		sb = new StringBuilder();
		ds.addJSON(sb);
		Assert.assertEquals("·{··fillColor·:·\"rgba(16,·32,·48,·1.0)\",·label·:·\"LABEL\",·data·:·[·2,·1,·3·]··}·",
				sb.toString().replaceAll("\\s", "·"));
//		System.out.println(sb.toString().replaceAll("\\s", "·"));
	}
}
