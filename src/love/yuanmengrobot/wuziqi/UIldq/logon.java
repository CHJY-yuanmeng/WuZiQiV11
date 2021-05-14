package love.yuanmengrobot.wuziqi.UIldq;

import love.yuanmengrobot.wuziqi.UI.MD5;
import love.yuanmengrobot.wuziqi.UI.loginfunction;
import love.yuanmengrobot.wuziqi.UIldq.loginForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;
/*
 * Created by JFormDesigner on Sun May 09 13:01:53 CST 2021
 */


/**
 * @author 1
 */
public class logon extends JFrame {
//    public static void main(String[] args) {
//        new logon();
//    }
    public logon() {
        initComponents();
    }

    private void button1MouseClicked(MouseEvent e) {
        String a=textField1.getText();
        String b=textField2.getText();
        String c=textField3.getText();
        String d=textField4.getText();
        if(c.equals(d)){
            try {
                String password= MD5.encoderByMd5(c);
                String sql= loginfunction.insertsql(a,b,password);
                Connection conn= loginfunction.getConnection();
                Statement stmt= loginfunction.GetStatement(conn);
                boolean flag= loginfunction.adduser(stmt,sql);
                if (flag==true){
                    JOptionPane.showMessageDialog(null,"注册成功",null,JOptionPane.WARNING_MESSAGE);
                }
                if(flag==false){
                    JOptionPane.showMessageDialog(null,"注册失败",null,JOptionPane.WARNING_MESSAGE);
                }
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                noSuchAlgorithmException.printStackTrace();
            } catch (UnsupportedEncodingException unsupportedEncodingException) {
                unsupportedEncodingException.printStackTrace();
            }
        }
        if(!c.equals(d)){
            JOptionPane.showMessageDialog(null,"两次输入的密码不一致，请重新输入！",null,JOptionPane.WARNING_MESSAGE);
        }




    }

    private void button2MouseClicked(MouseEvent e) {
        loginForm.lg.setVisible(true);
        loginForm.lgg.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        textField3 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u624b\u673a\u53f7\u7801");
        contentPane.add(label1);
        label1.setBounds(85, 40, 65, 30);
        contentPane.add(textField1);
        textField1.setBounds(155, 40, 195, textField1.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u7528\u6237\u540d");
        contentPane.add(label2);
        label2.setBounds(new Rectangle(new Point(100, 90), label2.getPreferredSize()));
        contentPane.add(textField2);
        textField2.setBounds(145, 75, 195, textField2.getPreferredSize().height);

        //---- label3 ----
        label3.setText("\u5bc6\u7801");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(105, 110), label3.getPreferredSize()));
        contentPane.add(textField3);
        textField3.setBounds(150, 110, 195, textField3.getPreferredSize().height);

        //---- label4 ----
        label4.setText("\u786e\u8ba4\u5bc6\u7801");
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(85, 145), label4.getPreferredSize()));
        contentPane.add(textField4);
        textField4.setBounds(150, 155, 195, textField4.getPreferredSize().height);

        //---- button1 ----
        button1.setText("\u786e\u8ba4");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(220, 195), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u53d6\u6d88");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked(e);
            }
        });
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(100, 190), button2.getPreferredSize()));

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(505, 425);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        setVisible(true);//让窗口可见
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口关闭的时候，进程也结束
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JTextField textField3;
    private JLabel label4;
    private JTextField textField4;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
