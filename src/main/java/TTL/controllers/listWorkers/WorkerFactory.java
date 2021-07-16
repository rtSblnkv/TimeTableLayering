package TTL.controllers.Lists;

import TTL.controllers.dataloader.*;

public class WorkerFactory {

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
