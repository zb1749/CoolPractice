package aop.aspectj.aspectj;

public aspect LogAspect
{
	// ����һ��PointCut������ΪlogPointcut��
	// ��Pointcut�����˺���������������ʽ�������ɸ��ø��������ʽ
	pointcut logPointcut()
		:execution(* aop.aspectj.*.*(..));
	after():logPointcut()
	{
		System.out.println("ģ���¼��־...");
	}
}
