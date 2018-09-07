package devlight.edu.conference.model;

public class Request_rate {
	private int request_id;
	private int juri_id;
	private double mark;

	public Request_rate(int request_id, int juri_id, double mark) {
		super();
		this.request_id = request_id;
		this.juri_id = juri_id;
		this.mark = mark;
	}

	public int getRequest_id() {
		return request_id;
	}

	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}

	public int getJuri_id() {
		return juri_id;
	}

	public void setJuri_id(int juri_id) {
		this.juri_id = juri_id;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "Request_rate [request_id=" + request_id + ", juri_id=" + juri_id + ", mark=" + mark + "]";
	}

}
