package br.com.dv.advisor.model;

import java.util.List;

public record Album(String name, String albumUrl, List<Artist> artists) implements AlbumData {

}
