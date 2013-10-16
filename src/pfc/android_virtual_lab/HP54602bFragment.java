package pfc.android_virtual_lab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class HP54602bFragment extends Fragment{
	
	private Spinner ch1Function, ch2Function, ch1Coupling, ch2Coupling, ch1Probe, ch2Probe;
	private Spinner triggerSource;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		View rootView = inflater.inflate(R.layout.fragment_hp54602b,
				container, false);

		// Let's populate spinners
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

		return rootView;
	}
}
