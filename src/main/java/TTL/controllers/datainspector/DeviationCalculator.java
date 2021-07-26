package TTL.controllers.datainspector;

import java.util.List;

public class DeviationCalculator {
    List<Double> epsilons;
    double size;

    /**
     * Constructor.Initialize epsilons value
     * @param epsilons - List of Double
     */
    public DeviationCalculator(List<Double> epsilons)
    {
        this.epsilons = epsilons;
        size = epsilons.size();
    }

    /**
     * returns average value of epsilons
     * @return double average value
     */
    public double getAverage()
    {
        if (size == 0) return Double.NaN;
        double sum = epsilons.stream().reduce(0.0,Double::sum);
        return sum/size;
    }

    /**
     * Returns standart deviation
     * @return double standart deviation value
     */
    public double getStandartDeviation()
    {
        if (size == 0) return Double.NaN;
        double average = getAverage();
        double sum = epsilons
                .stream()
                .map(epsilon -> Math.pow(epsilon - average,2))
                .reduce(0.0,Double::sum);
        return Math.sqrt(sum/size);
    }
}
