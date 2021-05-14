package love.yuanmengrobot.wuziqi.UI;/*
 * Created by JFormDesigner on Sun May 09 20:39:07 CST 2021
 */


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author 1
 */
public class StartGame extends JFrame {
    static Socket socket;
    PrintWriter out;
    public String userName;
//    public String userLoginId;
    public String roomNamePublic;
    private StrtForm strtForm;

//    public String getUserLoginId() {
//        return userLoginId;
//    }
//
//    public void setUserLoginId(String userLoginId) {
//        this.userLoginId = userLoginId;
//    }

    public StrtForm getStrtForm() {
        return strtForm;
    }

    public void setStrtForm(StrtForm strtForm) {
        this.strtForm = strtForm;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public StartGame(Socket socket) {
        this.socket = socket;
//        int x = (int) (Math.random() * 100);
//        userName = "玩家" + x;
        try {
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1View = new JPanel();
        scrollPane1 = new JScrollPane();
        table1Room = new JTable();
        button1Re = new JButton();
        button2Insert = new JButton();
        button3Create = new JButton();
        button1BackToHome = new JButton();
        panel2Room = new JPanel();
        button1CancelCreate = new JButton();
        textField1RoomName = new JTextField();
        label1 = new JLabel();
        button1SenRoomMeg = new JButton();
        panel3RoomView = new JPanel();
        scrollPane2 = new JScrollPane();
        table1RoomView = new JTable();
        button1Ready = new JButton();
        button2Leave = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1View ========
        {
            panel1View.setLayout(null);

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(table1Room);
            }
            panel1View.add(scrollPane1);
            scrollPane1.setBounds(85, 10, 300, 250);

            //---- button1Re ----
            button1Re.setText("\u5237\u65b0");
            panel1View.add(button1Re);
            button1Re.setBounds(new Rectangle(new Point(100, 280), button1Re.getPreferredSize()));

            //---- button2Insert ----
            button2Insert.setText("\u52a0\u5165\u623f\u95f4");
            panel1View.add(button2Insert);
            button2Insert.setBounds(new Rectangle(new Point(205, 280), button2Insert.getPreferredSize()));

            //---- button3Create ----
            button3Create.setText("\u521b\u5efa\u623f\u95f4");
            panel1View.add(button3Create);
            button3Create.setBounds(new Rectangle(new Point(325, 280), button3Create.getPreferredSize()));

            //---- button1BackToHome ----
            button1BackToHome.setText("\u8fd4\u56de\u9996\u9875");
            panel1View.add(button1BackToHome);
            button1BackToHome.setBounds(new Rectangle(new Point(395, 15), button1BackToHome.getPreferredSize()));

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1View.getComponentCount(); i++) {
                    Rectangle bounds = panel1View.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1View.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1View.setMinimumSize(preferredSize);
                panel1View.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1View);
        panel1View.setBounds(10, 10, 485, 315);

        //======== panel2Room ========
        {
            panel2Room.setLayout(null);

            //---- button1CancelCreate ----
            button1CancelCreate.setText("\u53d6\u6d88");
            panel2Room.add(button1CancelCreate);
            button1CancelCreate.setBounds(new Rectangle(new Point(145, 215), button1CancelCreate.getPreferredSize()));
            panel2Room.add(textField1RoomName);
            textField1RoomName.setBounds(170, 140, 115, textField1RoomName.getPreferredSize().height);

            //---- label1 ----
            label1.setText("\u623f\u95f4\u540d\u5b57");
            panel2Room.add(label1);
            label1.setBounds(195, 110, 70, 20);

            //---- button1SenRoomMeg ----
            button1SenRoomMeg.setText("\u521b\u5efa");
            panel2Room.add(button1SenRoomMeg);
            button1SenRoomMeg.setBounds(new Rectangle(new Point(245, 215), button1SenRoomMeg.getPreferredSize()));

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel2Room.getComponentCount(); i++) {
                    Rectangle bounds = panel2Room.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel2Room.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel2Room.setMinimumSize(preferredSize);
                panel2Room.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel2Room);
        panel2Room.setBounds(10, 10, 485, 315);

        //======== panel3RoomView ========
        {
            panel3RoomView.setLayout(null);

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(table1RoomView);
            }
            panel3RoomView.add(scrollPane2);
            scrollPane2.setBounds(45, 15, 400, 230);

            //---- button1Ready ----
            button1Ready.setText("\u51c6\u5907");
            panel3RoomView.add(button1Ready);
            button1Ready.setBounds(new Rectangle(new Point(305, 260), button1Ready.getPreferredSize()));

            //---- button2Leave ----
            button2Leave.setText("\u79bb\u5f00\u623f\u95f4");
            panel3RoomView.add(button2Leave);
            button2Leave.setBounds(new Rectangle(new Point(90, 260), button2Leave.getPreferredSize()));
        }
        contentPane.add(panel3RoomView);
        panel3RoomView.setBounds(10, 10, 485, 315);

        contentPane.setPreferredSize(new Dimension(510, 370));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents


        panel3RoomView.setVisible(false);
        panel2Room.setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        button1BackToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                strtForm.setVisible(true);

            }
        });


        button1Re.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String meg = "3#";//刷新房间指令
                sendMegToOne(meg, out);

                System.out.println("当前登录昵称："+userName);
            }
        });
        button3Create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel2Room.setVisible(true);
                panel1View.setVisible(false);
                panel3RoomView.setVisible(false);
