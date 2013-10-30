package pfc.android_virtual_lab.util;

public class Constants {
	public final static String SocketIp = "192.168.1.6";
	public final static int SocketPort = 5020;
	
	// Messages and Types
	public final static String AVAILABLE_FIELD = "AVAILABLE_FIELD";
	public final static int AVAILABLE_DEVICES_QUERY = 6;
	public final static int ECHO_TYPE = 0;
	public final static int CLOSING_CONNECTION = 1;
	public final static int HP33120A_SUCCESSFUL_QUERY = 11;
	public final static int AG33220A_SUCCESSFUL_QUERY = 21;
	public final static int AG33220A_QUERY = 20;
	public final static int HP34401A_SUCCESSFUL_QUERY = 31;
	public final static int HP34401A_ERRONEOUS_QUERY = 33;
	public final static int HP54602B_SUCCESSFUL_QUERY = 41;
	public final static int HP54602B_ERRONEOUS_QUERY = 43;
	public final static String ECHO_TEST_MSG = "JUST_A_TEST_MESSAGE";
	
	// Dispatcher Strings
	public final static String ESTABLISH_CONNECTION = "EstablishConnection";
	public final static String BIDIRECT_COMMUNICATION = "BidirectCommunication";
	
	// Extras Strings
	public final static String AG33220A_STATUS = "AG33220A_STATUS";
	public final static String HP33120A_STATUS = "HP33120A_STATUS";
	public final static String HP34401A_STATUS = "HP34401A_STATUS";
	public final static String HP54602B_STATUS = "HP54602B_STATUS";
}
