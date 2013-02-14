package de.hpfsc.server.security

import _root_.org.springframework.security.core.authority.SimpleGrantedAuthority
import _root_.org.springframework.security.core.{ GrantedAuthority, Authentication }
import collection.JavaConversions._

/**
 * Simple user authentication.
 */
class CustomUserAuthentication(role: String, authentication: Authentication, var authenticated: Boolean = false) extends Authentication {

  def getAuthorities: java.util.Collection[_ <: GrantedAuthority] = Set(new SimpleGrantedAuthority(role))

  def getCredentials: AnyRef = authentication.getCredentials

  def getDetails: AnyRef = authentication.getDetails

  def getPrincipal: AnyRef = authentication.getPrincipal

  def isAuthenticated: Boolean = authenticated

  def setAuthenticated(isAuthenticated: Boolean) {
    authenticated = isAuthenticated
  }

  def getName: String = classOf[CustomUserAuthentication].toString
}
