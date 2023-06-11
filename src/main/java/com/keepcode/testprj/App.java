package com.keepcode.testprj;

import com.keepcode.testprj.onlinesim.service.OnlinesimDataService;
import com.keepcode.testprj.ui.UIApp;
import com.keepcode.testprj.ui.container.UIMain;

import java.awt.*;

public class App {

    public static void main(String[] args) {
//        LogManager.getLogManager().readConfiguration(App.class.getResourceAsStream("logger.properties"));
        Container container = new UIMain(new OnlinesimDataService()).getPanel();
        new UIApp(container).run();
    }

}
