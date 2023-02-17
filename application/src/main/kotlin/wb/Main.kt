package wb

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.stage.Stage
import wb.frontend.*


class Main : Application() {
    private val rootcanvas = Pane()
    private var root = BorderPane()
    private var backgroundFill = BackgroundFill(Color.WHITE, null, null)
    private var background = Background(backgroundFill)
    private var shapeTools = ShapeTools(rootcanvas)
    private var penTools = PenTools()
    private var textTools = TextTools(rootcanvas)
    private var pathTools = PathTools(rootcanvas)


    override fun start(stage: Stage) {
        stage.title = "WhiteBoard"
        stage.minWidth = 480.0
        stage.minHeight = 320.0

        pathTools = PathTools(rootcanvas)
        root.top = TopMenu(::setBackgroundColour)
        root.left = ToolMenu(::setCursorType, pathTools.getPenTools())
        root.center = rootcanvas
        rootcanvas.background = background
        stage.scene = Scene(root, 800.0, 600.0)
        pathTools.setScale(stage.scene)
        shapeTools = ShapeTools(rootcanvas)
        shapeTools.setScale(stage.scene)

        stage.show()
    }

    private fun setCursorType(ctype: CursorType) {
        cursorType = ctype
        when (cursorType) {
            CursorType.cursor -> pathTools.cancelPath()
            CursorType.textbox -> textTools.createTextBox()
            CursorType.pen -> pathTools.initPath()
            CursorType.rectangle -> {
                pathTools.cancelPath()
                shapeTools.createRectangle()
            }

            CursorType.circle -> {
                pathTools.cancelPath()
                shapeTools.createCircle()
            }
            CursorType.eraser -> {
                pathTools.initPath()
                //pathTools.cancelPath()
                // shapeTools.createRectangle()
            };
        }
    }

    private fun setBackgroundColour(color: Color) {
        rootcanvas.background = Background(BackgroundFill(color, null, null))
    }
}

