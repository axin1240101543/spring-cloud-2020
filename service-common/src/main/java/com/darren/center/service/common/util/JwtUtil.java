package com.darren.center.service.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <h3>spring-cloud-2020</h3>
 * <p>JWT工具类</p>
 * 两个原因：
 *
 * 1. 由于token，需要和用户做对应，会增加服务端存储负担。所以有了无状态的jwt。
 * 2. 集群中要进行session共享。需要将session放到一个公共地方去，比如db。如果db挂了。咋整。
 *
 * JWT是一种基于JSON的令牌安全验证(在某些特定的场合可以替代Session或者Cookie)
 *
 * 1.头部信息(header)
 * 	作用:指定该JWT使用的签名
 *        {
 *     	“alg”: “HS256”,// 签名算法
 *     	“typ”: “JWT” //token类型
 *    }
 * 	 将上面的json，用Base64URL 算法转成字符串，即为header。
 * 2.消息体，也就是负载，java中用Claims(playload)
 * {
 * "iss" (issuer)：签发人
 * "exp" (expiration time)：过期时间
 * "sub" (subject)：主题,一般用用户id
 * "aud" (audience)：受众
 * "nbf" (Not Before)：生效时间
 * "iat" (Issued At)：签发时间
 * "jti" (JWT ID)：编号
 * }
 * 这个 JSON 对象也要使用 Base64URL 算法转成字符串。
 * 	作用:JWT的请求数据，
 * 3.签名( signature)
 * Signature 部分是对前两部分的签名，防止数据篡改。
 * 需要指定一个密钥（secret）。这个密钥只有服务器才知道，不能泄露给用户。然后，使用 Header 里面指定的签名算法（默认是 HMAC SHA256），按照下面的公式产生签名。
 * HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload),secret)
 *
 * 最终：把 Header、Payload、Signature 三个部分拼成一个字符串，每个部分之间用"点"（.）分隔，就可以返回给用户。
 * header.payload.signature
 *
 * 一般将jwt值放在：header中的Authorization中。设备码（或者ip，有时候我们换个wifi，银行客户端都提示网络环境改变，需要重新登录）也放header中。智远一户通。
 *
 * 主题中，用户id和设备唯一码，防止token被别人拿走用。这样设备码不一样。
 *
 * https保证设备外，网络中安全；设备码保证设备中安全，如果你把设备给别人了，那没办法了。
 *
 * @author : Darren
 * @date : 2020年08月26日 15:48:57
 **/
public class JwtUtil {

    private static String SECRET = "ABC123";

    /**
     * 创建token
     * @param subject 主题,一般用用户id
     * @param issueDate 签发时间
     * @return
     */
    public static String createToken(String subject, Date issueDate){
        String compactJws = Jwts.builder()
                //主题,一般用用户id
                .setSubject(subject)
                //签发时间
                .setIssuedAt(issueDate)
                //过期时间 10秒
                .setExpiration(new Date(System.currentTimeMillis() + 10000))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return compactJws;
    }

    public static String parseToken(String token){
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            if (claims != null) {
                return claims.getSubject();
            }
        }catch (ExpiredJwtException e){
            e.printStackTrace();
            System.out.println("jwt过期了");
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        String subject = "1";
        String token = createToken(subject, new Date());
        System.out.println("token:" + token);
        TimeUnit.MILLISECONDS.sleep(100);
        //TimeUnit.MILLISECONDS.sleep(10001);
        System.out.println("parse token:" + parseToken(token));
    }

}
