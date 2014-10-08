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
package org.springframework.social.weibo.api.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Annotated mixin to add Jackson annotations to PageTokenInfo.
 * @author cuizuoli
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class PageTokenInfoMixin extends WeiboObjectMixin {

	@JsonProperty("user")
	String user;

	@JsonProperty("algorithm")
	String algorithm;

	@JsonProperty("issued_at")
	int issuedAt;

	@JsonProperty("expires")
	int expires;

	@JsonProperty("oauth_token")
	String oauthToken;

	@JsonProperty("user_id")
	String userId;

	@JsonProperty("referer")
	String referer;

	@JsonProperty("origin")
	String origin;

	@JsonProperty("scope")
	String scope;

	@JsonProperty("ext_data")
	String extData;

	@JsonProperty("ouid")
	String ouid;

}
