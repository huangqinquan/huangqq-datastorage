package com.huangqq.storage.main;

import com.huangqq.storage.po.SinaWeibo;
import com.huangqq.storage.service.mysql.ISinaWeiboMysqlService;
import com.huangqq.storage.task.DataStorageTask;
import com.huangqq.storage.util.SpringContextUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangqq on 2017/8/10.
 * 入库程序启动入口
 */
public class Main {

    public static void main(String[] args) {
        //启动spring容器
        String springXmlPath = "classpath:spring/*.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(springXmlPath);
        ISinaWeiboMysqlService sinaWeiboService = (ISinaWeiboMysqlService) SpringContextUtil.getBean("sinaWeiboService");

//        List<SinaWeibo> sinaWeiboList = sinaWeiboService.getSinaWeiboPage(1000000, 10000);
//        System.out.println(sinaWeiboList.size());

        //查询mysql单页页数
        Integer pageNum = 10000;
        //游标
        Integer start = 0;
        //先查出数据库共有多少条数据
        Integer num = sinaWeiboService.getSinaWeiboCount();
        //配置线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        //每次去数据库取10000条数据出来进行入库
        while (start < num){
            List<SinaWeibo> sinaWeiboList = sinaWeiboService.getSinaWeiboPage(start, pageNum);
            //这里准备异步入库数据
            DataStorageTask task = new DataStorageTask(sinaWeiboList);
            executor.execute(task);

            start += pageNum;
            System.out.println("start = " + start + " sinaWeiboList.size = " + sinaWeiboList.size());
        }
        executor.shutdown();//只是不能再提交新任务，等待执行的任务不受影响

        try {
            //等待线程池工作结束
            boolean loop = true;
            do {    //等待所有任务完成
                loop = !executor.awaitTermination(2, TimeUnit.SECONDS);  //阻塞，直到线程池里所有任务结束
            } while(loop);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("所有数据入索引完毕!");
    }
}
