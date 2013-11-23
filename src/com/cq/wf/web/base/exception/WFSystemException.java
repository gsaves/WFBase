package com.cq.wf.web.base.exception;

public class WFSystemException extends WFException {

	
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public WFSystemException(String msg,Object...args){
    	message = String.format(msg, args);
    }

}
