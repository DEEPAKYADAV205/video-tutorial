/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.wizardpager.wizard.model;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.example.android.wizardpager.wizard.ui.CustomerInfoFragment;

/**
 * A page asking for a name and an email.
 */
public class CustomerInfoPage extends Page {
	public static final String NAME_DATA_KEY = "name";
	public static final String EMAIL_DATA_KEY = "email";
	public static final String PHONE_DATA_KEY = "phone";
	public static final String APHONE_DATA_KEY = "aphone";
	public static final String BRANCH_DATA_KEY = "branch";
	public static final String AREA_DATA_KEY = "area";
	public static final String RNAME_DATA_KEY = "resturant name";
	public static final String SNAME_DATA_KEY = "supervisor name";
	public static final String ADDRESS_DATA_KEY = "address";
	public static final String PASSWORD_DATA_KEY = "password";
	public static final String USERNAME_DATA_KEY = "username";
	public static final String CPASSWORD_DATA_KEY = "password";

	public CustomerInfoPage(ModelCallbacks callbacks, String title) {
		super(callbacks, title);
	}

	@Override
	public Fragment createFragment() {
		return CustomerInfoFragment.create(getKey());
	}

	@Override
	public void getReviewItems(ArrayList<ReviewItem> dest) {
		dest.add(new ReviewItem("Your name", mData.getString(NAME_DATA_KEY),
				getKey(), -1));
		dest.add(new ReviewItem("Your email", mData.getString(EMAIL_DATA_KEY),
				getKey(), -1));
		dest.add(new ReviewItem("Your phone", mData.getString(PHONE_DATA_KEY),
				getKey(), -1));
		dest.add(new ReviewItem("Your branch",
				mData.getString(BRANCH_DATA_KEY), getKey(), -1));
		dest.add(new ReviewItem("Your area", mData.getString(AREA_DATA_KEY),
				getKey(), -1));
		dest.add(new ReviewItem("Your resturant name", mData
				.getString(RNAME_DATA_KEY), getKey(), -1));
		dest.add(new ReviewItem("Your supervisor name", mData
				.getString(SNAME_DATA_KEY), getKey(), -1));
		dest.add(new ReviewItem("Your alternate phone", mData
				.getString(APHONE_DATA_KEY), getKey(), -1));
		dest.add(new ReviewItem("Your address", mData
				.getString(ADDRESS_DATA_KEY), getKey(), -1));
		dest.add(new ReviewItem("Your password", mData
				.getString(PASSWORD_DATA_KEY), getKey(), -1));
		dest.add(new ReviewItem("Your password", mData
				.getString(CPASSWORD_DATA_KEY), getKey(), -1));
		dest.add(new ReviewItem("Your username", mData
				.getString(USERNAME_DATA_KEY), getKey(), -1));
	}

	@Override
	public boolean isCompleted() {

		return !TextUtils.isEmpty(mData.getString(NAME_DATA_KEY));

	}
}
