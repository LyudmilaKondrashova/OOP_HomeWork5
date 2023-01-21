package personal.model;

import java.util.List;

public interface RepositoryNew {

    List<User> getNewAllUsers();

    String CreateNewUser(User user);

    void updateNewUser(User newUser);

    void deleteNewUser(String idUser);
}
