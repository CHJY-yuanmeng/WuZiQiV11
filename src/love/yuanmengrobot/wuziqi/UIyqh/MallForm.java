package love.yuanmengrobot.wuziqi.UIyqh;

import love.yuanmengrobot.wuziqi.UI.StrtForm;
import love.yuanmengrobot.wuziqi.Util.Main;
import love.yuanmengrobot.wuziqi.Util.Trade;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/*
 *   @Studio:CHJY · 源梦科技
 *   @Author:COOLIGHT
 *   @Ide:多人五子棋
 *   @Creation time:2021/5/8
 */

public class MallForm extends JFrame {
    String relativePath = "src/love/yuanmengrobot/wuziqi/images/";//图片相对路径
    String userId;//用户id
    Thread thread;//监控支付的线程
    private StrtForm strtForm;

    public StrtForm getStrtForm() {
        return strtForm;
    }

    public void setStrtForm(StrtForm strtForm) {
        this.strtForm = strtForm;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    //    public static void main(String[] args) {
//        new MallForm();//调用商城时请给出用户id，以对支付功能访问数据库
//
//    }
    public MallForm() {
        userId = "123456789";
        initComponents();
        pic1.setIcon(new ImageIcon(relativePath + "pic1.png"));//设置杀棋图片
        pic2.setIcon(new ImageIcon(relativePath + "pic2.png"));//设置变色棋图片
        pic3.setIcon(new ImageIcon(relativePath + "pic3.png"));//设置炸棋图片
        label34.setVisible(false);
        setVisible(false);

    }

    /*
     * 关闭商城
     * @e 鼠标点击事件*/
    private void buttonBackMouseClicked(MouseEvent e) {

        setVisible(false);//不显示商城
        strtForm.setVisible(true);

    }

    /*
     * 打开杀棋购买界面
     * @e 鼠标点击事件*/
    private void buttonPayItem1MouseClicked(MouseEvent e) {
        payPic1.setIcon(new ImageIcon(relativePath + "pic1.png"));//设置杀棋图片
        panelMall.setVisible(false);//不显示商城
        panelPayItem1.setVisible(true);//显示支付杀棋
        label31.setVisible(false);//隐藏购买状态
    }

    /*
     * 关闭杀棋购买界面
     * @e 鼠标点击事件*/
    private void buttonCancel1MouseClicked(MouseEvent e) {
        panelMall.setVisible(true);//显示商城
        panelPayItem1.setVisible(false);//不显示支付杀棋
        label34.setVisible(false);
    }

    /*
     * 关闭变色棋购买界面
     * @e 鼠标点击事件*/
    private void buttonCancel2MouseClicked(MouseEvent e) {
        panelMall.setVisible(true);//显示商城
        panelPayItem2.setVisible(false);//不显示支付变色棋
        label34.setVisible(false);
    }

    /*
     * 打开变色棋购买界面
     * @e 鼠标点击事件*/
    private void button4MouseClicked(MouseEvent e) {
        payPic2.setIcon(new ImageIcon(relativePath + "pic2.png"));//设置变色棋图片
        panelMall.setVisible(false);//不显示商城
        panelPayItem2.setVisible(true);//显示支付变色棋
        label32.setVisible(false);
    }

    /*
     * 打开炸棋购买界面
     * @e 鼠标点击事件*/
    private void button5MouseClicked(MouseEvent e) {
        payPic3.setIcon(new ImageIcon(relativePath + "pic3.png"));//设置炸棋图片
        panelMall.setVisible(false);//不显示商城
        panelPayItem3.setVisible(true);//显示支付炸棋
        label33.setVisible(false);
    }

    /*
     * 关闭杀棋购买界面
     * @e 鼠标点击事件*/
    private void buttonCancel3MouseClicked(MouseEvent e) {
        panelMall.setVisible(true);//显示商城
        panelPayItem3.setVisible(false);//不显示支付炸棋
        label34.setVisible(false);
    }

    /*
     * 杀棋数量减一
     * @e 鼠标点击事件*/
    private void buttonSub1MouseClicked(MouseEvent e) {
        int value = Integer.parseInt(textField1.getText()) - 1;//数量-1
        if (value < 0) {
            textField1.setText("0");//数量小于0置0
            label16.setText("总额:0积分");
        } else {
            textField1.setText(value + "");
            label16.setText("总额:" + value * 60 + "积分");
        }
        panelPayItem1.repaint();//刷新界面
    }

    /*
     * 变色棋数量减一
     * @e 鼠标点击事件*/
    private void buttonSub2MouseClicked(MouseEvent e) {
        int value = Integer.parseInt(textField2.getText()) - 1;//数量-1
        if (value < 0) {
            textField2.setText("0");//数量小于0置0
            label21.setText("总额:0积分");
        } else {
            textField2.setText(value + "");
            label21.setText("总额:" + value * 120 + "积分");
        }
        panelPayItem2.repaint();//刷新界面
    }

    /*
     * 炸棋数量减一
     * @e 鼠标点击事件*/
    private void buttonSub3MouseClicked(MouseEvent e) {
        int value = Integer.parseInt(textField3.getText()) - 1;//数量-1
        if (value < 0) {
            textField3.setText("0");//数量小于0置0
            label26.setText("总额:0积分");
        } else {
            textField3.setText(value + "");
            label26.setText("总额:" + value * 360 + "积分");
        }
        panelPayItem3.repaint();//刷新界面
    }

    /*
     * 杀棋数量加一
     * @e 鼠标点击事件*/
    private void buttonAdd1MouseClicked(MouseEvent e) {
        int value = Integer.parseInt(textField1.getText()) + 1;//数量+1
        textField1.setText(value + "");
        label16.setText("总额:" + value * 60 + "积分");
        panelPayItem1.repaint();//刷新界面
    }

    /*
     * 变色棋数量加一
     * @e 鼠标点击事件*/
    private void buttonAdd2MouseClicked(MouseEvent e) {
        int value = Integer.parseInt(textField2.getText()) + 1;//数量+1
        textField2.setText(value + "");
        label21.setText("总额:" + value * 120 + "积分");
        panelPayItem2.repaint();//刷新界面
    }

    /*
     * 炸棋数量加一
     * @e 鼠标点击事件*/
    private void buttonAdd3MouseClicked(MouseEvent e) {
        int value = Integer.parseInt(textField3.getText()) + 1;//数量+1
        textField3.setText(value + "");
        label26.setText("总额:" + value * 360 + "积分");
        panelPayItem3.repaint();//刷新界面
    }

    /*
     * 关闭积分充值
     * @e 鼠标点击事件*/
    private void buttonBack1MouseClicked(MouseEvent e) {
        panelScore.setVisible(false);//不显示积分充值
        panelMall.setVisible(true);//显示商城
    }

    /*
     * 显示积分充值
     * @e 鼠标点击事件*/
    private void buttonPayPointMouseClicked(MouseEvent e) {
        pointPic1.setIcon(new ImageIcon(relativePath + "pointPic1.png"));//设置积分充值333档图片
        pointPic2.setIcon(new ImageIcon(relativePath + "pointPic2.png"));//设置积分充值666档图片
        pointPic3.setIcon(new ImageIcon(relativePath + "pointPic3.png"));//设置积分充值999档图片
        panelMall.setVisible(false);//不显示商城
        panelScore.setVisible(true);//显示积分充值
    }

    /*
     * 购买道具杀棋
     * @e 鼠标点击事件*/
    private void pay1MouseClicked(MouseEvent e) {
        int score = 60 * Integer.parseInt(textField1.getText());
        if (score == 0) {
            label31.setText("数量为0");
            label31.setVisible(true);
            panelPayItem1.repaint();
            return;
        }
        Trade trade = new Trade();
        if (trade.purchasedItems(score, userId, "P01") == 1) {
            label31.setText("支付成功");
        } else {
            label31.setText("支付失败");
        }
        label31.setVisible(true);
        panelPayItem1.repaint();
    }

    /*
     * 购买道具变色棋
     * @e 鼠标点击事件*/
    private void pay2MouseClicked(MouseEvent e) {
        int score = 120 * Integer.parseInt(textField2.getText());
        if (score == 0) {
            label32.setText("数量为0");
            label32.setVisible(true);
            panelPayItem2.repaint();
            return;
        }
        Trade trade = new Trade();
        if (trade.purchasedItems(score, userId, "P02") == 1) {
            label32.setText("支付成功");
        } else {
            label32.setText("支付失败");
        }
        label32.setVisible(true);
        panelPayItem2.repaint();
    }

    /*
     * 购买道具炸棋
     * @e 鼠标点击事件*/
    private void pay3MouseClicked(MouseEvent e) {
        int score = 360 * Integer.parseInt(textField3.getText());
        if (score == 0) {
            label33.setText("数量为0");
            label33.setVisible(true);
            panelPayItem3.repaint();
            return;
        }
        Trade trade = new Trade();
        if (trade.purchasedItems(score, userId, "P03") == 1) {
            label33.setText("支付成功");
        } else {
            label33.setText("支付失败");
        }
        label33.setVisible(true);
        panelPayItem3.repaint();
    }

    /*
     * 积分充值第一档支付二维码显示
     * @e 鼠标点击事件*/
    private void buttonPayPoint1MouseClicked(MouseEvent e) {
        Main main = new Main();
        String orderNo = main.test_trade_precreate("3.33");//生成二维码并获取订单号
        try {
            picQRCode.setIcon(new ImageIcon(ImageIO.read(new File(relativePath + "qr.png"))));//设置二维码图片
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        panelQRCode.repaint();//刷新二维码界面
        panelQRCode.setVisible(true);//显示二维码
        panelScore.setVisible(false);//不显示积分充值
        thread = new Thread(new IsPaySuccess(main, orderNo, 333, userId, panelMall, panelQRCode, label34));
        thread.start();
    }

    /*
     * 积分充值第二档支付二维码显示
     * @e 鼠标点击事件*/
    private void buttonPayPoint2MouseClicked(MouseEvent e) {
        Main main = new Main();
        String orderNo = main.test_trade_precreate("6.66");//生成二维码并获取订单号
        try {
            picQRCode.setIcon(new ImageIcon(ImageIO.read(new File(relativePath + "qr.png"))));//设置二维码图片
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        panelQRCode.repaint();//刷新二维码界面
        panelQRCode.setVisible(true);//显示二维码
        panelScore.setVisible(false);//不显示积分充值
        thread = new Thread(new IsPaySuccess(main, orderNo, 666, userId, panelMall, panelQRCode, label34));
        thread.start();
    }

    /*
     * 积分充值第三档支付二维码显示
     * @e 鼠标点击事件*/
    private void buttonPayPoint3MouseClicked(MouseEvent e) {
        Main main = new Main();
        String orderNo = main.test_trade_precreate("9.99");//生成二维码并获取订单号
        try {
            picQRCode.setIcon(new ImageIcon(ImageIO.read(new File(relativePath + "qr.png"))));//设置二维码图片
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        panelQRCode.repaint();//刷新二维码界面
        panelQRCode.setVisible(true);//显示二维码
        panelScore.setVisible(false);//不显示积分充值
        thread = new Thread(new IsPaySuccess(main, orderNo, 999, userId, panelMall, panelQRCode, label34));
        thread.start();//线程启动
    }

    /*
     * 取消支付
     * @e 鼠标点击事件*/
    private void button1MouseClicked(MouseEvent e) {
        panelQRCode.setVisible(false);//不显示二维码
        panelMall.setVisible(true);//显示商城
        thread.stop();//停止监控支付线程
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panelMall = new JPanel();
        label1shang = new JLabel();
        buttonSBackHome = new JButton();
        buttonPayPoint = new JButton();
        pic1 = new JLabel();
        pic2 = new JLabel();
        pic3 = new JLabel();
        label2sha = new JLabel();
        label6bian = new JLabel();
        label12zha = new JLabel();
        label3qu = new JLabel();
        label5gai = new JLabel();
        label8xuan = new JLabel();
        label4zha2 = new JLabel();
        label7bian2 = new JLabel();
        label9zha2 = new JLabel();
        label10zha3 = new JLabel();
        label11zha4 = new JLabel();
        buttonPayItem1 = new JButton();
        button4buybian = new JButton();
        button5buyzha = new JButton();
        label34sess = new JLabel();
        panelPayItem1 = new JPanel();
        payPic1 = new JLabel();
        label13quqi = new JLabel();
        label14renyi = new JLabel();
        label15num = new JLabel();
        textField1bian = new JTextField();
        buttonSub1 = new JButton();
        buttonAdd1 = new JButton();
        label16zon60 = new JLabel();
        pay1 = new JButton();
        label31txt = new JLabel();
        label17sha = new JLabel();
        buttonCancel1 = new JButton();
        panelPayItem2 = new JPanel();
        payPic2 = new JLabel();
        label18gaib = new JLabel();
        label19yans = new JLabel();
        label20num = new JLabel();
        textField2num = new JTextField();
        buttonSub2gai = new JButton();
        buttonAdd2gai = new JButton();
        label21sum = new JLabel();
        pay2bian = new JButton();
        label22bian = new JLabel();
        buttonCancel2bian = new JButton();
        label32txt = new JLabel();
        panelPayItem3 = new JPanel();
        payPic3 = new JLabel();
        label23xuan = new JLabel();
        label24xuan = new JLabel();
        label25num = new JLabel();
        textField3zha = new JTextField();
        buttonSub3zha = new JButton();
        buttonAdd3zha = new JButton();
        label26zon = new JLabel();
        pay3zha = new JButton();
        label27zha = new JLabel();
        buttonCancel3zha = new JButton();
        label28zha = new JLabel();
        label29zha = new JLabel();
        label33zha = new JLabel();
        panelScore = new JPanel();
        label30zhu = new JLabel();
        pointPic1zhu = new JLabel();
        pointPic3zhu = new JLabel();
        pointPic2zhu = new JLabel();
        buttonPayPoint1zhu = new JButton();
        buttonPayPoint2zhu = new JButton();
        buttonPayPoint3zhu = new JButton();
        buttonBack1zhu = new JButton();
        panelQRCode = new JPanel();
        picQRCode = new JLabel();
        button1 = new JButton();

        //======== this ========
        setVisible(true);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panelMall ========
        {
            panelMall.setLayout(null);

            //---- label1shang ----
            label1shang.setText("\u5546\u57ce");
            label1shang.setFont(label1shang.getFont().deriveFont(label1shang.getFont().getSize() + 20f));
            panelMall.add(label1shang);
            label1shang.setBounds(new Rectangle(new Point(265, 25), label1shang.getPreferredSize()));

            //---- buttonSBackHome ----
            buttonSBackHome.setText("\u8fd4\u56de");
            buttonSBackHome.setFont(buttonSBackHome.getFont().deriveFont(buttonSBackHome.getFont().getSize() + 8f));
            buttonSBackHome.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonBackMouseClicked(e);
                }
            });
            panelMall.add(buttonSBackHome);
            buttonSBackHome.setBounds(580, 10, 114, buttonSBackHome.getPreferredSize().height);

            //---- buttonPayPoint ----
            buttonPayPoint.setText("\u79ef\u5206\u5145\u503c");
            buttonPayPoint.setFont(buttonPayPoint.getFont().deriveFont(buttonPayPoint.getFont().getSize() + 8f));
            buttonPayPoint.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonPayPointMouseClicked(e);
                }
            });
            panelMall.add(buttonPayPoint);
            buttonPayPoint.setBounds(new Rectangle(new Point(580, 55), buttonPayPoint.getPreferredSize()));

