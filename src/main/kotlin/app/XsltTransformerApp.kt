package app

import tornadofx.App
import tornadofx.launch
import views.MainScreen

class XsltTransformerApp : App(MainScreen::class)

fun main(args: Array<String>) {
    launch<XsltTransformerApp>(args)
}