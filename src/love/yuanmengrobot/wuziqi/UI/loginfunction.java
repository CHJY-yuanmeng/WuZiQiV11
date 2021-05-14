package love.yuanmengrobot.wuziqi.UI;
/*
 *   @Studio:CHJY · 源梦科技
 *   @Author:Lishao
 *   @Ide:IntelliJ IDEA
 *   @Creation time:2021/5/8 20:33
 */

import javax.swing.*;
import java.sql.*;

public class loginfunction {
    //获取数据库连接方法
    public static Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:oracle:thin:@112.74.190.130:1521:orcl";
            String user = "scott";
            String password = "scott1316";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("加载数据库驱动失败");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("连接数据库失败");
        }
        return conn;
    }

    //关闭数据库连接方法
    public static void close(Connection conn, Statement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Statement GetStatement(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return stmt;
        }
    }

    public static ResultSet getresult(Statement stmt, String select) {
        ResultSet rs = null;
        String sql = select;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
        }

        public static boolean adduser(Statement stmt,String add){
        int rs;
        String sql=add;
        boolean flag=false;
            try {
                rs=stmt.executeUpdate(sql);
                if(rs==1)
                    flag=true;
            } catch (SQLException e) {
                if(e.getErrorCode()==1)
                    JOptionPane.showMessageDialog(null, "账号或昵称已存在！", null, JOptionPane.WARNING_MESSAGE);
                e.printStackTrace();
            }
            finally {
                return flag;
            }

        }


    public static Boolean selectuser(ResultSet rss) {
        ResultSet rs = rss;
        boolean flag = false;
        try {
            if (rs.next()){
                int i=rs.getInt("count(*)");
                if(i==1)
                    flag=true;
                else
                    flag=false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return flag;
        }
    }

    public static String selectSql(String a, String b) {
        String sql = "select count(*) from wuziqiuser where username='"+a+"'and userpassword='" +b+"'";
        return sql;
    }

    public static String insertsql(String a,String b,String c){
        String sql="insert into wuziqiuser values('"+a+"','"+b+"','"+c+"','100')";
        return sql;
    }

    public static String getnamesql(String a,String b){
        String sql="select userName from wuziqiuser where userid='"+a+"'and userpassword='" +b+"'";
        return sql;
    }
    public static String getname(ResultSet rss){
        String name=null;
        ResultSet rs=rss;
        try {
            if(rs.next()){
                name=rs.getString("userName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
}




