package com.css.aq.base.attach.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GaoTF
 * @version V1.0
 * @description: TODO
 * @date 2019/9/14 10:18
 */
@Data
public class AttachDelEntity {

    private String systemNo;
    private Integer attachFlag;
    private List<Long> ids = new ArrayList<>();

    private AttachDelEntity() {
    }

    private volatile static AttachDelEntity attachDelEntity = null;

    public static AttachDelEntity getAttachDelEntity() {
        if (attachDelEntity == null) {
            synchronized (AttachDelEntity.class) {
                if (attachDelEntity == null) {
                    attachDelEntity = new AttachDelEntity();
                }
            }
        }
        return attachDelEntity;
    }


}
