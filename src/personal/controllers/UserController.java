package personal.controllers;

import personal.model.Repository;
import personal.model.RepositoryNew;
import personal.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserController {
    private final Repository repository;

    private final RepositoryNew repositoryNew;

    public UserController(Repository repository, RepositoryNew repositoryNew) {
        this.repository = repository;
        this.repositoryNew = repositoryNew;
    }

    public void saveUser(User user) throws Exception {
        validateUser(user);
        repository.CreateUser(user);
    }

    public void saveNewUser(User user) throws Exception {
        validateUser(user);
        repositoryNew.CreateNewUser(user);
    }

    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        throw new Exception("User not found");
    }

    public User readNewUser(String userId) throws Exception {
        List<User> users = repositoryNew.getNewAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        throw new Exception("User not found");
    }

    public List<User> readList() {
        List<User> users = repository.getAllUsers();
        return users;
    }

    public List<User> readNewList() {
        List<User> users = repositoryNew.getNewAllUsers();
        return users;
    }

    public void updateUser(String idUser, User newUser) throws Exception {
        idPresenceValidation(idUser);
        newUser.setId(idUser);
        validateUserId(newUser);
        repository.updateUser(newUser);
    }

    public void updateNewUser(String idUser, User newUser) throws Exception {
        idNewPresenceValidation(idUser);
        newUser.setId(idUser);
        validateUserId(newUser);
        repositoryNew.updateNewUser(newUser);
    }

    private void validateUser(User user) throws Exception {
        if (user.getFirstName().contains(" "))
            throw new Exception("User name has unacceptable characters");
        if (user.getLastName().contains(" "))
            throw new Exception("User lastname has unacceptable characters");
    }

    private void validateUserId (User user) throws Exception{
        if (user.getId().isEmpty())
            throw new Exception("User has no id");
        validateUser(user);
    }

    public void idPresenceValidation (String inputId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User u : users) {
            if (u.getId().equals(inputId))
                return;
        }
        throw new Exception("No such ID here");
    }

    public void idNewPresenceValidation (String inputId) throws Exception {
        List<User> users = repositoryNew.getNewAllUsers();
        for (User u : users) {
            if (u.getId().equals(inputId))
                return;
        }
        throw new Exception("No such ID here");
    }

    public void deleteUser(String idUser) throws Exception {
        idPresenceValidation(idUser);
        repository.deleteUser(idUser);
    }

    public void deleteNewUser(String idUser) throws Exception {
        idPresenceValidation(idUser);
        repositoryNew.deleteNewUser(idUser);
    }
}
