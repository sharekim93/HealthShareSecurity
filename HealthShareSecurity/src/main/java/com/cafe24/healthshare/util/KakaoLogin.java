package com.cafe24.healthshare.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KakaoLogin {
	
	public static JsonNode getKakaoAccessToken(String code) throws UnsupportedEncodingException,ClientProtocolException,IOException{
		 
	        final String 			  RequestUrl 	= "https://kauth.kakao.com/oauth/token?"
													+ "grant_type=authorization_code"
													+ "&client_id=1def83c8f6ac181e4df9db51d15d7f0e"
													+ "&redirect_uri=http://sharekim93.cafe24.com/HealthShareSecurity/member/KakaoLogin"
													+ "&code="+code; // Host
	        final HttpClient 		  client 		= HttpClientBuilder.create().build();
	        final HttpGet 			  get			= new HttpGet(RequestUrl);
            final HttpResponse 		  response 		= client.execute(get);
            final int 				  responseCode 	= response.getStatusLine().getStatusCode();
	        
            JsonNode returnNode = null;
            
            log.info("Sending 'GET' request to URL : " + RequestUrl);
            log.info("Response Code : " + responseCode);
 
            // JSON 형태 반환값 처리
            ObjectMapper mapper = new ObjectMapper();
            returnNode = mapper.readTree(response.getEntity().getContent());
	 
	        return returnNode;
	    }
	 
	public static JsonNode getKakaoUserInfo(JsonNode accessToken) throws ClientProtocolException,IOException {
		 
	        final String RequestUrl = "https://kapi.kakao.com/v2/user/me";
	        final HttpClient client = HttpClientBuilder.create().build();
	        final HttpPost post = new HttpPost(RequestUrl);
	 
	        JsonNode returnNode = null;
	        // add header
	        post.addHeader("Authorization", "Bearer " + accessToken.get("access_token"));
	 
            final HttpResponse response = client.execute(post);
            final int responseCode = response.getStatusLine().getStatusCode();
 
            log.info("Sending 'POST' request to URL : " + RequestUrl);
            log.info("Response Code : " + responseCode);
            // JSON 형태 반환값 처리
            ObjectMapper mapper = new ObjectMapper();
            returnNode = mapper.readTree(response.getEntity().getContent());
	        return returnNode;
	    }

}
