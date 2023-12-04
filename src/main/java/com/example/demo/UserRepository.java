package com.example.demo;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import java.util.List;

public interface UserRepository extends DatastoreRepository<User, Long> {

    // Search for users based on Google ID
    List<User> findByGoogleId(String googleId);

    // Search for users based on game handle
    List<User> findByGameHandle(String gameHandle);

    // Search method based on name
    List<User> findByName(String name);
}
