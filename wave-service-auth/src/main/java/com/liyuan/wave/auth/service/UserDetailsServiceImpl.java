package com.liyuan.wave.auth.service;

import com.liyuan.wave.auth.client.UserClient;
import com.liyuan.wave.auth.po.UserJwt;
import com.liyuan.wave.po.ucenter.Menu;
import com.liyuan.wave.po.ucenter.ext.UserExt;
import com.liyuan.wave.common.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserClient userClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*return User.withUsername(username)
				.password(passwordEncoder.encode("admin"))
				.authorities("ROLE_ADMIN")
				.build();*/
		//数据库对象 companyId,权限,User所有值
		UserExt userExt = userClient.getUserext(username);
		//密码
		String password=userExt.getPassword();
		//权限
		List<Menu> menus = userExt.getPermissions();
		ArrayList<String>  permissions = new ArrayList<>();
		for(Menu item: menus){
			if(StringUtils.isNotNull(item)){
				permissions.add(item.getCode());
			}
		}
		UserJwt userJwt = new UserJwt(username,password, AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", permissions)));
		BeanUtils.copyProperties(userExt,userJwt);

		return userJwt;
	}
}