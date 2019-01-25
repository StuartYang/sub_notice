package com.notice.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_comment")
public class Tcomment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 通知id(外键)
     */
    @Column(name = "topic_id")
    private Integer topicId;

    /**
     * 话题类型
     */
    @Column(name = "topic_type")
    private String topicType = "1";

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论用户id（外键）
     */
    @Column(name = "from_name")
    private String fromName;

    /**
     * 评论的时间
     */
    @Column(name = "c_time")
    private Date cTime;

    @Column(name = "c_rnum")

    private Integer cRnum = 0;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取通知id(外键)
     *
     * @return topic_id - 通知id(外键)
     */
    public Integer getTopicId() {
        return topicId;
    }

    /**
     * 设置通知id(外键)
     *
     * @param topicId 通知id(外键)
     */
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    /**
     * 获取话题类型
     *
     * @return topic_type - 话题类型
     */
    public String getTopicType() {
        return topicType;
    }

    /**
     * 设置话题类型
     *
     * @param topicType 话题类型
     */
    public void setTopicType(String topicType) {
        this.topicType = topicType == null ? null : topicType.trim();
    }

    /**
     * 获取评论内容
     *
     * @return content - 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     *
     * @param content 评论内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取评论用户id（外键）
     *
     * @return from_name - 评论用户id（外键）
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * 设置评论用户id（外键）
     *
     * @param fromName 评论用户id（外键）
     */
    public void setFromName(String fromName) {
        this.fromName = fromName == null ? null : fromName.trim();
    }

    /**
     * 获取评论的时间
     *
     * @return c_time - 评论的时间
     */
    public Date getcTime() {
        return cTime;
    }

    /**
     * 设置评论的时间
     *
     * @param cTime 评论的时间
     */
    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    /**
     * @return c_rnum
     */
    public Integer getcRnum() {
        return cRnum;
    }

    /**
     * @param cRnum
     */
    public void setcRnum(Integer cRnum) {
        this.cRnum = cRnum;
    }
}