package struts2.action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import org.apache.struts2.json.annotations.JSON;

public class JSONExample
{
	// ģ�⴦�����ĳ�Ա����
	private int[] ints = {10, 20};
	private Map<String , String> map
		= new HashMap<String , String>();
	private String customName = "�˿�";
	// ��װ���������������Ա����
	private String field1;
	// 'transient'���εĳ�Ա�������ᱻ���л�
	private transient String field2;
	// û��setter��getter�����ĳ�Ա�������ᱻ���л�
	private String field3;

	public String execute()
	{
		map.put("name", "struts2 json example");
		return Action.SUCCESS;
	}

	// ʹ��ע�����ı�ó�Ա�������л��������
	@JSON(name="newName")
	public Map getMap()
	{
		return this.map;
	}

	// customName��setter��getter����
	public void setCustomName(String customName)
	{
		this.customName = customName;
	}
	public String getCustomName()
	{
		return this.customName;
	}

	// field1��setter��getter����
	public void setField1(String field1)
	{
		this.field1 = field1;
	}
	public String getField1()
	{
		return this.field1;
	}

	// field2��setter��getter����
	public void setField2(String field2)
	{
		this.field2 = field2;
	}
	public String getField2()
	{
		return this.field2;
	}

	// field3��setter��getter����
	public void setField3(String field3)
	{
		this.field3 = field3;
	}
	public String getField3()
	{
		return this.field3;
	}
}