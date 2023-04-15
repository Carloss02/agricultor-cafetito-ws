/*
 * Equipo de desarrollo de Ingenieria de Software
 * UMG 2023
 */
package ws.dto;

import java.util.Date;

/**
 *
 * @author carlo
 */

public class WsActiveDto {
    private String message; 
    private boolean status; 
    private Date lastChecked; 

    public WsActiveDto() {
    }

    public WsActiveDto(String message, boolean status, Date lastChecked) {
        this.message = message;
        this.status = status;
        this.lastChecked = lastChecked;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getLastChecked() {
        return lastChecked;
    }

    public void setLastChecked(Date lastChecked) {
        this.lastChecked = lastChecked;
    }
    
    
}

