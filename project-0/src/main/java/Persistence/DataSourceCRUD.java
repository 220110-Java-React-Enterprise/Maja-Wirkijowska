package Persistence;

public interface DataSourceCRUD<T> {
    //CRUD = create, read, update, delete

    public Integer create(T t);

    public T read(Integer i);

    public T update(T t);

    public void delete(Integer i);
}
