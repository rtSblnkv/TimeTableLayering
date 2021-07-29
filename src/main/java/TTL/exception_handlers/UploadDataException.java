package TTL.exception_handlers;

public class UploadDataException extends RuntimeException {
    public UploadDataException(String errMessage,Throwable err) {
        super(errMessage,err);
    }

}
