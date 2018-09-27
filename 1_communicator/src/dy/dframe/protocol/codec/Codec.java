// FrontEnd Plus GUI for JAD
// DeCompiled : Codec.class

package dy.dframe.protocol.codec;


// Referenced classes of package dy.dframe.protocol.codec:
//            EnCoder, DeCoder

public class Codec
{

    private EnCoder encoder;
    private DeCoder decoder;

    public Codec()
    {
    }

    public DeCoder getDecoder()
    {
        return decoder;
    }

    public EnCoder getEncoder()
    {
        return encoder;
    }

    public void setDecoder(DeCoder decoder)
    {
        this.decoder = decoder;
    }

    public void setEncoder(EnCoder encoder)
    {
        this.encoder = encoder;
    }
}
