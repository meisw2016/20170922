package cn.springcloud.boot.ssh.autoinstall.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import cn.com.yusys.yusp.autoinstall.utils.RsaUtil;


@Service
public class UserService {

	@Value("${public-key}")
	String publicKeyPath;

	@Value("${private-key}")
	String privateKeyPath;

	public String getKey(String keyName) throws IOException {
		String path = keyName.equals("public_key") ? publicKeyPath : privateKeyPath;
		Resource resource = new ClassPathResource(path);
		Key key = null;
		ObjectInputStream ois = null;
		try {
			/** 读取私钥 */
			ois = new ObjectInputStream(resource.getInputStream());
			key = (Key) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ois.close();
		}
		return RsaUtil.getKeyBase64(key);
	}

}