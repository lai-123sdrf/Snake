package com.itheima;

import java.util.Random;

/**
 * 蛇由节点组成，每个食物都是一个节点
 */
public class  Node {
    private int x,y;//横纵坐标

    public Node(){

    }

    public Node(int x,int y){
        this.x=x;
        this.y=y;
    }
    public void SetX(int x){
            this.x=x;
    }
    public void SetY(int y){
        this.y=y;
    }
    public int GetX(){
    return x;
    }
    public int GetY(){
    return y;
    }

    //随机生成位置
    public void random(){
        //创建Random对象
        Random r=new Random();
        //随机生成x坐标
        this.x=r.nextInt(40);
        //随机生成y坐标
        this.y=r.nextInt(40);
    }
}
