package cn.yfjz.website.service.impl;

import cn.yfjz.website.domain.Article;
import cn.yfjz.website.service.ArticleService;
import com.avaje.ebean.EbeanServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by administrator on 2017/11/6.
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private EbeanServer ebeanServer;

    @Override
    public List<Article> queryAll() {
        return ebeanServer.find(Article.class).findList();
    }
}
