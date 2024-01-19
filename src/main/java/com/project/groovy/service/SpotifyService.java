package com.project.groovy.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.neovisionaries.i18n.CountryCode;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.AbstractModelObject;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Recommendations;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.browse.GetListOfNewReleasesRequest;
import se.michaelthelin.spotify.requests.data.browse.GetRecommendationsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;

@Service
public class SpotifyService {

	@Value("59ef494607ed4cb9a0d9f95b0ed09e5d")
    private String clientId;

    @Value("52ef66e7127744cb9c83f3a6bf5124c7")
    private String clientSecret;

    /**
     * 앨범 검색
     * @param query
     * @return
     */
    public List<Album> searchAlbums(String query) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();
        ClientCredentialsRequest credentialsRequest = spotifyApi.clientCredentials().build();

        try {
            ClientCredentials credentials = credentialsRequest.execute();
            spotifyApi.setAccessToken(credentials.getAccessToken());

            SearchAlbumsRequest searchRequest = spotifyApi.searchAlbums(query).market(CountryCode.KR).build();
            final Paging<AlbumSimplified> albumSimplifiedPaging = searchRequest.execute();
            
            List<Album> albums = new ArrayList<>();
            
            for (AlbumSimplified albumSimplified : albumSimplifiedPaging.getItems()) {
                String albumId = albumSimplified.getId();
                try {
                    Album album = spotifyApi.getAlbum(albumId).build().execute();
                    albums.add(album);
                } catch (IOException | SpotifyWebApiException | ParseException e) {
                    System.err.println("Error while retrieving album details for album ID: " + albumId);
                }
            }
            
            return albums;
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    
    /**
     * 최신 앨범
     * @return
     */
    public List<AlbumSimplified> getLatestAlbums(Integer limit) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();

        try {
            // 액세스 토큰 요청
            ClientCredentialsRequest credentialsRequest = spotifyApi.clientCredentials().build();
            ClientCredentials credentials = credentialsRequest.execute();
            spotifyApi.setAccessToken(credentials.getAccessToken());

            // 최신 앨범 가져오기
            GetListOfNewReleasesRequest newReleasesRequest = spotifyApi.getListOfNewReleases().limit(limit).build();
            final Paging<AlbumSimplified> albumPaging = newReleasesRequest.execute();

            List<AlbumSimplified> latestAlbums = new ArrayList<>();
            for (AbstractModelObject albumObject : albumPaging.getItems()) {
                if (albumObject instanceof AlbumSimplified) {
                    latestAlbums.add((AlbumSimplified) albumObject);
                }
            }

            return latestAlbums;
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            // 예외 처리 방법에 따라 로깅 또는 다른 조치를 취할 수 있습니다.
            // 예: 로깅
            System.err.println("Error while retrieving new releases: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    /**
     * 앨범 아이디로 앨범 검색
     * @param albumId
     * @return
     */
    public Album searchAlbumById(String albumId) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();
        ClientCredentialsRequest credentialsRequest = spotifyApi.clientCredentials().build();

        try {
            ClientCredentials credentials = credentialsRequest.execute();
            spotifyApi.setAccessToken(credentials.getAccessToken());

            Album album = spotifyApi.getAlbum(albumId).build().execute();
            return album;
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.err.println("앨범 ID " + albumId + "의 상세 정보를 가져오는 중 오류 발생");
            throw new RuntimeException("에러: " + e.getMessage());
        }
    }
    
    /**
     * 추천 앨범
     * @return
     */
    public List<Track> getRecommendAlbums(String genre, String artist, String track) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();

        ClientCredentialsRequest credentialsRequest = spotifyApi.clientCredentials().build();

        try {
        	ClientCredentials credentials = credentialsRequest.execute();
            spotifyApi.setAccessToken(credentials.getAccessToken());
            // 추천 앨범을 가져오기 위한 API 요청 생성
            GetRecommendationsRequest recommendationsRequest = spotifyApi.getRecommendations()
                    .limit(10)
                    .seed_genres(genre)
                    .seed_artists(artist)
                    .seed_tracks(track)
                    .market(CountryCode.KR)
                    .build();

            // API 요청 실행
            Recommendations recommendations = recommendationsRequest.execute();

            // 추천 앨범 목록 반환
            return Arrays.asList(recommendations.getTracks());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    
    public void getSearch() {
    	SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();
    	
    	ClientCredentialsRequest credentialsRequest = spotifyApi.clientCredentials().build();
    	
    	try {
    		ClientCredentials credentials = credentialsRequest.execute();
            spotifyApi.setAccessToken(credentials.getAccessToken());
            
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    public String search(String accessToken, String q, String type) {

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);;
        headers.add("Host", "api.spotify.com");
        headers.add("Content-type", "application/json;");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://api.spotify.com/v1/search?q=" + 
        q + "&type=" + type, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value(); //상태 코드가 들어갈 status 변수
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);

        return response;
    }
    
    public String genres(String accessToken) {

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);;
        headers.add("Host", "api.spotify.com");
        headers.add("Content-type", "application/json;");
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://api.spotify.com/v1/recommendations/available-genre-seeds", HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value(); //상태 코드가 들어갈 status 변수
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);

        return response;
    }
}
