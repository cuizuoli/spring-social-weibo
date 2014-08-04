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
package org.springframework.social.weibo.security;

import org.springframework.social.security.provider.OAuth2AuthenticationService;
import org.springframework.social.weibo.api.Weibo;
import org.springframework.social.weibo.connect.WeiboConnectionFactory;

/**
 * WeiboAuthenticationService
 * @author cuizuoli
 */
public class WeiboAuthenticationService extends OAuth2AuthenticationService<Weibo> {

	public WeiboAuthenticationService(String apiKey, String appSecret) {
		super(new WeiboConnectionFactory(apiKey, appSecret));
	}

	public WeiboAuthenticationService(String apiKey, String appSecret, String appNamespace) {
		super(new WeiboConnectionFactory(apiKey, appSecret, appNamespace));
	}

}
