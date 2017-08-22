package com.craft.rms.modules.sys.model;

import java.util.Date;

/**
 *
 * This class corresponds to the database table sys_users
 * @author pengpei
 */
public class SysUsers {
    /**
     * Database Column Remarks:
     *   用户的id
     *
     * This field corresponds to the database column sys_users.user_id
     */
    private Integer userId;

    /**
     * Database Column Remarks:
     *   用户名称
     *
     * This field corresponds to the database column sys_users.user_name
     */
    private String userName;

    /**
     * Database Column Remarks:
     *   登录名称
     *
     * This field corresponds to the database column sys_users.user_login_name
     */
    private String userLoginName;

    /**
     * Database Column Remarks:
     *   登录密码
     *
     * This field corresponds to the database column sys_users.user_password
     */
    private String userPassword;

    /**
     * Database Column Remarks:
     *   用户手机号码
     *
     * This field corresponds to the database column sys_users.user_phone
     */
    private String userPhone;

    /**
     *
     * This field corresponds to the database column sys_users.user_email
     */
    private String userEmail;

    /**
     *
     * This field corresponds to the database column sys_users.user_token
     */
    private String userToken;

    /**
     *
     * This field corresponds to the database column sys_users.user_type_cd
     */
    private String userTypeCd;

    /**
     * Database Column Remarks:
     *   用户状态
     *
     * This field corresponds to the database column sys_users.user_status_cd
     */
    private String userStatusCd;

    /**
     * Database Column Remarks:
     *   创建人
     *
     * This field corresponds to the database column sys_users.creat_id
     */
    private Integer creatId;

    /**
     * Database Column Remarks:
     *   创建日期
     *
     * This field corresponds to the database column sys_users.creat_dt
     */
    private Date creatDt;

    /**
     *
     * This field corresponds to the database column sys_users.modify_id
     */
    private Integer modifyId;

    /**
     *
     * This field corresponds to the database column sys_users.modify_dt
     */
    private Date modifyDt;

    /**
     * Database Column Remarks:
     *   有效日期
     *
     * This field corresponds to the database column sys_users.valid_dt
     */
    private Date validDt;

    /**
     * Database Column Remarks:
     *   是否是系统管理员（1是，0为普通用户）
     *
     * This field corresponds to the database column sys_users.user_manager
     */
    private String userManager;

    /**
     * Database Column Remarks:
     *   备注
     *
     * This field corresponds to the database column sys_users.comments
     */
    private String comments;

    /**
     * This method returns the value of the database column sys_users.user_id
     *
     *   <br/>用户的id
     * @return the value of sys_users.user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method sets the value of the database column sys_users.user_id
     *
     *   <br/>用户的id
     * @param userId the value for sys_users.user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method returns the value of the database column sys_users.user_name
     *
     *   <br/>用户名称
     * @return the value of sys_users.user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method sets the value of the database column sys_users.user_name
     *
     *   <br/>用户名称
     * @param userName the value for sys_users.user_name
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method returns the value of the database column sys_users.user_login_name
     *
     *   <br/>登录名称
     * @return the value of sys_users.user_login_name
     */
    public String getUserLoginName() {
        return userLoginName;
    }

