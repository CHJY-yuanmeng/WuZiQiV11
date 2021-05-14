package love.yuanmengrobot.wuziqi.UI;
/*
 *   @Studio:CHJY · 源梦科技
 *   @Author:CHJY创幻
 *   @Ide:IntelliJ IDEA
 *   @Creation time:2021/5/12 10:23
 */

import love.yuanmengrobot.wuziqi.Util.Point;

import java.awt.*;

public class Test
{

   static Color colorSen;
    static Color colorSen2;
    public static void main(String[] args) {
        colorSen= new Color(254, 0, 0);
        colorSen2= new Color(6, 0, 0);
        Point p=new Point(0,0,colorSen);
        Point p2=new Point(0,0,colorSen2);
        String meg="";
        meg+=p.getColor();
        System.out.println(getColorNumber(p.getColor())+"");
        System.out.println(getColorNumber(p2.getColor())+"");



    }


    public static int getColorNumber(Color c){
        if(c==colorSen)return 0;
        if(c==colorSen2)return 1;
        return 1;
    }
}
