/*
 * ChartjsGenj - Cheap an dirty Chart.js generator for Java.
 * Copyright (c) 2013-2015, Ricardo Mones <ricardo@mones.org>
 * All rights reserved.
 * Read the accompanying LICENCE file for usage details.
 */
package org.mones.chartjsgenj;

import java.lang.reflect.Field;

/**
 * Options for radar charts.
 *
 * @author mones
 * @see <a href="http://www.chartjs.org/docs/#radar-chart-chart-options">Chart.js radar chart options</a>
 */
public final class OptionsRadar extends Options {
	// Boolean - Whether to show lines for each scale point
	public boolean scaleShowLine = true;
	// Boolean - Whether we show the angle lines out of the radar
	public boolean angleShowLineOut = true;
	// Boolean - Whether to show labels on the scale
	public boolean scaleShowLabels = false;
	// Boolean - Whether the scale should begin at zero
	public boolean scaleBeginAtZero = true;
	// String - Colour of the angle line
	public Colour angleLineColor = new Colour(0, 0.1f);
	// Number - Pixel width of the angle line
	public int angleLineWidth = 1;
	// String - Point label font declaration
	public String pointLabelFontFamily = "'Arial'";
	// String - Point label font weight
	public String pointLabelFontStyle = "normal";
	// Number - Point label font size in pixels
	public int pointLabelFontSize = 10;
	// String - Point label font colour
	public Colour pointLabelFontColor = new Colour("#666");
	// Boolean - Whether to show a dot for each point
	public boolean pointDot = true;
	// Number - Radius of each point dot in pixels
	public int pointDotRadius = 3;
	// Number - Pixel width of point dot stroke
	public int pointDotStrokeWidth = 1;
	// Number - amount extra to add to the radius to cater for hit detection
	// outside the drawn point
	public int pointHitDetectionRadius = 20;
	// Boolean - Whether to show a stroke for datasets
	public boolean datasetStroke = true;
	// Number - Pixel width of dataset stroke
	public int datasetStrokeWidth = 2;
	// Boolean - Whether to fill the dataset with a colour
	public boolean datasetFill = true;
	// String - A legend template
	public String legendTemplate = "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].strokeColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>";

    public static OptionsRadar DEFAULT = new OptionsRadar();

	@Override
	public void addJSON(StringBuilder sb) {
		boolean initialized = false;
		sb.append(JSON.OBJ_BEGIN);
		initialized = super.addFieldListJSON(sb, initialized);
		initialized = addFieldListJSON(sb, initialized);
		if (initialized) {
			sb.setLength(sb.length() - JSON.SEP.length());
		}
		sb.append(JSON.OBJ_END);
	}

	protected boolean addFieldListJSON(StringBuilder sb, boolean initialized) {
		for (Field f: OptionsRadar.class.getDeclaredFields()) {
			String name = f.getName();
			try {
				final Object object = f.get(this);
				if (!f.get(OptionsRadar.DEFAULT).equals(object)) {
					if (!initialized) {
						initialized = true;
					}
					sb.append(name).append(JSON.FV_SEP);
					if (object instanceof JSONable) {
						((JSONable) object).addJSON(sb);
					} else {
						sb.append(object.toString());
					}
					sb.append(JSON.SEP);
				}
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}
		}
		return initialized;
	}
}
