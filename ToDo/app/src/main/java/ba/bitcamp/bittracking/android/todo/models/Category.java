package ba.bitcamp.bittracking.android.todo.models;

import java.util.UUID;

/**
 * Category model describes category where task belong
 * Created by Emina on 8.1.2016.
 */
public class Category {

    private UUID id;
    private String name;

    public Category(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
