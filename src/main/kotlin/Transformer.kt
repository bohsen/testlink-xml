import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.StringWriter
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

class XmlToHtmlTransformer {
    fun transform(xmlPath: String) {
        val file = File(xmlPath)
        val xmlData = javaClass.classLoader.getResourceAsStream("xml/books.xml")
        val xsltURL = javaClass.classLoader.getResource("xslt/Xslt2Html.xsl")
        val xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlData)
        val stylesource = StreamSource(xsltURL.openStream(), xsltURL.toExternalForm())
        val transformer = TransformerFactory.newInstance().newTransformer(stylesource)
        val stringWriter = StringWriter()
        transformer.transform(DOMSource(xmlDocument), StreamResult(stringWriter))
        // write to file
        val outputFile = File("books.html")
        if (!outputFile.exists()) {
            outputFile.createNewFile()
        }
        val fw = FileWriter(outputFile)
        val bw = BufferedWriter(fw)
        bw.write(stringWriter.toString())
        bw.close()
    }
}