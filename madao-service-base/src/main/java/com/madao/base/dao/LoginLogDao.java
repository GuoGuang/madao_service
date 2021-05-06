package com.madao.base.dao;

import com.madao.model.pojo.base.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public interface LoginLogDao extends JpaRepository<LoginLog, String>, JpaSpecificationExecutor<LoginLog> {
    @Modifying
    @Query("delete from LoginLog where id in (:ids)")
    void deleteBatch(@Param("ids") List<String> ids);

}
