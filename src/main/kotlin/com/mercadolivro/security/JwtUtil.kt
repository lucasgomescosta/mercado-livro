package com.mercadolivro.security

import com.mercadolivro.exception.AuthenticationException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import kotlin.math.exp

@Component
class JwtUtil {
    @Value("\${jwt.expiration}")
    private val expiration: Long? = null

    @Value("\${jwt.secret}")
    private val secret: String? = null

    fun generateToken(id: Int): String {
        return Jwts.builder()
                .setSubject(id.toString())
                .setExpiration(Date(System.currentTimeMillis() + expiration!!))
                .signWith(SignatureAlgorithm.ES512, secret!!.toByteArray())
                .compact()
    }

    fun isValidToken(token: String): Boolean {
        var clains = getClains(token)
        if(clains.subject == null || clains.expiration == null || Date().after(clains.expiration)) {
            return false
        }
        return true
    }

    private fun getClains(token: String): Claims {
        try {
            return Jwts.parser().setSigningKey(secret!!.toByteArray()).parseClaimsJws(token).body
        } catch (ex: Exception) {
            throw AuthenticationException("Token inválido!", "999")
        }
    }

    fun getSubject(token: String): String {
        return getClains(token).subject
    }
}