package com.itheima;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameWIN extends JFrame {
    private Snake snake;//蛇
    private Timer timer;//计时器，按时移动蛇
    private JPanel jPanel;//游戏棋盘
    private Node food;//初始化食物

    public GameWIN() throws HeadlessException{
        //初始化窗体参数
        intiFrame();
        //初始化游戏棋盘
        initGamepanel();
        //初始蛇
        initSnake();

        //初始化食物
        initFood();
        //初始化计时器
        initTimer();
        //设置键盘监听
        setKeyListener();
        
    }

    //初始化食物
    private void initFood(){
        food=new Node();
        food.random();
    }
    //设置键盘监听
    private void setKeyListener(){
        addKeyListener(new KeyAdapter(){
            //按下键盘时自动调用
            @Override
            public void keyPressed(KeyEvent e){
                
                //键盘中的键都有编号,getKeyCode()可以返回对应编号
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP://往上且不能往下
                    if(snake.getDirection()!=Direction.DOWN){
                    snake.setDirection(Direction.UP);
                    }
                        break;
                    case KeyEvent.VK_DOWN://往下且不能往上
                    if(snake.getDirection()!=Direction.UP){
                    snake.setDirection(Direction.DOWN);
                    }
                        break;
                    case KeyEvent.VK_LEFT://往左且不能往右
                    if(snake.getDirection()!=Direction.RIGHT){
                    snake.setDirection(Direction.LEFT);
                    }
                        break;
                    case KeyEvent.VK_RIGHT://往右且不能往左
                    if(snake.getDirection()!=Direction.LEFT){
                    snake.setDirection(Direction.RIGHT);
                    }
                        break;
                }
            }
        });
    }

    //初始化计时器
    private void initTimer(){
        //创建计时器对象
        timer=new Timer();

        //初始计时任务
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run(){
                snake.move();
                //判断蛇是否吃了食物
                Node hea=snake.GetBody().getFirst();
                System.out.print("蛇的位置：("+hea.GetX()+","+hea.GetY()+")  ");
                System.out.println("食物位置：("+food.GetX()+","+food.GetY()+")");
                if(hea.GetX()==food.GetX()&&hea.GetY()==food.GetY()){
                    snake.eat(food);
                    food.random();
                }
                //重绘游戏棋盘
                jPanel.repaint();
            }
        };

        //每次移动间隔100秒
        timer.scheduleAtFixedRate(timerTask, 0, 100);
    }

    private void initSnake(){
        snake=new Snake();
    }
    
    //初始化游戏棋盘
    private void initGamepanel(){
         
            

        jPanel=new JPanel(){
            //绘制棋盘中的内容
            @Override
            public void paint(Graphics g){
            //Graphics可用于绘制简单图形

            //清空棋盘
            g.clearRect(0, 0, 600, 600);

            //绘制40条横线
            for(int i=0;i<40;i++){
                g.drawLine(0, i*15, 600, i*15);
            }
            //绘制40条竖线
            for(int i=0;i<40;i++){
                g.drawLine(i*15,0,i*15,600);
            }
            //绘制蛇
            LinkedList<Node> body=snake.GetBody();
            for(Node node : body){
                g.fillRect(node.GetX()*15, node.GetY()*15, 15, 15);
            }

            //绘制食物
            g.fillRect(food.GetX()*15, food.GetY()*15, 15, 15);
            }
        };
        //将棋盘添加到窗体中
        add(jPanel);
    }

    //初始化窗体参数
    private void intiFrame(){
    //关闭按钮作用：退出程序
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //设置窗口大小
    this.setSize(600,640);//顶部占40
    //设置窗口大小不可变
    setResizable(false);
    //使窗口位置居中
    this.setLocationRelativeTo(null);
    //设置窗口标题
    this.setTitle("贪吃蛇");
}

public static void main(String [] args){
    //开始(创建窗体对象并显示)
    new GameWIN().setVisible(true);
}
}
