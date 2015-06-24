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

package com.example.android.wizardpager1.wizard.model1;

import com.example.android.wizardpager1.wizard.ui1.SingleChoiceFragment1;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A page offering the user a number of mutually exclusive choices.
 */
public class SingleFixedChoicePage1 extends Page1 {
    protected ArrayList<String> mChoices = new ArrayList<String>();

    public SingleFixedChoicePage1(ModelCallbacks1 callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment() {
        return SingleChoiceFragment1.create(getKey());
    }

    public String getOptionAt(int position) {
        return mChoices.get(position);
    }

    public int getOptionCount() {
        return mChoices.size();
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem1> dest) {
        dest.add(new ReviewItem1(getTitle(), mData.getString(SIMPLE_DATA_KEY), getKey()));
    }

    @Override
    public boolean isCompleted() {
        return !TextUtils.isEmpty(mData.getString(SIMPLE_DATA_KEY));
    }

    public SingleFixedChoicePage1 setChoices(String... choices) {
        mChoices.addAll(Arrays.asList(choices));
        return this;
    }

    public SingleFixedChoicePage1 setValue(String value) {
        mData.putString(SIMPLE_DATA_KEY, value);
        return this;
    }
}
