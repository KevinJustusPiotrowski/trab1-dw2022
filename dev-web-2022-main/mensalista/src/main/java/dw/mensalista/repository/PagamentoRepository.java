package dw.mensalista.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dw.mensalista.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    
}
