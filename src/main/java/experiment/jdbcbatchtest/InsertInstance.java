package experiment.jdbcbatchtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author : liulei
 **/
public class InsertInstance {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/craft_carrier?useSSL=true", "root", "123456");
            conn = DriverManager.getConnection("jdbc:mysql://10.1.139.149:3306/test?useSSL=true&rewriteBatchedStatements=true", "root", "123456");

            String sql = "insert into app_instance (app_key, ip, port, env, version, status, creator) values(?,?,?,?,?,?,?)";

            conn.setAutoCommit(false);
            pst = conn.prepareStatement(sql);

            for(int loop = 0; loop < 50; loop++) {
                int index = loop + 1;
                pst.setString(1, "key" + index);
                pst.setString(2, "10.1.139.1" + index);
                pst.setInt(3, index);
                pst.setInt(4, index);
                pst.setString(5, "version" + index);
                pst.setShort(6, (short) index);
                pst.setString(7, "ray");
                pst.addBatch();
            }

            pst.executeBatch();
            conn.commit();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(pst != null) {
                try {
                    pst.close();
                    System.out.println("pst close success");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(conn != null) {
                try {
                    conn.close();
                    System.out.println("con close success");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
