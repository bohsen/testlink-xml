package controllers

import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

class Transformer : Controller() {
    val statusProperty = SimpleStringProperty("")
    var status by statusProperty

    fun transform(xml: File, xslt: File, result: StreamResult) {
        runLater { status = "" }

        // create the DOM Source
        val factory = DocumentBuilderFactory.newInstance()
        factory.isNamespaceAware = true
        val builder = factory.newDocumentBuilder()
        val bbcDoc = builder.parse(xml)
        val source = DOMSource(bbcDoc)

        // Create an instance of the TransformerFactory
        val transfomerFactory = TransformerFactory.newInstance()
        val transformer = transfomerFactory.newTransformer(StreamSource(xslt))
                .apply {
                    setOutputProperty(OutputKeys.INDENT, "yes")
                    setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4")
                }
        runAsync {
            try {
                transformer.transform(source, result)
            } catch (e: Exception) {

            }
        }
    }
}