package data;

public class SessionManager {
    private static Customer currentUser; // Người dùng hiện tại

    // Gọi hàm này khi đăng nhập thành công
    public static void setCurrentUser(Customer user) {
        currentUser = user;
    }

    // Gọi hàm này khi cần lấy thông tin người dùng để in hóa đơn
    public static Customer getCurrentUser() {
        return currentUser;
    }
    
    // Gọi hàm này khi đăng xuất
    public static void logout() {
        currentUser = null;
    }
}