package TTL.services.writers;

import TTL.exception_handlers.WriteResultException;
import TTL.models.Node;
import TTL.models.Route;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class NodesToJsonWriter {

    public static void writePathToJson(String fileName, Map<Node, Route<Node>> nodes) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonList = mapper.writeValueAsString(nodes);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true)))
        {
            writer.write(jsonList);
        }
        catch(IOException ex)
        {
            String errMessage = "Error while writing in " + fileName + "."  + ex.getMessage();
            throw new WriteResultException(errMessage,ex);
        }
    }
}
