package com.huangqq.storage;

import com.huangqq.storage.dao.mysql.SinaWeiboMysqlDao;
import com.huangqq.storage.service.mysql.ISinaWeiboMysqlService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by huangqq on 2017/8/12.
 */
public class MysqlTest extends BaseJunit4Test{

    @Autowired
    private SinaWeiboMysqlDao sinaWeiboMysqlDao;

    @Autowired
    private ISinaWeiboMysqlService sinaWeiboMysqlService;

    @Test   //标明是测试方法
    @Transactional   //标明此方法需使用事务
    @Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚
    public void testMysql(){
//        Integer num = sinaWeiboDao.count();
        Integer num = sinaWeiboMysqlService.getSinaWeiboCount();
        System.out.println(num);

    }

    public static void main(String[] args) {
        //String str = System.Text.Encoding.UTF8.GetString("\xe5\x8f\x91\xe7\x94\x9f\xe9\x94\x99\xe8\xaf\xaf\xef\xbc\x8c\xe8\xaf\xb7\xe9\x87\x8d\xe8\xaf\x95".ToArray().Select(t => Convert.ToByte(t)).ToArray());
        //System.tex
    }
}
