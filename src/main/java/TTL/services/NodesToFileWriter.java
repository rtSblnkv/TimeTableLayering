package TTL.services;

import TTL.models.Node;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class NodesToFileWriter {

    /**
     * Create file by filename path if it doesn't exist
     * @param fileName - name of file for creating
     */
    public static void createFile(String fileName)
    {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println(fileName + ".txt файл создан в корневой директории проекта");
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Write result for current Node in dijkstra runner algorithm
     * in file with name fileName
     * @param fileName - Name for file to write
     * @param curNode - Node for which info is written
     * @param nodes - shortest path for curNode
     * @param datasetDistance - The value of distance to Node from dataset
     * @param calcDistance - The value of distance to Node computed by DijkstraRunner
     * @param epsilon - difference between calcDistance and  datasetDistance
     */
    public static void writeResultInFile(String fileName,Node curNode,
                                         List<Node> nodes,double datasetDistance,
                                         double calcDistance,double epsilon)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true)))
        {
            writer.write(" "  +
                    curNode.toString());
                    /*"\n Path :: " + nodes.toString() +
                    "\n Distance: Calculated -> " +
                    calcDistance + " In Dataset -> " +
                    datasetDistance + " Epsilon -> " +
                    epsilon);*/

            writer.append("\n");
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
