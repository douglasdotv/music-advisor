package br.com.dv.advisor.model;

import br.com.dv.advisor.data.ArtistData;

public record Artist(String name) implements ArtistData {

    @Override
    public String toString() {
        return this.name;
    }

}
