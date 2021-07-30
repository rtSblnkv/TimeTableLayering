package TTL.services;

import TTL.exception_handlers.WriteResultException;
import TTL.models.Node;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;

public class NodesToFileWriter {

    /**
     * Create file by filename path if it doesn't exist
     * @param fileName - name of file for creating
     */
    public static void createFile(String fileName) throws FileAlreadyExistsException,WriteResultException
    {
        boolean alreadyExists;
        try {
            File file = new File(fileName);
            alreadyExists = file.createNewFile();
        }
        catch(IOException ex)
        {
            String errMessage = "Error while writing in " + fileName + ".txt :"  + ex.getMessage();
            throw new WriteResultException(errMessage,ex);
        }
        if(alreadyExists)
        {
            throw new FileAlreadyExistsException(fileName + ".txt файл уже создан");
        }
    }

    /**
     * Write result for current Node in dijkstra runner algorithm
     * in file with name fileName
     * @param fileName - Name of the file to write
     * @param nodeTo - current node
     * @param path - shortest path for curNode
     * @param datasetDistance - The value of distance to Node from dataset
     * @param calculatedDistance - The value of distance to Node computed by DijkstraRunner
     * @param epsilon - difference between calcDistance and  datasetDistance
     */
    public static void writeResultInFile(String fileName, Node nodeTo, List<Node> path,
                                          double datasetDistance, double calculatedDistance,double epsilon)
    {
        String lat = Double.toString( nodeTo.getLatitude());
        String lon = Double.toString( nodeTo.getLongtitude());
        String datasetDist = Double.toString(datasetDistance);
        String calcDist = Double.toString(calculatedDistance);
        String eps = Double.toString(epsilon);
        try{
            writeLineInFile(fileName, lat,lon,
                    path.toString(),datasetDist ,
                    calcDist, eps);
        }
        catch( WriteResultException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Write incorrect result for current Node in dijkstra runner algorithm
     * in file of error nodes with name fileName
     * @param fileName - Name of the file to write
     * @param latitude - incorrect node latitude
     * @param longtitude - incorrect node longtitude
     */
    public static void writeErrResultInFile(String fileName, double latitude, double longtitude)
    {
        String lat = Double.toString(latitude);
        String lon = Double.toString(longtitude);
        try {
            writeLineInFile(fileName, lat, lon, "?", "?",
                    "?", "?");
        }
        catch(WriteResultException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * write line in file with path fileName
     * all data in string format
     * @param fileName - file path
     * @param lat - Node latitude
     * @param lon - Node longtitude
     * @param nodesList - list of nodes
     * @param datasetDistance - value of min distance in dataset
     * @param calcDistance - value of min distance
     * @param epsilon - difference between datasetDistance and calcDistance
     * @throws WriteResultException - is throwed when result writing failed
     */
    private static void writeLineInFile(String fileName,String lat,String lon,
                                        String nodesList,String datasetDistance,
                                        String calcDistance, String epsilon) throws WriteResultException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true)))
        {
            writer.write(" ["  +
                    lat + "," + lon + "]" +
                    "\n Path :: " + nodesList +
                    "\n Distance: Calculated -> " +
                    calcDistance + " In Dataset -> " +
                    datasetDistance + " Epsilon -> " +
                    epsilon);
            writer.append("\n");
        }
        catch(IOException ex)
        {
            String errMessage = "Error while writing in " + fileName + ".txt :"  + ex.getMessage();
            throw new WriteResultException(errMessage,ex);
        }
    }

}
