package aop.aspectj.aspectj;

public aspect TxAspect
{
	//  ָ��ִ��Hello.sayHello()����ʱִ����������
	Object around():call(* aop.aspectj.*.*(..))
	{
		System.out.println("ģ�⿪������...");
		// �ص�ԭ����Ŀ�귽��
		Object rvt = proceed();
		System.out.println("ģ���������...");
		return rvt;
	}
}