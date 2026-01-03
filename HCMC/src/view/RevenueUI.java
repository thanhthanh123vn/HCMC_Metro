package view;

import data.Order;
import data.OrderManager;
import data.Ticket;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RevenueUI extends JFrame {

    private static final Color BG_COLOR = new Color(242, 248, 255);
    private static final Color PRIMARY_COLOR = new Color(0, 90, 170);
    
    private JTable table;
    private JLabel lblTotalRevenue;
    private JRadioButton rbAsc, rbDesc; // Nút chọn sắp xếp
    
    // Lưu trữ dữ liệu thống kê để không phải tính lại khi bấm nút
    private TreeMap<String, double[]> statsMap; 

    public RevenueUI() {
        setTitle("Thống kê doanh thu");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(BG_COLOR);
        setLayout(new BorderLayout());

        // 1. Header
        add(createHeader(), BorderLayout.NORTH);

        // 2. Control Panel (Chọn sắp xếp)
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel.setBackground(BG_COLOR);
        controlPanel.setBorder(new EmptyBorder(10, 15, 0, 15));
        
        JLabel lblSort = new JLabel("Sắp xếp theo ngày: ");
        lblSort.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        rbAsc = new JRadioButton("Tăng dần (Cũ -> Mới)");
        rbDesc = new JRadioButton("Giảm dần (Mới -> Cũ)");
        rbAsc.setBackground(BG_COLOR);
        rbDesc.setBackground(BG_COLOR);
        rbAsc.setSelected(true); // Mặc định tăng dần

        // Gom nhóm nút để chỉ chọn được 1 trong 2
        ButtonGroup group = new ButtonGroup();
        group.add(rbAsc);
        group.add(rbDesc);
        
        // Sự kiện khi bấm nút
        rbAsc.addActionListener(e -> updateTableData());
        rbDesc.addActionListener(e -> updateTableData());

        controlPanel.add(lblSort);
        controlPanel.add(rbAsc);
        controlPanel.add(rbDesc);
        
        add(controlPanel, BorderLayout.NORTH); // Thêm vào vùng North (dưới Header nếu Header nằm trong panel riêng)
        
        // Sắp xếp lại layout một chút: Header -> Control -> Table
        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.add(createHeader(), BorderLayout.NORTH);
        topContainer.add(controlPanel, BorderLayout.SOUTH);
        add(topContainer, BorderLayout.NORTH);


        // 3. Bảng thống kê
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(BG_COLOR);
        contentPanel.setBorder(new EmptyBorder(10, 15, 20, 15));

        String[] columnNames = {"Ngày", "Số vé bán", "Doanh thu (VND)"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        styleTable(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // 4. Footer (Tổng tiền)
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        lblTotalRevenue = new JLabel("Tổng cộng: 0 VND");
        lblTotalRevenue.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTotalRevenue.setForeground(new Color(200, 0, 0));
        
        footerPanel.add(lblTotalRevenue);
        add(footerPanel, BorderLayout.SOUTH);
        add(contentPanel, BorderLayout.CENTER);

       
        calculateData();
        updateTableData(); 
    }

  
    private void calculateData() {
        statsMap = new TreeMap<>(); 
        List<Order> history = OrderManager.getInstance().getOrderHistory();
        SimpleDateFormat sdfKey = new SimpleDateFormat("yyyy-MM-dd");
        statsMap.put("2025-01-01", new double[]{5, 750000});
        statsMap.put("2025-01-03", new double[]{12, 1800000});
        statsMap.put("2025-01-02", new double[]{8, 1200000});
        statsMap.put("2024-12-31", new double[]{3, 450000});
        statsMap.put("2025-01-05", new double[]{20, 3000000});
        for (Order order : history) {
            String dateKey = sdfKey.format(order.getOrderDate());
            
   
            double[] current = statsMap.getOrDefault(dateKey, new double[]{0, 0});
            
            int count = order.getTickets().size();
            double money = 0;
            for(Ticket t : order.getTickets()) money += t.getPrice();

            current[0] += count;
            current[1] += money;
            
            statsMap.put(dateKey, current);
        }
    }


    private void updateTableData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); 
        
        double totalRevenueAll = 0;
        SimpleDateFormat sdfKey = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfDisplay = new SimpleDateFormat("dd/MM/yyyy");

        var entrySet = rbAsc.isSelected() ? statsMap.entrySet() : statsMap.descendingMap().entrySet();

        for (Map.Entry<String, double[]> entry : entrySet) {
            String rawDate = entry.getKey();
            double[] val = entry.getValue();
            
            // Format ngày hiển thị
            String displayDate = rawDate;
            try { displayDate = sdfDisplay.format(sdfKey.parse(rawDate)); } catch(Exception e){}

            model.addRow(new Object[]{
                displayDate,
                (int)val[0], // Số vé
                String.format("%,.0f", val[1]) // Tiền
            });
            
            totalRevenueAll += val[1];
        }
        
        lblTotalRevenue.setText("Tổng cộng: " + String.format("%,.0f VND", totalRevenueAll));
    }

    // --- GIAO DIỆN PHỤ TRỢ ---
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel lblBack = new JLabel(loadIcon("/img/back.png", 24, 24));
        lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new MetroAppUI().setVisible(true); // Quay về trang chủ
                dispose();
            }
        });

        JLabel lblTitle = new JLabel("Báo cáo doanh thu", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(PRIMARY_COLOR);

        header.add(lblBack, BorderLayout.WEST);
        header.add(lblTitle, BorderLayout.CENTER);
        header.add(new JLabel("       "), BorderLayout.EAST);
        return header;
    }

    private void styleTable(JTable table) {
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(230, 240, 255));
        table.getTableHeader().setForeground(PRIMARY_COLOR);
        table.setShowGrid(false);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) 
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }

    private ImageIcon loadIcon(String path, int w, int h) {
        try {
            URL url = getClass().getResource(path);
            if (url!=null) return new ImageIcon(ImageIO.read(url).getScaledInstance(w, h, Image.SCALE_SMOOTH));
        } catch(Exception e){}
        return null;
    }
}