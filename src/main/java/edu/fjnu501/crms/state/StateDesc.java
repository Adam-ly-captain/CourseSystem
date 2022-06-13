package edu.fjnu501.crms.state;

public enum StateDesc {

    ADD_SUCCESS("添加成功"), ADD_FAILED("添加失败"),
    LOGIN_SUCCESS("登录成功"), LOGIN_FAILED("登录失败"),
    DELETE_SUCCESS("删除成功"), DELETE_FAILED("删除失败"),
    UPDATE_SUCCESS("修改成功"), UPDATE_FAILED("修改失败"),
    SELECT_SUCCESS("查询成功"), SELECT_FAILED("查询失败"),
    PAGE_SUCCESS("获取分页数据成功"), PAGE_FAILED("获取分页数据失败"),
    TIME_CONFLICT("课程时间与其他课程冲突"), CLASSROOM_CONFLICT("教室已被其他课程占用"),
    COURSE_LIMITED("该课程人数已达上限"), NOT_SELECT("未选择过该课程"),
    REGISTER_SUCCESS("注册成功"), REGISTER_FAILED("注册失败"),
    UNAUTHORIZED("权限不足"), NOT_LOGIN("未登录"),
    COURSE_NAME_CONFLICT("课程名重复"),
    CLASSROOM_REPEATED("教室名重复"), CLASSROOM_NAME_NULL("教室名不能为空"), CLASSROOM_EXIST_DELETE("已有课程设置使用该教室名，无法删除"),
    SEARCH_SUCCESS("查找成功"),SEARCH_FAILED("查找失败"),
    WEEKDAY_ERROR("不存在该星期日");

    private String desc;

    StateDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