            //---- pic1 ----
            pic1.setIcon(null);
            panelMall.add(pic1);
            pic1.setBounds(20, 70, 169, 159);

            //---- pic2 ----
            pic2.setIcon(null);
            panelMall.add(pic2);
            pic2.setBounds(205, 100, 212, 97);

            //---- pic3 ----
            pic3.setIcon(null);
            panelMall.add(pic3);
            pic3.setBounds(430, 85, 134, 137);

            //---- label2sha ----
            label2sha.setText("\u6740\u68cb");
            label2sha.setFont(label2sha.getFont().deriveFont(label2sha.getFont().getSize() + 6f));
            label2sha.setHorizontalTextPosition(SwingConstants.CENTER);
            panelMall.add(label2sha);
            label2sha.setBounds(85, 235, 50, label2sha.getPreferredSize().height);

            //---- label6bian ----
            label6bian.setText("\u53d8\u8272\u68cb");
            label6bian.setFont(label6bian.getFont().deriveFont(label6bian.getFont().getSize() + 6f));
            panelMall.add(label6bian);
            label6bian.setBounds(280, 235, 65, label6bian.getPreferredSize().height);

            //---- label12zha ----
            label12zha.setText("\u70b8\u68cb");
            label12zha.setFont(label12zha.getFont().deriveFont(label12zha.getFont().getSize() + 6f));
            panelMall.add(label12zha);
            label12zha.setBounds(475, 235, 50, label12zha.getPreferredSize().height);

