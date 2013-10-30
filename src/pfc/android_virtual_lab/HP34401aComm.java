package pfc.android_virtual_lab;

import pfc.android_virtual_lab.util.TcpClientBidirectComm;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class HP34401aComm extends AsyncTask<String, Integer, String[]>{
	
	private static String TAG = "HP34401aComm";
	private TcpClientBidirectComm tcpClient;
	private View view;
	
	public HP34401aComm(Context applicationContext, Context activityContext, View view){
		tcpClient = new TcpClientBidirectComm(applicationContext);
		this.view = view;
	}

	@Override
	protected String[] doInBackground(String... params) {
		String[] result = null;
		try {
			result = tcpClient.bidirectComm(params[0],Integer.valueOf(params[1]));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	protected void onPostExecute(String[] result){
		Log.d(TAG, "OnPostExecute method");
		if(result != null){
			decodeHP34401aResponse(result);
		}else{
			Log.d(TAG, "There has been an error in communication process!");
		}
	}
	
	private void decodeHP34401aResponse(String[] result){
		int messageType = Integer.parseInt(result[0]);
		String receivedMeasure = result[1];
		TextView measure = (TextView) view.findViewById(R.id.hp34401a_measure);
		switch(messageType){
		case 31:
			// Measuring success
			// If it is needed to do some calculations with the measured value, method should be called here
			Log.d(TAG, "Successful query!");
			Log.d(TAG, "Received message -> " + result[1]);
			measure.setText(receivedMeasure);
			break;
		case 33:
			// Measuring failure
			Log.d(TAG, "Error in query!");
			Log.d(TAG, "Received Measure -> " + receivedMeasure);
			measure.setText(receivedMeasure);
			break;                        
		}
	}
}
