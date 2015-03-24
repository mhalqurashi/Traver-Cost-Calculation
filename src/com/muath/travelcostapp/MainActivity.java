package com.muath.travelcostapp;

import android.app.Activity;
import android.os.Bundle;

import java.text.NumberFormat;


import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.EditText;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.content.Intent;



public class MainActivity extends Activity {
	private static final NumberFormat currencyFormat = NumberFormat
			.getCurrencyInstance();
	//private double numberofGallons = 0;
	private int milesPerGallon = 25;
	private double gasPrice = 2.5;
	private double distance = 0;
	private double cost = 0;
	
	private EditText distanceEditText;
	private SeekBar mPGSeekBar;
	private SeekBar gasPriceSeekBar;
	private TextView costDisplayTextView;
	private TextView mPGDisplayTextView;
	private TextView gasPriceDisplayTextView;
	private ImageButton carButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		distanceEditText = (EditText) findViewById(R.id.distanceEditText);
		mPGSeekBar = (SeekBar) findViewById(R.id.mPGSeekBar);
		gasPriceSeekBar = (SeekBar) findViewById(R.id.gasPriceSeekBar);
		costDisplayTextView = 
				(TextView) findViewById(R.id.coastDisplayTextView);
		carButton = (ImageButton) findViewById(R.id.carButton);
		mPGDisplayTextView = (TextView) findViewById(R.id.mPGDisplayTextView);
		gasPriceDisplayTextView = 
				(TextView) findViewById(R.id.gasPriceDisplayTextView); 
		mPGDisplayTextView.setText(currencyFormat.format(gasPrice));
		gasPriceDisplayTextView.setText(currencyFormat.format(gasPrice));
		
		updateCost ();
		distanceEditText.addTextChangedListener(
				distanceEditTextWatcher);
		mPGSeekBar.setOnSeekBarChangeListener(
				mPGSeekSeekBarListener);
		gasPriceSeekBar.setOnSeekBarChangeListener(gasPriceSeekBarListener);
		mPGDisplayTextView.setText(String.valueOf(milesPerGallon));
		carButton.setOnClickListener(carButtonListener);
	}
	
	private void updateCost () {
		double numberOfGallons = distance / milesPerGallon;
		cost = numberOfGallons * gasPrice;
		costDisplayTextView.setText(currencyFormat.format(cost));
	}
	
	private TextWatcher distanceEditTextWatcher = new TextWatcher() {
		@Override
		public void onTextChanged (CharSequence s, int start, 
				int before, int count) {
			try {
				distance = Double.parseDouble(s.toString());
			}
			catch (NumberFormatException e) {
				distance = 0.0;
			}
			updateCost ();
		}
		@Override
		public void afterTextChanged (Editable s)  {}
		@Override
		public void beforeTextChanged (CharSequence s, int start, int count, 
				int after) {}
	};
	
	private OnSeekBarChangeListener gasPriceSeekBarListener = 
			new OnSeekBarChangeListener () {
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			gasPrice = (progress / 100.0) + 1.0;
			gasPriceDisplayTextView.setText(currencyFormat.format(gasPrice));
			//The added one is to set the minimum value to 1. 
			updateCost ();
		}
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {}
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {	
		}
	};
	
	private OnSeekBarChangeListener mPGSeekSeekBarListener = 
			new OnSeekBarChangeListener () {
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			milesPerGallon = progress + 10;
			mPGDisplayTextView.setText(String.valueOf(milesPerGallon));
			//The added one is to set the minimum value to 1. 
			updateCost ();
		}
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {}
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {	
		}
	};
	
	public OnClickListener carButtonListener = new OnClickListener() 
	   {
	      @Override
	      public void onClick(View v) 
	      {
	    	  String imageUrl = 
	    			  "http://en.wikipedia.org/wiki/Bugatti";
	    	  Intent webIntent = 
	    			  new Intent(Intent.ACTION_VIEW, Uri.parse(imageUrl));
	         startActivity(webIntent);
	      } // end method onClick
	   }; // end OnClickListener anonymous inner class

}
