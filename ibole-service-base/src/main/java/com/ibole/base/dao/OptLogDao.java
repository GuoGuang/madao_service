package com.ibole.base.dao;

import com.ibole.pojo.base.OptLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 操作日志
 **/

public interface OptLogDao extends JpaRepository<OptLog, String>, JpaSpecificationExecutor<OptLog> {


    @Modifying
    @Query("delete from OptLog where id in (:ids)")
    void deleteBatch(List<String> ids);
}
