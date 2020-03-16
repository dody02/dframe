package net.df.dframe.cluster;

/**
 * 构建集群实例 
 * @author dy02
 *
 */
public interface ClusterCreater {
	
	public ICluster getCluster(String url) throws Exception;
}
