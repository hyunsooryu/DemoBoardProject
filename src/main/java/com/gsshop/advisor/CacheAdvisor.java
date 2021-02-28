package com.gsshop.advisor;

import com.gsshop.beans.BoardInfoBean;
import com.gsshop.beans.UserBean;
import com.gsshop.cache.RedisCacheWrapper;
import com.gsshop.service.BoardService;
import com.gsshop.service.TestService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;

@Aspect
@Component
@PropertySource(value = "classpath:/properties/${spring.profiles.active}_cache.properties")
public class CacheAdvisor {
    Logger logger = LoggerFactory.getLogger(CacheAdvisor.class);

    private RedisCacheWrapper<UserBean> userBeanRedisCacheWrapper;

    private RedisCacheWrapper<List<BoardInfoBean>> boardInfoBeanRedisCacheWrapper;

    @Value("${board.info.list.key}")
    private String BOARD_INFO_LIST_KEY;


    public CacheAdvisor(RedisCacheWrapper<UserBean> userBeanRedisCacheWrapper,
                        RedisCacheWrapper<List<BoardInfoBean>> boardInfoBeanRedisCacheWrapper){
        logger.info("CacheAdvisor이 생성되었습니다.");
        this.userBeanRedisCacheWrapper = userBeanRedisCacheWrapper;
        this.boardInfoBeanRedisCacheWrapper = boardInfoBeanRedisCacheWrapper;
    }

    @PostConstruct
    public void initMethod(){
        logger.info("INJECTION CHECK");
        logger.info("userBeanRedisCacheWrapper : " + userBeanRedisCacheWrapper);
    }

    @Before(value = "@annotation(com.gsshop.annotations.RedisCacheFirst)")
    public void redisCacheFirstBefore(){
        System.out.println("redis Cache First ----- Before AOP");
    }

    @Around(value="@annotation(com.gsshop.annotations.RedisCacheFirst)")
    public Object redisCacheFirstAround(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("redis Cache First ----- AROUND AOP");
        //만일 Caching의 타겟이 유저 서비스라면
        if(pjp.getTarget().getClass().equals(TestService.class)){
            String key = pjp.getArgs()[0].toString();
            if(!StringUtils.hasLength(key)){
                return null;
            }
            UserBean userBean = userBeanRedisCacheWrapper.getFromCache(key).orElseGet(()->{
                UserBean tmpUserBean = null;
                try {
                    tmpUserBean = (UserBean)pjp.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                if(tmpUserBean != null)userBeanRedisCacheWrapper.setIntoCache(key, tmpUserBean);
                return tmpUserBean;
            });
            return userBean;
        //만일 Caching의 타겟이 보드 서비스라면
        }else if(pjp.getTarget().getClass().equals(BoardService.class)){
            String key = BOARD_INFO_LIST_KEY;
            System.out.println(key);
            if(!StringUtils.hasLength(key)){
                return null;
            }
            List<BoardInfoBean> boardInfoBean = boardInfoBeanRedisCacheWrapper.getFromCache(key).orElseGet(()->{
                List<BoardInfoBean> tmpBoardInfoBeanList = null;
                try {
                    tmpBoardInfoBeanList = (List<BoardInfoBean>) pjp.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                if(tmpBoardInfoBeanList != null)boardInfoBeanRedisCacheWrapper.setIntoCache(key, tmpBoardInfoBeanList);
                return tmpBoardInfoBeanList;
            });
            return boardInfoBean;
        }
        Object o = pjp.proceed();
        return o;
    }
}
