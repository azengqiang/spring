package pre.my.test.robot.mapper;

import pre.my.test.robot.dto.autoresponse.AutoResponseMessage;
import pre.my.test.robot.service.AutoResponseService;

import java.util.List;

/**
 * Author:qiang.zeng on 2017/4/21.
 */
public interface AutoResponseMapper {
    void save(AutoResponseMessage autoResponseMessage);

    void delete(AutoResponseMessage autoResponseMessage);

    List<AutoResponseMessage> select(AutoResponseMessage autoResponseMessage);
}