    /**
     * This method sets the value of the database column sys_users.user_login_name
     *
     *   <br/>登录名称
     * @param userLoginName the value for sys_users.user_login_name
     */
    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName == null ? null : userLoginName.trim();
    }

    /**
     * This method returns the value of the database column sys_users.user_password
     *
     *   <br/>登录密码
     * @return the value of sys_users.user_password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * This method sets the value of the database column sys_users.user_password
     *
     *   <br/>登录密码
     * @param userPassword the value for sys_users.user_password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    /**
     * This method returns the value of the database column sys_users.user_phone
     *
     *   <br/>用户手机号码
     * @return the value of sys_users.user_phone
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * This method sets the value of the database column sys_users.user_phone
     *
     *   <br/>用户手机号码
     * @param userPhone the value for sys_users.user_phone
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    /**
     * This method returns the value of the database column sys_users.user_email
     *
     * @return the value of sys_users.user_email
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * This method sets the value of the database column sys_users.user_email
     *
     * @param userEmail the value for sys_users.user_email
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    /**
     * This method returns the value of the database column sys_users.user_token
     *
     * @return the value of sys_users.user_token
     */
    public String getUserToken() {
        return userToken;
    }

    /**
     * This method sets the value of the database column sys_users.user_token
     *
     * @param userToken the value for sys_users.user_token
     */
    public void setUserToken(String userToken) {
        this.userToken = userToken == null ? null : userToken.trim();
    }

    /**
     * This method returns the value of the database column sys_users.user_type_cd
     *
     * @return the value of sys_users.user_type_cd
     */
    public String getUserTypeCd() {
        return userTypeCd;
    }

    /**
     * This method sets the value of the database column sys_users.user_type_cd
     *
     * @param userTypeCd the value for sys_users.user_type_cd
     */
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd == null ? null : userTypeCd.trim();
    }

    /**
     * This method returns the value of the database column sys_users.user_status_cd
     *
     *   <br/>用户状态
     * @return the value of sys_users.user_status_cd
     */
    public String getUserStatusCd() {
        return userStatusCd;
    }

    /**
     * This method sets the value of the database column sys_users.user_status_cd
     *
     *   <br/>用户状态
     * @param userStatusCd the value for sys_users.user_status_cd
     */
    public void setUserStatusCd(String userStatusCd) {
        this.userStatusCd = userStatusCd == null ? null : userStatusCd.trim();
    }

    /**
     * This method returns the value of the database column sys_users.creat_id
     *
     *   <br/>创建人
     * @return the value of sys_users.creat_id
     */
    public Integer getCreatId() {
        return creatId;
    }

    /**
     * This method sets the value of the database column sys_users.creat_id
     *
     *   <br/>创建人
     * @param creatId the value for sys_users.creat_id
     */
    public void setCreatId(Integer creatId) {
        this.creatId = creatId;
    }

    /**
     * This method returns the value of the database column sys_users.creat_dt
     *
     *   <br/>创建日期
     * @return the value of sys_users.creat_dt
     */
    public Date getCreatDt() {
        return creatDt;
    }

    /**
     * This method sets the value of the database column sys_users.creat_dt
     *
     *   <br/>创建日期
     * @param creatDt the value for sys_users.creat_dt
     */
    public void setCreatDt(Date creatDt) {
        this.creatDt = creatDt;
    }

    /**
     * This method returns the value of the database column sys_users.modify_id
     *
     * @return the value of sys_users.modify_id
     */
    public Integer getModifyId() {
        return modifyId;
    }

    /**
     * This method sets the value of the database column sys_users.modify_id
     *
     * @param modifyId the value for sys_users.modify_id
     */
    public void setModifyId(Integer modifyId) {
        this.modifyId = modifyId;
    }

    /**
     * This method returns the value of the database column sys_users.modify_dt
     *
     * @return the value of sys_users.modify_dt
     */
    public Date getModifyDt() {
        return modifyDt;
    }

    /**
     * This method sets the value of the database column sys_users.modify_dt
     *
     * @param modifyDt the value for sys_users.modify_dt
     */
    public void setModifyDt(Date modifyDt) {
        this.modifyDt = modifyDt;
    }

    /**
     * This method returns the value of the database column sys_users.valid_dt
     *
     *   <br/>有效日期
     * @return the value of sys_users.valid_dt
     */
    public Date getValidDt() {
        return validDt;
    }

    /**
     * This method sets the value of the database column sys_users.valid_dt
     *
     *   <br/>有效日期
     * @param validDt the value for sys_users.valid_dt
     */
    public void setValidDt(Date validDt) {
        this.validDt = validDt;
    }

    /**
     * This method returns the value of the database column sys_users.user_manager
     *
     *   <br/>是否是系统管理员（1是，0为普通用户）
     * @return the value of sys_users.user_manager
     */
    public String getUserManager() {
        return userManager;
    }

    /**
     * This method sets the value of the database column sys_users.user_manager
     *
     *   <br/>是否是系统管理员（1是，0为普通用户）
     * @param userManager the value for sys_users.user_manager
     */
    public void setUserManager(String userManager) {
        this.userManager = userManager == null ? null : userManager.trim();
    }

    /**
     * This method returns the value of the database column sys_users.comments
     *
     *   <br/>备注
     * @return the value of sys_users.comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * This method sets the value of the database column sys_users.comments
     *
     *   <br/>备注
     * @param comments the value for sys_users.comments
     */
    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }
}