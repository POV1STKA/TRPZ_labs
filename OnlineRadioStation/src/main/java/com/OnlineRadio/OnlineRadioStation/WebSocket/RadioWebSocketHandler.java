package com.OnlineRadio.OnlineRadioStation.WebSocket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.CloseStatus;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class RadioWebSocketHandler extends TextWebSocketHandler {

    private static final Set<WebSocketSession> users = new HashSet<>();
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private static final Map<String, Integer> userCountHistory = new LinkedHashMap<>();

    static {
        scheduler.scheduleAtFixedRate(RadioWebSocketHandler::collectUserCount, 0, 60, TimeUnit.SECONDS);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        synchronized (users) {
            users.add(session);
        }
        broadcastUserCount();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        synchronized (users) {
            users.remove(session);
        }
        broadcastUserCount();
    }

    public void sendPlayCommand() {
        String message = "{\"action\":\"playSong\"}";

        synchronized (users) {
            for (WebSocketSession session : users) {
                executorService.submit(() -> {
                    try {
                        if (session.isOpen()) {
                            session.sendMessage(new TextMessage(message));
                        }
                    } catch (IOException e) {
                        System.err.println("Error sending message: " + e.getMessage());
                    }
                });
            }
        }
    }

    private static void broadcastUserCount() {
        String message = "{\"action\":\"updateUserCount\", \"count\":" + users.size() + "}";

        synchronized (users) {
            for (WebSocketSession session : users) {
                executorService.submit(() -> {
                    try {
                        if (session.isOpen()) {
                            session.sendMessage(new TextMessage(message));
                        }
                    } catch (IOException e) {
                        System.err.println("Error sending user count: " + e.getMessage());
                    }
                });
            }
        }
    }

    private static void collectUserCount() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = sdf.format(new Date());
        int userCount;
        synchronized (users) {
            userCount = users.size();
        }
        synchronized (userCountHistory) {
            userCountHistory.put(timestamp, userCount);
        }
    }

    public Map<String, Integer> getUserCountHistory() {
        synchronized (userCountHistory) {
            return new LinkedHashMap<>(userCountHistory);
        }
    }
}
