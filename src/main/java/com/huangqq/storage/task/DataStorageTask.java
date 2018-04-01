package com.huangqq.storage.task;

import com.huangqq.storage.po.SinaWeibo;
import com.huangqq.storage.service.elasticsearch.ISinaWeiboEsService;
import com.huangqq.storage.util.SpringContextUtil;

import java.util.List;

/**
 * Created by huangqq on 2017/8/13.
 */
public class DataStorageTask implements Runnable{

    private List<SinaWeibo> sinaWeiboList;

    public DataStorageTask(List<SinaWeibo> sinaWeiboList){
        this.sinaWeiboList = sinaWeiboList;
    }

    @Override
    public void run() {
        //获取服务
        ISinaWeiboEsService sinaWeiboEsService = (ISinaWeiboEsService) SpringContextUtil.getBean("sinaWeiboEsServiceImpl");

        if (null == sinaWeiboList){
            return;
        }
        //将数据入到索引中
        Integer id = sinaWeiboEsService.saveSinaWeiboList(sinaWeiboList);
        System.out.println("一批数据入库 startid=" + id);
    }
}
