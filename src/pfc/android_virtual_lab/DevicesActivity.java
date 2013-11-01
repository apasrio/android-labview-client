package pfc.android_virtual_lab;

import java.util.Locale;

import pfc.android_virtual_lab.util.Constants;
import roboguice.activity.RoboFragmentActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DevicesActivity extends RoboFragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_devices);
		
		Bundle extras = getIntent().getExtras();
		extras.get(Constants.AG33220A_STATUS);
		extras.get(Constants.HP33120A_STATUS);
		extras.get(Constants.HP34401A_STATUS);
		extras.get(Constants.HP54602B_STATUS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager(), extras.getBoolean(Constants.AG33220A_STATUS),
				extras.getBoolean(Constants.HP33120A_STATUS),
				extras.getBoolean(Constants.HP34401A_STATUS),
				extras.getBoolean(Constants.HP54602B_STATUS));

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.devices, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		boolean ag33220aFlag, hp33120aFlag, hp34401aFlag, hp54602bFlag;

		public SectionsPagerAdapter(FragmentManager fm, boolean ag33220a,
				boolean hp33120a, boolean hp34401a, boolean hp54602b) {
			super(fm);
			this.ag33220aFlag = ag33220a;
			this.hp33120aFlag = hp33120a;
			this.hp34401aFlag = hp34401a;
			this.hp54602bFlag = hp54602b;
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment;
			switch(position){
			case 0:
				fragment = new AG33120aFragment();
				Bundle device1Args = new Bundle();
				device1Args.putBoolean(Constants.AG33220A_STATUS, ag33220aFlag);
				fragment.setArguments(device1Args);
				break;
			case 1:
				fragment = new HP33120aFragment();
				Bundle device2Args = new Bundle();
				device2Args.putBoolean(Constants.HP33120A_STATUS, hp33120aFlag);
				fragment.setArguments(device2Args);
				break;
			case 2:
				fragment = new HP34401aFragment();
				Bundle device3Args = new Bundle();
				device3Args.putBoolean(Constants.HP34401A_STATUS, hp34401aFlag);
				fragment.setArguments(device3Args);
				break;
			case 3:
				fragment = new HP54602bFragment();
				Bundle device4Args = new Bundle();
				device4Args.putBoolean(Constants.HP54602B_STATUS, hp54602bFlag);
				fragment.setArguments(device4Args);
				break;
			default:
				fragment = new DummySectionFragment();
				Bundle args = new Bundle();
				args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args);
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 4 total pages.
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			case 3:
				return getString(R.string.title_section4).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_devices_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

}
