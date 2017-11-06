package cn.yfjz.xg.common.service.impl;


import cn.yfjz.xg.common.service.Demoservice;
import cn.yfjz.xg.domain.Demo;

import com.avaje.ebean.EbeanServer;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 杨茗心 on 2016/10/28.
 */
@Service
public class DemoServiceImpl implements Demoservice {

    @Autowired
    private EbeanServer ebeanServer;


    @Override
    public void add(Demo demo) {
        ebeanServer.insert(demo);
    }

    @Override
    public void edit(Demo demo) {
        ebeanServer.update(demo);
    }
   // 获得execl表格的所有内容
    @Override
    public List<Demo> getAllByExecl(String file) {
        List<Demo> list = new ArrayList<Demo>();
        try {
            Workbook rwb = Workbook.getWorkbook(new File(file));
            Sheet rs = rwb.getSheet(0);
            int cols = rs.getColumns();//得到所有的列
            int rows = rs.getRows();//得到所有的行
            System.out.println(cols + rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    String ld = rs.getCell(j++, i).getContents();
                    String lc = rs.getCell(j++, i).getContents();
                    String fjh = rs.getCell(j++, i).getContents();
                    String zj = rs.getCell(j++, i).getContents();
                    String xm = rs.getCell(j++, i).getContents();
                    String xb = rs.getCell(j++, i).getContents();
                    String starttime = rs.getCell(j++, i).getContents();
                    String endtime = rs.getCell(j++, i).getContents();
                    String tel = rs.getCell(j++, i).getContents();
                    String zjh = rs.getCell(j++, i).getContents();
                    String fzjz = rs.getCell(j++, i).getContents();
                    String bz = rs.getCell(j++, i).getContents();
                    list.add(new Demo(ld, lc, fjh, zj, xm, xb, starttime, endtime, tel, zjh, fzjz, bz));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Demo> getAllDemo() {
        List list = new ArrayList();
        try {
            String sql = "select * from p_demo";
            list = ebeanServer.createSqlQuery(sql).findList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean isExist(String xm) {
        List list = ebeanServer.find(Demo.class).where().eq("xm", xm).findList();
        return list.isEmpty();
    }

    @Override
    public Demo findByxm(String xm) {
        List<Demo> list = ebeanServer.find(Demo.class).where().eq("xm", xm).findList();
        if(list !=null&& list.size()==1){
            return list.get(0);
        }
        return null;
    }


}
