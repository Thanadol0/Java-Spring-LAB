package demo.mockito_LAB2.repository;


import demo.mockito_LAB2.model.User;

public interface UserRepository {
    User findById(Long id);
}
