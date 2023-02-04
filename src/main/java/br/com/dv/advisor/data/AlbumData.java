package br.com.dv.advisor.data;

import br.com.dv.advisor.model.Artist;

import java.util.List;

public interface AlbumData {

    String name();
    String albumUrl();
    List<Artist> artists();

}
