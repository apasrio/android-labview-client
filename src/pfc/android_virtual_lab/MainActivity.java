package pfc.android_virtual_lab;

import pfc.android_virtual_lab.util.Constants;
import pfc.android_virtual_lab.util.TcpClientEstablishComm;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final static String TAG = "MainActivity";
	private TextView errorMsg;
	
	TcpClientEstablishComm clientSocket = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main_activity);

		errorMsg = (TextView) findViewById(R.id.connectionError);
		final View contentView = findViewById(R.id.fullscreen_content);	
		final Button connectButton = (Button) findViewById(R.id.dummy_button);

		// Set up the user interaction to manually show or hide the system UI.
		connectButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {								
				new TcpClientEstablishComm(getApplicationContext(),errorMsg).execute(Constants.ESTABLISH_CONNECTION);				
			}
		});
	}	
}
