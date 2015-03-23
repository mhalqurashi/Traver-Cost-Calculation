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
	private TextView distanceDisplatTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		distanceEditText = (EditText) findViewById(R.id.distanceEditText);
		distanceDisplatTextView = 
				(TextView) findViewById(R.id.distanceDisplayTextView);
		mPGSeekBar = (SeekBar) findViewById(R.id.mPGSeekBar);
		gasPriceSeekBar = (SeekBar) findViewById(R.id.gasPriceSeekBar);
		costDisplayTextView = 
				(TextView) findViewById(R.id.coastDisplayTextView);
		distanceDisplatTextView.setText(String.valueOf(distance));
		updateCost ();
		distanceEditText.addTextChangedListener(
				distanceEditTextWatcher);
		mPGSeekBar.setOnSeekBarChangeListener(
				mPGSeekSeekBarListener);
		
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
				distance = Double.parseDouble(s.toString()) / 100;
			}
			catch (NumberFormatException e) {
				distance = 0.0;
			}
			distanceDisplatTextView.setText
			(String.valueOf(distance));
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
			gasPrice = (progress / 100) + 1; 
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
			//The added one is to set the minimum value to 1. 
			updateCost ();
		}
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {}
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {	
		}
	};

}
