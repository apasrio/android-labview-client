package pfc.android_virtual_lab;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AG33120aFragment extends Fragment{
	
	private Spinner wfmShape, unit;
	
	public static final String CONTEXT = "context";
	
	public AG33120aFragment(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){		
		View rootView = inflater.inflate(R.layout.fragment_ag33220a,
				container, false);
		// Getting arguments
		
		// Let's populate spinners
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
		
		return rootView;
	}
}
