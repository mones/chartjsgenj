package org.mones.chartjsgenj;

import java.lang.reflect.Field;

/**
 * Common options for all charts.
 * 
 * @author mones
 * @see <a href="http://www.chartjs.org/docs/#getting-started-global-chart-configuration">Chart.js global chart configuration</a>
 */
public class Options implements JSONable {
	// Boolean - Whether to animate the chart
	public Boolean animation = true;
	// Number - Number of animation steps
	public Integer animationSteps = 60;
	// String - Animation easing effect
    // Possible effects are:
    // [easeInOutQuart, linear, easeOutBounce, easeInBack, easeInOutQuad,
    //  easeOutQuart, easeOutQuad, easeInOutBounce, easeOutSine, easeInOutCubic,
    //  easeInExpo, easeInOutBack, easeInCirc, easeInOutElastic, easeOutBack,
    //  easeInQuad, easeInOutExpo, easeInQuart, easeOutQuint, easeInOutCirc,
    //  easeInSine, easeOutExpo, easeOutCirc, easeOutCubic, easeInQuint,
    //  easeInElastic, easeInOutSine, easeInOutQuint, easeInBounce,
    //  easeOutElastic, easeInCubic]
	public String animationEasing = "easeOutQuart";
	// Boolean - If we should show the scale at all
	public Boolean showScale = true;
	// Boolean - If we want to override with a hard coded scale
	public Boolean scaleOverride = false;
	// ** Required if scaleOverride is true **
    // Number - The number of steps in a hard coded scale
	public Integer scaleSteps = 0;
	// Number - The value jump in the hard coded scale
	public Integer scaleStepWidth = 0;
	// Number - The scale starting value
	public Integer scaleStartValue = 0;
	// String - Colour of the scale line
	public Colour scaleLineColor = new Colour(0.1f);
	// Number - Pixel width of the scale line
	public Integer scaleLineWidth = 1;
	// Boolean - Whether to show labels on the scale
	public Boolean scaleShowLabels = true;
	// Interpolated JS string - can access value
	public String scaleLabel = "<%=value%>";
	// Boolean - Whether the scale should stick to integers, not floats even
	// if drawing space is there
	public Boolean scaleIntegersOnly = true;
	// Boolean - Whether the scale should start at zero, or an order of
	// magnitude down from the lowest value
	public Boolean scaleBeginAtZero = false;
	// String - Scale label font declaration for the scale label
	public String scaleFontFamily = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";
	// Number - Scale label font size in pixels
	public Integer scaleFontSize = 12;
	// String - Scale label font weight style
	public String scaleFontStyle = "normal";
	// String - Scale label font colour
	public Colour scaleFontColor = new Colour("#666");
	// Boolean - whether or not the chart should be responsive and resize
	// when the browser does.
	public Boolean responsive = false;
	// Boolean - whether to maintain the starting aspect ratio or not when
	// responsive, if set to false, will take up entire container
	public Boolean maintainAspectRatio = true;
	// Boolean - Determines whether to draw tooltips on the canvas or not
	public Boolean showTooltips = true;
	// Function - Determines whether to execute the customTooltips function
	// instead of drawing the built in tooltips (See [Advanced - External
	// Tooltips](#advanced-usage-custom-tooltips))
	public String customTooltips = "false";
	// Array - Array of string names to attach tooltip events
 	public String tooltipEvents = "[ \"mousemove\", \"touchstart\", \"touchmove\" ]";
	// String - Tooltip background colour
	public Colour tooltipFillColor = new Colour(0.8f);
	// String - Tooltip label font declaration for the scale label
	public String tooltipFontFamily = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";
	// Number - Tooltip label font size in pixels
	public Integer tooltipFontSize = 14;
	// String - Tooltip font weight style
	public String tooltipFontStyle = "normal";
	// String - Tooltip label font colour
	public Colour tooltipFontColor = new Colour("#fff");
	// String - Tooltip title font declaration for the scale label
	public String tooltipTitleFontFamily = "'Helvetica Neue', 'Helvetica', 'Arial', sans-serif";
	// Number - Tooltip title font size in pixels
	public Integer tooltipTitleFontSize = 14;
	// String - Tooltip title font weight style
	public String tooltipTitleFontStyle = "bold";
	// String - Tooltip title font colour
	public Colour tooltipTitleFontColor = new Colour("#fff");
	// Number - pixel width of padding around tooltip text
	public Integer tooltipYPadding = 6;
	// Number - pixel width of padding around tooltip text
	public Integer tooltipXPadding = 6;
	// Number - Size of the caret on the tooltip
	public Integer tooltipCaretSize = 8;
	// Number - Pixel radius of the tooltip border
	public Integer tooltipCornerRadius = 6;
	// Number - Pixel offset from point x to tooltip edge
	public Integer tooltipXOffset = 10;
	// String - Template string for single tooltips
	public String tooltipTemplate = "<%if (label){%><%=label%>: <%}%><%= value %>";
	// String - Template string for multiple tooltips
	public String multiTooltipTemplate = "<%= value %>";
	// Function - Will fire on animation progression.
	public String onAnimationProgress = "function(){}";
	// Function - Will fire on animation completion.
	public String onAnimationComplete = "function(){}";

	public static Options DEFAULT = new Options();

	@Override
	public void addJSON(StringBuilder sb) {		
		boolean initialized = false;
		sb.append(JSON.OBJ_BEGIN);
		initialized = addFieldListJSON(sb, initialized);
		if (initialized) {
			sb.setLength(sb.length() - JSON.SEP.length());
		}
		sb.append(JSON.OBJ_END);
	}

	protected boolean addFieldListJSON(StringBuilder sb, boolean initialized) {
		for (Field f: Options.class.getDeclaredFields()) {
			String name = f.getName();
			try {
				final Object object = f.get(this);
				if (!f.get(Options.DEFAULT).equals(object)) {
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

	public String jsOptionsVar(final String varOptions) {
		StringBuilder sb = new StringBuilder("var ");
		sb.append(varOptions).append(" = ");
		this.addJSON(sb);
		sb.append(";");
		return sb.toString();
	}
}
