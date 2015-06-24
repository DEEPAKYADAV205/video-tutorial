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

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.wizardpager.wizard.model.Page;
import com.example.android.wizardpager1.wizard.model1.AbstractWizardModel1;
import com.example.android.wizardpager1.wizard.model1.CustomerInfoPage1;
import com.example.android.wizardpager1.wizard.model1.ModelCallbacks1;
import com.example.android.wizardpager1.wizard.model1.Page1;
import com.example.android.wizardpager1.wizard.ui1.PageFragmentCallbacks1;
import com.example.android.wizardpager1.wizard.ui1.ReviewFragment1;
import com.example.android.wizardpager1.wizard.ui1.StepPagerStrip1;
import com.matesnetwork.cogdemov2.MainActivity2;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import delivery.dummyorder.R;

public class MainActivity1 extends FragmentActivity implements
		PageFragmentCallbacks1, ReviewFragment1.Callbacks, ModelCallbacks1 {
	private ViewPager mPager;
	private MyPagerAdapter mPagerAdapter;

	private boolean mEditingAfterReview;

	private AbstractWizardModel1 mWizardModel = new SandwichWizardModel1(this);

	private boolean mConsumePageSelectedEvent;

	private Button mNextButton;
	private Button mPrevButton;
	Integer total=1000;

	private List<Page1> mCurrentPageSequence;
	private StepPagerStrip1 mStepPagerStrip;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main11);

		if (savedInstanceState != null) {
			mWizardModel.load(savedInstanceState.getBundle("model"));
		}

		mWizardModel.registerListener(this);

		mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mPagerAdapter);
		mStepPagerStrip = (StepPagerStrip1) findViewById(R.id.strip);
		mStepPagerStrip
				.setOnPageSelectedListener(new StepPagerStrip1.OnPageSelectedListener() {
					@Override
					public void onPageStripSelected(int position) {
						position = Math.min(mPagerAdapter.getCount() - 1,
								position);
						if (mPager.getCurrentItem() != position) {
							mPager.setCurrentItem(position);
						}
					}
				});

		mNextButton = (Button) findViewById(R.id.next_button);
		mPrevButton = (Button) findViewById(R.id.prev_button);

		mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				mStepPagerStrip.setCurrentPage(position);

				if (mConsumePageSelectedEvent) {
					mConsumePageSelectedEvent = false;
					return;
				}

				mEditingAfterReview = false;
				updateBottomBar();
			}
		});

		mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mPager.getCurrentItem() == mCurrentPageSequence.size()) {

					final String name = mWizardModel
							.findByKey("Resturant info").getData()
							.getString(CustomerInfoPage1.NAME_DATA_KEY);
					final String asaddress = mWizardModel
							.findByKey("Resturant info").getData()
							.getString(CustomerInfoPage1.ADDRESS_DATA_KEY);
					final String asaphone = mWizardModel
							.findByKey("Resturant info").getData()
							.getString(CustomerInfoPage1.APHONE_DATA_KEY);
					final String asarea = mWizardModel
							.findByKey("Resturant info").getData()
							.getString(CustomerInfoPage1.AREA_DATA_KEY);
					final String asbranch = mWizardModel
							.findByKey("Resturant info").getData()
							.getString(CustomerInfoPage1.BRANCH_DATA_KEY);
					final String asemail = mWizardModel
							.findByKey("Resturant info").getData()
							.getString(CustomerInfoPage1.EMAIL_DATA_KEY);
					final String aspassword = mWizardModel
							.findByKey("Resturant info").getData()
							.getString(CustomerInfoPage1.PASSWORD_DATA_KEY);
					final String asphone = mWizardModel
							.findByKey("Resturant info").getData()
							.getString(CustomerInfoPage1.PHONE_DATA_KEY);
					final String asrname = mWizardModel
							.findByKey("Resturant info").getData()
							.getString(CustomerInfoPage1.RNAME_DATA_KEY);
					final String assname = mWizardModel
							.findByKey("Resturant info").getData()
							.getString(CustomerInfoPage1.SNAME_DATA_KEY);

				

					final String type = mWizardModel.findByKey("Login Type")
							.getData().getString(Page.SIMPLE_DATA_KEY);

					final String resttype = mWizardModel
							.findByKey("Resturant:Resturant type").getData()
							.getString(Page.SIMPLE_DATA_KEY);
					if (type.equals("Resturant") && asphone != null
							&& asaphone != null && asrname != null
							&& resttype != null && asemail != null
							&& name != null && asarea != null
							&& asbranch != null && asaddress != null
							&& assname != null) {

						final ParseUser user = new ParseUser();
						user.setUsername(asphone);
						user.setPassword(aspassword);
						user.put("name", type);
						user.put("phone", asphone);
						user.put("rname", asrname);
						user.put("sname", assname);
						user.setEmail(asemail);
						user.put("area", asarea);
						user.put("address", asaddress);
						user.put("branch", asbranch);
						user.put("credit", "0.0");
						user.put("debit", "0.0");
						user.put("total", "0.0");

						user.signUpInBackground(new SignUpCallback() {
							@Override
							public void done(ParseException e) {

								if (e != null) {

								} else if (e == null) {
									ParseObject resturant = new ParseObject(
											"resturant");
									resturant.put("type", type);
									resturant.put("name", name);
									resturant.put("user",
											ParseUser.getCurrentUser());

									resturant.put("resturantname", asrname);
									resturant.put("supervisorname", assname);

									resturant.put("owner_name", name);
									resturant.put("email", asemail);
									resturant.put("password", aspassword);
									resturant.put("phone", asphone);
									resturant.put("phone1", asaphone);
									resturant.put("area", asarea);
									resturant.put("resturantid", user.getObjectId());
									resturant.put("address", asaddress);
									resturant.put("branch", asbranch);
									resturant.put("credit", "0.0");
									resturant.put("debit", "0.0");
									resturant.put("total", total);
									resturant
											.put("organisation_type", resttype);
								
								
									resturant.saveInBackground();
									Intent i = new Intent(MainActivity1.this,
											MainActivity2.class);
									startActivity(i);

								}

							}
						});

					} else {
						Toast.makeText(getApplicationContext(),
								" one or more fields empty", Toast.LENGTH_LONG)
								.show();
					}

				} else {
					if (mEditingAfterReview) {
						mPager.setCurrentItem(mPagerAdapter.getCount() - 1);
					} else {
						mPager.setCurrentItem(mPager.getCurrentItem() + 1);
					}
				}
			}
		});

		mPrevButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mPager.setCurrentItem(mPager.getCurrentItem() - 1);
			}
		});

		onPageTreeChanged();
		updateBottomBar();
	}

	@Override
	public void onPageTreeChanged() {
		mCurrentPageSequence = mWizardModel.getCurrentPageSequence();
		recalculateCutOffPage();
		mStepPagerStrip.setPageCount(mCurrentPageSequence.size() + 1); // + 1 =
																		// review
																		// step
		mPagerAdapter.notifyDataSetChanged();
		updateBottomBar();
	}

	private void updateBottomBar() {
		int position = mPager.getCurrentItem();
		if (position == mCurrentPageSequence.size()) {
			mNextButton.setText(R.string.finish);
			mNextButton.setBackgroundResource(R.drawable.finish_background);
			mNextButton.setTextAppearance(this, R.style.TextAppearanceFinish);
		} else {
			mNextButton.setText(mEditingAfterReview ? R.string.review
					: R.string.next);
			mNextButton
					.setBackgroundResource(R.drawable.selectable_item_background);
			TypedValue v = new TypedValue();
			getTheme().resolveAttribute(android.R.attr.textAppearanceMedium, v,
					true);
			mNextButton.setTextAppearance(this, v.resourceId);
			mNextButton.setEnabled(position != mPagerAdapter.getCutOffPage());
		}

		mPrevButton
				.setVisibility(position <= 0 ? View.INVISIBLE : View.VISIBLE);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mWizardModel.unregisterListener(this);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBundle("model", mWizardModel.save());
	}

	@Override
	public AbstractWizardModel1 onGetModel() {
		return mWizardModel;
	}

	@Override
	public void onEditScreenAfterReview(String key) {
		for (int i = mCurrentPageSequence.size() - 1; i >= 0; i--) {
			if (mCurrentPageSequence.get(i).getKey().equals(key)) {
				mConsumePageSelectedEvent = true;
				mEditingAfterReview = true;
				mPager.setCurrentItem(i);
				updateBottomBar();
				break;
			}
		}
	}

	@Override
	public void onPageDataChanged(Page1 page) {
		if (page.isRequired()) {
			if (recalculateCutOffPage()) {
				mPagerAdapter.notifyDataSetChanged();
				updateBottomBar();
			}
		}
	}

	@Override
	public Page1 onGetPage(String key) {
		return mWizardModel.findByKey(key);
	}

	private boolean recalculateCutOffPage() {
		// Cut off the pager adapter at first required page that isn't completed
		int cutOffPage = mCurrentPageSequence.size() + 1;
		for (int i = 0; i < mCurrentPageSequence.size(); i++) {
			Page1 page = mCurrentPageSequence.get(i);
			if (page.isRequired() && !page.isCompleted()) {
				cutOffPage = i;
				break;
			}
		}

		if (mPagerAdapter.getCutOffPage() != cutOffPage) {
			mPagerAdapter.setCutOffPage(cutOffPage);
			return true;
		}

		return false;
	}

	public class MyPagerAdapter extends FragmentStatePagerAdapter {
		private int mCutOffPage;
		private Fragment mPrimaryItem;

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			if (i >= mCurrentPageSequence.size()) {
				return new ReviewFragment1();
			}

			return mCurrentPageSequence.get(i).createFragment();
		}

		@Override
		public int getItemPosition(Object object) {
			// TODO: be smarter about this
			if (object == mPrimaryItem) {
				// Re-use the current fragment (its position never changes)
				return POSITION_UNCHANGED;
			}

			return POSITION_NONE;
		}

		@Override
		public void setPrimaryItem(ViewGroup container, int position,
				Object object) {
			super.setPrimaryItem(container, position, object);
			mPrimaryItem = (Fragment) object;
		}

		@Override
		public int getCount() {
			if (mCurrentPageSequence == null) {
				return 0;
			}
			return Math.min(mCutOffPage + 1, mCurrentPageSequence.size() + 1);
		}

		public void setCutOffPage(int cutOffPage) {
			if (cutOffPage < 0) {
				cutOffPage = Integer.MAX_VALUE;
			}
			mCutOffPage = cutOffPage;
		}

		public int getCutOffPage() {
			return mCutOffPage;
		}
	}
}
