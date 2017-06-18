package JavaRMI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.rmi.Naming;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import presentation.userui.LoginUI;

public class Client {
	public static RMIFactory factory;

	public static void main(String[] args) {

		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		UIManager.put("RootPane.setupButtonVisible", false);// 隐藏设置
		BeautyEyeLNFHelper.translucencyAtFrameInactive = true;// 关闭半透明效果
		BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;

		new LoginUI();
		
	}

	public static RMIFactory getFactory() {
		if(factory==null){
			try {
				File file=new File("file/url.txt");
				FileReader fr=new FileReader(file);
				BufferedReader bufr=new BufferedReader(fr);
				String url=bufr.readLine();
				bufr.close();
				factory = (RMIFactory) Naming
						.lookup("rmi://"+url+":9999/factory");
			}  catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "连接服务器超时，登录失败", "系统信息",
						JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		return factory;
	}

}
