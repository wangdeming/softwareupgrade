package cn.ibdsr.web.modular.versionrecord.transfer;

import cn.ibdsr.core.base.dto.BaseDTO;
import cn.ibdsr.core.check.Verfication;
import cn.ibdsr.web.common.persistence.model.Software;
import java.util.Date;

/**
 * @Description 上传安装包DTO
 * @Version V1.0
 * @CreateDate 2019/4/26 17:00
 *
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/4/26      Wujiayun            类说明
 */
public class VersionRecordDTO extends BaseDTO {
	/**
	 * 软件Id
	 */
	@Verfication(name = "软件Id", notNull = true, forenign = Software.class)
	private Long softwareId;
	/**
	 * 安装包名称
	 */
	@Verfication(name = "安装包名称", notNull = true)
	private String appName;
	/**
	 * 包名
	 */
	@Verfication(name = "包名", notNull = true)
	private String packageName;
	/**
	 * 文件名
	 */
	@Verfication(name = "文件名", notNull = true)
	private String filename;
	/**
	 * 版本号
	 */
	@Verfication(name = "版本号", notNull = true)
	private String versionNo;
	/**
	 * 大小
	 */
	@Verfication(name = "大小", notNull = true, min = 1)
	private Long size;
	/**
	 * 安装包路径
	 */
	@Verfication(name = "安装包路径", notNull = true)
	private String url;
	/**
	 * 是否强制：0-不强制；1-强制
	 */
	@Verfication(name = "是否强制", notNull = true, min = 0, max = 1)
	private Integer isForce;
	/**
	 * 创建时间
	 */
	private Date createdTime;
	/**
	 * 修改时间
	 */
	private Date modifiedTime;
	/**
	 * 创建用户
	 */
	private Long createdUser;
	/**
	 * 修改用户
	 */
	private Long modifiedUser;
	/**
	 * 是否删除：0-未删除；1-已删除
	 */
	private Integer isDeleted;

	public Long getSoftwareId() {
		return softwareId;
	}

	public void setSoftwareId(Long softwareId) {
		this.softwareId = softwareId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getIsForce() {
		return isForce;
	}

	public void setIsForce(Integer isForce) {
		this.isForce = isForce;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Long getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(Long createdUser) {
		this.createdUser = createdUser;
	}

	public Long getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(Long modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
