package fr.acinq.eclair.db

import fr.acinq.bitcoin.BinaryData

/**
  * This database stores the preimages that we have received from downstream
  * (either directly via UpdateFulfillHtlc or by extracting the value from the
  * blockchain).
  *
  * This means that this database is only used in the context of *relaying* payments.
  *
  * We need to be sure that if downstream is able to pulls funds from us, we can always
  * do the same from upstream, otherwise we lose money. Hence the need for persistence
  * to handle all corner cases.
  *
  */
trait PreimagesDb {

  def addPreimage(channelId: BinaryData, htlcId: Long, paymentPreimage: BinaryData)

  def removePreimage(channelId: BinaryData, htlcId: Long)

  def listPreimages(channelId: BinaryData): List[(BinaryData, Long, BinaryData)]

}
