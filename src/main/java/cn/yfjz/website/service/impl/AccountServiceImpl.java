package cn.yfjz.website.service.impl;

import cn.yfjz.website.domain.Accounting;
import cn.yfjz.website.service.AccountService;
import com.avaje.ebean.EbeanServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by administrator on 2017/11/6.
 */
@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private EbeanServer ebeanServer;

    @Override
    public List<Accounting> queryAll() {
        return ebeanServer.find(Accounting.class).findList();
    }
}
