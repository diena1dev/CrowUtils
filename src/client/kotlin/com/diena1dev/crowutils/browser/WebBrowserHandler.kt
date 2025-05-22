package com.diena1dev.crowutils.browser

import com.cinemamod.mcef.MCEF
import com.cinemamod.mcef.MCEFBrowser
import com.diena1dev.crowutils.client.RenderHandler
import com.diena1dev.crowutils.client.gameInstance
import com.diena1dev.crowutils.config.Config

@Suppress("unused")
object WebBrowserHandler {
    lateinit var webBrowser: MCEFBrowser
    var c: Config = Config

    fun init() {
        if (!this::webBrowser.isInitialized && MCEF.isInitialized()) {
            webBrowser = MCEF.createBrowser(Config.webHomePage, true)
        }
    }

    fun refreshBrowser() {
        if (this::webBrowser.isInitialized) {
            c.hasInjectedCSS = false
            webBrowser.reload()
            println("refreshBrowser called, value ${c.hasInjectedCSS}")
        }
    }

    fun resizeBrowserAuto() { // resizes intelligently to match window resolution
        if (this::webBrowser.isInitialized) {
            webBrowser.resize(
                gameInstance.window.width
                ,
                gameInstance.window.height
            )
            }
    }

    fun resizeBrowserPrecise(x: Int, y: Int) { // resizes with the **exact** given x and y
        if (this::webBrowser.isInitialized) { webBrowser.resize(x, y) }
    }

    fun isBrowserInit(): Boolean {
        var result: Boolean

        if (this::webBrowser.isInitialized) {
            result = true
        } else {
            result = false
        }

        return result
    }

    fun onJumpClick() {
        webBrowser.executeJavaScript("console.log(document.querySelector(\".coord-control-value\").textContent + \" logPointer\");",
            c.webHomePage, 500)
        val process = ProcessBuilder("tail", "-f", "../logs/latest.log").start()
        process.inputStream.bufferedReader().useLines { lines ->
            lines.forEach { line ->
                if (line.contains("logPointer")) {
                    val coords = line.substringAfter("RightClickEvent: ").split(" ")
                    val x = coords[0].substringAfter("X=")
                    val y = coords[1].substringAfter("Y=")
                    println("Extracted Coordinates: X=$x, Y=$y")
                }
            }
        }


        fun injectCSS() {
        println("injectCSS called, value ${c.hasInjectedCSS}")
        if (!c.hasInjectedCSS && isBrowserInit()) {
            c.hasInjectedCSS = true
            webBrowser.executeJavaScript(
                "const injectCSS = css => {" +
                        "  let el = document.createElement('style');" +
                        "  el.type = 'text/css';" +
                        "  el.innerText = css;" +
                        "  document.head.appendChild(el);" +
                        "  return el;" +
                        "};" +
                        "injectCSS('/* By diena1dev (Crow) */" +
                        "" +
                        "/* Changes scale of and moves World icons */" +
                        ".dynmap .sublist .item {" +
                        "    display: block;" +
                        "    float: right;" +
                        "" +
                        "scale:95%;" +
                        "" +
                        "    height: 18px;" +
                        "    width: 18px;" +
                        "" +
                        "    padding: 2px;" +
                        "    margin: -20px 3px 1px 1px;" +
                        "" +
                        "    border-radius: 1px;" +
                        "    -moz-border-radius: 1px;" +
                        "" +
                        "    background: rgba(32,32,32,0.6);" +
                        "    border: 1px solid rgba(64,64,64,0.6);" +
                        "}" +
                        "" +
                        "/* Removes the bar across the list */" +
                        ".dynmap .panel .subsection {" +
                        "    display: block;" +
                        "    clear: both;" +
                        "" +
                        "    width: 100%;" +
                        "    line-height: 18px;" +
                        "    margin: 0 0 7px 0;" +
                        "" +
                        "    border-bottom: 1px solid rgba(0,0,0,0);" +
                        "" +
                        "}" +
                        "" +
                        "/* This makes the selected World easy to see */" +
                        ".dynmap .sublist .item.selected {" +
                        "    background: rgba(128,128,128,0.5);" +
                        "    border: 1px solid rgba(255,255,255,255);" +
                        "}" +
                        "" +
                        "/* This disables the Compass */" +
                        ".compass, .compass_NE, .compass_SE, .compass_NW, .compass_SW {" +
                        "    display: block;" +
                        "    position: absolute;" +
                        "    z-index: 10;" +
                        "    top: 0px;" +
                        "    right: 0px;" +
                        "    height: 0px;" +
                        "    width: 0px;" +
                        "    background-repeat: no-repeat;" +
                        "}');", webBrowser.url, 1
            )
        }
    }
}}

/**
 * <!DOCTYPE html>
 * <html lang="en">
 * <head>
 *     <meta charset="UTF-8">
 *     <meta name="viewport" content="width=device-width, initial-scale=1.0">
 *     <title>Right Click Copy Button</title>
 *     <style>
 *         body {
 *             font-family: Arial, sans-serif;
 *         }
 *         #copyButton {
 *             position: absolute;
 *             display: none;
 *             background-color: #007bff;
 *             color: white;
 *             border: none;
 *             padding: 5px 10px;
 *             cursor: pointer;
 *             border-radius: 5px;
 *         }
 *         #targetText {
 *             margin-top: 50px;
 *             padding: 10px;
 *             border: 1px solid #ccc;
 *         }
 *     </style>
 * </head>
 * <body>
 *     <p id="targetText">This is the text to copy!</p>
 *     <button id="copyButton">Copy</button>
 *
 *     <script>
 *         document.addEventListener("contextmenu", function(event) {
 *             event.preventDefault(); // Prevent default context menu
 *
 *             let button = document.getElementById("copyButton");
 *             button.style.left = event.pageX + "px";
 *             button.style.top = event.pageY + "px";
 *             button.style.display = "block";
 *         });
 *
 *         document.getElementById("copyButton").addEventListener("click", function() {
 *             let text = document.getElementById("targetText").textContent;
 *             navigator.clipboard.writeText(text).then(() => {
 *                 alert("Text copied to clipboard!");
 *             }).catch(err => {
 *                 console.error("Failed to copy text: ", err);
 *             });
 *
 *             this.style.display = "none"; // Hide button after copying
 *         });
 *
 *         // Hide button on click anywhere else
 *         document.addEventListener("click", function(event) {
 *             let button = document.getElementById("copyButton");
 *             if (event.target !== button) {
 *                 button.style.display = "none";
 *             }
 *         });
 *     </script>
 * </body>
 * </html>
  */
