package TTL.services.graphServices;

import TTL.models.Edge;
import TTL.models.Node;
import TTL.models.Route;
import TTL.services.DataService;
import TTL.services.graphServices.Scorers.DistanceTargetScorer;
import TTL.services.graphServices.Scorers.EdgeDistanceScorer;
import TTL.services.graphServices.Scorers.NewNodeScorer;
import TTL.services.graphServices.Scorers.TargetScorer;
import TTL.services.graphServices.a_star.AStar;
import TTL.services.graphServices.dijkstra.Dijkstra;
import TTL.services.graphServices.dijkstra.DijkstraRunner;
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
    private static NewNodeScorer<Edge> nextNodeScorer;
    private static TargetScorer<Node> targetScorer;
    // По отдельности
    //getShortestForAllOrders - 14 s 342 ms
    //getShortestForAllOrdersByOrderTypeLinear - 17 s 746 ms
    //getShortestForAllOrdersByOrderTypeParallel - 12 s 1 ms
    //using computePathesForLayer getShortestForOrdersByBranchCodeLinearPreliminary - 2 s 665 ms
    //using computePathesForLayer getShortestForOrdersByBranchCodeParallelPreliminary - 2 s 374 ms
    //getShortestForAllOrdersByBranchCodeLinear - 18 s 141 ms
    //getShortestForAllOrdersByBranchCodeParallel - 13 s 979 ms

    // Общий запуск
    //getShortestForAllOrders - 11 s 188ms
    //getShortestForAllOrdersByOrderTypeLinear - 14 s 196 ms
    //getShortestForAllOrdersByOrderTypeParallel - 8s 986ms
    //using computePathesForLayer getShortestForOrdersByBranchCodeLinearPreliminary - 428 ms
    //using computePathesForLayer getShortestForOrdersByBranchCodeParallelPreliminary - 266 ms
    //getShortestForAllOrdersByBranchCodeLinear - 14 s 196 ms
    //getShortestForAllOrdersByBranchCodeParallel - 11 s 488 ms

    //TODO:WHY?

    @BeforeClass
    public static void initializeLauncher()
    {
        DataService dataService = new DataService();
        dataService.uploadDataFromCsvFiles();
        DijkstraRunner dijkstraRunner = new DijkstraRunner(dataService.getNodes(),
                dataService.getEdges(),dataService.getBranches());
        RouteFinderRunner runner = new RouteFinderRunner(dataService.getNodes(),
                dataService.getEdges(),dataService.getBranches());
        nextNodeScorer = new EdgeDistanceScorer<>();
        targetScorer = new DistanceTargetScorer<>();
        launcher = new PathCalculatorLauncher(
                runner,
                dijkstraRunner,
                dataService.getOrders().subList(0,1),
                nextNodeScorer,
                targetScorer
        );
    }

    private Double computeDeviation(HashMap<Node, Route<Node>> pathes){
        List<Double> epsilons =
                pathes.values()
                        .stream()
                        .map(Route::getEpsilon)
                        .collect(Collectors.toList());

        System.out.println("Epsilons  " + epsilons);
        double average = DeviationCalculator.getAverage(epsilons);
        double deviation = DeviationCalculator.getStandartDeviation(epsilons);
        System.out.println("Average epsilon = " + average + "; Standart deviation= " + deviation);
        return deviation;
    }

    @Test
    public void getShortestForAllOrders() {
        HashMap<Node, Route<Node>> pathes = launcher.getShortestForAllOrders();
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForAllOrdersByOrderTypeLinear() {
        HashMap<Node, Route<Node>> pathes = launcher.getShortestForAllOrdersByOrderTypeLinear();
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForAllOrdersByOrderTypeParallel() {
        HashMap<Node, Route<Node>> pathes = launcher.getShortestForAllOrdersByOrderTypeParallel();
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForOrdersByBranchCodeLinear() {
        HashMap<Node, Route<Node>> pathes = launcher.getShortestForOrdersByBranchCodeLinearPreliminary(nextNodeScorer);
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForOrdersByBranchCodeParallel() {
        HashMap<Node,Route<Node>> pathes = launcher.getShortestForOrdersByBranchCodeParallelPreliminary(nextNodeScorer);
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForAllOrdersByBranchCodeLinear() {
        HashMap<Node,Route<Node>> pathes = launcher.getShortestForAllOrdersByBranchCodeLinear();
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void  getShortestForAllOrdersByBranchCodeParallel() {
        HashMap<Node,Route<Node>> pathes = launcher.getShortestForAllOrdersByBranchCodeParallel();
        double deviation = computeDeviation(pathes);
        Assert.assertTrue(deviation< 2000);
    }
}