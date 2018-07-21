package com.pdm;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 13, 2017 9:52:58 PM
 */
public class Personneldatamanager {

    public static final Boolean PRODUCTION_MODE = Boolean.TRUE;
    
    public static void main(String [] args) {
        
        final AppLauncherImpl launcher = new AppLauncherImpl(PRODUCTION_MODE);
        
        launcher.launch(args);  
    }    
}
