package com.huangqq.storage.service.mysql.impl;

import com.huangqq.storage.dao.mysql.SinaWeiboMysqlDao;
import com.huangqq.storage.po.SinaWeibo;
import com.huangqq.storage.service.mysql.ISinaWeiboMysqlService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by huangqq on 2017/8/10.
 */
@Service("sinaWeiboService")
public class SinaWeiboMysqlServiceImpl implements ISinaWeiboMysqlService {

    @Resource
    private SinaWeiboMysqlDao sinaWeiboDao;

    @Override
    public SinaWeibo getSinaWeibo(Integer id){
        return sinaWeiboDao.findById(id);
    }

    @Override
    public int getSinaWeiboCount() {
        return sinaWeiboDao.count();
    }

    @Override
    public void removeSinaWeibo(int id) {
        sinaWeiboDao.deleteById(id);
    }

    @Override
    public List<SinaWeibo> getSinaWeiboPage(int start, int pageNum) {
        return sinaWeiboDao.findByPage(start, pageNum);
    }


}
