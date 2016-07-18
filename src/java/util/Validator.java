/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Apollo
 */
public class Validator {
    public static boolean isInvalidParameter(String parameter){
        return parameter == null || parameter.equals("");
    }
    
    public static boolean isValidParameter(String parameter){
        return parameter != null && !parameter.equals("");
    }
    
    public static boolean isValidParameters(String... parameters){
        for(String parameter : parameters){
            if(isInvalidParameter(parameter)){
                return false;
            }
        }
        return true;
    }
}
