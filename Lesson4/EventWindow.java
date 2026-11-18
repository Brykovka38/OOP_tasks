import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.Box;

import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EventWindow extends JFrame {

    public EventWindow() {
        String[] languagesList = {
            "Java",
            "C#",
            "Python"
        };

        String[] info = {
            "Java — объектно-ориентированный язык программирования.",
            "C# — язык программирования платформы .NET.",
            "Python — язык программирования с динамической типизацией."
        };

        JComboBox<String> languages = new JComboBox<>(languagesList);
        JCheckBox infoLanguages =
            new JCheckBox("Показывать информацию о языке");

        JLabel label = new JLabel("Вы выбрали: " + languagesList[0]);
        JLabel labelInfo = new JLabel(info[0]);

        JTextField text = new JTextField(20);
        JLabel textInfo = new JLabel("Длина строки: 0");
        JLabel textType = new JLabel("Характеристика: Короткая");

        // Настройка размеров компонентов
        languages.setMaximumSize(languages.getPreferredSize());
        text.setMaximumSize(text.getPreferredSize());

        // Выравнивание элементов
        languages.setAlignmentX(0.0f);
        label.setAlignmentX(0.1f);
        labelInfo.setAlignmentX(0.0f);
        infoLanguages.setAlignmentX(0.0f);
        text.setAlignmentX(0.0f);
        textInfo.setAlignmentX(0.0f);
        textType.setAlignmentX(0.0f);

        // Создание панели
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Выберите язык для ООП"));
        panel.add(languages);
        panel.add(label);

        panel.add(Box.createVerticalStrut(10));
        panel.add(infoLanguages);
        panel.add(labelInfo);
        labelInfo.setVisible(false);

        panel.add(Box.createVerticalStrut(20));
        panel.add(new JLabel("Анализ строки"));
        panel.add(text);
        panel.add(textInfo);
        panel.add(textType);

        add(panel);

        // Событие изменения JComboBox
        languages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) languages.getSelectedItem();
                label.setText("Вы выбрали: " + selected);

                int index = languages.getSelectedIndex();
                labelInfo.setText(info[index]);
            }
        });

        // Событие выбора JCheckBox
        infoLanguages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelInfo.setVisible(infoLanguages.isSelected());
            }
        });

        // Событие изменения текста JTextField
        text.getDocument().addDocumentListener(new DocumentListener() {

            private void updateTextInfo() {
                String infoText = text.getText();
                int length = infoText.length();

                textInfo.setText("Длина строки: " + length);

                if (length <= 5) {
                    textType.setText("Характеристика: Короткая");
                } else if (length <= 10) {
                    textType.setText("Характеристика: Нормальная");
                } else {
                    textType.setText("Характеристика: Длинная");
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTextInfo();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTextInfo();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTextInfo();
            }
        });

        // Настройка окна
        setTitle("Lesson 4");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
