package baguette;

/**
 * Custom exception class for handling errors specific to the Baguette application.
 */
public class BaguetteException extends Exception {
    /**
     * Constructs a new BaguetteException with the specified message.
     *
     * @param message The message to be displayed to the user.
     */
    public BaguetteException(String message) {
        super(message);
    }
}