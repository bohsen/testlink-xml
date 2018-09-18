package models

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class TransformerModel : ViewModel(){
    val xml = bind { SimpleStringProperty() }
    val xslt = bind { SimpleStringProperty() }
}