package cn.beehive.cell.openai.domain.vo;

import cn.beehive.base.enums.MessageStatusEnum;
import cn.beehive.base.enums.MessageTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author hncboy
 * @date 2023/6/3
 * OpenAi 图像房间消息展示参数
 */
@Data
@Schema(title = "OpenAi 图像房间消息展示参数")
public class RoomOpenAiImageMsgVO {

    @Schema(title = "主键")
    private Long id;

    @Schema(title = "输入内容")
    private String prompt;

    @Schema(title = "尺寸大小")
    private String size;

    @Schema(title = "OpenAi 图像 url")
    private String openaiImageUrl;

    @Schema(title = "图像 url")
    private String imageUrl;

    @Schema(title = "消息类型")
    private MessageTypeEnum messageType;

    @Schema(title = "消息状态")
    private MessageStatusEnum status;

    @Schema(title = "创建时间")
    private Date createTime;
}
