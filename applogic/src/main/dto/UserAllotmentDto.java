package dto;

import lombok.Data;
import model.entity.Allotment;
import model.entity.User;

@Data
public class UserAllotmentDto {

    private UserDto userDto;
    private AllotmentDto allotmentDto;

    public UserAllotmentDto(UserDto userDto, AllotmentDto allotmentDto) {
        this.userDto = userDto;
        this.allotmentDto = allotmentDto;
    }

    public UserAllotmentDto(User user, Allotment allotment) {
        this.userDto = new UserDto(user);
        this.allotmentDto = new AllotmentDto(allotment);
    }
}