            //---- label3qu ----
            label3qu.setText("\u4ece\u68cb\u76d8\u4e0a\u53bb\u6389");
            label3qu.setFont(label3qu.getFont().deriveFont(label3qu.getFont().getSize() + 5f));
            panelMall.add(label3qu);
            label3qu.setBounds(new Rectangle(new Point(60, 285), label3qu.getPreferredSize()));

            //---- label5gai ----
            label5gai.setText("\u6539\u53d8\u68cb\u76d8\u4e0a\u4efb\u610f");
            label5gai.setFont(label5gai.getFont().deriveFont(label5gai.getFont().getSize() + 5f));
            panelMall.add(label5gai);
            label5gai.setBounds(240, 280, 127, label5gai.getPreferredSize().height);

            //---- label8xuan ----
            label8xuan.setText(" \u9009\u62e9\u4e00\u4e2a\u81ea\u5df1\u7684\u68cb\u5b50");
            label8xuan.setFont(label8xuan.getFont().deriveFont(label8xuan.getFont().getSize() + 3f));
            panelMall.add(label8xuan);
            label8xuan.setBounds(415, 275, 146, label8xuan.getPreferredSize().height);

            //---- label4zha2 ----
            label4zha2.setText("\u4efb\u610f\u4e00\u4e2a\u68cb\u5b50");
            label4zha2.setFont(label4zha2.getFont().deriveFont(label4zha2.getFont().getSize() + 5f));
            panelMall.add(label4zha2);
            label4zha2.setBounds(new Rectangle(new Point(60, 325), label4zha2.getPreferredSize()));

