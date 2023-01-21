package personal.model;

public class UserNewMapper {

    public String Newmap(User user) {
        return String.format("%s;%s;%s;%s", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone());
    }

    public User Newmap(String line) {
        String[] lines = line.split(";");
        return new User(lines[0], lines[1], lines[2], lines[3]);
    }
}
