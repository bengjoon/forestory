package com.forestory.client.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.forestory.domain.User;
import com.forestory.dto.CustomUserDetails;
import com.forestory.dto.GoogleReponse;
import com.forestory.dto.NaverResponse;
import com.forestory.dto.OAuth2Response;
import com.forestory.repository.UserRepository;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    //DefaultOAuth2UserService OAuth2UserService의 구현체
	
    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleReponse(oAuth2User.getAttributes());
        }
        else {
            System.out.println("OAuth2 로그인 실패");
        }
        String oAuthID = oAuth2Response.getProvider()+"_"+oAuth2Response.getProviderId();
        String nick[] = oAuth2Response.getEmail().split("@");
        boolean existsByUserNick = userRepository.existsByUserNick(nick[0]);
        
        while(existsByUserNick) {
        	int i = 1;
        	nick[0] += i;
        	i++;
        	existsByUserNick = userRepository.existsByUserNick(nick[0]);
        };
        
    	String role = "ROLE_USER";
        User user = userRepository.findByUserEmail(oAuthID);
        
        if (user == null) {
            user = User.builder()
    				.userEmail(oAuthID)
    				.userRole(role)
    				.userNick(nick[0])
//            		.userPhone("나중에할게")
    				.build();
            
            userRepository.save(user);
        }
        
        return new CustomUserDetails(oAuth2Response, user);
//        return new CustomOAuth2User(oAuth2Response, user);
    }
}
