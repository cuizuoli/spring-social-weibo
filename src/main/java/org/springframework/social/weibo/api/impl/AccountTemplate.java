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

import java.util.Map;

import org.springframework.social.weibo.api.AccountOperations;
import org.springframework.social.weibo.api.WeiboApiOperations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * AccountTemplate
 * @author cuizuoli
 */
public class AccountTemplate extends AbstractWeiboOperations implements AccountOperations {

	private final WeiboApiOperations weiboApi;

	public AccountTemplate(WeiboApiOperations weiboApi, boolean isAuthorizedForUser) {
		super(isAuthorizedForUser);
		this.weiboApi = weiboApi;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object>[] getSchoolList(Integer province, Integer city, Integer area, Integer type,
			String capital, String keyword, Integer count) {
		MultiValueMap<String, String> queryMap = new LinkedMultiValueMap<String, String>();
		if (province != null) {
			queryMap.add("province", String.valueOf(province));
		}
		if (city != null) {
			queryMap.add("city", String.valueOf(city));
		}
		if (area != null) {
			queryMap.add("area", String.valueOf(area));
		}
		if (type != null) {
			queryMap.add("type", String.valueOf(type));
		}
		if (capital != null) {
			queryMap.add("capital", String.valueOf(capital));
		}
		if (keyword != null) {
			queryMap.add("keyword", String.valueOf(keyword));
		}
		if (count != null) {
			queryMap.add("count", String.valueOf(count));
		}
		return weiboApi.getObject("account/profile/school_list.json", Map[].class, queryMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getRateLimitStatus() {
		return weiboApi.getObject("account/rate_limit_status.json", Map.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getUid() {
		return weiboApi.getObject("account/get_uid.json", Map.class);
	}

}
