package Compulsory;

public class InvalidCatalogException extends Exception {
    /**
     * @param except the exception
     */
    public InvalidCatalogException(Exception except) {
        super("Invalid catalog file.", except);
    }
}
