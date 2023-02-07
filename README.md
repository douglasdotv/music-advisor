# music-advisor

MusicAdvisor is a simple command-line application that provides users with access to different albums and playlists that are retrieved from [Spotify Web API](https://developer.spotify.com/documentation/web-api/).

### Features
- View featured playlists
- View new playlists
- View playlists by category
- Search for specific playlists

### Usage
To use MusicAdvisor, you can clone the repository and open it on an IDE.
You will need to provide access to the music streaming service by inputting the _auth_ command (just type 'auth').
Once you have authorized the app, you will be able to access the following commands:

- featured - View the featured playlists
- new - View the new playlists
- categories - View playlists by category
- playlists [playlist_name] - Search for specific playlists. Replace [playlist_name] with the name of the playlist you are searching for.
- exit - Exit the application

### What I learned by doing this project
By doing this project, I learned how to work with OAuth2 in order to interact with Spotify's API and applied some design patterns to improve code readability. (It could still be improved further though.)