package org.mones.chartjsgenj;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * A RGB colour.
 *
 * @author mones
 */
public class Colour implements JSONable, Cloneable {
	private static final float MAX_ALPHA = 1.0f;
	private static final float MIN_ALPHA = 0.0f;
	private int r;
	private int g;
	private int b;
	private float a;

	private static Map<String, int[]> named;

	public Colour() {
		this.r = this.g = this.b = 0;
		this.a = MAX_ALPHA;
	}

	public Colour(String color) {
		this.r = this.g = this.b = 0;
		this.a = MAX_ALPHA;
		if (color == null) {
			return;
		}
		if (color.startsWith("#")) {
			if (color.length() == 7) { /* #aabbcc */
				this.r = Integer.parseInt(color.substring(1, 3), 16);
				this.g = Integer.parseInt(color.substring(3, 5), 16);
				this.b = Integer.parseInt(color.substring(5, 7), 16);
			} else if (color.length() == 4) { /* #abc */
				this.r = Integer.parseInt(color.substring(1, 2), 16);
				this.g = Integer.parseInt(color.substring(2, 3), 16);
				this.b = Integer.parseInt(color.substring(3, 4), 16);
				/* http://www.w3.org/TR/CSS2/syndata.html#value-def-color */
				this.r |= (this.r << 4);
				this.g |= (this.g << 4);
				this.b |= (this.b << 4);
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof Colour) {
				return ((Colour) obj).a == this.a
						&& ((Colour) obj).r == this.r
						&& ((Colour) obj).g == this.g
						&& ((Colour) obj).b == this.b;
			} else if (obj instanceof String) {
				return this.equals(new Colour((String) obj));
			}
		}
		return false;
	}

	public Colour(float a) {
		this.r = this.g = this.b = 0;
		this.setA(a);
	}

	public Colour(int r, int g, int b) {
		this.setR(r);
		this.setG(g);
		this.setB(b);
		this.a = MAX_ALPHA;
	}

	public Colour(int v) {
		v = (v > 255)? 255: ((v < 0)? 0: v);
		this.r = v;
		this.g = v;
		this.b = v;
		this.a = MAX_ALPHA;
	}

	public Colour(int v, float a) {
		v = (v > 255)? 255: ((v < 0)? 0: v);
		this.r = v;
		this.g = v;
		this.b = v;
		this.setA(a);
	}

	public Colour(int r, int g, int b, float a) {
		this.setR(r);
		this.setG(g);
		this.setB(b);
		this.setA(a);
	}

	private static Random random = new Random(System.currentTimeMillis());

	public static Colour random(boolean opaque) {
		return new Colour(
				random.nextInt(256),
				random.nextInt(256),
				random.nextInt(256),
				opaque? MAX_ALPHA: random.nextFloat());
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = (r > 255)? 255: ((r < 0)? 0: r);
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = (g > 255)? 255: ((g < 0)? 0: g);
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = (b > 255)? 255: ((b < 0)? 0: b);
	}

	public float getA() {
		return a;
	}

	public void setA(float a) {
		this.a = (a > MAX_ALPHA)? MAX_ALPHA: ((a < MIN_ALPHA)? MIN_ALPHA: a);
	}

	public String toHTML() throws IllegalStateException {
		if (this.a != MAX_ALPHA) {
			throw new IllegalStateException();
		}
		return String.format("#%02X%02X%02X", this.r, this.g, this.b);
	}

	@Override
	public void addJSON(StringBuilder sb) {
		sb.append(JSON.QUOTE).append("rgba(")
			.append(r).append(JSON.SEP)
			.append(g).append(JSON.SEP)
			.append(b).append(JSON.SEP)
			.append(a).	append(')').append(JSON.QUOTE);
	}

	@Override
	public Colour clone() {
		return new Colour(this.r, this.g, this.b, this.a);
	}
}
