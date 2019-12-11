package com.ibole.base.dao;

import com.ibole.pojo.QuartzJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 任务调度
 **/

public interface JobDao extends JpaRepository<QuartzJob, String>, JpaSpecificationExecutor<QuartzJob> {

    @Modifying
    @Query("delete from QuartzJob where id in (:ids)")
    void deleteBatch(List<String> ids);
}
