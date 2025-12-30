const express = require('express');
const axios = require('axios');
const path = require('path');

const app = express();
const port = 3000;

// Serve static files from the current directory
app.use(express.static(__dirname));

// API endpoint to proxy the player data
app.get('/get-players', async (req, res) => {
    try {
        const response = await axios.get('https://map.cnation.net/tiles/players.json', {
            // Ensure no caching on the server-to-server request
            headers: {
                'Cache-Control': 'no-cache',
                'Pragma': 'no-cache',
                'Expires': '0',
            },
        });
        res.json(response.data);
    } catch (error) {
        console.error('Error fetching player data:', error.message);
        res.status(500).json({ error: 'Failed to fetch player data' });
    }
});

app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}`);
    console.log('Open this URL in your browser.');
});
