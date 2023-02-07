# music-advisor
[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/douglasdotv/music-advisor/blob/master/README.md)
[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](https://github.com/douglasdotv/music-advisor/blob/master/README.pt-br.md)

MusicAdvisor is a simple command-line application that provides users with access to different albums and playlists that are retrieved from [Spotify Web API](https://developer.spotify.com/documentation/web-api/).

### Features
- View featured playlists
- View new playlists
- View playlists by category
- Search for specific playlists

### Usage
If you want to test the app, follow the steps below:

1 - Head to [Spotify for Developers](https://developer.spotify.com/dashboard/login) website and create a test app.

There, you will be able to get the CLIENT_ID and CLIENT_SECRET that you will need to use the application.

Also, you will need to add the following redirect URI to your app: http://localhost:8080. 

To do that, head to the Edit Settings section of your app and add http://localhost:8080 to the Redirect URIs field.

2 - Clone the repository and open it on an IDE.
Then, head to the MusicAdvisorConfig class (inside config package) and update the CLIENT_ID and CLIENT_SECRET with valid values.

3 - Run the program from the Main class and input _auth_ command to get an authorization link.

Once you have authorized the app, you will be able to access the following commands:

- featured - View the featured playlists
- new - View the new playlists
- categories - View playlists by category
- playlists [playlist_name] - Search for specific playlists. Replace [playlist_name] with the name of the playlist you are searching for.
- prev/next - You can use these to navigate through pages (each command shows a limited amount of items per page).
- exit - Exit the application

### What I learned by doing this project
By doing this project, I learned how to work with OAuth2 in order to interact with Spotify's API and applied some design patterns.