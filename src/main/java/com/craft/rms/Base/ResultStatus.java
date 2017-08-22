package com.craft.rms.Base;

/**
 * 操作（添加、修改、删除）状态的定义：0为操作失败；-1为操作出现异常；1为操作成功；其它操作状态为-99
 */
public interface ResultStatus {
    /** 操作失败的标志 （小于0皆为失败）*/
    int FAIL = 0;
    /** 操作成功的标志（大于等于1皆为成功） */
    int SUCCESS = 1;
    /** 操作异常的标志 */
    int EX = -1;
    /** 操作其它状态的标志 */
    int OTHER = -99;
    
    /** 增加信息操作提示信息. */
    String SAVE = "保存";
    /** 增加信息操作成功后的通用提示信息. */
    String SUCCES_SAVE = "保存成功";
    /** 增加信息操作失败后的通用提示信息. */
    String FAIL_SAVE = "保存失败";
    /** 修改操作提示信息. */
    String UPDATE = "修改";
    /** 修改操作成功后的通用提示信息. */
    String SUCCES_UPDATE = "修改成功";
    /** 修改操作失败后的通用提示信息. */
    String FAIL_UPDATE = "修改失败";
    /** 删除操作提示信息. */
    String DEL = "删除";
    /** 删除操作成功后的通用提示信息. */
    String SUCCES_DEL = "删除成功";
    /** 删除操作失败后的通用提示信息. */
    String FAIL_DEL = "删除失败";
    /** 操作出现异常后的通用提示信息. */
    String EX_MSG = "系统异常，请稍后再试！管理员电话：";
    /** 操作失败的状态. */
    String FALSE = "false";
    /** 操作成功的状态. */
    String TRUE = "true";
}
