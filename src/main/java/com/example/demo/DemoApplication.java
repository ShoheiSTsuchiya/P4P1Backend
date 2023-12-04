package com.example.demo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import java.util.ArrayList;
import java.util.List;

import java.util.List;
import java.util.Date;

@SpringBootApplication
@ShellComponent
public class DemoApplication {
    @Autowired
    UserRepository userRepository;
    @Autowired
    GameRecordRepository gameRecordRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // Shell method to save a game record
    @ShellMethod("Saves a game record: save-game-record <googleId> <score>")
    public String saveGameRecord(String googleId, int score) {
        GameRecord gameRecord = new GameRecord();
        gameRecord.setGoogleId(googleId);
        gameRecord.setScore(score);
        gameRecord.setDate(new Date());
        gameRecordRepository.save(gameRecord);
        return "Game record saved successfully.";
    }

    // Shell method to update a user's game handle
    @ShellMethod("Updates a user's game handle: update-handle <userId> <newHandle>")
    public String updateUserGameHandle(String googleId, String newHandle) {
        List<User> users = userRepository.findByGoogleId(googleId);
        if (users.isEmpty()) {
            return "User not found.";
        }
        User user = users.get(0); // 最初のユーザーを取得
        user.setGameHandle(newHandle);
        userRepository.save(user);
        return "User handle updated successfully.";
    }


    // Shell method to fetch all game records for a user
    @ShellMethod("Fetches all game records for a user: fetch-records <googleId>")
    public String fetchUserGameRecords(String googleId) {
        List<GameRecord> records = gameRecordRepository.findByGoogleIdOrderByDateDesc(googleId);

        StringBuilder sb = new StringBuilder();
        for (GameRecord record : records) {
            sb.append("Score: ").append(record.getScore())
                    .append(", Date: ").append(record.getDate().toString())
                    .append("\n");
        }
        return sb.toString();
    }




    // Shell method to delete a game record
    @ShellMethod("Deletes a game record: delete-record <recordId>")
    public String deleteGameRecord(Long recordId) {
        if (gameRecordRepository.existsById(recordId)) {
            gameRecordRepository.deleteById(recordId);
            return "Game record deleted successfully.";
        } else {
            return "Game record not found.";
        }
    }


//for user manage

    @ShellMethod("Saves a user: save-user <googleId> <gameHandle> <name>")
    public String saveUser(String googleId, String gameHandle, String name) {
        User user = new User();
        user.setGoogleId(googleId);
        user.setGameHandle(gameHandle);
        user.setName(name);

        try {
            User savedUser = userRepository.save(user);
            return "User saved successfully with ID: " + savedUser.getId();
        } catch (Exception e) {
            System.err.println("Error saving user: " + e.getMessage());
            return "Error saving user";
        }
    }

    @ShellMethod("Fetches a user by Google ID: fetch-user <googleId>")
    public String fetchUserByGoogleId(String googleId) {
        List<User> users = userRepository.findByGoogleId(googleId);
        if (users.isEmpty()) {
            return "User not found.";
        }
        return users.get(0).toString();
    }

    @ShellMethod("Deletes a user: delete-user <googleId>")
    public String deleteUser(String googleId) {
        List<User> users = userRepository.findByGoogleId(googleId);
        if (users.isEmpty()) {
            return "User not found.";
        }
        userRepository.delete(users.get(0));
        return "User deleted successfully.";
    }


}
