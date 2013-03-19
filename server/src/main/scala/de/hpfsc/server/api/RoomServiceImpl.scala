package de.hpfsc.server.api

import _root_.com.google.gwt.user.server.rpc.RemoteServiceServlet
import _root_.java.util
import _root_.org.springframework.security.core.context.SecurityContextHolder
import com.weiglewilczek.slf4s.Logging
import de.hpfsc.web.RoomService
import de.hpfsc.shared.DTO.ChatEntry
import de.hpfsc.shared.FieldVerifier
import net.liftweb.json._
import scala.collection.JavaConversions._
import de.hpfsc.server.persistence.DatabaseEndpoint

/**
 * Implementation of room service REST API.
 */
class RoomServiceImpl extends RemoteServiceServlet with RoomService with Logging {
  implicit val formats = Serialization.formats(NoTypeHints)

  @Override
  def post(postText: String) {
    if (!FieldVerifier.isValidPost(postText)) {
      return
    }

    val author = SecurityContextHolder.getContext.getAuthentication.getPrincipal.toString

    DatabaseEndpoint.storeNewChatEntry(author, postText)
  }

  @Override
  def get(roomId: Int): util.List[ChatEntry] = DatabaseEndpoint.getChatEntries()
}

