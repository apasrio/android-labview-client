package pfc.android_virtual_lab;

import pfc.android_virtual_lab.util.TcpClientBidirectComm;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

public class HP54602bComm extends AsyncTask<String, Integer, String[]>{
	
	private static String TAG = "HP54602bComm";
	private TcpClientBidirectComm tcpClient;
	private View view;
	
	public HP54602bComm(Context applicationContext, Context activityContext, View view){
		tcpClient = new TcpClientBidirectComm(applicationContext);
		this.view = view;
	}

	@Override
	protected String[] doInBackground(String... params) {
		String[] result = null;
		try {
			Log.d(TAG, "Starting task in background");
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
			decodeHP54602bResponse(result);
		}else{
			Log.d(TAG, "There has been an error in communication process!");
		}
	}
	
	// Method to decode HP54602b response
	private void decodeHP54602bResponse(String[] receivedData){
		int messageType = Integer.parseInt(receivedData[0]);    
		Log.d(TAG, "decoding response!");
		switch(messageType){
		case 41:
			// Measuring success
			// If it is needed to do some calculations with the measured value, method should be called here
			// view.disableDataValidationMessage();
			Log.d(TAG, "Everything was ok!");
			decodeOkResponse(receivedData[1]);
			break;
		case 43:
			// Measuring failure
			Log.d(TAG, "Something was wrong!!");
			Log.d(TAG, receivedData[1]);
			//view.setDataValidationMessage("Error Server: " + receivedData[1]);
			break;                        
		}
	}
	
	private void decodeOkResponse(String receivedOkMessage){
        String[] decodedResponse;
        decodedResponse = receivedOkMessage.split(",");
        //view.setFunc1MeasuredValue(decodedResponse[0]);
        //view.setFunc2MeasuredValue(decodedResponse[1]);
        Log.d(TAG, "Function 1 ->" + decodedResponse[0]);
        Log.d(TAG, "Function 2 ->" + decodedResponse[1]);
        //int trace1Points = Integer.parseInt(decodedResponse[2]);
        //int trace2Points = Integer.parseInt(decodedResponse[3]);
        Log.d(TAG, "Trace 1: " + decodedResponse[2]);
        Log.d(TAG, "Trace 2: " + decodedResponse[3]);
        /*
        if(trace1Points > 0){
                // We need to draw trace 1
                trace1 = processTrace1(decodedResponse[4], decodedResponse[5], decodedResponse[6]);
        }
        if(trace2Points > 0){
                // We need to draw trace 2
                trace2 = processTrace2(decodedResponse[7],decodedResponse[8],decodedResponse[9]);
        }*/
        //view.setXYSeries(trace1, trace2);
}

}
