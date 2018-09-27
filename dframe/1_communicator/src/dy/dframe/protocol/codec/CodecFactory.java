// FrontEnd Plus GUI for JAD
// DeCompiled : CodecFactory.class

package dy.dframe.protocol.codec;


// Referenced classes of package dy.dframe.protocol.codec:
//            Codec

public interface CodecFactory
{

    public abstract Codec createCodec();
    
    public DeCoder createDeCoder();
    
    public EnCoder createEnCoder();
}
