package TTL.controllers.listWorkers;

public class WorkerFactory {

    /**
     * fabric method
     * @param cls - name of class type which list contains
     * @return Worker class
     */
    public Worker getWorker(String cls)
    {
        switch(cls)
        {
            case"Branch": return new BranchWorker();

            case"Order": return new OrderWorker();

            case"Edge":return new EdgeWorker();

            case"Node":return new NodeWorker();

            default: return null;
        }
    }
}
