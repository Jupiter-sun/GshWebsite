package cn.yfjz.core.security.service;

import cn.yfjz.core.global.AppContextHolder;
import cn.yfjz.core.global.ISecurityConst;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liwj on 16/8/5.
 */
public class JWTAuthService {
    /** 默认过期时间 */
    private static final int DEFAULT_EXPIRE_DAYS = 7;

    private static Log logger = LogFactory.getLog(JWTAuthService.class);

    /**
     * 生成验证token，客户端可以通过配置header(Authorization Bearer xxxx)来进行登录和调用接口，token的过期时间是7天。
     * <strong>注意：这个方法会缓存token</strong>
     * @param userId
     * @param expSeconds token的过期时间，默认是7天内过期
     * @return
     */
    public static String issue(String userId) {
        if(StringUtils.isEmpty(userId)) {
            return null;
        }
        //如果已经存在token则直接返回
        RedisTemplate<String, Object> redisTemplate = AppContextHolder.getContext().getBean(RedisTemplate.class);
        String token = String.valueOf(ObjectUtils.defaultIfNull(redisTemplate.opsForValue().get("token:" + userId), ""));
        if(StringUtils.isNotEmpty(token)) {
            String existTokenUserId = verifyAndReturnUserId(token);
            //如果token已经存在并且还未失效则返回当前的token
            if(userId.equals(existTokenUserId)) {
                return token;
            }
        }
        token = issue(userId, null);
        redisTemplate.opsForValue().set("token:" + userId, token);
        return token;
    }

    /**
     * 生成验证token，客户端可以通过配置header(Authorization Bearer xxxx)来进行登录和调用接口
     * @param userId
     * @param expSeconds token的过期时间，默认是7天内过期
     * @return
     */
    public static String issue(String userId, Integer expSeconds) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, DEFAULT_EXPIRE_DAYS);
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("iss", userId);
        //过期时间
        if(expSeconds == null) {
            expSeconds = (int)(cal.getTimeInMillis() / 1000);
        }
        claims.put("exp", expSeconds);
        return new JWTSigner(ISecurityConst.SSO_JWT_SECURITY_KEY).sign(claims);
    }

    /**
     * 验证token是否合法，如果合法的话返回验证结果
     * @param token
     * @return 如果合法的话返回：[iss: 用户id, exp: 过期时间]，反之为Null
     */
    public static Map<String, Object> verify(String token) {
        try {
            JWTVerifier jwtVerifier = new JWTVerifier(ISecurityConst.SSO_JWT_SECURITY_KEY);
            return jwtVerifier.verify(token);
        } catch (Exception e) {
            logger.error("认证头【"+ token +"】验证不合法", e);
        }
        return null;
    }

    /**
     * 验证jwt token并返回登录的用户id
     * @param token
     * @return
     */
    public static String verifyAndReturnUserId(String token) {
        try {
            JWTVerifier jwtVerifier = new JWTVerifier(ISecurityConst.SSO_JWT_SECURITY_KEY);
            Map<String, Object> result = jwtVerifier.verify(token);
            return result != null? MapUtils.getString(result, "iss") : null;
        } catch (Exception e) {
            logger.error("认证头【"+ token +"】验证不合法", e);
        }
        return null;
    }
}
