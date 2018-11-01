package br.com.rodasdev.futebas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.rodasdev.futebas.entity.Grupo;

@Repository
public interface GrupoRepository extends PagingAndSortingRepository<Grupo, Long>, CrudRepository<Grupo, Long> {

}
