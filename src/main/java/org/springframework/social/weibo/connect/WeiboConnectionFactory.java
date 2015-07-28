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

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.weibo.api.Weibo;

/**
 * Weibo ConnectionFactory implementation.
 * @author cuizuoli
 */
public class WeiboConnectionFactory extends OAuth2ConnectionFactory<Weibo> {

	/**
	 * Creates a WeiboConnectionFactory for the given application ID and secret.
	 * @param appId The application's App ID as assigned by Weibo 
	 * @param appSecret The application's App Secret as assigned by Weibo
	 */
	public WeiboConnectionFactory(String appId, String appSecret) {
		super("weibo", new WeiboServiceProvider(appId, appSecret), new WeiboAdapter());
	}

}
