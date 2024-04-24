package com.tju.elmcloud.filter;
import com.alibaba.fastjson.JSONObject;
import com.tju.elmcloud.constant.UserConstant;
import com.tju.elmcloud.manager.RedisManager;
import com.tju.elmcloud.utils.JWTProvider;
import com.tju.elmcloud.utils.StringUtil;
import com.tju.elmcloud.utils.StringUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
@Component
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private RedisManager redisManager;

    @Autowired
    private JWTProvider jwtProvider;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.prefix}")
    private String prefix;

    @Value("${allowed.paths}")
    private String paths;  

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String requestPath = request.getPath().toString();
        System.out.println(paths);
        System.out.println(requestPath);
        boolean allowedPath = false;
        if (paths != null && !paths.equals("")){
            allowedPath = requestPath.contains(paths);
        }
        if (allowedPath || StringUtils.isEmpty(requestPath)){
            return chain.filter(exchange);
        }
        
        // 验证token
        String authHeader = exchange.getRequest().getHeaders().getFirst(tokenHeader);
        if (authHeader != null && authHeader.startsWith(prefix)){
            String authToken = authHeader.substring(prefix.length());
            String userName = jwtProvider.getUserNameFromToken(authToken);

            // 查询redis
            Object token = redisManager.get(UserConstant.USER_TOKEN_KEY_REDIS + userName);
            if (token == null){
     
                return writeResponse(exchange.getResponse(),401,"token已过期...请重新登录");
            }
 
            String trimAuthToken = authToken.trim();
            if (! trimAuthToken.equals(token.toString())){
                return writeResponse(exchange.getResponse(),401,"token验证失败或已过期...请重新登录");
            }
        }else {
            return writeResponse(exchange.getResponse(),500,"token不存在");
        }
        return chain.filter(exchange);
    }

    /**
     * 值越小执行顺序越靠前
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 构建返回内容
     *
     * @param response ServerHttpResponse
     * @param code     返回码
     * @param msg     返回数据
     * @return Mono
     */
    protected Mono<Void> writeResponse(ServerHttpResponse response, Integer code, String msg) {
        JSONObject message = new JSONObject();
        message.put("code", code);
        message.put("msg", msg);
        byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}
