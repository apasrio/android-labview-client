package pfc.android_virtual_lab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class HP34401aFragment extends Fragment{
	
	private Spinner function, resolution, triggerSource;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		
		View rootView = inflater.inflate(R.layout.fragment_hp34401a,
				container, false);
		
		// Let's populate spinners
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
		
		return rootView;
	}
}
