package com.enroll.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.enroll.modules.oauth2.OAuth2Filter;
import com.enroll.modules.oauth2.OAuth2Realm;


@Configuration
public class ShiroConfig {

    @Bean("sessionManager")
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        //sessionManager.setSessionIdCookieEnabled(false);
        return sessionManager;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(OAuth2Realm oAuth2Realm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(oAuth2Realm);
        securityManager.setSessionManager(sessionManager);

        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        //oauth过滤
        Map<String, Filter> filters = new HashMap<>();
        filters.put("oauth2", new OAuth2Filter());
        shiroFilter.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap<>();

        //swagger配置
        filterMap.put("/sys/login.do", "anon");
        filterMap.put("/school/student/**", "anon");
        filterMap.put("/sys/touristVisit.do", "anon");
        filterMap.put("/sys/register/save.do", "anon");
        filterMap.put("/sys/user/checkUsername.do", "anon");
        filterMap.put("/sys/user/checkEmail.do", "anon");
        filterMap.put("/sys/user/checkPhoneIsUse.do", "anon");
        filterMap.put("/sys/user/checkEmailIsUse.do", "anon");
        filterMap.put("/sys/user/resetPassword.do", "anon");
        filterMap.put("/sys/user/updatePassword.do", "anon");
        filterMap.put("/sys/user/sendCode.do", "anon");
        filterMap.put("/sys/print/word.do", "anon");
        filterMap.put("/school/list/info/export.do", "anon");
        filterMap.put("/sys/logout.do", "logout");
        filterMap.put("/*", "anon");
        filterMap.put("/download/**", "anon");
        filterMap.put("/**/*.css", "anon");
        filterMap.put("/**/*.js", "anon");
        filterMap.put("/*.jsp", "anon");
        filterMap.put("/**/*.jsp", "anon");
        filterMap.put("/**/*.html", "anon");
        filterMap.put("/static/fonts/**", "anon");
        filterMap.put("/static/images/campusCultural/**", "anon");
        filterMap.put("/static/images/student/**", "anon");
        filterMap.put("/static/js/plugins/**", "anon");
        filterMap.put("/static/student/fonts/**", "anon");
        filterMap.put("/static/student/images/**", "anon");
        filterMap.put("/demo/fonts/**", "anon");
        filterMap.put("/demo/images/**", "anon");
        filterMap.put("/upload/images/**", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/captcha.jpg", "anon");      
        filterMap.put("/", "anon");
        filterMap.put("/**", "oauth2");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}

