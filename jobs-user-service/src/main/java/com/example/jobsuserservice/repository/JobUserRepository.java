package com.example.jobsuserservice.repository;

import com.example.jobsuserservice.model.JobUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface JobUserRepository extends JpaRepository<JobUser,Long> {
    @Query("select u from JobUser u where  u.id = ?1")
    JobUser loadById(Long id);

    @Query("select u from JobUser u where  u.jobId = ?1")
    List<JobUser> getCvByJobId(String id);

}
