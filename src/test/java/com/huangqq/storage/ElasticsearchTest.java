package com.huangqq.storage;

import com.huangqq.storage.po.SinaWeibo;
import com.huangqq.storage.service.mysql.ISinaWeiboMysqlService;
import com.huangqq.storage.service.elasticsearch.ISinaWeiboEsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by huangqq on 2017/8/13.
 */
public class ElasticsearchTest extends BaseJunit4Test{

    @Autowired
    private ISinaWeiboMysqlService sinaWeiboMysqlService;

    @Autowired
    private ISinaWeiboEsService sinaWeiboEsService;

    @Test   //标明是测试方法
    @Transactional   //标明此方法需使用事务
    @Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚
    public void testInsertIntoEs(){
        //查出前100条数据
        List<SinaWeibo> sinaWeiboList = sinaWeiboMysqlService.getSinaWeiboPage(0, 100);
        System.out.println(sinaWeiboList.size());
//        for (SinaWeibo sinaWeibo : sinaWeiboList) {
//            Integer id = sinaWeiboEsService.saveSinaWeibo(sinaWeibo);
//            System.out.println(id);
//        }
        Integer id = sinaWeiboEsService.saveSinaWeiboList(sinaWeiboList);
        System.out.println(id);
    }
}
