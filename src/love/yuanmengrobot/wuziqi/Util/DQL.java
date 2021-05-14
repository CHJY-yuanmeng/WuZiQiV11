package love.yuanmengrobot.wuziqi.Util;
/*
 *   @Studio:CHJY · 源梦科技
 *   @Author:lzl
 *   @Ide:IntelliJ IDEA
 *   @Creation time:2021/5/9 21:26
 */

import love.yuanmengrobot.wuziqi.UI.loginfunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DQL {
    public static ResultSet executeQuery(String sql){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        loginfunction lo=new loginfunction();
        try {
            conn=lo.getConnection();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
