package com.liyuan.wave.auth.converter;

import com.liyuan.wave.auth.po.UserJwt;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomerAuthenticationConverter extends DefaultUserAuthenticationConverter {
    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        LinkedHashMap<String,Object> response = new LinkedHashMap<>();
        String name = authentication.getName();
        response.put("user_name",name);
        response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));

        UserJwt userJwt = (UserJwt) authentication.getPrincipal();
        response.put("id",userJwt.getId());
        response.put("name",userJwt.getName());
        response.put("utype",userJwt.getUtype());
        response.put("userpic",userJwt.getUserpic());
        response.put("companyId",userJwt.getCompanyId());
        return response;


    }
}
