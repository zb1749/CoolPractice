package aop.aspectj;

public class Hello
{
	// ����һ���򵥷�����ģ��Ӧ���е�ҵ���߼�����
	public void foo()
	{
		System.out.println("ִ��Hello�����foo()����");
	}
	// ����һ��addUser()������ģ��Ӧ���е�����û��ķ���
	public int addUser(String name , String pass)
	{
		System.out.println("ִ��Hello�����addUser����û���" + name);
		return 20;
	}
}
