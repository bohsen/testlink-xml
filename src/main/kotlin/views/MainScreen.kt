package views

import controllers.Transformer
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.control.TextField
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import javafx.stage.FileChooser
import tornadofx.*
import java.io.File

class MainScreen : View("XSLT Transformer") {

    val model = ViewModel()
    val xml = model.bind { SimpleStringProperty() }
    val xslt = model.bind { SimpleStringProperty() }

    private val xmlFilter = arrayOf(FileChooser.ExtensionFilter("XML Filer (*.xml)", "*.xml"))
    private val xsltFilter = arrayOf(FileChooser.ExtensionFilter("XSLT Filer (*.xslt)", "*.xslt"))

    private lateinit var xmlInput: TextField
    private lateinit var xsltInput: TextField

    val transformer: Transformer by inject()

    override val root = form {
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("XSLT fil") {
                hbox {
                    xsltInput  = textfield(xslt) {
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
                    xmlInput  = textfield(xml) {
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
                    runAsyncWithProgress {
                        transformer.transform(File(xmlInput.text), File(xsltInput.text))
                    }
                }
            }
        }
        label(transformer.status) {
            style {
                paddingTop = 10
                textFill = Color.RED
                fontWeight = FontWeight.BOLD
            }
        }
    }
}
