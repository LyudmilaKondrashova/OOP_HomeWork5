package personal.model;

import java.util.List;

public interface FileNewOperation {

    List<String> readNewAllLines();

    void saveNewAllLines(List<String> lines);
}
