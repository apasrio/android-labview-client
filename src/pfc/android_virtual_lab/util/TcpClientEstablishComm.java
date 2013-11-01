package pfc.android_virtual_lab.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import pfc.android_virtual_lab.DevicesActivity;
import pfc.android_virtual_lab.MyApp;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class TcpClientEstablishComm extends AsyncTask<String, Integer, Integer>{
	
	private static final String TAG = "TcpCommunication";
	//Socket parameters
	// private static Socket clientSocket;
	static int receivedMessageLength, receivedMessageType;
	// Handling the DataStreams
	private DataOutputStream outToServer; 
	private BufferedReader inFromServer; 
	private TextView errMsg;
	private Context context;
	private boolean ag33220aFlag, hp33120aFlag, hp34401aFlag, hp54602bFlag;
	private MyApp myApp;
	
	public TcpClientEstablishComm(Context context, TextView errMsg){
		this.errMsg = errMsg;
		this.context = context;
		myApp = (MyApp)context.getApplicationContext();
		this.outToServer = myApp.getOutToServer();
		this.inFromServer = myApp.getInFromServer();
	}	
	
	/*
	 * Method to send a message to the server and wait for an answer
	 */
	public String[] bidirectComm(String message, Integer messageType) throws Exception{
		// Building the Header with the lenght of the message to send
		// Needed Variables		
		String headerToSend, receivedHeader, receivedMessage ,headerToPad = "";
		int messageLength;
		
		messageLength = message.length();
		headerToPad = messageLength + "," + messageType;
		
		
		// Header Building
		headerToSend = paddingHeaderMessage(headerToPad);
		System.out.println("Header to Send :" +headerToSend);
		outToServer.writeBytes(headerToSend);
		outToServer.writeBytes(message);
		
		receivedHeader = readHeader(inFromServer);
		//receivedMessage = convertStreamToString(clientSocket.getInputStream());
		System.out.println("Header From the Server: " + receivedHeader);
		decodeHeader(receivedHeader);		
		receivedMessage = readMessage(receivedMessageLength, inFromServer);
		
		System.out.println("Message From the Server: " + receivedMessage );
		String[] dataToProcess = new String[]{String.valueOf(receivedMessageType), receivedMessage};		
		return dataToProcess;
		}
	/*
	 * Method to establish communication with the server side. (Changing port system)
	 */
	public int establishComm(String IP) throws IOException{	
		int newPort;
		String receivedHeader, receivedMessage;
		receivedHeader = readHeader(inFromServer);
		decodeHeader(receivedHeader);
		if(receivedMessageType==3){
			// Change communication to received port
			receivedMessage = readMessage(receivedMessageLength, inFromServer);
			myApp.getClientSocket().close();	
			newPort = Integer.parseInt(receivedMessage);
			System.out.println("New port at: "+newPort);
			// Open a new socket with an assigned port
			myApp.setClientSocket(new Socket(IP, newPort));

			myApp.setOutToServer(new DataOutputStream(myApp.getClientSocket().getOutputStream()));
			myApp.setInFromServer(new BufferedReader(new InputStreamReader(myApp.getClientSocket().getInputStream())));
			this.outToServer = myApp.getOutToServer();
			this.inFromServer = myApp.getInFromServer();
			receivedHeader = readHeader(inFromServer);
			decodeHeader(receivedHeader);
			// Read Server Response in order to know its status
			
			receivedMessage = readMessage(receivedMessageLength, inFromServer);
			if(receivedMessageType==4){
				// Connection in good health
				Log.d(TAG, "Connection established succesfully");
				Log.d(TAG, receivedMessage);								
			} else if (receivedMessageType==5){
				// There has been a problem with the server side
				Log.d(TAG, "Error while connection stablishment");
				Log.d(TAG, receivedMessage);
				System.out.println(receivedMessage);
				// Sending response to close communication				
			}
		}
		return receivedMessageType;
	}


	/*
	 * Method to padding with 0's at the beginning of the header in order to send a 9 bytes header
	 */	
	private static String paddingHeaderMessage(String headerToPad){
		while(headerToPad.length()<9){
			headerToPad = "0" + headerToPad;
			}
		System.out.println(headerToPad);
		System.out.println(headerToPad.length());
		return headerToPad;
	 }
	
	/*
	 *  Method to read the header of a response from the socket
	 */
	private static String readHeader(BufferedReader inFromServer) throws IOException{
		int i = 0, read;
		String receivedHeader = "";
		while(i<9){
			read = inFromServer.read();
			// Character.toString((char) read)) converts ASCII code to the associated character
			receivedHeader = receivedHeader + Character.toString((char) read);
			System.out.println("Header: "+ Character.toString((char) read));
			i = i+1;
		}
		System.out.println("Lenght of received header is: "+ receivedHeader.length());
		return receivedHeader;
	}
	
	private static String readMessage(Integer messageLength, BufferedReader inFromServer) throws IOException{
		int i = 0, read;
		String receivedMessage = "";
		while(i<messageLength){
			read = inFromServer.read();
			// Converts ASCII code to the associated character
			receivedMessage = receivedMessage + Character.toString((char) read);
			i = i + 1;
		}
		return receivedMessage;
	}
	
	/*
	 *  Method to split the two parts of a header "Length of Message" + "Type of Message" and parse them to Integers
	 */
	private static void decodeHeader(String header){
		int separator;
		String length, type;
		separator = header.indexOf(",");
		
		length = header.substring(0, separator);
		type = header.substring(separator+1, 9);
		
		receivedMessageLength = Integer.parseInt(length);
		receivedMessageType = Integer.parseInt(type);		
		
		System.out.println("Decoded Header Length: " + receivedMessageLength + "\n Decoded Header Type: " + receivedMessageType);		
	}
	
	private String[] decodeAvailableDevicesQuery(String receivedMessage){
		String[] decodedQuery;
		decodedQuery = receivedMessage.split(",");
		return decodedQuery;
	}


	public void close() throws IOException {
		// TODO Auto-generated method stub
		myApp.getClientSocket().close();
	}
	
	@Override
	protected Integer doInBackground(String... params) {	
		int code = 0;
		
		try {
			myApp.setClientSocket(new Socket(Constants.SocketIp, Constants.SocketPort));
			myApp.setOutToServer(new DataOutputStream(myApp.getClientSocket().getOutputStream()));
			myApp.setInFromServer(new BufferedReader(new InputStreamReader(myApp.getClientSocket().getInputStream())));
			
			inFromServer = myApp.getInFromServer();
			outToServer = myApp.getOutToServer();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		if (params[0].equals((String)Constants.ESTABLISH_CONNECTION)){
			Log.d(TAG, "Trying to connect the server!!");
			try {
				ag33220aFlag = false;
				hp33120aFlag = false;
				hp34401aFlag = false;
				hp54602bFlag = false;
				code = establishComm(Constants.SocketIp);
				if (code == 4){
					String[] receivedData;
					receivedData = bidirectComm(Constants.AVAILABLE_FIELD,
							Constants.AVAILABLE_DEVICES_QUERY);
					
					receivedData = decodeAvailableDevicesQuery(receivedData[1]);
					Log.d(TAG, "AG33220A is -> " + receivedData[0]);
					Log.d(TAG, "HP33120A is -> " + receivedData[1]);
					Log.d(TAG, "HP34401A is -> " + receivedData[2]);
					Log.d(TAG, "HP54602B is -> " + receivedData[3]);
					
					if(receivedData[0].equals("1"))
						ag33220aFlag = true;
					if(receivedData[1].equals("1"))
						hp33120aFlag = true;
					if(receivedData[2].equals("1"))
						hp34401aFlag = true;
					if(receivedData[3].equals("1"))
						hp54602bFlag = true;
					
					
				} else if (code == 5){
					Log.d(TAG, "Server is Busy!!! Try again later! ");
				} else{
					Log.d(TAG, "Unknown Error!! Try again later! ");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (params[0].equals((String) Constants.BIDIRECT_COMMUNICATION)){
			Log.d(TAG, "BidirectComm has been called!!");
		}
		return code;
	}	

	
	@Override
	protected void onPostExecute(Integer result) {
		Log.d(TAG, "Finished establishing communication process");		
		// Here we are going to update the UI
		if(result == 4){
			// Everything was ok, so we need to launch a new activity
			Intent intent = new Intent(context, DevicesActivity.class);
			intent.putExtra(Constants.AG33220A_STATUS, ag33220aFlag);
			intent.putExtra(Constants.HP33120A_STATUS, hp33120aFlag);
			intent.putExtra(Constants.HP34401A_STATUS, hp34401aFlag);
			intent.putExtra(Constants.HP54602B_STATUS, hp54602bFlag);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);			
			context.startActivity(intent);
		} else if (result == 5){
			// Server is busy
			errMsg.setText("Server is Busy!!! Try again later! ");
		} else {
			// Unknown error
			errMsg.setText("Unknown Error!! Try again later!");
		}
    }

}
