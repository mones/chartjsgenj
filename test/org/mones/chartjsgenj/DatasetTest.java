/*
 * ChartjsGenj - Cheap an dirty Chart.js generator for Java.
 * Copyright (c) 2013-2015, Ricardo Mones <ricardo@mones.org>
 * All rights reserved.
 * Read the accompanying LICENCE file for usage details.
 */
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

	@Test
	public void testFromLabelsValues() {
		List<Dataset> d0 = Dataset.fromLabelsValues(null, null);
		Assert.assertNotNull(d0);
		Assert.assertEquals(0, d0.size());
		List<Dataset> d1 = Dataset.fromLabelsValues(Arrays
				.asList(new String [] { "1" }), null);
		Assert.assertNotNull(d1);
		Assert.assertEquals(0, d1.size());
		List<Dataset> d11 = Dataset.fromLabelsValues(Arrays
				.asList(new String [] { "L1" }), Arrays
				.asList(new Number [] { 11 }));
		Assert.assertNotNull(d11);
		Assert.assertEquals(1, d11.size());
		Assert.assertNull(d11.get(0).getData());
		Assert.assertEquals("L1", d11.get(0).getLabel());
		Assert.assertEquals(11, d11.get(0).getValue());
		List<Dataset> d2 = Dataset.fromLabelsValues(Arrays
				.asList(new String [] { "L1", "L2" }), Arrays
				.asList(new Number [] { 11, 22 }));
		Assert.assertNotNull(d2);
		Assert.assertEquals(2, d2.size());
		for (int i = 0; i < 2; ++i) {
			Assert.assertNull(d2.get(i).getData());
			Assert.assertEquals("L" + Integer.toString(i + 1), d2.get(i).getLabel());
			Assert.assertEquals(11 * (i + 1), d2.get(i).getValue());
		}
	}

}
