/** Thrown when someone tries to borrow an item that is already on loan. */
public class ItemNotAvailableException extends Exception {
    private static final long serialVersionUID = 1L;
    public ItemNotAvailableException(String message) { super(message); }
}
