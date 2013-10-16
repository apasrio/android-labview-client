package pfc.android_virtual_lab;

import pfc.android_virtual_lab.util.Constants;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class HP34401aFragment extends Fragment{
	
	private Spinner function, resolution, triggerSource;
	private EditText manualRange;
	private TextView measure;
	private ToggleButton autozero, autorange;
	private Button configButton;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		View rootView = inflater.inflate(R.layout.fragment_hp34401a,
				container, false);
		// Instance of Views
		manualRange = (EditText) rootView.findViewById(R.id.hp34401a_manual_range);
		measure = (TextView) rootView.findViewById(R.id.hp34401a_measure);
		autozero = (ToggleButton) rootView.findViewById(R.id.hp34401a_autozero);
		autorange = (ToggleButton) rootView.findViewById(R.id.hp34401a_autorange);
		configButton = (Button) rootView.findViewById(R.id.hp34401a_config_button);
		
		// Getting arguments
		boolean flag = getArguments().getBoolean(Constants.HP34401A_STATUS);
		
		// Let's populate spinners
		populateSpinners(rootView);
		changeDeviceState(flag);
		
		return rootView;
	}
	
	private void populateSpinners(View rootView){
		function = (Spinner) rootView.findViewById(R.id.hp34401a_function);
		resolution = (Spinner) rootView.findViewById(R.id.hp34401a_resolution);
		triggerSource = (Spinner) rootView.findViewById(R.id.hp34401a_trigger_src);
		
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
