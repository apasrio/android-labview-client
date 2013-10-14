package pfc.android_virtual_lab;

import java.io.IOException;
import java.net.UnknownHostException;

import pfc.android_virtual_lab.util.Constants;
import pfc.android_virtual_lab.util.TCPClient;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private final static String TAG = "MainActivity";
	TCPClient clientSocket = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_activity);
		
		final TextView errorMsg = (TextView) findViewById(R.id.connectionError);
		final View contentView = findViewById(R.id.fullscreen_content);		
		
		// Set up the user interaction to manually show or hide the system UI.
		contentView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				int connectionCode = 0;				
				try {
					clientSocket = new TCPClient(Constants.SocketIp, Constants.SocketPort);
					connectionCode = TCPClient.establishComm(Constants.SocketIp);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(connectionCode == 4){
					// Everything has been fine, so we need to know what devices are available
					contentView.setVisibility(View.INVISIBLE);
					try {
						String[] receivedData;
						receivedData = TCPClient.bidirectComm(Constants.AVAILABLE_FIELD,
								Constants.AVAILABLE_DEVICES_QUERY);
						
						receivedData = decodeAvailableDevicesQuery(receivedData[1]);
						
						Log.d(TAG, "AG33220A is -> " + receivedData[0]);
						Log.d(TAG, "HP33120A is -> " + receivedData[1]);
						Log.d(TAG, "HP34401A is -> " + receivedData[2]);
						Log.d(TAG, "HP54602B is -> " + receivedData[3]);
						
						// Then we need to launch next Activity with the devices configured
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (connectionCode == 5){
					// Show the user that system is busy
					Log.d(TAG, "Server is Busy");
				} else if (connectionCode == -1){
					// An error has occurred while connection procedure was in process
					Log.e(TAG, "Unknown Error! Try again later!");
				}
			}
		});
	}
	
	private String[] decodeAvailableDevicesQuery(String receivedMessage){
		String[] decodedQuery;
		decodedQuery = receivedMessage.split(",");
		return decodedQuery;
	}
}