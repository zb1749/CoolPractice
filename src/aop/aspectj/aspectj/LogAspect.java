package aop.aspectj.aspectj;

public aspect LogAspect
{
	// ����һ��PointCut������ΪlogPointcut��
	// ��Pointcut�����˺���������������ʽ�������ɸ��ø��������ʽ
	pointcut logPointcut()
		:execution(* org.crazyit.app.service.*.*(..));
	after():logPointcut()
	{
		System.out.println("ģ���¼��־...");
	}
}
