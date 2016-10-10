package aop.aspectj.aspectj;

public aspect TxAspect
{
	//  指定执行Hello.sayHello()方法时执行下面代码块
	Object around():call(* org.crazyit.app.service.*.*(..))
	{
		System.out.println("模拟开启事务...");
		// 回调原来的目标方法
		Object rvt = proceed();
		System.out.println("模拟结束事务...");
		return rvt;
	}
}