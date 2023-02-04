package br.com.dv.advisor.model;

import br.com.dv.advisor.data.PlaylistData;

public record Playlist(String name, String playlistUrl) implements PlaylistData {

}
