// FrontEnd Plus GUI for JAD
// DeCompiled : DefaultFactory.class

package dy.dframe.protocol.codec;


// Referenced classes of package dy.dframe.protocol.codec:
//            CodecFactory, Codec, EnCoder, DeCoder

public class DefaultFactory
    implements CodecFactory
{

    private EnCoder encoder;
    private DeCoder decoder;
    
    private Class encoderClazz;
    private Class decoderClazz;

    public DefaultFactory(EnCoder encoder, DeCoder decoder)
    {
        this.encoder = encoder;
        this.decoder = decoder;
    }
    
    public DefaultFactory (Class encoder, Class decoder){
    	this.encoderClazz = encoder;
    	this.decoderClazz = decoder;
    }
    /**
     * ֻ��һ��ʵ��
     */
    public Codec createCodec()
    {
        Codec codec = new Codec();
        codec.setDecoder(decoder);
        codec.setEncoder(encoder);
        return codec;
    }
    
    /**
     * ��ȡ��ʵ��
     */
	public DeCoder createDeCoder() {
		try {
		    return 	(DeCoder) decoderClazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
		}
	}
	
	/**
	 * ��ȡ��ʵ��
	 */
	public EnCoder createEnCoder() {
		try {
		    return 	(EnCoder) encoderClazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
		}
	}
}
