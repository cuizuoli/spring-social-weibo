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
package org.springframework.social.weibo.connect;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.social.weibo.api.WeiboAccessGrant;
import org.springframework.social.weibo.api.impl.json.WeiboModule;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Weibo-specific extension of OAuth2Template to use a RestTemplate that recognizes form-encoded responses as "text/plain".
 * Weibo token responses are form-encoded results with a content type of "text/plain", which prevents the FormHttpMessageConverter
 * registered by default from parsing the results.
 * @author cuizuoli
 */
public class WeiboOAuth2Template extends OAuth2Template {

	private final Log log = LogFactory.getLog(getClass());

	private static final String AUTHORIZE_URL = "https://api.weibo.com/oauth2/authorize";
	private static final String ACCESS_TOKEN_URL = "https://api.weibo.com/oauth2/access_token";

	public WeiboOAuth2Template(String clientId, String clientSecret) {
		super(clientId, clientSecret, AUTHORIZE_URL, ACCESS_TOKEN_URL);
		setUseParametersForClientAuthentication(true);
	}

	@Override
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = new RestTemplate(ClientHttpRequestFactorySelector.getRequestFactory());
		FormHttpMessageConverter messageConverter = new FormHttpMessageConverter() {
			public boolean canRead(Class<?> clazz, MediaType mediaType) {
				// always read as x-www-url-formencoded even though Weibo sets contentType to text/plain
				return true;
			}
		};
		restTemplate.setMessageConverters(Collections.<HttpMessageConverter<?>>singletonList(messageConverter));
		return restTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		MultiValueMap<String, String> response = getRestTemplate().postForObject(accessTokenUrl, parameters, MultiValueMap.class);
		Iterator<String> keyIterator = response.toSingleValueMap().keySet().iterator();
		WeiboAccessGrant weiboAccessGrant = new WeiboAccessGrant();
		if (keyIterator.hasNext()) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new WeiboModule());
			try {
				weiboAccessGrant = objectMapper.readValue(keyIterator.next(), WeiboAccessGrant.class);
			} catch (IOException e) {
				log.error(ExceptionUtils.getFullStackTrace(e));
			}
		}
		return new AccessGrant(weiboAccessGrant.getAccessToken(), weiboAccessGrant.getScope(), weiboAccessGrant.getRefreshToken(), weiboAccessGrant.getExpireTime());
	}

}
