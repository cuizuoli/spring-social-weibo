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

	//private final static String WEIBO = "weibo";

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

		throw new WeiboException(code, message);

		//		if (statusCode == HttpStatus.NOT_FOUND) {
		//			if (error.contains("Some of the aliases you requested do not exist")) {
		//				throw new ResourceNotFoundException(WEIBO, error);
		//			}
		//		} else if (statusCode == HttpStatus.BAD_REQUEST) {
		//			if (error.contains("Unknown path components")) {
		//				throw new ResourceNotFoundException(WEIBO, error);
		//			} else if (error.equals("An access token is required to request this resource.")) {
		//				throw new MissingAuthorizationException(WEIBO);
		//			} else if (error.equals("An active access token must be used to query information about the current user.")) {
		//				throw new MissingAuthorizationException(WEIBO);
		//			} else if (error.startsWith("Error validating access token")) {
		//				handleInvalidAccessToken(error);
		//			} else if (error.equals("Invalid access token signature.")) { // Access token that fails signature validation
		//				throw new InvalidAuthorizationException(WEIBO, error);
		//			} else if (error.contains("Application does not have the capability to make this API call.")
		//				|| error.contains("App must be on whitelist")) {
		//				throw new OperationNotPermittedException(WEIBO, error);
		//			} else if (error.contains("Invalid fbid") || error.contains("The parameter url is required")) {
		//				throw new OperationNotPermittedException(WEIBO, "Invalid object for this operation");
		//			} else if (error.contains("Duplicate status error")) {
		//				throw new DuplicateStatusException(WEIBO, error);
		//			} else if (error.contains("Feed action request limit reached")) {
		//				throw new RateLimitExceededException(WEIBO);
		//			} else if (error.contains("The status you are trying to publish is a duplicate of, or too similar to, one that we recently posted to Twitter")) {
		//				throw new DuplicateStatusException(WEIBO, error);
		//			}
		//		} else if (statusCode == HttpStatus.UNAUTHORIZED) {
		//			if (error.startsWith("Error validating access token")) {
		//				handleInvalidAccessToken(error);
		//			} else if (error.equals("Invalid OAuth access token.")) { // Bogus access token
		//				throw new InvalidAuthorizationException(WEIBO, error);
		//			} else if (error.startsWith("Error validating application.")) { // Access token with incorrect app ID
		//				throw new InvalidAuthorizationException(WEIBO, error);
		//			}
		//			throw new NotAuthorizedException(WEIBO, error);
		//		} else if (statusCode == HttpStatus.FORBIDDEN) {
		//			if (error.contains("Requires extended permission")) {
		//				throw new InsufficientPermissionException(WEIBO, error.split(": ")[1]);
		//			} else if (error.contains("Permissions error")) {
		//				throw new InsufficientPermissionException(WEIBO);
		//			} else if (error.contains("The user hasn't authorized the application to perform this action")) {
		//				throw new InsufficientPermissionException(WEIBO);
		//			} else {
		//				throw new OperationNotPermittedException(WEIBO, error);
		//			}
		//		} else if (statusCode == HttpStatus.NOT_FOUND) {
		//			throw new ResourceNotFoundException(WEIBO, error);
		//		} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR) {
		//			if (error.equals("User must be an owner of the friendlist")) { // watch for pattern in similar error in other resources
		//				throw new ResourceOwnershipException(error);
		//			} else if (error.equals("The member must be a friend of the current user.")) {
		//				throw new NotAFriendException(error);
		//			} else {
		//				throw new InternalServerErrorException(WEIBO, error);
		//			}
		//		}
	}

	//	private void handleInvalidAccessToken(String message) {
	//		if (message.contains("Session has expired at unix time")) {
	//			throw new ExpiredAuthorizationException("weibo");
	//		} else if (message.contains("The session has been invalidated because the user has changed the password.")) {
	//			throw new RevokedAuthorizationException("weibo", message);
	//		} else if (message.contains("The session is invalid because the user logged out.")) {
	//			throw new RevokedAuthorizationException("weibo", message);
	//		} else if (message.contains("The session was invalidated explicitly using an API call.")) {
	//			throw new RevokedAuthorizationException("weibo", message);
	//		} else if (message.contains("Session does not match current stored session.")) {
	//			throw new RevokedAuthorizationException("weibo", message);
	//		} else {
	//			throw new InvalidAuthorizationException("weibo", message);
	//		}
	//	}

	private void handleUncategorizedError(ClientHttpResponse response, Map<String, String> errorDetails) {
		try {
			super.handleError(response);
		} catch (Exception e) {
			if (errorDetails != null) {
				throw new UncategorizedApiException("weibo", errorDetails.get("message"), e);
			} else {
				throw new UncategorizedApiException("weibo", "No error details from Weibo", e);
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
			return mapper.<Map<String, String>> readValue(json, new TypeReference<Map<String, String>>() {
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
