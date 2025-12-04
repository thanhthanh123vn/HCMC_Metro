package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class MetroAppUI extends JFrame {

    // Màu sắc chủ đạo lấy từ ảnh
    private static final Color COLOR_BG_BLUE = new Color(20, 80, 140); // Xanh đậm nền trời
    private static final Color COLOR_ICON_BG = new Color(230, 240, 255); // Xanh nhạt nền icon
    private static final Color COLOR_TEXT_DARK = new Color(60, 60, 60);
    private static final Color COLOR_PRIMARY = new Color(0, 90, 170); // Xanh logo

    public MetroAppUI() {
        setTitle("HCMC Metro App");
        setSize(400, 750); // Kích thước tỷ lệ điện thoại
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // Cố định kích thước để giữ layout đẹp

        // Sử dụng JLayeredPane để xếp chồng các lớp (Background -> Card trắng -> Logo -> Nút gọi)
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(400, 750));
        setContentPane(layeredPane);

        // 1. LAYER BACKGROUND (Hình thành phố & Tàu)
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Giả lập màu trời (Gradient)
                GradientPaint gp = new GradientPaint(0, 0, new Color(40, 120, 180), 0, 400, new Color(240, 245, 250));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // TODO: Thay thế đoạn này bằng: g.drawImage(imgBackground, 0, 0, ...);
                // Vẽ minh họa tòa nhà/tàu đơn giản
                g2.setColor(new Color(255, 200, 150)); // Màu cam nhạt giả lập tòa nhà
                g2.fillRect(50, 100, 300, 200); 
                g2.setColor(Color.WHITE);
                g2.drawString("Background Image Here", 130, 200);
               
            }
        };
        backgroundPanel.setBounds(0, 0, 400, 750);
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);

        // 2. LAYER CARD MENU (Phần màu trắng bo tròn)
        int cardY = 280; // Vị trí bắt đầu của thẻ trắng
        JPanel cardPanel = new RoundedPanel(30, Color.WHITE);
        cardPanel.setLayout(null); // Dùng null layout để chỉnh vị trí icon thủ công cho giống hệt
        cardPanel.setBounds(20, cardY, 345, 350); // Canh lề trái phải

        // --- Thêm các Icon Menu vào Card ---
        // Hàng 1
        cardPanel.add(createMenuItem("Mua vé", "ticket_icon", 20, 60));
        cardPanel.add(createMenuItem("Vé của tôi", "my_ticket", 100, 60));
        cardPanel.add(createMenuItem("Đổi mã\nlấy vé", "qr_icon", 180, 60));
        cardPanel.add(createMenuItem("Thông tin\nvé", "info_icon", 260, 60));

        // Hàng 2
        cardPanel.add(createMenuItem("Hành trình", "map_icon", 20, 170));
        cardPanel.add(createMenuItem("Mua vé\nSuối Tiên", "park_icon", 100, 170));
        cardPanel.add(createMenuItem("Siêu thị\nonline", "cart_icon", 180, 170));
        cardPanel.add(createMenuItem("Tài khoản", "user_icon", 260, 170));
        
        // Dấu chấm chuyển trang (Pagination dots)
        JPanel dots = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.ORANGE);
                g.fillOval(0, 0, 8, 8);
                g.setColor(Color.LIGHT_GRAY);
                g.fillOval(12, 0, 8, 8);
            }
        };
        dots.setOpaque(false);
        dots.setBounds(160, 320, 50, 10);
        cardPanel.add(dots);

        layeredPane.add(cardPanel, JLayeredPane.PALETTE_LAYER);

        // 3. LAYER LOGO (Logo tròn nằm đè lên mép trên của Card)
        JPanel logoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Vẽ hình tròn trắng
                g2.setColor(Color.WHITE);
                g2.fillOval(0, 0, 70, 70);
                
                // Vẽ Logo chữ M cách điệu (Giả lập)
                g2.setColor(COLOR_PRIMARY);
                g2.setStroke(new BasicStroke(4));
                g2.drawArc(15, 25, 40, 30, 0, 180); // Hình cung
                g2.drawString("HURC", 20, 60);
            }
        };
        logoPanel.setOpaque(false);
        logoPanel.setBounds(165, cardY - 35, 70, 70); // Đặt vị trí đè lên card
        layeredPane.add(logoPanel, JLayeredPane.MODAL_LAYER);

        // 4. LAYER BANNER QUẢNG CÁO (Phía dưới cùng)
        JPanel bannerPanel = new RoundedPanel(20, new Color(200, 230, 255));
        bannerPanel.setLayout(new BorderLayout());
        JLabel bannerText = new JLabel("<html><center><font color='#005aab'>HƯỚNG DẪN MUA VÉ<br>TUYẾN METRO SỐ 1</font></center></html>", SwingConstants.CENTER);
        bannerText.setFont(new Font("Arial", Font.BOLD, 14));
        bannerPanel.add(bannerText, BorderLayout.CENTER);
        bannerPanel.setBounds(20, 650, 345, 100);
        layeredPane.add(bannerPanel, JLayeredPane.DEFAULT_LAYER);

        // 5. LAYER FLOAT BUTTON (Nút gọi điện thoại)
        JButton callButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 70, 140));
                g2.fillOval(0, 0, getWidth(), getHeight());
                // Vẽ icon điện thoại đơn giản
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(3));
                g2.drawArc(15, 15, 20, 20, 0, -90);
            }
        };
        callButton.setBounds(330, 600, 50, 50);
        callButton.setBorderPainted(false);
        callButton.setContentAreaFilled(false);
        callButton.setFocusPainted(false);
        layeredPane.add(callButton, JLayeredPane.DRAG_LAYER);
    }

    // --- Helper Method: Tạo một mục Menu (Icon + Text) ---
    private JPanel createMenuItem(String title, String iconType, int x, int y) {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.setOpaque(false);
        p.setBounds(x, y, 70, 90);

        // Phần Icon (Vẽ hình tròn)
        JLabel iconLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Vẽ nền tròn xanh nhạt
                g2.setColor(COLOR_ICON_BG);
                g2.fillOval(10, 0, 50, 50);

                // Vẽ icon giả lập bên trong
                g2.setColor(COLOR_PRIMARY);
                if(iconType.equals("ticket_icon")) g2.fillRect(25, 15, 20, 20);
                else if(iconType.equals("user_icon")) g2.fillOval(25, 15, 20, 20);
                else g2.fillRect(25, 20, 20, 10);
                
                // Nếu có icon liên kết (màu đỏ)
                if(iconType.equals("park_icon") || iconType.equals("cart_icon")) {
                    g2.setColor(Color.RED);
                    g2.fillOval(45, 0, 15, 15);
                    g2.setColor(Color.WHITE);
                    g2.setStroke(new BasicStroke(2));
                    g2.drawLine(48, 7, 56, 7);
                }
            }
        };
        iconLabel.setPreferredSize(new Dimension(70, 55));
        
        // Phần Text
        JLabel textLabel = new JLabel("<html><center>" + title.replace("\n", "<br>") + "</center></html>", SwingConstants.CENTER);
        textLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        textLabel.setForeground(COLOR_TEXT_DARK);

        p.add(iconLabel, BorderLayout.NORTH);
        p.add(textLabel, BorderLayout.CENTER);
        return p;
    }

    // --- Custom Component: Panel bo tròn góc ---
    class RoundedPanel extends JPanel {
        private int radius;
        private Color bgColor;

        public RoundedPanel(int radius, Color bgColor) {
            this.radius = radius;
            this.bgColor = bgColor;
            setOpaque(false); // Để trong suốt phần góc bo
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.setColor(bgColor);
            // Vẽ hình chữ nhật bo tròn
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            
            // Vẽ bóng mờ nhẹ (Shadow)
            g2.setColor(new Color(0,0,0, 20));
            g2.drawRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        }
    }

    public static void main(String[] args) {
        // Chạy trên luồng giao diện
        SwingUtilities.invokeLater(() -> {
            new MetroAppUI().setVisible(true);
        });
    }
}