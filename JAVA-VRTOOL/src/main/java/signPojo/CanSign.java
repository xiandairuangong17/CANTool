package signPojo;

/**
 * Created by Admin on 2017/10/19.
 */
public class CanSign {
	private int autoId;
	private int messageId;
	private String signName;
	private boolean starBit;
	private boolean bitLength;
	private boolean bitType;
	private double offsetValue;
	private double resolutionValue;
	private double minPhyValue;

	public int getAutoId() {
		return autoId;
	}

	public int getMessageId() {
		return messageId;
	}

	public String getSignName() {
		return signName;
	}

	public boolean isStarBit() {
		return starBit;
	}

	public boolean isBitLength() {
		return bitLength;
	}

	public boolean isBitType() {
		return bitType;
	}

	public double getOffsetValue() {
		return offsetValue;
	}

	public double getResolutionValue() {
		return resolutionValue;
	}

	public double getMinPhyValue() {
		return minPhyValue;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public void setStarBit(boolean starBit) {
		this.starBit = starBit;
	}

	public void setBitLength(boolean bitLength) {
		this.bitLength = bitLength;
	}

	public void setBitType(boolean bitType) {
		this.bitType = bitType;
	}

	public void setOffsetValue(double offsetValue) {
		this.offsetValue = offsetValue;
	}

	public void setResolutionValue(double resolutionValue) {
		this.resolutionValue = resolutionValue;
	}

	public void setMinPhyValue(double minPhyValue) {
		this.minPhyValue = minPhyValue;
	}
}
