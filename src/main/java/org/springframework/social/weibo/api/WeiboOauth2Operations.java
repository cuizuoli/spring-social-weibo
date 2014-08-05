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

import org.springframework.social.oauth2.AccessGrant;

/**
 * WeiboOauth2Operations
 * @author cuizuoli
 */
public interface WeiboOauth2Operations {

	static final String GET_TOKEN_INFO_URL = "https://api.weibo.com/oauth2/get_token_info";
	static final String REVOKE_OAUTH2_URL = "https://api.weibo.com/oauth2/revokeoauth2";

	AccessGrant getTokenInfo(String accessToken);

	boolean revokeOauth2(String accessToken);

}
