
public class ShopCalculator {

    private static final int[] PACKET_PRICES = {10, 20, 0};

    public double calculate(Product[] products, int[] counts,
                            int packetIndex, String promocode) {

        double total = 0;

        if (products.length != counts.length) {
            throw new IllegalArgumentException(
                "Ошибка данных о товарах"
            );
        }

        for (int i = 0; i < products.length; i++) {
            if (counts[i] < 0) {
                throw new IllegalArgumentException(
                    "Количество не может быть отрицательным!"
                );
            }

            total += products[i].getPrice() * counts[i];
        }

        if (promocode.trim().equalsIgnoreCase("SHOP10")) {
            total *= 0.9;
        }

        total += PACKET_PRICES[packetIndex];

        return total;
    }
}
