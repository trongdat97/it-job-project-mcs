package com.example.cvservice.repository;

import com.example.cvservice.model.CV;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CvRepository extends MongoRepository<CV,String> {
    @Query("{ 'id' : ?0 }")
    Optional<CV> findById(String id);

    void deleteById(String id);

    @Query("{ 'idJob' : ?0 }")
    List<CV> findByIdJob(String id);

    @Query("{'activate' : true}")
    CV findDefaultCV(Boolean activate);

    @Query("{'username': ?0}")
    List<CV> loadCVOfUser(String username);

    @Query("{'idUser': ?0}")
    List<CV> loadCVById(Long id);

}
