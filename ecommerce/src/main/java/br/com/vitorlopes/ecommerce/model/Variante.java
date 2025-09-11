package br.com.vitorlopes.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="tbl_variante_produto")
public class Variante {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_variante")
    private Integer id;
    @Column(name="nome", length=45)
    private String nome;
    @Column(name = "descricao",  columnDefinition = "TEXT")
    private String descricao;
    @Column(name="link_foto", length=255)
    private String linkFoto;
    @ManyToOne
    @JoinColumn(name = "id_produto")
    @JsonIgnoreProperties("variantes")
    private Produto produto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLinkFoto() {
        return linkFoto;
    }

    public void setLinkFoto(String linkFoto) {
        this.linkFoto = linkFoto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
