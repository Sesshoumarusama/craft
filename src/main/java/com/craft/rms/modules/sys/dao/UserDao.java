package com.craft.rms.modules.sys.dao;

import com.craft.rms.modules.sys.model.SysUsers;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    /**
     * This method corresponds to the database table sys_users
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * This method corresponds to the database table sys_users
     */
    int insert(SysUsers record);

    /**
     * This method corresponds to the database table sys_users
     */
    int insertSelective(SysUsers record);

    /**
     * This method corresponds to the database table sys_users
     */
    SysUsers selectByPrimaryKey(Integer userId);

    /**
     * This method corresponds to the database table sys_users
     */
    int updateByPrimaryKeySelective(SysUsers record);

    /**
     * This method corresponds to the database table sys_users
     */
    int updateByPrimaryKey(SysUsers record);

    List<SysUsers> selectByCondition(SysUsers sysUsers);

    SysUsers getByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);
}