//package com.forestory.dto;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//
//import com.forestory.domain.User;
//
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Map;
//
//public class CustomOAuth2User implements OAuth2User {
//
//    private final OAuth2Response oAuth2Response;
//    private User user;
//
//    public CustomOAuth2User(OAuth2Response oAuth2Response, User user) {
//        this.oAuth2Response = oAuth2Response;
//        this.user = user;
//    }
//
//    @Override
//    public Map<String, Object> getAttributes() {
//        return null;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
//        Collection<GrantedAuthority> collection = new ArrayList<>();
//
//        collection.add(new GrantedAuthority() {
//
//            @Override
//            public String getAuthority() {
//                return user.getUserRole();
//            }
//        });
//
//        return collection;
//    }
//
//    @Override
//    public String getName() {
//        return oAuth2Response.getName();
//    }
//
//    public String getUsername() {
//        return oAuth2Response.getProvider()+"_"+oAuth2Response.getProviderId();
//    }
//    
//    public String getUserRegdate() {
//		// yyyy-MM-dd
//		String userRegdate = user.getUserRegdate().format(DateTimeFormatter.ISO_LOCAL_DATE);
//		return userRegdate;
//	}
//	
//	public String getUserNick() {
//		return user.getUserNick();
//	}
//    
//}
