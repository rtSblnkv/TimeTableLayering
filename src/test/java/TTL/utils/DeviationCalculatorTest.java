package TTL.utils;

import TTL.utils.DeviationCalculator;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeviationCalculatorTest {

    @Test
    public void getAverage() {
        List<Double> numArray = Arrays.asList(1.0,2.0,3.0);
        double expected = 2.0;
        double actual = DeviationCalculator.getAverage(numArray);
        double actualEmptyList = DeviationCalculator.getAverage(new ArrayList<>());
        Assert.assertTrue(expected == actual );
        Assert.assertTrue(expected == actualEmptyList );
    }

    @Test
    public void getStandartDeviation() {
        List<Double> numArray=Arrays.asList(10.0,6.0,7.0,8.0,88.0);
        double expected = 32.12725;
        double actual = DeviationCalculator.getStandartDeviation(numArray);
        double actualEmptyList = DeviationCalculator.getStandartDeviation(new ArrayList<>());

        Assert.assertTrue(expected - actual  < 0.001);
        Assert.assertTrue(expected - actualEmptyList  < 0.001);
    }
}