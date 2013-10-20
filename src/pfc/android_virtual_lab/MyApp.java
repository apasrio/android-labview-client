package pfc.android_virtual_lab;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Socket;

import android.app.Application;


public class MyApp extends Application{
	
	// Class to keep the current status of the whole program!
	private boolean AG33220aStatus = false;
	private boolean HP33120aStatus = false;
	private boolean HP34401aStatus = false;
	private boolean HP54602bStatus = false;
	
	// Client socket
	private Socket clientSocket;
	private DataOutputStream outToServer; 
	private BufferedReader inFromServer;
	
	public Socket getClientSocket() {
		return clientSocket;
	}
	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	public DataOutputStream getOutToServer() {
		return outToServer;
	}
	public void setOutToServer(DataOutputStream outToServer) {
		this.outToServer = outToServer;
	}
	public BufferedReader getInFromServer() {
		return inFromServer;
	}
	public void setInFromServer(BufferedReader inFromServer) {
		this.inFromServer = inFromServer;
	}
	public boolean isAG33220aStatus() {
		return AG33220aStatus;
	}
	public void setAG33220aStatus(boolean aG33220aStatus) {
		AG33220aStatus = aG33220aStatus;
	}
	public boolean isHP33120aStatus() {
		return HP33120aStatus;
	}
	public void setHP33120aStatus(boolean hP33120aStatus) {
		HP33120aStatus = hP33120aStatus;
	}
	public boolean isHP34401aStatus() {
		return HP34401aStatus;
	}
	public void setHP34401aStatus(boolean hP34401aStatus) {
		HP34401aStatus = hP34401aStatus;
	}
	public boolean isHP54602bStatus() {
		return HP54602bStatus;
	}
	public void setHP54602bStatus(boolean hP54602bStatus) {
		HP54602bStatus = hP54602bStatus;
	} 
	
	
}
