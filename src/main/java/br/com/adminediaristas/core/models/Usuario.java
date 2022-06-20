package br.com.adminediaristas.core.models;

import br.com.adminediaristas.core.enums.TipoUsuarioEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {

    @EqualsAndHashCode.Include
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(name = "tipo_usuario", length = 8, nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuarioEnum tipoUsuario;

    @Column(unique = true, length = 11)
    private String cpf;

    private LocalDate nascimento;

    @Column(length = 11)
    private String telefone;

    private Double reputacao;

    @Column(name = "chave_pix", unique = true)
    private String chavePix;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "foto_documento")
    private Foto fotoDocumento;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "foto_usuario")
    private Foto fotoUsuario;

    @ManyToMany
    @JoinTable(name = "cidades_atendidas_usuarios",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "cidade_atendida_id"))
    private List<CidadeAtendida> cidadesAtendidas;
}
