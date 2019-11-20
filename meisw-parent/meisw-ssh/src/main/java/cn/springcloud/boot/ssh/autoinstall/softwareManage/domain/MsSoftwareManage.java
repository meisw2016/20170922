package cn.springcloud.boot.ssh.autoinstall.softwareManage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @项目名称: npam-disposal模块
 * @类名称: MsSoftwareManage
 * @类描述: #数据实体类
 * @功能描述: 
 * @创建人: Sunthiny
 * @创建时间: 2019-03-12 22:24:21
 * @修改备注: 
 * @修改记录: 修改时间    修改人员    修改原因
 * -------------------------------------------------------------
 * @version 1.0.0
 * @Copyright (c) 2017宇信科技-版权所有
 */
@Entity
@Table(name = "ms_software_manage")
public class MsSoftwareManage{
	private static final long serialVersionUID = 1L;
	
	/** SM_HOST **/
	@Id
	@Column(name = "SM_HOST")
	private String smHost;
	/** SM_SOFTWARE **/
	@Id
	@Column(name = "SM_SOFTWARE")
	private String smSoftware;
	
	/** SM_VERSION **/
	@Column(name = "SM_VERSION", unique = false, nullable = true, length = 50)
	private String smVersion;
	
	/** SM_PATH **/
	@Column(name = "SM_PATH", unique = false, nullable = true, length = 200)
	private String smPath;
	
	/** SM_OS **/
	@Column(name = "SM_OS", unique = false, nullable = true, length = 100)
	private String smOs;
	
	/** SM_ENV **/
	@Column(name = "SM_ENV", unique = false, nullable = true, length = 50)
	private String smEnv;
	
	
	/**
	 * @param smHost
	 */
	public void setSmHost(String smHost) {
		this.smHost = smHost == null ? null : smHost.trim();
	}
	
    /**
     * @return SmHost
     */	
	public String getSmHost() {
		return this.smHost;
	}
	
	/**
	 * @param smSoftware
	 */
	public void setSmSoftware(String smSoftware) {
		this.smSoftware = smSoftware == null ? null : smSoftware.trim();
	}
	
    /**
     * @return SmSoftware
     */	
	public String getSmSoftware() {
		return this.smSoftware;
	}
	
	/**
	 * @param smVersion
	 */
	public void setSmVersion(String smVersion) {
		this.smVersion = smVersion == null ? null : smVersion.trim();
	}
	
    /**
     * @return SmVersion
     */	
	public String getSmVersion() {
		return this.smVersion;
	}
	
	/**
	 * @param smPath
	 */
	public void setSmPath(String smPath) {
		this.smPath = smPath == null ? null : smPath.trim();
	}
	
    /**
     * @return SmPath
     */	
	public String getSmPath() {
		return this.smPath;
	}
	
	/**
	 * @param smOs
	 */
	public void setSmOs(String smOs) {
		this.smOs = smOs == null ? null : smOs.trim();
	}
	
    /**
     * @return SmOs
     */	
	public String getSmOs() {
		return this.smOs;
	}
	
	/**
	 * @param smEnv
	 */
	public void setSmEnv(String smEnv) {
		this.smEnv = smEnv == null ? null : smEnv.trim();
	}
	
    /**
     * @return SmEnv
     */	
	public String getSmEnv() {
		return this.smEnv;
	}


    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MsSoftwareManage other = (MsSoftwareManage) that;
		return (this.getSmHost() == null ? other.getSmHost() == null : this.getSmHost().equals(other.getSmHost()))
        	&& (this.getSmSoftware() == null ? other.getSmSoftware() == null : this.getSmSoftware().equals(other.getSmSoftware()))
        	&& (this.getSmVersion() == null ? other.getSmVersion() == null : this.getSmVersion().equals(other.getSmVersion()))
        	&& (this.getSmPath() == null ? other.getSmPath() == null : this.getSmPath().equals(other.getSmPath()))
        	&& (this.getSmOs() == null ? other.getSmOs() == null : this.getSmOs().equals(other.getSmOs()))
        	&& (this.getSmEnv() == null ? other.getSmEnv() == null : this.getSmEnv().equals(other.getSmEnv()))
        ;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSmHost() == null) ? 0 : getSmHost().hashCode());
        result = prime * result + ((getSmSoftware() == null) ? 0 : getSmSoftware().hashCode());
        result = prime * result + ((getSmVersion() == null) ? 0 : getSmVersion().hashCode());
        result = prime * result + ((getSmPath() == null) ? 0 : getSmPath().hashCode());
        result = prime * result + ((getSmOs() == null) ? 0 : getSmOs().hashCode());
        result = prime * result + ((getSmEnv() == null) ? 0 : getSmEnv().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("[");
        sb.append("Hash = ").append(hashCode());
        sb.append("]");
        return sb.toString();
    }
}