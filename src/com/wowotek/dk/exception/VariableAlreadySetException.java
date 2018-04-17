/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wowotek.dk.exception;

/**
 *
 * @author wowotek
 */
public class VariableAlreadySetException extends Exception
{

    public VariableAlreadySetException()
    {
        super();
    }

    public VariableAlreadySetException(String message)
    {
        super(message);
    }

    public VariableAlreadySetException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public VariableAlreadySetException(Throwable cause)
    {
        super(cause);
    }
}
