package view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Ticket extends JFrame {

    // --- CẤU HÌNH URL ICON & ẢNH NỀN ---
    // Bạn có thể thay đổi các đường dẫn này bằng link ảnh thực tế của bạn
    private static final String URL_BG_CITY = "https://images.unsplash.com/photo-1565593683058-20d292b15749?q=80&w=1000&auto=format&fit=crop"; // Ảnh thành phố
    
    // Icon menu (Sử dụng icon từ Icons8 hoặc Flaticon - link mẫu)
    private static final String ICON_TICKET = "https://img.icons8.com/fluency/96/train-ticket.png";
    private static final String ICON_MY_TICKET = "https://img.icons8.com/fluency/96/two-tickets.png";
    private static final String ICON_QR = "https://img.icons8.com/fluency/96/qr-code.png";
    private static final String ICON_INFO = "https://img.icons8.com/fluency/96/info.png";
    private static final String ICON_MAP = "https://img.icons8.com/fluency/96/map-marker.png";
    private static final String ICON_PARK = "https://img.icons8.com/fluency/96/theme-park.png";
    private static final String ICON_CART = "https://img.icons8.com/fluency/96/shopping-cart.png";
    private static final String ICON_USER = "https://img.icons8.com/fluency/96/user-male-circle.png";
    
    // Màu sắc
    private static final Color COLOR_ICON_BG = new Color(240, 248, 255); 
    private static final Color COLOR_TEXT_DARK = new Color(60, 60, 60);
    private static final Color COLOR_PRIMARY = new Color(0, 90, 170);

    public Ticket() {
        setTitle("HCMC Metro App");
        setSize(400, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(400, 750));
        setContentPane(layeredPane);

        // 1. LAYER BACKGROUND (Ảnh từ mạng)
        JPanel backgroundPanel = new JPanel() {
            Image bgImage = loadRemoteImage(URL_BG_CITY); // Tải ảnh nền

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgImage != null) {
                    // Vẽ ảnh cover (scale to fill)
                    g.drawImage(bgImage, 0, 0, getWidth(), 400, this);
                    
                    // Phủ một lớp màu trắng mờ ở dưới để dễ đọc chữ hơn nếu cần
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setPaint(new GradientPaint(0, 200, new Color(255,255,255, 0), 0, 400, new Color(255,255,255, 255)));
                    g2.fillRect(0, 200, getWidth(), 200);
                } else {
                    g.setColor(new Color(40, 120, 180));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        backgroundPanel.setBounds(0, 0, 400, 750);
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);

        // 2. LAYER CARD MENU (Phần màu trắng bo tròn)
        int cardY = 280;
        JPanel cardPanel = new RoundedPanel(30, Color.WHITE);
        cardPanel.setLayout(null);
        cardPanel.setBounds(20, cardY, 345, 350);

        // --- Thêm Menu Items (Load icon từ URL) ---
        // Hàng 1
        cardPanel.add(createMenuItem("Mua vé", ICON_TICKET, 20, 60));
        cardPanel.add(createMenuItem("Vé của tôi", ICON_MY_TICKET, 100, 60));
        cardPanel.add(createMenuItem("Đổi mã\nlấy vé", ICON_QR, 180, 60));
        cardPanel.add(createMenuItem("Thông tin\nvé", ICON_INFO, 260, 60));

        // Hàng 2
        cardPanel.add(createMenuItem("Hành trình", ICON_MAP, 20, 170));
        cardPanel.add(createMenuItem("Mua vé\nSuối Tiên", ICON_PARK, 100, 170));
        cardPanel.add(createMenuItem("Siêu thị\nonline", ICON_CART, 180, 170));
        cardPanel.add(createMenuItem("Tài khoản", ICON_USER, 260, 170));

        // Dấu chấm chuyển trang
        JPanel dots = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.ORANGE);
                g2.fillOval(0, 0, 8, 8);
                g2.setColor(Color.LIGHT_GRAY);
                g2.fillOval(12, 0, 8, 8);
            }
        };
        dots.setOpaque(false);
        dots.setBounds(160, 320, 50, 15);
        cardPanel.add(dots);

        layeredPane.add(cardPanel, JLayeredPane.PALETTE_LAYER);

        // 3. LAYER LOGO
        JPanel logoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillOval(0, 0, 70, 70);
                
                // Vẽ chữ Logo đơn giản
                g2.setColor(COLOR_PRIMARY);
                g2.setFont(new Font("Arial", Font.BOLD, 16));
                g2.drawString("HCMC", 12, 40);
            }
        };
        logoPanel.setOpaque(false);
        logoPanel.setBounds(165, cardY - 35, 70, 70);
        layeredPane.add(logoPanel, JLayeredPane.MODAL_LAYER);

        // 4. LAYER BANNER QUẢNG CÁO
        JPanel bannerPanel = new RoundedPanel(20, new Color(220, 240, 255));
        bannerPanel.setLayout(new BorderLayout());
        JLabel bannerText = new JLabel("<html><center><font color='#005aab'>HƯỚNG DẪN MUA VÉ<br>TUYẾN METRO SỐ 1</font></center></html>", SwingConstants.CENTER);
        bannerText.setFont(new Font("Arial", Font.BOLD, 14));
        bannerPanel.add(bannerText, BorderLayout.CENTER);
        bannerPanel.setBounds(20, 650, 345, 80);
        layeredPane.add(bannerPanel, JLayeredPane.DEFAULT_LAYER);

        // 5. NÚT GỌI (Float Button)
        JButton callButton = new JButton();
        callButton.setBounds(330, 600, 50, 50);
        callButton.setFocusPainted(false);
        callButton.setBorderPainted(false);
        callButton.setContentAreaFilled(false);
        try {
            // Icon điện thoại
            URL callUrl = new URL("https://img.icons8.com/ios-filled/50/228BE6/phone.png");
            Image callImg = ImageIO.read(callUrl).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            callButton.setIcon(new ImageIcon(callImg));
        } catch (Exception e) {
            callButton.setBackground(COLOR_PRIMARY); // Fallback màu nếu lỗi
        }
        layeredPane.add(callButton, JLayeredPane.DRAG_LAYER);
    }

    // --- Helper: Tạo Item với Icon tải từ URL ---
    private JPanel createMenuItem(String title, String imageUrl, int x, int y) {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.setOpaque(false);
        p.setBounds(x, y, 70, 90);

        // Nền tròn của icon
        JLabel bgLabel = new JLabel();
        bgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bgLabel.setPreferredSize(new Dimension(70, 60));
        
        // Tải ảnh từ URL
        try {
            URL url = new URL(imageUrl);
            BufferedImage img = ImageIO.read(url);
            if (img != null) {
                // Scale ảnh nhỏ lại để vừa nút tròn
                Image scaledImg = img.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(scaledImg);
                bgLabel.setIcon(icon);
            }
        } catch (Exception e) {
            e.printStackTrace();
            bgLabel.setText("X"); // Ký hiệu lỗi
        }

        // Vẽ vòng tròn nền bao quanh icon (Custom Painter cho JLabel)
        JPanel iconPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COLOR_ICON_BG);
                g2.fillOval(10, 5, 50, 50); // Vẽ vòng tròn nền
                super.paintComponent(g);
            }
        };
        iconPanel.setOpaque(false);
        iconPanel.add(bgLabel, BorderLayout.CENTER);

        JLabel textLabel = new JLabel("<html><center>" + title.replace("\n", "<br>") + "</center></html>", SwingConstants.CENTER);
        textLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        textLabel.setForeground(COLOR_TEXT_DARK);

        p.add(iconPanel, BorderLayout.NORTH);
        p.add(textLabel, BorderLayout.CENTER);
        return p;
    }

    // --- Helper: Tải ảnh an toàn ---
    private Image loadRemoteImage(String urlString) {
        try {
            return ImageIO.read(new URL(urlString));
        } catch (Exception e) {
            System.err.println("Không thể tải ảnh: " + urlString);
            return null;
        }
    }

    // --- Component: Panel bo tròn ---
    class RoundedPanel extends JPanel {
        private int radius;
        private Color bgColor;

        public RoundedPanel(int radius, Color bgColor) {
            this.radius = radius;
            this.bgColor = bgColor;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            
            // Viền mờ
            g2.setColor(new Color(200, 200, 200, 100));
            g2.drawRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MetroAppUI().setVisible(true));
    }
}