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

package com.example.android.wizardpager;

import android.content.Context;

import com.example.android.wizardpager.wizard.model.AbstractWizardModel;
import com.example.android.wizardpager.wizard.model.BranchPage;
import com.example.android.wizardpager.wizard.model.CustomerInfoPage;
import com.example.android.wizardpager.wizard.model.PageList;
import com.example.android.wizardpager.wizard.model.SingleFixedChoicePage;
import com.example.android.wizardpager1.wizard.model1.CustomerInfoPage1;

public class SandwichWizardModel extends AbstractWizardModel {
	public SandwichWizardModel(Context context) {
		super(context);
	}

	@Override
	protected PageList onNewRootPageList() {
		return new PageList(
				new BranchPage(this, "Login Type")
						.addBranch(
								"Agent",
								new SingleFixedChoicePage(this,
										"Mode of Delivery")
										.setChoices("Car", "Bike", "Bicycle",
												"Rikshaw", "On Feet")
										.setRequired(true),

								new SingleFixedChoicePage(this, "Are you a?")
										.setChoices("FreeLancer Delivery",
												"Resturant Delivery Agent")
										.setRequired(true))
							

						.addBranch(
								"Supervisor",
								new SingleFixedChoicePage(this, "Are you a?")
										.setChoices("Organisation Supervisor",
												"freelancer").setRequired(true))
								
						

						.setRequired(true),

				new CustomerInfoPage(this, "Your info").setRequired(true));
	}
}
