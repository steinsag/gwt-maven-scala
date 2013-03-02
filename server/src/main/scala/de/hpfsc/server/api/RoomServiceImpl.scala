package de.hpfsc.server.api

import _root_.com.google.gwt.user.server.rpc.RemoteServiceServlet
import _root_.java.util
import de.hpfsc.web.RoomService
import de.hpfsc.shared.DTO.{ ChatEntry, RoomDetails, RoomContent }
import collection.mutable
import _root_.java.util.Date
import de.hpfsc.shared.FieldVerifier
import de.hpfsc.web.exceptions.RoomNotFoundException

private object RoomServiceImpl {
  val rooms = mutable.ArrayBuffer[RoomContent]()
  addRoom("default room")

  def addRoom(title: String): RoomContent = {
    val chatEntries = new util.ArrayList[ChatEntry]
    val roomDetails = new RoomDetails(rooms.length, title)
    val newRoom: RoomContent = new RoomContent(roomDetails, chatEntries)

    rooms += newRoom

    newRoom
  }
}

/**
 * Implementation of room service REST API.
 */
class RoomServiceImpl extends RemoteServiceServlet with RoomService {
  @Override
  def get(roomId: Int): RoomContent = {
    try {
      RoomServiceImpl.rooms(roomId)
    } catch {
      case ex: IndexOutOfBoundsException ⇒ throw new RoomNotFoundException
    }
  }

  @Override
  def post(roomId: Int, postText: String) {
    if (!FieldVerifier.isValidPost(postText)) {
      return
    }

    val room: RoomContent = get(roomId)
    //    val author: String = SecurityContextHolder.getContext.getAuthentication.getPrincipal.toString
    val author = "author"
    val chatEntries: util.Collection[ChatEntry] = room.getChatEntries
    val chatEntry: ChatEntry = new ChatEntry(author, postText, new Date())

    if (!chatEntries.add(chatEntry)) {
      throw new Exception
    }
  }
}

