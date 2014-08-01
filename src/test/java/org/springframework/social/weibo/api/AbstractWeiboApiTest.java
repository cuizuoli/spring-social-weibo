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
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.social.test.client.MockRestServiceServer;
import org.springframework.social.weibo.api.impl.WeiboTemplate;

/**
 * AbstractWeiboApiTest
 * @author cuizuoli
 */
public abstract class AbstractWeiboApiTest {

	protected static final String ACCESS_TOKEN = "someAccessToken";
	protected static final String APP_ACCESS_TOKEN = "123456|abcdefg987654321";

	protected WeiboTemplate weibo;
	protected WeiboTemplate unauthorizedWeibo;
	protected WeiboTemplate appWeibo;
	protected MockRestServiceServer mockServer;
	protected MockRestServiceServer unauthorizedMockServer;
	protected MockRestServiceServer appWeiboMockServer;

	@Before
	public void setup() {
		weibo = createWeiboTemplate();
		mockServer = MockRestServiceServer.createServer(weibo.getRestTemplate());

		unauthorizedWeibo = new WeiboTemplate();
		unauthorizedMockServer = MockRestServiceServer.createServer(unauthorizedWeibo.getRestTemplate());

		appWeibo = new WeiboTemplate(APP_ACCESS_TOKEN);
		appWeiboMockServer = MockRestServiceServer.createServer(appWeibo.getRestTemplate());
	}

	protected WeiboTemplate createWeiboTemplate() {
		return new WeiboTemplate(ACCESS_TOKEN);
	}

	protected Resource jsonResource(String filename) {
		return new ClassPathResource(filename + ".json", getClass());
	}

	protected DateTime toDateTime(String dateString) {
		return WEIBO_DATE_FORMAT.parseDateTime(dateString);
	}

	private static final DateTimeFormatter WEIBO_DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ");

}
