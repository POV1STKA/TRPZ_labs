const socket = new WebSocket('ws://localhost:8080/ws/radio');

socket.onmessage = function(event) {
    const data = JSON.parse(event.data);
    if (data.action === "playSong") {
        if (playPauseButton.textContent === 'Pause') {
            playSong();
        }
    } else if (data.action === "updateUserCount") {
        updateUserCount(data.count);
    }
};

function updateUserCount(count) {
    const userCountElement = document.getElementById('userCount');
    userCountElement.textContent = `Users connected: ${count}`;
}

socket.onerror = function(event) {
    console.error("WebSocket error:", event);
};

socket.onclose = function(event) {
    console.log("WebSocket connection closed:", event);
};

const audioPlayer = document.getElementById('audioPlayer');
let selectedBitrate = 128;
let currentSongId = null;
updateSongHistory()

function togglePlayPause() {
    const playPauseButton = document.getElementById('playPauseButton');

    if (playPauseButton.textContent === 'Play Song') {
        playSong();
    } else if (playPauseButton.textContent === 'Pause') {
        pauseSong();
    } else if (playPauseButton.textContent === 'Continue') {
        continueSong();
    }
}

function playSong() {
    const playPauseButton = document.getElementById('playPauseButton');

    fetch('/api/sync', {
        method: 'GET',
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            alert('Failed to synchronize with the server.');
            throw new Error('Failed to get sync time');
        }
    })
    .then(syncData => {
        timeToStart = syncData.currentTime;
        currentSongId = syncData.songId;

        document.getElementById('songTitle').textContent = `${syncData.currentTitle}`;
        document.getElementById('songArtists').textContent = `By ${syncData.currentSongArtists.join(', ')}`;

        fetch(`/api/stream?bitrate=${selectedBitrate}`, {
            method: 'GET',
        })
        .then(response => {
            if (response.ok) {
                response.blob().then(blob => {
                    const audioPlayer = document.getElementById('audioPlayer');
                    audioPlayer.src = URL.createObjectURL(blob);

                    audioPlayer.currentTime = timeToStart;

                    audioPlayer.play();
                    playPauseButton.textContent = 'Pause';
                    updateSongHistory();
                });
            } else {
                alert('Failed to play the song.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error occurred while playing the song.');
        });
    })
    .catch(error => {
        console.error('Error:', error);
    });
}


function pauseSong() {
    const audioPlayer = document.getElementById('audioPlayer');
    const playPauseButton = document.getElementById('playPauseButton');

    audioPlayer.pause();
    playPauseButton.textContent = 'Continue';
}

function continueSong() {
    const audioPlayer = document.getElementById('audioPlayer');
    const playPauseButton = document.getElementById('playPauseButton');

    fetch('/api/sync', {
        method: 'GET',
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Failed to get sync time');
        }
    })
    .then(syncData => {
        const timeToStart = syncData.currentTime;
        const songId = syncData.songId;

        if (currentSongId !== songId) {
            currentSongId = songId;
            playSong();
        } else {
            audioPlayer.currentTime = timeToStart;
            audioPlayer.play();
            playPauseButton.textContent = 'Pause';
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

function updateBitrate(bitrate) {
    selectedBitrate = bitrate;
}

function setVolume(volume) {
    var audioPlayer = document.getElementById('audioPlayer');
    audioPlayer.volume = volume;
}

function updateBitrate(bitrate) {
    selectedBitrate = bitrate;
    playSong();
}

function updateSongHistory() {
    fetch('api/history', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(historyData => {
            const historyList = document.getElementById('songHistoryList');
            historyList.innerHTML = '';

             const historyArray = Object.entries(historyData);
             const reversedHistory = historyArray.reverse();

            for (let i = 0; i < Math.min(10, reversedHistory.length); i++) {
                const [timestamp, song] = reversedHistory[i];
                const listItem = document.createElement('li');
                listItem.textContent = `${timestamp}: ${song.title}`;
                historyList.appendChild(listItem);
            }
        })
        .catch(error => {
            console.error('Error fetching song history:', error);
        });
}










