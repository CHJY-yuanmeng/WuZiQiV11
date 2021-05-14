package love.yuanmengrobot.wuziqi.Util;

import java.awt.Color;

public class Point {
    private int x;//棋子在棋盘中的x索引值
    private int y;//棋子在棋盘中的y索引值
    private Color color;//颜色
    public static int DIAMETER=30;//直径
    public Point(int x,int y,Color color){
        this.x=x;
        this.y=y;
        this.color=color;
    }
    public int getX(){  //得到棋子在棋盘中的x索引值
        return x;
    }
    public int getY(){  //得到棋子在棋盘中的y索引值
        return y;
    }
    public Color getColor(){ //得到棋子颜色
        return color;
    }
}  
