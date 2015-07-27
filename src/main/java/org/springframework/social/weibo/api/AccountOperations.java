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

/**
 * AccountOperations
 * @author cuizuoli
 */
public interface AccountOperations {

	/**
	 * 获取所有的学校列表
	 * @param province - 省份范围，省份ID。
	 * @param city - 城市范围，城市ID。
	 * @param area - 区域范围，区ID。
	 * @param type - 学校类型，1：大学、2：高中、3：中专技校、4：初中、5：小学，默认为1。
	 * @param capital - 学校首字母，默认为A。
	 * @param keyword - 学校名称关键字。
	 * @param count - 返回的记录条数，默认为10。
	 * @return
	 */
	Map<String, Object>[] getSchoolList(Integer province, Integer city, Integer area, Integer type, String capital,
			String keyword, Integer count);

	/**
	 * 获取当前登录用户的API访问频率限制情况
	 * @return
	 */
	Map<String, Object> getRateLimitStatus();

	/**
	 * OAuth授权之后，获取授权用户的UID
	 * @return
	 */
	Map<String, Object> getUid();

}
