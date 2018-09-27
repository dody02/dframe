package dy.dframe.protocol.message;

import java.util.Date;


/**
 * ͨ��GPS��Ϣ����
 * NMEA0183
 * @author Yu
 * @version 1.0
 *
 */
public class CommGpsMessage {
	
	/**
	 * ���Ȱ���
	 * @author Yu
	 *
	 */
	enum Hemisphere {
		
		/**
		 * �ϰ���
		 */
		South,
		/**
		 * ������
		 */
		North,
		/**
		 * ������
		 */
		East,
		/**
		 * ������
		 */
		West;
		
		/**
		 * ����
		 */
		public String toString(){
			if ( this == East )
				return "East";
			else if ( this == West )
				return "West";
			else if ( this == North )
				return "North";
			else 
				return "South";
		}
	}
	
	/**
	 * UTCʱ��:��ʽhhmmss ʱ����
	 */
	private String UtcTime;
	/**
	 * γ��
	 * ddmm.mmmm �ȷ� ԭʼЭ����ǰ����0Ҳ����
	 */
	private float Latitude;
	/**
	 * ����
	 * dddmm.mmmm �ȷ� ԭʼЭ����ǰ����0Ҳ����
	 */
	private float Longitude;
	
	/**
	 * GPS״̬��0=δ��λ��1=�ǲ�ֶ�λ��2=��ֶ�λ��6=���ڹ���
	 */
	private String GpsStatus;
	/**
	 * ����ʹ�õ������� (00-12) ԭʼЭ����ǰ���0Ҳ����
	 */
	private int sateNum;
	/**
	 * HDOPˮƽ�������ӣ�0.5-99.9��
	 */
	private float hdop;
	/**
	 * ���θ߶�
	 */
	private float altitude;
	/**
	 * ��������������ڴ��ˮ׼��ĸ߶�
	 */
	private float hight;
	/**
	 * ���ʱ�䣨�����һ�ν��յ��źŵ�ʱ�俪ʼ����������ǲ�ֶ�λ��Ϊ�գ�
	 */
	private long DifferentialTime;
	/**
	 * ���վID�� 0000-1023
	 */
	private String DifferenttialID;
	
	/**
	 * ���Ȱ���
	 */
	private Hemisphere LongHemisphere;
	/**
	 * γ�Ȱ���
	 */
	private Hemisphere LatHemisphere;
	/**
	 * GPSʱ��
	 */
	private Date dateTime ;
	/**
	 * GPSʱ�� long����
	 */
	private long longTime;
	/**
	 * �ٶ�
	 */
	private float speed;
	/**
	 * ����
	 */
	private float trackAngle;
	
	public float getAltitude() {
		return altitude;
	}
	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}
	public long getDifferentialTime() {
		return DifferentialTime;
	}
	public void setDifferentialTime(long differentialTime) {
		DifferentialTime = differentialTime;
	}
	public String getDifferenttialID() {
		return DifferenttialID;
	}
	public void setDifferenttialID(String differenttialID) {
		DifferenttialID = differenttialID;
	}
	public String getGpsStatus() {
		return GpsStatus;
	}
	public void setGpsStatus(String gpsStatus) {
		GpsStatus = gpsStatus;
	}
	public float getHdop() {
		return hdop;
	}
	public void setHdop(float hdop) {
		this.hdop = hdop;
	}
	public float getHight() {
		return hight;
	}
	public void setHight(float hight) {
		this.hight = hight;
	}
	public float getLatitude() {
		return Latitude;
	}
	public void setLatitude(float latitude) {
		Latitude = latitude;
	}
	public float getLongitude() {
		return Longitude;
	}
	public void setLongitude(float longitude) {
		Longitude = longitude;
	}
	public int getSateNum() {
		return sateNum;
	}
	public void setSateNum(int sateNum) {
		this.sateNum = sateNum;
	}
	public String getUtcTime() {
		return UtcTime;
	}
	public void setUtcTime(String utcTime) {
		UtcTime = utcTime;
	}
	public Hemisphere getLatHemisphere() {
		return LatHemisphere;
	}
	public void setLatHemisphere(Hemisphere latHemisphere) {
		LatHemisphere = latHemisphere;
	}
	public Hemisphere getLongHemisphere() {
		return LongHemisphere;
	}
	public void setLongHemisphere(Hemisphere longHemisphere) {
		LongHemisphere = longHemisphere;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public long getLongTime() {
		return longTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public void setLongTime(long longTime) {
		this.longTime = longTime;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public float getSpeed() {
		return speed;
	}
	public void setTrackAngle(float trackAngle) {
		this.trackAngle = trackAngle;
	}
	public float getTrackAngle() {
		return trackAngle;
	}
}
