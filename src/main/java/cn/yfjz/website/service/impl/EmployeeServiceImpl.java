package cn.yfjz.website.service.impl;

import cn.yfjz.website.domain.Employee;
import cn.yfjz.website.service.EmployeeService;
import com.avaje.ebean.EbeanServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by administrator on 2017/11/6.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EbeanServer ebeanServer;
    @Override
    public List<Employee> queryAll() {
        return ebeanServer.find(Employee.class).findList();
    }
}
