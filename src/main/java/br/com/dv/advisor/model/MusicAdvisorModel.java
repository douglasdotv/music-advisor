package br.com.dv.advisor.model;

import br.com.dv.advisor.data.AlbumData;
import br.com.dv.advisor.data.CategoryData;
import br.com.dv.advisor.data.PlaylistData;

import java.util.Collections;
import java.util.List;

public class MusicAdvisorModel {

    private List<AlbumData> newReleases;
    private List<PlaylistData> featuredPlaylists;
    private List<CategoryData> categories;
    private List<PlaylistData> playlistsByCategory;

    public List<AlbumData> getNewReleases() {
        return Collections.unmodifiableList(newReleases);
    }

    public void setNewReleases(List<AlbumData> newReleases) {
        this.newReleases = newReleases;
    }

    public List<PlaylistData> getFeaturedPlaylists() {
        return Collections.unmodifiableList(featuredPlaylists);
    }

    public void setFeaturedPlaylists(List<PlaylistData> featuredPlaylists) {
        this.featuredPlaylists = featuredPlaylists;
    }

    public List<CategoryData> getCategories() {
        return Collections.unmodifiableList(categories);
    }

    public void setCategories(List<CategoryData> categories) {
        this.categories = categories;
    }

    public List<PlaylistData> getPlaylistsByCategory() {
        return Collections.unmodifiableList(playlistsByCategory);
    }

    public void setPlaylistsByCategory(List<PlaylistData> playlistsByCategory) {
        this.playlistsByCategory = playlistsByCategory;
    }

}
