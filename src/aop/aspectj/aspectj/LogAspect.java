package aop.aspectj.aspectj;

public aspect LogAspect
{
	// 定义一个PointCut，其名为logPointcut，
	// 该Pointcut代表了后面给出的切入点表达式，这样可复用该切入点表达式
	pointcut logPointcut()
		:execution(* org.crazyit.app.service.*.*(..));
	after():logPointcut()
	{
		System.out.println("模拟记录日志...");
	}
}
