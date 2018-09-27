// FrontEnd Plus GUI for JAD
// DeCompiled : DeCoder.class

package dy.dframe.protocol.codec;

import java.nio.ByteBuffer;

public interface DeCoder
{

    public abstract Object doDeCoder(byte abyte0[])
        throws Exception;

    public abstract Object doDeCoder(String s, String s1)
        throws Exception;

    public abstract Object doDeCoder(ByteBuffer bytebuffer)
        throws Exception;
    
    public abstract Object doDeCoder(long id, byte[] data)
    throws Exception;
    
    public abstract Object doDeCoder(Object id, byte[] data)
    throws Exception;
    
}
