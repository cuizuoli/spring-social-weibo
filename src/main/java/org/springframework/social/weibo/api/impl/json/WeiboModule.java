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

import org.springframework.social.weibo.api.PageTokenInfo;
import org.springframework.social.weibo.api.PicUrl;
import org.springframework.social.weibo.api.Status;
import org.springframework.social.weibo.api.TokenInfo;
import org.springframework.social.weibo.api.Visible;
import org.springframework.social.weibo.api.WeiboAccessGrant;
import org.springframework.social.weibo.api.WeiboProfile;

import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Jackson module for setting up mixin annotations on Weibo model types. This enables the use of Jackson annotations without
 * directly annotating the model classes themselves.
 * @author cuizuoli
 */
public class WeiboModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	public WeiboModule() {
		super("WeiboModule");
	}

	@Override
	public void setupModule(SetupContext context) {
		context.setMixInAnnotations(TokenInfo.class, TokenInfoMixin.class);
		context.setMixInAnnotations(PageTokenInfo.class, PageTokenInfoMixin.class);
		context.setMixInAnnotations(WeiboAccessGrant.class, WeiboAccessGrantMixin.class);
		context.setMixInAnnotations(Visible.class, VisibleMixin.class);
		context.setMixInAnnotations(PicUrl.class, PicUrlMixin.class);
		context.setMixInAnnotations(Status.class, StatusMixin.class);
		context.setMixInAnnotations(WeiboProfile.class, WeiboProfileMixin.class);
	}

}
