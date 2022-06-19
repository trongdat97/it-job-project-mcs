package com.example.jobsuserservice.repository;

import com.example.jobsuserservice.model.JobUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobUserRepository extends JpaRepository<JobUser, Long> {
}
