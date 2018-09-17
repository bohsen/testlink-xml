package controllers

import javafx.beans.property.SimpleStringProperty
import tornadofx.Controller
import tornadofx.runLater
import views.MainScreen
import views.TransformerScreen
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource


class Transformer : Controller() {
    private var statusProperty = SimpleStringProperty("")
    var status by statusProperty

    fun transform(xml: File, xslt: File) {
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
        // An object to hold the results. It can be a file. In This example we
        // output to console.
        val result = StreamResult(System.out)
        runLater {
            try {
                transformer.transform(source, result)
                find(MainScreen::class).replaceWith(TransformerScreen::class, sizeToScene = true, centerOnScreen = true)
            } catch (e:Exception) {
                status = e.message
            }
        }
    }
}