package pfc.android_virtual_lab;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import pfc.android_virtual_lab.util.TcpClientBidirectComm;

public class HP33120aComm extends AsyncTask<String, Integer, String[]>{
	private static String TAG = "HP33120aComm";
	private TcpClientBidirectComm tcpClient;
	
	public HP33120aComm(Context applicationContext, Context activityContext, View view){
		tcpClient = new TcpClientBidirectComm(applicationContext);
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
			Log.d(TAG, "Type of message -> " + result[0]);
			if(Integer.valueOf(result[0])== 11){
				Log.d(TAG, "Successful query!");
				Log.d(TAG, "Received message -> " + result[1]);
			}
			else{
				Log.d(TAG, "Error in query!");
			}			
		}else{
			Log.d(TAG, "There has been an error in communication process!");
		}
	}
}
