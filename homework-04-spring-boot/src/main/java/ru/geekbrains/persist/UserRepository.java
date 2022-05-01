package ru.geekbrains.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query("select u " +
            " from User u " +
            "where (u.username like concat('%', :username, '%') or :username is null) and " +
            "      (u.email like concat('%', :email, '%') or :email is null)")
    List<User> findUserByFilter(@Param("username") String username,
                                @Param("email") String email);

    @Query("select distinct u from User u left join fetch u.roles where u.username = :username")
    Optional<User> findUserByUsername(@Param("username") String username);



}