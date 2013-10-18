package pfc.android_virtual_lab;

import pfc.android_virtual_lab.util.Constants;
import roboguice.RoboGuice;
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
import android.widget.TextView;
import android.widget.ToggleButton;

public class HP34401aFragment extends RoboFragment{
	
	private boolean status;
	private View rootView;
	
	@InjectView(R.id.hp34401a_manual_range) EditText manualRange; 
	@InjectView(R.id.hp34401a_autozero) ToggleButton autozero;
	@InjectView(R.id.hp34401a_autorange) ToggleButton autorange;
	@InjectView(R.id.hp34401a_measure) TextView measure;
	@InjectView(R.id.hp34401a_config_button) Button configButton;
	@InjectView(R.id.hp34401a_function) Spinner function;
	@InjectView(R.id.hp34401a_resolution) Spinner resolution;
	@InjectView(R.id.hp34401a_trigger_src) Spinner triggerSource;
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		rootView = inflater.inflate(R.layout.fragment_hp34401a,
				container, false);		
		// Getting arguments
		status = getArguments().getBoolean(Constants.HP34401A_STATUS);		
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
		
		ArrayAdapter<CharSequence> functionAdapter = ArrayAdapter.createFromResource(getActivity(),
		        R.array.dmm_functions, android.R.layout.simple_spinner_item);
		ArrayAdapter<CharSequence> resolutionAdapter = ArrayAdapter.createFromResource(getActivity(),
				R.array.dmm_resolution, android.R.layout.simple_spinner_dropdown_item);
		ArrayAdapter<CharSequence> triggerAdapter = ArrayAdapter.createFromResource(getActivity(),
				R.array.dmm_trigger_source, android.R.layout.simple_spinner_dropdown_item);
		
		// Specify the layout to use when the list of choices appears
		functionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		resolutionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		triggerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		function.setAdapter(functionAdapter);		
		resolution.setAdapter(resolutionAdapter);
		triggerSource.setAdapter(triggerAdapter);
	}
	
	private void changeDeviceState(boolean state){
		manualRange.setEnabled(state);
		measure.setEnabled(state);
		autozero.setEnabled(state);
		autorange.setEnabled(state);
		configButton.setEnabled(state);
		function.setEnabled(state);
		resolution.setEnabled(state);
		triggerSource.setEnabled(state);
	}
}
