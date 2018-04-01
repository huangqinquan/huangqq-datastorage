package com.huangqq.storage.dao.elasticsearch;

import com.huangqq.storage.po.SinaWeibo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by huangqq on 2017/8/13.
 * repository其实和dao是一个意思
 */
public interface SinaWeiboEsRepository extends ElasticsearchRepository<SinaWeibo, Long>{

//    /**
//     * 存单个微博对象到索引中
//     * @return
//     */
//    public boolean saveSinaWeibo();
}
