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

	@SuppressWarnings("rawtypes")
	@Override
	public long getUid() {
		requireAuthorization();
		Map map = weiboApi.getObject("account/get_uid.json", Map.class);
		return Long.valueOf(map.get("uid").toString());
	}

}
