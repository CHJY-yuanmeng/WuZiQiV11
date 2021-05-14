package love.yuanmengrobot.wuziqi.Util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

/*棋盘类*/
public class ChessBoard extends JPanel implements MouseListener, Runnable {

    //添加网络

    static Socket socket = null;
    PrintWriter outC;
    static String userName = "chjy";
    static String roomName = null;
    int killNum;
    int disNum;
    int bombNum;

    //玩家列表
    Map<String, Integer> allPlayName;


    private int currenPlay;//当前有权限下棋的玩家

    public int getCurrenPlay() {
        return currenPlay;
    }

    public void setCurrenPlay(int currenPlay) {
        this.currenPlay = currenPlay;
    }

    public Map<String, Integer> getAllPlayName() {
        return allPlayName;
    }

    public void setAllPlayName(Map<String, Integer> allPlayName) {
        this.allPlayName = allPlayName;
    }

    public int getKillNum() {
        return killNum;
    }

    public void setKillNum(int killNum) {
        this.killNum = killNum;
    }

    public int getDisNum() {
        return disNum;
    }

    public void setDisNum(int disNum) {
        this.disNum = disNum;
    }

    public int getBombNum() {
        return bombNum;
    }

    public void setBombNum(int bombNum) {
        this.bombNum = bombNum;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        ChessBoard.userName = userName;
    }

    public static String getRoomName() {
        return roomName;
    }

    public static void setRoomName(String roomName) {
        ChessBoard.roomName = roomName;
    }


    public static int MARGIN = 30;//边距
    public static int GRID_SPAN = 35;//网格间距
    public static int ROWS = 15;//棋盘行数
    public static int COLS = 15;//棋盘列数

    //当前棋子属性
    private int currentChessAttribute;//0表示正常颜色棋子、1表示杀棋、2表示变色棋、3表示炸棋
    //当前下棋玩家的棋子颜色
    private int currentChessColor;//0、1、2、3、4····
    //玩家棋子颜色
    private int playChessColor;//0、1、2、3、4····

    public int getPlayChessColor() {
        return playChessColor;
    }

    public void setPlayChessColor(int playChessColor) {
        this.playChessColor = playChessColor;
    }

    //颜色声明
    Color colorSen[] = new Color[7];
    String colorSenName[] = new String[7];


    public int getCurrentChessAttribute() {
        return currentChessAttribute;
    }

    public void setCurrentChessAttribute(int currentChessAttribute) {
        this.currentChessAttribute = currentChessAttribute;
    }

    public int getCurrentChessColor() {
        return currentChessColor;
    }

    public void setCurrentChessColor(int currentChessColor) {
        this.currentChessColor = currentChessColor;
    }

    public int getBlackTime() {
        return blackTime;
    }

    public void setBlackTime(int blackTime) {
        this.blackTime = blackTime;
    }
//    Point[] chessList=new Point[(ROWS+1)*(COLS+1)];//初始化每个数组元素为null

    List<Point> chessArrayList = new ArrayList<Point>();

    boolean isBack = true;//默认开始是黑棋先下
    boolean gameOver = false;//游戏是否结束
    int chessCount;//当前棋盘的棋子个数
    int xIndex, yIndex;//当前刚下棋子的索引
    int maxTime = 1800;//最长时间
    int blackTime = 15;//黑方时间
    int whiteTime = 1800;//白方时间
    String blackMessage = "";//倒计时显示
    String whiteMessage = "";
    Thread timer1 = new Thread(this);//倒计时线程
    Thread timer2 = new Thread(this);

    public List<Point> getChessArrayList() {
        return chessArrayList;
    }

    public void setChessArrayList(List<Point> chessArrayList) {
        this.chessArrayList = chessArrayList;
    }

