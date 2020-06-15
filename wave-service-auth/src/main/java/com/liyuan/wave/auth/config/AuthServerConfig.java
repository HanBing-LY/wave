package com.liyuan.wave.auth.config;

import com.liyuan.wave.auth.converter.CustomerAuthenticationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.security.KeyPair;


@Configuration
@EnableAuthorizationServer
@EnableRedisHttpSession
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	@Resource
    private UserDetailsService userDetailsService;
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomerAuthenticationConverter customerAuthenticationConverter;

	@Bean
	public TokenStore jwtTokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter(customerAuthenticationConverter));
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter(CustomerAuthenticationConverter customerAuthenticationConverter) {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		// jwt需要一个签名
		// converter.setSigningKey("jwtSign");此方式不安全
		// 因此使用一个密钥key来当作签名
		KeyPair keyPair = new KeyStoreKeyFactory(
				new ClassPathResource("keystore"),
				"keystore".toCharArray())
				.getKeyPair("key","key".toCharArray());
		converter.setKeyPair(keyPair);
		DefaultAccessTokenConverter accessTokenConverter =  (DefaultAccessTokenConverter)converter.getAccessTokenConverter();
		accessTokenConverter.setUserTokenConverter(customerAuthenticationConverter);
		return converter;
	}

	//哪些用户可以来获取token
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		//配置jwtTokenStore
		endpoints.tokenStore(jwtTokenStore())
				.accessTokenConverter(jwtAccessTokenConverter(customerAuthenticationConverter))
				//用于给refresh_token使用，刷新token只有用户名，因此会调用这个里面的方法来获取密码
				.userDetailsService(userDetailsService)
				//启用密码模式
				.authenticationManager(authenticationManager);
	}


	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(new JdbcClientDetailsService(dataSource));
		//或者下面这种方式
		//clients.jdbc(dataSource);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		//支持验证token
		security
				.tokenKeyAccess("isAuthenticated()")
				.checkTokenAccess("isAuthenticated()");
	}

}
