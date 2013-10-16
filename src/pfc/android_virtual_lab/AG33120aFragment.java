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

public class AG33120aFragment extends Fragment{
	
	private Spinner wfmShape, unit;
	private EditText frequency, amplitude, offset, rampSymmetry, dutyCycleSq, dutyCyclePuls;
	private Button configButton;
	private View rootView;
	
	public AG33120aFragment(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){		
		rootView = inflater.inflate(R.layout.fragment_ag33220a,
				container, false);
		// Getting arguments
		boolean flag = getArguments().getBoolean(Constants.AG33220A_STATUS);
		// References to views
		frequency = (EditText) rootView.findViewById(R.id.ag33220a_frequency);
		amplitude = (EditText) rootView.findViewById(R.id.ag33220a_amplitude);
		offset = (EditText) rootView.findViewById(R.id.ag33220a_offset);
		rampSymmetry = (EditText) rootView.findViewById(R.id.ag33220a_ramp_symmetry);
		dutyCycleSq = (EditText) rootView.findViewById(R.id.ag33220a_duty_cycle_sq);
		dutyCyclePuls = (EditText) rootView.findViewById(R.id.ag33220a_duty_cycle_puls);
		configButton = (Button) rootView.findViewById(R.id.ag33220a_config_button);
		
		// Let's populate spinners
		populateSpinners(rootView);
		changeDeviceState(flag);
		
		
		return rootView;
	}
	
	private void populateSpinners(View rootView){
		wfmShape = (Spinner) rootView.findViewById(R.id.ag33220WfmShape);
		unit = (Spinner) rootView.findViewById(R.id.ag33220Unit);
		
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
