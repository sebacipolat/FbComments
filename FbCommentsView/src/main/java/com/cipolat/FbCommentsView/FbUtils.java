package com.cipolat.FbCommentsView;

/**
 * Created by sebastian on 08/03/18.
 */

public class FbUtils {

    public static String buildHtml(FbConfig config) {

        String html = "<!doctype html> <html lang=\"en\"> <head>" +
                "<meta property=\"fb:app_id\" content=\"" + config.getAppID() + "\">" +
                "</head> <body> " +
                "<div id=\"fb-root\"></div>" +
                "<script>(function(d, s, id) {" +
                "var js, fjs = d.getElementsByTagName(s)[0];" +
                "if (d.getElementById(id)) return;" +
                "js = d.createElement(s); js.id = id;" +
                "js.src = 'https://connect.facebook.net/es_LA/sdk.js#xfbml=1&version=v2.12&appId=" + config.getAppID() + "';" +
                "fjs.parentNode.insertBefore(js, fjs);" +
                "}(document, 'script', 'facebook-jssdk'));</script>" +
                "<div class=\"fb-comments\" data-href=\"" + config.getUrl() + "\" " +
                "data-numposts=\"" + FbConfig.MAX_COMMENTS + "\" data-order-by=\"reverse_time\">" +
                "</div> </body> </html>";

        return html;
    }
}
