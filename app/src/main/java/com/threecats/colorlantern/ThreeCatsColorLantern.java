package com.threecats.colorlantern;

/*
 *  Copyright 2011 3Cats Software <rumburake@gmail.com>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  see NOTICE and LICENSE files in the top level project folder.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.threecats.colorselect.ColorSelector;

public class ThreeCatsColorLantern extends AppCompatActivity implements ColorSelector.ColorSelectedListener {
	int color = 0xFFFF0000;
	View view;

	@Override
	public void onNewColor(int color) {
		SharedPreferences settings = ThreeCatsColorLantern.this.getPreferences(0);
		SharedPreferences.Editor settingsEditor = settings.edit();
		settingsEditor.putInt("color", color);
		settingsEditor.apply();
		// I save color into this activity's class member and also set the background to it.
		ThreeCatsColorLantern.this.color = color;
		ThreeCatsColorLantern.this.view.setBackgroundColor(color);

	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        w.setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, 
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        SharedPreferences settings = getPreferences(0);
        color = settings.getInt("color", 0xFF00AA00);
        
        setContentView(R.layout.main);
        view = findViewById(R.id.surface);
        view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ColorSelector colorSelector = ColorSelector.newInstance(color);
				colorSelector.show(getSupportFragmentManager(), "colorSelect");
			}
		});
        view.setBackgroundColor(color);
    }

}