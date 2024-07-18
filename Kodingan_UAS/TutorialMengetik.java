package pk10_uas_soalmembuataplikasi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Soal membuat aplikasi
 * @author Bariq 18/07-2024
 */

public class TutorialMengetik extends JFrame 
{
    private JTextArea textArea;
    private Map<String, JButton> keyMap;
    private final Color originalColor = new JButton().getBackground();
    private final Color highlightColor = Color.YELLOW;

    public TutorialMengetik() 
    {
        // Mengatur frame
        setTitle("Aplikasi Mengetik - Bariq Nashir Musad");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Membuat area teks
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 24));
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Membuat panel untuk informasi
        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        JLabel nimLabel = new JLabel("NIM: 41322010024", JLabel.LEFT);
        JLabel jurusanLabel = new JLabel("Jurusan: Teknik Mesin", JLabel.LEFT);
        JLabel uasLabel = new JLabel("UAS Pemrograman Komputer", JLabel.LEFT);

        nimLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        jurusanLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        uasLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        infoPanel.add(nimLabel);
        infoPanel.add(jurusanLabel);
        infoPanel.add(uasLabel);

        add(infoPanel, BorderLayout.NORTH);

        // Membuat panel untuk keyboard
        JPanel keyboardPanel = new JPanel();
        keyboardPanel.setLayout(new GridBagLayout());

        // Membuat peta untuk menyimpan tombol keyboard
        keyMap = new HashMap<>();

        // Teks tombol keyboard
        String[][] keyTexts = 
        {
            {"`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=", "Backspace"},
            {"Tab", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "[", "]", "\\"},
            {"Caps", "A", "S", "D", "F", "G", "H", "J", "K", "L", ";", "'", "Enter"},
            {"Shift", "Z", "X", "C", "V", "B", "N", "M", ",", ".", "/", "Shift"},
            {"Ctrl", "Fn", "Alt", "Space", "Alt", "Ctrl"}
        };

        // Membuat tombol keyboard dan menambahkannya ke panel dan peta
        GridBagConstraints gbc = new GridBagConstraints();
        for (int row = 0; row < keyTexts.length; row++) 
        {
            for (int col = 0; col < keyTexts[row].length; col++) 
            {
                if (!keyTexts[row][col].isEmpty()) 
                {
                    JButton button = new JButton(keyTexts[row][col]);
                    button.setFont(new Font("Arial", Font.PLAIN, 16));
                    button.setHorizontalAlignment(SwingConstants.CENTER);
                    button.setVerticalAlignment(SwingConstants.CENTER);
                    gbc.gridx = col;
                    gbc.gridy = row;
                    gbc.gridwidth = 1;
                    gbc.gridheight = 1;
                    gbc.weightx = 1.0;
                    gbc.weighty = 1.0;
                    gbc.insets = new Insets(2, 2, 2, 2); // Margin antar tombol
                    gbc.fill = GridBagConstraints.BOTH;

                    // Mengatur lebar tombol tertentu
                    switch (keyTexts[row][col]) 
                    {
                        case "Backspace":
                            gbc.gridwidth = 2;
                            break;
                        case "Tab":
                        case "Caps":
                        case "Enter":
                        case "Shift":
                        case "Ctrl":
                        case "Alt":
                            gbc.gridwidth = 1;
                            break;
                        case "Space":
                            gbc.gridwidth = 3;
                            break;
                    }

                    keyboardPanel.add(button, gbc);
                    keyMap.put(keyTexts[row][col], button);
                }
            }
        }

        // Menambahkan panel keyboard ke frame dan merapikan layout di tengah
        JPanel keyboardContainer = new JPanel(new GridBagLayout());
        keyboardContainer.add(keyboardPanel, new GridBagConstraints());
        add(keyboardContainer, BorderLayout.SOUTH);

        // Menambahkan KeyListener untuk menangani penekanan tombol
        textArea.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyPressed(KeyEvent e) 
            {
                String keyText = KeyEvent.getKeyText(e.getKeyCode());
                if (keyMap.containsKey(keyText)) 
                {
                    JButton button = keyMap.get(keyText);
                    button.setBackground(highlightColor);
                    switch (e.getKeyCode()) 
                    {
                        case KeyEvent.VK_BACK_SPACE:
                            String text = textArea.getText();
                            if (text.length() > 0) 
                            {
                                textArea.setText(text.substring(0, text.length() - 1));
                            }
                            break;
                        case KeyEvent.VK_TAB:
                            textArea.append("\t");
                            e.consume(); // Konsumsi event untuk mencegah default behavior
                            break;
                        case KeyEvent.VK_ENTER:
                            textArea.append("\n");
                            break;
                        case KeyEvent.VK_SPACE:
                            textArea.append(" ");
                            break;
                        default:
                            textArea.append(String.valueOf(e.getKeyChar()));
                            break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) 
            {
                String keyText = KeyEvent.getKeyText(e.getKeyCode());
                if (keyMap.containsKey(keyText)) 
                {
                    JButton button = keyMap.get(keyText);
                    button.setBackground(originalColor);
                }
            }
        });

        // Membuat frame terlihat
        setVisible(true);
        textArea.requestFocusInWindow();
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> 
        {
            new TutorialMengetik();
        });
    }
} //akhir kelas
