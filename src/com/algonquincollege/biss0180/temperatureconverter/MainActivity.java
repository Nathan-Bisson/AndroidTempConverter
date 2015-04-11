/**
 * Sets the Edit Text fields, and doubleSeekBars
 * Gets the Celsius and Fahrenheit Bars to move together in unison
 * allows users to enter a value to set the temperature
 * 
 * @author Nathan Bisson (biss0180)
 * @version 1.0 
 * 
 */

package com.algonquincollege.biss0180.temperatureconverter;

import java.util.Observable;
import java.util.Observer;

import model.TempuratureModel;

import com.tinycoolthings.double_seekbar.DoubleSeekBar;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;


public class MainActivity extends Activity implements Observer {
	
	private EditText editCelsius;
	private EditText editFahrenheit;
	private DoubleSeekBar seekBarCelsius;
	private DoubleSeekBar seekBarFahrenheit;
	//private View viewText;
	private TempuratureModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        model = new TempuratureModel(TempuratureModel.ROOMTEMP_CELSIUS, TempuratureModel.ROOMTEMP_FAHRENHEIT );
        model.addObserver(this);
        
        editCelsius = (EditText) findViewById(R.id.editTextCelsius);
        editFahrenheit = (EditText) findViewById(R.id.editTextFar);
        
        seekBarCelsius = (DoubleSeekBar) findViewById(R.id.celsius_doubleseekbar);
        seekBarFahrenheit = (DoubleSeekBar) findViewById(R.id.far_doubleseekbar);
        
        seekBarCelsius.setMaxValue((TempuratureModel.MAX_CELSIUS).intValue());
        seekBarCelsius.setMinValue((TempuratureModel.MIN_CELSIUS).intValue());
        seekBarCelsius.setHasMax(false);
        seekBarCelsius.setMinTitle("Celsius");
        seekBarCelsius.setUnits("¡");
        seekBarCelsius.setCurrentMinValue((TempuratureModel.ROOMTEMP_CELSIUS).intValue());
        
        seekBarFahrenheit.setMaxValue((TempuratureModel.MAX_FAHRENHEIT).intValue());
        seekBarFahrenheit.setMinValue((TempuratureModel.MIN_FAHRENHEIT).intValue());
        seekBarFahrenheit.setHasMax(false);
        seekBarFahrenheit.setMinTitle("Fahrenheit");
        seekBarFahrenheit.setUnits("¡");
        seekBarFahrenheit.setCurrentMinValue((TempuratureModel.ROOMTEMP_FAHRENHEIT).intValue());
        
        editCelsius.setText(Double.toString(TempuratureModel.ROOMTEMP_CELSIUS));
        editFahrenheit.setText(Double.toString(TempuratureModel.ROOMTEMP_FAHRENHEIT));
        
        seekBarCelsius.registerOnChangeListenerMinSB(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				model.setCelsius((double)seekBarCelsius.getCurrentMinValue());
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        this.updateView( );
        
        
        seekBarFahrenheit.registerOnChangeListenerMinSB(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				model.setFahrenheit((double)seekBarFahrenheit.getCurrentMinValue());
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        this.updateView( );
        
        editCelsius.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
					(keyCode == KeyEvent.KEYCODE_ENTER)) {
					double celsiusValue = Integer.parseInt(editCelsius.getText().toString());
					
					if(celsiusValue <= -41) {
						Toast.makeText(getApplicationContext(), "Enter a number greater than -41", Toast.LENGTH_SHORT).show();
					} else if(celsiusValue >= 51) {
						Toast.makeText(getApplicationContext(), "Enter a number less than 51", Toast.LENGTH_SHORT).show();
					} else {
						model.setCelsius(celsiusValue);
					}
					return true;
					} 
					return false;
					
			}
        	
        });
        
        editFahrenheit.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
					(keyCode == KeyEvent.KEYCODE_ENTER)) {
					double fahrenheitValue = Integer.parseInt(editFahrenheit.getText().toString());
					if(fahrenheitValue <= -41) {
						Toast.makeText(getApplicationContext(), "Enter a number greater than -41", Toast.LENGTH_SHORT).show();
					} else if(fahrenheitValue >= 123) {
						Toast.makeText(getApplicationContext(), "Enter a number less than 123", Toast.LENGTH_SHORT).show();
					} else {
						model.setFahrenheit(fahrenheitValue);
					}
					
					return true;
				}
				return false;
			}
        	
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		this.updateView();
	}
	
	private void updateCelsiusBar() {
		seekBarCelsius.setCurrentMinValue((int)Math.round(model.getCelsius()));
	}
	
	private void updateFahrenheitBar() {
		seekBarFahrenheit.setCurrentMinValue((int)Math.round(model.getFahrenheit()));
	}
	
	private void updateCelsiusText() {
		editCelsius.setText(Double.toString((int)Math.round(model.getCelsius())));
	}
	
	private void updateFahrenheitText() {
		editFahrenheit.setText(Double.toString((int)Math.round(model.getFahrenheit())));
	}
	
	public void updateView() {
		updateCelsiusBar();
		updateFahrenheitBar();
		updateCelsiusText();
		updateFahrenheitText();
	}
}
