package tech.b180.cordaptor.corda

import net.corda.core.contracts.ContractState
import net.corda.core.flows.FlowLogic
import kotlin.reflect.KClass

/**
 * Single access point for all descriptive information about CorDapps installed
 * on a particular Corda node that may be used when implementing Cordaptor API.
 *
 * Different modules implement this interface in a different way depending
 * on the nature of their interaction with the underlying node.
 */
interface CordaNodeCatalog {

  /** Contains descriptions of all available CorDapps */
  val cordapps: Collection<CordappInfo>
}

/**
 * Marker interface allowing decorating implementation of [CordaNodeCatalog] to locate
 * the underlying implementation.
 */
interface CordaNodeCatalogInner : CordaNodeCatalog

data class CordappInfo(
    val shortName: String,
    val flows: List<CordappFlowInfo>,
    val contractStates: List<CordappContractStateInfo>
)

data class CordappFlowInfo(
    val flowClass: KClass<out FlowLogic<Any>>,
    val flowResultClass: KClass<out Any>
)

data class CordappContractStateInfo(
    val stateClass: KClass<out ContractState>
)