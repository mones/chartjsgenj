package org.mones.chartjsgenj;

import java.lang.reflect.Field;

/**
 * Options for pie/dounught charts.
 *
 * @author mones
 * @see <a href="http://www.chartjs.org/docs/#doughnut-pie-chart-chart-options">Chart.js pie/dounught chart options</a>
 */
public class OptionsPieDoughnut extends Options {
	// Boolean - Whether we should show a stroke on each segment
	public boolean segmentShowStroke = true;
	// String - The colour of each segment stroke
	public Colour segmentStrokeColor = new Colour("#fff");
	// Number - The width of each segment stroke
	public int segmentStrokeWidth = 2;
	// Number - The percentage of the chart that we cut out of the middle
	public int percentageInnerCutout = 50; // This is 0 for Pie charts
	// Number - Amount of animation steps
	public int animationSteps = 100;
	// String - Animation easing effect
	public String animationEasing = "easeOutBounce";
	// Boolean - Whether we animate the rotation of the Doughnut
	public boolean animateRotate = true;
	// Boolean - Whether we animate scaling the Doughnut from the centre
	public boolean animateScale = false;
	// String - A legend template
	public String legendTemplate = "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>";

	public static OptionsPieDoughnut DEFAULT = new OptionsPieDoughnut();

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
		for (Field f: OptionsPieDoughnut.class.getDeclaredFields()) {
			String name = f.getName();
			try {
				final Object object = f.get(this);
				if (!f.get(OptionsPieDoughnut.DEFAULT).equals(object)) {
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