//                String meg = "3#";
//                sendMegToOne(meg, out);
            }
        });
        button1CancelCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel2Room.setVisible(false);
                panel3RoomView.setVisible(false);
                panel1View.setVisible(true);
                textField1RoomName.setText("");
            }
        });
        button1SenRoomMeg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String roomName = textField1RoomName.getText();
                roomNamePublic = roomName;
                String meg = "0#";//创建房间指令， 0、创建房间 | 房间名字 | 房主名字 |房间人数
                meg = meg + roomName + "#" + userName;
                sendMegToOne(meg, out);
                JOptionPane.showMessageDialog(null, "创建成功！");
                meg = "4#";
                meg = meg + roomName;
                sendMegToOne(meg, out);//刷新房间
                panel3RoomView.setVisible(true);
                panel2Room.setVisible(false);
                panel1View.setVisible(false);
//                meg = "3#";//刷新房间指令
//                sendMegToOne(meg, out);

//                panel1SetRoom.setVisible(false);
//                panel1View.setVisible(true);
            }
        });
        button2Insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = table1Room.getSelectedRow();//获取你选中的行号（记录）
                if (count < 0) {
                    JOptionPane.showMessageDialog(null, "没有选中房间哦！");
                } else {
                    String roomName = table1Room.getValueAt(count, 0).toString();//读取你获取行号的某一列的值（也就是字段）
                    System.out.println("加入房间：" + roomName);
                    roomNamePublic = roomName;
                    panel3RoomView.setVisible(true);
                    panel2Room.setVisible(false);
                    panel1View.setVisible(false);

                    String meg = "1#";//加入房间指令
                    meg = meg + roomName + "#" + userName;
                    sendMegToOne(meg, out);
                    meg = "4#";
                    meg = meg + roomName;
                    sendMegToOne(meg, out);//刷新房间
                }

            }
        });

        button2Leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String meg = "2#";//离开房间指令
                meg = meg + roomNamePublic + "#" + userName;
                sendMegToOne(meg, out);
//                meg = "4#";
//                meg = meg + roomNamePublic;
//                sendMegToOne(meg, out);//刷新房间
                panel3RoomView.setVisible(false);
                panel2Room.setVisible(false);
                panel1View.setVisible(true);

            }
        });

        button1Ready.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String meg = "5#";//准备指令
                meg = meg + roomNamePublic + "#" + userName;
                sendMegToOne(meg, out);
            }
        });
        String[][] room=null;
        refreshTable(room);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1View;
    private JScrollPane scrollPane1;
    private JTable table1Room;
    private JButton button1Re;
    private JButton button2Insert;
    private JButton button3Create;
    private JButton button1BackToHome;
    private JPanel panel2Room;
    private JButton button1CancelCreate;
    private JTextField textField1RoomName;
    private JLabel label1;
    private JButton button1SenRoomMeg;
    private JPanel panel3RoomView;
    private JScrollPane scrollPane2;
    private JTable table1RoomView;
    private JButton button1Ready;
    private JButton button2Leave;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private Object[][] data = null;
    private String[] head = {"房间名", "房间人数"};

    public Object[][] queryData(String[][] room) {
        if (room == null){
            data=null;
            return data;
        }
        data = new Object[room.length][head.length];
        for (int i = 0; i < room.length; i++) {
            data[i][0] = room[i][0];
            data[i][1] = room[i][1];
        }
        return data;
    }

    public void refreshTable(String[][] room) {

        DefaultTableModel tableModel = new DefaultTableModel(queryData(room), head) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1Room.setModel(tableModel);
    }

    private Object[][] dataView = null;
    private String[] headView = {"玩家名称", "状态"};

    public Object[][] queryDataView(String[][] room) {

        dataView = new Object[room.length][headView.length];
        for (int i = 0; i < room.length; i++) {
            dataView[i][0] = room[i][0];
            dataView[i][1] = room[i][1];
        }
        return dataView;
    }

    public void refreshTableView(String[][] room) {
        DefaultTableModel tableModel = new DefaultTableModel(queryDataView(room), headView) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1RoomView.setModel(tableModel);
    }

    public void sendMegToOne(String message, PrintWriter out) {
        out.println(message);
        out.flush();
    }


}
