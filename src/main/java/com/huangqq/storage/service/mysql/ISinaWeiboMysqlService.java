package com.huangqq.storage.service.mysql;

import com.huangqq.storage.po.SinaWeibo;

import java.util.List;

/**
 * Created by huangqq on 2017/8/10.
 */
public interface ISinaWeiboMysqlService {
    //获取单条微博数据
    public SinaWeibo getSinaWeibo(Integer id);
    //获取微博数据总量
    public int getSinaWeiboCount();
    //删除单条微博数据
    public void removeSinaWeibo(int id);
    //获取一页微博数据
    public List<SinaWeibo> getSinaWeiboPage(int start, int pageNum);
}
