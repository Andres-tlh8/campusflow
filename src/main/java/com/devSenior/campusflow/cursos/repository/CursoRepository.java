package com.devSenior.campusflow.cursos.repository;

import com.devSenior.campusflow.cursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}