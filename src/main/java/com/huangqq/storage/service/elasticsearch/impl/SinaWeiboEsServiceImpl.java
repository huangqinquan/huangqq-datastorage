package com.huangqq.storage.service.elasticsearch.impl;

import com.huangqq.storage.dao.elasticsearch.SinaWeiboEsRepository;
import com.huangqq.storage.po.SinaWeibo;
import com.huangqq.storage.service.elasticsearch.ISinaWeiboEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangqq on 2017/8/13.
 */
@Service
public class SinaWeiboEsServiceImpl implements ISinaWeiboEsService {

    @Autowired
    SinaWeiboEsRepository sinaWeiboEsRepository;

    @Override
    public Integer saveSinaWeibo(SinaWeibo sinaWeibo) {
        SinaWeibo sinaWeiboResult = sinaWeiboEsRepository.save(sinaWeibo);
        return sinaWeiboResult.getId();
    }

    @Override
    public Integer saveSinaWeiboList(List<SinaWeibo> sinaWeiboList) {
        Iterable it = sinaWeiboEsRepository.save(sinaWeiboList);
        SinaWeibo sinaWeibo = (SinaWeibo) it.iterator().next();
        return sinaWeibo.getId();
    }
}
