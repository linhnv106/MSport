package com.linhnv.apps.msport.view;


import com.linhnv.apps.msport.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class MyMenuView  extends LinearLayout{
	LinearLayout menu;
	public MyMenuView(Context context, AttributeSet attrs) {
		super(context, attrs);
		try{
			LayoutInflater.from(getContext()).inflate(R.layout.slide_menu, this, true);
			menu=(LinearLayout)findViewById(R.id.menu);
			
		}catch(Exception e){
			Log.w("Linhnv", "MyMenuView  error :" + e.getMessage());
		}
		
	}
	public void showMenu()
	{
		Log.d("Linhnv", "MyMenuView  showMenu :" );
		menu.setVisibility(View.VISIBLE);	
		menu.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.rbm_in_from_left));
	}

	public void hideMenu()
	{
		menu.setVisibility(View.GONE);
		menu.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.rbm_out_to_left));
	}

	public void toggleMenu()
	{
		
		Log.i("Linhnc", "toggleMenu");
		
		if (menu.getVisibility() == View.GONE)
		{
			showMenu();
		} 
		else 
		{
			hideMenu();
		}
	}
}
