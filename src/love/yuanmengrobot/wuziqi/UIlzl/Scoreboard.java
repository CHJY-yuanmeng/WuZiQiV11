/*
 * Created by JFormDesigner on Sun May 09 20:53:37 CST 2021
 */

package love.yuanmengrobot.wuziqi.UIlzl;

import love.yuanmengrobot.wuziqi.UI.StrtForm;
import love.yuanmengrobot.wuziqi.UI.WuZiQiClient;
import love.yuanmengrobot.wuziqi.Util.DQL;

import java.awt.event.*;
//import love.yuanmengrobot.wuziqi.Util.DQL;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @author 1
 */
public class Scoreboard extends JFrame {
   private DQL dql;
  private StrtForm strtForm;

    public StrtForm getStrtForm() {
        return strtForm;
    }

    public void setStrtForm(StrtForm strtForm) {
        this.strtForm = strtForm;
    }

    public Scoreboard() {

        initComponents();
        init();
        dql=new DQL();
        setVisible(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

//    public static void main(String[] args) {
//        new Scoreboard();
//    }
    void init(){
        Vector head=new Vector();
        Vector data=new Vector();
        int i=0;
        head.add("排名");
        head.add("用户");
        head.add("积分");
        ResultSet rs=null;
        try{
            rs= dql.executeQuery("select username,sum(score) totalscore from wuziqiuser natural join wuziqiscore where scoresource=1 group by username order by totalscore desc");
            while(rs.next()){
                Vector row=new Vector();
                row.add(++i);
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table1Recharge.setModel(new DefaultTableModel(data,head));

        data=new Vector();
        i=0;
        try{
            rs= DQL.executeQuery("select username,sum(score) totalscore from wuziqiuser natural join wuziqiscore where scoresource=0 group by username order by totalscore desc");
            while(rs.next()){
                Vector row=new Vector();
                row.add(++i);
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        table2Win.setModel(new DefaultTableModel(data,head));

        DefaultTableCellRenderer dtcr=new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        table1Recharge.setDefaultRenderer(Object.class,dtcr);
        table2Win.setDefaultRenderer(Object.class,dtcr);
    }

    private void button2ActionPerformed(ActionEvent e) {
        // TODO add your code here
        init();
    }

    private void button1ActionPerformed(ActionEvent e) {
        // TODO add your code here
        setVisible(false);
        strtForm.setVisible(true);



    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1Rop = new JLabel();
        scrollPane1Recharge = new JScrollPane();
        table1Recharge = new JTable();
        scrollPane2Win = new JScrollPane();
        table2Win = new JTable();
        label2Reop = new JLabel();
        label3Winop = new JLabel();
        button1RLtoHome = new JButton();
        button2RLre = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1Rop ----
        label1Rop.setText("\u79ef \u5206 \u6392 \u884c \u699c");
        label1Rop.setFont(label1Rop.getFont().deriveFont(label1Rop.getFont().getStyle() | Font.BOLD, label1Rop.getFont().getSize() + 20f));
        label1Rop.setForeground(new Color(204, 51, 0));
        contentPane.add(label1Rop);
        label1Rop.setBounds(325, 5, 215, 45);

        //======== scrollPane1Recharge ========
        {
            scrollPane1Recharge.setViewportView(table1Recharge);
        }
        contentPane.add(scrollPane1Recharge);
        scrollPane1Recharge.setBounds(90, 95, 300, 315);

        //======== scrollPane2Win ========
        {
            scrollPane2Win.setViewportView(table2Win);
        }
        contentPane.add(scrollPane2Win);
        scrollPane2Win.setBounds(470, 95, 300, 315);

        //---- label2Reop ----
        label2Reop.setText("\u5145\u503c\u6392\u884c");
        label2Reop.setFont(label2Reop.getFont().deriveFont(label2Reop.getFont().getStyle() | Font.BOLD, label2Reop.getFont().getSize() + 8f));
        label2Reop.setForeground(Color.blue);
        contentPane.add(label2Reop);
        label2Reop.setBounds(new Rectangle(new Point(200, 65), label2Reop.getPreferredSize()));

        //---- label3Winop ----
        label3Winop.setText("\u8d62\u53d6\u6392\u884c");
        label3Winop.setFont(label3Winop.getFont().deriveFont(label3Winop.getFont().getStyle() | Font.BOLD, label3Winop.getFont().getSize() + 8f));
        label3Winop.setForeground(new Color(51, 0, 255));
        contentPane.add(label3Winop);
        label3Winop.setBounds(new Rectangle(new Point(580, 65), label3Winop.getPreferredSize()));

        //---- button1RLtoHome ----
        button1RLtoHome.setText("\u8fd4\u56de\u4e3b\u9875");
        button1RLtoHome.addActionListener(e -> button1ActionPerformed(e));
        contentPane.add(button1RLtoHome);
        button1RLtoHome.setBounds(new Rectangle(new Point(775, 10), button1RLtoHome.getPreferredSize()));

        //---- button2RLre ----
        button2RLre.setText("\u5237\u65b0\u4e00\u4e0b");
        button2RLre.addActionListener(e -> button2ActionPerformed(e));
        contentPane.add(button2RLre);
        button2RLre.setBounds(370, 425, 125, button2RLre.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(870, 495));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents





    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1Rop;
    private JScrollPane scrollPane1Recharge;
    private JTable table1Recharge;
    private JScrollPane scrollPane2Win;
    private JTable table2Win;
    private JLabel label2Reop;
    private JLabel label3Winop;
    private JButton button1RLtoHome;
    private JButton button2RLre;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
