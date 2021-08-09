package TTL.exception_handlers;

/**
 * Thrown when data uploading failed
 */
public class UploadDataException extends RuntimeException {
    public UploadDataException(String errMessage, Throwable err) {
        super(errMessage, err);
    }

}
