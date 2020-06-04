package cn.ibdsr.web.modular.project.transfer;

import cn.ibdsr.core.base.dto.BaseDTO;
import cn.ibdsr.core.check.Verfication;
import cn.ibdsr.web.common.persistence.model.Project;
import cn.ibdsr.web.core.util.RegUtil;

import java.util.Date;

/**
 * @Description
 * @Version V1.0
 * @CreateDate 2019/7/11 11:41
 * .
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/11       Piht            类说明
 */
public class ProjectDTO extends BaseDTO {

    /**
     * 主键Id
     */
    private Long id;
    /**
     * 项目代号
     */
    @Verfication(name = "项目代号", notNull = true, unique="code", entity = Project.class, min = 0, maxlength= 20,
            regx = {RegUtil.PROJECT_CODE, "项目代号格式有误"})
    private String code;
    /**
     * 名称
     */
    @Verfication(name = "项目名称", notNull = true, unique="name", entity = Project.class, maxlength= 50)
    private String name;
    /**
     * 简介
     */
    @Verfication(name = "项目简介", maxlength = 120)
    private String intro;
    /**
     * 排序
     */
    @Verfication(name = "项目排序")
    private Integer sequence;
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


    @Override
    public Long getId() {
        return id;
    }

    @Override
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
    public String toString() {
        return super.toString() + "ProjectDTO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                ", sequence=" + sequence +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                ", createdUser=" + createdUser +
                ", modifiedUser=" + modifiedUser +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
