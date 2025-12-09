package view;

import data.Customer;
import data.SessionManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccountUI extends JFrame {

    // Màu sắc chủ đạo
    private static final Color BG_COLOR = new Color(242, 248, 255);
    private static final Color PRIMARY_COLOR = new Color(20, 25, 80);
    private static final Color TEXT_GRAY = new Color(100, 100, 100);

    public AccountUI() {
        setTitle("Thông tin tài khoản");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(BG_COLOR);
        setLayout(new BorderLayout());

        // Lấy thông tin user hiện tại
        Customer user = SessionManager.getCurrentUser();

        // 1. HEADER
        add(createHeader(), BorderLayout.NORTH);

        // 2. CONTENT
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(BG_COLOR);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        if (user != null) {
            // Avatar & Tên
            contentPanel.add(createProfileHeader(user));
            contentPanel.add(Box.createVerticalStrut(30));

            // Thông tin chi tiết
            contentPanel.add(createSectionTitle("Thông tin cá nhân"));
            contentPanel.add(Box.createVerticalStrut(10));
            contentPanel.add(createInfoCard(user));
            
            contentPanel.add(Box.createVerticalStrut(40));
            
            // Nút Đăng xuất
            contentPanel.add(createLogoutButton());
        } else {
            JLabel lblErr = new JLabel("Bạn chưa đăng nhập!");
            lblErr.setAlignmentX(CENTER_ALIGNMENT);
            contentPanel.add(lblErr);
        }

        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblBack = new JLabel(" < Trở về");
        lblBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblBack.setForeground(PRIMARY_COLOR);
        lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new MetroAppUI().setVisible(true);
                dispose();
            }
        });

        JLabel lblTitle = new JLabel("Tài khoản", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(PRIMARY_COLOR);

        JLabel dummy = new JLabel("       "); 

        header.add(lblBack, BorderLayout.WEST);
        header.add(lblTitle, BorderLayout.CENTER);
        header.add(dummy, BorderLayout.EAST);
        return header;
    }

    private JPanel createProfileHeader(Customer user) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(BG_COLOR);
        p.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Avatar giả lập (Hình tròn)
        JLabel lblAvatar = new JLabel("<html><div style='background-color:#DDEEFF; border-radius:50%; width:80px; height:80px; text-align:center; vertical-align:middle; line-height:80px; font-size:30px; color:#0055AA;'>" 
                + user.getName().charAt(0) + "</div></html>");
        lblAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblName = new JLabel(user.getName());
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblName.setForeground(PRIMARY_COLOR);
        lblName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblId = new JLabel("ID: " + user.getId());
        lblId.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblId.setForeground(TEXT_GRAY);
        lblId.setAlignmentX(Component.CENTER_ALIGNMENT);

        p.add(lblAvatar);
        p.add(Box.createVerticalStrut(10));
        p.add(lblName);
        p.add(Box.createVerticalStrut(5));
        p.add(lblId);

        return p;
    }

    private JLabel createSectionTitle(String title) {
        JLabel lbl = new JLabel(title);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbl.setForeground(TEXT_GRAY);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JPanel createInfoCard(Customer user) {
        RoundedPanel p = new RoundedPanel(20, Color.WHITE);
        p.setLayout(new GridLayout(3, 1, 10, 10)); // 3 dòng
        p.setBorder(new EmptyBorder(15, 15, 15, 15));
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        p.add(createRow("Tên đăng nhập", user.getName()));
        p.add(createRow("Mật khẩu", "********")); // Che mật khẩu
        
        // Kiểm tra null cho số điện thoại
        String phone = (user.getPhone() != null) ? user.getPhone() : "Chưa cập nhật";
        p.add(createRow("Số điện thoại", phone));

        return p;
    }

    private JPanel createRow(String key, String value) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);
        
        JLabel lblKey = new JLabel(key);
        lblKey.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblKey.setForeground(TEXT_GRAY);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblValue.setForeground(PRIMARY_COLOR);

        row.add(lblKey, BorderLayout.WEST);
        row.add(lblValue, BorderLayout.EAST);
        return row;
    }

    private JPanel createLogoutButton() {
        RoundedPanel btn = new RoundedPanel(20, new Color(255, 235, 235)); // Màu đỏ nhạt
        btn.setLayout(new BorderLayout());
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lbl = new JLabel("Đăng xuất", SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbl.setForeground(new Color(200, 0, 0)); // Chữ đỏ

        btn.add(lbl, BorderLayout.CENTER);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int confirm = JOptionPane.showConfirmDialog(AccountUI.this, 
                        "Bạn có chắc muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    SessionManager.logout(); // Xóa session (Cần thêm hàm này vào SessionManager nếu chưa có)
                    new LoginUI().setVisible(true); // Về màn hình Login
                    dispose();
                }
            }
        });

        return btn;
    }

    // Class bo tròn (tái sử dụng)
    static class RoundedPanel extends JPanel {
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
}