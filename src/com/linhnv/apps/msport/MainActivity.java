package com.linhnv.apps.msport;

import com.linhnv.apps.msport.util.Utils;
import com.linhnv.apps.msport.view.MyMenuView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements TabListener {
	final static String[] TAB_NAME = { "Tin Tức ", "LiveScore ", "Tư vấn ",
			"Radio thể thao", "Video Clip ", "Ảnh ", "Sport TV ",
			"Mobile Game ", "Tài khoản ", "Trợ giúp " };

	private ViewPager mViewPager;
	private MyMenuView mMenuView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mMenuView = (MyMenuView) findViewById(R.id.myMenu);
		initSlideMenu();
		final ActionBar actionBar = getActionBar();
		actionBar.setHomeButtonEnabled(false);
		// Specify that we will be displaying tabs in the action bar.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		TabMenuAdapter menuAdapter = new TabMenuAdapter(
				getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(menuAdapter);
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						// When swiping between different app sections, select
						// the corresponding tab.
						// We can also use ActionBar.Tab#select() to do this if
						// we have a reference to the
						// Tab.
						hideMenu();
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < menuAdapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab()
					.setText(menuAdapter.getPageTitle(i)).setTabListener(this));
		}
		
		AsyncTask< Void, Void, Void> test = new AsyncTask<Void, Void, Void>(){

			@Override
			protected Void doInBackground(Void... arg0) {
				String url ="http://203.113.159.65/InternalServices/SportInfoServices.asmx/Post";
				String data = "{\"Command\":\"getAllNews\",\"Module\":\"mdl_News\",\"Parameters\":{\"CategoryId\":\"0\",\"Highlights\":\"1\",\"Status\":\"2\"},\"PageNumber\":\"0\",\"PageSize\":\"10\"}";
				Utils.getJSONFromURL(url, data);
				return null;
			}
			
		};
		test.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void initSlideMenu() {
		ArrayAdapter<String> adapt = new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_list_item_1,
				TAB_NAME);
		ListView list = (ListView) mMenuView.findViewById(R.id.slide_menu);
		list.setAdapter(adapt);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_menu:
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					if (mMenuView.getVisibility() == View.VISIBLE) {
						hideMenu();
					} else {
						showMenu();
						// mMenuView.showMenu();
					}

				}
			});

			break;

		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	public void showMenu() {
		if (mMenuView.getVisibility() != View.VISIBLE) {
			Log.d("Linhnv", "MyMenuView  showMenu :");
			mMenuView.setVisibility(View.VISIBLE);
			mMenuView.startAnimation(AnimationUtils.loadAnimation(
					getBaseContext(), R.anim.rbm_in_from_left));
		}
	}

	public void hideMenu() {
		if (mMenuView.getVisibility() != View.GONE) {
			mMenuView.setVisibility(View.GONE);
			mMenuView.startAnimation(AnimationUtils.loadAnimation(
					getBaseContext(), R.anim.rbm_out_to_left));
		}
	}

	class TabMenuAdapter extends FragmentStatePagerAdapter {
		public TabMenuAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			TabFragment tab = new TabFragment();
			Bundle b = new Bundle();
			b.putString(TabFragment.ARG_NAME, TAB_NAME[arg0]);
			tab.setArguments(b);

			return tab;
		}

		@Override
		public int getCount() {
			return TAB_NAME.length;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TAB_NAME[position];
		}

	}

	class TabFragment extends Fragment {

		public static final String ARG_NAME = "name";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.tab_layout, container,
					false);
			TextView tabText = (TextView) rootView.findViewById(R.id.tabText);
			Bundle b = getArguments();
			tabText.setText("Dumpy screen !");
			return rootView;
		}

	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
	
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		mViewPager.setCurrentItem(tab.getPosition());
	}
	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			mMenuView.hideMenu();
			break;
		default:
			return super.onKeyDown(keyCode, event);
		}
		return true;
	}

}
