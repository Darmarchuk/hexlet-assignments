package exercise.mapper;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.model.Task;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TaskMapper {
    @Mapping(target = "assigneeId",source = "assignee.id" )
    public abstract TaskDTO map(Task task);

    @Mapping(target = "assigneeId",source = "assignee" )
    public abstract List<TaskDTO> map(List<Task> task);

    @InheritInverseConfiguration
    public abstract Task map(TaskDTO dto);


    public abstract void update(TaskUpdateDTO dto, @MappingTarget Task task);

    @Mapping(target = "assignee.id",source = "assigneeId" )
    public abstract Task map(TaskCreateDTO dto);

}
