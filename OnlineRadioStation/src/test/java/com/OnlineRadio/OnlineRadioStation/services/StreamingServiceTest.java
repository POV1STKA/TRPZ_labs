import com.OnlineRadio.OnlineRadioStation.models.streaming.*;
import com.OnlineRadio.OnlineRadioStation.services.StreamingService;
import com.OnlineRadio.OnlineRadioStation.factories.AudioStreamFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class StreamingServiceTest{

    private StreamingService streamingService;
    private AudioStream audioStreamMock;

    @BeforeEach
    public void setUp() {
        streamingService = new StreamingService();
        audioStreamMock = mock(AudioStream.class);
    }

    @Test
    public void testStartStreamingLowQuality() {
        try (MockedStatic<AudioStreamFactory> mockedFactory = mockStatic(AudioStreamFactory.class)) {
            // Коли запитують створення потоку низької якості, повертаємо мокований потік
            mockedFactory.when(() -> AudioStreamFactory.createStream("low")).thenReturn(audioStreamMock);

            // Переконуємося, що потік не null перед викликом startStream
            assertNotNull(audioStreamMock, "AudioStream should not be null");

            // Запускаємо стрімінг
            streamingService.startStreaming("song.mp3", "low");

            // Перевіряємо, чи був викликаний метод startStream на моковому потоці
            verify(audioStreamMock, times(1)).startStream("song.mp3");
        }
    }
}
