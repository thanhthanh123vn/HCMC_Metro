package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TicketInfoUI extends JFrame {

    private static final Color BG_COLOR = new Color(242, 248, 255);
    private static final Color PRIMARY_COLOR = new Color(20, 25, 80);

    public TicketInfoUI() {
        setTitle("Thông tin vé Metro");
        setSize(400, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(BG_COLOR);
        setLayout(new BorderLayout());

        // 1. HEADER
        add(createHeader(), BorderLayout.NORTH);

        // 2. NỘI DUNG (Sử dụng ScrollablePanel để fix lỗi tràn)
        // --- THAY ĐỔI Ở ĐÂY: Dùng ScrollablePanel thay vì JPanel thường ---
        ScrollablePanel contentPanel = new ScrollablePanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(BG_COLOR);
        contentPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        // -- Phần 1: Bảng giá vé --
        contentPanel.add(createSectionTitle("🎫 Các loại vé & Bảng giá"));
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(createPriceCard("Vé lượt (theo chặng)", "12k - 20k", "Dùng cho 1 lần đi duy nhất."));
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(createPriceCard("Vé 1 ngày", "40.000đ", "Không giới hạn lượt đi trong ngày."));
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(createPriceCard("Vé 3 ngày", "90.000đ", "Không giới hạn lượt đi trong 3 ngày."));
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(createPriceCard("Vé tháng", "300.000đ", "Ưu tiên cho hành khách thường xuyên."));

        contentPanel.add(Box.createVerticalStrut(25));

        // -- Phần 2: Hướng dẫn --
        contentPanel.add(createSectionTitle("ℹ️ Hướng dẫn sử dụng"));
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(createInstructionPanel());

        // Scroll Pane
        JScrollPane scroll = new JScrollPane(contentPanel);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0)); // Ẩn thanh cuộn dọc
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // Tắt thanh cuộn ngang
        
        add(scroll, BorderLayout.CENTER);
    }

    // --- Header ---
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

        JLabel lblTitle = new JLabel("Thông tin vé", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(PRIMARY_COLOR);

        JLabel dummy = new JLabel("       ");

        header.add(lblBack, BorderLayout.WEST);
        header.add(lblTitle, BorderLayout.CENTER);
        header.add(dummy, BorderLayout.EAST);
        return header;
    }

    // --- Title Section ---
    private JLabel createSectionTitle(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbl.setForeground(PRIMARY_COLOR);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    // --- Card Bảng giá ---
    private JPanel createPriceCard(String title, String price, String desc) {
        RoundedPanel p = new RoundedPanel(15, Color.WHITE);
        p.setLayout(new BorderLayout());
        p.setBorder(new EmptyBorder(10, 15, 10, 15));
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90)); 

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS)); 
        left.setOpaque(false);
        
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitle.setForeground(new Color(40, 40, 40));
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // JTextArea giúp text tự xuống dòng
        JTextArea txtDesc = new JTextArea(desc);
        txtDesc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtDesc.setForeground(Color.GRAY);
        txtDesc.setLineWrap(true);
        txtDesc.setWrapStyleWord(true);
        txtDesc.setOpaque(false);
        txtDesc.setEditable(false);
        txtDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        left.add(lblTitle);
        left.add(Box.createVerticalStrut(3));
        left.add(txtDesc);

        JLabel lblPrice = new JLabel(price);
        lblPrice.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPrice.setForeground(new Color(0, 90, 170));
        lblPrice.setVerticalAlignment(SwingConstants.TOP); 

        p.add(left, BorderLayout.CENTER);
        p.add(lblPrice, BorderLayout.EAST);

        return p;
    }

    // --- Panel Hướng dẫn ---
    private JPanel createInstructionPanel() {
        RoundedPanel p = new RoundedPanel(15, Color.WHITE);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        // Quan trọng: Không set maximum height cố định để nó tự giãn theo nội dung
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 500));

        p.add(createStepRow("1", "Mua vé tại quầy hoặc qua ứng dụng Metro để nhận mã QR."));
        p.add(Box.createVerticalStrut(15));
        p.add(createStepRow("2", "Sử dụng QR Code để quét qua cổng soát vé khi vào ga."));
        p.add(Box.createVerticalStrut(15));
        p.add(createStepRow("3", "Giữ vé hoặc điện thoại trong suốt quá trình di chuyển."));
        p.add(Box.createVerticalStrut(15));
        p.add(createStepRow("4", "Quét vé một lần nữa tại cổng ra để hoàn tất hành trình."));

        return p;
    }

    private JPanel createStepRow(String step, String text) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.setOpaque(false);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblStep = new JLabel(step, SwingConstants.CENTER);
        lblStep.setPreferredSize(new Dimension(24, 24));
        lblStep.setOpaque(true);
        lblStep.setBackground(new Color(230, 240, 255));
        lblStep.setForeground(PRIMARY_COLOR);
        lblStep.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JTextArea txtText = new JTextArea(text);
        txtText.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtText.setForeground(new Color(60, 60, 60));
        txtText.setLineWrap(true);       // Tự động xuống dòng
        txtText.setWrapStyleWord(true);  // Ngắt từ nguyên vẹn
        txtText.setOpaque(false);
        txtText.setEditable(false);

        // Container cho số thứ tự để nó nằm trên cùng
        JPanel stepContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        stepContainer.setOpaque(false);
        stepContainer.add(lblStep);

        row.add(stepContainer, BorderLayout.WEST);
        row.add(txtText, BorderLayout.CENTER);
        return row;
    }

    // --- CLASS QUAN TRỌNG ĐỂ FIX LỖI TRÀN ---
    // Class này ép chiều rộng của Panel luôn bằng chiều rộng của Viewport (khung nhìn)
    static class ScrollablePanel extends JPanel implements Scrollable {
        @Override
        public Dimension getPreferredScrollableViewportSize() {
            return getPreferredSize();
        }

        @Override
        public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
            return 16;
        }

        @Override
        public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
            return 16;
        }

        @Override
        public boolean getScrollableTracksViewportWidth() {
            return true; // <--- TRẢ VỀ TRUE LÀ CHÌA KHÓA ĐỂ FIX LỖI
        }

        @Override
        public boolean getScrollableTracksViewportHeight() {
            return false;
        }
    }

    // Class bo tròn
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