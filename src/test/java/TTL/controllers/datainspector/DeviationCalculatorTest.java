package TTL.controllers.datainspector;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DeviationCalculatorTest {

    @Test
    public void getAverage() {
        List<Double> numArray = Arrays.asList(1.0,2.0,3.0);
        double expected = 2.0;
        DeviationCalculator deviationCalculator = new DeviationCalculator(numArray);
        double actual = deviationCalculator.getAverage();
        Assert.assertTrue(expected == actual );

    }

    @Test
    public void getStandartDeviation() {
        List<Double> numArray=Arrays.asList(10.0,6.0,7.0,8.0,88.0);
        double expected = 32.12725;
        DeviationCalculator deviationCalculator = new DeviationCalculator(numArray);
        double actual = deviationCalculator.getStandartDeviation();
        Assert.assertTrue(expected - actual  < 0.001);
    }
}