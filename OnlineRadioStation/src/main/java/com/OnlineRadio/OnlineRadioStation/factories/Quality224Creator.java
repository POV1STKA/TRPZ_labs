package com.OnlineRadio.OnlineRadioStation.factories;

import com.OnlineRadio.OnlineRadioStation.adapters.FFmpegQualityAdapter;
import com.OnlineRadio.OnlineRadioStation.adapters.QualityAdapter;

public class Quality224Creator extends QualityAdapterCreator {
    @Override
    public QualityAdapter createAdapter() {
        return new FFmpegQualityAdapter(224);
    }
}
