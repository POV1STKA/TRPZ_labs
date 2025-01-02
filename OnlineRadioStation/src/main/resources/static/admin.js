document.addEventListener('DOMContentLoaded', () => {
    loadQueue();
    searchPlaylists();
    searchSongs();
});

async function addPlaylistToQueue(playlistId, shuffle) {
    await fetch(`/api/admin/addPlaylist/${playlistId}?shuffle=${shuffle}`, { method: 'POST' });
    loadQueue();
}

async function addSongToQueue(songId) {
    await fetch(`/api/admin/add/${songId}`, { method: 'POST' });
    loadQueue();
}

async function searchPlaylists() {
    const query = document.getElementById('playlistSearchField').value;
    try {
        const response = await fetch(`/admin/playlists?title=${encodeURIComponent(query)}`);
        if (!response.ok) {
            throw new Error(`Error ${response.status}: ${response.statusText}`);
        }
        const data = await response.json();
        updatePlaylistResultsView(data);
    } catch (error) {
        console.error(`Error fetching playlists:`, error);
    }
    loadQueue();
}

async function searchSongs() {
    const query = document.getElementById('songSearchField').value;
    try {
        const response = await fetch(`/admin/songs?title=${encodeURIComponent(query)}`);
        if (!response.ok) {
            throw new Error(`Error ${response.status}: ${response.statusText}`);
        }
        const data = await response.json();
        updateSongResultsView(data);
    } catch (error) {
        console.error(`Error fetching songs:`, error);
    }
    loadQueue();
}

function updatePlaylistResultsView(playlists) {
    const playlistList = document.getElementById('playlistList');
    if (!playlistList) {
        console.error('Playlist list element not found.');
        return;
    }
    playlistList.innerHTML = '';

    playlists.forEach(playlist => {
        const listItem = document.createElement('li');
        listItem.innerHTML = `
            <span>${playlist.name}</span>
            <div class="playlist-actions">
                <button class="btn-icon" onclick="addPlaylistToQueue(${playlist.id}, false)">
                    <i class="icon-sequential">+</i>
                </button>
                <button class="btn-icon" onclick="addPlaylistToQueue(${playlist.id}, true)">
                    <i class="icon-shuffled">🔀</i>
                </button>
            </div>
        `;
        playlistList.appendChild(listItem);
    });
}

async function updateSongResultsView(songs) {
    const songList = document.getElementById('songList');
    if (!songList) {
        console.error('Song list element not found.');
        return;
    }
    songList.innerHTML = '';

    for (let song of songs) {
        const artists = await fetchArtistsForSong(song.id);
        const artistNames = artists.map(artist => artist.name).join(', ');
        const listItem = document.createElement('li');
        listItem.innerHTML = `
            ${song.title} - ${artistNames}
                <button class="btn-icon" onclick="addSongToQueue(${song.id})">
                    <i class="icon-sequential">+</i>
                </button>
        `;
        songList.appendChild(listItem);
    }
}

async function fetchArtistsForSong(songId) {
    const response = await fetch(`/admin/songs/${songId}/artists`);
    if (!response.ok) {
        throw new Error(`Error ${response.status}: ${response.statusText}`);
    }
    return await response.json();
}

async function loadQueue() {
    const response = await fetch('/api/admin/queue');
    const queue = await response.json();
    const queueList = document.getElementById('queueList');
    queueList.innerHTML = '';
    for (let song of queue) {
        const artists = await fetchArtistsForSong(song.id);
        const artistNames = artists.map(artist => artist.name).join(', ');
        const listItem = document.createElement('li');
        listItem.textContent = `${song.title} - ${artistNames}`;
        queueList.appendChild(listItem);
    }
}

async function playNext() {
    await fetch('api/admin/next', { method: 'POST' });
    loadQueue();
}

async function clearQueue() {
    await fetch('api/admin/clear', { method: 'POST' });
    loadQueue();
}
