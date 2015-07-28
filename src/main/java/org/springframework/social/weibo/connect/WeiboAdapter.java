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

import java.util.Map;

import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.weibo.api.Weibo;

/**
 * Weibo ApiAdapter implementation.
 * @author cuizuoli
 */
public class WeiboAdapter implements ApiAdapter<Weibo> {

	@Override
	public boolean test(Weibo weibo) {
		try {
			Map<String, Object> userId = weibo.accountOperations().getUid();
			if (userId.get("uid") != null) {
				return true;
			}
			return false;
		} catch (ApiException e) {
			return false;
		}
	}

	@Override
	public void setConnectionValues(Weibo weibo, ConnectionValues values) {
		Map<String, Object> userId = weibo.accountOperations().getUid();
		Map<String, Object> profile = weibo.userOperations().getUserProfileById((Long)userId.get("uid"));
		values.setProviderUserId((String)profile.get("id"));
		values.setDisplayName((String)profile.get("screen_name"));
		values.setProfileUrl("http://weibo.com/" + (String)profile.get("profile_url"));
		values.setImageUrl((String)profile.get("profile_image_url"));
	}

	@Override
	public UserProfile fetchUserProfile(Weibo weibo) {
		Map<String, Object> userId = weibo.accountOperations().getUid();
		Map<String, Object> profile = weibo.userOperations().getUserProfileById((Long)userId.get("uid"));
		return new UserProfileBuilder()
			.setName((String)profile.get("screen_name"))
			.setUsername((String)profile.get("id"))
			.build();
	}

	@Override
	public void updateStatus(Weibo weibo, String message) {
		weibo.timelineOperations().updateStatus(message);
	}

}
