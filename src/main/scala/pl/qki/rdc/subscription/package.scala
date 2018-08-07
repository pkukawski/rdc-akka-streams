package pl.qki.rdc

package object subscription {
  case class Subscription(id: String, field: String, index: String, query: String, scanTable: String, interval: Long,
                          lastScan: Long, logTable: String, changedRows: List[String] = List())
}
