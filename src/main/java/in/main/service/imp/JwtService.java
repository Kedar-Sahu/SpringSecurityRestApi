package in.main.service.imp;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private String secreteKey="";
	
	public String generate(String name) {
		Map<String,Object> map=new HashMap<>();
		map.put("name", name);
		
		String token=Jwts.builder()
		.claims()
		.add(map)
		.subject(name)
		.issuedAt(new Date(System.currentTimeMillis()))
		.expiration(new Date(System.currentTimeMillis()+60*60*10))
		.and()
		.signWith(getKey())
		.compact();
		
		return token;
	}

	private Key getKey() {
		try {
			KeyGenerator key=KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk=key.generateKey();
			secreteKey=Base64.getEncoder().encodeToString(sk.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] keys= Decoders.BASE64.decode(secreteKey);
		
		return Keys.hmacShaKeyFor(keys);
	}
}
