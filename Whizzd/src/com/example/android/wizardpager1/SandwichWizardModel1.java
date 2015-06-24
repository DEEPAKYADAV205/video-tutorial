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

package com.example.android.wizardpager1;

import android.content.Context;

import com.example.android.wizardpager1.wizard.model1.AbstractWizardModel1;
import com.example.android.wizardpager1.wizard.model1.BranchPage1;
import com.example.android.wizardpager1.wizard.model1.CustomerInfoPage1;
import com.example.android.wizardpager1.wizard.model1.PageList1;
import com.example.android.wizardpager1.wizard.model1.SingleFixedChoicePage1;

public class SandwichWizardModel1 extends AbstractWizardModel1 {
	public SandwichWizardModel1(Context context) {
		super(context);
	}

	@Override
	protected PageList1 onNewRootPageList() {
		return new PageList1(new BranchPage1(this, "Login Type")

		.addBranch(
				"Resturant",
				new SingleFixedChoicePage1(this, "Resturant type").setChoices(
						"Individual food Resturant", "Food Resturant Chain")
						.setRequired(true))

		.setRequired(true),

		new CustomerInfoPage1(this, "Resturant info").setRequired(true));
	}
}
