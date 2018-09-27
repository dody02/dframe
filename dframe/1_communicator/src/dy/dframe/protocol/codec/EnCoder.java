// FrontEnd Plus GUI for JAD
// DeCompiled : EnCoder.class

package dy.dframe.protocol.codec;

import dy.dframe.protocol.message.ICommMessage;
import java.nio.ByteBuffer;

public interface EnCoder
{

    public abstract byte[] doEnCoder(ICommMessage icommmessage)
        throws Exception;

    public abstract String doEnCoder4String(ICommMessage icommmessage, String s)
        throws Exception;

    public abstract ByteBuffer doBufferEnCoder(ICommMessage icommmessage)
        throws Exception;
}
