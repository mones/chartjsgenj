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

	@Test
	public void testHtmlScriptFullPage() {
		String fname = TEST_WRITE_DIR + File.separator + "test1.html";
		Writer w = null;
		try {
			// prepare
			OptionsLine ol = new OptionsLine();
			OptionsBar ob = new OptionsBar();
			OptionsRadar or = new OptionsRadar();
			OptionsPolarArea opa = new OptionsPolarArea();
			OptionsPieDoughnut opd = new OptionsPieDoughnut();
			Chart c = new Chart(ChartType.LINE);
			c.setLabels(Arrays.asList(new String[] {
				"January", "February", "March", "April", "May", "June", "July"
			}));
			Dataset d1 = new Dataset("My First dataset");
			d1.setFillColor(new Colour(220, 0.2f));
			d1.setStrokeColor(new Colour(220));
			d1.setPointColor(new Colour(220));
			d1.setPointStrokeColor(new Colour("#fff"));
			d1.setPointHighlightFill(new Colour("#fff"));
			d1.setPointHighlightStroke(new Colour(220));
			d1.setData(new int[] { 65, 59, 80, 81, 56, 55, 40 });
			c.addDataset(d1);
			Dataset d2 = new Dataset("My Second dataset");
			d2.setFillColor(new Colour(151, 187, 205, 0.2f));
			d2.setStrokeColor(new Colour(151, 187, 205));
			d2.setPointColor(new Colour(151, 187, 205));
			d2.setPointStrokeColor(new Colour("#fff"));
			d2.setPointHighlightFill(new Colour("#fff"));
			d2.setPointHighlightStroke(new Colour(151, 187, 205));
			d2.setData(new int[] { 28, 48, 40, 19, 86, 27, 90 });
			c.addDataset(d2);
			Dataset[] pads = new Dataset[] {
				new Dataset("Red", 300, "#F7464A", "#FF5A5E"),
				new Dataset("Green", 50, "#46BFBD", "#5AD3D1"),
				new Dataset("Yellow", 100, "#FDB45C", "#FFC870"),
				new Dataset("Grey", 40, "#949FB1", "#A8B3C5"),
				new Dataset("Dark Grey", 120, "#4D5360", "#616774"),
			};
			// constants
			final String canvasid = "test";
			final String canvasid2 = "test2";
			final String canvasid3 = "test3";
			final String canvasid4 = "test4";
			final String canvasid5 = "test5";
			// write
			w = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(fname), "utf-8"));
			w.write("<html>\n<head>\n<title>Test</title>\n");
			w.write(Chart.htmlScript("Chart.js/", false));
			w.write("</head>\n<body>\n");
			w.write(c.htmlCanvas(canvasid , 710, 355));
			w.write(c.htmlScript(canvasid, ol));
			w.write("<br>");
			c.setType(ChartType.BAR);
			d1.getFillColor().setA(0.5f);
			d1.setHighlightFill(new Colour(220,0.75f));
			d1.setHighlightStroke(new Colour(220));
			d2.getFillColor().setA(0.5f);
			d2.setHighlightFill(new Colour(151, 187, 205, 0.75f));
			d2.setHighlightStroke(new Colour(151, 187, 205));
			w.write(c.htmlCanvas(canvasid2 , 710, 355));
			w.write(c.htmlScript(canvasid2, ob));
			w.write("<br>");
			c.setType(ChartType.RADAR);
			c.setLabels(Arrays.asList(new String[] {
				"Eating", "Drinking", "Sleeping", "Designing", "Coding", "Cycling", "Running"
			}));
			d1.getFillColor().setA(0.2f);
			d2.getFillColor().setA(0.2f);
			w.write(c.htmlCanvas(canvasid3 , 710, 355));
			w.write(c.htmlScript(canvasid3, or));
			w.write("<br>");
			c = new Chart(ChartType.POLAR_AREA);
			c.setDatasets(Arrays.asList(pads));
			w.write(c.htmlCanvas(canvasid4 , 710, 355));
			w.write(c.htmlScript(canvasid4, opa));
			w.write("<br>");
			Chart pie = new Chart(ChartType.PIE);
			pie.addDataset(pads[0]);
			pie.addDataset(pads[1]);
			pie.addDataset(pads[2]);
			Chart dou = new Chart(ChartType.DOUGHNUT);
			dou.addDataset(pads[0]);
			dou.addDataset(pads[1]);
			dou.addDataset(pads[2]);
			w.write(pie.htmlCanvas(canvasid5 + "pie" , 352, 176));
			w.write(dou.htmlCanvas(canvasid5 + "dou" , 352, 176));
			w.write(pie.htmlScript(canvasid5 + "pie", opd));
			w.write(dou.htmlScript(canvasid5 + "dou", opd));
			w.write("</body>\n</html>");
		} catch (UnsupportedEncodingException e) {
			Assert.fail(e.getMessage());
		} catch (FileNotFoundException e) {
			Assert.fail(e.getMessage());
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		} finally {
			try {
				if (w != null) {
					w.flush();
					w.close();
				}
			} catch (IOException e) {
				Assert.fail(e.getMessage());
			}
		}
		// check file size
		Assert.assertEquals(6098, (new File(fname)).length());
	}

	@Test
	public void testHtmlScriptThemes() {
		String fname = TEST_WRITE_DIR + File.separator + "test2.html";
		Writer w = null;
		try {
			// prepare
			OptionsLine ol = new OptionsLine();
			Chart c = new Chart(ChartType.LINE);
			c.setLabels(Arrays.asList(new String[] {
				"January", "February", "March", "April", "May", "June", "July"
			}));
			Dataset[] data = new Dataset[] {
				new Dataset("My First dataset",
					new int[] { 65, 59, 80, 81, 56, 55, 40 }),
				new Dataset("My Second dataset",
					new int[] { 28, 48, 40, 19, 86, 27, 90 }),
				new Dataset("My Third dataset",
					new int[] { 18, 19, 45, 44, 43, 32, 33 }),
			};
			c.setDatasets(Arrays.asList(data));
			Colour ca = new Colour(138, 179, 232);
			Colour cb = new Colour(95, 159, 242);
			Colour cc = new Colour(49, 137, 253);
			Theme t = new Theme(ca.toHTML() + "," + cb.toHTML() + "," + cc.toHTML());
			t.apply(c);
			// constants
			final String canvasid = "test";
			final String canvasid2 = "test2";
			// write
			w = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(fname), "utf-8"));
			w.write("<html>\n<head>\n<title>Test</title>\n");
			w.write(Chart.htmlScript("Chart.js/", false));
			w.write(Chart.htmlLegendStyle(
				"list-style-type: none; background-color: #eee; padding: 10px; width: 100px;",
				"display: inline;",
				"display: block; width: 16px; min-height: 16px;"));
			w.write("</head>\n<body>\n");
			w.write(c.htmlCanvas(canvasid , 710, 355));
			w.write(c.htmlScript(canvasid, ol));
			w.write("<br>");
			List<String> labels = Arrays.asList(new String[] {
				"One", "Two", "Three", "Four", "Five", "Six", "Seven"
			});
			List<Number> values = new ArrayList<Number>();
			for (int i = 0; i < labels.size(); ++i) {
				values.add(new Float(Math.round(10 + labels.size() * 10 * Math.random())));
			}
			List<Dataset> data2 = Dataset.fromLabelsValues(labels, values);
			Chart c2 = new Chart(ChartType.DOUGHNUT, true);
			c2.setDatasets(data2);
			Theme t2 = new Theme(Theme.SOLARIZED_ACCENT);
			t2.apply(c2);
			OptionsPieDoughnut ops = new OptionsPieDoughnut();
			ops.animateScale = true;
			ops.showScale = true;
			w.write(HTML.tableLeftRight(
					c2.htmlCanvas(canvasid2 , 500, 450),
					c2.htmlLegend(canvasid2)
					));
			w.write(c2.htmlScript(canvasid2, ops));
			w.write("</body>\n</html>");
		} catch (UnsupportedEncodingException e) {
			Assert.fail(e.getMessage());
		} catch (FileNotFoundException e) {
			Assert.fail(e.getMessage());
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		} finally {
			try {
				if (w != null) {
					w.flush();
					w.close();
				}
			} catch (IOException e) {
				Assert.fail(e.getMessage());
			}
		}
		// check file size
		Assert.assertEquals(5881, (new File(fname)).length());
	}
}
