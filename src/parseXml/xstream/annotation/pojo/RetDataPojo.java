package parseXml.xstream.annotation.pojo;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

public class RetDataPojo {

	@XStreamImplicit(itemFieldName="RetEnjoyBindData")
	private List<RetEnjoyBindData> RetEnjoyBindData;

	public List<RetEnjoyBindData> getRetEnjoyBindData() {
		return RetEnjoyBindData;
	}

	public void setRetEnjoyBindData(List<RetEnjoyBindData> retEnjoyBindData) {
		RetEnjoyBindData = retEnjoyBindData;
	}
	
}
