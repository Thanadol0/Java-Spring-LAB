package demo.mockito_LAB1.repository;

import demo.mockito_LAB1.model.User;

public interface UserRepository {
    User findById(Long id);
}
