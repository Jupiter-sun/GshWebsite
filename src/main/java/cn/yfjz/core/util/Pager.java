package cn.yfjz.core.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwj on 15/7/18.
 */
public class Pager<T> implements Serializable {
    private static final long serialVersionUID = 8848523495013555357L;
    public static int DEFAULT_PAGE_SIZE = 20;
    public static int MAX_FETCH_SIZE = 200;
    private int pageSize;
    private int page = 1;
    private int total;
    private int records;
    private List<T> rows = new ArrayList();
    public Pager(){
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public Pager(int page, int pageSize){
        this.pageSize = pageSize;
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        if(this.total < 0) {
            this.total = (int) Math.ceil((double)this.records / (double)this.pageSize);
        }
        return total;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
        this.records = records > 0?records:0;
        this.total = (int) Math.ceil((double)records / (double)this.pageSize);
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getStart(){
        int start = (page - 1) * pageSize;
        if (start < 0) {
            start = 0;
        }
        return start;
    }

    public int getViewStart(){
        int start = (page - 1) * pageSize + 1;
        return start;
    }
    public int getViewEnd(){
        int start = (page - 1) * pageSize + 1;
        if(pageSize<this.getTotal()){
            return start+pageSize;
        }
        else{
            return this.records;
        }
    }
}
