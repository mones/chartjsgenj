/*
 * ChartjsGenj - Cheap an dirty Chart.js generator for Java.
 * Copyright (c) 2013-2015, Ricardo Mones <ricardo@mones.org>
 * All rights reserved.
 * Read the accompanying LICENCE file for usage details.
 */
package org.mones.chartjsgenj;

import java.lang.reflect.Field;

/**
 * Options for line charts.
 * 
 * @author mones
 * @see <a href="http://www.chartjs.org/docs/#line-chart-chart-options">Chart.js line chart options</a>
 */
public final class OptionsLine extends Options {
	// Boolean - Whether grid lines are shown across the chart
	public boolean scaleShowGridLines = true;
	// String - Colour of the grid lines
	public Colour scaleGridLineColor = new Colour(0, 0.05f);
	// Number - Width of the grid lines
	public int scaleGridLineWidth = 1;
	// Boolean - Whether to show horizontal lines (except X axis)
	public boolean scaleShowHorizontalLines = true;
	// Boolean - Whether to show vertical lines (except Y axis)
	public boolean scaleShowVerticalLines = true;
	// Boolean - Whether the line is curved between points
	public boolean bezierCurve = true;
	// Number - Tension of the bezier curve between points
	public float bezierCurveTension = 0.4f;
	// Boolean - Whether to show a dot for each point
	public boolean pointDot = true;
	// Number - Radius of each point dot in pixels
	public int pointDotRadius = 4;
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
	
	public static OptionsLine DEFAULT = new OptionsLine();

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
		for (Field f: OptionsLine.class.getDeclaredFields()) {
			String name = f.getName();
			try {
				final Object object = f.get(this);
				if (!f.get(OptionsLine.DEFAULT).equals(object)) {
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
