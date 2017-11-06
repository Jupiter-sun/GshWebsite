package cn.yfjz.website.service.impl;

import cn.yfjz.website.domain.Evaluate;
import cn.yfjz.website.service.EvaluateService;
import com.avaje.ebean.EbeanServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by administrator on 2017/11/6.
 */
@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private EbeanServer ebeanServer;
    @Override
    public List<Evaluate> queryAll() {
        return ebeanServer.find(Evaluate.class).findList();
    }
}
