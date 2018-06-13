// This code covered by the Apache2 License: http://www.apache.org/licenses/LICENSE-2.0
// You are free to use it for your own good as long as it doesn't hurt anybody.
// For questions or suggestions please contact me at httpeter@gmail.com
package org.httpeter.util;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FMessage implements Serializable
{

    private static final Logger logger = Logger.getLogger(FMessage.class.getName());



    public final void warn(String msg)
    {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                        msg, null));
        logger.log(Level.WARNING, msg);
    }



    public final void error(String msg)
    {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        msg, null));
        logger.log(Level.WARNING, msg);
    }



    public final void fatal(String msg)
    {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        msg, null));
        logger.log(Level.WARNING, msg);
    }



    public final void info(String msg)
    {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        msg, null));
        logger.log(Level.INFO, msg);
    }

}
