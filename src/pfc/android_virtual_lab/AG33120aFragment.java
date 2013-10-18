package pfc.android_virtual_lab;

import pfc.android_virtual_lab.util.Constants;
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AG33120aFragment extends RoboFragment{
		
	private View rootView;
	private boolean status;
	
	@InjectView(R.id.ag33220WfmShape) Spinner wfmShape;
	@InjectView(R.id.ag33220Unit) Spinner unit;
	@InjectView(R.id.ag33220a_frequency) EditText frequency;
	@InjectView(R.id.ag33220a_amplitude) EditText amplitude;
	@InjectView(R.id.ag33220a_offset) EditText offset;
	@InjectView(R.id.ag33220a_ramp_symmetry) EditText rampSymmetry;
	@InjectView(R.id.ag33220a_duty_cycle_sq) EditText dutyCycleSq;
	@InjectView(R.id.ag33220a_duty_cycle_puls) EditText dutyCyclePuls;
	@InjectView(R.id.ag33220a_config_button) Button configButton;
	
	
	public AG33120aFragment(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){		
		rootView = inflater.inflate(R.layout.fragment_ag33220a,
				container, false);
		// Getting arguments
		status = getArguments().getBoolean(Constants.AG33220A_STATUS);			
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		changeDeviceState(status);		
		// Let's populate spinners
		populateSpinners(rootView);
	}
	
	private void populateSpinners(View rootView){		
		
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
		rampSymmetry.setEnabled(state);
		dutyCycleSq.setEnabled(state);
		dutyCyclePuls.setEnabled(state);
		configButton.setEnabled(state);
	}
}
