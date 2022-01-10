package br.com.adminediaristas.web.services;

import br.com.adminediaristas.core.enums.TipoUsuarioEnum;
import br.com.adminediaristas.core.exceptions.SenhaIncorretaException;
import br.com.adminediaristas.core.exceptions.SenhasNaoConferemException;
import br.com.adminediaristas.core.exceptions.UsuarioJaCadastradoException;
import br.com.adminediaristas.core.exceptions.UsuarioNotFoundException;
import br.com.adminediaristas.core.models.Usuario;
import br.com.adminediaristas.core.repositories.UsuarioRepository;
import br.com.adminediaristas.web.dtos.AlterarSenhaFormDTO;
import br.com.adminediaristas.web.dtos.UsuarioCadastroFormDTO;
import br.com.adminediaristas.web.dtos.UsuarioEdicaoFormDTO;
import br.com.adminediaristas.web.interfaces.IConfirmacaoSenha;
import br.com.adminediaristas.web.mappers.WebUsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebUsuarioService {

    private final UsuarioRepository repository;
    private final WebUsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public List<Usuario> buscarTodos() {
        return repository.findAll();
    }

    public Usuario cadastrar(UsuarioCadastroFormDTO form) {
        validarConfirmacaoSenha(form);

        var model = mapper.toModel(form);

        var senhaHash = passwordEncoder.encode(model.getSenha());

        model.setSenha(senhaHash);
        model.setTipoUsuario(TipoUsuarioEnum.ADMIN);

        validarCamposUnicos(model);

        return repository.save(model);
    }

    public Usuario buscarPorId(Long id) {
        var mensagem = String.format("Usuário com ID %d não encontrado", id);

        return repository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(mensagem));
    }

    public Usuario buscarPorEmail(String email) {
        var mensagem = String.format("Usuário com e-mail %s não encontrado", email);

        return repository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNotFoundException(mensagem));
    }

    public UsuarioEdicaoFormDTO buscarFormPorId(Long id) {
        var usuario = buscarPorId(id);

        return mapper.toForm(usuario);
    }

    public Usuario editar(UsuarioEdicaoFormDTO form, Long id) {
        var usuario = buscarPorId(id);


        var model = mapper.toModel(form);
        model.setId(usuario.getId());
        model.setSenha(usuario.getSenha());
        model.setTipoUsuario(usuario.getTipoUsuario());
        validarCamposUnicos(model);

        return repository.save(model);
    }

    public void excluirPorId(Long id) {
        var usuario = buscarPorId(id);
        repository.delete(usuario);
    }

    public void alterarSenha(AlterarSenhaFormDTO form, String email) {
        var usuario = buscarPorEmail(email);

        validarConfirmacaoSenha(form);

        var senhaAtual = usuario.getSenha();
        var senhaAntiga = form.getSenhaAntiga();
        var senha = form.getSenha();

        if(!passwordEncoder.matches(senhaAntiga, senhaAtual)) {
            var mensagem = "A senha antiga está incorreta";
            var fieldError = new FieldError(form.getClass().getName(), "senhaAntiga", senhaAntiga, false, null, null, mensagem);

            throw new SenhaIncorretaException(mensagem, fieldError);
        }

        var novaSenhaHash = passwordEncoder.encode(senha);
        usuario.setSenha(novaSenhaHash);
        repository.save(usuario);
    }

    private void validarCamposUnicos(Usuario usuario) {
        if(repository.isEmailJaCadastrado(usuario.getEmail(), usuario.getId())) {
                var mensagem = "Já existe um usuário cadastrado com esse e-mail";
                var fieldError = new FieldError(usuario.getClass().getName(), "email", usuario.getEmail(), false, null, null, mensagem);

                throw new UsuarioJaCadastradoException(mensagem, fieldError);
        }
    }

    private void validarConfirmacaoSenha(IConfirmacaoSenha obj) {
        var senha = obj.getSenha();
        var confirmacaoSenha = obj.getConfirmacaoSenha();

        if(!senha.equals(confirmacaoSenha)) {
            var mensagem = "Os dois campos de senha não conferem";
            var fieldError = new FieldError(obj.getClass().getName(), "confirmacaoSenha", obj.getConfirmacaoSenha(), false, null, null, mensagem);

            throw new SenhasNaoConferemException(mensagem, fieldError);
        }
    }
}
