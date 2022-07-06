package com.example.cvservice.repository;

import com.example.cvservice.model.CV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CvRepository extends JpaRepository<CV,Long> {
//    @Query("{ 'id' : ?0 }")
//    Optional<CV> findById(String id);
//
//    void deleteById(String id);
//
//    @Query("{ 'idJob' : ?0 }")
//    List<CV> findByIdJob(String id);
//
//    @Query("{'activate' : true}")
//    CV findDefaultCV(Boolean activate);
//
//    @Query("{'username': ?0}")
//    List<CV> loadCVOfUser(String username);
//
//    @Query("{'idUser': ?0}")
//    List<CV> loadCVById(Long id);
    @Query("select u from CV u where u.jobId = ?1")
    List<CV> findByJobId(Long id);
    @Query("select u from CV u where u.id = ?1")
    List<CV> loadCVById(Long id);
    @Query("select u from CV u where u.username = ?1")
    List<CV> loadCVOfUser(String username);


}
