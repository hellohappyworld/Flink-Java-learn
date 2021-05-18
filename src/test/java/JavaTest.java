import com.scallion.common.Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * created by gaowj.
 * created on 2021-05-14.
 * function:
 * origin ->
 */
public class JavaTest {
    public static void main(String[] args) throws Exception {
        Class.forName(Common.DRIVERNAME);
        Connection conn = DriverManager.getConnection(Common.JDBCURL, Common.USERNAME, Common.PASSWORD);
        PreparedStatement ps = conn.prepareStatement("select id,name,age,sex from tongji.rt_binlog_to_kudu where name='zhanghao'");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt("id") +
                    rs.getString("name") +
                    rs.getInt("age") +
                    rs.getString("sex"));
        }
        conn.close();
    }
}
