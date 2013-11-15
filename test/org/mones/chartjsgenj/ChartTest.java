package org.mones.chartjsgenj;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class ChartTest {

	private static final String TEST_WRITE_DIR = "/home/mones/tmp";

	@Test
	public void testHtmlScript() {
		String hs0 = Chart.htmlScript(null, false);
		assertEquals("<script src=\"Chart.js\"></script>\n", hs0);
		hs0 = Chart.htmlScript(null, true);
		assertEquals("<script src=\"Chart.min.js\"></script>\n", hs0);
		String hs1 = Chart.htmlScript("./", false);
		assertEquals("<script src=\"./Chart.js\"></script>\n", hs1);
		hs1 = Chart.htmlScript("./", true);
		assertEquals("<script src=\"./Chart.min.js\"></script>\n", hs1);
	}

	@Test
	public void testHtmlCanvas() {
		Chart c = new Chart(ChartType.LINE);
		String cv0 = c.htmlCanvas(null, 222, 111);
		assertEquals("<canvas id=\"null\" width=\"222\" height=\"111\"></canvas>", cv0);
		String cv1 = c.htmlCanvas("", 333, 222);
		assertEquals("<canvas id=\"\" width=\"333\" height=\"222\"></canvas>", cv1);
		String cv2 = c.htmlCanvas("area", 444, 333);
		assertEquals("<canvas id=\"area\" width=\"444\" height=\"333\"></canvas>", cv2);
	}

	@Test
	public void testJsDataVar() {
		Chart c = new Chart(ChartType.LINE);
		String js0 = c.jsDataVar("data");
		assertEquals("var data =  { \nlabels : [  ],\ndatasets : [  ]\n } ;\n", js0);

		c.setLabels(Arrays.asList(new String[] { "Enero", "Febrero", "Marzo" }));
		Dataset ds1 = new Dataset();
		ds1.setData(new int[] { 1, 2, 3});
		c.addDataset(ds1);
		Dataset ds2 = new Dataset();
		ds2.setData(new float[] { 1.0f, 2.0f, 3.0f});
		c.addDataset(ds2);
		String js1 = c.jsDataVar("data1");
		assertNotNull(js1);
		// System.out.println(js1);
	}

	@Test
	public void testJsChart() {
		Chart c = new Chart(ChartType.LINE);
		String js0 = c.jsChart(null, null, null);
		// System.out.println(js0);
		assertEquals("var nullChart = new Chart(nullContext).Line(null, null);\n", js0);
		String js1 = c.jsChart("canvas", "data", "options");
		// System.out.println(js1);
		assertEquals("var canvasChart = new Chart(canvasContext).Line(data, options);\n", js1);
	}

	@Test
	public void testHtmlScript2() {
		Chart c = new Chart(ChartType.LINE);
		String cv = c.htmlCanvas("id", 200, 200);
		assertNotNull(cv);
		assertEquals(true,  cv.length() > 10);
		String sc = c.htmlScript("id", null);
		assertNotNull(sc);
		assertEquals(true,  sc.length() > 20);
		/*
		System.out.println(cv);
		System.out.println(sc);
		*/
	}
}
