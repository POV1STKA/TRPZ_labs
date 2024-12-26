package com.OnlineRadio.OnlineRadioStation.adapters;

import com.OnlineRadio.OnlineRadioStation.models.Song;

import java.io.IOException;

public class FFmpegQualityAdapter implements QualityAdapter {
    private int bitrate;

    public FFmpegQualityAdapter(int bitrate) {
        this.bitrate = bitrate;
    }

    @Override
    public Song convert(Song song) {
        String inputFilePath = song.getFilePath();
        String outputFilePath = inputFilePath.replace(".mp3", "_" + bitrate + "kbps.mp3");

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "ffmpeg", "-i", inputFilePath, "-b:a", bitrate + "k", outputFilePath);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Successfully converted to " + bitrate + " kbps: " + outputFilePath);
                return new Song(song.getTitle(), outputFilePath);
            } else {
                throw new RuntimeException("Error converting file. Exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error during conversion with FFmpeg: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
