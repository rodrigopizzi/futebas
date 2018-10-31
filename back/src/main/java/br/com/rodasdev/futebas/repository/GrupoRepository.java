package br.com.rodasdev.futebas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.rodasdev.futebas.entity.Grupo;
import io.swagger.annotations.Api;

@Api(tags = "Grupo")
@RepositoryRestResource(collectionResourceRel = "grupo", path = "grupo")
public interface GrupoRepository extends PagingAndSortingRepository<Grupo, Long>, CrudRepository<Grupo, Long> {

}
