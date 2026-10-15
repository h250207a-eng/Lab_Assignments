import java.util.ArrayList;
import java.util.List;

/** Lab 9, Exercise 5: calculate minimum change for US coin denominations. */
public class CoinChange {
    enum Coin {
        PENNY(1), NICKEL(5), DIME(10), QUARTER(25);

        private final int cents;
        Coin(int cents) { this.cents = cents; }
        public int getCents() { return cents; }
    }

    static List<Coin> fewestCoins(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
        List<Coin> result = new ArrayList<>();
        Coin[] descending = {Coin.QUARTER, Coin.DIME, Coin.NICKEL, Coin.PENNY};
        // Greedy selection is optimal for these denominations; not for every coin system.
        for (Coin coin : descending) {
            while (amount >= coin.getCents()) {
                result.add(coin);
                amount -= coin.getCents();
            }
        }
        return List.copyOf(result);
    }

    public static void main(String[] args) {
        for (int amount : new int[]{0, 30, 41, 99}) {
            List<Coin> coins = fewestCoins(amount);
            System.out.println(amount + " cents: " + coins + " (" + coins.size() + " coins)");
        }
    }
}
