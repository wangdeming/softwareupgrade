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
 * 项目表
 * </p>
 *
 * @author Wujiayun
 * @since 2019-07-11
 */
public class Project extends Model<Project> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 项目代号
     */
	private String code;
    /**
     * 名称
     */
	private String name;
    /**
     * 简介
     */
	private String intro;
    /**
     * 软件数量
     */
	@TableField("software_num")
	private Integer softwareNum;
    /**
     * 排序
     */
	private Integer sequence;
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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getSoftwareNum() {
		return softwareNum;
	}

	public void setSoftwareNum(Integer softwareNum) {
		this.softwareNum = softwareNum;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
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
		return "Project{" +
			"id=" + id +
			", code=" + code +
			", name=" + name +
			", intro=" + intro +
			", softwareNum=" + softwareNum +
			", sequence=" + sequence +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdUser=" + createdUser +
			", modifiedUser=" + modifiedUser +
			", isDeleted=" + isDeleted +
			"}";
	}
}
