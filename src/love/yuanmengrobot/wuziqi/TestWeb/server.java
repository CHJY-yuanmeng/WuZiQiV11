package love.yuanmengrobot.wuziqi.TestWeb;

import love.yuanmengrobot.wuziqi.Util.Point;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.List;

import static java.lang.Thread.sleep;

public class server implements Runnable {// 服务端
    static List<Socket> socketList = new ArrayList<Socket>();
    // 读取 In
    static Socket socket = null;
    static ServerSocket serverSocket = null;
    static List<PrintWriter> allOut = null;
    //开局之前的map
    static Map<String, List<PrintWriter>> map = new HashMap<String, List<PrintWriter>>();//String是房间名字，List是房间内玩家的out流
    static Map<String, List<String>> mapName = new HashMap<String, List<String>>();//String是房间名字，List是房间内玩家的名字
    static Map<String, String> mapNameTai = new HashMap<String, String>();//String是房间名字，String是房间内玩家的状态
    //开局之后的map
    static Map<String, List<PrintWriter>> mapStartGameOut = new HashMap<String, List<PrintWriter>>();//String是房间名字，List是房间内的玩家的out流
    static Map<String, List<String>> mapRoomPlayName = new HashMap<String, List<String>>();//String是房间名字，List是房间内玩家的名字
    static Map<String, List<Point>> mapRoomChessList = new HashMap<String, List<Point>>();//String是房间名字，List是房间内的棋盘

    static Map<String, Thread> mapGameThread = new HashMap<String, Thread>();//String是房间名字，List是房间内的棋盘


    static int userCol;
    static int userCount;
    static int roomSum;
    Point[] chessList = new Point[16 * 16];//初始化每个数组元素为null
    static Color colorSen[] = new Color[7];

