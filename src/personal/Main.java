package personal;

import personal.controllers.UserController;
import personal.model.*;
import personal.views.ViewUser;

public class Main {
    public static void main(String[] args) {
        FileOperation fileOperation = new FileOperationImpl("users.txt");
        FileNewOperationImpl fileNewOperation = new FileNewOperationImpl("NewUsers.txt");
        Repository repository = new RepositoryFile(fileOperation);
        RepositoryNew repositoryNew = new RepositoryNewFile(fileNewOperation);
        UserController controller = new UserController(repository, repositoryNew);
        ViewUser view = new ViewUser(controller);
        view.run();
    }
}
