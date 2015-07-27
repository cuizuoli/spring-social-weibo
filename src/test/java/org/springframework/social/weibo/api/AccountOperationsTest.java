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

import org.junit.Test;

/**
 * AccountOperationsTest
 * @author cuizuoli
 */
public class AccountOperationsTest extends AbstractWeiboTest {

	@Test
	public void getSchoolList() {
		Map<String, Object>[] schools = weiboTemplate.accountOperations().getSchoolList(null, null, null, null, null, "东软", null);
		log.info(schools.toString());
	}

	@Test
	public void getRateLimitStatus() {
		Map<String, Object> rateLimitStatus = weiboTemplate.accountOperations().getRateLimitStatus();
		log.info(rateLimitStatus.toString());
	}

	@Test
	public void getUid() {
		Map<String, Object> userId = weiboTemplate.accountOperations().getUid();
		log.info(userId.toString());
	}

}
