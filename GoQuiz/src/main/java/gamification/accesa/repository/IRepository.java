package gamification.accesa.repository;

public interface IRepository<T>{
    T findOne(Long id);
    Iterable<T> findAll();
}
