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

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.weibo.api.Weibo;
import org.springframework.social.weibo.api.impl.WeiboTemplate;

/**
 * Weibo ServiceProvider implementation.
 * @author cuizuoli
 */
public class WeiboServiceProvider extends AbstractOAuth2ServiceProvider<Weibo> {

	private String appNamespace;

	/**
	 * Creates a WeiboServiceProvider for the given application ID, secret, and namespace.
	 * @param appId The application's App ID as assigned by Weibo 
	 * @param appSecret The application's App Secret as assigned by Weibo
	 * @param appNamespace The application's App Namespace as configured with Weibo. Enables use of Open Graph operations.
	 */
	public WeiboServiceProvider(String appId, String appSecret, String appNamespace) {
		super(new WeiboOAuth2Template(appId, appSecret));
		this.appNamespace = appNamespace;
	}

	public Weibo getApi(String accessToken) {
		return new WeiboTemplate(accessToken, appNamespace);
	}

}
