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

import org.springframework.social.weibo.api.UserOperations;
import org.springframework.social.weibo.api.WeiboApiOperations;
import org.springframework.social.weibo.api.WeiboProfile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * UserTemplate
 * @author cuizuoli
 */
class UserTemplate extends AbstractWeiboOperations implements UserOperations {

	private final WeiboApiOperations weiboApi;

	public UserTemplate(WeiboApiOperations weiboApi, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.weiboApi = weiboApi;
	}

	@Override
	public WeiboProfile getUserProfile(long userId) {
		MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<String, String>();
		queryMap.add("userId", String.valueOf(userId));
		return weiboApi.getObject("users/show.json", WeiboProfile.class, queryMap);
	}

	@Override
	public WeiboProfile getUserProfile(String domain) {
		MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<String, String>();
		queryMap.add("domain", domain);
		return weiboApi.getObject("users/domain_show", WeiboProfile.class, queryMap);
	}

}
