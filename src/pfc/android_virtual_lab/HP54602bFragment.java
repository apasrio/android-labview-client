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
import android.widget.ToggleButton;

public class HP54602bFragment extends Fragment{
	
	private Spinner ch1Function, ch2Function, ch1Coupling, ch2Coupling, ch1Probe, ch2Probe;
	private Spinner triggerSource;
	private EditText ch1Range, ch2Range, ch1Position, ch2Position, triggerLvl, timeRange, timeDelay;
	private ToggleButton ch1Enabler, ch2Enabler, autoset;
	private Button configButton;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		View rootView = inflater.inflate(R.layout.fragment_hp54602b,
				container, false);
		
		ch1Range = (EditText) rootView.findViewById(R.id.hp54602b_ch1_range);
		ch1Position = (EditText) rootView.findViewById(R.id.hp54602b_ch1_position);
		ch2Range = (EditText) rootView.findViewById(R.id.hp54602b_ch2_range);
		ch2Position = (EditText) rootView.findViewById(R.id.hp54602b_ch2_position);
		triggerLvl = (EditText) rootView.findViewById(R.id.hpt54602b_trigger_lvl);
		timeRange = (EditText) rootView.findViewById(R.id.hp54602b_time_range);
		timeDelay = (EditText) rootView.findViewById(R.id.hp54602b_time_delay);
		ch1Enabler = (ToggleButton) rootView.findViewById(R.id.hp54602b_ch1_button);
		ch2Enabler = (ToggleButton) rootView.findViewById(R.id.hp54602b_ch2_button);
		autoset = (ToggleButton) rootView.findViewById(R.id.hp54602b_autoset);
		configButton = (Button) rootView.findViewById(R.id.hp54602b_config_button);
		
		// Getting arguments
		boolean flag = getArguments().getBoolean(Constants.HP54602B_STATUS);

		// Let's populate spinners
		populateSpinners(rootView);
		changeDeviceState(flag);
		

		return rootView;
	}
	
	private void populateSpinners(View rootView){
		ch1Function = (Spinner) rootView.findViewById(R.id.hp54602b_ch1_function);
		ch2Function = (Spinner) rootView.findViewById(R.id.hp54602b_ch2_function);
		ch1Coupling = (Spinner) rootView.findViewById(R.id.hp54602b_ch1_coupling);
		ch2Coupling = (Spinner) rootView.findViewById(R.id.hp54602b_ch2_coupling);
		ch1Probe = (Spinner) rootView.findViewById(R.id.hp54602b_ch1_probe);
		ch2Probe = (Spinner) rootView.findViewById(R.id.hp54602b_ch2_probe);
		triggerSource = (Spinner) rootView.findViewById(R.id.hp54602b_trigger_src);

		ArrayAdapter<CharSequence> functionAdapter = ArrayAdapter.createFromResource(getActivity(),
				R.array.oscilloscope_functions, android.R.layout.simple_spinner_item);
		ArrayAdapter<CharSequence> couplingAdapter = ArrayAdapter.createFromResource(getActivity(),
				R.array.oscilloscope_coupling, android.R.layout.simple_spinner_item);
		ArrayAdapter<CharSequence> probeAdapter = ArrayAdapter.createFromResource(getActivity(),
				R.array.oscilloscope_probe, android.R.layout.simple_spinner_item);
		ArrayAdapter<CharSequence> triggerSourceAdapter = ArrayAdapter.createFromResource(getActivity(),
				R.array.oscilloscope_trigger_src, android.R.layout.simple_spinner_item);
		
		// Specify the layout to use when the list of choices appears		
		functionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		couplingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		probeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		triggerSourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// Apply the adapter to the spinner
		ch1Function.setAdapter(functionAdapter);
		ch2Function.setAdapter(functionAdapter);
		ch1Coupling.setAdapter(couplingAdapter);
		ch2Coupling.setAdapter(couplingAdapter);
		ch1Probe.setAdapter(probeAdapter);
		ch2Probe.setAdapter(probeAdapter);
		triggerSource.setAdapter(triggerSourceAdapter);
	}
	
	private void changeDeviceState(boolean State){
		ch1Function.setEnabled(State);
		ch2Function.setEnabled(State);
		ch1Coupling.setEnabled(State);
		ch2Coupling.setEnabled(State);
		ch1Probe.setEnabled(State);
		ch2Probe.setEnabled(State);
		triggerSource.setEnabled(State);
		
		ch1Range.setEnabled(State);
		ch1Position.setEnabled(State);
		ch2Range.setEnabled(State);
		ch2Position.setEnabled(State);
		triggerLvl.setEnabled(State);
		timeRange.setEnabled(State);
		timeDelay.setEnabled(State);
		ch1Enabler.setEnabled(State);
		ch2Enabler.setEnabled(State);
		autoset.setEnabled(State);
		configButton.setEnabled(State);
	}
}
