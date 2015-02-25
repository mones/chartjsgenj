package org.mones.chartjsgenj;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Test;

public class ThemeTest {

	@Test
	public void testTheme() {
		try {
			new Theme(null);
		} catch (NullPointerException npe) {
			// ok!
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		try {
			Theme t0 = new Theme("");
			Assert.assertNotNull(t0);
			Assert.assertNotNull(t0.getColours());
			Assert.assertEquals(1, t0.getColours().size()); // black
			Assert.assertEquals(0, t0.getColours().get(0).getR());
			Assert.assertEquals(0, t0.getColours().get(0).getG());
			Assert.assertEquals(0, t0.getColours().get(0).getB());
			Assert.assertEquals(1.0f, t0.getColours().get(0).getA());
			Theme t1 = new Theme(Theme.HM_LIME);
			Assert.assertNotNull(t1);
			Assert.assertNotNull(t1.getColours());
			Assert.assertEquals(6, t1.getColours().size());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testAddColors() {
		Theme t1 = new Theme(Theme.HM_LIME);
		Assert.assertNotNull(t1);
		Assert.assertNotNull(t1.getColours());
		Assert.assertEquals(6, t1.getColours().size());
		Theme t2 = new Theme(Theme.SOLARIZED_BASE);
		Assert.assertNotNull(t2);
		Assert.assertNotNull(t2.getColours());
		Assert.assertEquals(8, t2.getColours().size());
		t1.addColours(t2.getColours());
		Assert.assertEquals(8, t2.getColours().size());
		Assert.assertEquals(14, t1.getColours().size());
	}

	@Test
	public void testApplyChart() {
		Theme t1 = new Theme("#112233,#223311");
		Assert.assertNotNull(t1);
		Assert.assertNotNull(t1.getColours());
		Assert.assertEquals(2, t1.getColours().size());
		Chart c = new Chart(ChartType.LINE);
		c.addDataset(new Dataset("1", 1));
		c.addDataset(new Dataset("2", 2));
		t1.apply(c);
		Assert.assertEquals("#112233", c.getDatasets().get(0).getColor().toHTML());
		Assert.assertEquals("#112233", c.getDatasets().get(0).getPointColor().toHTML());
		Assert.assertEquals("#112233", c.getDatasets().get(0).getStrokeColor().toHTML());
		Assert.assertEquals("#223311", c.getDatasets().get(1).getColor().toHTML());
		Assert.assertEquals("#223311", c.getDatasets().get(1).getPointColor().toHTML());
		Assert.assertEquals("#223311", c.getDatasets().get(1).getStrokeColor().toHTML());
	}

	@Test
	public void testApplyChartInt() {
		Theme t1 = new Theme("#112233,#223311");
		Assert.assertNotNull(t1);
		Assert.assertNotNull(t1.getColours());
		Assert.assertEquals(2, t1.getColours().size());
		Chart c = new Chart(ChartType.LINE);
		c.addDataset(new Dataset("1", 1));
		c.addDataset(new Dataset("2", 2));
		t1.apply(c, 1);
		Assert.assertEquals("#223311", c.getDatasets().get(0).getColor().toHTML());
		Assert.assertEquals("#223311", c.getDatasets().get(0).getPointColor().toHTML());
		Assert.assertEquals("#223311", c.getDatasets().get(0).getStrokeColor().toHTML());
		Assert.assertEquals("#112233", c.getDatasets().get(1).getColor().toHTML());
		Assert.assertEquals("#112233", c.getDatasets().get(1).getPointColor().toHTML());
		Assert.assertEquals("#112233", c.getDatasets().get(1).getStrokeColor().toHTML());
	}

}
