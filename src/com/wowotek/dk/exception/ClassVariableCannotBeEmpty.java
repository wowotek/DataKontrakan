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
public class ClassVariableCannotBeEmpty extends Exception
{
    public ClassVariableCannotBeEmpty()
    {
        super();
    }

    public ClassVariableCannotBeEmpty(String message)
    {
        super(message);
    }

    public ClassVariableCannotBeEmpty(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ClassVariableCannotBeEmpty(Throwable cause)
    {
        super(cause);
    }
}