            //---- label7bian2 ----
            label7bian2.setText("\u4e00\u4e2a\u68cb\u5b50\u7684\u989c\u8272");
            label7bian2.setFont(label7bian2.getFont().deriveFont(label7bian2.getFont().getSize() + 5f));
            panelMall.add(label7bian2);
            label7bian2.setBounds(240, 320, 127, label7bian2.getPreferredSize().height);

            //---- label9zha2 ----
            label9zha2.setText("\u8ba9\u68cb\u5b50\u5468\u56f43x3\u7684\u68cb\u5b50");
            label9zha2.setFont(label9zha2.getFont().deriveFont(label9zha2.getFont().getSize() + 3f));
            panelMall.add(label9zha2);
            label9zha2.setBounds(new Rectangle(new Point(415, 295), label9zha2.getPreferredSize()));

            //---- label10zha3 ----
            label10zha3.setText("\u4ece\u68cb\u76d8\u4e0a\u53bb\u6389");
            label10zha3.setFont(label10zha3.getFont().deriveFont(label10zha3.getFont().getSize() + 3f));
            panelMall.add(label10zha3);
            label10zha3.setBounds(440, 315, 110, label10zha3.getPreferredSize().height);

            //---- label11zha4 ----
            label11zha4.setText("\u5305\u62ec\u81ea\u5df1\u7684\u68cb\u5b50");
            label11zha4.setFont(label11zha4.getFont().deriveFont(label11zha4.getFont().getSize() + 3f));
            panelMall.add(label11zha4);
            label11zha4.setBounds(435, 340, 120, label11zha4.getPreferredSize().height);

