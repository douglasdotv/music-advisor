package br.com.dv.advisor.model;

public record Artist(String name) implements ArtistData {

    @Override
    public String toString() {
        return this.name;
    }

}
