package TTL.services.graphServices;

import TTL.models.Node;
import TTL.services.DataService;
import TTL.utils.DeviationCalculator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Test all the methods in DijkstraRunner class
 * assert true if standart deviation < 2000
 */
public class PathCalculatorLauncherTest {
    private static PathCalculatorLauncher launcher;
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
    public static void initializeLauncher()
    {
        DataService dataService = new DataService();
        dataService.uploadDataFromCsvFiles();
        DijkstraRunner runner = new DijkstraRunner(dataService.getNodes(),
                dataService.getEdges(),dataService.getBranches());

        launcher = new PathCalculatorLauncher(runner,dataService.getOrders());
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
        HashMap<Node, List<Node>> pathes = launcher.getShortestForAllOrders();
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForAllOrdersByOrderTypeLinear() {
        HashMap<Node, List<Node>> pathes = launcher.getShortestForAllOrdersByOrderTypeLinear();
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForAllOrdersByOrderTypeParallel() {
        HashMap<Node, List<Node>> pathes = launcher.getShortestForAllOrdersByOrderTypeParallel();
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForOrdersByBranchCodeLinear() {
        HashMap<Node, List<Node>> pathes = launcher.getShortestForOrdersByBranchCodeLinear();
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForOrdersByBranchCodeParallel() {
        HashMap<Node,List<Node>> pathes = launcher.getShortestForOrdersByBranchCodeParallel();
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForAllOrdersByBranchCodeLinear() {
        HashMap<Node,List<Node>> pathes = launcher.getShortestForAllOrdersByBranchCodeLinear();
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForAllOrdersByBranchCodeParallel() {
        HashMap<Node,List<Node>> pathes = launcher.getShortestForAllOrdersByBranchCodeParallel();
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }
}