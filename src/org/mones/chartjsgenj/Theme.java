/*
 * ChartjsGenj - Cheap an dirty Chart.js generator for Java.
 * Copyright (c) 2013-2015, Ricardo Mones <ricardo@mones.org>
 * All rights reserved.
 * Read the accompanying LICENCE file for usage details.
 */
package org.mones.chartjsgenj;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Some lists of colours for the charts.
 *
 * @author mones
 */
public final class Theme {
	// Some colors from https://en.wikipedia.org/wiki/X11_color_names
	public static String X_CORNFLOWER = "#6495ED";
	public static String X_INDIAN_RED = "#CD5C5C";
	public static String X_LIGHT_STEEL_BLUE = "#B0C4DE";
	// Hand made green sets by random colour picking from images
	public static String HM_GREEYNISH = "#bcc1c4,#95c04c,#797979,#444d52,#99a69d,#8a9a8f,#6a776d";
	public static String HM_LIME = "#a0b585,#7c9909,#f0f5de,#bad30a,#d6eb6c,#f7f4d2";
	// Randomly generated from https://coolors.co
	public static String COOLORS_PASTELS = "#efb0a1,#f4afb4,#c9b7ad,#94a89a,#797d81";
	public static String COOLORS_DIGRESS = "#2d3047,#93b7be,#e0ca3c,#a799b7,#048a81";
	// As found on http://ethanschoonover.com/solarized
	public static String SOLARIZED_BASE = "#002b36,#073642,#586e75,#657b83,#839496,#93a1a1,#eee8d5,#fdf6e3";
	public static String SOLARIZED_ACCENT = "#b58900,#cb4b16,#dc322f,#d33682,#6c71c4,#268bd2,#2aa198,#859900";
	public static String SOLARIZED_MIX = "#002b36,#b58900,#073642,#cb4b16,#586e75,#dc322f,#657b83,#d33682,#839496,#6c71c4,#93a1a1,#268bd2,#eee8d5,#2aa198,#fdf6e3,#859900";

	private List<Colour> colours;

	private void setColours(String colours) {
		this.colours = new ArrayList<Colour>();
		if (colours == null || "".equals(colours)) {
			this.colours.add(new Colour()); // black
			return;
		}
		String[] cs = colours.split(",");
		for (String c: cs) {
			this.colours.add(new Colour(c));
		}
	}

	private Properties load(final File file) {
		Properties p = new Properties();
		InputStream r = null;
		try {
			r = new FileInputStream(file);
			if (r != null) {
				p.load(r);
			}
		} catch (IOException e) {
			return null;
		} finally {
			if (r != null) {
				try {
					r.close();
				} catch (IOException e) {
					return null;
				}
			}
		}
		return p;
	}

	/**
	 * Create a theme from a list of colours.
	 *
	 * @param colours List of HTML colours (#abc or #aabbcc format).
	 */
	public Theme(String colours) {
		setColours(colours);
	}

	/**
	 * Create a theme from a properties filename and a key.
	 *
	 * @param filename Properties filename with the colour sets.
	 * @param set The set of colours to get from properties.
	 */
	public Theme(String filename, String set) {
		String colours = null;
		if (filename != null && set != null) {
			Properties p = this.load(new File(filename));
			if (p != null) {
				colours = p.getProperty(set);
			}
		}
		setColours(colours);
	}

	public List<Colour> getColours() {
		return colours;
	}

	public void setColours(List<Colour> colours) {
		this.colours = colours;
	}

	public void addColours(List<Colour> colours) {
		this.colours.addAll(colours);
	}

	/**
	 * Apply the theme to the chart.
	 *
	 * @param chart The chart to colourise.
	 */
	public void apply(Chart chart) {
		this.apply(chart, 0);
	}

	/**
	 * Apply the theme to the chart, starting at some colour.
	 *
	 * @param chart The chart to colourise.
	 * @param start Start at this colour index.
	 */
	public void apply(Chart chart, int start) {
		int i = (start < this.colours.size())? start: 0;
		for (Dataset d: chart.getDatasets()) {
			Colour co = this.colours.get(i);
			Colour co25 = co.clone();
			Colour co50 = co.clone();
			Colour co75 = co.clone();
			co25.setA(0.25f);
			co50.setA(0.5f);
			co75.setA(0.75f);
			// FIXME this should look at chart type probably
			d.setColor(co);
			d.setFillColor(co50);
			d.setStrokeColor(co);
			d.setHighlight(co25);
			d.setHighlightFill(co50);
			d.setPointColor(co);
			d.setPointHighlightFill(co50);
			d.setPointHighlightStroke(co);
			d.setPointStrokeColor(co75);
			++i;
			if (i >= this.colours.size()) { // rotate
				i = 0;
			}
		}
	}
}
