package dw.mensalista.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.ManyToOne;

@Entity
@Table(name="pagamento")
public class Pagamento implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long codPagamento;

    @Column(name="ano")
    private short ano;

    @Column(name="mes")
    private byte mes;

    @Column(name="valor")
    private float valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jogador_cod_jogador", nullable = false)
    @JsonIgnore
    protected Jogador jogador;
    
    public long getCodPagamento() {
        return codPagamento;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Pagamento(){}

    public Pagamento(short ano, byte mes, float valor) {
        this.ano = ano;
        this.mes = mes;
        this.valor = valor;
    }

    public short getAno() {
        return ano;
    }

    public void setAno(short ano) {
        this.ano = ano;
    }

    public byte getMes() {
        return mes;
    }

    public void setMes(byte mes) {
        this.mes = mes;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Pagamento [ano=" + ano + ", codPagamento=" + codPagamento + ", jogador=" + jogador.getNome() + ", mes=" + mes
                + ", valor=" + valor + "]";
    }

    
}