package com.woniu.show.util;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.*;


// 上下课闹钟
public class MyClock {

    public MyClock() {
        Timer timer = new Timer();
        // 早上9点20上课
        timer.scheduleAtFixedRate(new MyTask(9,20,false), new Date(), 1000*60);
        // 中午12点20下课
        timer.scheduleAtFixedRate(new MyTask(12,20,false), new Date(), 1000*60);
        // 下午2点上课
        timer.scheduleAtFixedRate(new MyTask(14,0,false), new Date(), 1000*60);
        // 晚上6点下课
        timer.scheduleAtFixedRate(new MyTask(18,0,false), new Date(), 1000*60);

        System.out.println("-------------------------上下课闹钟，正在后台执行-------------------------");
    }

    // 手动设置一个打铃时间
    public MyClock(int hh,int mm){
        System.out.println("新建了一个打铃时间");
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTask(hh,mm,true), new Date(), 1000);
    }

//    public static void main(String[] args) {
//        new MyClock(17,31);
//
//    }
}

// 定时任务,这是一个线程
class MyTask extends TimerTask {
    private final int setHH;
    private final int setMM;
    private Boolean diyTime = false;
    public MyTask(int setHH,int setMM,Boolean diyTime){
        this.setHH = setHH;
        this.setMM = setMM;
        this.diyTime = diyTime;
    }
    @Override
    public void run() {
        // 获取系统时间
        GregorianCalendar calendar = new GregorianCalendar();
        int HH = calendar.get(Calendar.HOUR_OF_DAY);
        int MM = calendar.get(Calendar.MINUTE);
        int SS = calendar.get(Calendar.SECOND);
        // 对比设定的时间和系统时间
        if(setHH==HH && setMM==MM && SS==0){
            playMp3();
            playMp3();
            playMp3();
            playMp3();
            System.out.println(setHH+"时"+setMM+"分的闹钟已响");
            // 用于手动打铃，闹钟完成后取消这个线程
            if (diyTime){
                System.out.println("已经有一个手动设置的闹钟结束了");
                cancel();
            }
        }
    }

    // 播放下课铃声
    public static void playMp3(){
        try {
            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream("1.mp3"));
            Player player = new Player(buffer);
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}