package com.example.demo;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GameRecordRepository extends DatastoreRepository<GameRecord, Long> {

    // Search for game records by a specific Google ID

    List<GameRecord> findByGoogleId(String googleId);


    //Page<GameRecord> findByGoogleIdOrderByDateDesc(String googleId, Pageable pageable);
    // Search for game records by a specific Google ID without pagination
    List<GameRecord> findByGoogleIdOrderByDateDesc(String googleId);

    Page<GameRecord> findAllByOrderByScoreDesc(Pageable pageable);

    void deleteById(Long id);

    void deleteByGoogleId(String googleId);


}