            //---- buttonPayItem1 ----
            buttonPayItem1.setText("\u8d2d\u4e70");
            buttonPayItem1.setFont(buttonPayItem1.getFont().deriveFont(buttonPayItem1.getFont().getSize() + 6f));
            buttonPayItem1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonPayItem1MouseClicked(e);
                }
            });
            panelMall.add(buttonPayItem1);
            buttonPayItem1.setBounds(70, 365, 80, buttonPayItem1.getPreferredSize().height);

            //---- button4buybian ----
            button4buybian.setText("\u8d2d\u4e70");
            button4buybian.setFont(button4buybian.getFont().deriveFont(button4buybian.getFont().getSize() + 6f));
            button4buybian.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button4MouseClicked(e);
                }
            });
            panelMall.add(button4buybian);
            button4buybian.setBounds(265, 365, 80, button4buybian.getPreferredSize().height);

            //---- button5buyzha ----
            button5buyzha.setText("\u8d2d\u4e70");
            button5buyzha.setFont(button5buyzha.getFont().deriveFont(button5buyzha.getFont().getSize() + 6f));
            button5buyzha.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button5MouseClicked(e);
                }
            });
            panelMall.add(button5buyzha);
            button5buyzha.setBounds(445, 365, 80, button5buyzha.getPreferredSize().height);

            //---- label34sess ----
            label34sess.setFont(label34sess.getFont().deriveFont(label34sess.getFont().getSize() + 8f));
            label34sess.setText("\u79ef\u5206\u5145\u503c\u6210\u529f!");
            panelMall.add(label34sess);
            label34sess.setBounds(260, 400, 300, label34sess.getPreferredSize().height);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panelMall.getComponentCount(); i++) {
                    Rectangle bounds = panelMall.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panelMall.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panelMall.setMinimumSize(preferredSize);
                panelMall.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panelMall);
        panelMall.setBounds(35, 30, 700, 525);

        //======== panelPayItem1 ========
        {
            panelPayItem1.setVisible(false);
            panelPayItem1.setLayout(null);
            panelPayItem1.add(payPic1);
            payPic1.setBounds(165, 15, 169, 159);

            //---- label13quqi ----
            label13quqi.setText("\u4ece\u68cb\u76d8\u4e0a\u53bb\u6389");
            label13quqi.setFont(label13quqi.getFont().deriveFont(label13quqi.getFont().getSize() + 7f));
            panelPayItem1.add(label13quqi);
            label13quqi.setBounds(new Rectangle(new Point(200, 220), label13quqi.getPreferredSize()));

            //---- label14renyi ----
            label14renyi.setText("\u4efb\u610f\u4e00\u4e2a\u68cb\u5b50");
            label14renyi.setFont(label14renyi.getFont().deriveFont(label14renyi.getFont().getSize() + 7f));
            panelPayItem1.add(label14renyi);
            label14renyi.setBounds(new Rectangle(new Point(200, 250), label14renyi.getPreferredSize()));

            //---- label15num ----
            label15num.setText("\u6570\u91cf:");
            label15num.setFont(label15num.getFont().deriveFont(label15num.getFont().getSize() + 10f));
            panelPayItem1.add(label15num);
            label15num.setBounds(195, 300, 55, label15num.getPreferredSize().height);

            //---- textField1bian ----
            textField1bian.setText("1");
            textField1bian.setFont(textField1bian.getFont().deriveFont(textField1bian.getFont().getSize() + 12f));
            panelPayItem1.add(textField1bian);
            textField1bian.setBounds(255, 290, 50, textField1bian.getPreferredSize().height);

            //---- buttonSub1 ----
            buttonSub1.setText("<");
            buttonSub1.setFont(buttonSub1.getFont().deriveFont(buttonSub1.getFont().getSize() + 5f));
            buttonSub1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonSub1MouseClicked(e);
                }
            });
            panelPayItem1.add(buttonSub1);
            buttonSub1.setBounds(305, 285, 50, 50);

            //---- buttonAdd1 ----
            buttonAdd1.setText(">");
            buttonAdd1.setFont(buttonAdd1.getFont().deriveFont(buttonAdd1.getFont().getSize() + 5f));
            buttonAdd1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonAdd1MouseClicked(e);
                }
            });
            panelPayItem1.add(buttonAdd1);
            buttonAdd1.setBounds(355, 285, 50, 50);

            //---- label16zon60 ----
            label16zon60.setText("\u603b\u989d:60\u79ef\u5206");
            label16zon60.setFont(label16zon60.getFont().deriveFont(label16zon60.getFont().getSize() + 8f));
            panelPayItem1.add(label16zon60);
            label16zon60.setBounds(205, 345, 200, label16zon60.getPreferredSize().height);

            //---- pay1 ----
            pay1.setText("\u652f\u4ed8");
            pay1.setFont(pay1.getFont().deriveFont(pay1.getFont().getSize() + 8f));
            pay1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    pay1MouseClicked(e);
                }
            });
            panelPayItem1.add(pay1);
            pay1.setBounds(new Rectangle(new Point(220, 380), pay1.getPreferredSize()));

            //---- label31txt ----
            label31txt.setText("text");
            label31txt.setFont(label31txt.getFont().deriveFont(label31txt.getFont().getSize() + 8f));
            label31txt.setVisible(false);
            panelPayItem1.add(label31txt);
            label31txt.setBounds(310, 380, 100, label31txt.getPreferredSize().height);

            //---- label17sha ----
            label17sha.setText("\u6740\u68cb");
            label17sha.setFont(label17sha.getFont().deriveFont(label17sha.getFont().getSize() + 8f));
            panelPayItem1.add(label17sha);
            label17sha.setBounds(new Rectangle(new Point(230, 185), label17sha.getPreferredSize()));

            //---- buttonCancel1 ----
            buttonCancel1.setText("\u53d6\u6d88");
            buttonCancel1.setFont(buttonCancel1.getFont().deriveFont(buttonCancel1.getFont().getSize() + 8f));
            buttonCancel1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonCancel1MouseClicked(e);
                }
            });
            panelPayItem1.add(buttonCancel1);
            buttonCancel1.setBounds(new Rectangle(new Point(220, 420), buttonCancel1.getPreferredSize()));

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panelPayItem1.getComponentCount(); i++) {
                    Rectangle bounds = panelPayItem1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panelPayItem1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panelPayItem1.setMinimumSize(preferredSize);
                panelPayItem1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panelPayItem1);
        panelPayItem1.setBounds(45, 15, 565, 480);

        //======== panelPayItem2 ========
        {
            panelPayItem2.setVisible(false);
            panelPayItem2.setLayout(null);
            panelPayItem2.add(payPic2);
            payPic2.setBounds(165, 15, 212, 97);

            //---- label18gaib ----
            label18gaib.setText("\u6539\u53d8\u68cb\u76d8\u4e0a\u4efb\u610f");
            label18gaib.setFont(label18gaib.getFont().deriveFont(label18gaib.getFont().getSize() + 7f));
            panelPayItem2.add(label18gaib);
            label18gaib.setBounds(new Rectangle(new Point(205, 165), label18gaib.getPreferredSize()));

            //---- label19yans ----
            label19yans.setText("\u4e00\u4e2a\u68cb\u5b50\u7684\u989c\u8272");
            label19yans.setFont(label19yans.getFont().deriveFont(label19yans.getFont().getSize() + 7f));
            panelPayItem2.add(label19yans);
            label19yans.setBounds(new Rectangle(new Point(205, 200), label19yans.getPreferredSize()));

            //---- label20num ----
            label20num.setText("\u6570\u91cf:");
            label20num.setFont(label20num.getFont().deriveFont(label20num.getFont().getSize() + 10f));
            panelPayItem2.add(label20num);
            label20num.setBounds(180, 245, 55, label20num.getPreferredSize().height);

            //---- textField2num ----
            textField2num.setText("1");
            textField2num.setFont(textField2num.getFont().deriveFont(textField2num.getFont().getSize() + 12f));
            panelPayItem2.add(textField2num);
            textField2num.setBounds(245, 240, 50, textField2num.getPreferredSize().height);

            //---- buttonSub2gai ----
            buttonSub2gai.setText("<");
            buttonSub2gai.setFont(buttonSub2gai.getFont().deriveFont(buttonSub2gai.getFont().getSize() + 5f));
            buttonSub2gai.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonSub2MouseClicked(e);
                }
            });
            panelPayItem2.add(buttonSub2gai);
            buttonSub2gai.setBounds(295, 235, 50, 50);

            //---- buttonAdd2gai ----
            buttonAdd2gai.setText(">");
            buttonAdd2gai.setFont(buttonAdd2gai.getFont().deriveFont(buttonAdd2gai.getFont().getSize() + 5f));
            buttonAdd2gai.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonAdd2MouseClicked(e);
                }
            });
            panelPayItem2.add(buttonAdd2gai);
            buttonAdd2gai.setBounds(345, 235, 50, 50);

            //---- label21sum ----
            label21sum.setText("\u603b\u989d:120\u79ef\u5206");
            label21sum.setFont(label21sum.getFont().deriveFont(label21sum.getFont().getSize() + 8f));
            panelPayItem2.add(label21sum);
            label21sum.setBounds(210, 295, 200, label21sum.getPreferredSize().height);

            //---- pay2bian ----
            pay2bian.setText("\u652f\u4ed8");
            pay2bian.setFont(pay2bian.getFont().deriveFont(pay2bian.getFont().getSize() + 8f));
            pay2bian.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    pay2MouseClicked(e);
                }
            });
            panelPayItem2.add(pay2bian);
            pay2bian.setBounds(new Rectangle(new Point(230, 330), pay2bian.getPreferredSize()));

            //---- label22bian ----
            label22bian.setText("\u53d8\u8272\u68cb");
            label22bian.setFont(label22bian.getFont().deriveFont(label22bian.getFont().getSize() + 8f));
            panelPayItem2.add(label22bian);
            label22bian.setBounds(new Rectangle(new Point(240, 135), label22bian.getPreferredSize()));

            //---- buttonCancel2bian ----
            buttonCancel2bian.setText("\u53d6\u6d88");
            buttonCancel2bian.setFont(buttonCancel2bian.getFont().deriveFont(buttonCancel2bian.getFont().getSize() + 8f));
            buttonCancel2bian.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonCancel1MouseClicked(e);
                    buttonCancel2MouseClicked(e);
                }
            });
            panelPayItem2.add(buttonCancel2bian);
            buttonCancel2bian.setBounds(new Rectangle(new Point(230, 370), buttonCancel2bian.getPreferredSize()));

            //---- label32txt ----
            label32txt.setText("text");
            label32txt.setFont(label32txt.getFont().deriveFont(label32txt.getFont().getSize() + 8f));
            label32txt.setVisible(false);
            panelPayItem2.add(label32txt);
            label32txt.setBounds(310, 330, 100, 27);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panelPayItem2.getComponentCount(); i++) {
                    Rectangle bounds = panelPayItem2.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panelPayItem2.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panelPayItem2.setMinimumSize(preferredSize);
                panelPayItem2.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panelPayItem2);
        panelPayItem2.setBounds(45, 15, 565, 430);

        //======== panelPayItem3 ========
        {
            panelPayItem3.setVisible(false);
            panelPayItem3.setLayout(null);
            panelPayItem3.add(payPic3);
            payPic3.setBounds(195, 15, 134, 137);

            //---- label23xuan ----
            label23xuan.setText("\u9009\u62e9\u4e00\u4e2a\u81ea\u5df1\u7684\u68cb\u5b50");
            label23xuan.setFont(label23xuan.getFont().deriveFont(label23xuan.getFont().getSize() + 4f));
            panelPayItem3.add(label23xuan);
            label23xuan.setBounds(new Rectangle(new Point(200, 195), label23xuan.getPreferredSize()));

            //---- label24xuan ----
            label24xuan.setFont(label24xuan.getFont().deriveFont(label24xuan.getFont().getSize() + 4f));
            label24xuan.setText("\u8ba9\u68cb\u5b50\u5468\u56f43x3\u7684\u68cb\u5b50");
            panelPayItem3.add(label24xuan);
            label24xuan.setBounds(new Rectangle(new Point(195, 215), label24xuan.getPreferredSize()));

            //---- label25num ----
            label25num.setText("\u6570\u91cf:");
            label25num.setFont(label25num.getFont().deriveFont(label25num.getFont().getSize() + 10f));
            panelPayItem3.add(label25num);
            label25num.setBounds(170, 290, 55, label25num.getPreferredSize().height);

            //---- textField3zha ----
            textField3zha.setText("1");
            textField3zha.setFont(textField3zha.getFont().deriveFont(textField3zha.getFont().getSize() + 12f));
            panelPayItem3.add(textField3zha);
            textField3zha.setBounds(235, 290, 50, textField3zha.getPreferredSize().height);

            //---- buttonSub3zha ----
            buttonSub3zha.setText("<");
            buttonSub3zha.setFont(buttonSub3zha.getFont().deriveFont(buttonSub3zha.getFont().getSize() + 5f));
            buttonSub3zha.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonSub3MouseClicked(e);
                }
            });
            panelPayItem3.add(buttonSub3zha);
            buttonSub3zha.setBounds(290, 285, 50, 50);

            //---- buttonAdd3zha ----
            buttonAdd3zha.setText(">");
            buttonAdd3zha.setFont(buttonAdd3zha.getFont().deriveFont(buttonAdd3zha.getFont().getSize() + 5f));
            buttonAdd3zha.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonAdd3MouseClicked(e);
                }
            });
            panelPayItem3.add(buttonAdd3zha);
            buttonAdd3zha.setBounds(340, 285, 50, 50);

            //---- label26zon ----
            label26zon.setText("\u603b\u989d:360\u79ef\u5206");
            label26zon.setFont(label26zon.getFont().deriveFont(label26zon.getFont().getSize() + 8f));
            panelPayItem3.add(label26zon);
            label26zon.setBounds(205, 345, 200, label26zon.getPreferredSize().height);

            //---- pay3zha ----
            pay3zha.setText("\u652f\u4ed8");
            pay3zha.setFont(pay3zha.getFont().deriveFont(pay3zha.getFont().getSize() + 8f));
            pay3zha.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    pay3MouseClicked(e);
                }
            });
            panelPayItem3.add(pay3zha);
            pay3zha.setBounds(new Rectangle(new Point(220, 380), pay3zha.getPreferredSize()));

            //---- label27zha ----
            label27zha.setText("\u70b8\u68cb");
            label27zha.setFont(label27zha.getFont().deriveFont(label27zha.getFont().getSize() + 8f));
            panelPayItem3.add(label27zha);
            label27zha.setBounds(new Rectangle(new Point(245, 165), label27zha.getPreferredSize()));

            //---- buttonCancel3zha ----
            buttonCancel3zha.setText("\u53d6\u6d88");
            buttonCancel3zha.setFont(buttonCancel3zha.getFont().deriveFont(buttonCancel3zha.getFont().getSize() + 8f));
            buttonCancel3zha.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonCancel1MouseClicked(e);
                    buttonCancel2MouseClicked(e);
                    buttonCancel3MouseClicked(e);
                }
            });
            panelPayItem3.add(buttonCancel3zha);
            buttonCancel3zha.setBounds(new Rectangle(new Point(220, 420), buttonCancel3zha.getPreferredSize()));

            //---- label28zha ----
            label28zha.setText("\u4ece\u68cb\u76d8\u4e0a\u53bb\u6389");
            label28zha.setFont(label28zha.getFont().deriveFont(label28zha.getFont().getSize() + 4f));
            panelPayItem3.add(label28zha);
            label28zha.setBounds(new Rectangle(new Point(220, 240), label28zha.getPreferredSize()));

            //---- label29zha ----
            label29zha.setText("\u5305\u62ec\u81ea\u5df1\u7684\u68cb\u5b50");
            label29zha.setFont(label29zha.getFont().deriveFont(label29zha.getFont().getSize() + 4f));
            panelPayItem3.add(label29zha);
            label29zha.setBounds(new Rectangle(new Point(215, 265), label29zha.getPreferredSize()));

            //---- label33zha ----
            label33zha.setText("text");
            label33zha.setFont(label33zha.getFont().deriveFont(label33zha.getFont().getSize() + 8f));
            label33zha.setVisible(false);
            panelPayItem3.add(label33zha);
            label33zha.setBounds(310, 380, 100, 27);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panelPayItem3.getComponentCount(); i++) {
                    Rectangle bounds = panelPayItem3.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panelPayItem3.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panelPayItem3.setMinimumSize(preferredSize);
                panelPayItem3.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panelPayItem3);
        panelPayItem3.setBounds(45, 15, 565, 489);

        //======== panelScore ========
        {
            panelScore.setVisible(false);
            panelScore.setLayout(null);

            //---- label30zhu ----
            label30zhu.setText("\u79ef\u5206\u52a9\u529b\u5305");
            label30zhu.setFont(label30zhu.getFont().deriveFont(label30zhu.getFont().getSize() + 30f));
            panelScore.add(label30zhu);
            label30zhu.setBounds(new Rectangle(new Point(160, 30), label30zhu.getPreferredSize()));
            panelScore.add(pointPic1zhu);
            pointPic1zhu.setBounds(15, 105, 158, 137);
            panelScore.add(pointPic3zhu);
            pointPic3zhu.setBounds(360, 105, 154, 133);
            panelScore.add(pointPic2zhu);
            pointPic2zhu.setBounds(190, 105, 155, 133);

            //---- buttonPayPoint1zhu ----
            buttonPayPoint1zhu.setText("\u8d2d\u4e70");
            buttonPayPoint1zhu.setFont(buttonPayPoint1zhu.getFont().deriveFont(buttonPayPoint1zhu.getFont().getSize() + 8f));
            buttonPayPoint1zhu.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonPayPoint1MouseClicked(e);
                }
            });
            panelScore.add(buttonPayPoint1zhu);
            buttonPayPoint1zhu.setBounds(new Rectangle(new Point(50, 260), buttonPayPoint1zhu.getPreferredSize()));

            //---- buttonPayPoint2zhu ----
            buttonPayPoint2zhu.setText("\u8d2d\u4e70");
            buttonPayPoint2zhu.setFont(buttonPayPoint2zhu.getFont().deriveFont(buttonPayPoint2zhu.getFont().getSize() + 8f));
            buttonPayPoint2zhu.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonPayPoint2MouseClicked(e);
                }
            });
            panelScore.add(buttonPayPoint2zhu);
            buttonPayPoint2zhu.setBounds(230, 260, 78, 33);

            //---- buttonPayPoint3zhu ----
            buttonPayPoint3zhu.setText("\u8d2d\u4e70");
            buttonPayPoint3zhu.setFont(buttonPayPoint3zhu.getFont().deriveFont(buttonPayPoint3zhu.getFont().getSize() + 8f));
            buttonPayPoint3zhu.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonPayPoint3MouseClicked(e);
                }
            });
            panelScore.add(buttonPayPoint3zhu);
            buttonPayPoint3zhu.setBounds(400, 260, 78, 33);

            //---- buttonBack1zhu ----
            buttonBack1zhu.setText("\u8fd4\u56de");
            buttonBack1zhu.setFont(buttonBack1zhu.getFont().deriveFont(buttonBack1zhu.getFont().getSize() + 8f));
            buttonBack1zhu.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    buttonBack1MouseClicked(e);
                }
            });
            panelScore.add(buttonBack1zhu);
            buttonBack1zhu.setBounds(400, 20, 114, 33);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panelScore.getComponentCount(); i++) {
                    Rectangle bounds = panelScore.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panelScore.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panelScore.setMinimumSize(preferredSize);
                panelScore.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panelScore);
        panelScore.setBounds(45, 15, 535, 320);

        //======== panelQRCode ========
        {
            panelQRCode.setVisible(false);
            panelQRCode.setLayout(null);
            panelQRCode.add(picQRCode);
            picQRCode.setBounds(50, 55, 300, 300);

            //---- button1 ----
            button1.setText("\u53d6\u6d88");
            button1.setFont(button1.getFont().deriveFont(button1.getFont().getSize() + 8f));
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            panelQRCode.add(button1);
            button1.setBounds(new Rectangle(new Point(160, 360), button1.getPreferredSize()));

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panelQRCode.getComponentCount(); i++) {
                    Rectangle bounds = panelQRCode.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panelQRCode.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panelQRCode.setMinimumSize(preferredSize);
                panelQRCode.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panelQRCode);
        panelQRCode.setBounds(150, 80, 400, 400);

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
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panelMall;
    private JLabel label1shang;
    private JButton buttonSBackHome;
    private JButton buttonPayPoint;
    private JLabel pic1;
    private JLabel pic2;
    private JLabel pic3;
    private JLabel label2sha;
    private JLabel label6bian;
    private JLabel label12zha;
    private JLabel label3qu;
    private JLabel label5gai;
    private JLabel label8xuan;
    private JLabel label4zha2;
    private JLabel label7bian2;
    private JLabel label9zha2;
    private JLabel label10zha3;
    private JLabel label11zha4;
    private JButton buttonPayItem1;
    private JButton button4buybian;
    private JButton button5buyzha;
    private JLabel label34sess;
    private JPanel panelPayItem1;
    private JLabel payPic1;
    private JLabel label13quqi;
    private JLabel label14renyi;
    private JLabel label15num;
    private JTextField textField1bian;
    private JButton buttonSub1;
    private JButton buttonAdd1;
    private JLabel label16zon60;
    private JButton pay1;
    private JLabel label31txt;
    private JLabel label17sha;
    private JButton buttonCancel1;
    private JPanel panelPayItem2;
    private JLabel payPic2;
    private JLabel label18gaib;
    private JLabel label19yans;
    private JLabel label20num;
    private JTextField textField2num;
    private JButton buttonSub2gai;
    private JButton buttonAdd2gai;
    private JLabel label21sum;
    private JButton pay2bian;
    private JLabel label22bian;
    private JButton buttonCancel2bian;
    private JLabel label32txt;
    private JPanel panelPayItem3;
    private JLabel payPic3;
    private JLabel label23xuan;
    private JLabel label24xuan;
    private JLabel label25num;
    private JTextField textField3zha;
    private JButton buttonSub3zha;
    private JButton buttonAdd3zha;
    private JLabel label26zon;
    private JButton pay3zha;
    private JLabel label27zha;
    private JButton buttonCancel3zha;
    private JLabel label28zha;
    private JLabel label29zha;
    private JLabel label33zha;
    private JPanel panelScore;
    private JLabel label30zhu;
    private JLabel pointPic1zhu;
    private JLabel pointPic3zhu;
    private JLabel pointPic2zhu;
    private JButton buttonPayPoint1zhu;
    private JButton buttonPayPoint2zhu;
    private JButton buttonPayPoint3zhu;
    private JButton buttonBack1zhu;
    private JPanel panelQRCode;
    private JLabel picQRCode;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

/*监控支付成功的进程类*/
class IsPaySuccess implements Runnable {
    Main main;
    String orderNo;
    int items;
    String userId;
    JPanel mall;
    JPanel qr;
    JLabel label;

    public IsPaySuccess(Main main, String orderNo, int score, String userId, JPanel mall, JPanel qr, JLabel label) {
        this.main = main;
        this.orderNo = orderNo;
        this.items = score;
        this.userId = userId;
        this.mall = mall;
        this.qr = qr;
        this.label = label;
    }

    @Override
    public void run() {
        while (true) {
            String result = main.test_trade_query(orderNo);//查询订单状态
            if (result.equals("SUCCESS")) {
                Trade trade = new Trade();
                trade.pointsRecharge(items, userId);//充积分
                mall.setVisible(true);//显示商城
                qr.setVisible(false);//关闭二维码
                label.setVisible(true);
                return;
            }
        }

    }
}
