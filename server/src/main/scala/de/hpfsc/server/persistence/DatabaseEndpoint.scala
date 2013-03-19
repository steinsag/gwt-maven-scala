package de.hpfsc.server.persistence

import _root_.java.util.{Date, UUID}
import _root_.org.apache.commons.io.IOUtils
import _root_.org.apache.http.client.methods.{HttpGet, HttpPut}
import _root_.org.apache.http.client.utils.URIBuilder
import _root_.org.apache.http.entity.{ContentType, StringEntity}
import _root_.org.apache.http.HttpEntity
import _root_.org.apache.http.impl.client.DefaultHttpClient
import com.weiglewilczek.slf4s.Logging
import net.liftweb.json._
import net.liftweb.json.Serialization.write
import de.hpfsc.shared.DTO.ChatEntry

/**
 *
 */
object DatabaseEndpoint extends Logging {
  private implicit val formats = Serialization.formats(NoTypeHints)

  private val httpClient = new DefaultHttpClient()

  private def getProtocol = "http"

  private def getHost = "localhost"

  private def getPort = 5984

  private def getDatabase = "chatapplication"

  private def getBaseUrl = getProtocol + "://" + getHost + ":" + getPort + "/"

  private def getEndpoint: String = getBaseUrl + getDatabase + "/"

  private def getEndpoint(path: String): String = getEndpoint + path + "/"

  /**
   * Store chat entry in database.
   *
   * @param author Author name of chat entry.
   * @param text Text of the chat entry.
   */
  def storeNewChatEntry(author: String, text: String) {
    val created = new Date
    val id = UUID.randomUUID()

    val chatEntryEntity = new StringEntity(write(new ChatEntryData(author, text, created)), ContentType.APPLICATION_JSON)

    val putChatEntry = new HttpPut(getEndpoint(id.toString))
    putChatEntry.setEntity(chatEntryEntity)

    val response = httpClient.execute(putChatEntry)
    try {
      response.getStatusLine.getStatusCode match {
        case 201 => logger.info("Created new chat entry with ID " + id + " by author " + author + " saying: " + text)
        case code => logger.debug("Failed creating new chat entry with ID " + id + " by author " + author + " saying: " + text)
      }
    } finally {
      putChatEntry.releaseConnection()
    }
  }

  def getChatEntries(): List[ChatEntry] = {
    logger.info("Retrieving last 100 chat entries")

    val builder = new URIBuilder(getEndpoint("_all_docs"))
    builder.setParameter("limit", "100")
    builder.setParameter("include_docs", "true")

    val getDocs = new HttpGet(builder.build())

    val response = httpClient.execute(getDocs)

    val chatEntries: List[ChatEntry] = List()
    try {
      val entity: HttpEntity = response.getEntity
      val json = IOUtils.toString(entity.getContent, entity.getContentEncoding.toString)

      //      response.getStatusLine.getStatusCode match {
      //        case 201 => logger.info("Got documents")
      //        case code => logger.debug("Problem getting docs")
      //      }
    } finally {
      getDocs.releaseConnection()
    }

    chatEntries
  }

  /**
   * Ensures database is accessible (e.g. creating new database if needed).
   *
   * @return true when database is ready to use, otherwise false
   */
  def ensureDatabaseExists(): Boolean = {
    logger.info("Checking if database exists already.")

    val putDb = new HttpPut(getEndpoint)
    val responsePut = httpClient.execute(putDb)

    try {
      responsePut.getStatusLine.getStatusCode match {
        case 201 => logger.info("New database created."); true
        case 412 => logger.info("Database already exists. Doing nothing."); true
        case code: Int => logger.error("Unexpected response while looking for database: " + code); false
      }
    } finally {
      putDb.releaseConnection()
    }
  }
}

/**
 * Helper class so that we can create JSON for chat entry easily.
 */
private case class ChatEntryData(author: String, text: String, created: Date);
