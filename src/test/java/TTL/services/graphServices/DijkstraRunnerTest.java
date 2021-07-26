package TTL.services.graphServices;

import TTL.controllers.datainspector.DeviationCalculator;
import TTL.models.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class DijkstraRunnerTest {
    DijkstraRunner runner;
     // По отдельности
    //getShortestForAllOrders - 20 s 712ms
    //getShortestForAllOrdersByOrderTypeLinear - 22 s 270 ms
    //getShortestForAllOrdersByOrderTypeParallel - 20 s 577ms
    //using computePathesForLayer getShortestForOrdersByBranchCodeLinear - 6 s 12 ms
    //using computePathesForLayer getShortestForOrdersByBranchCodeParallel - 6 s 658 ms
    //getShortestForAllOrdersByBranchCodeLinear - 19 s 672 ms
    //getShortestForAllOrdersByBranchCodeParallel - 13 s 979 ms

    // Общий запуск
    //getShortestForAllOrders - 25 s 477ms
    //getShortestForAllOrdersByOrderTypeLinear - 16 s 204 ms
    //getShortestForAllOrdersByOrderTypeParallel - 8s 203ms
    //using computePathesForLayer getShortestForOrdersByBranchCodeLinear - 1 s 12 ms
    //using computePathesForLayer getShortestForOrdersByBranchCodeParallel - 805 ms
    //getShortestForAllOrdersByBranchCodeLinear - 14 s 175 ms
    //getShortestForAllOrdersByBranchCodeParallel - 12 s 721 ms

    //TODO:WHY?

    @Before
    public void initializeRunner()
    {
        runner = new DijkstraRunner();
    }

    @Test
    public void getShortestForAllOrders() {
        HashMap<Node, List<Node>> pathes = runner.getShortestForAllOrders();
        List<Double> epsilons =
                pathes
                        .keySet()
                        .stream()
                        .map(Node::getEpsilon)
                        .collect(Collectors.toList());
        System.out.println("Epsilons  " + epsilons);
        DeviationCalculator devCalc = new DeviationCalculator(epsilons);
        double average = devCalc.getAverage();
        double deviation = devCalc.getStandartDeviation();
        System.out.println("Average epsilon = " + average + "; Standart deviation= " + deviation);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForAllOrdersByOrderTypeLinear() {
        HashMap<Node, List<Node>> pathes = runner.getShortestForAllOrdersByOrderTypeLinear();
        List<Double> epsilons =
                pathes
                        .keySet()
                        .stream()
                        .map(Node::getEpsilon)
                        .collect(Collectors.toList());
        System.out.println("Epsilons  " + epsilons);
        DeviationCalculator devCalc = new DeviationCalculator(epsilons);
        double average = devCalc.getAverage();
        double deviation = devCalc.getStandartDeviation();
        System.out.println("Average epsilon = " + average + "; Standart deviation= " + deviation);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForAllOrdersByOrderTypeParallel() {
        HashMap<Node, List<Node>> pathes = runner.getShortestForAllOrdersByOrderTypeParallel();
        List<Double> epsilons =
                pathes
                        .keySet()
                        .stream()
                        .map(Node::getEpsilon)
                        .collect(Collectors.toList());
        System.out.println("Epsilons  " + epsilons);
        DeviationCalculator devCalc = new DeviationCalculator(epsilons);
        double average = devCalc.getAverage();
        double deviation = devCalc.getStandartDeviation();
        System.out.println("Average epsilon = " + average + "; Standart deviation= " + deviation);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForOrdersByBranchCodeLinear() {
        HashMap<Node, List<Node>> pathes = runner.getShortestForOrdersByBranchCodeLinear();
        List<Double> epsilons =
                pathes
                        .keySet()
                        .stream()
                        .map(Node::getEpsilon)
                        .collect(Collectors.toList());
        System.out.println("Epsilons  " + epsilons);
        DeviationCalculator devCalc = new DeviationCalculator(epsilons);
        double average = devCalc.getAverage();
        double deviation = devCalc.getStandartDeviation();
        System.out.println("Average epsilon = " + average + "; Standart deviation= " + deviation);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForOrdersByBranchCodeParallel() {
        HashMap<Node,List<Node>> pathes = runner.getShortestForOrdersByBranchCodeParallel();
        List<Double> epsilons =
                pathes
                        .keySet()
                        .stream()
                        .map(Node::getEpsilon)
                        .collect(Collectors.toList());
        System.out.println("Epsilons  " + epsilons);
        DeviationCalculator devCalc = new DeviationCalculator(epsilons);
        double average = devCalc.getAverage();
        double deviation = devCalc.getStandartDeviation();
        System.out.println("Average epsilon = " + average + "; Standart deviation= " + deviation);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForAllOrdersByBranchCodeLinear() {
        HashMap<Node,List<Node>> pathes = runner.getShortestForAllOrdersByBranchCodeLinear();
        List<Double> epsilons =
                pathes
                        .keySet()
                        .stream()
                        .map(Node::getEpsilon)
                        .collect(Collectors.toList());
        System.out.println("Epsilons  " + epsilons);
        DeviationCalculator devCalc = new DeviationCalculator(epsilons);
        double average = devCalc.getAverage();
        double deviation = devCalc.getStandartDeviation();
        System.out.println("Average epsilon = " + average + "; Standart deviation= " + deviation);
        Assert.assertTrue(deviation< 2000);
    }

    @Test
    public void getShortestForAllOrdersByBranchCodeParallel() {
        HashMap<Node,List<Node>> pathes = runner.getShortestForAllOrdersByBranchCodeParallel();
        List<Double> epsilons =
                pathes
                        .keySet()
                        .stream()
                        .map(Node::getEpsilon)
                        .collect(Collectors.toList());
        System.out.println("Epsilons  " + epsilons);
        DeviationCalculator devCalc = new DeviationCalculator(epsilons);
        double average = devCalc.getAverage();
        double deviation = devCalc.getStandartDeviation();
        System.out.println("Average epsilon = " + average + "; Standart deviation= " + deviation);
        Assert.assertTrue(deviation< 2000);
    }



}