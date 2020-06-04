package cn.ibdsr.web.modular.software.transfer;

import cn.ibdsr.core.base.dto.BaseDTO;
import cn.ibdsr.core.check.Verfication;
import cn.ibdsr.web.common.persistence.model.Software;
import cn.ibdsr.web.core.util.RegUtil;

import java.util.Date;

/**
 * @Description
 * @Version V1.0
 * @CreateDate 2019/7/11 16:28
 * .
 * Date           Author               Description
 * ------------------------------------------------------
 * 2019/7/11       Piht            类说明
 */
public class SoftwareDTO extends BaseDTO {

    /**
     * 主键Id
     */
    private Long id;
    /**
     * 项目Id
     */
    @Verfication(name = "项目Id", notNull = true)
    private Long projectId;
    /**
     * 软件代号
     */
    @Verfication(name = "软件代号", notNull = true, unique="code", entity = Software.class, maxlength=30,
            regx ={RegUtil.SOFTWARE_CODE, "软件代号格式有误"})
    private String code;
    /**
     * 名称
     */
    @Verfication(name = "软件名称", notNull = true, unique="name", entity = Software.class, maxlength =30 )
    private String name;
    /**
     * 类型（关联dict表num）
     */
    @Verfication(name = "软件类型", notNull = true )
    private Integer type;
    /**
     * 说明
     */
    @Verfication(name = "软件说明", maxlength =50 )
    private String description;
    /**
     * 排序
     */
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
        return super.toString() + "SoftwareDTO{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", sequence=" + sequence +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                ", createdUser=" + createdUser +
                ", modifiedUser=" + modifiedUser +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
