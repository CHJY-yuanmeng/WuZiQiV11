package love.yuanmengrobot.wuziqi.UIldq;

import love.yuanmengrobot.wuziqi.UI.MD5;
import love.yuanmengrobot.wuziqi.UI.StrtForm;
import love.yuanmengrobot.wuziqi.UI.loginfunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/*
 * Created by JFormDesigner on Sat Apr 24 19:48:19 CST 2021
 */


/**
 * @author unknown
 */
public  class loginForm extends JFrame {
    public static  String name=null;
    public static logon lgg=new logon();
    public static StrtForm stat;
    public static loginForm lg=new loginForm();

    public static void main(String[] args) {
        lgg.setVisible(false);
        lg.setVisible(true);


    }
    public loginForm() {
        initComponents();
    }

    private void button1MouseClicked(MouseEvent e) {
        Connection conn = loginfunction.getConnection();
        Statement stmt = loginfunction.GetStatement(conn);
        String a = textField1.getText();
        String b = textField2.getText();
        String password = null;
        try {
            password = MD5.encoderByMd5(b);
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            noSuchAlgorithmException.printStackTrace();
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            unsupportedEncodingException.printStackTrace();
        }
        String c = loginfunction.selectSql(a, password);
        ResultSet rs = loginfunction.getresult(stmt, c);
        boolean flag = loginfunction.selectuser(rs);
        String getnamesql=loginfunction.getnamesql(a,password);
        ResultSet rs1=loginfunction.getresult(stmt,getnamesql);
        name=loginfunction.getname(rs1);
        loginfunction.close(conn, stmt, rs);

        if (flag == true) {
            //JOptionPane.showMessageDialog(null, "用户名或者密码正确", null, JOptionPane.WARNING_MESSAGE);
//            stat=new StrtForm();
//            stat.setVisible(true);
            lg.setVisible(false);
        }
        if (flag == false) {
            JOptionPane.showMessageDialog(null, "用户名或者密码错误", null, JOptionPane.WARNING_MESSAGE);
        }
    }



    private void button2MouseClicked(MouseEvent e) {
        lgg.setVisible(true);
        lg.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u8d26\u53f7");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(75, 50), label1.getPreferredSize()));

        //---- label2 ----
        label2.setText("\u5bc6\u7801");
        contentPane.add(label2);
        label2.setBounds(75, 90, 55, 40);
        contentPane.add(textField1);
        textField1.setBounds(160, 50, 145, 20);
        contentPane.add(textField2);
        textField2.setBounds(160, 100, 145, 20);

        //---- button1 ----
        button1.setText("\u767b\u5f55");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(140, 185), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u6ce8\u518c");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked(e);
                button2MouseClicked(e);
            }
        });
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(230, 185), button2.getPreferredSize()));

        contentPane.setPreferredSize(new Dimension(475, 335));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        setVisible(true);//让窗口可见
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口关闭的时候，进程也结束
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
