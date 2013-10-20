package pfc.android_virtual_lab;


public class HP54602b {
	private int ch1, ch2, ch1BW, ch2BW, autoset, positiveNegativeSlope;
	private int ch1Function, ch2Function, ch1Coupling, ch2Coupling;
	private int ch1Probe, ch2Probe, triggerSource;
	private float ch1Range, ch2Range, ch1Pos, ch2Pos, timeRange, timeDelay, triggerLevel;
	private String frame;
	
	public HP54602b(){
		/*
		 *  Constructor to build a default instance of HP54602B Oscilloscope
		 *  Default configuration -> Autoset with Vpp measure functions and CH1 and CH2 enabled
		 */		
		this.ch1 = 1;
		this.ch2 = 1;
		this.ch1BW = 0;
		this.ch2BW = 0;		
		
		this.ch1Function = 0;			// Vpp
		this.ch2Function = 0;			// Vpp
		this.ch1Coupling = 0;			// DC Coupling
		this.ch2Coupling = 0;			// DC Coupling
		
		this.autoset = 1;				// Autoset Enabled
		this.positiveNegativeSlope = 0;
		
		this.ch1Probe = 0;				// 1X Probe	
		this.ch2Probe = 0;				// 1X Probe
		this.triggerSource = 0;			// Channel 1
		
		// Next fields are not important because autoset is enabled
		this.ch1Range = 0f;				
		this.ch2Range = 0f;
		this.ch1Pos = 0f;
		this.ch2Pos = 0f;
		this.timeRange = 0f;
		this.timeDelay = 0f;
		this.triggerLevel = 0f;
	}
	
	public void setFrame(){
		this.frame = String.valueOf(ch1)
				+ "," + String.valueOf(ch2)
				+ "," + String.valueOf(ch1BW)
				+ "," + String.valueOf(ch2BW)
				+ "," + String.valueOf(ch1Function)
				+ "," + String.valueOf(ch2Function)
				+ "," + String.valueOf(ch1Coupling)
				+ "," + String.valueOf(ch2Coupling)
				+ "," + String.valueOf(autoset)
				+ "," + String.valueOf(positiveNegativeSlope)
				+ "," + String.valueOf(ch1Probe)
				+ "," + String.valueOf(ch2Probe)
				+ "," + String.valueOf(triggerSource)
				+ "," + String.valueOf(ch1Range)
				+ "," + String.valueOf(ch2Range)
				+ "," + String.valueOf(ch1Pos)
				+ "," + String.valueOf(ch2Pos)
				+ "," + String.valueOf(timeRange)
				+ "," + String.valueOf(timeDelay)
				+ "," + String.valueOf(triggerLevel);		
	}

	public void setCh1(boolean ch1) {
		if (ch1)
			this.ch1 = 1;
		else
			this.ch1 = 0;
	}

	public void setCh2(boolean ch2) {
		if(ch2)
			this.ch2 = 1;
		else
			this.ch2 = 0;
	}

	public void setCh1BW(boolean ch1BW) {
		if(ch1BW)
			this.ch1BW = 1;
		else
			this.ch1BW = 0;
	}

	public void setCh2BW(boolean ch2BW) {
		if(ch2BW)
			this.ch2BW = 1;
		else
			this.ch2BW = 0;
	}

	public void setAutoset(boolean autoset) {
		if(autoset)
			this.autoset = 1;
		else
			this.autoset = 0;
	}

	public void setPositiveNegativeSlope(boolean positiveNegativeSlope) {
		if(positiveNegativeSlope)
			this.positiveNegativeSlope = 1;
		else
			this.positiveNegativeSlope = 0;
		}

	public void setCh1Function(int ch1Function) {
		this.ch1Function = ch1Function;
	}

	public void setCh2Function(int ch2Function) {
		this.ch2Function = ch2Function;
	}

	public void setCh1Coupling(int ch1Coupling) {
		this.ch1Coupling = ch1Coupling;
	}

	public void setCh2Coupling(int ch2Coupling) {
		this.ch2Coupling = ch2Coupling;
	}

	public void setCh1Probe(int ch1Probe) {
		this.ch1Probe = ch1Probe;
	}

	public void setCh2Probe(int ch2Probe) {
		this.ch2Probe = ch2Probe;
	}

	public void setTriggerSource(int triggerSource) {
		this.triggerSource = triggerSource;
	}

	public void setCh1Range(float ch1Range) {
		this.ch1Range = ch1Range;
	}

	public void setCh2Range(float ch2Range) {
		this.ch2Range = ch2Range;
	}

	public void setCh1Pos(float ch1Pos) {
		this.ch1Pos = ch1Pos;
	}

	public void setCh2Pos(float ch2Pos) {
		this.ch2Pos = ch2Pos;
	}

	public void setTimeRange(float timeRange) {
		this.timeRange = timeRange;
	}

	public void setTimeDelay(float timeDelay) {
		this.timeDelay = timeDelay;
	}

	public void setTriggerLevel(float triggerLevel) {
		this.triggerLevel = triggerLevel;
	}

	public String getFrame() {
		return frame;
	}
}
