package cn.yfjz.core.security.utils;

import cn.yfjz.core.sys.domain.Role;

/**
 * Created by sibetech on 2015-1-14.
 */
public class RoleUtil {
    //
    public static final String SYS_SCHOOL = "SYS_SCHOOL";
    //
    public static final String SYS_DEPT = "SYS_DEPT";
    //
    public static final String SYS_FDY = "SYS_FDY";
    //
    public static final String SYS_BZR = "SYS_BZR";

    public static final String SYS_BJZL = "SYS_BJZL";
    //
    public static final String SYS_STUDENT = "SYS_STUDENT";
    
    public static final String SYS_CEPING_BZR = "SYS_CEPING_BZR";

    public static final String SYS_CEPING_CPXZ = "SYS_CEPING_CPXZ";
    
    //是否是学工处管理员
    public static boolean isSysadmin(){
        Role role = UserAuthUtils.getCurrentRole();
        return role!=null && SYS_SCHOOL.equals(role.getCode());
    }

    public static boolean isDeptAdmin(){
        Role role = UserAuthUtils.getCurrentRole();
        return role!=null && SYS_DEPT.equals(role.getCode());
    }
    //是否是辅导员
    public static boolean isFdy() {
        Role role = UserAuthUtils.getCurrentRole();
        return role!=null && SYS_FDY.equals(role.getCode());
    }
    //是否是班主任
    public static boolean isBzr() {
        Role role = UserAuthUtils.getCurrentRole();
        return role!=null && SYS_BZR.equals(role.getCode());
    }
    //是否是班级助理
    public static boolean isBjzl() {
        Role role = UserAuthUtils.getCurrentRole();
        return role!=null && SYS_BJZL.equals(role.getCode());
    }

    //是否是学生
    public static boolean isStudent() {
        Role role = UserAuthUtils.getCurrentRole();
        return role!=null && SYS_STUDENT.equals(role.getCode());
    }
    
    //是否是测评班主任
    public static boolean isCepingBzr(){
    	Role role = UserAuthUtils.getCurrentRole();
    	return role!=null&& SYS_CEPING_BZR.equals(role.getCode());
    }
    //是否是测评小组
    public static boolean isCepingXz(){
        Role role = UserAuthUtils.getCurrentRole();
        return role!=null&& SYS_CEPING_CPXZ.equals(role.getCode());
    }


    public static String getStudentScopeSql() {
        if(isDeptAdmin()){
            return "";
        }else if(isBzr()) {
            return "";
        }else if(isCepingXz()){
            return " and exists( select 1 from ceping_student student where exists(" +
                    "select 1 from ceping_class_manager cpxz where cpxz.class_id=student.c_id and cpxz.user_id="+UserAuthUtils.getCurrentUser().getId()+")" +
                    " and student.student_id=studentId)";
        }else{
            return "";
        }
    }
}
