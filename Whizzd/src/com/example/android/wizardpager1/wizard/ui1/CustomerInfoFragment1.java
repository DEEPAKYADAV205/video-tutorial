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

package com.example.android.wizardpager1.wizard.ui1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.android.wizardpager.wizard.model.CustomerInfoPage;
import com.example.android.wizardpager1.wizard.model1.CustomerInfoPage1;

import delivery.dummyorder.R;

public class CustomerInfoFragment1 extends Fragment {
	private static final String ARG_KEY = "key";

	private PageFragmentCallbacks1 mCallbacks;
	private String mKey;
	private CustomerInfoPage1 mPage;
	private TextView mNameView;
	private TextView mEmailView, mAphoneView, mBranch, mAddress, mArea, mRname,
			mSname, mPassword;
	private TextView mPhoneView;

	public static CustomerInfoFragment1 create(String key) {
		Bundle args = new Bundle();
		args.putString(ARG_KEY, key);

		CustomerInfoFragment1 fragment = new CustomerInfoFragment1();
		fragment.setArguments(args);
		return fragment;
	}

	public CustomerInfoFragment1() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle args = getArguments();
		mKey = args.getString(ARG_KEY);
		mPage = (CustomerInfoPage1) mCallbacks.onGetPage(mKey);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_page_customer_info11, container, false);
		((TextView) rootView.findViewById(android.R.id.title)).setText(mPage
				.getTitle());

		mNameView = ((TextView) rootView.findViewById(R.id.your_name));
		mNameView.setText(mPage.getData().getString(
				CustomerInfoPage1.NAME_DATA_KEY));

		mEmailView = ((TextView) rootView.findViewById(R.id.your_email));
		mEmailView.setText(mPage.getData().getString(
				CustomerInfoPage1.EMAIL_DATA_KEY));
		mAphoneView = ((TextView) rootView
				.findViewById(R.id.your_alternatenumber));
		mAphoneView.setText(mPage.getData().getString(
				CustomerInfoPage1.APHONE_DATA_KEY));
		mBranch = ((TextView) rootView.findViewById(R.id.your_branch));
		mBranch.setText(mPage.getData().getString(
				CustomerInfoPage1.BRANCH_DATA_KEY));
		mAddress = ((TextView) rootView.findViewById(R.id.your_address));
		mAddress.setText(mPage.getData().getString(
				CustomerInfoPage1.ADDRESS_DATA_KEY));
		mArea = ((TextView) rootView.findViewById(R.id.your_area));
		mArea.setText(mPage.getData()
				.getString(CustomerInfoPage1.AREA_DATA_KEY));
		mRname = ((TextView) rootView.findViewById(R.id.your_resturant));
		mRname.setText(mPage.getData().getString(
				CustomerInfoPage1.RNAME_DATA_KEY));
		mSname = ((TextView) rootView.findViewById(R.id.your_supervisor));
		mSname.setText(mPage.getData().getString(
				CustomerInfoPage1.SNAME_DATA_KEY));
		mPhoneView = ((TextView) rootView.findViewById(R.id.your_phone));
		mPhoneView.setText(mPage.getData().getString(
				CustomerInfoPage1.PHONE_DATA_KEY));
		mPassword = ((TextView) rootView.findViewById(R.id.your_password));
		mPassword.setText(mPage.getData().getString(
				CustomerInfoPage1.PASSWORD_DATA_KEY));
		
		
	
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		if (!(activity instanceof PageFragmentCallbacks1)) {
			throw new ClassCastException(
					"Activity must implement PageFragmentCallbacks");
		}

		mCallbacks = (PageFragmentCallbacks1) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = null;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mNameView.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i,
					int i1, int i2) {
			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1,
					int i2) {
			}

			@Override
			public void afterTextChanged(Editable editable) {
				mPage.getData().putString(CustomerInfoPage1.NAME_DATA_KEY,
						(editable != null) ? editable.toString() : null);
				mPage.notifyDataChanged();
			}
		});

		mEmailView.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i,
					int i1, int i2) {
			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1,
					int i2) {
			}

			@Override
			public void afterTextChanged(Editable editable) {
				mPage.getData().putString(CustomerInfoPage1.EMAIL_DATA_KEY,
						(editable != null) ? editable.toString() : null);
				mPage.notifyDataChanged();
			}
		});
		 mAphoneView.addTextChangedListener(new TextWatcher() {
	            @Override
	            public void beforeTextChanged(CharSequence charSequence, int i, int i1,
	                    int i2) {
	            }

	            @Override
	            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
	            }

	            @Override
	            public void afterTextChanged(Editable editable) {
	                mPage.getData().putString(CustomerInfoPage1.APHONE_DATA_KEY,
	                        (editable != null) ? editable.toString() : null);
	                mPage.notifyDataChanged();
	            }
	        });
	        mPhoneView.addTextChangedListener(new TextWatcher() {
	            @Override
	            public void beforeTextChanged(CharSequence charSequence, int i, int i1,
	                    int i2) {
	            }

	            @Override
	            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
	            }

	            @Override
	            public void afterTextChanged(Editable editable) {
	                mPage.getData().putString(CustomerInfoPage1.PHONE_DATA_KEY,
	                        (editable != null) ? editable.toString() : null);
	                mPage.notifyDataChanged();
	            }
	        });
	        mArea.addTextChangedListener(new TextWatcher() {
	            @Override
	            public void beforeTextChanged(CharSequence charSequence, int i, int i1,
	                    int i2) {
	            }

	            @Override
	            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
	            }

	            @Override
	            public void afterTextChanged(Editable editable) {
	                mPage.getData().putString(CustomerInfoPage1.AREA_DATA_KEY,
	                        (editable != null) ? editable.toString() : null);
	                mPage.notifyDataChanged();
	            }
	        });
	        mBranch.addTextChangedListener(new TextWatcher() {
	            @Override
	            public void beforeTextChanged(CharSequence charSequence, int i, int i1,
	                    int i2) {
	            }

	            @Override
	            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
	            }

	            @Override
	            public void afterTextChanged(Editable editable) {
	                mPage.getData().putString(CustomerInfoPage1.BRANCH_DATA_KEY,
	                        (editable != null) ? editable.toString() : null);
	                mPage.notifyDataChanged();
	            }
	        });
	        mRname.addTextChangedListener(new TextWatcher() {
	            @Override
	            public void beforeTextChanged(CharSequence charSequence, int i, int i1,
	                    int i2) {
	            }

	            @Override
	            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
	            }

	            @Override
	            public void afterTextChanged(Editable editable) {
	                mPage.getData().putString(CustomerInfoPage1.RNAME_DATA_KEY,
	                        (editable != null) ? editable.toString() : null);
	                mPage.notifyDataChanged();
	            }
	        });
	        mSname.addTextChangedListener(new TextWatcher() {
	            @Override
	            public void beforeTextChanged(CharSequence charSequence, int i, int i1,
	                    int i2) {
	            }

	            @Override
	            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
	            }

	            @Override
	            public void afterTextChanged(Editable editable) {
	                mPage.getData().putString(CustomerInfoPage1.SNAME_DATA_KEY,
	                        (editable != null) ? editable.toString() : null);
	                mPage.notifyDataChanged();
	            }
	        });
	        mAddress.addTextChangedListener(new TextWatcher() {
	            @Override
	            public void beforeTextChanged(CharSequence charSequence, int i, int i1,
	                    int i2) {
	            }

	            @Override
	            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
	            }

	            @Override
	            public void afterTextChanged(Editable editable) {
	                mPage.getData().putString(CustomerInfoPage1.ADDRESS_DATA_KEY,
	                        (editable != null) ? editable.toString() : null);
	                mPage.notifyDataChanged();
	            }
	        });
	        mPassword.addTextChangedListener(new TextWatcher() {
	            @Override
	            public void beforeTextChanged(CharSequence charSequence, int i, int i1,
	                    int i2) {
	            }

	            @Override
	            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
	            }

	            @Override
	            public void afterTextChanged(Editable editable) {
	                mPage.getData().putString(CustomerInfoPage1.PASSWORD_DATA_KEY,
	                        (editable != null) ? editable.toString() : null);
	                mPage.notifyDataChanged();
	            }
	        });
	      
	      
	      
	}

	@Override
	public void setMenuVisibility(boolean menuVisible) {
		super.setMenuVisibility(menuVisible);

		// In a future update to the support library, this should override
		// setUserVisibleHint
		// instead of setMenuVisibility.
		if (mNameView != null) {
			InputMethodManager imm = (InputMethodManager) getActivity()
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			if (!menuVisible) {
				imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
			}
		}
	}
}
