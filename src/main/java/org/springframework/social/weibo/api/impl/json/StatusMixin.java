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

import java.util.Date;

import org.springframework.social.weibo.api.Visible;
import org.springframework.social.weibo.api.WeiboProfile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Annotated mixin to add Jackson annotations to Status.
 * @author cuizuoli
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class StatusMixin extends WeiboObjectMixin {

	@JsonProperty("created_at")
	@JsonDeserialize(using = WeiboDateTimeDeserializer.class)
	Date createdAt;

	@JsonProperty("id")
	Long id;

	@JsonProperty("mid")
	Long mid;

	@JsonProperty("idstr")
	Long idstr;

	@JsonProperty("text")
	String text;

	@JsonProperty("source")
	String source;

	@JsonProperty("favorited")
	Boolean favorited;

	@JsonProperty("truncated")
	Boolean truncated;

	@JsonProperty("in_reply_to_status_id")
	String inReplyToStatusId;

	@JsonProperty("in_reply_to_user_id")
	String inReplyToUserId;

	@JsonProperty("in_reply_to_screen_name")
	String inReplyToScreenName;

	@JsonProperty("pic_urls")
	String[] picUrls;

	@JsonProperty("geo")
	String geo;

	@JsonProperty("user")
	WeiboProfile user;

	@JsonProperty("reposts_count")
	Integer repostsCount;

	@JsonProperty("comments_count")
	Integer commentsCount;

	@JsonProperty("attitudes_count")
	Integer attitudesCount;

	@JsonProperty("mlevel")
	Integer mlevel;

	@JsonProperty("visible")
	Visible visible;

	@JsonProperty("darwin_tags")
	String[] darwinTags;

}
