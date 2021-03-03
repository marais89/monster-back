package com.monster.history.mapper;

import com.monster.history.dto.ActionType;
import com.monster.history.dto.EventsDto;
import com.monster.history.entity.Events;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    @Mapping(source = "actionType", target = "actionType", qualifiedByName = "convertToActionType")
    @Mapping(source = "id.username", target = "username")
    @Mapping(source = "id.datetime", target = "datetime")
    EventsDto mapToDto(Events events);

    @Mapping(source = "username", target = "id.username")
    @Mapping(source = "datetime", target = "id.datetime")
    Events mapToEntity(EventsDto eventsDto);

    @Named("convertToActionType")
    static ActionType convertToActionType(String actionType) {
        if (StringUtils.isNotEmpty(actionType)) {
            return ActionType.valueOf(actionType);
        }
        return null;
    }
}
