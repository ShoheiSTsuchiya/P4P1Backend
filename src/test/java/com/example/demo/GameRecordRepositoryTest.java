package com.example.demo; // このパッケージ名は実際のパッケージ名に置き換えてください

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GameRecordRepositoryTest {

    @Autowired
    private GameRecordRepository gameRecordRepository;

    private GameRecord testRecord;

    @BeforeEach
    public void setup() {
        String testGoogleId = "testGoogleId123";
        testRecord = new GameRecord();
        testRecord.setGoogleId(testGoogleId);

        gameRecordRepository.save(testRecord);
    }

    @AfterEach
    public void cleanup() {
        if (testRecord != null) {
            gameRecordRepository.delete(testRecord);
        }
    }

    @Test
    public void testFindByGoogleId() {
        List<GameRecord> foundRecords = gameRecordRepository.findByGoogleId(testRecord.getGoogleId());

        assertFalse(foundRecords.isEmpty());
        assertEquals(testRecord.getGoogleId(), foundRecords.get(0).getGoogleId()); // Google IDが一致することを確認
    }
}
