package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import data.FareCalculator;
import data.MetroService;
import data.Ticket;
import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class BuyTicketUI extends JFrame {

    // --- MÀU SẮC ---
    private final Color PRIMARY = new Color(20, 25, 80);
    private final Color BG = new Color(242, 248, 255);
    private final Color CARD_BG = Color.WHITE;
    private final Color CARD_BORDER = new Color(210, 225, 245);
    private final Color ICON_BG = new Color(225, 240, 255);

    public Ticket ticket = new Ticket();
    private MetroService metroService = new MetroService();
    private FareCalculator fareCalculator = new FareCalculator();

    public BuyTicketUI() {
        setTitle("Mua vé Metro");
        setSize(430, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BG);

        // --- CONTENT CHÍNH ---
        JPanel mainContent = new JPanel();
        mainContent.setBackground(BG);
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBorder(new EmptyBorder(0, 0, 80, 0));

        // Header & Welcome
        mainContent.add(header());
        mainContent.add(wrap(welcomeCard()));

        // Section 1: Nổi bật (Đã thay link web bằng ảnh local)
        mainContent.add(sectionTitle("🔥 Nổi bật 🔥"));
        mainContent.add(wrap(ticketCard("Vé 1 ngày", "40.000 đ", "/img/vengay.png")));
        mainContent.add(wrap(ticketCard("Vé 3 ngày", "90.000 đ", "/img/ve3ngay.png")));
        mainContent.add(wrap(ticketCard("Vé tháng", "300.000 đ", "/img/vethang.png")));

        // Section 2: Ưu đãi
        mainContent.add(sectionTitle("Ưu đãi Học sinh 🎒 Sinh viên 🎓"));
        mainContent.add(wrap(ticketCard("Vé tháng HSSV", "150.000 đ", "/img/vethang.png")));

        // ==== CHỌN GA METRO ==== //
        JPanel stationPanel = new JPanel();
        stationPanel.setBackground(BG);
        stationPanel.setLayout(new GridLayout(3, 2, 10, 10));
        stationPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel lblFrom = new JLabel("Ga đi:");
        lblFrom.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JLabel lblTo = new JLabel("Ga đến:");
        lblTo.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        String[] stationNames = metroService.getStationNames();
        JComboBox<String> cbFrom = new JComboBox<>(stationNames);
        cbFrom.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JComboBox<String> cbTo = new JComboBox<>(stationNames);
        cbTo.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JLabel lblFare = new JLabel("Giá vé: ---");
        lblFare.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblFare.setForeground(Color.BLUE);

        stationPanel.add(lblFrom);
        stationPanel.add(cbFrom);
        stationPanel.add(lblTo);
        stationPanel.add(cbTo);
        stationPanel.add(new JLabel("")); 
        stationPanel.add(lblFare);

        mainContent.add(stationPanel);

        // --- SỰ KIỆN TÍNH GIÁ ---
        ItemListener listener = e -> {
            int fIndex = cbFrom.getSelectedIndex();
            int tIndex = cbTo.getSelectedIndex();

            if (fIndex >= 0 && tIndex >= 0) {
                double dist = metroService.calculateDistance(fIndex, tIndex);
                double fareDouble = fareCalculator.calculateFare(dist); 
                int fare = (int) fareDouble;

                lblFare.setText("Giá vé: " + String.format("%,d đ", fare) + " (" + String.format("%.1f", dist) + " km)");

                String startName = metroService.getStationAt(fIndex).getName();
                String endName = metroService.getStationAt(tIndex).getName();

                ticket.setSeatNumber(startName + " → " + endName);
                ticket.setPrice(fareDouble);
            }
        };

        cbFrom.addItemListener(listener);
        cbTo.addItemListener(listener);

        // Scroll Pane
        JScrollPane scroll = new JScrollPane(mainContent);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll, BorderLayout.CENTER);

        // --- FOOTER ---
        JPanel bottomArea = new JPanel();
        bottomArea.setLayout(new BoxLayout(bottomArea, BoxLayout.Y_AXIS));
        bottomArea.setBackground(Color.WHITE);
        bottomArea.add(new JSeparator());
        bottomArea.add(createBookButton());
        bottomArea.add(createBottomBar());
        add(bottomArea, BorderLayout.SOUTH);
    }

    // --- CÁC HÀM TẠO GIAO DIỆN ---

    private JPanel header() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(BG);
        p.setBorder(new EmptyBorder(15, 20, 10, 20));

        // Load icon từ local
        JLabel home = new JLabel(loadIcon("/img/trangchu.png", 24, 24)); 
        home.setCursor(new Cursor(Cursor.HAND_CURSOR));
        home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new MetroAppUI().setVisible(true);
                dispose();
            }
        });

        JLabel title = new JLabel("Mua vé", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(PRIMARY);

        JLabel dummy = new JLabel();
        dummy.setPreferredSize(new Dimension(24, 24));

        p.add(home, BorderLayout.WEST);
        p.add(title, BorderLayout.CENTER);
        p.add(dummy, BorderLayout.EAST);

        return p;
    }

    private JPanel welcomeCard() {
        RoundedPanel p = new RoundedPanel(25, CARD_BG);
        p.setLayout(new BorderLayout(15, 0));
        p.setBorder(new EmptyBorder(15, 15, 15, 15));
        p.setPreferredSize(new Dimension(380, 90));
        p.setBorderColor(CARD_BORDER);

        JLabel avatar = new JLabel(loadIcon("/img/user-male-circle.png", 50, 50)); 

        JLabel text = new JLabel("<html><div style='width: 200px;'>"
                + "<span style='font-size:11px; color:#212568; font-weight:bold;'>Chào mừng bạn!</span><br/>"
                + "<span style='font-size:10px; color:#555555;'>Bắt đầu các trải nghiệm mới cùng Metro nhé!</span>"
                + "</div></html>");

        p.add(avatar, BorderLayout.WEST);
        p.add(text, BorderLayout.CENTER);
        return p;
    }

    private Component sectionTitle(String t) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.setBackground(BG);
        p.setBorder(new EmptyBorder(10, 20, 0, 0));
        JLabel lbl = new JLabel(t);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 16));
        lbl.setForeground(PRIMARY);
        p.add(lbl);
        return p;
    }

    private JPanel ticketCard(String name, String price, String iconPath) {
        RoundedPanel p = new RoundedPanel(20, CARD_BG);
        p.setLayout(new BorderLayout(15, 0));
        p.setBorder(new EmptyBorder(15, 15, 15, 15));
        p.setPreferredSize(new Dimension(380, 85));
        p.setBorderColor(CARD_BORDER);

        RoundedPanel iconContainer = new RoundedPanel(15, ICON_BG);
        iconContainer.setPreferredSize(new Dimension(50, 50));
        iconContainer.setLayout(new GridBagLayout());
        
        // Load icon từ local path
        iconContainer.add(new JLabel(loadIcon(iconPath, 32, 32)));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);

        JLabel title = new JLabel(name);
        title.setFont(new Font("SansSerif", Font.BOLD, 15));
        title.setForeground(PRIMARY);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel money = new JLabel(price);
        money.setFont(new Font("SansSerif", Font.PLAIN, 14));
        money.setForeground(Color.DARK_GRAY);
        money.setAlignmentX(Component.LEFT_ALIGNMENT);

        textPanel.add(Box.createVerticalStrut(2));
        textPanel.add(title);
        textPanel.add(Box.createVerticalStrut(4));
        textPanel.add(money);

        p.add(iconContainer, BorderLayout.WEST);
        p.add(textPanel, BorderLayout.CENTER);

        p.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        p.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ticket.setSeatNumber(name);
                String priceNumber = price.replace(".", "").replace("đ", "").trim();
                double priceValue = Double.parseDouble(priceNumber);
                ticket.setPrice(priceValue);
                new OrderScreenUI(ticket);
                Window currentWindow = SwingUtilities.getWindowAncestor(p);
                if (currentWindow != null) currentWindow.dispose();
            }
            @Override
            public void mouseEntered(MouseEvent e) { p.setBackground(new Color(235, 245, 255)); p.repaint(); }
            @Override
            public void mouseExited(MouseEvent e) { p.setBackground(CARD_BG); p.repaint(); }
        });

        return p;
    }

    private JPanel createBookButton() {
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Color.WHITE);
        container.setBorder(new EmptyBorder(10, 20, 5, 20));

        RoundedPanel btn = new RoundedPanel(20, PRIMARY);
        btn.setLayout(new BorderLayout());
        btn.setPreferredSize(new Dimension(100, 50));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel lblText = new JLabel("ĐẶT VÉ NGAY", SwingConstants.CENTER);
        lblText.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblText.setForeground(Color.WHITE);
        btn.add(lblText, BorderLayout.CENTER);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn.setBackground(new Color(40, 45, 100));
                if (ticket.getPrice() > 0) {
                    new OrderScreenUI(ticket);
                    Window currentWindow = SwingUtilities.getWindowAncestor(btn);
                    if (currentWindow != null) currentWindow.dispose();
                } else {
                    JOptionPane.showMessageDialog(container, "Vui lòng chọn hành trình!", "Lỗi", JOptionPane.WARNING_MESSAGE);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) { btn.setBackground(PRIMARY); }
        });
        container.add(btn, BorderLayout.CENTER);
        return container;
    }

    private JPanel createBottomBar() {
        JPanel bar = new JPanel();
        bar.setBackground(Color.WHITE);
        bar.setLayout(new GridLayout(1, 4));
        bar.setPreferredSize(new Dimension(400, 70));
        bar.setBorder(new EmptyBorder(5, 0, 0, 0));

        // Load icon local cho menu dưới
        bar.add(createNavButton("/img/qr.png", "")); 
        bar.add(createNavButton("/img/ve.png", "")); 
        bar.add(createNavButton("/img/lichsu.png", "")); 

        return bar;
    }

    private JLabel createNavButton(String iconPath, String text) {
        JLabel lbl = new JLabel(loadIcon(iconPath, 24, 24));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        return lbl;
    }

    private JPanel wrap(JPanel content) {
        JPanel wrapper = new JPanel();
        wrapper.setBackground(BG);
        wrapper.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 8));
        wrapper.add(content);
        return wrapper;
    }

    // --- HÀM LOAD ICON TỪ LOCAL (QUAN TRỌNG) ---
    private ImageIcon loadIcon(String path, int width, int height) {
        try {
            // Sử dụng getResource để lấy file từ classpath (thư mục src/img)
            URL imgUrl = getClass().getResource(path);
            if (imgUrl != null) {
                BufferedImage image = ImageIO.read(imgUrl);
                if (image != null) {
                    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    return new ImageIcon(scaledImage);
                }
            } else {
                System.err.println("Không tìm thấy ảnh: " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static class RoundedPanel extends JPanel {
        private Color backgroundColor;
        private int cornerRadius = 15;
        private Color borderColor = null;
        public RoundedPanel(int radius, Color bgColor) {
            super();
            this.cornerRadius = radius;
            this.backgroundColor = bgColor;
            setOpaque(false);
        }
        public void setBorderColor(Color c) { this.borderColor = c; }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
                graphics.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
            }
            if (borderColor != null) {
                graphics.setColor(borderColor);
                graphics.setStroke(new BasicStroke(1));
                graphics.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BuyTicketUI().setVisible(true));
    }
}