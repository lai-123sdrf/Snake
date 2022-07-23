package com.itheima;

import java.util.LinkedList;

/**
 * 一条蛇有多个节点组成，这些节点由LinkedList集合储存，蛇出生时有两个节点
 */
public class Snake {
    //蛇的身体
    private LinkedList<Node> body;
    //蛇的移动，默认向右
    private Direction direction=Direction.RIGHT;
    //蛇是否活着
    private boolean isLiving=true;
    //创建蛇的对象时执行
    public Snake(){
        //初始化蛇的身体
        initSnakebody();
    }

    //蛇沿蛇头方向的移动
    public void move(){
        if(isLiving){
            //获取蛇头
            Node head=body.getFirst();
            switch(direction){
            case UP:
                //在蛇头上方添加一个节点
                body.addFirst(new Node(head.GetX(), head.GetY()-1));
                break;
            case DOWN:
                body.addFirst(new Node(head.GetX(), head.GetY()+1));
                break;
            case LEFT:
                body.addFirst(new Node(head.GetX()-1, head.GetY()));
                break;
            case RIGHT:
                body.addFirst(new Node(head.GetX()+1, head.GetY())); 
                break;
            }
            //删除最后的节点
            body.removeLast();

            //判断蛇是否撞墙
            head=body.getFirst();
            if(head.GetX()<0||head.GetY()<0||head.GetX()>=39||head.GetY()>=39){
                isLiving=false;
            }
            
            //判断蛇是否碰到自己
            for(int i=1;i<body.size();i++){
                Node node=body.get(i);
                if(head.GetX()==node.GetX()&&head.GetY()==node.GetY()){
                    isLiving=false;
                }
            }
        } else{
            //System.exit(0);
        }
    }

    public void initSnakebody(){
        body=new LinkedList<>();
        //创建两个节点并添加到集合中
        body.add(new Node(1,1));
        body.add(new Node(2,1));
    }

    public LinkedList<Node> GetBody(){
        return body;
    }

    public void  SetBody(LinkedList<Node> body){
        this.body=body;
    }

    public Direction getDirection(){
        return direction;
    }

    public void setDirection(Direction direction){
        this.direction=direction;
    }

    //吃食物
    public void eat(Node food) {
        //获取蛇头
        Node head=body.getFirst();
        switch(direction){
        case UP:
            //在蛇头上方添加一个节点
            body.addFirst(new Node(head.GetX(), head.GetY()-1));
            break;
        case DOWN:
            body.addFirst(new Node(head.GetX(), head.GetY()+1));
            break;
        case LEFT:
            body.addFirst(new Node(head.GetX()-1, head.GetY()));
            break;
        case RIGHT:
            body.addFirst(new Node(head.GetX()+1, head.GetY())); 
            break;
        }
    }
}
