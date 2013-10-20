package pfc.android_virtual_lab;


public class HP34401a {
	private String frame, dataValidationMessage;
	private int function, triggerSource;
	private float resolution, range;
	private int autoZero, autoRange;
	private boolean dataValidationFlag;
	
	
	public HP34401a(){
		/*
		 *  Constructor to build a default instance of HP34401A Digital Multimeter
		 *  Default configuration -> Function DC Voltage
		 */
		this.function = 0;
		this.resolution = 5.5f;
		this.range = 100f;
		
		this.autoRange = 0;
		this.autoZero = 0;
		
		// Frame Initialization to an empty value
		this.frame = "";
	}
	
	public void setFrame(){
		this.frame = String.valueOf(function)
				+ "," + String.valueOf(resolution)
				+ "," + String.valueOf(triggerSource)
				+ "," + String.valueOf(range)
				+ "," + String.valueOf(autoZero)
				+ "," + String.valueOf(autoRange);
		System.out.println(this.frame);
	}
	
	public boolean dataValidation(String dataValMessage){
		dataValidationFlag = false;
		dataValidationMessage = dataValMessage;
		
		return dataValidationFlag;
	}

	public String getFrame() {
		return frame;
	}

	public void setFrame(String frame) {
		this.frame = frame;
	}

	public String getDataValidationMessage() {
		return dataValidationMessage;
	}

	public void setDataValidationMessage(String dataValidationMessage) {
		this.dataValidationMessage = dataValidationMessage;
	}

	public int getFunction() {
		return function;
	}

	public void setFunction(int function) {
		this.function = function;
	}

	public float getResolution() {
		return resolution;
	}

	public void setResolution(int resolution) {
		// Device needs a float to know which resolution has been configured
		switch(resolution){
		case 0:
			this.resolution = 4.5f;
			break;
		case 1:
			this.resolution = 5.5f;
			break;
		case 2:
			this.resolution = 6.5f;
			break;
		}
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

	public int getTriggerSource() {
		return triggerSource;
	}

	public void setTriggerSource(int triggerSource) {
		this.triggerSource = triggerSource;
	}

	public int getAutoZero() {
		return autoZero;
	}

	public void setAutoZero(boolean autoZero) {
		System.out.println("autoZero -> " + autoZero);
		if(autoZero){
			this.autoZero = 1;
		} else {
			this.autoZero = 0;
		}		
	}

	public int getAutoRange() {
		return autoRange;
	}

	public void setAutoRange(boolean autoRange) {
		System.out.println("autoRange -> " + autoRange);
		if(autoRange){
			this.autoRange = 1;			
		} else {
			this.autoRange = 0;
		}		
	}
}