    public ChessBoard(Socket soc) {
        System.out.println("************客户端:" + userName + "*************");
        try {
            socket = soc;
            System.out.println("棋盘已经连上服务器了");
            outC = new PrintWriter(socket.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
        //颜色
        colorSen[0] = new Color(254, 0, 0);
        colorSen[1] = new Color(253, 166, 0);
        colorSen[2] = new Color(255, 255, 1);
        colorSen[3] = new Color(0, 128, 1);
        colorSen[4] = new Color(63, 224, 208);
        colorSen[5] = new Color(64, 0, 151);
        colorSen[6] = new Color(148, 0, 212);
        colorSenName[0] = "红";
        colorSenName[1] = "橙";
        colorSenName[2] = "金";
        colorSenName[3] = "绿";
        colorSenName[4] = "蓝";
        colorSenName[5] = "靛";
        colorSenName[6] = "紫";

        currenPlay = -1;// 等于-1，当前无人可以开始下棋。
        setBackground(Color.LIGHT_GRAY);//设置背景颜色为 色
        addMouseListener(this);//添加事件监听器
        timer1.start(); //启动倒计时线程
        addMouseMotionListener(new MouseMotionListener() {//匿名内部类
            @Override
            public void mouseMoved(MouseEvent e) {
                int x1 = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
                int y1 = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;//将鼠标单击的坐标位置转化为网格索引
                if (x1 < 0 || x1 > ROWS || y1 < 0 || y1 > COLS || gameOver || findChess(x1, y1)) {//游戏已经结束，不能下；落在棋盘外，不能下；x，y位置已经有棋子存在，不能下
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//设置成默认形状
                } else {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));//设置成手型
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }
        });
    }

    /*绘制*/
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//画棋盘
        g.setFont(new Font("宋体", 1, 20));//设置字体
        if (currenPlay >= 0)
            g.drawString(colorSenName[currenPlay] + "方时间：" + blackMessage, 10, 20);
        else
            g.drawString("即将开始：" + blackMessage, 10, 20);
//        g.drawString("白方时间：" + whiteMessage, 260, 580);

        g.drawString("我方：" + colorSenName[playChessColor] + "色", 260, 580);
        for (int i = 0; i <= ROWS; i++) {//画横线
            g.drawLine(MARGIN, MARGIN + i * GRID_SPAN, MARGIN + COLS * GRID_SPAN, MARGIN + i * GRID_SPAN);
        }
        for (int i = 0; i <= COLS; i++) {//画直线
            g.drawLine(MARGIN + i * GRID_SPAN, MARGIN, MARGIN + i * GRID_SPAN, MARGIN + ROWS * GRID_SPAN);
        }
        /*画棋子*/
        int i = 0;
        for (Point p : chessArrayList) {
            int xPos = p.getX() * GRID_SPAN + MARGIN;//网格交叉的x坐标
            int yPos = p.getY() * GRID_SPAN + MARGIN;//网格交叉的y坐标
            g.setColor(p.getColor());//设置颜色
            g.fillOval(xPos - Point.DIAMETER / 2, yPos - Point.DIAMETER / 2, Point.DIAMETER, Point.DIAMETER);
            if (i == chessArrayList.size() - 1) {
                g.setColor(Color.RED);
                g.drawRect(xPos - Point.DIAMETER / 2, yPos - Point.DIAMETER / 2, Point.DIAMETER, Point.DIAMETER);
            }
            i++;
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {//鼠标按键在组件上按下时调用


        if (gameOver)//游戏已经结束，不能下
            return;

        System.out.println("当前下棋玩家序号：" + currenPlay);
        System.out.println("我的序号：" + allPlayName.get(userName));
        if (allPlayName.get(userName) != currenPlay) {
            System.out.println("当前还没到你下棋··");


        } else {


            if (currentChessAttribute == 0) {//当前选择原色棋子
                String colorName = isBack ? "黑方" : "白方";
                xIndex = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
                yIndex = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;//将鼠标单击的坐标位置转化为网格索引
                if (xIndex < 0 || xIndex > ROWS || yIndex < 0 || yIndex > COLS)//棋子落在棋盘外，不能下
                    return;
                if (findChess(xIndex, yIndex))//x,y位置已经有棋子存在，不能下
                    return;

                //7、下棋 | 房间名称 | 0，1，2，3（原棋还是道具） | x | y | color
                String Meg = null;
                Meg = "7#";
                Meg += roomName + "#";
                Meg += currentChessAttribute + "#";
                Meg += xIndex + "#";
                Meg += yIndex + "#";
                Meg += allPlayName.get(userName);
                try {
                    outC.println(Meg);
                    outC.flush();
                } catch (Exception er) {
                    er.printStackTrace();
                }

                Point ch = new Point(xIndex, yIndex, isBack ? Color.black : Color.white);
//        chessList[chessCount++]=ch;
                chessArrayList.add(ch);
                chessCount++;
                repaint();//通知系统重新绘制
//                if (isWin()) {
//                    String msg = String.format("恭喜，%s赢啦~", colorName);
//                    JOptionPane.showMessageDialog(this, msg);
//                    gameOver = true;
//                    timer1.stop();
//                } else if (chessCount == (COLS + 1) * (ROWS + 1)) {
//                    String msg = String.format("实力相当啊~");
//                    JOptionPane.showMessageDialog(this, msg);
//                    gameOver = true;
//                    timer1.stop();
//                }
                isBack = !isBack;

            } else if (currentChessAttribute == 1) {//当前选择杀棋
                //补充执行杀棋的代码


            } else if (currentChessAttribute == 2) {//当前选择变色棋
                //补充执行变色棋的代码


            } else if (currentChessAttribute == 3) {//当前选择炸棋
                //补充执行炸棋子的代码


            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {//鼠标按键在组件上单击(按下并释放)时调用
    }

    @Override
    public void mouseReleased(MouseEvent e) {////鼠标按键在组件上释放时调用
    }

    @Override
    public void mouseEntered(MouseEvent e) {//鼠标进入组件时调用
    }

    @Override
    public void mouseExited(MouseEvent e) {//鼠标离开组件时调用
    }

    private boolean findChess(int x, int y) {
        for (Point c : chessArrayList) {
            if (c != null && c.getX() == x && c.getY() == y)
                return true;
        }
        return false;
    }

    /*判断哪方赢*/
    private boolean isWin() {
        int continueCount = 1;//连续棋子的个数
        for (int x = xIndex - 1; x >= 0; x--) {//横向向左寻找
            Color c = isBack ? Color.black : Color.white;
            if (getChess(x, yIndex, c) != null) {
                continueCount++;
            } else
                break;
        }
        for (int x = xIndex + 1; x <= ROWS; x++) {//横向向右寻找
            Color c = isBack ? Color.black : Color.white;
            if (getChess(x, yIndex, c) != null) {
                continueCount++;
            } else
                break;
        }
        if (continueCount >= 5) {//判断记录数大于等于五，即表示此方获胜
            return true;
        } else
            continueCount = 1;
        for (int y = yIndex - 1; y >= 0; y--) {//纵向向上寻找
            Color c = isBack ? Color.black : Color.white;
            if (getChess(xIndex, y, c) != null) {
                continueCount++;
            } else
                break;
        }
        for (int y = yIndex + 1; y <= ROWS; y++) {//纵向向下寻找
            Color c = isBack ? Color.black : Color.white;
            if (getChess(xIndex, y, c) != null) {
                continueCount++;
            } else
                break;
        }
        if (continueCount >= 5) {//判断记录数大于等于五，即表示此方获胜
            return true;
        } else
            continueCount = 1;

        for (int x = xIndex + 1, y = yIndex - 1; y >= 0 && x <= COLS; x++, y--) {//右下寻找
            Color c = isBack ? Color.black : Color.white;
            if (getChess(x, y, c) != null) {
                continueCount++;
            } else
                break;
        }
        for (int x = xIndex - 1, y = yIndex + 1; y <= ROWS && x >= 0; x--, y++) {//左上寻找
            Color c = isBack ? Color.black : Color.white;
            if (getChess(x, y, c) != null) {
                continueCount++;
            } else
                break;
        }
        if (continueCount >= 5) {//判断记录数大于等于五，即表示此方获胜
            return true;
        } else
            continueCount = 1;

        for (int x = xIndex - 1, y = yIndex - 1; y >= 0 && x >= 0; x--, y--) {//左下寻找
            Color c = isBack ? Color.black : Color.white;
            if (getChess(x, y, c) != null) {
                continueCount++;
            } else
                break;
        }
        for (int x = xIndex + 1, y = yIndex + 1; y <= ROWS && x <= COLS; x++, y++) {//右上寻找
            Color c = isBack ? Color.black : Color.white;
            if (getChess(x, y, c) != null) {
                continueCount++;
            } else
                break;
        }
        if (continueCount >= 5) {//判断记录数大于等于五，即表示此方获胜
            return true;
        } else
            continueCount = 1;
        return false;
    }

    private Point getChess(int xIndex, int yIndex, Color color) {
        for (Point c : chessArrayList) {
            if (c != null && c.getX() == xIndex && c.getY() == yIndex && c.getColor() == color)
                return c;
        }
        return null;
    }

    public void restartGame() {//清除棋子
//        for(int i=0;i<chessArrayList.size();i++)
        chessArrayList = new ArrayList<Point>();
        /*恢复游戏相关的变量值*/
        isBack = true;
        gameOver = false;//游戏是否结束
        chessCount = 0;//当前棋盘的棋子个数
        repaint();
        blackTime = 1800;
        whiteTime = 1800;
        timer2.start();
    }

    public void goback() {//取消悔棋动作
//        if(chessCount==0)
//            return ;
//        chessList[chessCount-1]=null;
//        chessCount--;
//        if(chessCount>0){
//            xIndex=chessList[chessCount-1].getX();
//            yIndex=chessList[chessCount-1].getY();
//        }
//        isBack=!isBack;
//        repaint();
    }

    public Dimension getPreferredSize() {
        return new Dimension(MARGIN * 2 + GRID_SPAN * COLS, MARGIN * 2 + GRID_SPAN * ROWS);
    }

    public void run() {//重写run函数，进行倒计时
        synchronized (timer1) {
            if (maxTime > 0) {
                while (true) {
//                    if (isBack) {
//                        blackTime--;
//                        if (blackTime == 0) {
//                            JOptionPane.showMessageDialog(this, "黑方超时，游戏结束!");
//                            gameOver = true;
//                        }
//                    } else {
//                        whiteTime--;
//                        if (whiteTime == 0) {
//                            JOptionPane.showMessageDialog(this, "白方超时，游戏结束!");
//                            gameOver = true;
//                        }
//                    }


                    blackMessage = ":" + blackTime;
                    blackTime--;
                    if (blackTime == -1) blackTime = 15;
//                    whiteMessage = whiteTime / 3600 + ":" + (whiteTime / 60 - whiteTime / 3600 * 60) + ":" + (whiteTime - whiteTime / 60 * 60);
                    this.repaint();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void nextQi(String[] reList) {

    }
}
