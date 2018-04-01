package com.huangqq.storage.service.elasticsearch;

import com.huangqq.storage.po.SinaWeibo;

import java.util.List;

/**
 * Created by huangqq on 2017/8/13.
 */
public interface ISinaWeiboEsService {

    /**
     * 新增微博信息
     *返回插入数据的id
     * @param sinaWeibo
     * @return
     */
    public Integer saveSinaWeibo(SinaWeibo sinaWeibo);

    /**
     * 批量索引数据
     * 返回插入数据的第一条的id
     * @param sinaWeiboList
     */
    public Integer saveSinaWeiboList(List<SinaWeibo> sinaWeiboList);
}
