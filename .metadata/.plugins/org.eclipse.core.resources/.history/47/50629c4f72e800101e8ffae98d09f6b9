package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class MetroAppUI extends JFrame {

	// Màu sắc chủ đạo
	private static final Color COLOR_PRIMARY = new Color(0, 90, 170);
	private static final Color COLOR_BG_GRADIENT_1 = new Color(40, 120, 180);
	private static final Color COLOR_BG_GRADIENT_2 = new Color(240, 245, 250);

	public MetroAppUI() {
		setTitle("HCMC Metro App");
		setSize(400, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(400, 750));
		setContentPane(layeredPane);

		// --- 1. LAYER BACKGROUND ---
		JPanel backgroundPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				// Vẽ màu nền Gradient
				GradientPaint gp = new GradientPaint(0, 0, COLOR_BG_GRADIENT_1, 0, 400, COLOR_BG_GRADIENT_2);
				g2.setPaint(gp);
				g2.fillRect(0, 0, getWidth(), getHeight());

				// Vẽ trang trí đơn giản
				g2.setColor(new Color(255, 255, 255, 50));
				g2.fillOval(-50, -50, 300, 300);
			}
		};
		backgroundPanel.setBounds(0, 0, 400, 750);
		layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);

		// --- 2. LAYER CARD MENU ---
		int cardY = 280;
		JPanel cardPanel = new RoundedPanel(30, Color.WHITE);
		cardPanel.setLayout(null);
		cardPanel.setBounds(20, cardY, 345, 350);

		// 1. Tạo nút Mua vé
		JPanel btnMuaVe = createMenuItem("Mua vé", "train-ticket.png", 20, 50);
		// Thêm sự kiện Click cho nút Mua vé
		btnMuaVe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Mở màn hình BuyTicketUI
				BuyTicketUI buyTicketScreen = new BuyTicketUI();
				buyTicketScreen.setVisible(true);

				// Đóng màn hình hiện tại (nếu muốn giữ màn hình cũ thì xóa dòng này đi)
				dispose();
			}
		});
		cardPanel.add(btnMuaVe);

		JPanel btnMyTickets = createMenuItem("Vé của tôi", "two-tickets.png", 100, 50);
		btnMyTickets.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Mở màn hình MyTicketsUI
				MyTicketsUI myTicketsScreen = new MyTicketsUI();
				myTicketsScreen.setVisible(true);
				dispose(); // Đóng màn hình chính
			}
		});
		cardPanel.add(btnMyTickets);
		JPanel btnRedeem = createMenuItem("Đổi mã\nlấy vé", "qr-code.png", 180, 50);

		btnRedeem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Mở màn hình Đổi mã
				new RedeemTicketUI().setVisible(true);

				// Đóng màn hình chính hiện tại
				dispose();
			}
		});

		cardPanel.add(btnRedeem);

		JPanel btnInfo = createMenuItem("Thông tin\nvé", "info.png", 260, 50);
		btnInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new TicketInfoUI().setVisible(true); // Mở màn hình thông tin
				dispose();
			}
		});
		cardPanel.add(btnInfo);

		// --- Hàng 2 ---
		cardPanel.add(createMenuItem("Hành trình", "map-marker.png", 20, 160));
		cardPanel.add(createMenuItem("Mua vé\nSuối Tiên", "theme-park.png", 100, 160));
		cardPanel.add(createMenuItem("Siêu thị\nonline", "shopping-cart.png", 180, 160));

		JPanel btnAccount = createMenuItem("Tài khoản", "user-male-circle.png", 260, 160);
		btnAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Mở màn hình AccountUI
				new AccountUI().setVisible(true);
				dispose();
			}
		});
		cardPanel.add(btnAccount);

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
				g2.fillOval(15, 0, 8, 8);
			}
		};
		dots.setOpaque(false);
		dots.setBounds(160, 310, 50, 20);
		cardPanel.add(dots);

		layeredPane.add(cardPanel, JLayeredPane.PALETTE_LAYER);

		// --- 3. LAYER LOGO ---
		JPanel logoPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.WHITE);
				g2.fillOval(0, 0, 70, 70);

				// Vẽ chữ Logo
				g2.setColor(COLOR_PRIMARY);
				g2.setFont(new Font("Arial", Font.BOLD, 18));
				g2.drawString("HCMC", 10, 42);
			}
		};
		logoPanel.setOpaque(false);
		logoPanel.setBounds(165, cardY - 35, 70, 70);
		layeredPane.add(logoPanel, JLayeredPane.MODAL_LAYER);

		// --- 4. LAYER BANNER ---
		JPanel bannerPanel = new RoundedPanel(20, new Color(220, 240, 255));
		bannerPanel.setLayout(new BorderLayout());
		JLabel bannerText = new JLabel(
				"<html><center><font color='#005aab'>HƯỚNG DẪN MUA VÉ<br>TUYẾN METRO SỐ 1</font></center></html>",
				SwingConstants.CENTER);
		bannerText.setFont(new Font("Arial", Font.BOLD, 13));
		bannerPanel.add(bannerText, BorderLayout.CENTER);
		bannerPanel.setBounds(20, 650, 345, 80);
		layeredPane.add(bannerPanel, JLayeredPane.DEFAULT_LAYER);
	}

	// Helper: Tạo Item Menu load ảnh từ thư mục img
	private JPanel createMenuItem(String title, String iconName, int x, int y) {
		JPanel p = new JPanel(new BorderLayout());
		p.setOpaque(false);
		p.setBounds(x, y, 70, 90);

		// Nút tròn chứa icon
		JLabel iconLabel = new JLabel();
		iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
		iconLabel.setPreferredSize(new Dimension(70, 60));

		// Tải ảnh từ thư mục src/img
		try {
			URL imgUrl = getClass().getResource("/img/" + iconName);
			if (imgUrl != null) {
				ImageIcon originalIcon = new ImageIcon(imgUrl);
				Image scaled = originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
				iconLabel.setIcon(new ImageIcon(scaled));
			} else {
				// System.err.println("Không tìm thấy ảnh: " + iconName); // Ẩn lỗi nếu không có
				// ảnh
				iconLabel.setText("?");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Vẽ vòng tròn nền nhạt dưới icon
		JPanel iconBg = new JPanel(new BorderLayout()) {
			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(230, 245, 255));
				g2.fillOval(10, 5, 50, 50);
				super.paintComponent(g);
			}
		};
		iconBg.setOpaque(false);
		iconBg.add(iconLabel, BorderLayout.CENTER);

		JLabel textLabel = new JLabel("<html><center>" + title.replace("\n", "<br>") + "</center></html>",
				SwingConstants.CENTER);
		textLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textLabel.setForeground(new Color(60, 60, 60));

		p.add(iconBg, BorderLayout.NORTH);
		p.add(textLabel, BorderLayout.CENTER);

		// Hiệu ứng hover chuột
		p.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				p.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});

		return p;
	}

	// Class vẽ Panel bo tròn
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
		SwingUtilities.invokeLater(() -> new MetroAppUI().setVisible(true));
	}
}