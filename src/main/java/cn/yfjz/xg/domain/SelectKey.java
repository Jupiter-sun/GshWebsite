package cn.yfjz.xg.domain;

/**
 * Created by sibetech on 2015-2-2.
 */
public enum SelectKey {
    novalue,dept,role,roleStd,zzlb,process,zzfz,code,tableType,zxs,poorType,poorFund,user,manager,member,reportPerson,area,troubleType,active;
    public static SelectKey toKey(String str){
        try {
            return valueOf(str);
        }
        catch (Exception ex) {
            return novalue;
        }
    }
}
