package view;

import data.Order;
import data.OrderManager;
import data.Ticket;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

public class MyTicketsUI extends JFrame {

    private static final Color BG_COLOR = new Color(242, 248, 255);
    private static final Color PRIMARY_COLOR = new Color(0, 90, 170);
    
    public MyTicketsUI() {
        setTitle("Vé của tôi");
        setSize(400, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(BG_COLOR);
        setLayout(new BorderLayout());

        // 1. HEADER
        add(createHeader(), BorderLayout.NORTH);

        // 2. DANH SÁCH VÉ (SCROLL)
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(BG_COLOR);
        listPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        // Lấy dữ liệu từ OrderManager
        List<Order> history = OrderManager.getInstance().getOrderHistory();

        if (history.isEmpty()) {
            JLabel lblEmpty = new JLabel("Bạn chưa mua vé nào.", SwingConstants.CENTER);
            lblEmpty.setFont(new Font("Segoe UI", Font.ITALIC, 14));
            lblEmpty.setForeground(Color.GRAY);
            lblEmpty.setAlignmentX(Component.CENTER_ALIGNMENT);
            listPanel.add(Box.createVerticalStrut(50));
            listPanel.add(lblEmpty);
        } else {
            // Duyệt ngược để hiện vé mới nhất lên đầu
            for (int i = history.size() - 1; i >= 0; i--) {
                Order order = history.get(i);
                for (Ticket t : order.getTickets()) {
                    listPanel.add(createTicketItem(t, order));
                    listPanel.add(Box.createVerticalStrut(15)); // Khoảng cách giữa các vé
                }
            }
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0)); // Ẩn thanh cuộn
        add(scrollPane, BorderLayout.CENTER);
    }

    // --- Header có nút Back ---
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(new EmptyBorder(15, 15, 15, 15));

    
        JLabel lblBack = new JLabel(loadIcon("/img/back.png", 24, 24));
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

        JLabel lblTitle = new JLabel("Vé của tôi", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(PRIMARY_COLOR);

        JLabel dummy = new JLabel("       "); // Để cân giữa tiêu đề

        header.add(lblBack, BorderLayout.WEST);
        header.add(lblTitle, BorderLayout.CENTER);
        header.add(dummy, BorderLayout.EAST);
        
        return header;
    }
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
    // --- Tạo Card hiển thị thông tin 1 vé ---
    private JPanel createTicketItem(Ticket t, Order o) {
        RoundedPanel p = new RoundedPanel(20, Color.WHITE);
        p.setLayout(new BorderLayout());
        p.setBorder(new EmptyBorder(10, 15, 10, 15));
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110)); // Chiều cao cố định

        // Phần trái: Icon QR Code (giả lập)
        JLabel lblIcon = new JLabel();
        lblIcon.setPreferredSize(new Dimension(60, 60));
        lblIcon.setOpaque(true);
        lblIcon.setBackground(new Color(230, 240, 255));
        lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
        lblIcon.setText("QR"); // Có thể thay bằng ảnh thật
        lblIcon.setFont(new Font("Arial", Font.BOLD, 16));
        lblIcon.setForeground(PRIMARY_COLOR);

        // Phần giữa: Thông tin vé
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(new EmptyBorder(0, 15, 0, 0));

        JLabel lblRoute = new JLabel(t.getSeatNumber()); // "Bến Thành -> Suối Tiên"
        lblRoute.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblRoute.setForeground(new Color(40, 40, 60));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        JLabel lblDate = new JLabel("Ngày mua: " + sdf.format(o.getOrderDate()));
        lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDate.setForeground(Color.GRAY);

        JLabel lblPrice = new JLabel(String.format("%,.0f đ", t.getPrice()));
        lblPrice.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPrice.setForeground(new Color(0, 150, 0)); // Màu xanh lá

        infoPanel.add(lblRoute);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(lblDate);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(lblPrice);

        // Phần phải: Trạng thái
        JLabel lblStatus = new JLabel("Đã thanh toán");
        lblStatus.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblStatus.setForeground(PRIMARY_COLOR);

        p.add(lblIcon, BorderLayout.WEST);
        p.add(infoPanel, BorderLayout.CENTER);
        p.add(lblStatus, BorderLayout.SOUTH);

        return p;
    }


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