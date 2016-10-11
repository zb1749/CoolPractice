package aop.aspectj;

public class AspectJTest
{
	public static void main(String[] args)
	{
		Hello hello = new Hello();
		hello.foo();
		hello.addUser("ËïÎò¿Õ" , "7788");
		World world = new World();
		world.bar();
	}
}
