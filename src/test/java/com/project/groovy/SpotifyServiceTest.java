package com.project.groovy;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.groovy.service.SpotifyService;

import se.michaelthelin.spotify.model_objects.special.FeaturedPlaylists;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;

public class SpotifyServiceTest {
	
	@Autowired
	SpotifyService spotifyService;

	@Test
	public void test() {
	}

}
