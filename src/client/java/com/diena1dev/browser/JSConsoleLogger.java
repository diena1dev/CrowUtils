package com.diena1dev.browser;

import org.cef.handler.CefDisplayHandler;
/*
public class JSConsoleLogger {
    @Override
    public boolean handleQuery(IBrowser b, long queryId, String query, boolean persistent, IJSQueryCallback cb) {
        if(query.startsWith("console:")) {
            String data = query.substring("console:".length());
            int idx = data.indexOf(':');

            if(idx != -1) {
                String level = data.substring(0, idx);
                String message = data.substring(idx + 1);

                switch(level) {
                    case "log":
                    case "info":
                    case "debug":
                        System.out.println("[JS Console/" + level + "] " + message);
                        break;
                    case "warn":
                        System.out.println("[JS Console/WARN] " + message);
                        break;
                    case "error":
                        System.err.println("[JS Console/ERROR] " + message);
                        break;
                    default:
                        System.out.println("[JS Console] " + message);
                }

                cb.success("ok");
                return true;
            } else {
                cb.failure(400, "Invalid console format");
                return true;
            }
        }

        return false;
    }

webBrowser.executeJavaScript(
        "(function() {" +
        "  const levels = ['log', 'warn', 'error', 'info', 'debug'];" +
        "  levels.forEach(function(level) {" +
        "    const original = console[level];" +
        "    console[level] = function(...args) {" +
        "      original.apply(console, args);" +
        "      try {" +
        "        window.mcefQuery({" +
        "          request: 'console:' + level + ':' + args.map(a => " +
        "            (typeof a === 'object' ? JSON.stringify(a) : String(a))" +
        "          ).join(' ')," +
        "          persistent: false," +
        "          onSuccess: function() {}," +
        "          onFailure: function() {}" +
        "        });" +
        "      } catch (e) {}" +
        "    };" +
        "  });" +
        "})();",
        webBrowser.getURL(),
        0
        );
}
*/