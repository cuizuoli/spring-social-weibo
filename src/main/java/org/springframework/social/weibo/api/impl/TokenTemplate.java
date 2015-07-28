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

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.social.weibo.api.TokenOperations;
import org.springframework.social.weibo.api.WeiboApiOperations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * TokenTemplate
 * @author cuizuoli
 */
public class TokenTemplate extends AbstractWeiboOperations implements TokenOperations {

	private final Log log = LogFactory.getLog(getClass());

	private final WeiboApiOperations weiboApi;

	public TokenTemplate(WeiboApiOperations weiboApi, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.weiboApi = weiboApi;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getPageTokenInfo(String signedRequest, String appSecret) {
		Map<String, Object> tokenInfo = null;
		try {
			tokenInfo = weiboApi.getObjectMapper().readValue(parseSignedRequest(signedRequest, appSecret), Map.class);
		} catch (JsonParseException e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		} catch (JsonMappingException e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		} catch (IOException e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		return tokenInfo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getTokenInfo() {
		requireAuthorization();
		MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<String, String>();
		String response = weiboApi.postOauth2Object("get_token_info", String.class, queryMap);
		Map<String, Object> tokenInfo = null;
		try {
			tokenInfo = weiboApi.getObjectMapper().readValue(response, Map.class);
		} catch (JsonParseException e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		} catch (JsonMappingException e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		} catch (IOException e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		return tokenInfo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> revokeOauth2() {
		requireAuthorization();
		MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<String, String>();
		String response = weiboApi.postOauth2Object("revokeoauth2", String.class, queryMap);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result = weiboApi.getObjectMapper().readValue(response, Map.class);
		} catch (JsonParseException e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		} catch (JsonMappingException e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		} catch (IOException e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		return result;
	}

	private static final String ALGORITHM_HMACSHA256 = "hmacSHA256";

	/**
	 * http://open.weibo.com/wiki/%E8%BD%BB%E5%BA%94%E7%94%A8%E5%BC%80%E5%8F%91%E6%8C%87%E5%8D%97
	 * @param signedRequest
	 * @param appSecret
	 * @return
	 */
	private String parseSignedRequest(String signedRequest, String appSecret) {
		String tokenInfoValue = null;
		String[] tokens = StringUtils.split(signedRequest, "\\.", 2);
		// base64Token
		String base64Token = tokens[0];
		// 为了和 url encode/decode 不冲突，base64url 编码方式会将
		// '+'，'/'转换成'-'，'_'，并且去掉结尾的'='。 因此解码之前需要还原到默认的base64编码，结尾的'='可以用以下算法还原
		int padding = (4 - base64Token.length() % 4);
		for (int i = 0; i < padding; i++) {
			base64Token += "=";
		}
		base64Token = StringUtils.replace(base64Token, "-", "+");
		base64Token = StringUtils.replace(base64Token, "_", "/");
		// base64Token1
		String token = tokens[1];
		SecretKey key = new SecretKeySpec(appSecret.getBytes(), ALGORITHM_HMACSHA256);
		try {
			Mac mac = Mac.getInstance(ALGORITHM_HMACSHA256);
			mac.init(key);
			mac.update(token.getBytes());
			byte[] macResult = mac.doFinal();
			String base64Token1 = Base64.encodeBase64String(macResult);
			// access token
			if (StringUtils.equals(base64Token, base64Token1)) {
				tokenInfoValue = new String(Base64.decodeBase64(token));
			}
		} catch (NoSuchAlgorithmException e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		} catch (InvalidKeyException e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		return tokenInfoValue;
	}

}
