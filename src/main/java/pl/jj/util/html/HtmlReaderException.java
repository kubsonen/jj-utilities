package pl.jj.util.html;

/**
 * Reader exception.
 * @author JNartowicz
 */
public class HtmlReaderException extends Exception {

    public static final String FILE_NOT_EXISTS = "File not exists.";

    public HtmlReaderException(Throwable cause) {
        super(cause);
    }

    public HtmlReaderException(String message) {
        super(message);
    }
}
