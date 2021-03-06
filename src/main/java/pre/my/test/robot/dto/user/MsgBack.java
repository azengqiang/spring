package pre.my.test.robot.dto.user;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Author:qiang.zeng on 2017/2/10.
 */
@Table(name = "rb_message")
public class MsgBack {

    @Id
    @GeneratedValue()
    private Long msgid;
    /**
     * 用户外键，方便查询用户信息
     */
    private Long userid;
    /**
     * 用户输入的内容
     */
    private String userContent;
    /**
     * 机器人回复的内容
     */
    private String robotContent;
    /**
     * 创建时间
     */
    private Date creationDate;

    public Long getMsgid() {
        return msgid;
    }

    public void setMsgid(Long msgid) {
        this.msgid = msgid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUserContent() {
        return userContent;
    }

    public void setUserContent(String userContent) {
        this.userContent = userContent;
    }

    public String getRobotContent() {
        return robotContent;
    }

    public void setRobotContent(String robotContent) {
        this.robotContent = robotContent;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
