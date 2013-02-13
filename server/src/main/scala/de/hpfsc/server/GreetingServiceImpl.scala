package de.hpfsc.server

import org.apache.commons.lang3.StringEscapeUtils
import com.google.gwt.user.server.rpc.RemoteServiceServlet
import de.hpfsc.web.GreetingService
import de.hpfsc.shared.FieldVerifier

class GreetingServiceImpl extends RemoteServiceServlet with GreetingService {
  def greetServer(name: String): String = {
    // Verify that the input is valid.
    if (!FieldVerifier.isValidName(name)) {
      throw new IllegalArgumentException("Name must be at least 4 characters long")
    }

    val serverInfo = getServletContext.getServerInfo
    val userAgent = StringEscapeUtils.escapeHtml4(getThreadLocalRequest.getHeader("User-Agent"))
    val nameEscaped = StringEscapeUtils.escapeHtml4(name)

    "Hello, " + nameEscaped + "!<br><br>Scala is greeting you. I am running " + serverInfo + ".<br><br>It looks like you are using:<br>" + userAgent
  }
}
