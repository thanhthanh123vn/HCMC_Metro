package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class LoginUI extends JFrame {

    // Màu sắc chủ đạo (đồng bộ với MetroAppUI)
    private static final Color COLOR_PRIMARY = new Color(0, 90, 170);
    private static final Color COLOR_BG_GRADIENT_1 = new Color(40, 120, 180);
    private static final Color COLOR_BG_GRADIENT_2 = new Color(240, 245, 250);

    public LoginUI() {
        setTitle("Đăng Nhập - HCMC Metro");
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

        // --- 2. LOGO SECTION ---
        JLabel logoLabel = new JLabel();
        logoLabel.setBounds(150, 60, 100, 100);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // Tải icon user làm logo tạm
        try {
            URL url = getClass().getResource("/img/user-male-circle.png");
            if (url != null) {
                ImageIcon icon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
                logoLabel.setIcon(icon);
            }
        } catch (Exception e) {}
        layeredPane.add(logoLabel, JLayeredPane.PALETTE_LAYER);

        JLabel titleLabel = new JLabel("HCMC METRO", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(50, 150, 300, 40);
        layeredPane.add(titleLabel, JLayeredPane.PALETTE_LAYER);

        // --- 3. FORM CARD ---
        JPanel cardPanel = new RoundedPanel(30, Color.WHITE);
        cardPanel.setLayout(null);
        cardPanel.setBounds(30, 220, 340, 350);

        JLabel lblLogin = new JLabel("ĐĂNG NHẬP", SwingConstants.CENTER);
        lblLogin.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblLogin.setForeground(COLOR_PRIMARY);
        lblLogin.setBounds(20, 20, 300, 30);
        cardPanel.add(lblLogin);

        // Username
        JLabel lblUser = new JLabel("Tài khoản / SĐT:");
        lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUser.setBounds(30, 70, 200, 20);
        cardPanel.add(lblUser);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(30, 95, 280, 40);
        txtUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cardPanel.add(txtUser);

        // Password
        JLabel lblPass = new JLabel("Mật khẩu:");
        lblPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPass.setBounds(30, 150, 200, 20);
        cardPanel.add(lblPass);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(30, 175, 280, 40);
        txtPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cardPanel.add(txtPass);

        // Button Login
        JButton btnLogin = new JButton("ĐĂNG NHẬP");
        btnLogin.setBounds(30, 250, 280, 45);
        btnLogin.setBackground(COLOR_PRIMARY);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = txtUser.getText();
                String password = String.valueOf(txtPass.getPassword());

                if (username.equals("tiến") && password.equals("2345678")) {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
                    new MetroAppUI().setVisible(true);

                    JFrame current = (JFrame) SwingUtilities.getWindowAncestor(btnLogin);
                    current.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "Sai tài khoản hoặc mật khẩu!", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });


        cardPanel.add(btnLogin);

        // Link Register
        JLabel lblRegister = new JLabel("<html><u>Chưa có tài khoản? Đăng ký ngay</u></html>", SwingConstants.CENTER);
        lblRegister.setForeground(new Color(100, 100, 100));
        lblRegister.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblRegister.setBounds(30, 310, 280, 20);
        lblRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Sự kiện chuyển sang màn hình Đăng ký
        lblRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	
                new RegisterUI().setVisible(true);
                dispose();
            }
        });
        cardPanel.add(lblRegister);

        layeredPane.add(cardPanel, JLayeredPane.PALETTE_LAYER);
    }

    // Component bo tròn góc (Giống MetroAppUI)
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
        SwingUtilities.invokeLater(() -> new LoginUI().setVisible(true));
    }
}