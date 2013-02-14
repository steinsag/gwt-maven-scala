package de.hpfsc.server.security

import _root_.org.springframework.security.core.Authentication
import _root_.org.springframework.security.authentication.{ UsernamePasswordAuthenticationToken, BadCredentialsException, AuthenticationProvider }

/**
 * Simple authentication provider with advanced security: It grants access if username and password are equal :-)
 */
class CustomAuthProvider extends AuthenticationProvider {
  def authenticate(authentication: Authentication): Authentication = {
    val username = authentication.getPrincipal.asInstanceOf[String]
    val password = authentication.getCredentials.asInstanceOf[String]

    if (username != password) throw new BadCredentialsException("username and password don't match!")

    // actually, this custom class below isn't needed, because we could simply do the following instead:
    //
    // new UsernamePasswordAuthenticationToken(username, password, Set(new SimpleGrantedAuthority("ROLE_USER")))
    new CustomUserAuthentication("ROLE_USER", authentication, true)
  }

  def supports(authentication: Class[_]): Boolean = classOf[UsernamePasswordAuthenticationToken].isAssignableFrom(authentication)
}