    public server() {// 构造方法
        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("************服务端*************");
        server t = new server();
        userCount = 0;
        colorSen[0] = new Color(254, 0, 0);
        colorSen[1] = new Color(253, 166, 0);
        colorSen[2] = new Color(255, 255, 1);
        colorSen[3] = new Color(0, 128, 1);
        colorSen[4] = new Color(63, 224, 208);
        colorSen[5] = new Color(64, 0, 151);
        colorSen[6] = new Color(148, 0, 212);
        while (true) {
            try {
//              System.out.println("端口9999等待被连接......");
                socket = serverSocket.accept();
                userCount++;
                System.out.println("第" + userCount + "个客户已连接");
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                socketList.add(socket);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//            Print p = new Print(socket);
            Thread read = new Thread(t);
//            Thread print = new Thread(p);
            read.start();
            System.out.println("产生线程--：" + read.getName());
//            print.start();
        }
    }

    public void addUserColor() {

        userCol++;
        if (userCol >= userCount) userCol = 0;
    }


    @Override
    public void run() {


        // 重写run方法
        PrintWriter out = null;

        try {
//            Thread.sleep(1000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            int roomSumTem = 0;
            String userNameTem = "99";
            List<PrintWriter> allOutTem = new LinkedList<PrintWriter>();
            List<String> allNameTem = new LinkedList<String>();
            out = new PrintWriter(this.socket.getOutputStream());
//            System.out.println(out.toString() + "为out");

            String Meg = null;
            while (true) {
                String jieshou = in.readLine();
                String reList[] = jieshou.split("#");
                System.out.print("指令：");
                for (String s : reList) {
                    System.out.print(s + " | ");
                }
                System.out.println("");
                String[][] roomMeg;
                switch (Integer.parseInt(reList[0])) {
                    case 0:
                        roomSumTem = roomSum;

                        addRoom(reList[1], allOutTem, reList[1], allNameTem);//创建房间，输入参数：房间名字、房间输出流、房主名称
                        addOut(map.get(reList[1]), out, mapName.get(reList[1]), reList[2]);//加入房间，输入房间流list，玩家输出流，房主名称加入列表
                        mapNameTai.put(reList[2], "等待中.");
                        break;

                    case 1:
                        System.out.println(reList[1] + "*********");
                        addOut(map.get(reList[1]), out, mapName.get(reList[1]), reList[2]);//加入房间，输入房间流list，玩家输出流
                        mapNameTai.put(reList[2], "等待中.");
                        //刷新其他房间信息。
//                        break;

                    case 4:
                        Meg = "4#";
                        String[][] roomViewMeg = allRoomUser(reList[1]);//
                        for (int i = 0; i < roomViewMeg.length; i++)//打包数据
                        {
                            Meg = Meg + roomViewMeg[i][0] + "@";//房间名字与人数分割
                            Meg = Meg + roomViewMeg[i][1];//
                            if (i + 1 < roomViewMeg.length)
                                Meg += "/";//玩家之间用/分割
                        }
                        sendMessage(Meg, map.get(reList[1]));//发送数据包
                        break;
                    case 2:
                        int userSum = removeOut(out, reList[1], reList[2]);//离开房间
                        if (userSum == 0) {//房间消失，告知其他正在浏览房间的玩家
                            roomMeg = allRoom();//
                            Meg = "3#";//刷新客户端-房间列表
                            for (int i = 0; i < roomMeg.length; i++)//打包数据
                            {
                                System.out.println("dea");
                                Meg = Meg + roomMeg[i][0] + "@";//房间名字与人数用@分割
                                Meg = Meg + roomMeg[i][1];//
                                if (i + 1 < roomMeg.length)
                                    Meg += "/";//房间与房间之间用/分割
                            }
                            sendMegToAll(Meg);//发送数据包
                            break;
                        } else {
                            Meg = "4#";//刷新客户端-房间内部消息
                            roomViewMeg = allRoomUser(reList[1]);//
                            for (int i = 0; i < roomViewMeg.length; i++)//打包数据
                            {
                                Meg = Meg + roomViewMeg[i][0] + "@";//玩家名字与状态分割
                                Meg = Meg + roomViewMeg[i][1];//
                                if (i + 1 < roomViewMeg.length)
                                    Meg += "/";//玩家之间用/分割
                            }
                            sendMessage(Meg, map.get(reList[1]));//发送数据包
                        }
                        break;
                    case 3:
                        roomMeg = allRoom();//
                        Meg = "3#";
                        for (int i = 0; i < roomMeg.length; i++)//打包数据
                        {
                            System.out.println("dea");
                            Meg = Meg + roomMeg[i][0] + "@";//房间名字与人数用@分割
                            Meg = Meg + roomMeg[i][1];//
                            if (i + 1 < roomMeg.length)
                                Meg += "/";//房间与房间之间用/分割
                        }
//                        if(roomMeg.length==0) Meg +="/0";
                        System.out.println("刷新客户端房间列表");
                        sendMegToOne(Meg, out);//发送数据包
                        break;
                    case 5:
                        mapNameTai.put(reList[2], "准备!");//该玩家发起准备
                        Meg = "4#";
                        roomViewMeg = allRoomUser(reList[1]);//
                        for (int i = 0; i < roomViewMeg.length; i++)//打包数据
                        {
                            Meg = Meg + roomViewMeg[i][0] + "@";//玩家名字与状态分割
                            Meg = Meg + roomViewMeg[i][1];//
                            if (i + 1 < roomViewMeg.length)
                                Meg += "/";//玩家之间用/分割
                        }
                        sendMessage(Meg, map.get(reList[1]));//发送数据包
                    {//判断房间内的玩家是否全员准备（>=2人）
                        List<String> lit = mapName.get(reList[1]);
                        int readyCount = 0;
                        for (String listAll : lit) {
                            String outRe = mapNameTai.get(listAll);
//                            System.out.println("该场内的玩家："+listAll+" "+outRe+" "+lit.size());
                            if (outRe.equals("准备!")) readyCount++;
                        }
                        if (readyCount >= 2 && readyCount == lit.size()) {
                            System.out.println("足够人员准备!");
                            //将房间移动到另一个场地(开局map)
                            mapStartGameOut.put(reList[1], map.get(reList[1]));
                            mapRoomPlayName.put(reList[1], mapName.get(reList[1]));
                            //删除开局之前的存储
                            removeRoom(reList[1]);
                            //为房间添加棋盘
                            mapRoomChessList.put(reList[1], new ArrayList<Point>());
                            //告知客户端开始准备道具的指令
                            Meg = "5#";
                            Meg += reList[1] + "#";
                            List<String> listN = mapRoomPlayName.get(reList[1]);
                            for (String li : listN) {
                                Meg = Meg + li + "/";//玩家名字用/分开
                            }
                            sendMessage(Meg, mapStartGameOut.get(reList[1]));//发送数据包，包含指令5、房间名称、房间内全玩家名称

                            //开启线程处理游戏时间
                            GameStart p = new GameStart(reList[1], mapStartGameOut.get(reList[1]), mapRoomPlayName.get(reList[1]));
                            Thread gameStart = new Thread(p);
                            gameStart.start();
                            mapGameThread.put(reList[1], gameStart);//将线程放入
                            System.out.println("产生线程：" + gameStart.getName());


                        }
                    }
                    break;
                    case 6:

                        Meg = "6#";//告诉客户端轮到哪位玩家下棋。
                        Meg += reList[1];
                        sendMessage(Meg, map.get(reList[1]));//发送数据包
                        break;
                    case 7:
//                        List<Point> lip = ;
                        switch (Integer.parseInt(reList[2])) {
                            case 0://玩家下了普通棋子
                                Point pTem = new Point(Integer.parseInt(reList[3]), Integer.parseInt(reList[4]), colorSen[Integer.parseInt(reList[5])]);
                                mapRoomChessList.get(reList[1]).add(pTem);//加入棋子
                                System.out.println("玩家下了普通棋子");
                                break;
                            case 1://杀棋

                                break;
                            case 2://变色棋子

                                break;
                            case 3://炸棋

                                break;

                        }

                        int next = Integer.parseInt(reList[5]) + 1;
                        if(next==mapRoomPlayName.get(reList[1]).size())
                            next=0;
                        Meg = "7#";//告诉客户端最新棋盘什么样子。
                        Meg += reList[1] + "#";
                        Meg += next + "#";
                        int i = 0;
                        for (Point p : mapRoomChessList.get(reList[1])) {
                            Meg += p.getX() + "/";
                            Meg += p.getY() + "/";
                            Meg += getColorNumber(p.getColor());
                            i++;
                            if (i != mapRoomChessList.get(reList[1]).size())
                                Meg += "@";
                        }
                        synchronized (mapStartGameOut.get(reList[1])) {
                            System.out.println("开始唤醒计时线程" + mapGameThread.get(reList[1]).getName());
                            mapStartGameOut.get(reList[1]).notify();
                            System.out.println("唤醒计时线程结束");
                        }
                        sendMessage(Meg, mapStartGameOut.get(reList[1]));//发送数据包

                        //开始唤醒计时线程

                        break;

                }
//                server端收到的消息模板
           /*
                0、创建房间 | 房间名字 | 房主名字 |房间人数
                1、加入房间 | 房间名字 | 玩家名字
                2、离开房间 | 房间名字 | 玩家名字
                3、刷新房间 | 房间名字 | 房间人数
                4、刷新本房间 | 房间名字 | 房间 人数 |房间玩家名字···
                5、准备 | 房间名字 | 玩家名字 | 玩家名字 | 玩家名字```
                6、指定玩家可以下棋 | 房间名字 | 玩家名字 | x | y
                7、玩家下了棋了 | 房间名字 | 玩家名字 | x | y
                 */


            }
        } catch (IOException e) {
            System.out.println("客户端断开连接......");
            //处理掉线，待补充
            {


            }







//            e.printStackTrace();
        }
//        removeUser(out);

    }

    public String[][] allRoom() {//查找所有房间信息

        int roomSum = map.size();
        String[][] roomMeg = new String[roomSum][2];
        int i = 0;
        for (String key : map.keySet()) {

            roomMeg[i][0] = key;//房间名字
            roomMeg[i][1] = String.valueOf(map.get(key).size());//房间人数
            System.out.println("查询到的房间：" + key + " 人数：" + roomMeg[i][1]);
            i++;
        }

        return roomMeg;

    }

    public String[][] allRoomUser(String roomName) {//查找房间内部信息


        List<String> allName = mapName.get(roomName);

        String[][] roomViewMeg = new String[allName.size()][2];
        int i = 0;
        for (String name : allName) {
            System.out.println(i + "玩家名称：" + name);
            roomViewMeg[i][0] = name;
            roomViewMeg[i][1] = mapNameTai.get(name);
            i++;
        }


        return roomViewMeg;

    }


    private synchronized void addOut(List<PrintWriter> allOut, PrintWriter out, List<String> allName, String playName) {
        allOut.add(out);
        System.out.println(playName + "^^^^^^^");
        allName.add(playName);

    }

    private synchronized void addRoom(String roomName, List<PrintWriter> allOut, String name, List<String> list) {

        map.put(roomName, allOut);//房间名称、房间流
        mapName.put(name, list);//房间名称、房间全员名称
    }


    private synchronized int removeOut(PrintWriter out, String roomName, String playName) {

        List<PrintWriter> allOut = map.get(roomName);
        List<String> allName = mapName.get(roomName);
        allOut.remove(out);
        allName.remove(playName);
        if (allOut.size() == 0) {//房间人数为0，删除房间
            removeRoom(roomName);
            System.out.println("删除房间：" + roomName);
            return 0;
        }
        return 1;
    }

    private synchronized void removeRoom(String roomName) {
        map.remove(roomName);
        mapName.remove(roomName);
    }

    /**
     * 将给定的消息发送给所有客户端
     *
     * @param
     */
    private synchronized void sendMessage(String message, List<PrintWriter> allOutTem) {//房间内消息互发
        for (PrintWriter out : allOutTem) {
            out.println(message);
            out.flush();
        }
    }

    public void sendMegToAll(String msg) {
        try {
            for (int i = 0; i < socketList.size(); i++) {
                Socket socket = socketList.get(i);
                PrintWriter out = null;

                out = new PrintWriter(socket.getOutputStream());

                out.println(msg);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendMegToOne(String message, PrintWriter out) {
        out.println(message);
        out.flush();
    }

    public int getColorNumber(Color c) {
        if (c == colorSen[0]) return 0;
        if (c == colorSen[1]) return 1;
        if (c == colorSen[2]) return 2;
        if (c == colorSen[3]) return 3;
        if (c == colorSen[4]) return 4;
        if (c == colorSen[5]) return 5;
        if (c == colorSen[6]) return 6;

        return 6;
    }


    class GameStart implements Runnable {


        private String roomGName;
        private List<PrintWriter> printGWriters;//全玩家输出流
        private List<String> listG;//全玩家名称
//

        public GameStart(String roomName, List<PrintWriter> printWriters, List<String> list) {
            roomGName = roomName;
            printGWriters = printWriters;
            listG = list;

        }

        @Override
        public void run() {//计时线程
            synchronized (mapStartGameOut.get(roomGName)) {
                try {

                    Thread.sleep(16000);//等待15秒让玩家选择道具
                    System.out.println("等待15秒让玩家选择道具");
                    int currenPlay = 0;
                    while (true) {


                        String msg = null;

                        msg = "6#";
                        msg += roomGName + "#";
                        msg += currenPlay + "";

                        System.out.println("对客户端说：" + msg);
//                int i=0;
                        for (PrintWriter pout : printGWriters) {
                            pout.println(msg);
                            pout.flush();
                        }
                        currenPlay++;
                        if (currenPlay == printGWriters.size()) currenPlay = 0;

                        mapStartGameOut.get(roomGName).wait(16000);  //每个玩家有15s下棋时间
                        System.out.println("等待15秒让玩家下棋");
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        }
    }
}

///////////////////////////////////////////////////////////////////
//////////