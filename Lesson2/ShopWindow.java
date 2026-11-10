
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class ShopWindow extends JFrame {

    private Product[] catalog = {
        new Product("Хлеб", 30.0),
        new Product("Молоко", 60.0),
        new Product("Колбаса", 99.5)
    };

    private JCheckBox[] products = new JCheckBox[catalog.length];
    private JTextField[] counts = new JTextField[catalog.length];

    private JComboBox<String> packets;
    private JTextField promocode;
    private JLabel resultLabel;

    private ShopCalculator calculator = new ShopCalculator();

    public ShopWindow() {
        setTitle("Мини-магазин");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BoxLayout(
            getContentPane(), BoxLayout.Y_AXIS
        ));

        createInterface();
        setVisible(true);
    }

    private void addCentered(JComponent component) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(component);
    }

    // Создание интерфейса
    private void createInterface() {
        addCentered(new JLabel("Мини-магазин"));
        addCentered(new JLabel("Выберите продукты"));

        for (int i = 0; i < catalog.length; i++) {
            final int index = i;

            products[i] = new JCheckBox(
                catalog[i].getName() + " - "
                + catalog[i].getPrice() + " руб."
            );

            counts[i] = new JTextField("0", 5);

            JPanel panel = new JPanel(new FlowLayout());
            panel.add(products[i]);
            panel.add(new JLabel("Количество:"));
            panel.add(counts[i]);

            panel.setMaximumSize(new Dimension(380, 45));
            addCentered(panel);

            // Галочка изменяет количество
            products[i].addActionListener(e -> {
                counts[index].setText(
                    products[index].isSelected() ? "1" : "0"
                );
            });

            // Количество изменяет галочку
            counts[i].getDocument().addDocumentListener(
                new DocumentListener() {
                    public void insertUpdate(DocumentEvent e) {
                        update();
                    }

                    public void removeUpdate(DocumentEvent e) {
                        update();
                    }

                    public void changedUpdate(DocumentEvent e) {
                        update();
                    }

                    private void update() {
                        try {
                            int count = Integer.parseInt(
                                counts[index].getText().trim()
                            );
                            products[index].setSelected(count > 0);
                        } catch (NumberFormatException ex) {
                            products[index].setSelected(false);
                        }
                    }
                }
            );
        }

        addCentered(new JLabel("Пакет"));

        String[] packetNames = {
            "Маленький пакет - 10 руб.",
            "Большой пакет - 20 руб.",
            "Без пакета - 0 руб."
        };

        packets = new JComboBox<>(packetNames);
        packets.setMaximumSize(new Dimension(220, 30));
        addCentered(packets);

        addCentered(new JLabel("Промокод"));

        promocode = new JTextField(15);
        promocode.setMaximumSize(new Dimension(220, 30));
        addCentered(promocode);

        addCentered(new JLabel("Промокод SHOP10: скидка 10%"));

        JButton button = new JButton("Рассчитать стоимость");
        addCentered(button);

        resultLabel = new JLabel(" ");
        addCentered(resultLabel);

        button.addActionListener(e -> calculateOrder());
    }

    // Получение данных из формы и вывод результата
    private void calculateOrder() {
        try {
            int[] quantities = new int[catalog.length];

            for (int i = 0; i < counts.length; i++) {
                quantities[i] = Integer.parseInt(
                    counts[i].getText().trim()
                );
            }

            double total = calculator.calculate(
                catalog,
                quantities,
                packets.getSelectedIndex(),
                promocode.getText()
            );

            resultLabel.setText(
                String.format("Итого: %.2f руб.", total)
            );

        } catch (NumberFormatException ex) {
            resultLabel.setText(
                "Ошибка: введите целое количество!"
            );
        } catch (IllegalArgumentException ex) {
            resultLabel.setText(ex.getMessage());
        }
    }
}
