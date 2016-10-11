package aop.aspectj.aspectj;

public aspect AuthAspect
{
	// 指定在执行org.crazyit.app.service包中任意类的、任意方法之前执行下面代码块
	// 第一个星号表示返回值不限；第二个星号表示类名不限；
	// 第三个星号表示方法名不限；圆括号中..代表任意个数、类型不限的形参
	before(): execution(* aop.aspectj.*.*(..))
	{
		System.out.println("模拟进行权限检查...");
	}
}
