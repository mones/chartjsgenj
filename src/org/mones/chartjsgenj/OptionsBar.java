package org.mones.chartjsgenj;

import java.lang.reflect.Field;

/**
 * Options for bar charts.
 *
 * @author mones
 * @see  <a Href="http://www.chartjs.org/docs/#bar-chart-chart-options">Chart.js bar chart options</a>
 */
public final class OptionsBar extends Options {
	// Boolean - Whether the scale should start at zero; or an order of
	// magnitude down from the lowest value
	public boolean scaleBeginAtZero = true;
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
	// Boolean - If there is a stroke on each bar
	public boolean barShowStroke = true;
	// Number - Pixel width of the bar stroke
	public int barStrokeWidth = 2;
	// Number - Spacing between each of the X value sets
	public int barValueSpacing = 5;
	// Number - Spacing between data sets within X values
	public int barDatasetSpacing = 1;
	// String - A legend template
	public String legendTemplate = "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<datasets.length; i++){%><li><span style=\"background-color:<%=datasets[i].fillColor%>\"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>";

    public static OptionsBar DEFAULT = new OptionsBar();

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
		for (Field f: OptionsBar.class.getDeclaredFields()) {
			String name = f.getName();
			try {
				final Object object = f.get(this);
				if (!f.get(OptionsBar.DEFAULT).equals(object)) {
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
