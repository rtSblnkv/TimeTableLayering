package TTL.services.graphServices;

import TTL.utils.DeviationCalculator;
import TTL.models.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)

/**
 * Test all the methods in DijkstraRunner class
 * assert true if standart deviation < 2000
 */
public class DijkstraRunnerTest {
    private static DijkstraRunner runner;
     // По отдельности
    //getShortestForAllOrders - 14 s 342 ms
    //getShortestForAllOrdersByOrderTypeLinear - 17 s 746 ms
    //getShortestForAllOrdersByOrderTypeParallel - 12 s 1 ms
    //using computePathesForLayer getShortestForOrdersByBranchCodeLinear - 2 s 665 ms
    //using computePathesForLayer getShortestForOrdersByBranchCodeParallel - 2 s 374 ms
    //getShortestForAllOrdersByBranchCodeLinear - 18 s 141 ms
    //getShortestForAllOrdersByBranchCodeParallel - 13 s 979 ms

    // Общий запуск
    //getShortestForAllOrders - 11 s 188ms
    //getShortestForAllOrdersByOrderTypeLinear - 14 s 196 ms
    //getShortestForAllOrdersByOrderTypeParallel - 8s 986ms
    //using computePathesForLayer getShortestForOrdersByBranchCodeLinear - 428 ms
    //using computePathesForLayer getShortestForOrdersByBranchCodeParallel - 266 ms
    //getShortestForAllOrdersByBranchCodeLinear - 14 s 196 ms
    //getShortestForAllOrdersByBranchCodeParallel - 11 s 488 ms

    //TODO:WHY?

    @BeforeClass
    public static void initializeRunner()
    {
        runner = new DijkstraRunner();
        runner.uploadDataFromCsvFiles();
    }

    private Double computeDeviation(HashMap<Node, List<Node>> pathes){
        List<Double> epsilons =
                pathes.keySet()
                        .stream()
                        .map(Node::getEpsilon)
                        .collect(Collectors.toList());

        System.out.println("Epsilons  " + epsilons);
        double average = DeviationCalculator.getAverage(epsilons);
        double deviation = DeviationCalculator.getStandartDeviation(epsilons);
        System.out.println("Average epsilon = " + average + "; Standart deviation= " + deviation);
        return deviation;
    }

    @Test
    public void getShortestForAllOrders() {
        HashMap<Node, List<Node>> pathes = runner.getShortestForAllOrders();
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForAllOrdersByOrderTypeLinear() {
        HashMap<Node, List<Node>> pathes = runner.getShortestForAllOrdersByOrderTypeLinear();
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForAllOrdersByOrderTypeParallel() {
        HashMap<Node, List<Node>> pathes = runner.getShortestForAllOrdersByOrderTypeParallel();
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForOrdersByBranchCodeLinear() {
        HashMap<Node, List<Node>> pathes = runner.getShortestForOrdersByBranchCodeLinear();
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForOrdersByBranchCodeParallel() {
        HashMap<Node,List<Node>> pathes = runner.getShortestForOrdersByBranchCodeParallel();
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForAllOrdersByBranchCodeLinear() {
        HashMap<Node,List<Node>> pathes = runner.getShortestForAllOrdersByBranchCodeLinear();
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForAllOrdersByBranchCodeParallel() {
        HashMap<Node,List<Node>> pathes = runner.getShortestForAllOrdersByBranchCodeParallel();
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }



}