package views

import controllers.Transformer
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import javafx.stage.FileChooser
import models.TransformerModel
import tornadofx.*
import java.io.File
import java.io.StringWriter
import javax.xml.transform.stream.StreamResult


class MainScreen : View("XSLT Transformer") {

    val model: TransformerModel by inject()

    private val xmlFilter = arrayOf(FileChooser.ExtensionFilter("XML Filer (*.xml)", "*.xml"))
    private val xsltFilter = arrayOf(FileChooser.ExtensionFilter("XSLT Filer (*.xslt)", "*.xslt"))

    private lateinit var xmlInput: TextField
    private lateinit var xsltInput: TextField

    val transformer: Transformer by inject()

    override val root = form {
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("XSLT fil") {
                hbox {
                    xsltInput  = textfield(model.xslt) {
                        required(message = "Dette felt er obligatorisk")
                    }
                    button("...") {
                        action {
                            val fn: List<File> = chooseFile("Vælg XSLT fil",xsltFilter, FileChooserMode.Single)
                            if (fn.isNotEmpty()) {
                                xsltInput.text = "${fn.first()}"
                            }
                        }
                    }
                }
            }
            field("XML Input Fil") {
                hbox {
                    xmlInput  = textfield(model.xml) {
                        required(message = "Dette felt er obligatorisk")
                    }
                    button("...") {
                        action {
                            val fn: List<File> = chooseFile("Vælg XML fil",xmlFilter, FileChooserMode.Single)
                            if (fn.isNotEmpty()) {
                                xmlInput.text = "${fn.first()}"
                            }
                        }
                    }
                }
            }
            button("Konverter") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    // An object to hold the results. It can be a file.
//                    val writer = System.out
                    val output = StreamResult(StringWriter())
                    runAsyncWithProgress {
                        try {
                            transformer.transform(File(xmlInput.text), File(xsltInput.text), output)
                        } catch (e: Exception) {
                            transformer.status = e.message
                        }
                    } ui {
                        showDialogResult(output)
                    }
                }
            }
        }
        label(transformer.statusProperty) {
            style {
                paddingTop = 10
                textFill = Color.RED
                fontWeight = FontWeight.BOLD
            }
        }
    }

    /**
     * Opens a dialog showing the result of the xml conversion
     */
    private fun showDialogResult(output: StreamResult) {
        dialog("Resultat:") {
            val model = ViewModel()
            val xml = model.bind { SimpleStringProperty() }

            field("XML") {
                textarea(xml) {
                    required()
                    text = output.writer.toString()
                    whenDocked { requestFocus() }
                }
            }
            buttonbar {
                button("Gem").action {
                    enableWhen(model.valid)
                    model.commit { doSave() }
                }
            }
        }
    }

    private fun doSave() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
