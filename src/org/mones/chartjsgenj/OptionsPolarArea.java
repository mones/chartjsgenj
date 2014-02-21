package org.mones.chartjsgenj;

import java.lang.reflect.Field;

/**
 * Options for polar area charts.
 *
 * @author mones
 * @see <a href="http://www.chartjs.org/docs/#polar-area-chart-chart-options">Chart.js polar area chart options</a>
 */
public final class OptionsPolarArea extends Options {
	// Boolean - Show a backdrop to the scale label
	public boolean scaleShowLabelBackdrop = true;
	// String - The colour of the label backdrop
	public Colour scaleBackdropColor = new Colour(255, 0.75f);
	// Boolean - Whether the scale should begin at zero
	public boolean scaleBeginAtZero = true;
	// Number - The backdrop padding above & below the label in pixels
	public int scaleBackdropPaddingY = 2;
	// Number - The backdrop padding to the side of the label in pixels
	public int scaleBackdropPaddingX = 2;
	// Boolean - Show line for each value in the scale
	public boolean scaleShowLine = true;
	// Boolean - Stroke a line around each segment in the chart
	public boolean segmentShowStroke = true;
	// String - The colour of the stroke on each segement.
	public Colour segmentStrokeColor = new Colour("#fff");
	// Number - The width of the stroke value in pixels
	public int segmentStrokeWidth = 2;
	// Number - Amount of animation steps
	public int animationSteps = 100;
	// String - Animation easing effect.
	public String animationEasing = "easeOutBounce";
	// Boolean - Whether to animate the rotation of the chart
	public boolean animateRotate = true;
	// Boolean - Whether to animate scaling the chart from the centre
	public boolean animateScale = false;
	// String - A legend template
	public String legendTemplate = "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>";

    public static final OptionsPolarArea DEFAULT = new OptionsPolarArea();

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
		for (Field f: OptionsPolarArea.class.getDeclaredFields()) {
			String name = f.getName();
			try {
				final Object object = f.get(this);
				if (!f.get(OptionsPolarArea.DEFAULT).equals(object)) {
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
