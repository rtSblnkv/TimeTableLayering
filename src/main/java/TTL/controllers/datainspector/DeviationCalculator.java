package TTL.controllers.datainspector;

import java.util.List;

public class DeviationCalculator {
    List<Double> epsilons;
    double size;

    public DeviationCalculator(List<Double> epsilons)
    {
        this.epsilons = epsilons;
        size = epsilons.size();
    }

    public double getAverage()
    {
        if (size == 0) return Double.NaN;
        double sum = epsilons.stream().reduce(0.0,Double::sum);
        return sum/size;
    }

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
