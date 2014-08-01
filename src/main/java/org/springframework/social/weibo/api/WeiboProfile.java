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
 * Model class containing a Weibo user's profile information.
 * @author cuizuoli
 */
public class WeiboProfile extends WeiboObject {
	private Long id;
	private String idstr;
	private Integer clzss;
	private String screenName;
	private String name;
	private Integer province;
	private Integer city;
	private String location;
	private String description;
	private String url;
	private String profileImageUrl;
	private String coverImage;
	private String profileUrl;
	private String domain;
	private String weihao;
	private String gender;
	private Integer followersCount;
	private Integer friendsCount;
	private Integer statusesCount;
	private Integer favouritesCount;
	private DateTime createdAt;
	private Boolean following;
	private Boolean allowAllActMsg;
	private Boolean geoEnabled;
	private Boolean verified;
	private Integer verifiedType;
	private String remark;
	private Status status;
	private Integer ptype;
	private Boolean allowAllComment;
	private String avatarLarge;
	private String avatarHd;
	private String verifiedReason;
	private String verifiedTrade;
	private String verifiedReasonUrl;
	private String verifiedSource;
	private String verifiedSourceUrl;
	private Boolean followMe;
	private Integer onlineStatus;
	private Integer biFollowersCount;
	private String lang;
	private Integer star;
	private Integer mbtype;
	private Integer mbrank;
	private Integer blockWord;
	private Integer blockApp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdstr() {
		return idstr;
	}

	public void setIdstr(String idstr) {
		this.idstr = idstr;
	}

	public Integer getClzss() {
		return clzss;
	}

	public void setClzss(Integer clzss) {
		this.clzss = clzss;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getWeihao() {
		return weihao;
	}

	public void setWeihao(String weihao) {
		this.weihao = weihao;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(Integer followersCount) {
		this.followersCount = followersCount;
	}

	public Integer getFriendsCount() {
		return friendsCount;
	}

	public void setFriendsCount(Integer friendsCount) {
		this.friendsCount = friendsCount;
	}

	public Integer getStatusesCount() {
		return statusesCount;
	}

	public void setStatusesCount(Integer statusesCount) {
		this.statusesCount = statusesCount;
	}

	public Integer getFavouritesCount() {
		return favouritesCount;
	}

	public void setFavouritesCount(Integer favouritesCount) {
		this.favouritesCount = favouritesCount;
	}

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Boolean getFollowing() {
		return following;
	}

	public void setFollowing(Boolean following) {
		this.following = following;
	}

	public Boolean getAllowAllActMsg() {
		return allowAllActMsg;
	}

	public void setAllowAllActMsg(Boolean allowAllActMsg) {
		this.allowAllActMsg = allowAllActMsg;
	}

	public Boolean getGeoEnabled() {
		return geoEnabled;
	}

	public void setGeoEnabled(Boolean geoEnabled) {
		this.geoEnabled = geoEnabled;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	public Integer getVerifiedType() {
		return verifiedType;
	}

	public void setVerifiedType(Integer verifiedType) {
		this.verifiedType = verifiedType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getPtype() {
		return ptype;
	}

	public void setPtype(Integer ptype) {
		this.ptype = ptype;
	}

	public Boolean getAllowAllComment() {
		return allowAllComment;
	}

	public void setAllowAllComment(Boolean allowAllComment) {
		this.allowAllComment = allowAllComment;
	}

	public String getAvatarLarge() {
		return avatarLarge;
	}

	public void setAvatarLarge(String avatarLarge) {
		this.avatarLarge = avatarLarge;
	}

	public String getAvatarHd() {
		return avatarHd;
	}

	public void setAvatarHd(String avatarHd) {
		this.avatarHd = avatarHd;
	}

	public String getVerifiedReason() {
		return verifiedReason;
	}

	public void setVerifiedReason(String verifiedReason) {
		this.verifiedReason = verifiedReason;
	}

	public String getVerifiedTrade() {
		return verifiedTrade;
	}

	public void setVerifiedTrade(String verifiedTrade) {
		this.verifiedTrade = verifiedTrade;
	}

	public String getVerifiedReasonUrl() {
		return verifiedReasonUrl;
	}

	public void setVerifiedReasonUrl(String verifiedReasonUrl) {
		this.verifiedReasonUrl = verifiedReasonUrl;
	}

	public String getVerifiedSource() {
		return verifiedSource;
	}

	public void setVerifiedSource(String verifiedSource) {
		this.verifiedSource = verifiedSource;
	}

	public String getVerifiedSourceUrl() {
		return verifiedSourceUrl;
	}

	public void setVerifiedSourceUrl(String verifiedSourceUrl) {
		this.verifiedSourceUrl = verifiedSourceUrl;
	}

	public Boolean getFollowMe() {
		return followMe;
	}

	public void setFollowMe(Boolean followMe) {
		this.followMe = followMe;
	}

	public Integer getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(Integer onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public Integer getBiFollowersCount() {
		return biFollowersCount;
	}

	public void setBiFollowersCount(Integer biFollowersCount) {
		this.biFollowersCount = biFollowersCount;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Integer getMbtype() {
		return mbtype;
	}

	public void setMbtype(Integer mbtype) {
		this.mbtype = mbtype;
	}

	public Integer getMbrank() {
		return mbrank;
	}

	public void setMbrank(Integer mbrank) {
		this.mbrank = mbrank;
	}

	public Integer getBlockWord() {
		return blockWord;
	}

	public void setBlockWord(Integer blockWord) {
		this.blockWord = blockWord;
	}

	public Integer getBlockApp() {
		return blockApp;
	}

	public void setBlockApp(Integer blockApp) {
		this.blockApp = blockApp;
	}
}
