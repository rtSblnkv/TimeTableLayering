package TTL.exception_handlers;

import TTL.models.Node;

public class NoShortPathException extends RuntimeException{

    private Node unattainableNode;

    public NoShortPathException(String errMessage,Node unattainableNode)
    {
        super(errMessage);
        this.unattainableNode = unattainableNode;
    }

    public Node getUnattainableNode() {
        return unattainableNode;
    }

}
