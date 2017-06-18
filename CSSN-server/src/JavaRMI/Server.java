package JavaRMI;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
	
	public static void main(String[] args) {
		registerFactory();
	}
	
	// 这里使用了静态方法来注册工厂对象，
	public static void registerFactory(){
		try {
			RMIFactory factory = new RMIFactoryImpl();
			 LocateRegistry.createRegistry(9999);
			 Naming.bind("rmi://127.0.0.1:9999/factory",factory);
			System.out.println("服务器注册工厂成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
