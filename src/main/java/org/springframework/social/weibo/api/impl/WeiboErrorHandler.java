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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.weibo.api.WeiboException;
import org.springframework.web.client.DefaultResponseErrorHandler;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Subclass of {@link DefaultResponseErrorHandler} that handles errors from Weibo's
 * REST API, interpreting them into appropriate exceptions.
 * @author cuizuoli
 */
public class WeiboErrorHandler extends DefaultResponseErrorHandler {

	private final static Log logger = LogFactory.getLog(WeiboErrorHandler.class);

	private final static String WEIBO = "weibo";

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		Map<String, String> errorDetails = extractErrorDetailsFromResponse(response);
		if (errorDetails == null) {
			handleUncategorizedError(response, errorDetails);
		}
		handleWeiboError(response.getStatusCode(), errorDetails);

		// if not otherwise handled, do default handling and wrap with UncategorizedApiException
		handleUncategorizedError(response, errorDetails);
	}

	/**
	 * Examines the error data returned from Weibo and throws the most applicable exception.
	 * @param errorDetails a Map containing a "type" and a "message" corresponding to the Graph API's error response structure.
	 */
	void handleWeiboError(HttpStatus statusCode, Map<String, String> errorDetails) {
		// Can't trust the type to be useful. It's often OAuthException, even for things not OAuth-related. 
		// Can rely only on the message (which itself isn't very consistent).
		String code = errorDetails.get("error_code");
		String message = errorDetails.get("error");
		String request = errorDetails.get("request");

		throw new WeiboException(code, message, request);

	}

	private void handleUncategorizedError(ClientHttpResponse response, Map<String, String> errorDetails) {
		try {
			super.handleError(response);
		} catch (Exception e) {
			if (errorDetails != null) {
				throw new UncategorizedApiException(WEIBO, errorDetails.get("error_code") + ":"
					+ errorDetails.get("error"), e);
			} else {
				throw new UncategorizedApiException(WEIBO, "No error details from Weibo", e);
			}
		}
	}

	/*
	 * Attempts to extract Weibo error details from the response.
	 * Returns null if the response doesn't match the expected JSON error response.
	 */
	private Map<String, String> extractErrorDetailsFromResponse(ClientHttpResponse response) throws IOException {
		ObjectMapper mapper = new ObjectMapper(new JsonFactory());
		String json = readFully(response.getBody());

		System.out.println(json);

		if (logger.isDebugEnabled()) {
			logger.debug("Error from Weibo: " + json);
		}

		try {
			return mapper.<Map<String, String>>readValue(json, new TypeReference<Map<String, String>>() {
			});
		} catch (JsonParseException e) {
			return null;
		}
	}

	private String readFully(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder sb = new StringBuilder();
		while (reader.ready()) {
			sb.append(reader.readLine());
		}
		return sb.toString();
	}

}
