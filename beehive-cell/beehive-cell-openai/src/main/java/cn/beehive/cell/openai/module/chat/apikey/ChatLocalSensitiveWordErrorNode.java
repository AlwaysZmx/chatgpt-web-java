package cn.beehive.cell.openai.module.chat.apikey;

import cn.beehive.base.domain.entity.RoomOpenAiChatMsgDO;
import cn.beehive.base.handler.SensitiveWordHandler;
import cn.beehive.cell.core.hander.strategy.DataWrapper;
import cn.beehive.cell.openai.enums.OpenAiChatCellConfigCodeEnum;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author hncboy
 * @date 2023/6/10
 * 本地敏感词库校验错误处理节点
 */
@Component
public class ChatLocalSensitiveWordErrorNode implements ChatErrorNode {

    @Override
    public Pair<Boolean, String> doHandle(RoomOpenAiChatMsgDO questionMessage, Map<OpenAiChatCellConfigCodeEnum, DataWrapper> roomConfigParamAsMap) {
        List<String> userMessageSensitiveWords = SensitiveWordHandler.checkWord(questionMessage.getContent());
        List<String> systemMessageSensitiveWords = SensitiveWordHandler.checkWord(roomConfigParamAsMap.get(OpenAiChatCellConfigCodeEnum.SYSTEM_MESSAGE).asString());
        if (CollectionUtil.isEmpty(userMessageSensitiveWords) && CollectionUtil.isEmpty(systemMessageSensitiveWords)) {
            return new Pair<>(true, null);
        }

        if (CollectionUtil.isNotEmpty(systemMessageSensitiveWords)) {
            return new Pair<>(false, StrUtil.format("发送失败，系统消息包含敏感词{}", systemMessageSensitiveWords));
        }

        return new Pair<>(false, StrUtil.format("发送失败，发送消息包含敏感词{}", userMessageSensitiveWords));
    }
}
