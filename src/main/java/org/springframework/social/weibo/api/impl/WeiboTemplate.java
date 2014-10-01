/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.weibo.api.impl;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.OAuth2Version;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.social.support.URIBuilder;
import org.springframework.social.weibo.api.AccountOperations;
import org.springframework.social.weibo.api.TimelineOperations;
import org.springframework.social.weibo.api.UserOperations;
import org.springframework.social.weibo.api.Weibo;
import org.springframework.social.weibo.api.WeiboApiOperations;
import org.springframework.social.weibo.api.impl.json.WeiboModule;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>This is the central class for interacting with Weibo.</p>
 * <p>
 * There are some operations, such as searching, that do not require OAuth
 * authentication. In those cases, you may use a {@link WeiboTemplate} that is
 * created through the default constructor and without any OAuth details.
 * Attempts to perform secured operations through such an instance, however,
 * will result in {@link NotAuthorizedException} being thrown.
 * </p>
 * @author cuizuoli
 */
public class WeiboTemplate extends AbstractOAuth2ApiBinding implements WeiboApiOperations, Weibo {

	private UserOperations userOperations;

	private AccountOperations accountOperations;

	private TimelineOperations timelineOperations;

	private ObjectMapper objectMapper;

	private String applicationNamespace;

	/**
	 * Create a new instance of WeiboTemplate.
	 * This constructor creates a new WeiboTemplate able to perform unauthenticated operations against Weibo's API.
	 * Some operations do not require OAuth authentication. 
	 * For example, retrieving a specified user's profile or feed does not require authentication (although the data returned will be limited to what is publicly available). 
	 * A WeiboTemplate created with this constructor will support those operations.
	 * Those operations requiring authentication will throw {@link NotAuthorizedException}.
	 */
	public WeiboTemplate() {
		initialize();
	}

	/**
	 * Create a new instance of WeiboTemplate.
	 * This constructor creates the WeiboTemplate using a given access token.
	 * @param accessToken An access token given by Weibo after a successful OAuth 2 authentication (or through Weibo's JS library).
	 */
	public WeiboTemplate(String accessToken) {
		this(accessToken, null);
	}

	public WeiboTemplate(String accessToken, String applicationNamespace) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.applicationNamespace = applicationNamespace;
		initialize();
	}

	@Override
	public void setRequestFactory(ClientHttpRequestFactory requestFactory) {
		// Wrap the request factory with a BufferingClientHttpRequestFactory so that the error handler can do repeat reads on the response.getBody()
		super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(requestFactory));
	}

	@Override
	public UserOperations userOperations() {
		return userOperations;
	}

	@Override
	public AccountOperations accountOperations() {
		return accountOperations;
	}

	@Override
	public TimelineOperations timelineOperations() {
		return timelineOperations;
	}

	public String getApplicationNamespace() {
		return applicationNamespace;
	}

	// low-level Graph API operations
	@Override
	public <T> T getObject(String objectId, Class<T> type) {
		URI uri = URIBuilder.fromUri(WEIBO_API_URL + objectId).build();
		return getRestTemplate().getForObject(uri, type);
	}

	@Override
	public <T> T getObject(String objectId, Class<T> type, MultiValueMap<String, String> queryMap) {
		URI uri = URIBuilder.fromUri(WEIBO_API_URL + objectId).queryParams(queryMap).build();
		return getRestTemplate().getForObject(uri, type);
	}

	@Override
	public <T> T postObject(String objectId, Class<T> type, MultiValueMap<String, String> queryMap) {
		URI uri = URIBuilder.fromUri(WEIBO_API_URL + objectId).build();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<? extends Object> httpEntity = new HttpEntity<Object>(queryMap, headers);
		return getRestTemplate().postForObject(uri, httpEntity, type);
	}

	// AbstractOAuth2ApiBinding hooks
	@Override
	protected OAuth2Version getOAuth2Version() {
		return OAuth2Version.DRAFT_10;
	}

	@Override
	protected void configureRestTemplate(RestTemplate restTemplate) {
		restTemplate.setErrorHandler(new WeiboErrorHandler());
	}

	@Override
	protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
		MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new WeiboModule());
		converter.setObjectMapper(objectMapper);
		return converter;
	}

	// private helpers
	private void initialize() {
		// Wrap the request factory with a BufferingClientHttpRequestFactory so that the error handler can do repeat reads on the response.getBody()
		super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(getRestTemplate().getRequestFactory()));
		initSubApis();
	}

	private void initSubApis() {
		userOperations = new UserTemplate(this, isAuthorized());
		accountOperations = new AccountTemplate(this, isAuthorized());
		timelineOperations = new TimelineTemplate(this, isAuthorized());
	}

}
