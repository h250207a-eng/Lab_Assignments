/** Contract for anything that can be lent out and returned. */
public interface Borrowable {
    void borrow(Member member) throws ItemNotAvailableException;
    void giveBack();
}
