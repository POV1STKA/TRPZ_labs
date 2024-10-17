package com.OnlineRadio.OnlineRadioStation.repositories;

import com.OnlineRadio.OnlineRadioStation.models.Statistic;
import java.util.HashMap;
import java.util.Map;

public class StatisticRepository {
    private Map<String, Statistic> statistics = new HashMap<>();

    public void addStatistic(Statistic statistic) {
        statistics.put(statistic.getId(), statistic);
    }

    public Statistic findStatisticById(String id) {
        return statistics.get(id);
    }
}
