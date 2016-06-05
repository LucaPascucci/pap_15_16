package ass08;

class HeartbeatSlot {

	private double t0,t1;
	private double v0,v1;

	public HeartbeatSlot(double t0, double t1, double v0, double v1) {
		super();
		this.t0 = t0;
		this.t1 = t1;
		this.v0 = v0;
		this.v1 = v1;
	}

	public double getFromTime() {
		return t0;
	}

	public double getToTime() {
		return t1;
	}

	public double getFromValue() {
		return v0;
	}

	public double getToValue() {
		return v1;
	}
	
	
}
