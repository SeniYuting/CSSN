package JavaRMI;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
	
	public static void main(String[] args) {
		registerFactory();
	}
	
	// ����ʹ���˾�̬������ע�Ṥ������
	public static void registerFactory(){
		try {
			RMIFactory factory = new RMIFactoryImpl();
			 LocateRegistry.createRegistry(9999);
			 Naming.bind("rmi://127.0.0.1:9999/factory",factory);
			System.out.println("������ע�Ṥ���ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
