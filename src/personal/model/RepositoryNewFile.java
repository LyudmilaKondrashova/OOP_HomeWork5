package personal.model;

import java.util.ArrayList;
import java.util.List;

public class RepositoryNewFile implements RepositoryNew {

    private UserNewMapper newMapper = new UserNewMapper();

    private FileNewOperation fileNewOperation;

    public RepositoryNewFile(FileNewOperation fileNewOperation) {
        this.fileNewOperation = fileNewOperation;
    }

    @Override
    public List<User> getNewAllUsers() {
        List<String> lines = fileNewOperation.readNewAllLines();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(newMapper.Newmap(line));
        }
        return users;
    }

    @Override
    public String CreateNewUser(User user) {
        List<User> users = getNewAllUsers();
        int max = 0;
        for (User item : users) {
            int id = Integer.parseInt(item.getId());
            if (max < id) {
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        user.setId(id);
        users.add(user);
        writeNewDown(users);
        return id;
    }

    @Override
    public void updateNewUser(User newUser) {
        List<User> users = getNewAllUsers();
        User updateUser = users.stream().filter(i -> i.getId().equals(newUser.getId())).findFirst().get();
        updateUser.setFirstName(newUser.getFirstName());
        updateUser.setLastName(newUser.getLastName());
        updateUser.setPhone(newUser.getPhone());
        writeNewDown(users);
    }

    @Override
    public void deleteNewUser(String idUser) {
        List<User> users = getNewAllUsers();
        List<String> lines = new ArrayList<>();
        for (User item : users) {
            if (!item.getId().equals(idUser)) {
                lines.add(newMapper.Newmap(item));
            }
            fileNewOperation.saveNewAllLines(lines);
        }
    }

    private void writeNewDown(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User item : users) {
            lines.add(newMapper.Newmap(item));
        }
        fileNewOperation.saveNewAllLines(lines);
    }
}
