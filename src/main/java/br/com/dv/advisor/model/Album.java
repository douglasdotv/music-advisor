package br.com.dv.advisor.model;

import br.com.dv.advisor.data.AlbumData;

import java.util.List;

public record Album(String name, String albumUrl, List<Artist> artists) implements AlbumData {

}
