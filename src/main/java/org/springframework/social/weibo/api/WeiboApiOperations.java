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
package org.springframework.social.weibo.api;

import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Defines low-level operations against Weibo's API.
 * @author cuizuoli
 */
public interface WeiboApiOperations {

	/**
	 * getObjectMapper
	 * @return
	 */
	ObjectMapper getObjectMapper();

	/**
	 * getObject
	 * @param objectId
	 * @param type
	 * @return
	 */
	<T> T getObject(String objectId, Class<T> type);

	/**
	 * getObject
	 * @param objectId
	 * @param type
	 * @param queryMap
	 * @return
	 */
	<T> T getObject(String objectId, Class<T> type, MultiValueMap<String, String> queryMap);

	/**
	 * postObject
	 * @param objectId
	 * @param type
	 * @param queryMap
	 * @return
	 */
	<T> T postObject(String objectId, Class<T> type, MultiValueMap<String, String> queryMap);

	/**
	 * getOauth2Object
	 * @param objectId
	 * @param type
	 * @return
	 */
	<T> T getOauth2Object(String objectId, Class<T> type);

	/**
	 * getOauth2Object
	 * @param objectId
	 * @param type
	 * @param queryMap
	 * @return
	 */
	<T> T getOauth2Object(String objectId, Class<T> type, MultiValueMap<String, String> queryMap);

	/**
	 * postOauth2Object
	 * @param objectId
	 * @param type
	 * @param queryMap
	 * @return
	 */
	<T> T postOauth2Object(String objectId, Class<T> type, MultiValueMap<String, String> queryMap);

	static final String BASE_API_URL = "https://api.weibo.com/2/";
	static final String BASE_OAUTH2_URL = "https://api.weibo.com/oauth2/";
}
