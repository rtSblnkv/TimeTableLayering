package TTL.services.writers;

import TTL.exception_handlers.WriteResultException;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

public class Writer {

    /**
     * Create file by filename path if it doesn't exist
     * @param fileName - name of file for creating
     */
    public static void createFile(String fileName) throws FileAlreadyExistsException, WriteResultException
    {
        boolean alreadyExists;
        try {
            File file = new File(fileName);
            alreadyExists = file.createNewFile();
        }
        catch(IOException ex)
        {
            String errMessage = "Error while writing in " + fileName  + ex.getMessage();
            throw new WriteResultException(errMessage,ex);
        }
        if(alreadyExists)
        {
            throw new FileAlreadyExistsException(fileName + ".txt файл уже создан");
        }
    }
}
