package pfc.android_virtual_lab;

import pfc.android_virtual_lab.util.Constants;
import pfc.android_virtual_lab.util.TcpClientBidirectComm;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class ConfigComm extends AsyncTask<String, Integer, String[]>{
	private TcpClientBidirectComm tcpClient;
	private Context context;
	
	private final static String TAG = "ConfigComm";
	
	public ConfigComm(Context context){
		this.context = context;
		tcpClient = new TcpClientBidirectComm(context);
	}

	@Override
	protected String[] doInBackground(String... params) {
		String[] result = null;
		try{
			result = tcpClient.bidirectComm(params[0], Integer.valueOf(params[1]));
		} catch (NumberFormatException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	protected void onPostExecute(String[] result){
		Log.d(TAG, "OnPostExecute method");
		if(result != null){
			decodeConfigResponse(result);
		}else{
			Log.d(TAG, "There has been an error in communication process!");
		}
	}
	
	private void decodeConfigResponse(String[] receivedData){
		int messageType = Integer.parseInt(receivedData[0]);
		Log.d(TAG, "decoding response!");
		switch(messageType){
		case Constants.CLOSING_CONNECTION:
			Toast.makeText(context, "Disconnection Success", Toast.LENGTH_SHORT).show();
			break;
		default:
			Toast.makeText(context, "Failure in Disconnection Process", Toast.LENGTH_SHORT).show();
			break;
		}
	}
}
