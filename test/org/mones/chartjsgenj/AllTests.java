package org.mones.chartjsgenj;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ChartTest.class,
	ColorTest.class,
	DatasetTest.class,
	LineOptionsTest.class,
	OptionsTest.class
})
public class AllTests {

}
