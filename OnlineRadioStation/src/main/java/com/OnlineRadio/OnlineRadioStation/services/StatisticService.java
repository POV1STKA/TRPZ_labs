package com.OnlineRadio.OnlineRadioStation.services;

import com.OnlineRadio.OnlineRadioStation.models.Statistic;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class StatisticService {

    private final List<Statistic> statistics = new ArrayList<>();

    public void logPlay(String userId, String songId, int listeningDuration) {
        Statistic statistic = new Statistic(generateId(), userId, LocalDateTime.now(), listeningDuration);
        statistic.addSongId(songId);
        statistics.add(statistic);
    }

    public List<Statistic> getUserStatistics(String userId) {
        return statistics.stream()
                .filter(statistic -> statistic.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<Statistic> getSongStatistics(String songId) {
        return statistics.stream()
                .filter(statistic -> statistic.getSongIds().contains(songId))
                .collect(Collectors.toList());
    }

    public List<Statistic> getAllStatistics() {
        return new ArrayList<>(statistics);
    }

    private String generateId() {
        return java.util.UUID.randomUUID().toString();
    }
}
