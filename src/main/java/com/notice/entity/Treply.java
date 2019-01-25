package com.notice.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_reply")
public class Treply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 回复目标id
     */
    @Column(name = "comment_id")
    private Integer commentId;

    /**
     * 空着
     */
    @Column(name = "reply_id")
    private Integer replyId;

    /**
     * 0一级1二级
     */
    @Column(name = "reply_type")
    private Integer replyType;

    /**
     * 内容
     */
    private String content;

    /**
     * session取
     */
    @Column(name = "from_name")
    private String fromName;

    /**
     * comment取
     */
    @Column(name = "to_name")
    private String toName;

    @Column(name = "r_time")
    private Date rTime;

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
     * 获取回复目标id
     *
     * @return comment_id - 回复目标id
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * 设置回复目标id
     *
     * @param commentId 回复目标id
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * 获取空着
     *
     * @return reply_id - 空着
     */
    public Integer getReplyId() {
        return replyId;
    }

    /**
     * 设置空着
     *
     * @param replyId 空着
     */
    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    /**
     * 获取0一级1二级
     *
     * @return reply_type - 0一级1二级
     */
    public Integer getReplyType() {
        return replyType;
    }

    /**
     * 设置0一级1二级
     *
     * @param replyType 0一级1二级
     */
    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取session取
     *
     * @return from_name - session取
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * 设置session取
     *
     * @param fromName session取
     */
    public void setFromName(String fromName) {
        this.fromName = fromName == null ? null : fromName.trim();
    }

    /**
     * 获取comment取
     *
     * @return to_name - comment取
     */
    public String getToName() {
        return toName;
    }

    /**
     * 设置comment取
     *
     * @param toName comment取
     */
    public void setToName(String toName) {
        this.toName = toName == null ? null : toName.trim();
    }

    /**
     * @return r_time
     */
    public Date getrTime() {
        return rTime;
    }

    /**
     * @param rTime
     */
    public void setrTime(Date rTime) {
        this.rTime = rTime;
    }
}