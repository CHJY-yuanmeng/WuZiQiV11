package love.yuanmengrobot.wuziqi.Util;
/*
 *   @Studio:CHJY · 源梦科技
 *   @Author:COOLIGHT
 *   @Ide:IntelliJ IDEA
 *   @Creation time:2021/5/9 0009 13:52
 */

import java.sql.*;

public class Trade {
    /*
    * 购买道具*/
    public int purchasedItems(int items,String userId,String propId) {
        /*desc wuziqiuserprops
         名称                                      是否为空? 类型
         ----------------------------------------- -------- ----------------------------
         USERID                                             VARCHAR2(50)
         PROPID                                             VARCHAR2(50)
         PROPNUM                                            NUMBER(20)*/
        Connection conn = null;
        String url = "jdbc:oracle:thin:@112.74.190.130:1521:orcl";
        PreparedStatement pstmt;
        String sql = "select * from wuziqiuserprops where userid = ? and propid = ?";//查询用户道具
        String sql1 = "insert into wuziqiuserprops values (?,?,?)";//插入道具记录
        String sql2 = "update wuziqiuserprops set propnum = propnum + ? where userid = ? and propid = ?";//更新道具记录
        String sql3 = "update wuziqiuser set userscore = userscore - ? where userid = ?";//更新积分记录
        String sql4 = "select userscore from wuziqiuser where userid = ?";//查询用户积分
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");//
            conn = DriverManager.getConnection(url, "scott", "scott1316");
            pstmt = conn.prepareStatement(sql4);
            pstmt.setString(1,userId);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            if(rs.getInt("userscore") > items){
                pstmt = conn.prepareStatement(sql3);
                pstmt.setInt(1,items);
                pstmt.setString(2,userId);
                pstmt.executeUpdate();

                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,userId);
                pstmt.setString(2,propId);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    pstmt = conn.prepareStatement(sql2);
                    pstmt.setInt(1,items);//参数设置
                    pstmt.setString(2,userId);//参数设置
                    pstmt.setString(3,propId);//参数设置
                    pstmt.executeUpdate();//数据存在,执行更新
                    System.out.println("购买成功update");
                } else {
                    pstmt = conn.prepareStatement(sql1);
                    pstmt.setString(1,userId);//参数设置
                    pstmt.setString(2,propId);//参数设置
                    pstmt.setInt(3,items);//参数设置
                    pstmt.executeUpdate();//数据不存在，执行插入
                    System.out.println("购买成功insert");
                }
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /*
    * 积分充值*/
    public void pointsRecharge(int score, String userId) {
        /*desc wuziqiuser
         名称                                      是否为空? 类型
         ----------------------------------------- -------- ----------------------------
         USERID                                    NOT NULL VARCHAR2(50)
         USERNAME                                           VARCHAR2(50)
         USERPASSWORD                                       VARCHAR2(200)
         USERSCORE                                          NUMBER(20)
        desc wuziqiscore
         名称                                      是否为空? 类型
         ----------------------------------------- -------- ----------------------------
         USERID                                             VARCHAR2(50)
         SCORE                                              NUMBER(20)
         SCORESOURCE                                        NUMBER(20)
         SCOREDATE                                          DATE
         */
        Connection conn = null;
        String url = "jdbc:oracle:thin:@112.74.190.130:1521:orcl";
        PreparedStatement pstmt;
        String sql1 = "update wuziqiuser set userscore = userscore + ? where userid = ?";
        //String sql2 = "insert into wuziqiuser values (?,?,?,null)";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");//
            conn = DriverManager.getConnection(url, "scott", "scott1316");
            pstmt = conn.prepareStatement(sql1);
            pstmt.setInt(1,score);//参数设置
            pstmt.setString(2,userId);//参数设置
            pstmt.executeUpdate();//执行更新
            System.out.println("充值成功");
//            pstmt = conn.prepareStatement(sql2);
//            pstmt.setString(1,userId);
//            pstmt.setInt(2,score);
//            pstmt.setInt(3,score);
//            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
