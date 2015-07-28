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

import java.util.Map;

/**
 * TokenOperations
 * @author cuizuoli
 */
public interface TokenOperations {

	/**
	 * 解析站内应用post的SignedRequest split为part1和part2两部分
	 * @param signedRequest
	 * @param appSecret
	 * @return
	 */
	Map<String, Object> getPageTokenInfo(String signedRequest, String appSecret);

	/**
	 * 查询用户access_token的授权相关信息，包括授权时间，过期时间和scope权限。
	 * @return
	 */
	Map<String, Object> getTokenInfo();

	/**
	 * 授权回收接口，帮助开发者主动取消用户的授权。
	 * @return
	 */
	Map<String, Object> revokeOauth2();

}
