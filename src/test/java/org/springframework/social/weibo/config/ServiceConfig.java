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
package org.springframework.social.weibo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.weibo.api.WeiboApiOperations;
import org.springframework.social.weibo.api.impl.WeiboTemplate;

/**
 * ServiceConfig
 * @author cuizuoli
 */
@Configuration
public class ServiceConfig {

	private static final String ACCESS_TOKEN = "2.00RDYo1C8J8wTEe0d81b55a11MyF5E";

	@Bean
	public WeiboApiOperations weiboApi() {
		return new WeiboTemplate(ACCESS_TOKEN);
	}

}
