package tech.b180.cordaptor.rpc

import net.corda.node.internal.cordapp.JarScanningCordappLoader
import org.koin.core.inject
import tech.b180.cordaptor.corda.CordaNodeCatalog
import tech.b180.cordaptor.corda.CordaNodeCatalogInner
import tech.b180.cordaptor.corda.CordappInfo
import tech.b180.cordaptor.corda.CordappInfoBuilder
import tech.b180.cordaptor.kernel.CordaptorComponent
import tech.b180.cordaptor.kernel.loggerFor

/**
 * Implementation of the [CordaNodeCatalog] interface that uses Corda RPC API
 * and local JAR file introspection to discover available CorDapps.
 */
class ClientNodeCatalogImpl : CordaNodeCatalogInner, CordaptorComponent {

  companion object {
    private val logger = loggerFor<ClientNodeCatalogImpl>()
  }

  private val settings: Settings by inject()

  override val cordapps: Collection<CordappInfo>

  init {
    val scanner = JarScanningCordappLoader.fromDirectories(
        cordappDirs = listOf(settings.cordappDir))

    cordapps = CordappInfoBuilder(cordapps = scanner.cordapps, isEmbedded = false).build()
  }
}