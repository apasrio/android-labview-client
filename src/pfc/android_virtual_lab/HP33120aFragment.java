package pfc.android_virtual_lab;

import pfc.android_virtual_lab.util.Constants;
import pfc.android_virtual_lab.util.TcpClientBidirectComm;
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class HP33120aFragment extends RoboFragment{	
	
	private boolean status;
	private View rootView;
	private Context applicationContext, activityContext;
	private HP33120a hp33120a;
	private static final String TAG = "HP33120aFragment";
	
	@InjectView(R.id.hp33120a_amplitude) EditText amplitude;
	@InjectView(R.id.hp33120a_frequency) EditText frequency;
	@InjectView(R.id.hp33120a_offset) EditText offset;
	@InjectView(R.id.hp33120a_duty_cycle) EditText dutyCycle;
	@InjectView(R.id.hp33120aWaveform) Spinner wfmShape;
	@InjectView(R.id.hp33120aUnit) Spinner unit;
	@InjectView(R.id.hp33120a_configButton) Button configButton;
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){		
		rootView = inflater.inflate(R.layout.fragment_hp33120a,
				container, false);
		// Getting arguments
		status = getArguments().getBoolean(Constants.HP33120A_STATUS);
		if(status)
			hp33120a = new HP33120a();
		// Let's populate spinners
		populateSpinners(rootView);	
		applicationContext = getActivity().getApplicationContext();
		activityContext = getActivity();
		
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		changeDeviceState(status);		
		// Let's populate spinners
		populateSpinners(rootView);
		configButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {				
				Log.d(TAG, "Do it! Button has been pressed");
				//TCPClient.bidirectComm(message, messageType)
				readFields();
				hp33120a.setFrame();
				Log.d(TAG, "Frame -> " + hp33120a.getFrame());
				new HP33120aComm(applicationContext, activityContext, rootView).execute(hp33120a.getFrame(),
						String.valueOf(Constants.HP33120A_QUERY));
			}			
		});
	}
	
	private void populateSpinners(View rootView){
		wfmShape = (Spinner) rootView.findViewById(R.id.hp33120aWaveform);
		unit = (Spinner) rootView.findViewById(R.id.hp33120aUnit);
		
		ArrayAdapter<CharSequence> waveformAdapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.waveform_shape, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		waveformAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		wfmShape.setAdapter(waveformAdapter);
		
		ArrayAdapter<CharSequence> unitsAdapter = ArrayAdapter.createFromResource(getActivity(),
				R.array.units, android.R.layout.simple_spinner_dropdown_item);
		
		unitsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		unit.setAdapter(unitsAdapter);
	}
	
	private void changeDeviceState(boolean state){
		wfmShape.setEnabled(state);
		unit.setEnabled(state);
		frequency.setEnabled(state);
		amplitude.setEnabled(state);
		offset.setEnabled(state);
		dutyCycle.setEnabled(state);
		configButton.setEnabled(state);
	}
	
	private void readFields(){
		hp33120a.setSignalShape(wfmShape.getSelectedItemPosition());
		hp33120a.setUnit(unit.getSelectedItemPosition());
		hp33120a.setSignalFreq(Float.valueOf(frequency.getText().toString()));
		hp33120a.setSignalAmp(Float.valueOf(amplitude.getText().toString()));
		hp33120a.setSignalOff(Float.valueOf(offset.getText().toString()));
		hp33120a.setDutyCycleSq(Integer.valueOf(dutyCycle.getText().toString()));
	}
}
