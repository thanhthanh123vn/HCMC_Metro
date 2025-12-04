package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterUI extends JFrame {

    private static final Color COLOR_PRIMARY = new Color(0, 90, 170);
    private static final Color COLOR_BG_GRADIENT_1 = new Color(40, 120, 180);
    private static final Color COLOR_BG_GRADIENT_2 = new Color(240, 245, 250);

    public RegisterUI() {
        setTitle("Đăng Ký - HCMC Metro");
        setSize(400, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(400, 750));
        setContentPane(layeredPane);

        // --- 1. BACKGROUND ---
        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, COLOR_BG_GRADIENT_1, 0, 400, COLOR_BG_GRADIENT_2);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bgPanel.setBounds(0, 0, 400, 750);
        layeredPane.add(bgPanel, JLayeredPane.DEFAULT_LAYER);

        // Title Header
        JLabel titleLabel = new JLabel("TẠO TÀI KHOẢN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(50, 60, 300, 40);
        layeredPane.add(titleLabel, JLayeredPane.PALETTE_LAYER);

        // --- 2. FORM CARD ---
        JPanel cardPanel = new RoundedPanel(30, Color.WHITE);
        cardPanel.setLayout(null);
        // Form đăng ký dài hơn nên panel cao hơn
        cardPanel.setBounds(25, 120, 350, 500);

        int y = 30;
        int gap = 65;

        // Họ tên
        cardPanel.add(createLabel("Họ và tên:", 30, y));
        JTextField txtName = createTextField(30, y + 25);
        cardPanel.add(txtName);

        // Số điện thoại
        y += gap;
        cardPanel.add(createLabel("Số điện thoại:", 30, y));
        JTextField txtPhone = createTextField(30, y + 25);
        cardPanel.add(txtPhone);

        // Mật khẩu
        y += gap;
        cardPanel.add(createLabel("Mật khẩu:", 30, y));
        JPasswordField txtPass = createPasswordField(30, y + 25);
        cardPanel.add(txtPass);

        // Nhập lại mật khẩu
        y += gap;
        cardPanel.add(createLabel("Nhập lại mật khẩu:", 30, y));
        JPasswordField txtConfirm = createPasswordField(30, y + 25);
        cardPanel.add(txtConfirm);

        // Button Đăng ký
        y += 80;
        JButton btnRegister = new JButton("ĐĂNG KÝ");
        btnRegister.setBounds(30, y, 290, 45);
        btnRegister.setBackground(new Color(0, 153, 76)); // Màu xanh lá
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnRegister.setFocusPainted(false);
        btnRegister.setBorderPainted(false);
        
        btnRegister.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Đăng ký thành công! Vui lòng đăng nhập.");
            new LoginUI().setVisible(true);
            dispose();
        });
        cardPanel.add(btnRegister);

        // Link Back
        y += 55;
        JLabel lblBack = new JLabel("<html><u>Quay lại Đăng nhập</u></html>", SwingConstants.CENTER);
        lblBack.setForeground(COLOR_PRIMARY);
        lblBack.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblBack.setBounds(30, y, 290, 20);
        lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        lblBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LoginUI().setVisible(true);
                dispose();
            }
        });
        cardPanel.add(lblBack);

        layeredPane.add(cardPanel, JLayeredPane.PALETTE_LAYER);
    }

    // Helper methods để tạo component nhanh
    private JLabel createLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setForeground(new Color(80, 80, 80));
        lbl.setBounds(x, y, 200, 20);
        return lbl;
    }

    private JTextField createTextField(int x, int y) {
        JTextField txt = new JTextField();
        txt.setBounds(x, y, 290, 35);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return txt;
    }
    
    private JPasswordField createPasswordField(int x, int y) {
        JPasswordField txt = new JPasswordField();
        txt.setBounds(x, y, 290, 35);
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return txt;
    }

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
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterUI().setVisible(true));
    }
}