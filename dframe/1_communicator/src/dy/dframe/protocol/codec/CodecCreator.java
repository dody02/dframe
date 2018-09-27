// FrontEnd Plus GUI for JAD
// DeCompiled : CodecCreator.class

package dy.dframe.protocol.codec;


// Referenced classes of package dy.dframe.protocol.codec:
//            CodecFactory, Codec

public class CodecCreator
{

    public CodecCreator()
    {
    }

    public Codec getCodec(CodecFactory factory)
    {
        return factory.createCodec();
    }
}
