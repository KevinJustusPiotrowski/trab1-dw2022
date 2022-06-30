package dw.mensalista.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "jogador")
public class Jogador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long codJogador;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nasc")
    private Date dataNasc;

    @OneToMany(mappedBy = "jogador", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    protected List<Pagamento> pagamentos;

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    protected Jogador() {
    }

    public Jogador(String nome, String email, Date dataNasc) {
        this.nome = nome;
        this.email = email;
        this.dataNasc = dataNasc;
    }

    public long getCodJogador() {
        return codJogador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    @Override
    public String toString() {
        return "Jogador [codJogador=" + codJogador + ", dataNasc=" + dataNasc + ", email=" + email + ", nome=" + nome
                + ", pagamentos=" + pagamentos + "]";
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }
}