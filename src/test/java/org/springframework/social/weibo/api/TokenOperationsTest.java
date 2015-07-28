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
 * TestCase for TokenOperations.
 * @author cuizuoli
 */
public class TokenOperationsTest extends AbstractWeiboTest {

	@Test
	public void getPageTokenInfo() {
		String signedRequest = "sFDyTb9MNnO9ONg8qOBAWGh9rnirdQ5rCDmzbQcMvoI.eyJ1c2VyIjp7ImNvdW50cnkiOiJjbiIsImxvY2FsZSI6IiIsInZlcnNpb24iOjV9LCJhbGdvcml0aG0iOiJITUFDLVNIQTI1NiIsImlzc3VlZF9hdCI6MTQzODA0NjEyMCwiZXhwaXJlcyI6MTU3NjgwMDAwLCJvYXV0aF90b2tlbiI6IjIuMDBSRFlvMUNXYzE5WkM2ZmI1MDczYWQwM2F2RW5EIiwidXNlcl9pZCI6MjEzOTgzOTY4MywicmVmZXJlciI6Imh0dHA6XC9cL29wZW4ud2VpYm8uY29tXC9hcHBzXC8yMzU2MTg5ODc2XC9pbmZvXC90ZXN0Iiwic2NvcGUiOiIiLCJleHRfZGF0YSI6IiIsIm91aWQiOiIyMTM5ODM5NjgzIiwib3JpZ2luIjoidGVzdF9wcmV2aWV3In0";
		Map<String, Object> tokenInfo = weiboTemplate.tokenOperations().getPageTokenInfo(signedRequest, "aec9541f26933b82f4a9b3f2f0c3b610");
		log.info(tokenInfo.toString());
	}

	@Test
	public void getTokenInfo() {
		Map<String, Object> tokenInfo = weiboTemplate.tokenOperations().getTokenInfo();
		log.info(tokenInfo.toString());
	}

	@Test
	public void revokeOauth2() {
		Map<String, Object> result = weiboTemplate.tokenOperations().revokeOauth2();
		log.info(result.toString());
	}

}
