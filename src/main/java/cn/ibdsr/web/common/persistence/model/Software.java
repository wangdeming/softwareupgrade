package cn.ibdsr.web.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 软件表
 * </p>
 *
 * @author Wujiayun
 * @since 2019-07-16
 */
public class Software extends Model<Software> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 项目Id
     */
	@TableField("project_id")
	private Long projectId;
    /**
     * 软件代号
     */
	private String code;
    /**
     * 名称
     */
	private String name;
    /**
     * 类型（关联dict表num）
     */
	private Integer type;
    /**
     * 说明
     */
	private String description;
    /**
     * 排序
     */
	private Integer sequence;
    /**
     * 最新安装包名称
     */
	@TableField("app_name")
	private String appName;
	private String filename;
    /**
     * 最新包名
     */
	@TableField("package_name")
	private String packageName;
    /**
     * 最新版本号
     */
	@TableField("version_no")
	private String versionNo;
    /**
     * 最新安装包大小
     */
	private Long size;
    /**
     * 最新安装包路径
     */
	private String url;
    /**
     * 是否强制：0-不强制；1-强制
     */
	@TableField("is_force")
	private Integer isForce;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private Date createdTime;
    /**
     * 修改时间
     */
	@TableField("modified_time")
	private Date modifiedTime;
    /**
     * 创建用户
     */
	@TableField("created_user")
	private Long createdUser;
    /**
     * 修改用户
     */
	@TableField("modified_user")
	private Long modifiedUser;
    /**
     * 是否删除：0-未删除；1-已删除
     */
	@TableField("is_deleted")
	private Integer isDeleted;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Software{" +
			"id=" + id +
			", projectId=" + projectId +
			", code=" + code +
			", name=" + name +
			", type=" + type +
			", description=" + description +
			", sequence=" + sequence +
			", appName=" + appName +
			", filename=" + filename +
			", packageName=" + packageName +
			", versionNo=" + versionNo +
			", size=" + size +
			", url=" + url +
			", isForce=" + isForce +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdUser=" + createdUser +
			", modifiedUser=" + modifiedUser +
			", isDeleted=" + isDeleted +
			"}";
	}
}
