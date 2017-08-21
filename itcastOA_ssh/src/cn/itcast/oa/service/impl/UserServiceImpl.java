package cn.itcast.oa.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import cn.itcast.oa.baseImpl.BaseDaoImpl;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.UserService;
@Service
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService {

	@Override
	public void save(User user) {
		user.setPassword(DigestUtils.md5Hex("1234"));
		super.save(user);
	}

	@Override
	public User findloginNameAndPassword(String loginName, String password) {
		
		if(loginName==null||password==null){
			return null;
		}else{
			
			return  (User) getSession().createQuery//
					("FROM User u WHERE u.loginName=? AND u.password=?")//
					.setParameter(0, loginName)//
					.setParameter(1,DigestUtils.md5Hex(password))//需要使用MD5摘要
					.uniqueResult();
		}
		
	}

	
	  
}
