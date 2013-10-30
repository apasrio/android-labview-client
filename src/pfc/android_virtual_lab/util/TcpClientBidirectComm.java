package pfc.android_virtual_lab.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

import pfc.android_virtual_lab.MyApp;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class TcpClientBidirectComm {	
	// extends AsyncTask<String, Integer, String[]>
	static int receivedMessageLength, receivedMessageType;
	private DataOutputStream outToServer; 
	private BufferedReader inFromServer; 
	private MyApp myApp;
	
	private static final String TAG = "TcpClientBidirectComm";	
	
	public TcpClientBidirectComm(Context context){
		this.myApp = (MyApp) context.getApplicationContext();
		this.outToServer = myApp.getOutToServer();
		this.inFromServer = myApp.getInFromServer(); 
	}
	/*
	@Override
	protected String[] doInBackground(String... params) {		
		String[] result = null;
		try {
			result = bidirectComm(params[0], Integer.valueOf(params[1]));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, "Number Format Exception");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, "Exception!");
		}
		return result;
	}
	
	@Override
	protected void onPostExecute(String[] result){
		// Log.d(TAG, "Type of Message: " + result[0]);
		// Log.d(TAG, "Received Message: " + result[1]);		
		Log.d(TAG, "onPostExecute");
		if(result != null){
			Log.d(TAG, "Type of Message: " + result[0]);
			Log.d(TAG, "Received Message: " + result[1]);	
		} else {
			Log.d(TAG, "There's been an error in communication process!");
		}
	}*/
	
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
	
	private void receiverDecider(String typeOfReceivedMessage, String receivedMessage){
		int type;
		type = Integer.valueOf(typeOfReceivedMessage);
		switch(type){
		case Constants.CLOSING_CONNECTION:
			// Closing sockets
			break;
		case Constants.HP33120A_SUCCESSFUL_QUERY:
			// Request to HP33120a has been successful
		}
	}

}
