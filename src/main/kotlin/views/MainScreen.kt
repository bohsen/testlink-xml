package views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
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
                        TODO("Create controller class that transforms the xml")
                    }
                }
            }
        }
//        label(loginController.statusProperty) {
//            style {
//                paddingTop = 10
//                textFill = Color.RED
//                fontWeight = FontWeight.BOLD
//            }
//        }
    }
}
