package TTL;

public class BenchMarkRunner {
    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}

/*
Result "TTL.services.graphServices.DijkstraRunner.getShortestForAllOrdersByBranchCodeParallel":
  7,749 ±(99.9%) 1,611 ops/s [Average]
  (min, avg, max) = (4,458, 7,749, 10,116), stdev = 1,918
  CI (99.9%): [6,138, 9,360] (assumes normal distribution)


# Run complete. Total time: 00:09:08

REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
experiments, perform baseline and negative tests that provide experimental control, make sure
the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
Do not assume the numbers tell you what you want them to tell.

Benchmark                                            Mode  Cnt  Score   Error  Units
TTL.services.graphServices.DijkstraRunner.getShortestForAllOrdersByBranchCodeParallel  thrpt   21  7,749 ± 1,611  ops/s
*/
