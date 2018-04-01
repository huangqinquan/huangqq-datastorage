package com.huangqq.storage.dao.mysql;

import com.huangqq.storage.po.SinaWeibo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huangqq on 2017/8/10.
 */

@Repository
public interface SinaWeiboMysqlDao {
    /**
     * 通过id来查询一条数据
     * @param id
     * @return
     */
    public SinaWeibo findById(Integer id);

    /**
     * 查询表总数据量
     * @return
     */
//    @Select("select count(*) from sina_weibo")
    public int count();

    /**
     * 按id去删除数据
     * @param id
     */
    public void deleteById(int id);

    /**
     * @param start 分页起始数
     * @param pageNum   单页数据量
     * @return
     */
    public List<SinaWeibo> findByPage(int start, int pageNum);
}
