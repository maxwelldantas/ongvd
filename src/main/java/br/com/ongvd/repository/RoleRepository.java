package br.com.ongvd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ongvd.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {}
