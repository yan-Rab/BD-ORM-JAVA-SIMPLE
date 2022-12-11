package project.models;

public class GenericModel {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GenericModel{" + "id=" + id + '}';
    }
}
