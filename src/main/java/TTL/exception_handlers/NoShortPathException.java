package TTL.exception_handlers;

import TTL.models.Node;
import lombok.Getter;

/**
 * Thrown when no any path to the node from current node
 * Param - unattainable node
 */
@Getter
public class NoShortPathException extends RuntimeException{

    private Node unattainableNode;

    public NoShortPathException(String errMessage,Node unattainableNode)
    {
        super(errMessage);
        this.unattainableNode = unattainableNode;
    }
}
