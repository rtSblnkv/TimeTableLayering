package TTL;

import TTL.models.Node;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class NodesToFileWriter {

    public static void createFile(String fileName)
    {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println(fileName + ".txt файл создан в корневой директории проекта");
            } else System.out.println(fileName + ".txt файл уже существует в корневой директории проекта");
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void writeNodesInFile(String fileName, List<Node> nodes_)
    {
        createFile(fileName);
        try (FileWriter writer = new FileWriter(fileName,false))
        {
            int  i = 0;
            for (Node node:nodes_)
            {
                writer.write(node.toString());
                writer.append("\n");
                i++;
            }
            System.out.println(i);
            System.out.println("Nodes have been written");
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void writeNodesInFile(String fileName,List<Node> nodes_,String marker,String colour)
    {
        createFile(fileName);
        try (FileWriter writer = new FileWriter(fileName,false))
        {
            int  i = 0;
            for (Node node:nodes_)
            {
                writer.write(node.toString());
                writer.append("\n");
                i++;
            }
            System.out.println(i);
            System.out.println("Nodes have been written");
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /*writeNodesInFile("nodes.txt",nodes);
        HashMap<String,String> idColour = new HashMap<String,String>(){{
            put("NS","yellow");
            put("TP","red");
            put("BK","green");
            put("Breakfast","red");
            put("Lunch","yellow");
            put("Dinner","green");
        }};

        HashMap<String,List<Order>> ordersByBranch = ToHashMap.ordersListToHashMapByBranch(orders,branches);
        ordersByBranch.forEach((id,order) ->
                //writeNodesInFileCustom(id + ".txt",createListOfOrderNodes(order),"circle2",idColour.get(id))
                writeNodesInFile(id + ".txt",createListOfOrderNodes(order))
        );
        HashMap<String,List<Order>> ordersByOrderType = ToHashMap.ordersListToHashMapByOrderType(orders);
        ordersByOrderType.forEach((id,order) ->
                //writeNodesInFileCustom(id + ".txt",createListOfOrderNodes(order),"plus3",idColour.get(id))
                writeNodesInFile(id + ".txt",createListOfOrderNodes(order))
        );*/
}
