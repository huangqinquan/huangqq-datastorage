package com.huangqq.storage.po;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by huangqq on 2017/8/9.
 */
@Data
@Document(indexName = "datasetindex", type = "sinaWeibo")
public class SinaWeibo implements Serializable{

    private static final long serialVersionUID = -1L;

    public Integer id;
    public String uid;
    public String mid;
    public Date time;
    public Long forward_count;
    public Long comment_count;
    public Long like_count;
    public String content;



}
