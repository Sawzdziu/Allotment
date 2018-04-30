package dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Allotment;
import model.entity.User;

@Data
@NoArgsConstructor
public class UserAllotmentDto {

    private UserDto userDto;
    private AllotmentDto allotmentDto;
    private Boolean active;

    public UserAllotmentDto(UserDto userDto, AllotmentDto allotmentDto) {
        this.userDto = userDto;
        this.allotmentDto = allotmentDto;
    }

    public UserAllotmentDto(UserDto userDto, AllotmentDto allotmentDto, Boolean active) {
        this.userDto = userDto;
        this.allotmentDto = allotmentDto;
        this.active = active;
    }

    public UserAllotmentDto(User user, Allotment allotment, Boolean active) {
        this.userDto = new UserDto(user);
        this.allotmentDto = new AllotmentDto(allotment);
        this.active = active;
    }

    public UserAllotmentDto(Allotment allotment, Boolean active) {
        this.allotmentDto = new AllotmentDto(allotment);
        this.active = active;
    }
}
