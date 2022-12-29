package com.monster.history.mapper;

import com.monster.history.dto.*;
import com.monster.history.entity.Events;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    @Mapping(source = "actionType", target = "actionType", qualifiedByName = "convertToActionType")
    EventsDto mapToDto(Events events);


    Events mapToEntity(EventsDto eventsDto);

    @Named("convertToActionType")
    static ActionType convertToActionType(String actionType) {
        if (StringUtils.isNotEmpty(actionType)) {
            return ActionType.valueOf(actionType);
        }
        return null;
    }
}
