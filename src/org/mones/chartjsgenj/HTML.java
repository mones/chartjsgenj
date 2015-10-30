/*
 * ChartjsGenj - Cheap an dirty Chart.js generator for Java.
 * Copyright (c) 2013-2015, Ricardo Mones <ricardo@mones.org>
 * All rights reserved.
 * Read the accompanying LICENCE file for usage details.
 */
package org.mones.chartjsgenj;

/**
 * Generic markup generation functions.
 *
 * @author mones
 */
public class HTML {

	public static String canvas(final String canvasId, int width, int height) {
		StringBuilder sb = new StringBuilder("<canvas id=\"");
		sb.append(canvasId).append("\" width=\"")
			.append(width).append("\" height=\"")
			.append(height).append("\"></canvas>");
		return sb.toString();
	}

	public static String div(final String divId) {
		StringBuilder sb = new StringBuilder("<div id=\"");
		sb.append(divId).append("\"></div>");
		return sb.toString();
	}

	public static String script(final String javascript) {
		StringBuilder sb = new StringBuilder("<script type=\"text/javascript\">\n");
		sb.append("// <![CDATA[\n")
			.append(javascript)
			.append("// ]]>\n")
			.append("</script>\n");
		return sb.toString();
	}

	public static String tableUpDown(
			String up, String down) {
		StringBuilder sb = new StringBuilder("<table><tbody><tr><td>");
		sb.append(up).append("</td></tr><tr><td>")
			.append(down).append("</td></tr></tbody></table>");
		return sb.toString();
	}

	public static String tableLeftRight(
			String left, String right) {
		StringBuilder sb = new StringBuilder("<table><tbody><tr><td>");
		sb.append(left).append("</td><td>")
			.append(right).append("</td></tr></tbody></table>");
		return sb.toString();
	}
}
