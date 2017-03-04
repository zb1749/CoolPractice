package jdk.serialize;

/**
 * 序列化Serialize，反序列化Deserialize。要序列化必须实现Serializable（标记接口，无需实现任何方法）或Externalizable接口。
 * 反序列化要提供Java对象所属类的class文件，无需调用构造器
 *
 * 使用序列化机制向文件中写入了多个java对象，使用反序列化机制恢复对象时必须按实际写入的顺序读取；当一个可序列化类有多个父类时（直接或间接），
 * 这些父类要么有无参数的构造器，要么也是可序列化的，如果父类不可序列化，只是带有无参数的构造器，则父类中定义的Field值不会序列化到二进制流中。
 *
 * Created by kevin on 2017/3/4.
 */
public class SerializeTest {
}
