package com.example.demo;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

@Entity(name = "users")
public class User {
  @Id
  Long id;
  String googleId;
  String gameHandle;
  String name;

  public User() {
    // Keep the contents of the empty constructor. Modify as needed.
    this.googleId = "";
    this.gameHandle = "";
    this.name = "";
  }

  public User(Long id, String googleId, String gameHandle, String name) {
    this.id = id;
    this.googleId = googleId;
    this.gameHandle = gameHandle;
    this.name = name;
  }

  // Getters and setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getGoogleId() {
    return googleId;
  }

  public void setGoogleId(String googleId) {
    this.googleId = googleId;
    // Set the Google ID as the game handle only if it is not already set
    if (this.gameHandle == null || this.gameHandle.isEmpty()) {
      this.gameHandle = googleId;
    }
  }

  public String getGameHandle() {
    return gameHandle;
  }

  public void setGameHandle(String gameHandle) {
    this.gameHandle = gameHandle;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // toString method for debugging
  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", googleId='" + googleId + '\'' +
            ", gameHandle='" + gameHandle + '\'' +
            ", name='" + name + '\'' +
            '}';
  }
}
