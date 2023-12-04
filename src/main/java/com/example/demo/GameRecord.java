package com.example.demo;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;
import java.util.Date;

@Entity(name = "game_records")
public class GameRecord {
    @Id
    Long id;
    String googleId;  // identify by google id
    int score;
    private Date date;  // date of play game
    private String phrase; // the phrase for the game
    private int wrongGuesses; // number of wrong guesses
    private boolean success; // whether the game was successful

    // Full constructor
    public GameRecord(Long id, String googleId, int score, Date date, String phrase, int wrongGuesses, boolean success) {
        this.id = id;
        this.googleId = googleId;
        this.score = score;
        this.date = date;
        this.phrase = phrase;
        this.wrongGuesses = wrongGuesses;
        this.success = success;
    }

    // Default constructor
    public GameRecord() {}

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
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public int getWrongGuesses() {
        return wrongGuesses;
    }

    public void setWrongGuesses(int wrongGuesses) {
        this.wrongGuesses = wrongGuesses;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    // toString method
    @Override
    public String toString() {
        return "GameRecord{" +
                "id=" + id +
                ", googleId='" + googleId + '\'' +
                ", score=" + score +
                ", date=" + date +
                ", phrase='" + phrase + '\'' +
                ", wrongGuesses=" + wrongGuesses +
                ", success=" + success +
                '}';
    }
}
