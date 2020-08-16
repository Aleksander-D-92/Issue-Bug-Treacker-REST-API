package com.secure.secure_back_end.configuration.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider
{
    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "authorities";
    private static final String USER_ID = "user_id";

    private final String secretKey = "secretkey";

    private final long tokenValidityInMilliseconds = 50_100_100;

    private final long tokenValidityInMillisecondsForRememberMe = 100_100_100;

    //Create the token from Authentication object
    public String createToken(Authentication authentication, Boolean rememberMe)
    {
        //turn GrantedAuthority to claims
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        //create expiration date if (rememberMe)
        long now = (new Date()).getTime();
        Date validity;
        if (rememberMe)
        {
            validity = new Date(now + this.tokenValidityInMillisecondsForRememberMe);
        } else
        {
            validity = new Date(now + this.tokenValidityInMilliseconds);
        }
        //set username, claims, setExpiration date, sign it with secret
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(validity)
                .compact();
    }

    //Parse token claims
    public Authentication getAuthentication(String token)
    {
        //Get all the claims
        Claims claims = null;
        if (validateToken(token))
        {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } else
        {
            return new UsernamePasswordAuthenticationToken(null, null, null); // invalid user
        }

        //Get the GrantedAuthorities from the claims
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        //Create an object to put in SpringSecurityContextHolder
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String authToken)
    {
        try
        {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e)
        {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e)
        {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e)
        {
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e)
        {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e)
        {

            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }
}
