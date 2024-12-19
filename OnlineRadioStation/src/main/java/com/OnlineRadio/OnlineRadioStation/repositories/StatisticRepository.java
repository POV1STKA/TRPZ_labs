package com.OnlineRadio.OnlineRadioStation.repositories;

import com.OnlineRadio.OnlineRadioStation.models.Statistic;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Repository
public class StatisticRepository {
    private Map<String, Statistic> statistics = new HashMap<>();

    public void addStatistic(Statistic statistic) {
        statistics.put(statistic.getId(), statistic);
    }

    public Statistic findStatisticById(String id) {
        return statistics.get(id);
    }

    public List<Statistic> findStatisticsByUserId(String userId) {
        return statistics.values().stream()
                .filter(statistic -> statistic.getUserId().equals(userId))
                .toList();
    }

}
