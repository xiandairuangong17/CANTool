package signPojo;

/**
 * Created by Admin on 2017/10/19.
 */
public class CanMessage {
	private int id;
	private String messageName;
	private boolean dcl;
	private String nodeName;

	public int getId() {
		return id;
	}

	public String getMessageName() {
		return messageName;
	}

	public boolean isDcl() {
		return dcl;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	public void setDcl(boolean dcl) {
		this.dcl = dcl;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
}
