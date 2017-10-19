package signPojo;

import java.util.Date;

/**
 * Created by Admin on 2017/10/19.
 */
public class CanMsgData {
	private int autoId;
	private String id;
	private boolean dcl;
	private String byteStr;
	private Date time;

	public int getAutoId() {
		return autoId;
	}

	public String getId() {
		return id;
	}

	public boolean isDcl() {
		return dcl;
	}

	public String getByteStr() {
		return byteStr;
	}

	public Date getTime() {
		return time;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDcl(boolean dcl) {
		this.dcl = dcl;
	}

	public void setByteStr(String byteStr) {
		this.byteStr = byteStr;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
