package com.fiap.synthia.empresa.dados;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DadosRepository extends JpaRepository<DadosModel, Long> {

    @Query("SELECT d FROM DadosModel d WHERE d.usermodel.id = :userId")
    List<DadosModel> findByUserId(@Param("userId") Long userId);
}