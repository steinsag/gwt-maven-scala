package de.hpfsc.server.persistence

import _root_.javax.servlet.{ServletContextEvent, ServletContextListener}

/**
 * Perform database init during container startup.
 */
class InitDatabase extends ServletContextListener {
  @Override
  def contextInitialized(p1: ServletContextEvent) {
    if (!DatabaseEndpoint.ensureDatabaseExists()) {
      throw new Exception("Error during startup while ensuring database exists.")
    }
  }

  @Override
  def contextDestroyed(p1: ServletContextEvent) {}
}
