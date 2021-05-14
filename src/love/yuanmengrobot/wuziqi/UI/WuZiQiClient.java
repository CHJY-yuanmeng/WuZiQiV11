package love.yuanmengrobot.wuziqi.UI;/*
 * Created by JFormDesigner on Sun May 09 16:28:46 CST 2021
 */


import love.yuanmengrobot.wuziqi.UIlzl.Scoreboard;
import love.yuanmengrobot.wuziqi.UIyqh.MallForm;
import love.yuanmengrobot.wuziqi.Util.ChessBoard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;


/**
 * @author 1
 */
public class WuZiQiClient extends JFrame implements Runnable {
    public WuZiQiClient() {

//        colorSen[0] = new Color(254, 0, 0);
//        colorSen[1] = new Color(253, 166, 0);
//        colorSen[2] = new Color(255, 255, 1);
//        colorSen[3] = new Color(0, 128, 1);
//        colorSen[4] = new Color(63, 224, 208);
//        colorSen[5] = new Color(64, 0, 151);
//        colorSen[6] = new Color(148, 0, 212);
//        try {
//            socket = new Socket("127.0.0.1", 9999);
//            System.out.println("已经连上服务器了11");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        wuZiQiClient = new WuZiQiClient();
//        wuZiQiClient.readMeg.start();
//
//        startGame = new StartGame(socket);
//
//
//        wuZiQiClient.setVisible(false);
//        startGame.setVisible(true);
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panelProp = new JPanel();
        spinner1Kill = new JSpinner();
        spinner2Discolor = new JSpinner();
        spinner3Bomb = new JSpinner();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        label13 = new JLabel();
        label14 = new JLabel();
        button1AckProp = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
//        contentPane.setLayout(null);

        //======== panelProp ========
        {
            panelProp.setBackground(Color.white);
            panelProp.setLayout(null);

            //---- spinner1Kill ----
            spinner1Kill.setModel(new SpinnerNumberModel(0, 0, 3, 1));
            panelProp.add(spinner1Kill);
            spinner1Kill.setBounds(200, 225, 68, 34);

            //---- spinner2Discolor ----
            spinner2Discolor.setModel(new SpinnerNumberModel(0, 0, 3, 1));
            panelProp.add(spinner2Discolor);
            spinner2Discolor.setBounds(330, 225, 68, 34);

            //---- spinner3Bomb ----
            spinner3Bomb.setModel(new SpinnerNumberModel(0, 0, 1, 1));
            panelProp.add(spinner3Bomb);
            spinner3Bomb.setBounds(455, 225, 68, 34);

            //---- label4 ----
            label4.setText("\u6740\u68cb");
            panelProp.add(label4);
            label4.setBounds(new Rectangle(new Point(220, 265), label4.getPreferredSize()));

            //---- label5 ----
            label5.setText("\u4ece\u68cb\u76d8\u4e0a\u53bb\u6389");
            panelProp.add(label5);
            label5.setBounds(new Rectangle(new Point(200, 295), label5.getPreferredSize()));

            //---- label6 ----
            label6.setText("\u4efb\u610f\u4e00\u4e2a\u68cb\u5b50");
            panelProp.add(label6);
            label6.setBounds(new Rectangle(new Point(200, 325), label6.getPreferredSize()));

            //---- label7 ----
            label7.setText("\u6539\u53d8\u68cb\u76d8\u4e0a");
            panelProp.add(label7);
            label7.setBounds(330, 295, 72, 17);

            //---- label8 ----
            label8.setText("\u53d8\u8272\u68cb");
            panelProp.add(label8);
            label8.setBounds(345, 265, 45, 17);

            //---- label9 ----
            label9.setText("\u4efb\u610f\u4e00\u4e2a\u68cb\u5b50\u7684\u989c\u8272");
            panelProp.add(label9);
            label9.setBounds(310, 325, 130, 17);

            //---- label10 ----
            label10.setText("\u9009\u62e9\u4e00\u4e2a\u81ea\u5df1\u7684\u68cb\u5b50");
            panelProp.add(label10);
            label10.setBounds(450, 295, 135, 17);

            //---- label11 ----
            label11.setText("\u70b8\u68cb");
            panelProp.add(label11);
            label11.setBounds(480, 265, 55, 17);

            //---- label12 ----
            label12.setText("\u8ba9\u68cb\u5b50\u5468\u56f4\u4e00\u5708\u7684\u68cb\u5b50");
            panelProp.add(label12);
            label12.setBounds(445, 325, 160, 17);

            //---- label13 ----
            label13.setText("\u5305\u62ec\u81ea\u5df1\u7684\u68cb\u5b50");
            panelProp.add(label13);
            label13.setBounds(460, 375, 105, label13.getPreferredSize().height);

            //---- label14 ----
            label14.setText("\u4ece\u68cb\u76d8\u4e0a\u53bb\u6389");
            panelProp.add(label14);
            label14.setBounds(465, 350, 95, label14.getPreferredSize().height);

            //---- button1AckProp ----
            button1AckProp.setText("\u786e\u5b9a");
            panelProp.add(button1AckProp);
            button1AckProp.setBounds(new Rectangle(new Point(335, 390), button1AckProp.getPreferredSize()));
        }
        contentPane.add(panelProp);
        panelProp.setBounds(0, 0, 900, 700);

        contentPane.setPreferredSize(new Dimension(735, 555));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        chessBoard = new ChessBoard(socket);//初始化面板对象，创建和添加菜单
        MyItemListener lis = new MyItemListener();//初始化按钮事件监听器内部类

//        panelGame.add(chessBoard);
//        panelGame.setVisible(true);
        chessBoard.setOpaque(true);


        chessBoard.setLayout(new BorderLayout());

        add(chessBoard, BorderLayout.CENTER);//将面板对象添加到窗体上


        button1AckProp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int killNum = Integer.parseInt(spinner1Kill.getValue().toString());
                int disNum = Integer.parseInt(spinner2Discolor.getValue().toString());
                int bombNum = Integer.parseInt(spinner3Bomb.getValue().toString());
                chessBoard.setBombNum(bombNum);
                chessBoard.setDisNum(disNum);
                chessBoard.setKillNum(killNum);

                //添加使用道具的JDBC代码
//                userId、bombNum、disNum、killNum

                panelProp.setVisible(false);

            }
        });
        panelProp.setVisible(false);
        panelProp.setSize(900, 700);
        setSize(900, 700);
        setResizable(false);// 设置窗体不可以放大
