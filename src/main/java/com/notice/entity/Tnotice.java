package com.notice.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_notice")
public class Tnotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * æ ‡é¢˜
     */
    @Column(name = "tn_title")
    private String tnTitle;

    /**
     * ç±»åž‹
     */
    @Column(name = "tn_type")
    private Integer tnType;

    @Column(name = "tn_content")
    private String tnContent;

    @Column(name = "gen_time")
    private Date genTime;

    @Column(name = "tu_id")
    private Integer tuId;

    @Column(name = "tu_status")
    private Integer tuStatus;

    @Column(name = "modify_time")
    private Date modifyTime;


    private String file;


    @Column(name = "public_time")
    private Date publicTime;


    private Integer views;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTnTitle() {
        return tnTitle;
    }

    public void setTnTitle(String tnTitle) {
        this.tnTitle = tnTitle == null ? null : tnTitle.trim();
    }

    public Integer getTnType() {
        return tnType;
    }

    public void setTnType(Integer tnType) {
        this.tnType = tnType;
    }

    public String getTnContent() {
        return tnContent;
    }

    /**
     * @param tnContent
     */
    public void setTnContent(String tnContent) {
        this.tnContent = tnContent == null ? null : tnContent.trim();
    }

    /**
     * @return gen_time
     */
    public Date getGenTime() {
        return genTime;
    }

    /**
     * @param genTime
     */
    public void setGenTime(Date genTime) {
        this.genTime = genTime;
    }

    /**
     * @return tu_id
     */
    public Integer getTuId() {
        return tuId;
    }

    /**
     * @param tuId
     */
    public void setTuId(Integer tuId) {
        this.tuId = tuId;
    }

    /**
     * @return tu_status
     */
    public Integer getTuStatus() {
        return tuStatus;
    }

    /**
     * @param tuStatus
     */
    public void setTuStatus(Integer tuStatus) {
        this.tuStatus = tuStatus;
    }

    /**
     * @return modify_time
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取ä¼˜å…ˆçº§
     *
     * @return file - ä¼˜å…ˆçº§
     */
    public String getFile() {
        return file;
    }

    /**
     * 设置ä¼˜å…ˆçº§
     *
     * @param file ä¼˜å…ˆçº§
     */
    public void setFile(String file) {
        this.file = file == null ? null : file.trim();
    }

    /**
     * 获取å‘å¸ƒæ—¶é—´
     *
     * @return public_time - å‘å¸ƒæ—¶é—´
     */
    public Date getPublicTime() {
        return publicTime;
    }

    /**
     * 设置å‘å¸ƒæ—¶é—´
     *
     * @param publicTime å‘å¸ƒæ—¶é—´
     */
    public void setPublicTime(Date publicTime) {
        this.publicTime = publicTime;
    }

    /**
     * 获取æµè§ˆé‡
     *
     * @return views - æµè§ˆé‡
     */
    public Integer getViews() {
        return views;
    }

    /**
     * 设置æµè§ˆé‡
     *
     * @param views æµè§ˆé‡
     */
    public void setViews(Integer views) {
        this.views = views;
    }
}