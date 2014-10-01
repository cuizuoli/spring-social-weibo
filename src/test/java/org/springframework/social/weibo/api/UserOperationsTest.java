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

import org.junit.Test;

/**
 * UserOperationsTest
 * @author cuizuoli
 */
public class UserOperationsTest extends AbstractWeiboTest {

	@Test
	public void getUserProfileById() {
		WeiboProfile weiboProfile = weiboTemplate.userOperations().getUserProfileById(1904178193);
		log.info("weiboProfile - " + weiboProfile);
	}

	@Test
	public void getUserProfileByName() {
		WeiboProfile weiboProfile = weiboTemplate.userOperations().getUserProfileByName("微博开放平台");
		log.info("weiboProfile - " + weiboProfile);
	}

	@Test
	public void getUserProfileByDomain() {
		WeiboProfile weiboProfile = weiboTemplate.userOperations().getUserProfileByDomain("openapi");
		log.info("weiboProfile - " + weiboProfile);
	}

}
