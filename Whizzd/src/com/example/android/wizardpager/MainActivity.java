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

import com.example.android.wizardpager.wizard.model.AbstractWizardModel;
import com.example.android.wizardpager.wizard.model.CustomerInfoPage;
import com.example.android.wizardpager.wizard.model.ModelCallbacks;
import com.example.android.wizardpager.wizard.model.Page;
import com.example.android.wizardpager.wizard.ui.PageFragmentCallbacks;
import com.example.android.wizardpager.wizard.ui.ReviewFragment;
import com.example.android.wizardpager.wizard.ui.StepPagerStrip;
import com.matesnetwork.cogdemov2.MainActivity2;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import delivery.dummyorder.R;

public class MainActivity extends FragmentActivity implements
		PageFragmentCallbacks, ReviewFragment.Callbacks, ModelCallbacks {
	private ViewPager mPager;
	private MyPagerAdapter mPagerAdapter;

	private boolean mEditingAfterReview;

	private AbstractWizardModel mWizardModel = new SandwichWizardModel(this);

	private boolean mConsumePageSelectedEvent;

	private Button mNextButton;
	private Button mPrevButton;
	String credit, debit;
	Integer total = 1000;
	private List<Page> mCurrentPageSequence;
	private StepPagerStrip mStepPagerStrip;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);

		if (savedInstanceState != null) {
			mWizardModel.load(savedInstanceState.getBundle("model"));
		}

		mWizardModel.registerListener(this);

		mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mPagerAdapter);
		mStepPagerStrip = (StepPagerStrip) findViewById(R.id.strip);
		mStepPagerStrip
				.setOnPageSelectedListener(new StepPagerStrip.OnPageSelectedListener() {
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
					// String data =
					// mWizardModel.findByKey("Salad type:Dressing").getData().getString(MultipleFixedChoicePage.SIMPLE_DATA_KEY);

					final String name = mWizardModel.findByKey("Your info")
							.getData()
							.getString(CustomerInfoPage.NAME_DATA_KEY);
					final String asaddress = mWizardModel
							.findByKey("Your info").getData()
							.getString(CustomerInfoPage.ADDRESS_DATA_KEY);
					final String asaphone = mWizardModel.findByKey("Your info")
							.getData()
							.getString(CustomerInfoPage.APHONE_DATA_KEY);
					final String asarea = mWizardModel.findByKey("Your info")
							.getData()
							.getString(CustomerInfoPage.AREA_DATA_KEY);
					final String asbranch = mWizardModel.findByKey("Your info")
							.getData()
							.getString(CustomerInfoPage.BRANCH_DATA_KEY);
					final String asemail = mWizardModel.findByKey("Your info")
							.getData()
							.getString(CustomerInfoPage.EMAIL_DATA_KEY);
					final String aspassword = mWizardModel
							.findByKey("Your info").getData()
							.getString(CustomerInfoPage.PASSWORD_DATA_KEY);
					final String asphone = mWizardModel.findByKey("Your info")
							.getData()
							.getString(CustomerInfoPage.PHONE_DATA_KEY);
					final String asrname = mWizardModel.findByKey("Your info")
							.getData()
							.getString(CustomerInfoPage.RNAME_DATA_KEY);
					final String assname = mWizardModel.findByKey("Your info")
							.getData()
							.getString(CustomerInfoPage.SNAME_DATA_KEY);
					final String asuname = mWizardModel.findByKey("Your info")
							.getData()
							.getString(CustomerInfoPage.USERNAME_DATA_KEY);

					final String type = mWizardModel.findByKey("Login Type")
							.getData().getString(Page.SIMPLE_DATA_KEY);
					final String mode = mWizardModel
							.findByKey("Agent:Mode of Delivery").getData()
							.getString(Page.SIMPLE_DATA_KEY);
					final String boytype = mWizardModel
							.findByKey("Agent:Are you a?").getData()
							.getString(Page.SIMPLE_DATA_KEY);
					final String supertype = mWizardModel
							.findByKey("Supervisor:Are you a?").getData()
							.getString(Page.SIMPLE_DATA_KEY);

					// Toast.makeText(getApplicationContext(), data+"data",
					// Toast.LENGTH_LONG).show();
					Toast.makeText(getApplicationContext(),
							"data" + aspassword, Toast.LENGTH_LONG).show();

					if (type.equals("Agent") && asuname != null
							&& asphone != null && asaphone != null
							&& asrname != null && boytype != null
							&& asemail != null && name != null
							&& asarea != null && asbranch != null
							&& asaddress != null && assname != null
							&& aspassword != null) {

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
						user.put("total", total);

						user.signUpInBackground(new SignUpCallback() {
							@Override
							public void done(ParseException e) {

								if (e != null) {

								} else if (e == null) {
									ParseObject agent = new ParseObject("agent");
									agent.put("type", type);
									agent.put("username", asuname);
									agent.put("user",
											ParseUser.getCurrentUser());
								
									agent.put("mode", mode);
									agent.put("boytype", boytype);
									agent.put("resturantname", asrname);
									agent.put("supervisorname", assname);
									agent.put("agentid", user.getObjectId());
									agent.put("name", name);
									agent.put("email", asemail);
									agent.put("password", aspassword);
									agent.put("phone", asphone);
									agent.put("phone1", asaphone);
									agent.put("area", asarea);
									agent.put("address", asaddress);
									agent.put("branch", asbranch);
									agent.put("credit", "0.0");
									agent.put("debit", "0.0");
									agent.put("total", total);

									agent.saveInBackground();
									Intent i = new Intent(MainActivity.this,
											MainActivity2.class);
									startActivity(i);
									Toast.makeText(getApplicationContext(),
											" Agent registerd Successfully ",
											Toast.LENGTH_LONG).show();
									Toast.makeText(getApplicationContext(),
											user.getObjectId(),
											Toast.LENGTH_LONG).show();

								}

							}
						});

					} else if (type.equals("Supervisor") && asuname != null
							&& asphone != null && asaphone != null
							&& asrname != null && supertype != null
							&& asemail != null && name != null
							&& asarea != null && asbranch != null
							&& asaddress != null && assname != null) {
						final ParseUser user = new ParseUser();
						user.setUsername(asphone);
						user.setPassword(aspassword);
						user.put("name", type);
						user.put("supertype", supertype);
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
									ParseObject sup1 = new ParseObject(
											"supervisor");
									sup1.put("type", type);
									sup1.put("supertype", supertype);
									sup1.put("username", asuname);
									sup1.put("user", ParseUser.getCurrentUser());
									sup1.put("resturantname", asrname);
									sup1.put("supervisorname", assname);
									sup1.put("resturantname", asrname);
									sup1.put("name", name);
									sup1.put("email", asemail);
									sup1.put("password", aspassword);
									sup1.put("supervisorid", user.getObjectId());
									sup1.put("phone", asphone);
									sup1.put("phone1", asaphone);
									sup1.put("area", asarea);
									sup1.put("address", asaddress);
									sup1.put("branch", asaddress);
									sup1.put("credit", "0.0");
									sup1.put("debit", "0.0");
									sup1.put("total", "0.0");
									sup1.saveInBackground();
									Intent i = new Intent(MainActivity.this,
											MainActivity2.class);
									startActivity(i);
									Toast.makeText(
											getApplicationContext(),
											" supervisor registerd Successfully ",
											Toast.LENGTH_LONG).show();
								}
							}
						});

					} else {
						Toast.makeText(getApplicationContext(),
								" one or more fields empty ", Toast.LENGTH_LONG)
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
	public AbstractWizardModel onGetModel() {
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
	public void onPageDataChanged(Page page) {
		if (page.isRequired()) {
			if (recalculateCutOffPage()) {
				mPagerAdapter.notifyDataSetChanged();
				updateBottomBar();
			}
		}
	}

	@Override
	public Page onGetPage(String key) {
		return mWizardModel.findByKey(key);
	}

	private boolean recalculateCutOffPage() {
		// Cut off the pager adapter at first required page that isn't completed
		int cutOffPage = mCurrentPageSequence.size() + 1;
		for (int i = 0; i < mCurrentPageSequence.size(); i++) {
			Page page = mCurrentPageSequence.get(i);
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
				return new ReviewFragment();
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
