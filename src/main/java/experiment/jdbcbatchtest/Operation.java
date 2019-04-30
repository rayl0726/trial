package experiment.jdbcbatchtest;

import java.sql.*;

/**
 * @author : liulei
 **/
public class Operation {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/craft_carrier?useSSL=true", "root", "123456");
            conn = DriverManager.getConnection("jdbc:mysql://10.1.139.149:3306/test?useSSL=true&rewriteBatchedStatements=true", "root", "123456");

            String sql = "insert into app (app_key, app_name, app_type, gitlab_id, gitlab_name, gitlab_url,creator) values(?,?,?,?,?,?,?)";

            conn.setAutoCommit(false);
            pst = conn.prepareStatement(sql);

            for(int loop = 0; loop < 100000; loop++) {
                int index = loop + 1;
                pst.setString(1, "key" + index);
                pst.setString(2, "name" + index);
                pst.setShort(3, (short)1);
                pst.setInt(4, index);
                pst.setString(5, "git" + index);
                pst.setString(6, "git");
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
