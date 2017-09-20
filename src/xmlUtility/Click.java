package xmlUtility;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"time", "torf", "x", "y"})
public class Click {

	private long time;
	private boolean torf;
    private int x;
	private int y;


	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public boolean isTorf() {
		return torf;
	}
	public void setTorf(boolean torf) {
		this.torf = torf;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public Click(){
		setTime(0);
		setTorf(false);
		setX(0);
		setY(0);
	}

	public Click(int time, boolean torf, int x, int y){
		setTime(time);
		setTorf(torf);
		setX(x);
		setY(y);
	}

}
