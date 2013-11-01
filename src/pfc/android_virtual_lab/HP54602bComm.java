package pfc.android_virtual_lab;

import org.achartengine.model.XYSeries;

import pfc.android_virtual_lab.util.Constants;
import pfc.android_virtual_lab.util.TcpClientBidirectComm;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class HP54602bComm extends AsyncTask<String, Integer, String[]>{
	
	private static String TAG = "HP54602bComm";
	private TcpClientBidirectComm tcpClient;
	private Context applicationContext, activityContext;
	private View view;
	private Intent intent;
	
	public HP54602bComm(Context applicationContext, Context activityContext, View view){
		tcpClient = new TcpClientBidirectComm(applicationContext);
		this.view = view;
		this.activityContext = activityContext;
		this.applicationContext = applicationContext;
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
		intent = new Intent(activityContext, HP54602bChartActivity.class);
		switch(messageType){
		case 41:
			// Measuring success
			// If it is needed to do some calculations with the measured value, method should be called here
			// view.disableDataValidationMessage();
			Log.d(TAG, "Everything was ok!");
			decodeOkResponse(receivedData[1]);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			activityContext.startActivity(intent);
			break;
		case 43:
			// Measuring failure
			Log.d(TAG, "Something was wrong!!");
			Log.d(TAG, receivedData[1]);
			Toast.makeText(activityContext, "Error Server: " + receivedData[1], Toast.LENGTH_LONG).show();
			break;                        
		}
	}
	
	private void decodeOkResponse(String receivedOkMessage){
		String[] decodedResponse;
		decodedResponse = receivedOkMessage.split(",");
		XYSeries trace1 = null;
		XYSeries trace2 = null;

		// Function Measures as extras
		intent.putExtra(Constants.HP54602B_MEASURE_1, decodedResponse[0]);
		intent.putExtra(Constants.HP54602B_MEASURE_2, decodedResponse[1]);
		
		Log.d(TAG, "Trace Points 1: " + decodedResponse[2]);
		Log.d(TAG, "Trace Points 2: " + decodedResponse[3]);
		int trace1Points = Integer.parseInt(decodedResponse[2]);
		int trace2Points = Integer.parseInt(decodedResponse[3]);
		
        if(trace1Points > 0){
                // We need to draw trace 1
                trace1 = processTrace1(decodedResponse[4], decodedResponse[5], decodedResponse[6]);
        }
        if(trace2Points > 0){
                // We need to draw trace 2
                trace2 = processTrace2(decodedResponse[7],decodedResponse[8],decodedResponse[9]);
        }
        intent.putExtra(Constants.HP54602B_TRACE_1, trace1);
        intent.putExtra(Constants.HP54602B_TRACE_2, trace2);
	}
	
	private XYSeries processTrace1(String t_0, String dtime, String trace){
		int i;
		XYSeries series = new XYSeries("Channel 1");
		Double t0 = Double.parseDouble(t_0);
		Double dt = Double.parseDouble(dtime);
		
		String[] decodedTrace = trace.split(";");
		for(i=0;i<decodedTrace.length;i++){
			// We need to add data to XYSeries
			// t0 is the initial time value and dt is a differential time value
			series.add((t0 + dt*i), Double.parseDouble(decodedTrace[i]));
		}
		return series;
	}
	
	private XYSeries processTrace2(String t_0, String dtime, String trace){
        XYSeries series = new XYSeries ("Channel 2");
        int i;
        Double t0 = Double.parseDouble(t_0);
        Double dt = Double.parseDouble(dtime);
        Log.d(TAG, "Channel 2 t0 -> "+ t0);
		Log.d(TAG, "Channel 2 dt ->" + dt);
		Log.d(TAG, "Channel 2 trace -> " + trace);
        String[] decodedTrace = trace.split(";");
        for(i=0;i<decodedTrace.length;i++){
                series.add((t0 + dt*i), Double.parseDouble(decodedTrace[i]));
        }
        return series;
}

}
