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

public class HP33120aFragment extends Fragment{
	
	private Spinner wfmShape, unit;
	private EditText frequency, amplitude, offset, dutyCycle;
	private Button configButton;
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){		
		View rootView = inflater.inflate(R.layout.fragment_hp33120a,
				container, false);
		frequency = (EditText) rootView.findViewById(R.id.hp33120a_frequency);
		amplitude = (EditText) rootView.findViewById(R.id.hp33120a_amplitude);
		offset = (EditText) rootView.findViewById(R.id.hp33120a_offset);
		dutyCycle = (EditText) rootView.findViewById(R.id.hp33120a_duty_cycle);
		configButton = (Button) rootView.findViewById(R.id.hp33120a_configButton);
		// Getting arguments
		boolean flag = getArguments().getBoolean(Constants.HP33120A_STATUS);
		// Let's populate spinners
		populateSpinners(rootView);
		changeDeviceState(flag);
		
		return rootView;
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
}
