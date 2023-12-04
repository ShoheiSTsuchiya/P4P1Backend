package com.example.demo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class GameRecordController {
    private final GameRecordRepository gameRecordRepository;

    public GameRecordController(GameRecordRepository gameRecordRepository) {
        this.gameRecordRepository = gameRecordRepository;
    }

    // Endpoint for saving a game record
    @PostMapping("/saveGameRecord")
    @CrossOrigin(origins = "*")
    public String saveGameRecord(@RequestBody GameRecord gameRecord) {
        if (gameRecord == null) {
            return "Invalid game record";
        }

        // input record to log
        System.out.println("Received game record: " + gameRecord);

        try {
            gameRecordRepository.save(gameRecord);
        } catch (Exception e) {
            System.out.println("Error saving game record: " + e.getMessage());
            return "Error saving game record";
        }

        return "Success";
    }

    // Endpoint to fetch all game records for a specific user
    @GetMapping("/fetchUserScores")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public List<GameRecord> fetchUserScores(@RequestParam String googleId) {
        Iterable<GameRecord> recordsIterable = gameRecordRepository.findByGoogleId(googleId);
        List<GameRecord> records = new ArrayList<>();
        recordsIterable.forEach(records::add);
        return records;
    }



    // Endpoint to fetch high scores
    @GetMapping("/fetchHighScores")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public Page<GameRecord> fetchHighScores(Pageable pageable) {
        return gameRecordRepository.findAllByOrderByScoreDesc(pageable);
    }

    // Endpoint to delete a specific game record
    @DeleteMapping("/deleteUserScore")
    @CrossOrigin(origins = "*")
    public String deleteUserScore(@RequestParam Long id) {
        if (gameRecordRepository.existsById(id)) {
            gameRecordRepository.deleteById(id);
            return "Success";
        } else {
            return "User score not found";
        }
    }

    // Endpoint to delete all game records for a specific user (based on Google ID)
    @DeleteMapping("/deleteAllUserScores")
    @CrossOrigin(origins = "*")
    public String deleteAllUserScores(@RequestParam String googleId) {
        gameRecordRepository.deleteByGoogleId(googleId);
        return "All scores deleted successfully";
    }
}