//        setVisible(true);
        //      第30行  contentPane.setLayout(null);//该行不注释，棋盘不显示
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panelProp;
    private JSpinner spinner1Kill;
    private JSpinner spinner2Discolor;
    private JSpinner spinner3Bomb;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JButton button1AckProp;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    List<love.yuanmengrobot.wuziqi.Util.Point> chessArrayList;
    static Color colorSen[] = new Color[7];

    public ChessBoard chessBoard;//对战面板
    private Button startButton;//设置开始按钮
    private Button backButton;//设置悔棋按钮
    private Button exitButton;//设置退出按钮
    public static Socket socket;
    static StartGame startGame;
    static WuZiQiClient wuZiQiClient;
    static StrtForm strtForm;
    static Scoreboard scoreboard;
    static MallForm mallForm;





    Thread readMeg = new Thread(this);//倒计时线程


    private class MyItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();//获取事件源
            if (obj == startButton) {
                System.out.println("重新开始...");//重新开始
                chessBoard.restartGame();
            } else if (obj == exitButton) {
                System.exit(0);//结束应用程序
            } else if (obj == backButton) {
                System.out.println("悔棋...");//悔棋
                chessBoard.goback();
            }
        }
    }
//
    public static void main(String[] args) {

        colorSen[0] = new Color(254, 0, 0);
        colorSen[1] = new Color(253, 166, 0);
        colorSen[2] = new Color(255, 255, 1);
        colorSen[3] = new Color(0, 128, 1);
        colorSen[4] = new Color(63, 224, 208);
        colorSen[5] = new Color(64, 0, 151);
        colorSen[6] = new Color(148, 0, 212);
        try {
            socket = new Socket("127.0.0.1", 9999);
            System.out.println("客户端连上服务器了");
        } catch (IOException e) {
            e.printStackTrace();
        }

        wuZiQiClient = new WuZiQiClient();
        wuZiQiClient.readMeg.start();

        startGame = new StartGame(socket);
        strtForm=new StrtForm();
        scoreboard=new Scoreboard();
        mallForm=new MallForm();

//
//        startGame.setStrtForm(strtForm);//让开始游戏可以返回主页
//        scoreboard.setStrtForm(strtForm);//让排行榜可以返回主页
//        mallForm.setStrtForm(strtForm);//让商城可以返回主页
//
//
//        strtForm.setWuZiQiMain(wuZiQiClient);
//        strtForm.setStartGame(startGame);
//        strtForm.setScoreboard(scoreboard);
//        strtForm.setMallForm(mallForm);
//
//
//
//        strtForm.setVisible(true);//开始界面
//        wuZiQiClient.setVisible(false);
//        startGame.setVisible(false);
//        scoreboard.setVisible(false);





    }


    public void run() {//
        String[][] roomMeg = null;
        while (true) {
            try {

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String reSu = in.readLine();
                System.out.println(reSu);
                String[] reList = reSu.split("#");
                for (String ss : reList) {
                    System.out.println(ss + "-------------------");
                }

//                0、创建房间 | 房间名字 | 房主名字 |房间人数
//                1、加入房间 | 房间名字 | 玩家名字
//                2、离开房间 | 房间名字 | 玩家名字
//                3、刷新房间 | 房间名字 | 房间人数
//                4、刷新本房间 | 房间名字 | 房间人数 |房间玩家名字···
//                5、道具使用 |房间名字 | 玩家名称 | 玩家名称| 玩家名称```
//                6、指定玩家可以下棋 | 房间名字 | 玩家序号


                switch (Integer.parseInt(reList[0])) {

                    case 0:

                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3: //3、刷新房间 | 房间名字 | 房间人数


//                        String [][]roomMeg=new String[][];


                        if (reList.length == 1) {
                            roomMeg = null;
                            startGame.refreshTable(roomMeg);
                            JOptionPane.showMessageDialog(null, "没有人创建了房间哦！");
//                            startGame.
//                            roomMeg=null;
//                            startGame.refreshTable(roomMeg);
                        } else {
                            String room[] = reList[1].split("/");//分割房间消息


                            roomMeg = new String[room.length][2];

                            for (int iR = 0; iR < room.length; iR++) {
                                System.out.println("房间数据:" + room[iR] + " " + room.length);
                                String roomTem[] = room[iR].split("@");//分割房间名称、人数
                                roomMeg[iR][0] = roomTem[0];
                                roomMeg[iR][1] = roomTem[1];
                            }

                            startGame.refreshTable(roomMeg);
                        }
                        break;
                    case 4:
                        String roomView[] = reList[1].split("/");//分割房间消息
                        String[][] roomViewMeg = new String[roomView.length][2];

                        for (int iR = 0; iR < roomView.length; iR++) {
                            System.out.println("房间玩家数据:" + roomView[iR] + " " + roomView.length);
                            if (iR == roomView.length) break;
                            String roomTem[] = roomView[iR].split("@");//分割房间名称、人数
                            roomViewMeg[iR][0] = roomTem[0];
                            roomViewMeg[iR][1] = roomTem[1];
                        }

                        startGame.refreshTableView(roomViewMeg);
                        break;
                    case 5://
                        String allName[] = reList[2].split("/");
                        Map<String, Integer> mapTem = new HashMap<String, Integer>();
                        for (int i = 0; i < allName.length; i++) {
                            mapTem.put(allName[i], i);
                            System.out.println("入局玩家名称：" + allName[i]);
                        }
                        chessBoard.setAllPlayName(mapTem);//把玩家名称添加到棋盘

                        //开始选择道具
                        startGame.setVisible(false);
                        wuZiQiClient.setVisible(true);
                        chessBoard.setRoomName(reList[1]);//
                        chessBoard.setUserName(startGame.getUserName());
                        chessBoard.setPlayChessColor(mapTem.get(startGame.getUserName()));

                        chessBoard.setBlackTime(15);

                        break;
                    case 6://更新持手
                        chessBoard.setCurrentChessColor(Integer.parseInt(reList[2]));//设置当前下棋的颜色
                        chessBoard.setCurrenPlay(Integer.parseInt(reList[2]));//设置当前可用下棋的玩家
                        chessBoard.setBlackTime(15);


                        break;

                    case 7://刷新棋盘
                        love.yuanmengrobot.wuziqi.Util.Point point = null;
                        String[] plist = reList[3].split("@");
                        chessArrayList = new ArrayList<love.yuanmengrobot.wuziqi.Util.Point>();
                        for (String p : plist) {
                            System.out.println("第一次分割结果：" + p);
                            String pp[] = p.split("/");
                            point = new love.yuanmengrobot.wuziqi.Util.Point(Integer.parseInt(pp[0]), Integer.parseInt(pp[1]), colorSen[Integer.parseInt(pp[2])]);
                            chessArrayList.add(point);
                        }
                        chessBoard.setCurrenPlay(Integer.parseInt(reList[2]));//设置当前可用下棋的玩家
                        chessBoard.setBlackTime(15);
                        chessBoard.setChessArrayList(chessArrayList);
                        break;

                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}

