package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import beans.User;

// DAOクラス：Userテーブルに対するデータアクセスを担当
public class UserDao {

    // DB接続を取得するメソッド
    private Connection getConnection() throws SQLException {
        // 実際の環境に合わせて接続情報を設定してください
        String url = "jdbc:mysql://localhost:3306/sampledb";
        String user = "root";
        String password = "password";
        return DriverManager.getConnection(url, user, password);
    }

    // 全件取得（表示処理）
    public List<User> findAll() throws UserNotFoundException, SQLException {
        List<User> list = new ArrayList<>();
        String sql = "SELECT id, fName, gName, fName_kana, gName_kana, birth_year, birth_month, birth_day FROM users";

        // SQLを実行して全件取得
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // 取得結果をUserオブジェクトに詰めてリストに追加
            while (rs.next()) {
                User u = new User(
                    rs.getInt("id"),
                    rs.getString("fName"),
                    rs.getString("gName"),
                    rs.getString("fName_kana"),
                    rs.getString("gName_kana"),
                    rs.getInt("birth_year"),
                    rs.getInt("birth_month"),
                    rs.getInt("birth_day")
                );
                list.add(u);
            }
        }

        // データが存在しない場合は例外を投げる
        if (list.isEmpty()) {
            throw new UserNotFoundException("登録されているユーザー情報が存在しません。");
        }
        return list;
    }

    // id指定検索（更新・削除時に利用）
    public User findById(int id) throws UserNotFoundException, SQLException {
        String sql = "SELECT id, fName, gName, fName_kana, gName_kana, birth_year, birth_month, birth_day FROM users WHERE id=?";
        User user = null;

        // SQLを実行してidに該当するユーザーを取得
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                        rs.getInt("id"),
                        rs.getString("fName"),
                        rs.getString("gName"),
                        rs.getString("fName_kana"),
                        rs.getString("gName_kana"),
                        rs.getInt("birth_year"),
                        rs.getInt("birth_month"),
                        rs.getInt("birth_day")
                    );
                }
            }
        }

        // 該当データが存在しない場合は例外を投げる
        if (user == null) {
            throw new UserNotFoundException("指定されたIDのユーザーは存在しません。");
        }
        return user;
    }

    // 追加処理
    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO users(fName, gName, fName_kana, gName_kana, birth_year, birth_month, birth_day) VALUES(?, ?, ?, ?, ?, ?, ?)";

        // SQLを実行して新規ユーザーを追加
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getfName());
            ps.setString(2, user.getgName());
            ps.setString(3, user.getfName_kana());
            ps.setString(4, user.getgName_kana());
            ps.setInt(5, user.getBirth_year());
            ps.setInt(6, user.getBirth_month());
            ps.setInt(7, user.getBirth_day());
            ps.executeUpdate();
        }
    }

    // 更新処理
    public void update(User user) throws UserNotFoundException, SQLException {
        String sql = "UPDATE users SET fName=?, gName=?, fName_kana=?, gName_kana=?, birth_year=?, birth_month=?, birth_day=? WHERE id=?";

        // SQLを実行して指定idのユーザー情報を更新
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getfName());
            ps.setString(2, user.getgName());
            ps.setString(3, user.getfName_kana());
            ps.setString(4, user.getgName_kana());
            ps.setInt(5, user.getBirth_year());
            ps.setInt(6, user.getBirth_month());
            ps.setInt(7, user.getBirth_day());
            ps.setInt(8, user.getId());

            int updated = ps.executeUpdate();

            // 更新件数が0ならid未存在 → 例外を投げる
            if (updated == 0) {
                throw new UserNotFoundException("指定されたIDのユーザーは存在しません。");
            }
        }
    }

    // 削除処理
    public void delete(int id) throws UserNotFoundException, SQLException {
        String sql = "DELETE FROM users WHERE id=?";

        // SQLを実行して指定idのユーザーを削除
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            int deleted = ps.executeUpdate();

            // 削除件数が0ならid未存在 → 例外を投げる
            if (deleted == 0) {
                throw new UserNotFoundException("指定されたIDのユーザーは存在しません。");
            }
        }
    }
}