# music-advisor
[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/douglasdotv/music-advisor/blob/master/README.md)
[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](https://github.com/douglasdotv/music-advisor/blob/master/README.pt-br.md)

This project is a command-line application that that makes preference-based suggestions and shares links to new releases and featured playlists via [Spotify Web API](https://developer.spotify.com/documentation/web-api/).

### Features
- OAuth2 authentication
- Get authorization from Spotify to access the API
- View featured playlists, playlists by category and new album releases based on user preferences

**Example:**
![-](./screenshots/musicadvisor.jpg)

### Technologies used
- Java 17
- Gradle
- [Spotify Web API](https://developer.spotify.com/documentation/web-api/)

### Skills developed throughout the project
- How to use OAuth2 protocol to authenticate users
- How to use the Spotify Web API to get information about playlists, albums, artists and categories based on user preferences
- How to use Java `HttpClient` class to make HTTP requests
- How to parse JSON responses from the API
- How to apply MVC pattern to separate the application into three layers: model, view and controller
- How to apply strategy pattern to handle different types of inputs
- How to paginate results retrieved from the Spotify API

### How to run
In order to run the app, you'll need:

- JDK, JRE and Gradle installed
- A Spotify account

Then, follow the steps below:

##### **1**)
Head to [Spotify for Developers](https://developer.spotify.com/dashboard/login) website and create a test app.

There, you will be able to get a Client ID and a Client Secret, which are needed to test the project.

Also, you will need to add the following redirect URI to your app: http://localhost:8080. To do that, head to the _Edit Settings_ section of your app and add http://localhost:8080 to the _Redirect URIs_ field.

##### **2**)
Open a terminal and clone this repository to your machine:
```
git clone https://github.com/douglasdotv/music-advisor.git
```

##### **3**)
Inside the project's root directory, navigate to the following location: 

_\src\main\java\br\com\dv\advisor\config_

Then, open _MusicAdvisorConfig.java_ with your favorite text editor and update CLIENT_ID and CLIENT_SECRET with the values you got from Spotify for Developers website. (Note that the values initially set in the file are just examples and do not work at all.)

##### **4**)
Now, go back to the project's root directory, build and run the JAR file via terminal:
```
./gradlew build
java -cp build/libs/music-advisor-1.0-SNAPSHOT.jar br.com.dv.advisor.Main
```

##### **5**)
Type _auth_ and press enter to get an authorization link from Spotify. Then, access the link and authorize the app.
Once you have authorized the app, you can use the commands below to interact with the application:

- featured – View the featured playlists
- new – View the new playlists
- categories – View playlists by category
- playlists [playlist_name] – Search for specific playlists (replace [playlist_name] with the name of the playlist you are searching for)
- prev/next – You can use these to navigate through pages (each command shows a limited amount of items per page)
- exit – Exit the application

### Contact
If you have any questions or suggestions, feel free to contact me at [LinkedIn](https://www.linkedin.com/in/douglasdotv) or via email (douglas16722@gmail.com).
