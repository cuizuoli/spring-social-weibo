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

import org.joda.time.DateTime;

/**
 * Status
 * @author cuizuoli
 */
public class Status extends WeiboObject {
	private DateTime createdAt;
	private Long id;
	private Long mid;
	private Long idstr;
	private String text;
	private String source;
	private Boolean favorited;
	private Boolean truncated;
	private String inReplyToStatusId;
	private String inReplyToUserId;
	private String inReplyToScreenName;
	private PicUrl[] picUrls;
	private String geo;
	private WeiboProfile user;
	private Integer repostsCount;
	private Integer commentsCount;
	private Integer attitudesCount;
	private Integer mlevel;
	private Visible visible;
	private String[] darwinTags;

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public Long getIdstr() {
		return idstr;
	}

	public void setIdstr(Long idstr) {
		this.idstr = idstr;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Boolean getFavorited() {
		return favorited;
	}

	public void setFavorited(Boolean favorited) {
		this.favorited = favorited;
	}

	public Boolean getTruncated() {
		return truncated;
	}

	public void setTruncated(Boolean truncated) {
		this.truncated = truncated;
	}

	public String getInReplyToStatusId() {
		return inReplyToStatusId;
	}

	public void setInReplyToStatusId(String inReplyToStatusId) {
		this.inReplyToStatusId = inReplyToStatusId;
	}

	public String getInReplyToUserId() {
		return inReplyToUserId;
	}

	public void setInReplyToUserId(String inReplyToUserId) {
		this.inReplyToUserId = inReplyToUserId;
	}

	public String getInReplyToScreenName() {
		return inReplyToScreenName;
	}

	public void setInReplyToScreenName(String inReplyToScreenName) {
		this.inReplyToScreenName = inReplyToScreenName;
	}

	public PicUrl[] getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(PicUrl[] picUrls) {
		this.picUrls = picUrls;
	}

	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}

	public WeiboProfile getUser() {
		return user;
	}

	public void setUser(WeiboProfile user) {
		this.user = user;
	}

	public Integer getRepostsCount() {
		return repostsCount;
	}

	public void setRepostsCount(Integer repostsCount) {
		this.repostsCount = repostsCount;
	}

	public Integer getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(Integer commentsCount) {
		this.commentsCount = commentsCount;
	}

	public Integer getAttitudesCount() {
		return attitudesCount;
	}

	public void setAttitudesCount(Integer attitudesCount) {
		this.attitudesCount = attitudesCount;
	}

	public Integer getMlevel() {
		return mlevel;
	}

	public void setMlevel(Integer mlevel) {
		this.mlevel = mlevel;
	}

	public Visible getVisible() {
		return visible;
	}

	public void setVisible(Visible visible) {
		this.visible = visible;
	}

	public String[] getDarwinTags() {
		return darwinTags;
	}

	public void setDarwinTags(String[] darwinTags) {
		this.darwinTags = darwinTags;
	}
}
